# Longest Continuous Increasing Subsequence

## Problem Description

Given an unsorted array of integers `nums`, return the length of the longest continuous increasing subsequence (i.e., subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by a subarray where each element is strictly greater than the previous element.

### Example 1:
```
Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element 4.
```

### Example 2:
```
Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], with length 1. Note that it must be strictly increasing.
```

### Constraints:
- 1 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9

## Approach: Sliding Window

This problem can be approached using a sliding window technique, where we track the length of the current increasing subsequence as we iterate through the array.

1. Iterate through the array, comparing adjacent elements.
2. If the current element is greater than the previous one, extend the current subsequence.
3. Otherwise, reset the length of the current subsequence to 1.
4. Track the maximum subsequence length encountered.

### Time Complexity: O(n)
- We iterate through the array once, where n is the length of the array.

### Space Complexity: O(1)
- We only use a constant amount of extra space regardless of input size.

## Solution

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxLength = 1;  // At least one element
        int currentLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                // Current element is greater than previous, extend subsequence
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // Current element is not greater, reset subsequence
                currentLength = 1;
            }
        }
        
        return maxLength;
    }
}
```

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        if not nums:
            return 0
        
        max_length = 1  # At least one element
        current_length = 1
        
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                # Current element is greater than previous, extend subsequence
                current_length += 1
                max_length = max(max_length, current_length)
            else:
                # Current element is not greater, reset subsequence
                current_length = 1
        
        return max_length
```

## Alternative Implementation

Here's another way to implement the solution using a more traditional sliding window approach with two pointers:

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxLength = 1;
        int start = 0;
        
        for (int end = 1; end < nums.length; end++) {
            if (nums[end] <= nums[end - 1]) {
                // Reset the start pointer when the sequence breaks
                start = end;
            }
            
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }
}
```

## Key Insights

1. **Strictly Increasing**: The problem requires the subsequence to be strictly increasing, meaning each element must be strictly greater than the previous one. Equal elements break the sequence.

2. **Continuous Subsequence**: Unlike some other subsequence problems, this one requires the elements to be continuous (a subarray), which simplifies our approach.

3. **Reset on Break**: When the increasing sequence breaks, we need to reset our counter (or start pointer) to begin a new subsequence.

4. **Single Pass**: The algorithm makes a single pass through the array, making it very efficient.

5. **Edge Cases**: We need to handle empty arrays or arrays with a single element, which we do by initializing lengths to 1 and performing a check at the beginning.

6. **Two Pointers vs. Counter**: We can solve this either by using a counter to track the current length or using two pointers to track the start and end of the current subsequence.

7. **Maximum Tracking**: Throughout the iteration, we continuously update our maximum length to ensure we capture the longest subsequence even if it appears earlier in the array.
