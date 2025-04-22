# Count of Range Sum (LeetCode #327)

## Problem Statement
Given an integer array `nums` and two integers `lower` and `upper`, return the number of range sums that lie in `[lower, upper]` inclusive.

Range sum `S(i, j)` is defined as the sum of the elements in `nums` between indices `i` and `j` inclusive, where `i <= j`.

## Example 1:
```
Input: nums = [-2,5,-1], lower = -2, upper = 2
Output: 3
Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
```

## Example 2:
```
Input: nums = [0], lower = 0, upper = 0
Output: 1
```

## Constraints:
- 1 <= nums.length <= 10^4
- -2^31 <= nums[i] <= 2^31 - 1
- -10^5 <= lower <= upper <= 10^5
- The answer is guaranteed to fit in a 32-bit integer.

## Two Pointers Approach with Merge Sort
```java
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1]; // Prefix sums
        
        // Calculate prefix sums
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                  + countWhileMergeSort(sums, mid, end, lower, upper);
        
        // Count ranges that cross the midpoint
        int j = mid, k = mid;
        for (int i = start; i < mid; i++) {
            // Find the smallest j such that sums[j] - sums[i] >= lower
            while (j < end && sums[j] - sums[i] < lower) j++;
            
            // Find the largest k such that sums[k] - sums[i] <= upper
            while (k < end && sums[k] - sums[i] <= upper) k++;
            
            count += k - j;
        }
        
        // Merge the sorted subarrays
        merge(sums, start, mid, end);
        
        return count;
    }
    
    private void merge(long[] sums, int start, int mid, int end) {
        long[] temp = new long[end - start];
        int i = start, j = mid, k = 0;
        
        while (i < mid && j < end) {
            if (sums[i] <= sums[j]) {
                temp[k++] = sums[i++];
            } else {
                temp[k++] = sums[j++];
            }
        }
        
        while (i < mid) {
            temp[k++] = sums[i++];
        }
        
        while (j < end) {
            temp[k++] = sums[j++];
        }
        
        System.arraycopy(temp, 0, sums, start, end - start);
    }
}
```

## Time Complexity
O(n log n) where n is the length of the array, due to the merge sort algorithm.

## Space Complexity
O(n) for the prefix sums array and the temporary arrays used in merge sort.