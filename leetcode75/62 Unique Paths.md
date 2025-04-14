# Unique Paths

## 📌 Problem Statement

**LeetCode Problem:** [62. Unique Paths](https://leetcode.com/problems/unique-paths/)  
**Difficulty:** Medium  

**Description:**
There is a robot on an m x n grid. The robot is initially positioned at the top-left corner (i.e., `grid[0][0]`). The robot tries to move to the bottom-right corner (i.e., `grid[m-1][n-1]`). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

### **Example 1:**
**Input:** 
```
m = 3, n = 7
```
**Output:** 
```
28
```
![Example 1 illustration](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

### **Example 2:**
**Input:** 
```
m = 3, n = 2
```
**Output:** 
```
3
```
**Explanation:** 
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

### **Constraints:**
- `1 <= m, n <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine a robot on a grid that looks like a checkerboard. The robot starts at the top-left square. It wants to reach the bottom-right square. But here's the rule: the robot can only move right or down, never left or up.

Your job is to figure out how many different ways the robot can reach the goal.

For example, on a tiny 2x2 grid, there are two ways:
1. Right then Down
2. Down then Right

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What are the possible moves at each step?**
   - The robot can only move right or down.
2. **How can we count all possible paths?**
   - We can use dynamic programming to build up the answer.
3. **What's the base case?**
   - For cells in the top row or leftmost column, there's only one way to reach them.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a 2D DP array to store the number of unique paths to each cell
```java
int[][] dp = new int[m][n];
```

### Step 2: Initialize the base cases (top row and leftmost column)
```java
// Top row - only one way to reach (move right)
for (int j = 0; j < n; j++) {
    dp[0][j] = 1;
}

// Leftmost column - only one way to reach (move down)
for (int i = 0; i < m; i++) {
    dp[i][0] = 1;
}
```

### Step 3: Fill the DP array using the recurrence relation
```java
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i-1][j] + dp[i][j-1];
    }
}
```

### Step 4: Return the number of unique paths to the bottom-right cell
```java
return dp[m-1][n-1];
```

---

## 🛠️ Dynamic Programming Solution

```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // Create DP array to store the number of unique paths
        int[][] dp = new int[m][n];
        
        // Initialize the base cases
        
        // Top row - only one way to reach (move right)
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Leftmost column - only one way to reach (move down)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the DP array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Number of ways to reach current cell = 
                // Number of ways to reach cell above + number of ways to reach cell to the left
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        // Return the number of unique paths to the bottom-right cell
        return dp[m-1][n-1];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` as we process each cell once.
- **Space Complexity:** `O(m * n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the values from the row above and the column to the left, we can optimize space by using a 1D array:

```java
public class UniquePathsOptimized {
    public int uniquePaths(int m, int n) {
        // Create a 1D array to store the number of unique paths for the current row
        int[] dp = new int[n];
        
        // Initialize the base case (first row)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Process each row
        for (int i = 1; i < m; i++) {
            // Process each column
            for (int j = 1; j < n; j++) {
                // dp[j] already contains the number of paths from the cell above
                // dp[j-1] contains the number of paths from the cell to the left
                dp[j] = dp[j] + dp[j-1];
            }
        }
        
        return dp[n-1];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` as we process each cell once.
- **Space Complexity:** `O(n)` for the 1D dp array.

---

## 🧮 Mathematical Solution using Combinations

There's also a mathematical approach to this problem. Since the robot must take exactly (m-1) steps down and (n-1) steps right to reach the bottom-right corner, the total number of steps is (m-1) + (n-1) = m+n-2. We need to choose which (m-1) of these steps will be down-steps (or equivalently, which (n-1) will be right-steps). This is a combination problem: C(m+n-2, m-1) or C(m+n-2, n-1).

```java
public class UniquePathsMath {
    public int uniquePaths(int m, int n) {
        // We need to calculate C(m+n-2, m-1)
        
        // To avoid overflow, we'll compute C(m+n-2, min(m-1, n-1))
        int a = m + n - 2;
        int b = Math.min(m - 1, n - 1);
        
        long result = 1;
        
        // Calculate a! / (b! * (a-b)!)
        for (int i = 1; i <= b; i++) {
            result = result * (a - b + i) / i;
        }
        
        return (int) result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(min(m, n))` for the combination calculation.
- **Space Complexity:** `O(1)` as we only use a few variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a classic grid traversal problem that can be solved efficiently using dynamic programming."
- "The key insight is that to reach any cell (i, j), the robot can only come from either the cell above (i-1, j) or the cell to the left (i, j-1)."
- "Therefore, the number of unique paths to reach cell (i, j) is the sum of the number of unique paths to reach the cell above and the number of unique paths to reach the cell to the left."
- "This gives us the recurrence relation: dp[i][j] = dp[i-1][j] + dp[i][j-1]."
- "For the base cases, cells in the top row or leftmost column can only be reached in one way."
- "I implemented a space-optimized solution that uses a 1D array instead of a 2D array, reducing the space complexity from O(m*n) to O(n)."
- "There's also a mathematical solution using combinations, as the problem reduces to choosing which (m-1) out of (m+n-2) steps will be down-steps."

---

## 🔥 Final Takeaways
- **Dynamic programming is perfect for counting path problems on grids.**
- **The recurrence relation is key: dp[i][j] = dp[i-1][j] + dp[i][j-1].**
- **Space optimization is possible by recognizing that we only need the current and previous row.**
- **Combinatorial problems like this often have mathematical solutions using combinatorics.**
- **This problem teaches the importance of recognizing patterns and applying dynamic programming efficiently.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/unique-paths/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-third problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.