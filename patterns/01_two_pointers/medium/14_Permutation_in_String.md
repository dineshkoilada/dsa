# Permutation in String (LeetCode #567)

## Problem Statement
Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.

In other words, return `true` if one of `s1`'s permutations is the substring of `s2`.

## Example 1:
```
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
```

## Example 2:
```
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
```

## Constraints:
- 1 <= s1.length, s2.length <= 10^4
- s1 and s2 consist of lowercase English letters.

## Two Pointers Approach
```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        // Create frequency counters
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        
        // Initialize s1 counter
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }
        
        // Sliding window approach
        for (int i = 0; i < s2.length(); i++) {
            // Add current character to window
            s2Count[s2.charAt(i) - 'a']++;
            
            // Remove character outside the window
            if (i >= s1.length()) {
                s2Count[s2.charAt(i - s1.length()) - 'a']--;
            }
            
            // Check if the current window contains a permutation of s1
            if (i >= s1.length() - 1 && arraysEqual(s1Count, s2Count)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean arraysEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

## Time Complexity
O(n) where n is the length of s2.

## Space Complexity
O(1) because we use two fixed-size arrays of size 26, regardless of input size.