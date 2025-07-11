# Find All Anagrams in a String

## Problem Statement

Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Examples

**Example 1:**
```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```

**Example 2:**
```
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

## Approach: Sliding Window with Character Frequency Matching

This problem is similar to finding a permutation in a string but requires collecting all matching positions. We'll use a fixed-size sliding window with character frequency matching.

### Algorithm:

1. Create frequency maps for characters in pattern `p`
2. Use a sliding window of size equal to the length of `p` in string `s`
3. For each window, check if the character frequencies match those in `p`
4. If there's a match, add the starting index to the result
5. Slide the window one character at a time and repeat

## Code Solution

```java
import java.util.*;

public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }
        
        // Create frequency arrays
        int[] pCount = new int[26];
        int[] windowCount = new int[26];
        
        // Initialize frequency array for pattern p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        // Initial window
        for (int i = 0; i < p.length(); i++) {
            windowCount[s.charAt(i) - 'a']++;
        }
        
        // Check first window
        if (Arrays.equals(pCount, windowCount)) {
            result.add(0);
        }
        
        // Slide window
        for (int i = p.length(); i < s.length(); i++) {
            // Add new character to window
            windowCount[s.charAt(i) - 'a']++;
            
            // Remove character outside the window
            windowCount[s.charAt(i - p.length()) - 'a']--;
            
            // Check for anagram
            if (Arrays.equals(pCount, windowCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
}
```

## Optimized Solution

We can optimize the solution by avoiding the array comparison in each window check:

```java
import java.util.*;

public class FindAllAnagramsInStringOptimized {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }
        
        int[] count = new int[26];
        
        // Count characters in pattern p
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        
        int matches = 0;
        int distinct = 0;
        
        // Count distinct characters in p
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                distinct++;
            }
        }
        
        int left = 0, right = 0;
        
        // Slide window through s
        while (right < s.length()) {
            // Process the character entering the window
            int rightChar = s.charAt(right) - 'a';
            count[rightChar]--;
            
            if (count[rightChar] == 0) {
                matches++;
            } else if (count[rightChar] == -1) {
                matches--;
            }
            
            right++;
            
            // Check if window size equals pattern length
            if (right - left == p.length()) {
                // If matches equals distinct, we found an anagram
                if (matches == distinct) {
                    result.add(left);
                }
                
                // Process the character leaving the window
                int leftChar = s.charAt(left) - 'a';
                count[leftChar]++;
                
                if (count[leftChar] == 0) {
                    matches++;
                } else if (count[leftChar] == 1) {
                    matches--;
                }
                
                left++;
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of string `s`. We process each character once.
- **Space Complexity**: O(1) for the frequency arrays since they have a fixed size of 26. The result list isn't counted in space complexity analysis.

## Key Insights

1. This problem extends the "Permutation in String" concept to finding all permutation starting positions.
2. The fixed-size sliding window approach is ideal for anagram problems because we're looking for substrings of a specific length.
3. Using character frequency matching instead of generating all permutations significantly reduces time complexity from O(n! * n) to O(n).
4. The optimized solution tracks matches incrementally rather than comparing full arrays, which is more efficient for long inputs.
5. This pattern of using a sliding window for pattern matching is common in string processing algorithms.
