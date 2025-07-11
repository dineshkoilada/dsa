# Longest Substring with At Most K Distinct Characters

## Problem Statement

Given a string `s` and an integer `k`, return the length of the longest substring of `s` that contains at most `k` distinct characters.

## Examples

**Example 1:**
```
Input: s = "eceba", k = 2
Output: 3
Explanation: The substring "ece" contains 2 distinct characters.
```

**Example 2:**
```
Input: s = "aa", k = 1
Output: 2
Explanation: The substring "aa" contains 1 distinct character.
```

## Approach: Sliding Window with HashMap

This problem can be solved efficiently using a sliding window approach with a HashMap to track character frequencies. The window will expand until we have more than `k` distinct characters, then contract from the left.

### Algorithm:

1. Maintain a HashMap to track characters and their frequencies in the current window
2. Expand the window by moving the right pointer and adding characters
3. When the number of distinct characters exceeds `k`, shrink the window from the left
4. Keep track of the maximum window size seen so far
5. Return the maximum length

## Code Solution

```java
import java.util.*;

public class LongestSubstringWithKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        // HashMap to track character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Add current character to the HashMap
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
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Optimization: Using a LinkedHashMap

We can optimize further by using a LinkedHashMap, which maintains the order of insertion, to quickly find the character to remove when shrinking the window:

```java
import java.util.*;

public class LongestSubstringWithKDistinctOptimized {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        // Maps characters to their rightmost position
        Map<Character, Integer> lastPosition = new LinkedHashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // If character is already in map, remove its old position
            if (lastPosition.containsKey(rightChar)) {
                lastPosition.remove(rightChar);
            }
            
            // Add current character with its current position
            lastPosition.put(rightChar, right);
            
            // If we have more than k distinct characters
            if (lastPosition.size() > k) {
                // Get the character with the leftmost position
                Map.Entry<Character, Integer> leftmost = lastPosition.entrySet().iterator().next();
                
                // Update left pointer
                left = leftmost.getValue() + 1;
                
                // Remove the leftmost character
                lastPosition.remove(leftmost.getKey());
            }
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. Although we have nested loops, each character is processed at most twice (once when added to the window and once when removed).
- **Space Complexity**: O(min(k, m)) where m is the size of the character set. The HashMap will contain at most k+1 characters at any time.

## Key Insights

1. This problem demonstrates a variable-size sliding window with a constraint on the number of distinct characters.
2. Using a HashMap efficiently keeps track of character frequencies and allows us to quickly check if our window has at most k distinct characters.
3. When we exceed the limit, we shrink the window from the left until the constraint is satisfied again.
4. The algorithm works by continuously finding the longest valid window as we process the string.
5. This pattern is commonly used for problems involving substring constraints on character diversity.
