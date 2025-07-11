# Minimum Recolors to Get K Consecutive Black Blocks

## Problem Statement

You are given a 0-indexed string `blocks` of length n, where `blocks[i]` is either 'W' or 'B', representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer `k`, which represents the length of a block sequence you need to create.

You want to create a block sequence of length `k` where all blocks have the same color. This can be done by recoloring some of the blocks so that they all have the same color.

Return the minimum number of blocks that need to be recolored to create a block sequence of length `k` where all blocks have the same color (either black or white).

## Examples

**Example 1:**
```
Input: blocks = "WBBWWBBWBW", k = 7
Output: 3
Explanation: One way to get 7 consecutive black blocks is to recolor the 0th, 4th, and 6th blocks:
"BBBWBBBWBW"
It can be shown that there is no way to get 7 consecutive black blocks in less than 3 operations.
Note that there are other ways to get 7 consecutive black blocks in 3 operations.
```

**Example 2:**
```
Input: blocks = "WBWBBBW", k = 2
Output: 0
Explanation: There are already 2 consecutive black blocks at indices 2 and 3, so no recoloring is needed.
```

## Approach: Fixed-Size Sliding Window

This problem can be efficiently solved using a fixed-size sliding window approach. We need to find a window of size `k` that requires the minimum number of recolors to make all blocks black.

### Algorithm:

1. Initialize a window of size `k`
2. Count the number of white blocks ('W') in the initial window
3. Slide the window through the string, updating the count of white blocks
4. Track the minimum number of white blocks seen in any window
5. Return the minimum count as the answer (since these are the blocks that need to be recolored)

## Code Solution

```java
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {
    public int minimumRecolors(String blocks, int k) {
        // Count white blocks in the initial window
        int whiteCount = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
        }
        
        // Initialize minimum white count
        int minWhiteCount = whiteCount;
        
        // Slide the window
        for (int i = k; i < blocks.length(); i++) {
            // Remove the block that's going out of the window
            if (blocks.charAt(i - k) == 'W') {
                whiteCount--;
            }
            
            // Add the block that's coming into the window
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
            
            // Update minimum white count
            minWhiteCount = Math.min(minWhiteCount, whiteCount);
        }
        
        return minWhiteCount;
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the string. We process each character at most twice.
- **Space Complexity**: O(1) as we only use a constant amount of extra space regardless of input size.

## Key Insights

1. This problem asks for the minimum number of operations to achieve a specific condition within a fixed-size window.
2. Instead of trying all possible positions for the window and recounting from scratch each time, we use a sliding window approach to efficiently update our count.
3. We focus on counting white blocks rather than black blocks, as the white blocks need to be recolored to black to achieve the desired sequence.
4. The problem illustrates how the sliding window pattern can be applied to problems involving character sequences and minimization.
5. Note that the problem only asks for the minimum number of recolors, not the actual positions to recolor, which makes it a perfect fit for the sliding window approach.
