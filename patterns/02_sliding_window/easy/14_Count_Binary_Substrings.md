# Count Binary Substrings

## Problem Description

Give a binary string `s`, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

In other words, count the number of substrings that contain an equal number of consecutive 0's followed by consecutive 1's, or consecutive 1's followed by consecutive 0's.

### Example 1:
```
Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat, and we count each occurrence separately.
```

### Example 2:
```
Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
```

### Constraints:
- 1 <= s.length <= 10^5
- s[i] is either '0' or '1'.

## Approach: Groups of Consecutive Characters

This problem uses a variation of the sliding window approach:

1. We count the consecutive runs of 0's and 1's.
2. For any two adjacent groups of 0's and 1's, the number of valid substrings is the minimum of the two counts.

### Time Complexity: O(n)
- We iterate through the string once, where n is the length of the string.

### Space Complexity: O(1)
- We only use a constant amount of extra space for our pointers and counters.

## Solution

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int prevRunLength = 0;
        int currRunLength = 1;
        int result = 0;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currRunLength++;
            } else {
                // Found a transition from 0 to 1 or 1 to 0
                result += Math.min(prevRunLength, currRunLength);
                prevRunLength = currRunLength;
                currRunLength = 1;
            }
        }
        
        // Don't forget to include the last group
        result += Math.min(prevRunLength, currRunLength);
        
        return result;
    }
}
```

```python
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        prev_run_length = 0
        curr_run_length = 1
        result = 0
        
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                curr_run_length += 1
            else:
                # Found a transition from 0 to 1 or 1 to 0
                result += min(prev_run_length, curr_run_length)
                prev_run_length = curr_run_length
                curr_run_length = 1
        
        # Don't forget to include the last group
        result += min(prev_run_length, curr_run_length)
        
        return result
```

## Alternative Solution (Groups Array)

We can also solve this by creating an array of consecutive character counts:

```java
class Solution {
    public int countBinarySubstrings(String s) {
        List<Integer> groups = new ArrayList<>();
        int count = 1;
        
        // Group consecutive characters
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count); // Add the last group
        
        int result = 0;
        // Count valid substrings between adjacent groups
        for (int i = 1; i < groups.size(); i++) {
            result += Math.min(groups.get(i - 1), groups.get(i));
        }
        
        return result;
    }
}
```

## Key Insights

1. **Group Counting**: The key insight is to count consecutive groups of the same character and then find the minimum of adjacent groups to determine valid substring count.

2. **Transition Points**: Each transition point from 0 to 1 or 1 to 0 in the string creates potential valid substrings.

3. **Minimum Length**: The number of valid substrings that can be formed at a transition point is limited by the shorter of the two adjacent runs.

4. **Direct Counting**: Instead of explicitly checking each substring, we can directly count valid substrings based on the consecutive character counts.

5. **Edge Case Handling**: Make sure to include the last group comparison in your calculation.

6. **Space Optimization**: The first solution uses constant space by only keeping track of the previous and current run lengths, instead of storing all groups.

7. **Intuition**: The problem can be visualized as finding pairs of "balanced" character runs, where each valid substring must have equal numbers of consecutive 0's and 1's.
