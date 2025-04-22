# Find All Anagrams in a String (LeetCode #438)

## Problem Statement
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Example 1:
```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```

## Example 2:
```
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

## Constraints:
- 1 <= s.length, p.length <= 3 * 10^4
- s and p consist of lowercase English letters.

## Two Pointers Approach
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()) {
            return result;
        }
        
        // Create character frequency counters
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        // Initialize the p counter
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        // Use sliding window with two pointers
        for (int right = 0; right < s.length(); right++) {
            // Add current character to window
            sCount[s.charAt(right) - 'a']++;
            
            // Remove character outside the window (sliding the window)
            if (right >= p.length()) {
                sCount[s.charAt(right - p.length()) - 'a']--;
            }
            
            // Check if current window is an anagram
            if (right >= p.length() - 1 && arraysEqual(pCount, sCount)) {
                result.add(right - (p.length() - 1));
            }
        }
        
        return result;
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
O(n) where n is the length of string s.

## Space Complexity
O(1) because we use two fixed-size arrays of size 26, regardless of input size.