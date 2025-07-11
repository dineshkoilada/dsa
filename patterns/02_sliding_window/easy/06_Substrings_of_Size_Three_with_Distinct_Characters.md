# Substrings of Size Three with Distinct Characters

## Problem Statement

A string is called **good** if there are no repeated characters.

Given a string `s`, return the number of **good substrings** of length three in `s`.

A **substring** is a contiguous sequence of characters within a string.

## Examples

**Example 1:**
```
Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
Only "xyz" is good because it has no repeated characters.
```

**Example 2:**
```
Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".
```

## Approach: Fixed-Size Sliding Window

This problem asks us to count substrings of length 3 that have all distinct characters, making it perfect for a fixed-size sliding window approach.

### Algorithm:

1. Initialize a counter for good substrings
2. Iterate through the string, considering each possible substring of length 3
3. For each substring, check if all three characters are distinct
4. Increment the counter when a good substring is found

## Code Solution

```java
public class SubstringsOfSizeThreeWithDistinctCharacters {
    public int countGoodSubstrings(String s) {
        // If the string length is less than 3, no good substrings are possible
        if (s.length() < 3) {
            return 0;
        }
        
        int count = 0;
        
        // Iterate through all possible substrings of length 3
        for (int i = 0; i <= s.length() - 3; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            char c = s.charAt(i + 2);
            
            // Check if all three characters are distinct
            if (a != b && b != c && a != c) {
                count++;
            }
        }
        
        return count;
    }
}
```

## Alternative Implementation with HashSet

```java
import java.util.HashSet;
import java.util.Set;

public class SubstringsOfSizeThreeWithDistinctCharactersSet {
    public int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }
        
        int count = 0;
        
        for (int i = 0; i <= s.length() - 3; i++) {
            Set<Character> charSet = new HashSet<>();
            charSet.add(s.charAt(i));
            charSet.add(s.charAt(i + 1));
            charSet.add(s.charAt(i + 2));
            
            // If the set has 3 elements, all characters are distinct
            if (charSet.size() == 3) {
                count++;
            }
        }
        
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character at most a constant number of times.
- **Space Complexity**: O(1) for the first solution as we only use a constant amount of extra space. For the HashSet solution, it's still O(1) since the set never contains more than 3 elements.

## Key Insights

1. This problem demonstrates the fixed-size sliding window pattern with a very specific window size (3).
2. The straightforward approach of directly checking for distinct characters is efficient when the window size is small and fixed.
3. For larger window sizes, using a HashSet to track unique characters might be more scalable.
4. The problem is simplified because we only need to check if all characters in the window are distinct, not track which ones they are.
5. This is a good introductory problem to understand the sliding window pattern without complex data structures.
