# Find K-Length Substrings With No Repeated Characters

## Problem Statement

Given a string `s` and an integer `k`, return the number of substrings in `s` of length `k` with no repeated characters.

## Examples

**Example 1:**
```
Input: s = "havefunonleetcode", k = 5
Output: 6
Explanation: The 6 substrings of length 5 with no repeated characters are:
"havefu", "avefun", "vefuno", "efunon", "funont", "unonle"
```

**Example 2:**
```
Input: s = "home", k = 5
Output: 0
Explanation: There are no substrings of length 5 in the given string.
```

## Constraints
- 1 <= s.length <= 10^4
- s consists of lowercase English letters.
- 1 <= k <= 26

## Approach: Sliding Window

This problem is a perfect candidate for the sliding window approach since we need to find substrings of a fixed length `k`. We'll use a sliding window of size `k` and check if each window contains unique characters.

### Algorithm:

1. Initialize a sliding window of size `k`
2. Use a frequency map or HashSet to track character occurrences within the window
3. For each window, check if all characters are unique
4. If yes, increment our count of valid substrings
5. Slide the window by one character and repeat

## Code Solution

```java
public class KLengthSubstringsWithNoRepeatedCharacters {
    public int numKLenSubstrNoRepeats(String s, int k) {
        // If string length is less than k, return 0
        if (s.length() < k) {
            return 0;
        }
        
        int count = 0;
        Set<Character> charSet = new HashSet<>();
        
        // Check the first window
        boolean isValid = true;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (charSet.contains(c)) {
                isValid = false;
            }
            charSet.add(c);
        }
        
        if (isValid) {
            count++;
        }
        
        // Slide the window
        for (int i = k; i < s.length(); i++) {
            // Remove the character leaving the window
            char outgoing = s.charAt(i - k);
            charSet.remove(outgoing);
            
            // Add the character entering the window
            char incoming = s.charAt(i);
            
            // If the new character is already in the set, this window has a duplicate
            if (charSet.contains(incoming)) {
                isValid = false;
            } else {
                // Re-evaluate if the window is valid after removing the outgoing character
                charSet.add(incoming);
                isValid = (charSet.size() == k);
            }
            
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
}
```

## Optimized Solution

The previous solution has a flaw in how it handles the validity check after sliding the window. Let's optimize it:

```java
public class KLengthSubstringsOptimized {
    public int numKLenSubstrNoRepeats(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        
        int count = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        
        // Process the first window
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        
        // If all characters in the first window are unique, count it
        if (charFreq.size() == k) {
            count++;
        }
        
        // Slide the window
        for (int i = k; i < s.length(); i++) {
            // Remove the character leaving the window
            char outgoing = s.charAt(i - k);
            charFreq.put(outgoing, charFreq.get(outgoing) - 1);
            if (charFreq.get(outgoing) == 0) {
                charFreq.remove(outgoing);
            }
            
            // Add the character entering the window
            char incoming = s.charAt(i);
            charFreq.put(incoming, charFreq.getOrDefault(incoming, 0) + 1);
            
            // If all characters in current window are unique, count it
            if (charFreq.size() == k) {
                count++;
            }
        }
        
        return count;
    }
}
```

## A More Efficient Approach

Since we know that all characters must be unique in our k-length substring, we can use a more efficient approach by maintaining the last position of each character:

```java
public class KLengthSubstringsMostEfficient {
    public int numKLenSubstrNoRepeats(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        
        int count = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If we've seen this character before and it's within our current window
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                // Move left pointer to position after the last occurrence of this character
                left = lastSeen.get(c) + 1;
            }
            
            // Update the last seen position of current character
            lastSeen.put(c, right);
            
            // If our window is exactly of size k and has no repeats, count it
            if (right - left + 1 == k) {
                count++;
                // Move left pointer to consider next window
                left++;
            }
        }
        
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character once.
- **Space Complexity**: O(min(k, 26)) where k is the window size. Since we're dealing with lowercase English letters, our space usage is bounded by the alphabet size (26).

## Key Insights

1. This is a classic fixed-size sliding window problem, but with the additional constraint that all characters must be unique.
2. The key is efficiently tracking uniqueness as we slide the window.
3. Using a frequency map or set helps us quickly check if all characters in the current window are unique.
4. The most efficient approach uses a map to track the last position of each character, allowing us to jump the left pointer when we encounter a repeat.
5. The constraint that k ≤ 26 is significant because it means a valid substring can never have more characters than the English alphabet.
6. This problem demonstrates how the sliding window technique can be combined with hash-based data structures to efficiently solve substring problems.
