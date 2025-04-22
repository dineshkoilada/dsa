# Trapping Rain Water (LeetCode #42)

## Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

## Example 1:
```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The elevation map is shown above. In this case, 6 units of rain water are being trapped.
```

## Example 2:
```
Input: height = [4,2,0,3,2,5]
Output: 9
```

## Constraints:
- n == height.length
- 1 <= n <= 2 * 10^4
- 0 <= height[i] <= 10^5

## Two Pointers Approach
```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0; // Cannot trap water with less than 3 elements
        
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                // If current left height is greater than leftMax, update leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // If not, calculate trapped water at this position
                    water += leftMax - height[left];
                }
                left++;
            } else {
                // If current right height is greater than rightMax, update rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // If not, calculate trapped water at this position
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
}
```

## Time Complexity
O(n) where n is the length of the array.

## Space Complexity
O(1) as we only use a constant amount of extra space.