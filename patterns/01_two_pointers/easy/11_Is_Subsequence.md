# Is Subsequence (LeetCode #392)

## Problem Statement
Given two strings `s` and `t`, return `true` if `s` is a subsequence of `t`, or `false` otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

## Example 1:
```
Input: s = "abc", t = "ahbgdc"
Output: true
```

## Example 2:
```
Input: s = "axc", t = "ahbgdc"
Output: false
```

## Constraints:
- 0 <= s.length <= 100
- 0 <= t.length <= 10^4
- s and t consist only of lowercase English letters.

## Two Pointers Approach
```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        
        int sPointer = 0;
        int tPointer = 0;
        
        while (tPointer < t.length()) {
            if (t.charAt(tPointer) == s.charAt(sPointer)) {
                sPointer++;
                if (sPointer == s.length()) {
                    return true;
                }
            }
            tPointer++;
        }
        
        return false;
    }
}
```

## Time Complexity
O(n) where n is the length of string t.

## Space Complexity
O(1) as we only use two pointers.