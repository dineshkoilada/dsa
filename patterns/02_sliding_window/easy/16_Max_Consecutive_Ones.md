# Max Consecutive Ones

## Problem Description

Given a binary array `nums`, return the maximum number of consecutive 1's in the array.

### Example 1:
```
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
```

### Example 2:
```
Input: nums = [1,0,1,1,0,1]
Output: 2
```

### Constraints:
- 1 <= nums.length <= 10^5
- nums[i] is either 0 or 1.

## Approach: Sliding Window

This problem can be solved with a sliding window approach, though it's a simplified version since we only need to track consecutive 1's.

1. Maintain a counter for the current consecutive 1's.
2. Iterate through the array. When we see a 1, increment the counter.
3. When we see a 0, reset the counter to 0.
4. Keep track of the maximum counter value seen so far.

### Time Complexity: O(n)
- We iterate through the array once, where n is the length of the array.

### Space Complexity: O(1)
- We only use a constant amount of extra space.

## Solution

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutive = 0;
        int currentConsecutive = 0;
        
        for (int num : nums) {
            if (num == 1) {
                // Increment current streak of 1's
                currentConsecutive++;
                // Update max if current streak is longer
                maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
            } else {
                // Reset streak when we see a 0
                currentConsecutive = 0;
            }
        }
        
        return maxConsecutive;
    }
}
```

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        max_consecutive = 0
        current_consecutive = 0
        
        for num in nums:
            if num == 1:
                # Increment current streak of 1's
                current_consecutive += 1
                # Update max if current streak is longer
                max_consecutive = max(max_consecutive, current_consecutive)
            else:
                # Reset streak when we see a 0
                current_consecutive = 0
        
        return max_consecutive
```

## Key Insights

1. **Counter Reset**: The key insight is to reset the counter whenever we encounter a 0, as it breaks the consecutive 1's sequence.

2. **Maximum Tracking**: We continuously update our maximum count whenever our current count increases.

3. **Single Pass**: This solution only requires a single pass through the array, making it efficient.

4. **No Additional Space**: Unlike some sliding window problems that require a data structure to track elements, this problem only needs two integer counters.

5. **Edge Cases**: The algorithm handles edge cases well:
   - If there are no 1's in the array, we return 0
   - If all elements are 1's, we return the length of the array
   - If there are multiple sequences of 1's, we return the length of the longest sequence
