# Sliding Window Maximum

## Problem Statement

You are given an array of integers `nums`, a sliding window of size `k` moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position, return the maximum element in the current window.

## Examples

**Example 1:**
```
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

**Example 2:**
```
Input: nums = [1], k = 1
Output: [1]
```

## Approach: Using a Deque

A naive approach would be to find the maximum for each window, which would result in an O(n*k) solution. However, we can optimize this using a deque (double-ended queue) data structure to maintain potential maximums, resulting in an O(n) solution.

### Algorithm:

1. Create a deque to store indices of array elements
2. Process the first k elements separately to build the initial deque
3. For each element at index i:
   - Remove elements from the deque's back that are smaller than the current element
   - Remove elements from the deque's front that are outside the current window
   - Add the current element's index to the deque
   - If the window is complete (i >= k-1), add the front element of the deque to the result

### Why it works:
- The deque maintains indices in decreasing order of their corresponding values
- Elements smaller than the current element can never be the maximum, so we remove them
- The front of the deque always contains the maximum element of the current window

## Code Solution

```java
import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        
        // Process first k elements
        for (int i = 0; i < k; i++) {
            // Remove elements smaller than current from back
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        
        // Process rest of the elements
        for (int i = k; i < n; i++) {
            // Add max of previous window to result
            result[i - k] = nums[deque.peekFirst()];
            
            // Remove elements outside current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            
            // Remove elements smaller than current from back
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            
            // Add current element to deque
            deque.addLast(i);
        }
        
        // Add max of last window to result
        result[n - k] = nums[deque.peekFirst()];
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the number of elements in the array. Each element is processed exactly once.
- **Space Complexity**: O(k) for the deque, which at most contains k elements.

## Key Insights

1. Using a data structure like a deque allows us to efficiently keep track of potential maximums.
2. By maintaining the deque in decreasing order, we ensure the maximum is always at the front.
3. The sliding window pattern is perfect for this type of problem where we need to find maximums (or minimums) within consecutive subarrays of fixed size.
