# Longest Substring with At Most Two Distinct Characters

## Problem Statement

Given a string `s`, return the length of the longest substring that contains at most two distinct characters.

## Examples

**Example 1:**
```
Input: s = "eceba"
Output: 3
Explanation: The substring "ece" contains 2 distinct characters.
```

**Example 2:**
```
Input: s = "ccaabbb"
Output: 5
Explanation: The substring "aabbb" contains 2 distinct characters.
```

## Approach: Variable-Size Sliding Window with HashMap

This problem requires a sliding window with a variable size that expands and contracts based on the number of distinct characters in the current window.

### Algorithm:

1. Use a HashMap to track the frequency of each character in the current window
2. Maintain two pointers: `left` and `right` to define the window boundaries
3. Extend the window by moving the `right` pointer and adding characters
4. When the window contains more than 2 distinct characters, shrink it from the left
5. Track the maximum window size seen so far

## Code Solution

```java
import java.util.*;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Use a map to track character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Add current character to window
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Shrink window while we have more than 2 distinct characters
            while (charCount.size() > 2) {
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

## Optimized Solution Using Character Array

For strings containing only ASCII characters, we can optimize space usage by using an array instead of a HashMap:

```java
public class LongestSubstringWithAtMostTwoDistinctCharactersOptimized {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] count = new int[128]; // Assuming ASCII
        int distinctCount = 0;
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            if (count[s.charAt(right)]++ == 0) {
                distinctCount++;
            }
            
            while (distinctCount > 2) {
                if (--count[s.charAt(left)] == 0) {
                    distinctCount--;
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Further Optimization Using Last Position Map

We can optimize further by tracking the last position of each character:

```java
import java.util.*;

public class LongestSubstringWithAtMostTwoDistinctCharactersLastPos {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> lastPosition = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            lastPosition.put(rightChar, right);
            
            // If we have more than 2 distinct characters, remove the leftmost character
            if (lastPosition.size() > 2) {
                // Find the character with the smallest last position
                int leftmostPosition = Collections.min(lastPosition.values());
                char leftmostChar = ' ';
                
                for (Map.Entry<Character, Integer> entry : lastPosition.entrySet()) {
                    if (entry.getValue() == leftmostPosition) {
                        leftmostChar = entry.getKey();
                        break;
                    }
                }
                
                // Remove the leftmost character and update left pointer
                lastPosition.remove(leftmostChar);
                left = leftmostPosition + 1;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Complexity Analysis

- **HashMap Approach**:
  - **Time Complexity**: O(n) where n is the length of the string. We process each character exactly once.
  - **Space Complexity**: O(1) as we store at most 3 character-count pairs in the HashMap.

- **Character Array Approach**:
  - **Time Complexity**: O(n)
  - **Space Complexity**: O(1) for a fixed-size array of 128 elements.

- **Last Position Map Approach**:
  - **Time Complexity**: O(n) with a slightly higher constant factor due to finding the minimum position.
  - **Space Complexity**: O(1) as we store at most 3 character-position pairs.

## Key Insights

1. This problem demonstrates the variable-size sliding window pattern with a constraint on the maximum number of distinct elements.
2. The HashMap approach provides a general solution that can be extended to handle any number of distinct characters.
3. For a small number of distinct characters (like 2), we can optimize the solution using a fixed-size array or tracking last positions.
4. The solution pattern can be generalized to the "Longest Substring with At Most K Distinct Characters" problem by replacing the constant 2 with k.
5. This pattern applies to many similar problems involving substrings with constraints on the types of characters they contain.
