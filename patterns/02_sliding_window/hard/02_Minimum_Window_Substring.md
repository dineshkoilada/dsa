# Minimum Window Substring

## Problem Statement

Given two strings `s` and `t`, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

The testcases will be generated such that the answer is unique.

## Examples

**Example 1:**
```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

**Example 2:**
```
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
```

**Example 3:**
```
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
```

## Approach: Sliding Window with Two Pointers

We'll use a sliding window approach with two pointers to find the minimum window substring:

### Algorithm:

1. Create frequency maps for characters in string `t`
2. Initialize two pointers (`left` and `right`) at the beginning of string `s`
3. Expand the window by moving the `right` pointer until all characters in `t` are included
4. Once we find a valid window, try to minimize it by moving the `left` pointer
5. Keep track of the minimum window seen so far

### Detailed Steps:

1. Create a frequency map `targetMap` for characters in string `t`
2. Initialize `left = 0`, `right = 0`, and variables to track:
   - `formed`: Count of characters matched with required frequency
   - `required`: Number of distinct characters in `t`
   - `windowMap`: Frequency map for current window
3. Move the `right` pointer to expand the window until all characters are found
4. Once a valid window is found, move the `left` pointer to minimize the window
5. Update the minimum window whenever a smaller valid window is found

## Code Solution

```java
import java.util.*;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        
        // Dictionary to keep count of characters in t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        
        // Number of unique characters in t
        int required = targetMap.size();
        
        // Number of unique characters matched in current window
        int formed = 0;
        
        // Dictionary to keep count of characters in current window
        Map<Character, Integer> windowMap = new HashMap<>();
        
        // Variables to store minimum window substring info
        int[] result = {-1, 0, 0}; // [window length, left, right]
        
        // Sliding window pointers
        int left = 0, right = 0;
        
        while (right < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            
            // Check if we have matched a character with required frequency
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                formed++;
            }
            
            // Try to minimize the window by moving left pointer
            while (left <= right && formed == required) {
                c = s.charAt(left);
                
                // Update result if current window is smaller
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }
                
                // Remove the leftmost character
                windowMap.put(c, windowMap.get(c) - 1);
                
                // If the removed character was needed, decrement formed
                if (targetMap.containsKey(c) && windowMap.get(c).intValue() < targetMap.get(c).intValue()) {
                    formed--;
                }
                
                left++;
            }
            
            right++;
        }
        
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n + m) where n is the length of string s and m is the length of string t.
- **Space Complexity**: O(n + m) for the two hash maps to store character frequencies.

## Key Insights

1. This problem demonstrates the variable-size sliding window pattern. Unlike fixed-size windows, we need to dynamically shrink and expand the window.
2. Using hash maps to keep track of character frequencies is an effective way to determine if a window contains all required characters.
3. The optimization of shrinking the window as soon as we have a valid match helps find the minimum window efficiently.
4. This problem is a classic example of how the sliding window technique can be applied to string problems involving substrings.
5. The two-pointer approach (left and right) allows us to avoid rechecking the entire string for each potential window.
