# Max Consecutive Ones III

## Problem Description

Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` 0's to 1's.

### Example 1:
```
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
We flipped 2 zeros (bolded) to get 6 consecutive 1's.
```

### Example 2:
```
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: We flip the 0's at positions 0, 1, and 9 to get the maximum consecutive 1's.
```

### Constraints:
- 1 <= nums.length <= 10^5
- nums[i] is either 0 or 1
- 0 <= k <= nums.length

## Approach: Sliding Window

This problem is a perfect candidate for the sliding window approach:

1. Maintain a window containing at most `k` zeros.
2. Expand the window to the right as long as the constraint is maintained.
3. When the constraint is violated (more than `k` zeros), shrink the window from the left.
4. The length of the window at any point represents the number of consecutive 1's we can achieve by flipping at most `k` zeros.

### Time Complexity: O(n)
- We iterate through the array once, where n is the length of the array.

### Space Complexity: O(1)
- We only use a constant amount of extra space for our pointers and counters.

## Solution

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxConsecutive = 0;
        int zeroCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // If we encounter a zero, increment zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // If we have more than k zeros, we need to shrink the window
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // Update the maximum length
            maxConsecutive = Math.max(maxConsecutive, right - left + 1);
        }
        
        return maxConsecutive;
    }
}
```

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        left = 0
        max_consecutive = 0
        zero_count = 0
        
        for right in range(len(nums)):
            # If we encounter a zero, increment zero_count
            if nums[right] == 0:
                zero_count += 1
            
            # If we have more than k zeros, we need to shrink the window
            while zero_count > k:
                if nums[left] == 0:
                    zero_count -= 1
                left += 1
            
            # Update the maximum length
            max_consecutive = max(max_consecutive, right - left + 1)
        
        return max_consecutive
```

## Optimized Solution

We can optimize the solution by realizing that we don't need to explicitly track the maximum length since the window size will monotonically increase. The final window size will be the maximum.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // If we encounter a zero, increment zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // If we have more than k zeros, we need to shrink the window
            if (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // No need to update max length explicitly, the window size will be the max at the end
        }
        
        return nums.length - left;
    }
}
```

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        left = 0
        zero_count = 0
        
        for right in range(len(nums)):
            # If we encounter a zero, increment zero_count
            if nums[right] == 0:
                zero_count += 1
            
            # If we have more than k zeros, we need to shrink the window
            if zero_count > k:
                if nums[left] == 0:
                    zero_count -= 1
                left += 1
            
            # No need to update max length explicitly
        
        # The size of the final window is the answer
        return len(nums) - left
```

## Key Insights

1. **Flipping vs. Counting**: Instead of actually flipping 0's to 1's, we count the number of 0's in our current window and ensure it doesn't exceed `k`.

2. **Sliding Window Application**: This problem demonstrates a classic use case of the sliding window pattern where we expand to the right and contract from the left as needed.

3. **Optimization Insight**: We don't need to explicitly track the maximum window size since the window only shrinks when necessary to maintain the constraint, ensuring we always have the optimal window size.

4. **Constraint Handling**: The key to the sliding window approach is maintaining the constraint that there are at most `k` zeros in the window.

5. **Window Length Calculation**: The length of the window at any point (right - left + 1) represents the number of consecutive 1's we can achieve with at most `k` flips.

6. **Special Cases**: The algorithm correctly handles edge cases:
   - If k = 0, we're finding the longest sequence of consecutive 1's.
   - If all elements are 1's, we return the length of the array.
   - If all elements are 0's and k >= array length, we return the array length.
