# Longest Repeating Character Replacement

## Problem Statement

You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Examples

**Example 1:**
```
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
```

**Example 2:**
```
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
```

## Approach: Sliding Window with Character Frequency Tracking

This problem can be solved using a sliding window approach with character frequency tracking. We'll maintain a window where the number of characters that need to be replaced is at most `k`.

### Algorithm:

1. Maintain a frequency map for characters in the current window
2. Track the character with the maximum frequency in the window
3. The number of characters to replace = window size - max frequency
4. If the number of characters to replace > k, shrink the window from the left
5. Update the maximum window size as we go

## Code Solution

```java
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26]; // For uppercase English letters
        int maxLength = 0;
        int maxFrequency = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Increment count of current character
            charCount[s.charAt(right) - 'A']++;
            
            // Update maximum frequency if needed
            maxFrequency = Math.max(maxFrequency, charCount[s.charAt(right) - 'A']);
            
            // If the number of characters to replace exceeds k, shrink window
            if (right - left + 1 - maxFrequency > k) {
                // Decrease count of character going out of window
                charCount[s.charAt(left) - 'A']--;
                left++;
            }
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Optimization

An interesting optimization for this solution is that we don't always need to decrease `maxFrequency` when we shrink the window. This is because we're only interested in the longest valid substring, and decreasing `maxFrequency` could potentially lead to a shorter result.

```java
public class LongestRepeatingCharacterReplacementOptimized {
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26];
        int maxLength = 0;
        int maxFrequency = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            charCount[s.charAt(right) - 'A']++;
            maxFrequency = Math.max(maxFrequency, charCount[s.charAt(right) - 'A']);
            
            // If window is invalid, shrink it
            if (right - left + 1 - maxFrequency > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character once.
- **Space Complexity**: O(1) since we use a fixed-size array of 26 characters.

## Key Insights

1. This problem uses a sliding window with a constraint: the number of characters to replace must not exceed `k`.
2. The key insight is calculating how many characters need to be replaced: window size - max frequency.
3. We don't actually need to perform the replacements; we just need to know if they're possible within the limit `k`.
4. The optimization where we don't decrease `maxFrequency` works because if a new maximum frequency is found, it can only lead to a longer valid substring.
5. This pattern is useful for problems involving substring manipulations with constraints on the number of operations allowed.
