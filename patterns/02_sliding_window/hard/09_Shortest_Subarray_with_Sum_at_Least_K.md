# Shortest Subarray with Sum at Least K

## Problem Statement

Given an array of integers `nums` and an integer `k`, return the length of the shortest non-empty subarray of `nums` with a sum of at least `k`. If there is no such subarray, return `-1`.

## Examples

**Example 1:**
```
Input: nums = [1], k = 1
Output: 1
```

**Example 2:**
```
Input: nums = [1,2], k = 4
Output: -1
```

**Example 3:**
```
Input: nums = [2,-1,2], k = 3
Output: 3
```

## Approach: Sliding Window with Monotonic Queue

This problem is challenging because the array can contain negative numbers. The standard sliding window approach doesn't work directly because negative elements could make a longer subarray have a smaller sum.

Instead, we'll use a monotonic deque combined with prefix sums to efficiently find the shortest subarray with sum at least k.

### Algorithm:

1. Compute the prefix sum array
2. Use a monotonic deque to keep track of potential starting points
3. For each ending point, find the best starting point that gives a sum of at least k
4. Maintain the deque in a way that preserves monotonicity

## Code Solution

```java
import java.util.*;

public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int shortestLength = n + 1; // Initialize to impossible value
        
        // Calculate prefix sums
        // prefixSum[i] = sum of elements from 0 to i-1
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        // Use a deque to store indices of possible starting points
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int right = 0; right <= n; right++) {
            // Remove indices from the deque where the current prefix sum is smaller
            // This means we've found a better starting point
            while (!deque.isEmpty() && prefixSum[right] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }
            
            // Check if we can find a valid subarray ending at 'right'
            while (!deque.isEmpty() && prefixSum[right] - prefixSum[deque.peekFirst()] >= k) {
                shortestLength = Math.min(shortestLength, right - deque.pollFirst());
            }
            
            // Add the current index to the deque
            deque.addLast(right);
        }
        
        return shortestLength <= n ? shortestLength : -1;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array. Each element is added to and removed from the deque at most once.
- **Space Complexity**: O(n) for the prefix sum array and the deque.

## Alternative Approach: Using a TreeMap

We can also solve this problem using a TreeMap to efficiently search for prefix sums:

```java
import java.util.*;

public class ShortestSubarrayWithSumAtLeastKTreeMap {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int shortestLength = n + 1;
        
        // Calculate prefix sums and use TreeMap to store (prefixSum, index)
        TreeMap<Long, Integer> prefixMap = new TreeMap<>();
        prefixMap.put(0L, 0);
        
        long currentSum = 0;
        
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            
            // Find the largest prefix sum such that currentSum - prefix >= k
            Long prefix = prefixMap.floorKey(currentSum - k);
            
            if (prefix != null) {
                shortestLength = Math.min(shortestLength, i + 1 - prefixMap.get(prefix));
            }
            
            // Remove prefixes that are greater than or equal to currentSum
            // These won't be useful for future indices
            while (!prefixMap.isEmpty() && prefixMap.lastKey() >= currentSum) {
                prefixMap.pollLastEntry();
            }
            
            // Add current prefix sum
            prefixMap.put(currentSum, i + 1);
        }
        
        return shortestLength <= n ? shortestLength : -1;
    }
}
```

## Key Insights

1. This problem illustrates a key challenge with sliding windows: negative numbers can break the monotonicity assumption.
2. Using prefix sums allows us to efficiently calculate the sum of any subarray.
3. The monotonic deque approach helps maintain potential starting points in a way that optimally handles the search for the shortest valid subarray.
4. When a standard sliding window approach doesn't work, combining it with other data structures (like deques or TreeMaps) can provide an efficient solution.
5. This problem demonstrates that sliding window patterns often need to be adapted and combined with other techniques to handle complex constraints.
6. The time complexity is still linear despite the problem's complexity, showing the power of appropriate data structure selection.
