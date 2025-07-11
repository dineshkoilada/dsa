# Permutation in String

## Problem Statement

Given two strings `s1` and `s2`, return true if `s2` contains a permutation of `s1`, or false otherwise.

In other words, return true if one of `s1`'s permutations is the substring of `s2`.

## Examples

**Example 1:**
```
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
```

**Example 2:**
```
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
```

## Approach: Sliding Window with Character Frequency Matching

This problem can be efficiently solved using a sliding window approach with character frequency matching. Since we're looking for any permutation of `s1` in `s2`, the order of characters doesn't matter, only their frequencies.

### Algorithm:

1. Create frequency maps for characters in `s1`
2. Use a sliding window of size equal to the length of `s1` in `s2`
3. For each window, check if the character frequencies match those in `s1`
4. If there's a match, return true; otherwise, check the next window
5. Return false if no matches are found

## Code Solution

```java
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        // Create frequency arrays for s1 and current window in s2
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];
        
        // Initialize frequency array for s1
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }
        
        // Initialize first window
        for (int i = 0; i < s1.length(); i++) {
            windowCount[s2.charAt(i) - 'a']++;
        }
        
        // Check if first window is a permutation
        if (matches(s1Count, windowCount)) {
            return true;
        }
        
        // Slide window and check remaining windows
        for (int i = s1.length(); i < s2.length(); i++) {
            // Add new character to window
            windowCount[s2.charAt(i) - 'a']++;
            
            // Remove character outside the window
            windowCount[s2.charAt(i - s1.length()) - 'a']--;
            
            // Check if current window is a permutation
            if (matches(s1Count, windowCount)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Helper function to check if frequency arrays match
    private boolean matches(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
```

## Optimized Solution

We can optimize the solution by using a single variable to track matches rather than comparing full arrays each time:

```java
public class PermutationInStringOptimized {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        int[] count = new int[26];
        
        // Count characters in s1
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        
        int matches = 0;
        int distinct = 0;
        
        // Count distinct characters in s1
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                distinct++;
            }
        }
        
        int left = 0, right = 0;
        
        // Slide window through s2
        while (right < s2.length()) {
            // Expand window
            int rightChar = s2.charAt(right) - 'a';
            count[rightChar]--;
            
            if (count[rightChar] == 0) {
                matches++;
            } else if (count[rightChar] == -1) {
                matches--;
            }
            
            right++;
            
            // Check if window size equals s1 length
            if (right - left == s1.length()) {
                if (matches == distinct) {
                    return true;
                }
                
                // Shrink window
                int leftChar = s2.charAt(left) - 'a';
                count[leftChar]++;
                
                if (count[leftChar] == 0) {
                    matches++;
                } else if (count[leftChar] == 1) {
                    matches--;
                }
                
                left++;
            }
        }
        
        return false;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of string `s2`. We slide the window through the string once.
- **Space Complexity**: O(1) since we use fixed-size arrays of length 26 for character frequencies.

## Key Insights

1. This problem is a perfect example of the fixed-size sliding window pattern, where the window size is determined by the length of string `s1`.
2. The key insight is that permutations have the same character frequencies regardless of character order.
3. By using an array to track character frequencies, we can efficiently check if the current window has the same character frequencies as `s1`.
4. The optimized solution avoids redundant comparisons by incrementally tracking matches and mismatches as the window slides.
5. This problem demonstrates how sliding windows can be used to find pattern matches efficiently.
