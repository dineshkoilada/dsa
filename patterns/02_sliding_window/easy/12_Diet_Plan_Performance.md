# Diet Plan Performance

## Problem Description

A dieter consumes calories[i] calories on the i-th day. 

Given an integer array `calories`, which represents the calories consumed per day, and two integers `k` and `lower` and `upper`, return the number of points the dieter gains or loses.

Points are calculated as follows:
- For every consecutive sequence of `k` days, the dieter gains 1 point if the total calories consumed during that period is less than `lower`.
- The dieter loses 1 point if the total calories consumed during that period is greater than `upper`.
- Otherwise, the dieter gains no points.

Return the total points the dieter has after calculating all sequences.

### Example 1:
```
Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
Output: 0
Explanation: Since k = 1, we consider each element of the array separately and compare it to lower and upper.
calories[0] < lower so 1 point is gained.
calories[1] < lower so 1 point is gained.
calories[2] = lower so no points are gained or lost.
calories[3] > upper so 1 point is lost.
calories[4] > upper so 1 point is lost.
Total points: 1 + 1 - 1 - 1 = 0
```

### Example 2:
```
Input: calories = [3,2], k = 2, lower = 0, upper = 1
Output: 1
Explanation: Since k = 2, we consider subarrays of length 2.
calories[0] + calories[1] > upper so 1 point is lost.
Total points: -1
```

### Example 3:
```
Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
Output: 0
Explanation: Since k = 2, we consider subarrays of length 2.
calories[0] + calories[1] > upper so 1 point is lost.
calories[1] + calories[2] = lower so no points are gained or lost.
calories[2] + calories[3] < lower so 1 point is gained.
Total points: -1 + 0 + 1 = 0
```

### Constraints:
- 1 <= k <= calories.length <= 10^5
- 0 <= calories[i] <= 20000
- 0 <= lower <= upper <= 20000

## Approach: Sliding Window

This problem is a perfect fit for the sliding window pattern since we need to calculate the sum of consecutive subarrays of fixed length `k`.

1. Initialize a window of size `k` and calculate its sum.
2. Check if the sum falls below `lower` (gain a point) or exceeds `upper` (lose a point).
3. Slide the window by removing the element at the start and adding the new element at the end.
4. Repeat steps 2-3 until we've processed the entire array.

### Time Complexity: O(n)
- We iterate through the array once, where n is the length of the calories array.

### Space Complexity: O(1)
- We only use a few variables regardless of input size.

## Solution

```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length;
        int points = 0;
        int windowSum = 0;
        
        // Calculate sum of first k days
        for (int i = 0; i < k; i++) {
            windowSum += calories[i];
        }
        
        // Check points for first window
        if (windowSum < lower) points--;
        else if (windowSum > upper) points++;
        
        // Slide the window and calculate points
        for (int i = k; i < n; i++) {
            // Remove the element leaving the window
            windowSum -= calories[i - k];
            // Add the element entering the window
            windowSum += calories[i];
            
            // Check points for current window
            if (windowSum < lower) points--;
            else if (windowSum > upper) points++;
        }
        
        return points;
    }
}
```

```python
class Solution:
    def dietPlanPerformance(self, calories: List[int], k: int, lower: int, upper: int) -> int:
        n = len(calories)
        points = 0
        window_sum = sum(calories[:k])
        
        # Check points for first window
        if window_sum < lower:
            points -= 1
        elif window_sum > upper:
            points += 1
        
        # Slide the window and calculate points
        for i in range(k, n):
            # Remove the element leaving the window
            window_sum -= calories[i - k]
            # Add the element entering the window
            window_sum += calories[i]
            
            # Check points for current window
            if window_sum < lower:
                points -= 1
            elif window_sum > upper:
                points += 1
        
        return points
```

## Key Insights

1. **Fixed-Size Window**: This problem involves a sliding window of fixed size `k`, making it relatively straightforward to implement.

2. **Efficient Sum Calculation**: Instead of recalculating the sum for each window from scratch, we use the sliding window technique to update the sum incrementally by adding the new element and removing the oldest element.

3. **Boundary Condition Handling**: Pay attention to the comparison with `lower` and `upper`. The problem states that points are gained/lost only when the sum is strictly less than `lower` or strictly greater than `upper`.

4. **Point System**: This problem has an interesting scoring system where we gain or lose points based on specific conditions, rather than just finding a maximum or minimum value.
