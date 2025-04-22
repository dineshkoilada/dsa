# 3Sum Closest (LeetCode #16)

## Problem Statement
Given an integer array `nums` of length `n` and an integer `target`, find three integers in `nums` such that the sum is closest to `target`.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

## Example 1:
```
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

## Example 2:
```
Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
```

## Constraints:
- 3 <= nums.length <= 500
- -1000 <= nums[i] <= 1000
- -10^4 <= target <= 10^4

## Two Pointers Approach
```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Sort the array
        Arrays.sort(nums);
        
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with the first three elements
        
        for (int i = 0; i < n - 2; i++) {
            // Using two pointers for the remaining elements
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // Update the closest sum if the current sum is closer to the target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                if (currentSum < target) {
                    left++; // Try to get a larger sum
                } else if (currentSum > target) {
                    right--; // Try to get a smaller sum
                } else {
                    // If the sum equals the target, we can't get any closer
                    return target;
                }
            }
        }
        
        return closestSum;
    }
}
```

## Time Complexity
O(n²) where n is the length of the array. We sort the array (O(n log n)) and then use two nested loops.

## Space Complexity
O(log n) to O(n) depending on the sorting algorithm implementation, plus O(1) additional space for the two pointers.