# Min Cost Climbing Stairs

## 📌 Problem Statement

**LeetCode Problem:** [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)  
**Difficulty:** Easy  

**Description:**
You are given an integer array `cost` where `cost[i]` is the cost of `i`th step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

### **Example 1:**
**Input:** 
```
cost = [10,15,20]
```
**Output:** 
```
15
```
**Explanation:** 
You will start at index 1.
- Pay 15 and climb one step to reach the top.
The total cost is 15.

### **Example 2:**
**Input:** 
```
cost = [1,100,1,1,1,100,1,1,100,1]
```
**Output:** 
```
6
```
**Explanation:** 
You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.

### **Constraints:**
- `2 <= cost.length <= 1000`
- `0 <= cost[i] <= 999`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a staircase, and each step has a number written on it. That number is how many coins you need to pay to stand on that step.

You can start on either the first step (index 0) or the second step (index 1). Once you pay and stand on a step, you can either:
- Jump one step up, or
- Jump two steps up.

Your goal is to reach the top of the staircase (which is one step beyond the last step in the array) while paying the least amount of coins possible.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **When we reach a step, should we jump one step or two steps?**
   - We should choose the option that minimizes our total cost.
2. **How do we keep track of the minimum cost for each step?**
   - We can use dynamic programming to store the minimum cost to reach each step.
3. **What's our base case?**
   - The minimum cost to reach steps 0 and 1 is just their own costs.

👉 These considerations will help us approach the problem systematically!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a dynamic programming array to store minimum costs
```java
int n = cost.length;
int[] dp = new int[n];
```

### Step 2: Set the base cases (first two steps)
```java
dp[0] = cost[0];
dp[1] = cost[1];
```

### Step 3: Calculate the minimum cost for each subsequent step
```java
for (int i = 2; i < n; i++) {
    dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
}
```

### Step 4: Return the minimum cost to reach the top (which is beyond the last step)
```java
return Math.min(dp[n-1], dp[n-2]);
```

---

## 🛠️ Dynamic Programming Solution

```java
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        // Edge cases
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        
        // Create array to store minimum cost to reach each step
        int[] dp = new int[n];
        
        // Base cases
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        // Fill the dp array
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }
        
        // The final answer is the minimum cost to reach the top
        // which is one step beyond the last step
        return Math.min(dp[n-1], dp[n-2]);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each step once.
- **Space Complexity:** `O(n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the minimum costs for the two previous steps at any point, we can optimize the space complexity:

```java
public class MinCostClimbingStairsOptimized {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        // Edge cases
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        
        // We only need to keep track of the two previous steps
        int oneStepBefore = cost[1];
        int twoStepsBefore = cost[0];
        int currentStep;
        
        // Calculate minimum cost for each step
        for (int i = 2; i < n; i++) {
            currentStep = cost[i] + Math.min(oneStepBefore, twoStepsBefore);
            twoStepsBefore = oneStepBefore;
            oneStepBefore = currentStep;
        }
        
        // Return minimum cost to reach the top
        return Math.min(oneStepBefore, twoStepsBefore);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each step once.
- **Space Complexity:** `O(1)` as we only use three variables regardless of the input size.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a classic dynamic programming problem where we need to make optimal choices at each step."
- "For each step, we have two choices: we can either reach it by taking one step from the previous step, or two steps from the step before that."
- "The key insight is that the minimum cost to reach the current step is the cost of the current step plus the minimum of the costs to reach the two previous steps."
- "I used a bottom-up dynamic programming approach, starting from the base cases (the first two steps) and building up to the final solution."
- "For each position i, dp[i] represents the minimum cost to reach step i."
- "To reach the top of the floor, which is one step beyond the last step, we can either take one step from the second-last step or two steps from the third-last step. So the answer is the minimum of dp[n-1] and dp[n-2]."
- "I also implemented a space-optimized version that uses only constant extra space, as we only need to remember the minimum costs for the two previous steps at any point."

---

## 🔥 Final Takeaways
- **Dynamic programming is perfect for problems with overlapping subproblems and optimal substructure.**
- **The recurrence relation is key: dp[i] = cost[i] + min(dp[i-1], dp[i-2]).**
- **Space optimization is often possible when we only need information from a few previous states.**
- **Always consider the base cases carefully to build your DP solution correctly.**
- **This problem teaches the importance of realizing that sometimes the optimal solution requires making locally suboptimal choices.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/min-cost-climbing-stairs/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixtieth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.