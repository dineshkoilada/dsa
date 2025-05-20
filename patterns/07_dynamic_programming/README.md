# Dynamic Programming (DP) Pattern 🧮

## 📌 Introduction: The Power of Remembering

Imagine climbing a staircase, where at each step, you can either take 1 step or 2 steps. Instead of recalculating the total ways from scratch every time, you remember previous results and build upon them. This is the essence of **Dynamic Programming (DP)**—solving problems by breaking them into simpler overlapping subproblems and storing solutions to avoid redundant work.

### 🎬 Real-World Analogies:

1. **Staircase Climbing** 🪜
   - You remember how many ways to reach each step, so you don't have to recalculate for every new step.
2. **Memo Board** 📝
   - You write down solutions to subproblems so you can look them up instead of solving them again.
3. **Recipe Book** 📖
   - You reuse recipes (sub-solutions) for different dishes, saving time and effort.

Dynamic Programming is your secret weapon for:
- 🏆 Optimization problems (finding the maximum/minimum values)
- 🔁 Problems with overlapping subproblems
- 🧩 Problems with optimal substructure (solutions can be built from smaller solutions)
- 🔢 Counting problems (number of ways, number of paths)

### 🎯 Visual Example:
Climbing stairs with 1 or 2 steps at a time:
```
Ways to reach step n:
ways(n) = ways(n-1) + ways(n-2)

If you know ways(2) and ways(1), you can quickly get ways(3), then ways(4), and so on.
```

---

## 🧠 How to Recognize a Dynamic Programming Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Overlapping Subproblems" Clue** 🔄
   - The same subproblem is solved multiple times
   - Example: "Fibonacci sequence, climbing stairs"
2. **The "Optimal Substructure" Hint** 🧩
   - The solution can be built from solutions to smaller subproblems
   - Example: "Maximum sum subarray, longest increasing subsequence"
3. **The "Counting/Optimization" Signal** 🏆
   - The problem asks for the number of ways, or the best (max/min) value

### 🤔 Essential Questions to Ask:

1. **Problem Type Questions:**
   - Are you optimizing something or counting the number of ways?
   - Does the solution depend on solving smaller subproblems first?
2. **Content Questions:**
   - What are the base cases?
   - Are there constraints on the input size?
3. **Edge Case Questions:**
   - What should be returned for the smallest input?
   - Are there any restrictions or constraints?

### 🎨 Visual Problem-Solving Framework:

Imagine you’re filling out a table, one cell at a time, where each cell depends on the answers to previous cells. Here’s a different way to visualize DP:

```
Table (dp array) for n = 5:

Step 1: Start with base cases
[1][1][ ][ ][ ]   // Fill in known answers (e.g., ways(0)=1, ways(1)=1)

Step 2: Fill next cell using previous answers
[1][1][2][ ][ ]   // ways(2) = ways(1) + ways(0)
[1][1][2][3][ ]   // ways(3) = ways(2) + ways(1)
[1][1][2][3][5]   // ways(4) = ways(3) + ways(2)

Arrows show dependencies:
[1]←[1]   [2]←[1]+[1]   [3]←[2]+[1]   [5]←[3]+[2]

Or as a dependency graph:

   [5]
  ↙   ↘
[3]   [2]
 |\   /|
[2][1][1][0]

- Each cell is filled using results from previous cells.
- The arrows show how subproblems combine to solve bigger problems.
- DP is like building a pyramid, where each block rests on the ones below.
```
- This approach highlights the table-filling and dependency structure of DP, making it easy to see how solutions are built up.

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you optimizing something or counting the number of ways?
- Does the solution depend on solving smaller subproblems first?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned for the smallest input?
- Are there any restrictions or constraints?

### ✅ **3. Identify Base Cases**
- Define solutions for the simplest cases (e.g., when `n = 0` or `n = 1`).

### ✅ **4. Write Pseudo-Code for Base Cases**
```
function DP(input):
    initialize dp array
    set base cases
    for i from 1 to n:
        dp[i] = combine(dp[i-1], dp[i-2], ...)
    return dp[n]
```

### ✅ **5. Write the Code Skeleton**
```java
public class DPTemplate {
    public static int solveDP(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The input is zero or negative.
- The input is very large (consider memory usage).

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                | Time Complexity | Space Complexity |
|--------------------------|-----------------|------------------|
| Bottom-up DP             | O(n)            | O(n)             |
| Top-down DP (Memoization)| O(n)            | O(n)             |
| Space Optimized          | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Every subproblem is solved once.
- **Space Complexity:** Depends on whether you use a full table or optimize space using variables.

---

## 📚 Example 1: Easy Problem - Fibonacci Sequence

**Problem:**
Find the `n`th Fibonacci number.

**Input:** `n = 5`

**Expected Output:** `5`

### 🔑 **Solution Steps**
1. Initialize an array `dp` of size `n+1`.
2. Set `dp[0] = 0` and `dp[1] = 1`.
3. Use the relation `dp[i] = dp[i-1] + dp[i-2]`.

### ✅ **Code:**
```java
public class FibonacciDP {
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each Fibonacci number is computed once.
- **Space:** O(n) — Array stores `n+1` elements.

---

## 📚 Example 2: Medium Problem - Climbing Stairs

**Problem:**
You can climb either 1 or 2 steps at a time. Find how many distinct ways you can climb to the top.

**Input:** `n = 3`

**Expected Output:** `3`

### 🔑 **Solution Steps**
1. Initialize `dp` array.
2. Base cases: `dp[0] = 1`, `dp[1] = 1`.
3. Use relation `dp[i] = dp[i-1] + dp[i-2]`.

### ✅ **Code:**
```java
public class ClimbingStairsDP {
    public static int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Solving each subproblem once.
- **Space:** O(n) — Array of size `n+1`.

---

## 📚 Example 3: Hard Problem - Longest Palindromic Substring

**Problem:**
Find the longest palindromic substring in a given string.

**Input:** `"babad"`

**Expected Output:** `"bab"` or `"aba"`

### 🔑 **Solution Steps**
1. Initialize a `dp` table where `dp[i][j]` is `true` if `s[i..j]` is a palindrome.
2. Base cases: Every single character is a palindrome.
3. Check substrings of increasing lengths.

### ✅ **Code:**
```java
public class LongestPalindromeDP {
    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String result = "";

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > result.length()) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n²) — All substrings are checked.
- **Space:** O(n²) — 2D array for palindromic checks.

---

## 📚 Key Takeaways

1. Use Dynamic Programming for problems with overlapping subproblems and optimal substructure.
2. Break down problems into smaller subproblems and solve them incrementally.
3. Time and space complexity often depend on the size of the input and the dimensions of the problem.
4. Space optimization techniques can help reduce memory usage for larger problems.

---

Next, let’s dive into the **Backtracking Pattern** for solving problems that involve exploring all possible configurations like permutations, combinations, and puzzles!

