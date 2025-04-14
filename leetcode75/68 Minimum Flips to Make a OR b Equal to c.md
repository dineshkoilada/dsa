# Minimum Flips to Make a OR b Equal to c

## 📌 Problem Statement

**LeetCode Problem:** [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/)  
**Difficulty:** Medium  

**Description:**
Given 3 positives numbers `a`, `b` and `c`. Return the minimum flips required in some bits of `a` and `b` to make (`a` OR `b` == `c`). (bitwise OR operation).
Flip operation consists of changing any single bit 1 to 0 or changing the bit 0 to 1 in their binary representation.

### **Example 1:**
**Input:** 
```
a = 2, b = 6, c = 5
```
**Output:** 
```
3
```
**Explanation:** 
- Binary representation of numbers:
  - a = 010
  - b = 110
  - c = 101
  - a OR b = 110
- To make a OR b equal to c, we need to flip:
  - Flip a[2] to make a = 110, then a OR b = 110 == c
  - Flip b[1] to make b = 100, then a OR b = 110 == c
  - Flip b[0] to make b = 111, then a OR b = 111 == c
- In total, we need to flip 3 bits.

### **Example 2:**
**Input:** 
```
a = 4, b = 2, c = 7
```
**Output:** 
```
1
```
**Explanation:** 
- Binary representation of numbers:
  - a = 100
  - b = 010
  - c = 111
  - a OR b = 110
- We need to flip one bit in a or b (a[0] or b[0]) to get c = 111.

### **Example 3:**
**Input:** 
```
a = 1, b = 2, c = 3
```
**Output:** 
```
0
```
**Explanation:** 
- a OR b is already equal to c, so we don't need any flips.

### **Constraints:**
- `1 <= a, b, c <= 10^9`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have two binary numbers (which only consist of 0s and 1s) called `a` and `b`. When you perform an OR operation, you get a new number where a position is 1 if either `a` OR `b` (or both) have a 1 in that position.

For example:
- If a = 010 and b = 110, then a OR b = 110 (because at each position, if either has a 1, the result has a 1)

The problem says: You have three numbers `a`, `b`, and `c`. You want to make (a OR b) equal to `c` by flipping some bits in `a` and `b` (changing 0s to 1s or 1s to 0s). How many bits do you need to flip at minimum?

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What makes a bit in the result of (a OR b) equal to 0?**
   - Both a and b must have 0 at that position.
2. **What makes a bit in the result of (a OR b) equal to 1?**
   - Either a or b (or both) must have 1 at that position.
3. **When do we need to flip bits?**
   - When the current result of (a OR b) doesn't match c at a particular position.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Understand the Bit Patterns and When Flips are Needed

Let's analyze when we need to flip bits:

1. If c[i] = 0 (we want the result to be 0):
   - If (a[i] OR b[i]) = 1, we need to flip all 1's in a and b to 0's
   - Number of flips = number of 1's in a and b at position i

2. If c[i] = 1 (we want the result to be 1):
   - If (a[i] OR b[i]) = 0, we need to flip one 0 to a 1 (either in a or b)
   - Number of flips = 1 if both a and b have 0 at position i, otherwise 0

### Step 2: Count the Required Flips

Let's implement a bit-by-bit comparison:

```java
int flips = 0;
// Iterate through all 32 bits (int is 32 bits in Java)
for (int i = 0; i < 32; i++) {
    // Get the i-th bit of each number
    int bitA = (a >> i) & 1;
    int bitB = (b >> i) & 1;
    int bitC = (c >> i) & 1;
    
    // Case 1: c[i] = 0, need both a[i] and b[i] to be 0
    if (bitC == 0) {
        flips += bitA + bitB; // Count how many 1's need to be flipped to 0's
    }
    // Case 2: c[i] = 1, need at least one of a[i] or b[i] to be 1
    else {
        if (bitA == 0 && bitB == 0) {
            flips += 1; // Need to flip one 0 to 1
        }
    }
}
return flips;
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
public class MinimumFlipsToMakeAORBEqualToC {
    
    /**
     * Calculates the minimum number of bit flips required to make (a OR b) equal to c
     * 
     * @param a First integer
     * @param b Second integer
     * @param c Target integer
     * @return Minimum number of bit flips required
     */
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        // Check each bit position (32 bits in an integer)
        for (int i = 0; i < 32; i++) {
            // Extract the i-th bit of each number
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            int bitC = (c >> i) & 1;
            
            // Case 1: The target bit is 0
            if (bitC == 0) {
                // To get (a OR b) = 0 at this position, both bits must be 0
                // Count how many bits need to be flipped from 1 to 0
                flips += (bitA + bitB);
            } 
            // Case 2: The target bit is 1
            else {
                // To get (a OR b) = 1 at this position, at least one bit must be 1
                // If both are 0, we need one flip
                if (bitA == 0 && bitB == 0) {
                    flips += 1;
                }
                // Otherwise, we already have what we need (a OR b = 1)
            }
        }
        
        return flips;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(1)` because we're iterating through a fixed number of bits (32 for integers).
- **Space Complexity:** `O(1)` as we only use a few variables regardless of input size.

---

## 🔑 Alternative Solution Using Bitwise Operations

We can make the solution even more concise by directly using bitwise operations:

```java
public int minFlips(int a, int b, int c) {
    int flips = 0;
    
    // XOR identifies positions where the OR doesn't match c
    int xor = (a | b) ^ c;
    
    for (int i = 0; i < 32; i++) {
        int bit = (xor >> i) & 1;
        
        // If the bit is 1, we need to flip something
        if (bit == 1) {
            // If target bit is 1, we need one flip
            if (((c >> i) & 1) == 1) {
                flips += 1;
            }
            // If target bit is 0, we need to flip all 1's in a and b
            else {
                flips += ((a >> i) & 1) + ((b >> i) & 1);
            }
        }
    }
    
    return flips;
}
```

We can simplify this even further:

```java
public int minFlips(int a, int b, int c) {
    // Bits that need to be changed to match c
    int diff = (a | b) ^ c;
    
    // Count cases where both a and b have 1 but c has 0
    // This requires 2 flips (both a and b need to be flipped)
    int bothOnes = a & b & diff;
    
    // Count total bits to be flipped
    return Integer.bitCount(diff) + Integer.bitCount(bothOnes);
}
```

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"This problem is about bit manipulation and logical operations. I approached it by examining the requirements for each bit position:

1. For each bit position i:
   - If c[i] = 0: Both a[i] and b[i] must be 0. So I need to count how many of them are 1 and flip those.
   - If c[i] = 1: At least one of a[i] or b[i] must be 1. If both are 0, I need one flip.

2. I implemented this by:
   - Iterating through each bit position
   - Extracting the corresponding bit from each number using bit shifts and masks
   - Counting the required flips based on the logic above

3. The time complexity is O(1) since we're processing a fixed number of bits (32 for integers), and the space complexity is O(1) as we only use a constant amount of extra space.

4. For optimization, I also demonstrated a solution that uses direct bitwise operations to count the differing bits more efficiently, leveraging functions like Integer.bitCount() to count the number of set bits."

---

## 🔥 Bit Manipulation Techniques

This problem showcases several important bit manipulation techniques:

1. **Extracting a specific bit**: `(num >> i) & 1` gives you the i-th bit of num.

2. **Bitwise OR (`|`)**: In `a | b`, a bit is 1 if either of the corresponding bits in a or b is 1.

3. **Bitwise XOR (`^`)**: In `a ^ b`, a bit is 1 only if the corresponding bits in a and b differ.

4. **Bitwise AND (`&`)**: In `a & b`, a bit is 1 only if both corresponding bits in a and b are 1.

5. **Counting set bits**: `Integer.bitCount(num)` counts the number of 1's in the binary representation of num.

These operations are fundamental for solving bit manipulation problems efficiently.

---

## 🔥 Final Takeaways

- **Bit manipulation problems often have elegant solutions** that are more efficient than naive approaches.
- **Understanding the logical requirements** for each bit position is key to solving this type of problem.
- **Breaking down the problem into cases** (when c[i] is 0 or 1) makes it easier to solve.
- **Using bitwise operations directly** can often lead to more concise and efficient code.
- **The solution demonstrates how to process bits** one by one and how to leverage built-in bit counting functions for optimization.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-ninth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.