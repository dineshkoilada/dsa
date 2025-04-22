# Move Zeroes (LeetCode #283)

## Problem Statement
Given an integer array `nums`, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

## Example 1:
```
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

## Example 2:
```
Input: nums = [0]
Output: [0]
```

## Constraints:
- 1 <= nums.length <= 10^4
- -2^31 <= nums[i] <= 2^31 - 1

## Two Pointers Approach
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0; // Position to place the next non-zero element
        
        // Move all non-zero elements to the front
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // Fill remaining positions with zeroes
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}
```

## Time Complexity
O(n) where n is the length of the array.

## Space Complexity
O(1) as we only use two pointers and modify the array in-place.