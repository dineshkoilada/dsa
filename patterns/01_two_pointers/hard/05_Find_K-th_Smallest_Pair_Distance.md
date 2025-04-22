# Find K-th Smallest Pair Distance (LeetCode #719)

## Problem Statement
The distance of a pair of integers `a` and `b` is defined as the absolute difference between `a` and `b`.

Given an integer array `nums` and an integer `k`, return the `k`th smallest distance among all the pairs `nums[i]` and `nums[j]` where `0 <= i < j < nums.length`.

## Example 1:
```
Input: nums = [1,3,1], k = 1
Output: 0
Explanation: The smallest distance is 0 between pair (1,1) at indices (0,2).
```

## Example 2:
```
Input: nums = [1,1,1], k = 2
Output: 0
Explanation: The smallest distance is 0 between pair (1,1) at indices (0,1).
The 2nd smallest distance is also 0 between pair (1,1) at indices (0,2).
```

## Example 3:
```
Input: nums = [1,6,1], k = 3
Output: 5
Explanation: The distances between pairs are [0,5,5].
The 3rd smallest distance is 5 between pair (1,6) at indices (0,1) and (1,6) at indices (1,2).
```

## Constraints:
- n == nums.length
- 2 <= n <= 10^4
- 0 <= nums[i] <= 10^6
- 1 <= k <= n * (n - 1) / 2

## Two Pointers Approach with Binary Search
```java
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        // Sort the array
        Arrays.sort(nums);
        
        // Define the range for binary search
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Count pairs with distance <= mid
            int count = countPairs(nums, mid);
            
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return low;
    }
    
    // Count pairs with distance <= dist
    private int countPairs(int[] nums, int dist) {
        int count = 0;
        int left = 0;
        
        // For each element, count elements to its right with distance <= dist
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > dist) {
                left++;
            }
            count += right - left;
        }
        
        return count;
    }
}
```

## Time Complexity
O(n log n + n log W) where n is the length of the array and W is the range of values in nums.

## Space Complexity
O(1) for the sorting algorithm and the binary search.