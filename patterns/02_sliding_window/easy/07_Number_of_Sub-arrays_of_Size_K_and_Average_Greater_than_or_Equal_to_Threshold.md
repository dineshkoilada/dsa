# Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

## Problem Statement

Given an array of integers `arr` and two integers `k` and `threshold`, return the number of sub-arrays of size `k` and average greater than or equal to `threshold`.

## Examples

**Example 1:**
```
Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5], [5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
```

**Example 2:**
```
Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
```

## Approach: Fixed-Size Sliding Window

This problem is a classic fixed-size sliding window problem where we need to count subarrays with a specific property.

### Algorithm:

1. Calculate the sum of the first `k` elements
2. Check if the average (sum/k) meets the threshold
3. Slide the window through the array:
   - Add the new element to the sum
   - Remove the element that's going out of the window
   - Check if the average meets the threshold
4. Return the count of qualifying subarrays

## Code Solution

```java
public class SubarraysWithAverageGreaterThanThreshold {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;
        int sum = 0;
        
        // Calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        
        // Check if the average meets threshold
        if (sum / k >= threshold) {
            count++;
        }
        
        // Slide the window
        for (int i = k; i < arr.length; i++) {
            // Add new element and remove element going out of window
            sum = sum + arr[i] - arr[i - k];
            
            // Check if the average meets threshold
            if (sum / k >= threshold) {
                count++;
            }
        }
        
        return count;
    }
}
```

## Optimized Solution

The solution can be optimized by checking against the sum threshold directly rather than calculating averages, which might lead to floating-point precision issues:

```java
public class SubarraysWithAverageGreaterThanThresholdOptimized {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;
        int sum = 0;
        int sumThreshold = k * threshold; // Calculate sum threshold
        
        // Calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        
        // Check if the sum meets threshold
        if (sum >= sumThreshold) {
            count++;
        }
        
        // Slide the window
        for (int i = k; i < arr.length; i++) {
            // Add new element and remove element going out of window
            sum = sum + arr[i] - arr[i - k];
            
            // Check if the sum meets threshold
            if (sum >= sumThreshold) {
                count++;
            }
        }
        
        return count;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. We process each element exactly once.
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.

## Key Insights

1. This problem demonstrates the fixed-size sliding window pattern where we track a property (the sum) as we slide the window.
2. Instead of recalculating the sum for each window from scratch (which would be O(n*k)), we maintain a running sum as we slide the window.
3. The optimized solution avoids potential precision issues with floating-point division by comparing sums directly.
4. By computing the threshold sum (k * threshold) once at the beginning, we save redundant multiplication operations.
5. This problem shows how the sliding window pattern can be used to efficiently count elements with specific properties.
