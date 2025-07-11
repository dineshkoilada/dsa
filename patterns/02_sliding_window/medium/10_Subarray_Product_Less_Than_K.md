# Subarray Product Less Than K

## Problem Statement

Given an array of positive integers `nums` and an integer `k`, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than `k`.

## Examples

**Example 1:**
```
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
Note that [10,5,2] is not included as the product of 100 is not strictly less than k.
```

**Example 2:**
```
Input: nums = [1,2,3], k = 0
Output: 0
```

## Approach: Sliding Window with Running Product

This problem can be efficiently solved using a sliding window approach where we maintain a running product of elements in the current window.

### Algorithm:

1. Initialize a sliding window with left and right pointers both at the start of the array
2. Maintain a running product of elements in the current window
3. When the product exceeds or equals k, shrink the window from the left until the product is less than k
4. For each position of the right pointer, add the number of valid subarrays ending at that position
5. Return the total count of valid subarrays

## Code Solution

```java
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // If k <= 1, no subarray will have product less than k
        if (k <= 1) return 0;
        
        int product = 1;
        int count = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Expand the window by including the element at right
            product *= nums[right];
            
            // Shrink the window from the left while product >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            // Count subarrays ending at right index
            // (right - left + 1) represents the number of subarrays ending at right
            count += right - left + 1;
        }
        
        return count;
    }
}
```

## Intuition Behind the Count Logic

The key insight in this solution is understanding why we add `right - left + 1` to our count for each valid window. 

For each position of the right pointer, we're counting all valid subarrays that:
1. End at the current right pointer position
2. Have a product less than k

When we have a valid window [left, right], the valid subarrays ending at right are:
- [right]
- [right-1, right]
- [right-2, right]
- ...
- [left, left+1, ..., right]

There are exactly (right - left + 1) such subarrays.

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. Each element is processed at most twice (once when it enters the window and once when it leaves).
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.

## Key Insights

1. This problem demonstrates how a sliding window approach can be used for product-based constraints, not just sum-based ones.
2. The key to solving this problem efficiently is recognizing that once we have a valid window, we can count multiple valid subarrays at once.
3. The solution leverages the fact that all numbers are positive, which ensures that removing elements from the left always decreases the product.
4. Unlike some sliding window problems, we don't need to explicitly check all subarrays; instead, we can count them mathematically.
5. Special care must be taken for the case where k ≤ 1, as no positive number or product can be strictly less than 1.
