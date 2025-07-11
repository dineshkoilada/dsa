# Consecutive Characters

## Problem Description

The power of the string is the maximum length of a non-empty substring that contains only one unique character.

Given a string `s`, return the power of `s`.

### Example 1:
```
Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.
```

### Example 2:
```
Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
```

### Example 3:
```
Input: s = "triplepillooooow"
Output: 5
Explanation: The substring "ooooo" is of length 5 with the character 'o' only.
```

### Example 4:
```
Input: s = "hooraaaaaaaaaaay"
Output: 11
Explanation: The substring "aaaaaaaaaaaa" is of length 11 with the character 'a' only.
```

### Example 5:
```
Input: s = "tourist"
Output: 1
Explanation: Each character in the string occurs only once, so the power is 1.
```

### Constraints:
- 1 <= s.length <= 500
- s consists of only lowercase English letters.

## Approach: Sliding Window / Running Counter

This problem is a straightforward application of the sliding window pattern where we track consecutive occurrences of the same character.

1. Iterate through the string character by character.
2. Keep a counter for the current consecutive character count.
3. When the current character differs from the previous one, reset the counter.
4. Keep track of the maximum counter value seen so far.

### Time Complexity: O(n)
- We iterate through the string once, where n is the length of the string.

### Space Complexity: O(1)
- We only use constant extra space regardless of input size.

## Solution

```java
class Solution {
    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int maxCount = 1;  // Power of at least 1
        int currentCount = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // Current character is the same as previous one
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                // Current character is different, reset the counter
                currentCount = 1;
            }
        }
        
        return maxCount;
    }
}
```

```python
class Solution:
    def maxPower(self, s: str) -> int:
        if not s:
            return 0
        
        max_count = 1  # Power of at least 1
        current_count = 1
        
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                # Current character is the same as previous one
                current_count += 1
                max_count = max(max_count, current_count)
            else:
                # Current character is different, reset the counter
                current_count = 1
        
        return max_count
```

## Key Insights

1. **Simple Counter**: This problem is essentially asking us to find the longest run of the same character, which can be done with a simple counter.

2. **Reset Strategy**: The key operation is to reset the counter whenever the current character differs from the previous one.

3. **Sliding Window Pattern**: While not a traditional sliding window problem with two pointers, this problem uses the same principle of maintaining a "window" of consecutive same characters.

4. **Initial Values**: Be careful with the initial values for counter variables. Since each character has a power of at least 1, we start with 1 instead of 0.

5. **Edge Cases**: The solution handles empty strings and strings of length 1 correctly by initializing the max count to 1 and checking for null/empty input.

6. **Optimization**: We don't need to store the character, just compare the current one with the previous one, which simplifies the code.

7. **Single Pass**: The algorithm completes in a single pass through the string, making it very efficient.
