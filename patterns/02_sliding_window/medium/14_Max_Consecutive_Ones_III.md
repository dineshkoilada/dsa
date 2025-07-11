# Max Consecutive Ones III

## Problem Statement

Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` 0's.

## Examples

**Example 1:**
```
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
             Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
```

**Example 2:**
```
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
             Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
```

## Approach: Sliding Window

This problem can be elegantly solved using the sliding window technique. The key insight is that we want to find the longest subarray that contains at most `k` zeros which we can flip to ones.

### Algorithm:

1. Initialize two pointers `left` and `right` at the beginning of the array
2. Maintain a counter for the number of zeros in the current window
3. Expand the window by moving the `right` pointer as long as we have at most `k` zeros
4. When we have more than `k` zeros, shrink the window from the left until we have at most `k` zeros again
5. Track the maximum window size during this process

## Code Solution

```java
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right;
        int zeroCount = 0;
        int maxConsecutiveOnes = 0;
        
        for (right = 0; right < nums.length; right++) {
            // If the current element is 0, increment the count of zeros
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // If we have more than k zeros, we need to shrink the window
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            // Update the maximum consecutive ones
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, right - left + 1);
        }
        
        return maxConsecutiveOnes;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. Each element is visited at most twice (once by the right pointer and once by the left pointer).
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.

## Key Insights

1. This problem demonstrates the variable-size sliding window pattern where we maintain a window with at most `k` zeros.
2. Instead of actually flipping the zeros, we just keep track of how many zeros are in our current window.
3. The length of the window gives us the number of consecutive ones after the flips.
4. This solution works because we're looking for the maximum length of consecutive ones, so we always try to expand our window as much as possible.
5. When we can no longer expand (because we'd exceed `k` zeros), we shrink from the left, removing zeros until we're back under our limit.
