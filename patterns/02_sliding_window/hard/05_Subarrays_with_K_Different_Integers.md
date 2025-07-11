# Subarrays with K Different Integers

## Problem Statement

Given an array `nums` of positive integers and an integer `k`, return the number of subarrays where the number of different integers in that subarray is exactly `k`.

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

## Approach: Sliding Window using At Most K Distinct Elements

This problem is tricky because directly counting subarrays with exactly k distinct integers is challenging with a simple sliding window. However, we can use a clever approach:

**Number of subarrays with exactly k distinct integers = Number of subarrays with at most k distinct integers - Number of subarrays with at most (k-1) distinct integers**

### Algorithm:

1. Create a helper function `atMostK` that counts subarrays with at most K distinct integers
2. The final result is `atMostK(nums, k) - atMostK(nums, k-1)`

The `atMostK` function:
1. Use a sliding window with two pointers: `left` and `right`
2. Use a HashMap to track frequency of each integer in the current window
3. Expand the window to the right, adding elements to the HashMap
4. When the number of distinct elements exceeds k, shrink the window from the left
5. For each valid window, add `right - left + 1` to the count (this counts all subarrays ending at `right`)

## Code Solution

```java
import java.util.*;

public class SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Subarrays with exactly K distinct = 
        // Subarrays with at most K distinct - Subarrays with at most (K-1) distinct
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    private int atMostK(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int left = 0;
        int result = 0;
        
        for (int right = 0; right < n; right++) {
            // Add the current element to our window
            frequencyMap.put(nums[right], frequencyMap.getOrDefault(nums[right], 0) + 1);
            
            // Shrink window until we have at most k distinct elements
            while (frequencyMap.size() > k) {
                frequencyMap.put(nums[left], frequencyMap.get(nums[left]) - 1);
                if (frequencyMap.get(nums[left]) == 0) {
                    frequencyMap.remove(nums[left]);
                }
                left++;
            }
            
            // Add count of all subarrays ending at 'right'
            // For each right pointer, we count all possible left pointers
            // which is equal to (right - left + 1)
            result += right - left + 1;
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We call the `atMostK` function twice, each of which is O(n).
- **Space Complexity**: O(k) in the worst case, where the frequency map stores at most k+1 distinct integers.

## Key Insights

1. This problem demonstrates a more advanced application of the sliding window technique where we use a mathematical trick to count "exactly k" by using "at most k" and "at most k-1".

2. The most important insight is understanding how to count all subarrays ending at the right pointer. For each valid window with boundaries [left, right], there are (right - left + 1) valid subarrays ending at right. This is because we can choose any element from index left to right as the starting point.

3. The time complexity remains linear despite having to call the sliding window function twice because each call to `atMostK` is O(n).

4. This technique of "at most K - at most (K-1)" can be applied to other problems requiring exact counts with sliding windows.

5. The solution elegantly handles the challenge of counting subarrays with exactly k distinct integers, which is not trivial to do with a single pass of a sliding window.
