# Minimum Number of K Consecutive Bit Flips

## Problem Statement

You are given a binary array `nums` and an integer `k`.

A **k-bit flip** is choosing a subarray of length `k` from `nums` and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

## Examples

**Example 1:**
```
Input: nums = [0,1,0], k = 1
Output: 2
Explanation: Flip nums[0], then flip nums[2].
```

**Example 2:**
```
Input: nums = [1,1,0], k = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
```

**Example 3:**
```
Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation: 
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
```

## Approach 1: Greedy Algorithm with Array Simulation

The key insight is to process the array from left to right. For each position `i`, if `nums[i]` is 0, we must perform a flip starting at position `i`. We'll simulate the flips to track the state of the array.

### Algorithm:

1. Iterate through the array from left to right
2. For each position, if the current bit is 0 after accounting for previous flips, perform a new flip
3. Keep track of the total number of flips performed
4. If we reach a position where a flip is needed but can't be performed (not enough elements left), return -1

## Code Solution 1: Simulating Flips

```java
public class MinimumNumberOfKConsecutiveBitFlips {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int flips = 0;
        boolean[] flipped = new boolean[n]; // Tracks if a position is flipped
        int currentFlips = 0; // Number of ongoing flips affecting current position
        
        for (int i = 0; i < n; i++) {
            // If we have an active flip ending at position i-k, decrement currentFlips
            if (i >= k && flipped[i - k]) {
                currentFlips--;
            }
            
            // Determine current bit value after previous flips
            // If currentFlips is odd, the bit is flipped once
            int bit = nums[i];
            if (currentFlips % 2 == 1) {
                bit = 1 - bit; // Flip the bit
            }
            
            // If the bit is 0 after all flips, we need another flip
            if (bit == 0) {
                // Can't flip if we don't have k elements left
                if (i + k > n) {
                    return -1;
                }
                
                flipped[i] = true;
                currentFlips++;
                flips++;
            }
        }
        
        return flips;
    }
}
```

## Approach 2: Sliding Window with Flip Counter

We can optimize our solution using a sliding window approach with a flip counter.

### Algorithm:

1. Use a queue to keep track of the positions where we perform flips
2. Track the number of flips affecting the current position
3. If the current bit after accounting for flips is 0, perform a new flip
4. Move the window and update the flip count

## Code Solution 2: Sliding Window

```java
import java.util.*;

public class MinimumNumberOfKConsecutiveBitFlipsOptimized {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> flipQueue = new LinkedList<>(); // Stores indices where flips start
        int flips = 0;
        
        for (int i = 0; i < n; i++) {
            // Remove expired flips (those that no longer affect current position)
            if (!flipQueue.isEmpty() && flipQueue.peek() + k <= i) {
                flipQueue.poll();
            }
            
            // Current bit after accounting for previous flips
            // If flipQueue.size() is odd, the bit is flipped
            int bit = nums[i];
            if (flipQueue.size() % 2 == 1) {
                bit = 1 - bit; // Flip the bit
            }
            
            // If the bit is 0 after all flips, perform another flip
            if (bit == 0) {
                // Can't flip if we don't have k elements left
                if (i + k > n) {
                    return -1;
                }
                
                flipQueue.offer(i); // Add current index to flip queue
                flips++;
            }
        }
        
        return flips;
    }
}
```

## Approach 3: Constant Space Solution

We can further optimize by using the original array to track flip information.

### Algorithm:

1. Use the original array to store flip information by temporarily modifying values beyond their original 0/1 range
2. Track the number of active flips affecting each position
3. Restore the array at the end if needed

## Code Solution 3: Constant Space

```java
public class MinimumNumberOfKConsecutiveBitFlipsConstantSpace {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int flips = 0;
        int currentFlips = 0;
        
        for (int i = 0; i < n; i++) {
            // If we have a flip ending at position i, decrement currentFlips
            if (i >= k && nums[i - k] > 1) {
                currentFlips--;
                nums[i - k] -= 2; // Restore original value if needed
            }
            
            // If the current bit needs to be flipped
            if ((nums[i] + currentFlips) % 2 == 0) {
                // Can't flip if we don't have k elements left
                if (i + k > n) {
                    return -1;
                }
                
                nums[i] += 2; // Mark this position as flipped
                currentFlips++;
                flips++;
            }
        }
        
        return flips;
    }
}
```

## Complexity Analysis

- **Approach 1 (Array Simulation)**:
  - **Time Complexity**: O(n) where n is the length of the array.
  - **Space Complexity**: O(n) for the flipped array.

- **Approach 2 (Sliding Window)**:
  - **Time Complexity**: O(n)
  - **Space Complexity**: O(k) for the queue that stores at most k flip positions.

- **Approach 3 (Constant Space)**:
  - **Time Complexity**: O(n)
  - **Space Complexity**: O(1) as we use the original array to track flips.

## Key Insights

1. This problem combines the sliding window technique with a greedy approach. We process the array from left to right, making flips whenever necessary.

2. The key insight is that for each position, we need to know the current state of the bit after all previous flips. This requires keeping track of the number of active flips affecting each position.

3. We can optimize space usage by cleverly reusing the input array to store flip information or by using a queue to track only the positions where flips start.

4. The problem demonstrates how the sliding window pattern can be applied to problems involving operations on contiguous subarrays of fixed size.

5. The greedy approach works because if we see a 0 at any position after accounting for previous flips, we must flip the subarray starting at that position to make it 1.
