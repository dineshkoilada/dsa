# Median of Two Sorted Arrays (LeetCode #4)

## Problem Statement
Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

## Example 1:
```
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
```

## Example 2:
```
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
```

## Constraints:
- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -10^6 <= nums1[i], nums2[i] <= 10^6

## Two Pointers Approach with Binary Search
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for simplicity
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int x = nums1.length;
        int y = nums2.length;
        
        int low = 0;
        int high = x;
        
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;
            
            // If partitionX is 0, use -INF for maxX
            // If partitionX is x, use INF for minX
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            
            // If partitionY is 0, use -INF for maxY
            // If partitionY is y, use INF for minY
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];
            
            if (maxX <= minY && maxY <= minX) {
                // We have found the correct partition
                
                // If total length is odd
                if ((x + y) % 2 != 0) {
                    return Math.max(maxX, maxY);
                } else {
                    // If total length is even
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                }
            } else if (maxX > minY) {
                // Move partition to the left
                high = partitionX - 1;
            } else {
                // Move partition to the right
                low = partitionX + 1;
            }
        }
        
        // If we reach here, the input arrays were not sorted
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
```

## Time Complexity
O(log(min(m,n))) where m and n are the lengths of the arrays.

## Space Complexity
O(1) as we only use a constant amount of extra space.