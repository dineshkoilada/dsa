# Reverse String (LeetCode #344)

## Problem Statement
Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array in-place with O(1) extra memory.

## Example 1:
```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

## Example 2:
```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## Constraints:
- 1 <= s.length <= 10^5
- s[i] is a printable ascii character.

## Two Pointers Approach
```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            // Move pointers inward
            left++;
            right--;
        }
    }
}
```

## Time Complexity
O(n) where n is the length of the string.

## Space Complexity
O(1) as we only use two pointers and modify the array in-place.