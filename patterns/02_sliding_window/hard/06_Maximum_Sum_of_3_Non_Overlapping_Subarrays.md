# Maximum Sum of 3 Non-Overlapping Subarrays

## Problem Statement

You are given an integer array `nums` and an integer `k`. Find three non-overlapping subarrays of length `k` each with the largest possible sum and return the sum.

## Examples

**Example 1:**
```
Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: 23
Explanation: The three subarrays are [1,2], [6,7], and [5,1].
1 + 2 + 6 + 7 + 5 + 1 = 23
```

**Example 2:**
```
Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: 13
Explanation: The three subarrays are [1,2], [1,2], and [1,2].
1 + 2 + 1 + 2 + 1 + 2 = 13
```

## Approach: Sliding Window with Dynamic Programming

This problem combines the sliding window technique with dynamic programming. We first compute the sum of all possible k-sized subarrays, then use dynamic programming to find the optimal combination of three non-overlapping subarrays.

### Algorithm:

1. Calculate the sum of each possible k-sized subarray using a sliding window
2. Compute left and right maximal subarray sums:
   - For each position i, find the maximum sum of a k-sized subarray ending at or before position i (left max)
   - For each position i, find the maximum sum of a k-sized subarray starting at or after position i (right max)
3. For each possible middle subarray position, find the best combination with left and right subarrays

## Code Solution

```java
public class MaximumSumOf3NonOverlappingSubarrays {
    public int maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        
        // Calculate sum of all k-sized subarrays
        int[] windowSums = new int[n - k + 1]; // windowSums[i] = sum of nums[i:i+k-1]
        int sum = 0;
        
        // Calculate the first window sum
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        windowSums[0] = sum;
        
        // Calculate remaining window sums with sliding window
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            windowSums[i - k + 1] = sum;
        }
        
        // leftMax[i] = index of the max sum k-sized subarray from 0 to i
        int[] leftMax = new int[n - k + 1];
        int bestSumIndex = 0;
        
        for (int i = 0; i < leftMax.length; i++) {
            if (windowSums[i] > windowSums[bestSumIndex]) {
                bestSumIndex = i;
            }
            leftMax[i] = bestSumIndex;
        }
        
        // rightMax[i] = index of the max sum k-sized subarray from i to n-k
        int[] rightMax = new int[n - k + 1];
        bestSumIndex = n - k;
        
        for (int i = rightMax.length - 1; i >= 0; i--) {
            if (windowSums[i] >= windowSums[bestSumIndex]) {
                bestSumIndex = i;
            }
            rightMax[i] = bestSumIndex;
        }
        
        // Find the best trio of non-overlapping subarrays
        int maxSum = 0;
        int[] bestIndices = new int[3];
        
        // Try each possible middle subarray
        for (int mid = k; mid < n - 2 * k + 1; mid++) {
            int left = leftMax[mid - k];
            int right = rightMax[mid + k];
            
            int totalSum = windowSums[left] + windowSums[mid] + windowSums[right];
            
            if (totalSum > maxSum) {
                maxSum = totalSum;
                bestIndices[0] = left;
                bestIndices[1] = mid;
                bestIndices[2] = right;
            }
        }
        
        return maxSum;
    }
}
```

## Alternate Solution: Return the Indices

If we need to return the starting indices of the subarrays rather than just the sum:

```java
public class MaximumSumOf3NonOverlappingSubarraysIndices {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[3];
        
        // Calculate sum of all k-sized subarrays
        int[] windowSums = new int[n - k + 1];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        windowSums[0] = sum;
        
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            windowSums[i - k + 1] = sum;
        }
        
        // leftIndices[i] = index of the max sum subarray from 0 to i
        int[] leftIndices = new int[n - k + 1];
        int bestSumIndex = 0;
        for (int i = 0; i < leftIndices.length; i++) {
            if (windowSums[i] > windowSums[bestSumIndex]) {
                bestSumIndex = i;
            }
            leftIndices[i] = bestSumIndex;
        }
        
        // rightIndices[i] = index of the max sum subarray from i to n-k
        int[] rightIndices = new int[n - k + 1];
        bestSumIndex = n - k;
        for (int i = rightIndices.length - 1; i >= 0; i--) {
            // Use >= to favor earlier indices when there's a tie
            if (windowSums[i] >= windowSums[bestSumIndex]) {
                bestSumIndex = i;
            }
            rightIndices[i] = bestSumIndex;
        }
        
        // Find the best trio of non-overlapping subarrays
        int maxSum = 0;
        
        // Try each possible middle subarray
        for (int mid = k; mid < n - 2 * k + 1; mid++) {
            int left = leftIndices[mid - k];
            int right = rightIndices[mid + k];
            
            int totalSum = windowSums[left] + windowSums[mid] + windowSums[right];
            
            if (totalSum > maxSum) {
                maxSum = totalSum;
                result[0] = left;
                result[1] = mid;
                result[2] = right;
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We make a single pass through the array to compute window sums and another pass to compute left and right maximal indices.
- **Space Complexity**: O(n) for storing the window sums and maximal indices arrays.

## Key Insights

1. This problem demonstrates how the sliding window technique can be combined with dynamic programming to solve complex optimization problems.
2. By precomputing window sums, we avoid redundant calculations when evaluating different subarray combinations.
3. The left and right maximal arrays allow us to efficiently find the optimal subarrays for any given middle subarray position.
4. Careful handling of ties (using >= for the right maximal calculation) ensures we favor earlier indices when sums are equal.
5. The solution generalizes to finding any fixed number of non-overlapping subarrays with maximum sum, not just three.
