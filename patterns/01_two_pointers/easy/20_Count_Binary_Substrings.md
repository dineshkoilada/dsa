# Count Binary Substrings (LeetCode #696)

## Problem Statement
Give a binary string `s`, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

## Example 1:
```
Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
```

## Example 2:
```
Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
```

## Constraints:
- 1 <= s.length <= 10^5
- s[i] is either '0' or '1'.

## Two Pointers Approach
```java
class Solution {
    public int countBinarySubstrings(String s) {
        int prev = 0; // Count of the previous group
        int curr = 1; // Count of the current group
        int count = 0; // Result
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // Extend the current group
                curr++;
            } else {
                // Start a new group
                count += Math.min(prev, curr); // The number of valid substrings we can form
                prev = curr;
                curr = 1;
            }
        }
        
        // Count substrings from the last two groups
        count += Math.min(prev, curr);
        
        return count;
    }
}
```

## Time Complexity
O(n) where n is the length of the string.

## Space Complexity
O(1) as we only use a few variables.