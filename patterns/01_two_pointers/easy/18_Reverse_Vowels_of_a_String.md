# Reverse Vowels of a String (LeetCode #345)

## Problem Statement
Given a string `s`, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

## Example 1:
```
Input: s = "hello"
Output: "holle"
```

## Example 2:
```
Input: s = "leetcode"
Output: "leotcede"
```

## Constraints:
- 1 <= s.length <= 3 * 10^5
- s consists of printable ASCII characters.

## Two Pointers Approach
```java
class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        
        // Set of vowels for quick lookup
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        
        while (left < right) {
            // Find the leftmost vowel
            while (left < right && !vowels.contains(chars[left])) {
                left++;
            }
            
            // Find the rightmost vowel
            while (left < right && !vowels.contains(chars[right])) {
                right--;
            }
            
            // Swap the vowels
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                
                left++;
                right--;
            }
        }
        
        return new String(chars);
    }
}
```

## Time Complexity
O(n) where n is the length of the string.

## Space Complexity
O(n) for the character array and O(1) for the vowels set (as it's a fixed size).