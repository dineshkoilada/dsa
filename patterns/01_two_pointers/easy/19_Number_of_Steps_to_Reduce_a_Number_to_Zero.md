# Number of Steps to Reduce a Number to Zero (LeetCode #1342)

## Problem Statement
Given an integer `num`, return the number of steps to reduce it to zero.

In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.

## Example 1:
```
Input: num = 14
Output: 6
Explanation: 
Step 1) 14 is even; divide by 2 and obtain 7. 
Step 2) 7 is odd; subtract 1 and obtain 6.
Step 3) 6 is even; divide by 2 and obtain 3. 
Step 4) 3 is odd; subtract 1 and obtain 2. 
Step 5) 2 is even; divide by 2 and obtain 1. 
Step 6) 1 is odd; subtract 1 and obtain 0.
```

## Example 2:
```
Input: num = 8
Output: 4
Explanation: 
Step 1) 8 is even; divide by 2 and obtain 4. 
Step 2) 4 is even; divide by 2 and obtain 2. 
Step 3) 2 is even; divide by 2 and obtain 1. 
Step 4) 1 is odd; subtract 1 and obtain 0.
```

## Example 3:
```
Input: num = 123
Output: 12
```

## Constraints:
- 0 <= num <= 10^6

## Two Pointers Approach (Binary Representation)
```java
class Solution {
    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        
        // Convert to binary representation
        String binary = Integer.toBinaryString(num);
        
        int steps = 0;
        
        // For each bit, we need one step to divide by 2 (right shift)
        // For each '1' bit, we need an additional step to subtract 1
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                steps += 2; // Subtract 1, then divide by 2
            } else {
                steps += 1; // Just divide by 2
            }
        }
        
        // The last '1' bit only needs one step (subtract 1)
        return steps - 1;
    }
}
```

## Time Complexity
O(log n) as the binary representation of a number has log(n) bits.

## Space Complexity
O(log n) for storing the binary representation.