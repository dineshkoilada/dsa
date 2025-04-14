# N-th Tribonacci Number

## 📌 Problem Statement

**LeetCode Problem:** [1137. N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number/)  
**Difficulty:** Easy  

**Description:**
The Tribonacci sequence T_n is defined as follows: 

T_0 = 0, T_1 = 1, T_2 = 1, and T_n+3 = T_n + T_n+1 + T_n+2 for n >= 0.

Given n, return the value of T_n.

### **Example 1:**
**Input:** 
```
n = 4
```
**Output:** 
```
4
```
**Explanation:** 
T_0 = 0, T_1 = 1, T_2 = 1, T_3 = T_0 + T_1 + T_2 = 0 + 1 + 1 = 2
T_4 = T_1 + T_2 + T_3 = 1 + 1 + 2 = 4

### **Example 2:**
**Input:** 
```
n = 25
```
**Output:** 
```
1389537
```

### **Constraints:**
- `0 <= n <= 37`
- The answer is guaranteed to fit within a 32-bit integer, ie. `answer <= 2^31 - 1`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

You've probably heard of the Fibonacci sequence, where each number is the sum of the two numbers before it. The Tribonacci sequence is similar, but each number is the sum of the *three* numbers before it!

Here's how it starts:
- The 0th number is 0
- The 1st number is 1
- The 2nd number is 1
- The 3rd number is 0 + 1 + 1 = 2
- The 4th number is 1 + 1 + 2 = 4
- The 5th number is 1 + 2 + 4 = 7
- And so on...

Your job is to find the n-th number in this sequence.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What are the base cases?**
   - We know the first three numbers: T_0 = 0, T_1 = 1, T_2 = 1.
2. **How do we calculate subsequent numbers?**
   - Each number is the sum of the three previous numbers.
3. **Can we use recursion?**
   - Yes, but it might be inefficient due to repeated calculations.
4. **Would an iterative approach be better?**
   - Likely yes, we can build the sequence step by step.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Handle the base cases
```java
if (n == 0) return 0;
if (n == 1 || n == 2) return 1;
```

### Step 2: Use dynamic programming to calculate each number in the sequence
```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;
dp[2] = 1;

for (int i = 3; i <= n; i++) {
    dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
}

return dp[n];
```

---

## 🛠️ Naive Recursive Solution (Not Efficient)

```java
public class NthTribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(3^n)` as we make 3 recursive calls for each n.
- **Space Complexity:** `O(n)` for the recursion stack.

---

## 🚀 Optimized Solution using Dynamic Programming

```java
public class NthTribonacciNumberDP {
    public int tribonacci(int n) {
        // Handle base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        // Create array to store Tribonacci numbers
        int[] dp = new int[n + 1];
        
        // Initialize the first three numbers
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        
        // Calculate each subsequent number
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        
        return dp[n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we calculate each number once.
- **Space Complexity:** `O(n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the last three numbers to calculate the next one, we can optimize the space complexity:

```java
public class NthTribonacciNumberSpaceOptimized {
    public int tribonacci(int n) {
        // Handle base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        // Initialize the first three numbers
        int t0 = 0, t1 = 1, t2 = 1, tn = 0;
        
        // Calculate each subsequent number
        for (int i = 3; i <= n; i++) {
            tn = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = tn;
        }
        
        return tn;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we calculate each number once.
- **Space Complexity:** `O(1)` as we only store 4 variables regardless of n.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "The Tribonacci sequence is defined recursively, where each number is the sum of the three previous numbers."
- "I first considered a recursive solution, but it would be inefficient due to repeated calculations, with a time complexity of O(3^n)."
- "Instead, I used a bottom-up dynamic programming approach, which has a time complexity of O(n) and avoids redundant calculations."
- "Starting with the base cases (T_0 = 0, T_1 = 1, T_2 = 1), I iteratively computed each subsequent number using the formula T_n = T_n-1 + T_n-2 + T_n-3."
- "To optimize space, I realized that we only need to keep track of the last three numbers to calculate the next one. This reduced the space complexity from O(n) to O(1)."
- "This approach is efficient for the given constraints and ensures we compute the n-th Tribonacci number correctly."

---

## 🔥 Final Takeaways
- **Dynamic programming is excellent for problems with overlapping subproblems.**
- **The bottom-up approach avoids the overhead of recursion and prevents redundant calculations.**
- **Space optimization is often possible by recognizing that we only need to store a limited number of previous states.**
- **Always check the constraints of the problem - here, n ≤ 37 means the answer fits in a 32-bit integer.**
- **This problem teaches the value of identifying patterns and the efficiency of iterative vs. recursive approaches.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/n-th-tribonacci-number/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-nine problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.