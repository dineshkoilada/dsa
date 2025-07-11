# K Empty Slots

## Problem Statement

You have `n` bulbs in a row numbered from `1` to `n`. Initially, all the bulbs are turned off. Each day, you turn on exactly one bulb. On the `i`th day, you turn on the bulb at position `positions[i-1]`.

Given an array `positions` of length `n`, return the number of the day when all bulbs between any two turned-on bulbs are also turned on. If there isn't such a day, return `-1`.

## Examples

**Example 1:**
```
Input: positions = [1,3,2], k = 1
Output: 2
Explanation: 
On the first day: bulbs[1] = 1, first bulb is turned on.
On the second day: bulbs[3] = 1, third bulb is turned on.
On the third day: bulbs[2] = 1, second bulb is turned on.
We return 2 because on the second day, there were two on bulbs with one off bulb between them.
```

**Example 2:**
```
Input: positions = [1,2,3], k = 1
Output: -1
Explanation: There is no day when there are two on bulbs with one off bulb between them.
```

## Approach: Sliding Window + Bucket Sorting

This problem requires a clever approach that combines the sliding window concept with bucket sorting.

### Algorithm:

1. Instead of tracking when bulbs are turned on in order of days, we'll track the day on which each position gets a bulb turned on.
2. For each consecutive segment of k+2 positions, we check if the leftmost and rightmost bulbs in this segment are turned on before all the bulbs in between.
3. Use a sliding window of size k+2 to find valid segments.

## Code Solution

```java
public class KEmptySlots {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] days = new int[n + 1]; // days[i] = the day when position i has a flower blooming
        
        for (int i = 0; i < n; i++) {
            days[flowers[i]] = i + 1; // +1 because days are 1-indexed
        }
        
        int result = Integer.MAX_VALUE;
        int left = 1, right = k + 2; // We need k empty slots between left and right
        
        // Sliding window approach
        while (right <= n) {
            boolean valid = true;
            
            // Check if all positions between left and right bloom later than both left and right
            for (int i = left + 1; i < right; i++) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    valid = false;
                    break;
                }
            }
            
            // If valid, update result with the later of the two days
            if (valid) {
                result = Math.min(result, Math.max(days[left], days[right]));
            }
            
            // Move window forward
            left++;
            right++;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
```

## Optimized Approach: Using a Sliding Window Directly

We can optimize the solution by avoiding the inner loop and using a true sliding window approach:

```java
public class KEmptySlotsOptimized {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] days = new int[n + 1]; // days[i] = the day when position i has a flower blooming
        
        for (int i = 0; i < n; i++) {
            days[flowers[i]] = i + 1; // +1 because days are 1-indexed
        }
        
        int result = Integer.MAX_VALUE;
        
        // Sliding window with two boundaries
        for (int left = 1, right; left + k + 1 <= n; left++) {
            right = left + k + 1;
            
            boolean valid = true;
            // Check if min day in window is > both boundary days
            for (int i = left + 1; i < right; i++) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    // If any day in between is earlier, skip to that position
                    left = i - 1; // -1 because left will be incremented in the for loop
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                // Record the later of the two days when the left and right flowers bloom
                result = Math.min(result, Math.max(days[left], days[right]));
            }
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) in the worst case, where n is the number of bulbs.
- **Space Complexity**: O(n) for the days array.

## Key Insights

1. This problem can be approached using the sliding window pattern combined with a bucket sorting technique.
2. The key insight is to convert the problem from "when is a bulb turned on" to "which day is a position's bulb turned on".
3. By using a sliding window of size k+2, we can check if there are exactly k empty slots between two turned-on bulbs.
4. The optimized solution avoids unnecessary comparisons by jumping ahead when we find a violation of our criteria.
5. This problem demonstrates how the sliding window pattern can be adapted to solve problems that don't initially seem to fit the pattern.
