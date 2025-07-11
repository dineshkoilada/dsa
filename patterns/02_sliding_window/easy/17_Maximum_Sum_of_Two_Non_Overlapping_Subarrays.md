# Maximum Sum of Two Non-Overlapping Subarrays

## Problem Description

Given an integer array `nums` and two integers `firstLen` and `secondLen`, return the maximum sum of elements in two non-overlapping subarrays with lengths `firstLen` and `secondLen`.

The array with length `firstLen` could occur before or after the array with length `secondLen`, but they have to be non-overlapping.

A subarray is a contiguous part of an array.

### Example 1:
```
Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
```

### Example 2:
```
Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
```

### Example 3:
```
Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
```

### Constraints:
- 1 <= firstLen, secondLen <= 1000
- 2 <= firstLen + secondLen <= 1000
- firstLen + secondLen <= nums.length <= 1000
- 0 <= nums[i] <= 1000

## Approach: Prefix Sums and Sliding Window

This problem combines prefix sums and sliding window concepts:

1. Calculate prefix sums to efficiently compute subarray sums.
2. For each possible position of the second subarray, find the maximum sum of the first subarray that doesn't overlap with it.
3. Consider both cases: first subarray before second, and second subarray before first.
4. Return the maximum combined sum.

### Time Complexity: O(n)
- We iterate through the array a constant number of times, where n is the length of the array.

### Space Complexity: O(n)
- We use extra space for the prefix sum array.

## Solution

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // Calculate prefix sum for efficient subarray sum calculations
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        // Try both cases: first subarray before second, and second before first
        return Math.max(
            maxSum(prefixSum, firstLen, secondLen),
            maxSum(prefixSum, secondLen, firstLen)
        );
    }
    
    // Helper method to find max sum with L-length subarray followed by M-length subarray
    private int maxSum(int[] prefixSum, int L, int M) {
        int maxResult = 0;
        int maxL = 0;
        
        // Start with the first possible position for M-length subarray
        // which is after an L-length subarray
        for (int i = L + M; i < prefixSum.length; i++) {
            // Update max sum for L-length subarray ending at i - M
            maxL = Math.max(maxL, prefixSum[i - M] - prefixSum[i - M - L]);
            
            // Compute current M-length subarray sum
            int currM = prefixSum[i] - prefixSum[i - M];
            
            // Update the overall maximum
            maxResult = Math.max(maxResult, maxL + currM);
        }
        
        return maxResult;
    }
}
```

```python
class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        # Calculate prefix sum for efficient subarray sum calculations
        prefix_sum = [0] * (len(nums) + 1)
        for i in range(len(nums)):
            prefix_sum[i + 1] = prefix_sum[i] + nums[i]
        
        # Try both cases: first subarray before second, and second before first
        return max(
            self.max_sum(prefix_sum, firstLen, secondLen),
            self.max_sum(prefix_sum, secondLen, firstLen)
        )
    
    # Helper method to find max sum with L-length subarray followed by M-length subarray
    def max_sum(self, prefix_sum, L, M):
        max_result = 0
        max_L = 0
        
        # Start with the first possible position for M-length subarray
        # which is after an L-length subarray
        for i in range(L + M, len(prefix_sum)):
            # Update max sum for L-length subarray ending at i - M
            max_L = max(max_L, prefix_sum[i - M] - prefix_sum[i - M - L])
            
            # Compute current M-length subarray sum
            curr_M = prefix_sum[i] - prefix_sum[i - M]
            
            # Update the overall maximum
            max_result = max(max_result, max_L + curr_M)
        
        return max_result
```

## Alternative One-Pass Solution

We can also solve this problem in a single pass by maintaining running maximums for both cases:

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // Compute prefix sums
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        int maxSum = 0;
        int maxFirstSum = 0;
        int maxSecondSum = 0;
        
        // Case 1: firstLen subarray followed by secondLen subarray
        for (int i = firstLen; i + secondLen <= nums.length; i++) {
            // Max sum of firstLen subarray ending at position i
            maxFirstSum = Math.max(maxFirstSum, prefixSum[i] - prefixSum[i - firstLen]);
            
            // Current sum of secondLen subarray starting at position i
            int currSecondSum = prefixSum[i + secondLen] - prefixSum[i];
            
            maxSum = Math.max(maxSum, maxFirstSum + currSecondSum);
        }
        
        // Reset
        maxFirstSum = 0;
        
        // Case 2: secondLen subarray followed by firstLen subarray
        for (int i = secondLen; i + firstLen <= nums.length; i++) {
            // Max sum of secondLen subarray ending at position i
            maxSecondSum = Math.max(maxSecondSum, prefixSum[i] - prefixSum[i - secondLen]);
            
            // Current sum of firstLen subarray starting at position i
            int currFirstSum = prefixSum[i + firstLen] - prefixSum[i];
            
            maxSum = Math.max(maxSum, maxSecondSum + currFirstSum);
        }
        
        return maxSum;
    }
}
```

## Key Insights

1. **Prefix Sums**: Using prefix sums enables efficient computation of subarray sums in O(1) time.

2. **Two Cases**: We need to consider both possibilities: first subarray before second, and second before first.

3. **Running Maximum**: For each position, we maintain the maximum sum of a subarray ending before that position, which allows us to find the optimal solution without brute force.

4. **Non-Overlapping Constraint**: The key challenge is ensuring the subarrays don't overlap. We handle this by positioning the second subarray to start only after the first one ends.

5. **Array Bounds**: Pay attention to the bounds when computing subarray sums using prefix sums. The indices need careful adjustment.

6. **Optimization**: The problem can be solved in a single pass through the array for each case by keeping track of running maximums.

7. **Combinatorial Problem**: This is a combinatorial problem where we're choosing the best combination of two subarrays of fixed lengths, making the sliding window approach appropriate.
