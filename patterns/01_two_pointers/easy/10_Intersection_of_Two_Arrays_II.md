# Intersection of Two Arrays II (LeetCode #350)

## Problem Statement
Given two integer arrays `nums1` and `nums2`, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

## Example 1:
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```

## Example 2:
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
```

## Constraints:
- 1 <= nums1.length, nums2.length <= 1000
- 0 <= nums1[i], nums2[i] <= 1000

## Two Pointers Approach
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        // Initialize two pointers
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();
        
        // Compare elements using two pointers
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // Found a common element
                result.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        // Convert list to array
        int[] intersection = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            intersection[k] = result.get(k);
        }
        
        return intersection;
    }
}
```

## Time Complexity
O(n log n + m log m) where n and m are the lengths of the arrays (due to sorting).

## Space Complexity
O(min(n, m)) for the result array in the worst case.