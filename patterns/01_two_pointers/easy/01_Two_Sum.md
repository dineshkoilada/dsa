# Two Sum (LeetCode #1)

## Problem Statement
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Example 1:
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

## Example 2:
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

## Example 3:
```
Input: nums = [3,3], target = 6
Output: [0,1]
```

## Constraints:
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- Only one valid answer exists.

## Two Pointers Approach
Although the standard solution uses a hash map, this problem can be solved with two pointers if the array is sorted first.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a copy of the array with indices
        int[][] numWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numWithIndex[i][0] = nums[i];
            numWithIndex[i][1] = i;
        }
        
        // Sort the array
        Arrays.sort(numWithIndex, (a, b) -> a[0] - b[0]);
        
        // Two pointers
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = numWithIndex[left][0] + numWithIndex[right][0];
            if (sum == target) {
                return new int[] { numWithIndex[left][1], numWithIndex[right][1] };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[] { -1, -1 }; // No solution found
    }
}
```

## Time Complexity
O(n log n) due to the sorting operation.

## Space Complexity
O(n) for the numWithIndex array.