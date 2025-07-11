# Maximum Average Subarray I

## Problem Statement

You are given an integer array `nums` consisting of `n` elements, and an integer `k`.

Find a contiguous subarray of length `k` that has the maximum average value and return this value. Any answer with a calculation error less than 10^-5 will be accepted.

## Examples

**Example 1:**
```
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
```

**Example 2:**
```
Input: nums = [5], k = 1
Output: 5.00000
```

## Approach: Fixed-Size Sliding Window

This is a classic fixed-size sliding window problem where we need to find the maximum average of a contiguous subarray of size `k`. The sliding window technique is perfect for this problem because we're examining every possible contiguous subarray of a fixed size.

### Algorithm:

1. Calculate the sum of the first `k` elements in the array
2. Compute the initial average and set it as the maximum average
3. Slide the window forward one element at a time:
   - Add the new element that enters the window
   - Remove the element that leaves the window
   - Recalculate the average and update the maximum if necessary
4. Return the maximum average found

## Code Solution

```java
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        // Calculate the sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        // Initialize maxSum with the sum of first window
        int maxSum = sum;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            // Add the current element and remove the first element of previous window
            sum = sum + nums[i] - nums[i - k];
            
            // Update maxSum if current window sum is greater
            maxSum = Math.max(maxSum, sum);
        }
        
        // Calculate and return the maximum average
        return (double) maxSum / k;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We process each element of the array at most twice (once when it enters the window and once when it leaves).
- **Space Complexity**: O(1) as we only use a fixed amount of extra space regardless of the input size.

## Key Insights

1. This problem demonstrates the classic fixed-size sliding window pattern where we maintain a window of size `k` and slide it through the array.
2. Instead of recalculating the sum for each possible subarray (which would be O(n*k) time), we efficiently update the sum by adding the new element and removing the element that's no longer in the window.
3. We only need to track the sum, not the average, during the sliding process. We can calculate the average at the end by dividing the maximum sum by `k`.
4. The solution handles the edge case where the array length is equal to `k` (in which case there's only one possible subarray).
5. Note that we're asked to return the maximum average, not the subarray itself that has the maximum average.
