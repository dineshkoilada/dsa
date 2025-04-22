# Frequency of the Most Frequent Element

## Problem Statement

The **frequency** of an element is the number of times it occurs in an array.

You are given an integer array `nums` and an integer `k`. In one operation, you can choose an index of `nums` and increment the element at that index by 1.

Return the **maximum possible frequency** of an element after performing **at most** `k` operations.

## Examples

**Example 1:**
```
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.
```

**Example 2:**
```
Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
```

**Example 3:**
```
Input: nums = [3,9,6], k = 2
Output: 1
Explanation: No element can be incremented more than once.
```

## Approach: Sliding Window

The key insight is that we can only increase values, not decrease them. To maximize frequency, we should make elements equal to some target value by performing operations.

Since we can only increment elements, we should sort the array first. Then we can use a sliding window to find the maximum window size where we can make all elements equal to the rightmost element using at most `k` operations.

### Algorithm:

1. Sort the array
2. Use a sliding window to track a range of elements
3. For each range, calculate the cost to make all elements equal to the largest element
4. If the cost exceeds `k`, shrink the window from the left
5. Keep track of the maximum window size, which represents the maximum frequency achievable

## Code Solution

```java
import java.util.Arrays;

public class MaxFrequency {
    public int maxFrequency(int[] nums, int k) {
        // Sort the array
        Arrays.sort(nums);
        
        int left = 0;
        int maxFrequency = 0;
        long operations = 0; // Use long to prevent overflow
        
        // Sliding window approach
        for (int right = 0; right < nums.length; right++) {
            // To make all elements in the window equal to nums[right],
            // we need to increment each element by (nums[right] - its value)
            operations += nums[right];
            
            // Calculate the total cost of making all elements equal to the rightmost
            // Total cost = current window size * rightmost value - sum of elements in window
            long totalCost = (long)(right - left + 1) * nums[right] - operations;
            
            // If cost exceeds k, shrink the window from the left
            while (left <= right && totalCost > k) {
                operations -= nums[left];
                left++;
                totalCost = (long)(right - left + 1) * nums[right] - operations;
            }
            
            // Update the maximum frequency
            maxFrequency = Math.max(maxFrequency, right - left + 1);
        }
        
        return maxFrequency;
    }
}
```

## Optimized Solution

A more intuitive approach is to calculate the number of operations needed for each window directly:

```java
import java.util.Arrays;

public class MaxFrequencyOptimized {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        
        int left = 0;
        int maxFrequency = 0;
        long windowSum = 0; // Sum of elements in the current window
        
        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            
            // Calculate the cost to make all elements in the window equal to nums[right]
            // Cost = (window size * nums[right]) - window sum
            while ((long)nums[right] * (right - left + 1) - windowSum > k) {
                windowSum -= nums[left];
                left++;
            }
            
            maxFrequency = Math.max(maxFrequency, right - left + 1);
        }
        
        return maxFrequency;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n log n) where n is the length of the array. Sorting the array takes O(n log n) time, and the sliding window takes O(n) time.
- **Space Complexity**: O(log n) or O(n) depending on the sorting algorithm implementation.

## Key Insights

1. Sorting the array is crucial for this problem because we can only increment elements, not decrement them.
2. After sorting, we can use a sliding window to find consecutive elements that can be made equal with at most `k` operations.
3. For each window, the optimal strategy is to make all elements equal to the largest element (the rightmost element after sorting).
4. The cost calculation is key: for a window with elements [a, b, c], the cost to make all equal to c is (c-a) + (c-b) + (c-c) = 3c - (a+b+c).
5. We can generalize this to: cost = window_size * rightmost_value - sum_of_window.
6. This problem showcases how sorting can transform a complex problem into a sliding window problem.
7. When working with large numbers, be careful about potential integer overflow (hence using long for calculations).