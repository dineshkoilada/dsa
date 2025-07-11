# Longest Substring with At Most K Distinct Characters

## Problem Statement

Given a string `s` and an integer `k`, return the length of the longest substring of `s` that contains at most `k` distinct characters.

## Examples

**Example 1:**
```
Input: s = "eceba", k = 2
Output: 3
Explanation: The substring "ece" contains 2 distinct characters 'e' and 'c'.
```

**Example 2:**
```
Input: s = "aa", k = 1
Output: 2
Explanation: The substring "aa" contains 1 distinct character 'a'.
```

## Approach: Sliding Window with HashMap

This problem is a perfect fit for the sliding window technique because we're looking for a substring (contiguous sequence) with a specific property - having at most `k` distinct characters.

### Algorithm:

1. Use two pointers `left` and `right` to define the current window
2. Use a HashMap to track the frequency of characters in the current window
3. Expand the window to the right as long as we have at most `k` distinct characters
4. When we encounter a new character that would exceed `k` distinct characters, shrink the window from the left
5. Keep track of the maximum window size seen so far

## Code Solution

```java
import java.util.*;

public class LongestSubstringWithKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        // Map to store characters and their frequencies in the current window
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Add the current character to the window
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Shrink window until we have at most k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                
                left++;
            }
            
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Optimized Solution using LinkedHashMap

We can optimize our solution by using a LinkedHashMap to efficiently find the character with the oldest occurrence:

```java
import java.util.*;

public class LongestSubstringWithKDistinctOptimized {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        // LinkedHashMap to track characters and their most recent positions
        Map<Character, Integer> lastPosition = new LinkedHashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // If character already exists, remove it first to update its position
            if (lastPosition.containsKey(rightChar)) {
                lastPosition.remove(rightChar);
            }
            
            // Add/update the character with its current position
            lastPosition.put(rightChar, right);
            
            // If we have more than k distinct characters, remove the oldest one
            if (lastPosition.size() > k) {
                // Get the character with the smallest index (oldest occurrence)
                Map.Entry<Character, Integer> oldest = lastPosition.entrySet().iterator().next();
                left = oldest.getValue() + 1;
                lastPosition.remove(oldest.getKey());
            }
            
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) for both solutions, where n is the length of the string. We process each character at most twice.
- **Space Complexity**: O(k) for both solutions, as we store at most k+1 characters in the HashMap.

## Key Insights

1. This problem demonstrates a classic sliding window pattern with a variable-size window constrained by the number of distinct characters.

2. The HashMap approach is intuitive and straightforward, especially for beginners. It keeps track of character frequencies and allows us to shrink the window efficiently.

3. The LinkedHashMap optimization leverages the fact that LinkedHashMap maintains insertion order, which helps us quickly find the character with the oldest occurrence when we need to shrink the window.

4. This problem is similar to "Longest Substring Without Repeating Characters" but with a more general constraint of at most k distinct characters instead of no repeating characters.

5. The solution gracefully handles edge cases like empty strings and k=0.

6. This problem forms the foundation for more complex sliding window problems that involve character frequency constraints.
