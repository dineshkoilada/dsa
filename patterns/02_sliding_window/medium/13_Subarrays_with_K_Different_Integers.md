# Subarrays with K Different Integers

## Problem Statement

Given an integer array `nums` and an integer `k`, return the number of good subarrays.

A **good array** is an array where the number of different integers in that array is exactly `k`.

## Examples

**Example 1:**
```
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
```

**Example 2:**
```
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4]
```

## Approach: Sliding Window with Difference Method

This problem is tricky because a standard sliding window approach would typically find subarrays with **at most** K different integers, not **exactly** K. The key insight is that we can use the difference between:
- Number of subarrays with at most K different integers
- Number of subarrays with at most (K-1) different integers

This difference gives us the number of subarrays with exactly K different integers.

### Algorithm:

1. Create a helper function `atMostK` that calculates the number of subarrays with at most K different integers
2. Calculate `atMostK(nums, k) - atMostK(nums, k-1)`
3. The result is the number of subarrays with exactly K different integers

## Code Solution

```java
import java.util.*;

public class SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    // Helper function to count subarrays with at most K different integers
    private int atMostK(int[] nums, int k) {
        int[] count = new int[nums.length + 1]; // Assuming nums[i] is within range
        int left = 0;
        int result = 0;
        int distinct = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Add the current element to our window
            if (count[nums[right]]++ == 0) {
                distinct++;
            }
            
            // Shrink window while we have more than k distinct elements
            while (distinct > k) {
                if (--count[nums[left]] == 0) {
                    distinct--;
                }
                left++;
            }
            
            // Count subarrays ending at index right
            result += right - left + 1;
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We call the `atMostK` function twice, and each call processes the array in O(n) time.
- **Space Complexity**: O(n) for the count array, which in the worst case needs to store the frequency of each unique integer in the input array.

## Alternative Implementation

If the range of integers is large, we can use a HashMap instead:

```java
import java.util.*;

public class SubarraysWithKDifferentIntegersHashMap {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    private int atMostK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int result = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Add the current element to our window
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
            
            // Shrink window while we have more than k distinct elements
            while (count.size() > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                if (count.get(nums[left]) == 0) {
                    count.remove(nums[left]);
                }
                left++;
            }
            
            // Count subarrays ending at index right
            result += right - left + 1;
        }
        
        return result;
    }
}
```

## Intuition Behind Counting

The key part of the solution is understanding why `right - left + 1` gives us the count of valid subarrays ending at position `right`. This works because:

For each valid window `[left, right]`, the subarrays ending at `right` are:
- `[right]`
- `[right-1, right]`
- `[right-2, right]`
- ...
- `[left, left+1, ..., right]`

There are exactly `right - left + 1` such subarrays.

## Key Insights

1. This problem introduces a clever technique of using the difference between "at most K" and "at most K-1" to find "exactly K".
2. The sliding window pattern is adapted to count subarrays rather than just finding a maximum length.
3. Understanding how to count subarrays within a window is critical for this problem.
4. The solution demonstrates how to transform a problem that doesn't initially seem like a standard sliding window into one that fits the pattern.
5. This approach can be generalized to other problems requiring exact counts with sliding window constraints.
