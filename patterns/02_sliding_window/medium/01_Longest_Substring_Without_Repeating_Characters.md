# Longest Substring Without Repeating Characters

## Problem Statement

Given a string `s`, find the length of the longest substring without repeating characters.

## Examples

**Example 1:**
```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

**Example 2:**
```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**
```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Approach: Sliding Window with HashSet

This problem is a classic application of the sliding window pattern with a variable-sized window. We use a HashSet to efficiently check for repeating characters.

### Algorithm:

1. Initialize a sliding window with two pointers: `left` and `right`, both starting at the beginning of the string
2. Use a HashSet to keep track of characters in the current window
3. Expand the window (move `right` pointer) as long as the new character isn't a duplicate
4. When we encounter a duplicate, shrink the window from the left until we remove the duplicate
5. Keep track of the maximum window size seen so far

## Code Solution

```java
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // Edge case: empty string
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0, right = 0;
        
        // Expand window to the right
        while (right < n) {
            char currentChar = s.charAt(right);
            
            // If the character is already in our window, shrink window from left
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            
            // Add current character to set and expand window
            charSet.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        
        return maxLength;
    }
}
```

## Optimization: Using HashMap for Direct Jumping

We can further optimize by using a HashMap to store the index of each character, allowing us to directly jump the `left` pointer when we encounter a duplicate:

```java
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharactersOptimized {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        
        // Try to extend the range [left, right]
        for (int left = 0, right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            
            // If the character is already in the map, move left pointer to position after the duplicate
            if (charMap.containsKey(currentChar)) {
                left = Math.max(left, charMap.get(currentChar) + 1);
            }
            
            // Update maximum length and character position
            maxLength = Math.max(maxLength, right - left + 1);
            charMap.put(currentChar, right);
        }
        
        return maxLength;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. Each character is visited at most twice (once by the right pointer and once by the left pointer).
- **Space Complexity**: O(min(m, n)) where m is the size of the character set. In the worst case, the HashMap or HashSet will contain each character in the string.

## Key Insights

1. This problem showcases a dynamic sliding window where we need to adjust both ends of the window.
2. Using a HashSet provides an O(1) lookup time for checking if a character exists in our current window.
3. The optimized approach using a HashMap allows us to jump the left pointer directly to the position after the duplicate, reducing unnecessary shrinking steps.
4. This is a canonical example of the sliding window pattern applied to string problems involving unique characters.
5. The technique of expanding and shrinking the window based on a condition (no repeating characters) is a fundamental sliding window concept.
