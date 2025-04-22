# Subarrays with K Different Integers (LeetCode #992)

## Problem Statement
Given an array `nums` of positive integers, call a (contiguous, not necessarily distinct) subarray of `nums` good if the number of different integers in that subarray is exactly `k`.

Return the number of good subarrays of `nums`.

## Example 1:
```
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
```

## Example 2:
```
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
```

## Constraints:
- 1 <= nums.length <= 2 * 10^4
- 1 <= nums[i], k <= nums.length

## Two Pointers Approach
```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // The key insight: count of subarrays with exactly K distinct integers
        // = count of subarrays with at most K distinct integers - count of subarrays with at most (K-1) distinct integers
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    private int atMostK(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[n + 1]; // Frequency counter for each integer
        int left = 0;
        int result = 0;
        int distinct = 0;
        
        for (int right = 0; right < n; right++) {
            // Add current element to window
            if (count[nums[right]]++ == 0) {
                distinct++;
            }
            
            // Shrink window if needed
            while (distinct > k) {
                if (--count[nums[left]] == 0) {
                    distinct--;
                }
                left++;
            }
            
            // All subarrays ending at 'right' with at most k distinct integers
            result += right - left + 1;
        }
        
        return result;
    }
}
```

## Time Complexity
O(n) where n is the length of the array.

## Space Complexity
O(n) for the frequency counter array.