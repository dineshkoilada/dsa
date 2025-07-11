# Count Unique Characters of All Substrings of a Given String

## Problem Statement

Let's define a function `countUniqueChars(s)` that returns the number of unique characters in string `s`.

For example, calling `countUniqueChars("LEETCODE")` should return 7.

Now, given a string `s`, return the sum of `countUniqueChars(t)` where `t` is a substring of `s`. The test cases are generated such that the answer fits in a 32-bit integer.

A substring is a contiguous sequence of characters within a string.

## Examples

**Example 1:**
```
Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A", "B", "C", "AB", "BC", and "ABC".
- countUniqueChars("A") = 1
- countUniqueChars("B") = 1
- countUniqueChars("C") = 1
- countUniqueChars("AB") = 2
- countUniqueChars("BC") = 2
- countUniqueChars("ABC") = 3
So the sum is 10.
```

**Example 2:**
```
Input: s = "ABA"
Output: 8
Explanation: All possible substrings are: "A", "B", "A", "AB", "BA", and "ABA".
- countUniqueChars("A") = 1
- countUniqueChars("B") = 1
- countUniqueChars("A") = 1
- countUniqueChars("AB") = 2
- countUniqueChars("BA") = 2
- countUniqueChars("ABA") = 2
So the sum is 8.
```

**Example 3:**
```
Input: s = "LEETCODE"
Output: 92
```

## Approach: Mathematical Calculation

A brute force approach would be to generate all substrings and count unique characters for each one. However, this would be O(n³) and is inefficient.

Instead, we can use a mathematical approach to calculate the contribution of each character to the final result:

### Algorithm:

1. For each character in the string, calculate how many substrings contain this character exactly once
2. Sum up these contributions for the final result

### Key Insight:
- For each index i, we calculate:
  - The number of substrings starting before or at i and ending at i (left)
  - The number of substrings starting at i and ending after i (right)
  - The total number of substrings containing i exactly once is left * right

## Code Solution

```java
import java.util.*;

public class CountUniqueCharactersOfAllSubstrings {
    public int uniqueLetterString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, List<Integer>> lastPositions = new HashMap<>();
        
        // Initialize positions for each character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!lastPositions.containsKey(c)) {
                lastPositions.put(c, new ArrayList<>());
                lastPositions.get(c).add(-1); // Initialize with -1 for easier calculation
            }
            lastPositions.get(c).add(i);
        }
        
        // Add a sentinel value for easier calculation
        for (char c : lastPositions.keySet()) {
            lastPositions.get(c).add(s.length());
        }
        
        int result = 0;
        
        // Calculate contribution of each character
        for (char c : lastPositions.keySet()) {
            List<Integer> positions = lastPositions.get(c);
            for (int i = 1; i < positions.size() - 1; i++) {
                // Current position of the character
                int current = positions.get(i);
                
                // Previous and next positions of the same character
                int prev = positions.get(i - 1);
                int next = positions.get(i + 1);
                
                // Contribution to the result: (current - prev) * (next - current)
                result += (current - prev) * (next - current);
            }
        }
        
        return result;
    }
}
```

## Alternate Approach: One-Pass Solution

We can also solve this with a simpler one-pass approach:

```java
public class CountUniqueCharactersOfAllSubstringsSimplified {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[] lastPosition = new int[26];
        int[] secondLastPosition = new int[26];
        Arrays.fill(lastPosition, -1);
        Arrays.fill(secondLastPosition, -1);
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            int charIndex = s.charAt(i) - 'A';
            
            // Current contribution: current position - last position of this char
            result += (i - lastPosition[charIndex]) * (lastPosition[charIndex] - secondLastPosition[charIndex]);
            
            // Update positions
            secondLastPosition[charIndex] = lastPosition[charIndex];
            lastPosition[charIndex] = i;
        }
        
        // Handle the last occurrence of each character
        for (int i = 0; i < 26; i++) {
            if (lastPosition[i] != -1) {
                result += (n - lastPosition[i]) * (lastPosition[i] - secondLastPosition[i]);
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character once.
- **Space Complexity**: O(k) where k is the number of unique characters in the string, which is at most 26 for uppercase English letters.

## Key Insights

1. Instead of generating all substrings, we calculate the contribution of each character position.
2. A character at position i contributes to all substrings that include it as a unique character.
3. The number of such substrings depends on the distance to the previous and next occurrences of the same character.
4. This mathematical approach transforms an O(n³) problem into an O(n) solution.
5. This problem demonstrates that sliding window problems sometimes require mathematical insights beyond the basic pattern implementation.
