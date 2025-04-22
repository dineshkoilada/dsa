# Shortest Subarray with Sum at Least K (LeetCode #862)

## Problem Statement
Given an integer array `nums` and an integer `k`, return the length of the shortest non-empty subarray of `nums` with a sum of at least `k`. If there is no such subarray, return -1.

## Example 1:
```
Input: nums = [1], k = 1
Output: 1
```

## Example 2:
```
Input: nums = [1,2], k = 4
Output: -1
```

## Example 3:
```
Input: nums = [2,-1,2], k = 3
Output: 3
```

## Constraints:
- 1 <= nums.length <= 10^5
- -10^5 <= nums[i] <= 10^5
- 1 <= k <= 10^9

## Two Pointers Approach with Deque
```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int minLength = n + 1; // Initialize with a value larger than possible answers
        
        // Compute prefix sums
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        // Use a deque to store indices of increasing prefix sums
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i <= n; i++) {
            // For each index, find the earliest index j such that 
            // sum(j+1 to i) >= k by using prefix sums
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            
            // Remove indices that won't be optimal for future calculations
            // (if prefixSum[j] >= prefixSum[i], j < i, then j is never better than i)
            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
        }
        
        return minLength <= n ? minLength : -1;
    }
}
```

## Time Complexity
O(n) where n is the length of the array. Each element is pushed and popped from the deque at most once.

## Space Complexity
O(n) for the prefix sum array and the deque.