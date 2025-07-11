# Grumpy Bookstore Owner

## Problem Description

There is a bookstore owner that has a store open for `n` minutes. Every minute, some number of customers enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy. If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for `minutes` consecutive minutes, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

### Example 1:
```
Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 0 + 1 + 1 + 1 + 7 + 5 = 16.
```

### Example 2:
```
Input: customers = [1], grumpy = [0], minutes = 1
Output: 1
```

### Constraints:
- n == customers.length == grumpy.length
- 1 <= n <= 2 * 10^4
- 0 <= customers[i] <= 1000
- 0 <= grumpy[i] <= 1
- 1 <= minutes <= n

## Approach: Sliding Window

This problem is an excellent candidate for the sliding window pattern:

1. Calculate the base satisfaction (customers when the owner is not grumpy).
2. Use a sliding window of size `minutes` to find the maximum additional satisfaction we can achieve by using the secret technique.
3. The final answer is the base satisfaction plus the maximum additional satisfaction.

### Time Complexity: O(n)
- We iterate through the array once.

### Space Complexity: O(1)
- We use only constant extra space.

## Solution

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        
        // Calculate base satisfaction (when owner is not grumpy naturally)
        int baseSatisfaction = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                baseSatisfaction += customers[i];
            }
        }
        
        // Calculate additional satisfaction using the secret technique
        int currentAdditional = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                currentAdditional += customers[i];
            }
        }
        
        int maxAdditional = currentAdditional;
        
        // Slide the window and find the maximum additional satisfaction
        for (int i = minutes; i < n; i++) {
            // Remove the customer leaving the window
            if (grumpy[i - minutes] == 1) {
                currentAdditional -= customers[i - minutes];
            }
            
            // Add the customer entering the window
            if (grumpy[i] == 1) {
                currentAdditional += customers[i];
            }
            
            maxAdditional = Math.max(maxAdditional, currentAdditional);
        }
        
        return baseSatisfaction + maxAdditional;
    }
}
```

```python
class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], minutes: int) -> int:
        n = len(customers)
        
        # Calculate base satisfaction (when owner is not grumpy naturally)
        base_satisfaction = 0
        for i in range(n):
            if grumpy[i] == 0:
                base_satisfaction += customers[i]
        
        # Calculate additional satisfaction using the secret technique
        current_additional = 0
        for i in range(minutes):
            if grumpy[i] == 1:
                current_additional += customers[i]
        
        max_additional = current_additional
        
        # Slide the window and find the maximum additional satisfaction
        for i in range(minutes, n):
            # Remove the customer leaving the window
            if grumpy[i - minutes] == 1:
                current_additional -= customers[i - minutes]
            
            # Add the customer entering the window
            if grumpy[i] == 1:
                current_additional += customers[i]
            
            max_additional = max(max_additional, current_additional)
        
        return base_satisfaction + max_additional
```

## Key Insights

1. **Decomposition**: Breaking the problem into "already satisfied" and "potentially satisfied with technique" makes it clearer.

2. **Sliding Window**: We use the sliding window technique to find the optimal consecutive minutes to apply the secret technique.

3. **Optimization**: We only consider the grumpy minutes when calculating additional satisfaction since non-grumpy minutes already contribute to the base satisfaction.

4. **Single Pass**: The entire algorithm runs in a single pass through the array, making it efficient for large inputs.
