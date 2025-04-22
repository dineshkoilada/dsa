# Minimum Window Substring (LeetCode #76)

## Problem Statement
Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

The testcases will be generated such that the answer is unique.

## Example 1:
```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

## Example 2:
```
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
```

## Example 3:
```
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
```

## Constraints:
- m == s.length
- n == t.length
- 1 <= m, n <= 10^5
- s and t consist of uppercase and lowercase English letters.

## Two Pointers Approach
```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        
        // Dictionary to keep track of character counts
        Map<Character, Integer> targetCounts = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCounts.put(c, targetCounts.getOrDefault(c, 0) + 1);
        }
        
        int required = targetCounts.size(); // Number of distinct characters needed
        int formed = 0; // Number of distinct characters formed so far
        
        // Dictionary to keep track of current window character counts
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        // Result variables
        int[] result = {-1, 0, 0}; // [window length, left, right]
        
        // Two pointers
        int left = 0, right = 0;
        
        while (right < s.length()) {
            // Add one character from the right
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            
            // Check if we've met the requirement for this character
            if (targetCounts.containsKey(c) && windowCounts.get(c).intValue() == targetCounts.get(c).intValue()) {
                formed++;
            }
            
            // Try to contract the window from the left
            while (left <= right && formed == required) {
                c = s.charAt(left);
                
                // Update the result if this is a smaller window
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }
                
                // Remove the leftmost character
                windowCounts.put(c, windowCounts.get(c) - 1);
                
                // Check if we still satisfy the requirement for this character
                if (targetCounts.containsKey(c) && windowCounts.get(c).intValue() < targetCounts.get(c).intValue()) {
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

## Time Complexity
O(n + m) where n is the length of string s and m is the length of string t.

## Space Complexity
O(n) for the dictionaries used to count characters.