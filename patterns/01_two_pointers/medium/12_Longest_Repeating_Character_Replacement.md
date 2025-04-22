# Longest Repeating Character Replacement (LeetCode #424)

## Problem Statement
You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Example 1:
```
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
```

## Example 2:
```
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
```

## Constraints:
- 1 <= s.length <= 10^5
- s consists of only uppercase English letters.
- 0 <= k <= s.length

## Two Pointers Approach
```java
class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int[] charCount = new int[26]; // For counting uppercase English letters
        int maxCount = 0; // Track the most frequent character in the current window
        int maxLength = 0;
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            // Increase the count of the current character
            charCount[s.charAt(right) - 'A']++;
            
            // Update the count of the most frequent character
            maxCount = Math.max(maxCount, charCount[s.charAt(right) - 'A']);
            
            // If the number of characters to replace exceeds k, shrink the window
            // (window length - count of most frequent character) = characters to replace
            if (right - left + 1 - maxCount > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
            }
            
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Time Complexity
O(n) where n is the length of the string. We process each character once.

## Space Complexity
O(1) because we use a fixed-size array of 26 characters regardless of input size.