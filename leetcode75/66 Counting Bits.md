# Counting Bits

## 📌 Problem Statement

**LeetCode Problem:** [338. Counting Bits](https://leetcode.com/problems/counting-bits/)  
**Difficulty:** Easy  

**Description:**
Given an integer `n`, return an array `ans` of length `n + 1` such that for each `i` (`0 <= i <= n`), `ans[i]` is the number of `1`'s in the binary representation of `i`.

### **Example 1:**
**Input:** 
```
n = 2
```
**Output:** 
```
[0,1,1]
```
**Explanation:** 
- 0 → 0 (0 has 0 bits set to 1)
- 1 → 1 (1 has 1 bit set to 1)
- 2 → 10 (2 has 1 bit set to 1)

### **Example 2:**
**Input:** 
```
n = 5
```
**Output:** 
```
[0,1,1,2,1,2]
```
**Explanation:** 
- 0 → 0 (0 has 0 bits set to 1)
- 1 → 1 (1 has 1 bit set to 1)
- 2 → 10 (2 has 1 bit set to 1)
- 3 → 11 (3 has 2 bits set to 1)
- 4 → 100 (4 has 1 bit set to 1)
- 5 → 101 (5 has 2 bits set to 1)

### **Constraints:**
- `0 <= n <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a counting game where you need to count how many 1's appear when you write numbers in binary (which means using only 0's and 1's).

For example:
- The number 0 in binary is just "0", so there are 0 ones.
- The number 1 in binary is "1", so there is 1 one.
- The number 2 in binary is "10", so there is 1 one.
- The number 3 in binary is "11", so there are 2 ones.
- The number 4 in binary is "100", so there is 1 one.
- The number 5 in binary is "101", so there are 2 ones.

The question asks you to create a list where, for each number from 0 to n, you count how many 1's are in its binary representation.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Is there a pattern we can observe in the binary representation of numbers?**
   - Yes, there are interesting patterns we can use.
2. **Can we use results we've already calculated to find new results?**
   - Yes, this is a perfect case for dynamic programming.
3. **Do we need to manually convert each number to binary?**
   - We can use bit manipulation techniques instead.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Approach 1: Counting Bits Directly (Brute Force)

The most straightforward approach is to count the number of 1's in each number's binary representation:

```java
int[] result = new int[n + 1];
for (int i = 0; i <= n; i++) {
    result[i] = countOnes(i);
}
return result;

// Helper function to count 1's in binary representation
private int countOnes(int num) {
    int count = 0;
    while (num > 0) {
        count += num & 1; // Check if the least significant bit is 1
        num >>= 1;        // Right shift by 1
    }
    return count;
}
```

### Approach 2: Dynamic Programming (Last Significant Bit)

We can observe that: `bits[i] = bits[i >> 1] + (i & 1)`

In other words, the number of 1's in the current number equals:
- The number of 1's in the number with the last bit removed, plus
- The value of the last bit (0 or 1)

```java
int[] result = new int[n + 1];
for (int i = 1; i <= n; i++) {
    result[i] = result[i >> 1] + (i & 1);
}
return result;
```

### Approach 3: Dynamic Programming (Least Significant Bit)

Another pattern: `bits[i] = bits[i & (i-1)] + 1`

This uses the fact that `i & (i-1)` removes the least significant 1-bit from i.

```java
int[] result = new int[n + 1];
for (int i = 1; i <= n; i++) {
    result[i] = result[i & (i-1)] + 1;
}
return result;
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
public class CountingBits {
    
    /**
     * Returns an array where each element is the count of 1's in the binary representation
     * of its index.
     * 
     * @param n Upper limit of the range [0, n]
     * @return Array containing the count of 1's for each number from 0 to n
     */
    public int[] countBits(int n) {
        // Create array to store results
        int[] result = new int[n + 1];
        
        // Base case: 0 has 0 bits set to 1
        result[0] = 0;
        
        // Fill the array using dynamic programming
        for (int i = 1; i <= n; i++) {
            // Key insight: The number of 1's in the current number equals
            // the number of 1's in the number with the least significant bit removed,
            // plus the value of the least significant bit
            result[i] = result[i >> 1] + (i & 1);
        }
        
        return result;
    }
    
    /**
     * Alternative implementation using the pattern: 
     * bits[i] = bits[i & (i-1)] + 1
     */
    public int[] countBitsAlternative(int n) {
        int[] result = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // i & (i-1) removes the least significant 1-bit from i
            result[i] = result[i & (i-1)] + 1;
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** Both implementations are `O(n)` because we're iterating through numbers from 0 to n once.
- **Space Complexity:** `O(n)` for the result array of size n+1.

---

## 🔥 Bit Manipulation Fundamentals

This problem introduces some fundamental bit manipulation operations:

1. **AND operation (`&`)**: 
   - `n & 1` returns the least significant bit (0 or 1)
   - `i & (i-1)` removes the least significant 1-bit

2. **Right Shift (`>>`)**: 
   - `n >> 1` divides n by 2 and removes the least significant bit

Understanding these operations is key to solving bit manipulation problems efficiently.

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I observed that we can use dynamic programming to solve this problem efficiently. The key insight is that we can leverage the binary representation patterns:

1. For any number i, the number of 1's in i equals the number of 1's in i/2, plus the least significant bit of i.
   - For example, 6 (110) has the same number of 1's as 3 (11) plus its last bit (0).

2. So the recurrence relation is: dp[i] = dp[i >> 1] + (i & 1)

3. I implemented this by:
   - Creating an array to store results
   - Handling the base case (0 has 0 bits)
   - Iterating from 1 to n, applying the recurrence relation
   
This solution has O(n) time complexity and O(n) space complexity, which is optimal since we need to return an array of size n+1."

---

## 🔬 Alternative Approach: Bit Counting Builtin

Some programming languages provide built-in functions to count bits:

```java
public int[] countBits(int n) {
    int[] result = new int[n + 1];
    for (int i = 0; i <= n; i++) {
        result[i] = Integer.bitCount(i);  // Built-in function to count bits
    }
    return result;
}
```

While this works, understanding the underlying patterns is much more valuable for interviews and problem-solving skills.

---

## 🔥 Final Takeaways

- **Bit manipulation problems often have elegant solutions** that are faster than brute force approaches.
- **Recognizing patterns in binary representation** can lead to efficient dynamic programming solutions.
- **The recurrence relation dp[i] = dp[i >> 1] + (i & 1)** elegantly captures the relationship between a number and its binary representation.
- **Basic bit operations like & and >>** are fundamental tools for solving bit manipulation problems.
- **This problem is an excellent introduction to both dynamic programming and bit manipulation techniques.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/counting-bits/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-seventh problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.