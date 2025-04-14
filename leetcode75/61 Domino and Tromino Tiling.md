# Domino and Tromino Tiling

## 📌 Problem Statement

**LeetCode Problem:** [790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling/)  
**Difficulty:** Medium  

**Description:**
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.

![Domino and Tromino](https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg)

Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 10^9 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

### **Example 1:**
**Input:** 
```
n = 3
```
**Output:** 
```
5
```
**Explanation:** 
The five different ways are show above.

### **Example 2:**
**Input:** 
```
n = 1
```
**Output:** 
```
1
```

### **Constraints:**
- `1 <= n <= 1000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a rectangular board that is 2 units tall and n units wide. You also have two types of tiles:
1. Domino tiles: 2×1 rectangles (you can place them horizontally or vertically)
2. Tromino tiles: L-shaped tiles (you can rotate them in different ways)

You need to find out how many different ways you can completely cover the board with these tiles, without any overlaps or gaps.

For example, if the board is 2×3, there are 5 different ways to cover it.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What are the base cases?**
   - For n = 1, there is only 1 way to tile the board (a horizontal domino).
   - For n = 2, there are 2 ways (two horizontal dominoes or two vertical dominoes).
2. **Can we find a pattern or recurrence relation?**
   - We can try to express the number of ways for a board of width n in terms of smaller boards.
3. **Do we need to consider different configurations?**
   - Yes! We need to account for partially filled states as well.

👉 These considerations will help us develop a dynamic programming solution!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define our DP states
```java
// dp[n] represents the number of ways to fully tile a 2 x n board
int[] dp = new int[n + 1];
```

### Step 2: Initialize with base cases
```java
dp[1] = 1; // Only one way to tile a 2 x 1 board
if (n >= 2) dp[2] = 2; // Two ways to tile a 2 x 2 board
if (n >= 3) dp[3] = 5; // Five ways to tile a 2 x 3 board
```

### Step 3: Fill the DP array using the recurrence relation
```java
for (int i = 4; i <= n; i++) {
    dp[i] = (2 * dp[i-1] + dp[i-3]) % 1_000_000_007;
}
```

### Step 4: Return the final result
```java
return dp[n];
```

---

## 🛠️ Dynamic Programming Solution with Simple Recurrence

```java
public class DominoAndTrominoTiling {
    public int numTilings(int n) {
        final int MOD = 1_000_000_007;
        
        // Handle base cases
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        
        // Create array to store the number of ways
        int[] dp = new int[n + 1];
        
        // Initialize base cases
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        
        // Fill the dp array
        for (int i = 4; i <= n; i++) {
            dp[i] = (2 * dp[i-1] % MOD + dp[i-3] % MOD) % MOD;
        }
        
        return dp[n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each width once.
- **Space Complexity:** `O(n)` for the dp array.

---

## 🔄 More Detailed DP Solution

The simplified recurrence above works, but let's understand the problem more deeply by considering different states:

```java
public class DominoAndTrominoTilingDetailed {
    public int numTilings(int n) {
        final int MOD = 1_000_000_007;
        
        // Handle base case
        if (n == 1) return 1;
        
        // dp[i][0]: number of ways to tile a 2 x i board completely
        // dp[i][1]: number of ways to tile a 2 x i board with the top-right cell uncovered
        // dp[i][2]: number of ways to tile a 2 x i board with the bottom-right cell uncovered
        long[][] dp = new long[n + 1][3];
        
        // Initialize base cases
        dp[1][0] = 1; // One way to fully tile a 2 x 1 board
        dp[1][1] = 0; // Can't have the top-right cell uncovered in a 2 x 1 board
        dp[1][2] = 0; // Can't have the bottom-right cell uncovered in a 2 x 1 board
        
        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-2][0] + dp[i-1][1]) % MOD;
        }
        
        return (int) dp[n][0];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each width once.
- **Space Complexity:** `O(n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the results from the previous two iterations, we can optimize the space complexity:

```java
public class DominoAndTrominoTilingSpaceOptimized {
    public int numTilings(int n) {
        final int MOD = 1_000_000_007;
        
        // Handle base cases
        if (n == 1) return 1;
        
        // We only need to keep track of the previous two states
        long[] dp0 = {1, 0, 0}; // i-2
        long[] dp1 = {1, 0, 0}; // i-1
        long[] curr = new long[3]; // current
        
        for (int i = 2; i <= n; i++) {
            curr[0] = (dp1[0] + dp0[0] + dp1[1] + dp1[2]) % MOD;
            curr[1] = (dp0[0] + dp1[2]) % MOD;
            curr[2] = (dp0[0] + dp1[1]) % MOD;
            
            // Update for next iteration
            dp0 = dp1;
            dp1 = curr.clone();
        }
        
        return (int) curr[0];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each width once.
- **Space Complexity:** `O(1)` as we only use a constant amount of space.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a dynamic programming problem where we need to count the number of valid tilings for a 2 x n board."
- "I observed that there are three possible states for any column i: fully tiled, top cell uncovered, or bottom cell uncovered."
- "For a fully tiled board up to column i, we can derive it from:
  - A fully tiled board up to column i-1 and placing a vertical domino
  - A fully tiled board up to column i-2 and placing two horizontal dominoes
  - A board with the top of column i-1 uncovered and placing a tromino
  - A board with the bottom of column i-1 uncovered and placing a tromino"
- "Similarly, I worked out recurrence relations for the other states."
- "The simpler recurrence relation dp[i] = 2 * dp[i-1] + dp[i-3] can also be derived by analyzing the pattern, but the approach with three states provides better insight into the problem."
- "I implemented a space-optimized version that only keeps track of the necessary previous states, reducing the space complexity from O(n) to O(1)."

---

## 🔥 Final Takeaways
- **Dynamic programming can be applied to counting problems by defining appropriate states.**
- **For tiling problems, considering partial tilings or boundary conditions is often key.**
- **The mod operation (% 1_000_000_007) is needed to prevent integer overflow for large results.**
- **Space optimization is possible by recognizing dependencies only on recent previous states.**
- **This problem teaches the importance of identifying recurrence relations and handling base cases correctly.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/domino-and-tromino-tiling/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-second problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.