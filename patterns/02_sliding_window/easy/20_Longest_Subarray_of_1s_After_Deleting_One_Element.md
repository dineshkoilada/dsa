# Longest Subarray of 1's After Deleting One Element

## Problem Description

Given a binary array `nums`, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

### Example 1:
```
Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number at index 2 (0), the array becomes [1,1,1]. The length of the longest subarray of consecutive 1's is 3.
```

### Example 2:
```
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number at index 0 (0), the array becomes [1,1,1,0,1,1,0,1]. The length of the longest subarray of consecutive 1's is 5.
```

### Example 3:
```
Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element, so the maximum possible length is 2.
```

### Constraints:
- 1 <= nums.length <= 10^5
- nums[i] is either 0 or 1.

## Approach: Sliding Window

This problem can be viewed as a variation of the "Maximum Consecutive Ones III" problem, where k=1. 
Instead of flipping at most k zeros, we are required to delete exactly one element.

1. Use a sliding window approach to maintain a window with at most one zero.
2. When we encounter a second zero, shrink the window from the left until we remove one zero.
3. Keep track of the maximum window size.
4. Since we must delete exactly one element (not necessarily a zero), if the maximum window has no zeros, we need to reduce the length by 1 (for deleting one mandatory element).

### Time Complexity: O(n)
- We iterate through the array once, where n is the length of the array.

### Space Complexity: O(1)
- We only use constant extra space.

## Solution

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Count zeros in the current window
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // If we have more than 1 zero, shrink the window from the left
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // Update the maximum length
            // Note: We don't add +1 because we need to delete exactly one element
            maxLength = Math.max(maxLength, right - left);
        }
        
        // If the entire array consists of 1s, we still need to delete one element
        return Math.min(maxLength, nums.length - 1);
    }
}
```

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        left = 0
        zero_count = 0
        max_length = 0
        
        for right in range(len(nums)):
            # Count zeros in the current window
            if nums[right] == 0:
                zero_count += 1
            
            # If we have more than 1 zero, shrink the window from the left
            while zero_count > 1:
                if nums[left] == 0:
                    zero_count -= 1
                left += 1
            
            # Update the maximum length
            # The window size is (right - left + 1), but we need to delete one element
            max_length = max(max_length, right - left)
        
        # If all elements are 1's, we still need to delete one element
        return min(max_length, len(nums) - 1)
```

## Cleaner Solution

Here's a cleaner implementation that handles the edge case differently:

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int result = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // right - left gives the length after deletion
            result = Math.max(result, right - left);
        }
        
        return result;
    }
}
```

## Key Insights

1. **Mandatory Deletion**: Unlike previous sliding window problems, here we must delete exactly one element, which is a twist on the standard approach.

2. **Window Size Calculation**: The window size (right - left + 1) represents the number of elements in the current window. Since we need to delete one element, the length after deletion would be (right - left).

3. **Edge Case - All Ones**: If the array consists of all 1's, the answer is (n-1) because we must delete one element.

4. **Optimization**: We don't need to calculate (right - left + 1) and then subtract 1. We can directly calculate (right - left) as our window size after deletion.

5. **Zero Count**: The key to the sliding window approach is maintaining at most one zero in the window, as this zero will be the element we delete.

6. **Single Pass Algorithm**: The algorithm makes a single pass through the array, making it efficient even for large inputs.
