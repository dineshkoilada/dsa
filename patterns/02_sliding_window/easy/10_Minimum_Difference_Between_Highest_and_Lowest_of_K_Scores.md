# Minimum Difference Between Highest and Lowest of K Scores

## Problem Statement

You are given a 0-indexed integer array `nums`, where `nums[i]` represents the score of the ith student. You are also given an integer `k`.

Pick the scores of any `k` students from the array so that the difference between the highest and the lowest of the `k` scores is minimized.

Return the minimum possible difference.

## Examples

**Example 1:**
```
Input: nums = [90,98,89,86,95,93,97,88], k = 3
Output: 2
Explanation: 
We can pick from the students with scores [89,86,95] or [89,95,93].
The best subset we can choose is the students with scores [89,95,93].
The difference between the highest and lowest score is 95 - 89 = 6.
```

**Example 2:**
```
Input: nums = [9,4,1,7], k = 2
Output: 2
Explanation: 
We can pick from the students with scores [9,7].
The difference between the highest and lowest score is 9 - 7 = 2.
```

## Approach: Sorting + Sliding Window

This problem requires us to find the minimum range of k elements from the array. We'll use a combination of sorting and the sliding window technique.

### Algorithm:

1. Sort the array in ascending order
2. Initialize a window of size `k` (the first k elements)
3. Calculate the initial difference between the highest and lowest scores
4. Slide the window one element at a time, recalculating the difference
5. Return the minimum difference found

## Code Solution

```java
import java.util.Arrays;

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public int minimumDifference(int[] nums, int k) {
        // If k is 1, the difference is always 0
        if (k == 1) {
            return 0;
        }
        
        // Sort the array
        Arrays.sort(nums);
        
        int minDifference = Integer.MAX_VALUE;
        
        // Slide a window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            // Calculate the difference between the highest and lowest scores in this window
            int difference = nums[i + k - 1] - nums[i];
            
            // Update the minimum difference
            minDifference = Math.min(minDifference, difference);
        }
        
        return minDifference;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n log n) where n is the length of the array. The dominant operation is sorting the array, which takes O(n log n). The sliding window iteration afterwards is O(n).
- **Space Complexity**: O(1) or O(n) depending on the sorting algorithm used. Most standard sorting implementations use O(log n) or O(n) extra space.

## Key Insights

1. This problem combines sorting with the sliding window technique to efficiently find the minimum difference.
2. By sorting the array first, we ensure that the lowest and highest scores in each window are at the start and end of the window, respectively.
3. The sliding window approach allows us to check all possible k-sized subsets while maintaining O(n) time complexity for the window operations.
4. This is a different application of the sliding window pattern than many other problems, as we're not computing a running sum or count but rather looking for the minimum range.
5. The problem illustrates how the sliding window pattern can be combined with other techniques (like sorting) to solve optimization problems efficiently.
