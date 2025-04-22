# Sort Colors (LeetCode #75)

## Problem Statement
Given an array `nums` with `n` objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

## Example 1:
```
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

## Example 2:
```
Input: nums = [2,0,1]
Output: [0,1,2]
```

## Constraints:
- n == nums.length
- 1 <= n <= 300
- nums[i] is either 0, 1, or 2.

## Two Pointers Approach (Dutch National Flag Algorithm)
```java
class Solution {
    public void sortColors(int[] nums) {
        // Initialize the three pointers
        int low = 0;        // for 0s (red)
        int mid = 0;        // for 1s (white)
        int high = nums.length - 1;  // for 2s (blue)
        
        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    // Swap nums[low] and nums[mid]
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    // No need to swap, just move the mid pointer
                    mid++;
                    break;
                case 2:
                    // Swap nums[mid] and nums[high]
                    swap(nums, mid, high);
                    high--;
                    // Don't increment mid here because we need to check the newly swapped element
                    break;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

## Time Complexity
O(n) where n is the length of the array.

## Space Complexity
O(1) as we only use three pointers and modify the array in-place.