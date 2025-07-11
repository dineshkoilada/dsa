# Maximum Number of Vowels in a Substring of Given Length

## Problem Statement

Given a string `s` and an integer `k`, return the maximum number of vowel letters in any substring of `s` with length `k`.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

## Examples

**Example 1:**
```
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
```

**Example 2:**
```
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
```

**Example 3:**
```
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "tco" contain 2 vowels.
```

## Approach: Fixed-Size Sliding Window

This is a perfect example of a fixed-size sliding window problem, where we need to find the maximum number of vowels in a substring of length `k`.

### Algorithm:

1. Define a function to check if a character is a vowel
2. Initialize a window of size `k` and count the vowels in it
3. Slide the window through the string:
   - Remove the character that's going out of the window
   - Add the character that's coming into the window
   - Update the maximum vowel count seen so far

## Code Solution

```java
public class MaximumVowelsInSubstring {
    public int maxVowels(String s, int k) {
        int maxVowels = 0;
        int currentVowels = 0;
        
        // Count vowels in the first window of size k
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }
        }
        
        maxVowels = currentVowels;
        
        // Slide the window
        for (int i = k; i < s.length(); i++) {
            // Remove the character going out of the window
            if (isVowel(s.charAt(i - k))) {
                currentVowels--;
            }
            
            // Add the character coming into the window
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }
            
            // Update max vowels
            maxVowels = Math.max(maxVowels, currentVowels);
        }
        
        return maxVowels;
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character exactly once.
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.

## Key Insights

1. This problem is a classic fixed-size sliding window problem where the window size is exactly `k`.
2. Instead of recalculating the vowel count for each possible substring (which would be inefficient), we maintain a running count as we slide the window.
3. The sliding window approach reduces the time complexity from O(n*k) to O(n) by avoiding unnecessary recalculations.
4. A simple helper function to check for vowels makes the code more readable and maintainable.
5. Edge cases are handled naturally by the algorithm - if the string length is less than `k`, the initial loop will only go up to the string length.
