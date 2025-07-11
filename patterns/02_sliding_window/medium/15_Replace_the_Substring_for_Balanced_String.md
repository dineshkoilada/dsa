# Replace the Substring for Balanced String

## Problem Statement

You are given a string `s` containing only the characters 'Q', 'W', 'E', and 'R'. A string is said to be **balanced** if each of its characters appears exactly `n/4` times where `n` is the length of the string.

Return the minimum length of a substring that can be replaced with any other characters to make the string balanced. If the string is already balanced, return 0.

## Examples

**Example 1:**
```
Input: s = "QWER"
Output: 0
Explanation: s is already balanced.
```

**Example 2:**
```
Input: s = "QQWE"
Output: 1
Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
```

**Example 3:**
```
Input: s = "QQQW"
Output: 2
Explanation: We can replace the first "QQ" to "ER". 
```

**Example 4:**
```
Input: s = "QQQQ"
Output: 3
Explanation: We can replace the last 3 'Q' to make s = "QWER".
```

## Approach: Sliding Window with Character Frequency

This problem can be solved using a sliding window approach. The key insight is that we need to find the shortest substring that, when replaced, leaves exactly `n/4` occurrences of each character in the string.

### Algorithm:

1. Calculate the target count for each character (n/4)
2. Count the occurrences of each character in the original string
3. Define excess characters as those appearing more than n/4 times
4. Use sliding window to find the minimum substring that, when replaced, reduces excess characters to at most the target count
5. Return the minimum window length

## Code Solution

```java
public class ReplaceSubstringForBalancedString {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;  // Each character should appear exactly n/4 times
        
        // Count occurrences of each character
        int[] count = new int[128];  // ASCII table size
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        
        // Check if string is already balanced
        if (count['Q'] == target && count['W'] == target && 
            count['E'] == target && count['R'] == target) {
            return 0;
        }
        
        int left = 0;
        int minLen = n;  // Initialize with maximum possible length
        
        for (int right = 0; right < n; right++) {
            // Decrement count for the character entering the window
            // (This simulates removing the character from consideration)
            count[s.charAt(right)]--;
            
            // Shrink window from left while all excess characters are covered
            while (left <= right && 
                   count['Q'] <= target && count['W'] <= target && 
                   count['E'] <= target && count['R'] <= target) {
                
                // Update minimum length found so far
                minLen = Math.min(minLen, right - left + 1);
                
                // Increment count for the character leaving the window
                // (This simulates adding the character back to consideration)
                count[s.charAt(left)]++;
                left++;
            }
        }
        
        return minLen;
    }
}
```

## Optimization

We can optimize the solution by using an array to track character counts more efficiently:

```java
public class ReplaceSubstringForBalancedStringOptimized {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;
        
        // Create a map for character counts (using array for efficiency)
        int[] count = new int[4];
        
        // Map characters to indices: Q=0, W=1, E=2, R=3
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'Q': count[0]++; break;
                case 'W': count[1]++; break;
                case 'E': count[2]++; break;
                case 'R': count[3]++; break;
            }
        }
        
        // Check if already balanced
        if (count[0] == target && count[1] == target && 
            count[2] == target && count[3] == target) {
            return 0;
        }
        
        int left = 0;
        int minLen = n;
        
        for (int right = 0; right < n; right++) {
            // Decrement count for character entering window
            char c = s.charAt(right);
            switch (c) {
                case 'Q': count[0]--; break;
                case 'W': count[1]--; break;
                case 'E': count[2]--; break;
                case 'R': count[3]--; break;
            }
            
            // Shrink window while valid
            while (left <= right && 
                   count[0] <= target && count[1] <= target && 
                   count[2] <= target && count[3] <= target) {
                
                minLen = Math.min(minLen, right - left + 1);
                
                // Increment count for character leaving window
                c = s.charAt(left);
                switch (c) {
                    case 'Q': count[0]++; break;
                    case 'W': count[1]++; break;
                    case 'E': count[2]++; break;
                    case 'R': count[3]++; break;
                }
                
                left++;
            }
        }
        
        return minLen;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character at most twice.
- **Space Complexity**: O(1) since we use fixed-size arrays regardless of input size (either ASCII table size or just 4 elements).

## Key Insights

1. This problem requires a sliding window approach where the window represents the substring to be replaced.
2. The key insight is to focus on what remains outside the window - it must have at most n/4 of each character.
3. The window is valid when replacing it would make the remaining string have at most n/4 of each character.
4. We continuously shrink the window from the left to find the minimum size that maintains validity.
5. The sliding window pattern is particularly useful here because we're looking for the minimum substring with a specific property.
