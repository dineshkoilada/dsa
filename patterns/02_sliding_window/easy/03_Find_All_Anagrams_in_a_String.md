# Find All Anagrams in a String

## Problem Statement

Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Examples

**Example 1:**
```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```

**Example 2:**
```
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

## Approach: Sliding Window with Character Frequency Counter

This problem can be efficiently solved using a sliding window approach with character frequency counters. We'll maintain a fixed-size window of length equal to string `p` and check if the window forms an anagram of `p`.

### Algorithm:

1. Create character frequency counters for both strings `p` and the current window in `s`
2. Initialize a window of size equal to the length of string `p`
3. Slide the window through string `s`:
   - Add the new character entering the window to the frequency counter
   - Remove the character leaving the window from the frequency counter
   - Check if the current window forms an anagram of `p`
4. If the frequencies match, add the starting index of the window to the result

## Code Solution

```java
import java.util.*;

public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }
        
        // Create frequency counters
        int[] pCount = new int[26]; // For pattern string
        int[] sCount = new int[26]; // For current window in source string
        
        // Fill the frequency counter for pattern string
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        // Process the first window
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }
        
        // Check if the first window is an anagram
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }
        
        // Slide the window and check for anagrams
        for (int i = p.length(); i < s.length(); i++) {
            // Add new character to the window
            sCount[s.charAt(i) - 'a']++;
            
            // Remove the character leaving the window
            sCount[s.charAt(i - p.length()) - 'a']--;
            
            // Check if current window forms an anagram
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
}
```

## Optimized Solution: Using a Single Counter

We can optimize the solution by using a single counter instead of comparing two arrays:

```java
import java.util.*;

public class FindAllAnagramsInStringOptimized {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        
        int[] count = new int[26];
        
        // Fill the counter for pattern string
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        
        int left = 0;
        int right = 0;
        int remaining = p.length(); // Number of characters to match
        
        while (right < s.length()) {
            // Current character is part of the anagram
            if (count[s.charAt(right) - 'a'] > 0) {
                remaining--;
            }
            
            // Decrease the counter for the current character
            count[s.charAt(right) - 'a']--;
            right++;
            
            // Found an anagram
            if (remaining == 0) {
                result.add(left);
            }
            
            // When the window size equals to pattern length, shrink from left
            if (right - left == p.length()) {
                // If the character was part of the anagram pattern, we need to look for it again
                if (count[s.charAt(left) - 'a'] >= 0) {
                    remaining++;
                }
                
                // Increase the counter as the character moves out of the window
                count[s.charAt(left) - 'a']++;
                left++;
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of string `s`. We process each character in `s` at most twice.
- **Space Complexity**: O(1) as we use fixed-size arrays of length 26 for the character counters.

## Key Insights

1. This problem demonstrates how the sliding window technique can be applied to find substrings with specific properties (in this case, anagrams).
2. The key insight is that anagrams have the same character frequency count, regardless of character order.
3. Using a fixed-size window equal to the length of pattern string `p` ensures we only consider substrings of the correct length.
4. The optimized solution uses a single counter and a "remaining" variable to avoid comparing entire arrays for each window position.
5. This problem can also be solved using a HashMap instead of an array for character counting, which would be more versatile if we're dealing with strings that don't just contain lowercase English letters.
