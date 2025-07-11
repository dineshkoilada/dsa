# Defuse the Bomb

## Problem Statement

You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array `code` of length of n and a key `k`.

To defuse the bomb, you need to replace every number. You have the following replacement options:
- If `k > 0`, replace the `i`th number with the sum of the next `k` numbers.
- If `k < 0`, replace the `i`th number with the sum of the previous `|k|` numbers.
- If `k == 0`, replace every number with `0`.

The indices are circular, so the next element after `code[n-1]` is `code[0]`, and the previous element before `code[0]` is `code[n-1]`.

Given the circular array `code` and an integer key `k`, return the decrypted code to defuse the bomb!

## Examples

**Example 1:**
```
Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation:
For code[0]=5, we take the values at indices 1, 2, 3, which are [7,1,4]. Their sum is 12, so code[0]=12.
For code[1]=7, we take the values at indices 2, 3, 0, which are [1,4,5]. Their sum is 10, so code[1]=10.
For code[2]=1, we take the values at indices 3, 0, 1, which are [4,5,7]. Their sum is 16, so code[2]=16.
For code[3]=4, we take the values at indices 0, 1, 2, which are [5,7,1]. Their sum is 13, so code[3]=13.
```

**Example 2:**
```
Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: k = 0 means that we replace each element with 0.
```

**Example 3:**
```
Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation:
For code[0]=2, we take the values at indices n-2 and n-1, which are [9,3]. Their sum is 12, so code[0]=12.
For code[1]=4, we take the values at indices n-1 and 0, which are [3,2]. Their sum is 5, so code[1]=5.
For code[2]=9, we take the values at indices 0 and 1, which are [2,4]. Their sum is 6, so code[2]=6.
For code[3]=3, we take the values at indices 1 and 2, which are [4,9]. Their sum is 13, so code[3]=13.
```

## Approach: Sliding Window on Circular Array

This problem involves a sliding window over a circular array. We need to compute the sum of `k` consecutive elements for each position.

### Algorithm:

1. Handle the special case of `k == 0` separately
2. Create a new result array to store the decrypted code
3. For each index `i` in the array:
   - Calculate the sum of the next `k` or previous `|k|` elements (depending on the sign of `k`)
   - Use modular arithmetic to handle the circular nature of the array
   - Store the sum in the result array

## Code Solution

```java
public class DefuseTheBomb {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        
        // Special case: k = 0
        if (k == 0) {
            return result; // Already filled with zeros
        }
        
        // Process each position
        for (int i = 0; i < n; i++) {
            int sum = 0;
            
            if (k > 0) {
                // Sum of next k elements
                for (int j = 1; j <= k; j++) {
                    sum += code[(i + j) % n];
                }
            } else {
                // Sum of previous |k| elements
                for (int j = 1; j <= -k; j++) {
                    // Use modulo to handle negative indices
                    sum += code[(i - j + n) % n];
                }
            }
            
            result[i] = sum;
        }
        
        return result;
    }
}
```

## Optimized Solution

We can optimize the solution by using a sliding window approach to avoid redundant calculations:

```java
public class DefuseTheBombOptimized {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        
        // Special case: k = 0
        if (k == 0) {
            return result; // Already filled with zeros
        }
        
        // Initialize the first window sum
        int sum = 0;
        int absK = Math.abs(k);
        
        // Prepare initial sum
        for (int i = 0; i < absK; i++) {
            if (k > 0) {
                sum += code[(i + 1) % n];
            } else {
                sum += code[(n - i - 1) % n];
            }
        }
        
        // Slide the window for each position
        for (int i = 0; i < n; i++) {
            result[i] = sum;
            
            if (k > 0) {
                // Remove the element going out of window, add the new element
                sum -= code[(i + 1) % n];
                sum += code[(i + k + 1) % n];
            } else {
                // For negative k, move the window in reverse
                sum -= code[(n + i - absK) % n];
                sum += code[i % n];
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Time Complexity**: 
  - Basic solution: O(n * |k|) where n is the length of the array and |k| is the absolute value of k
  - Optimized solution: O(n + |k|) as we compute the initial sum in O(|k|) and then slide the window in O(n)
- **Space Complexity**: O(n) for the result array

## Key Insights

1. This problem demonstrates the sliding window technique applied to a circular array.
2. Using modular arithmetic (`% n`) is essential for handling the circular nature of the array.
3. The optimized solution reduces the time complexity by avoiding redundant calculations.
4. The problem involves three distinct cases (k > 0, k < 0, k = 0) that need to be handled separately.
5. When working with circular arrays, it's important to properly handle boundary conditions to avoid index out of bounds errors.
