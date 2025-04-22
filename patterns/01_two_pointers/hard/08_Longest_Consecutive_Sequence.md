# Longest Consecutive Sequence (LeetCode #128)

## Problem Statement
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

## Example 1:
```
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

## Example 2:
```
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

## Constraints:
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9

## Two Pointers Approach (with HashSet)
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Add all numbers to a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        // Check each number that could be the start of a sequence
        for (int num : numSet) {
            // If this number is the start of a sequence
            // (i.e., num-1 is not in the set)
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // Count how long the sequence extends
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                // Update the longest streak
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }
}
```

## Time Complexity
O(n) where n is the length of the array. Although there are nested loops, the inner while loop can run at most n times in total because it explores each streak exactly once.

## Space Complexity
O(n) for storing all the numbers in the HashSet.