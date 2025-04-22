# Longest Substring Without Repeating Characters (LeetCode #3)

## Problem Statement
Given a string `s`, find the length of the longest substring without repeating characters.

## Example 1:
```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

## Example 2:
```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

## Example 3:
```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Constraints:
- 0 <= s.length <= 5 * 10^4
- s consists of English letters, digits, symbols and spaces.

## Two Pointers Approach
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        
        // Set to track characters in the current window
        Set<Character> charSet = new HashSet<>();
        
        // Two pointers representing the sliding window
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            
            // If the character is already in the window, shrink the window from the left
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            
            // Add the current character to the set
            charSet.add(currentChar);
            
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Time Complexity
O(n) where n is the length of the string. Each character is processed at most twice - once added to the set and once removed.

## Space Complexity
O(min(m, n)) where n is the length of the string and m is the size of the character set. In the worst case, this could be O(n) if all characters are unique.