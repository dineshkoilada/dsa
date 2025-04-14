# Best Time to Buy and Sell Stock with Transaction Fee

## 📌 Problem Statement

**LeetCode Problem:** [714. Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)  
**Difficulty:** Medium  

**Description:**
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day, and an integer `fee` representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:
- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
- The transaction fee is only charged when you sell the stock.

### **Example 1:**
**Input:** 
```
prices = [1,3,2,8,4,9], fee = 2
```
**Output:** 
```
8
```
**Explanation:** 
The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9

The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

### **Example 2:**
**Input:** 
```
prices = [1,3,7,5,10,3], fee = 3
```
**Output:** 
```
6
```

### **Constraints:**
- `1 <= prices.length <= 5 * 10^4`
- `0 <= prices[i], fee < 5 * 10^4`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're playing a game where you can buy and sell stocks. Each day, the stock has a different price. You want to make as much money as possible by buying stocks on some days and selling them on other days.

There's a special rule: every time you sell a stock, you have to pay a transaction fee. You can buy and sell as many times as you want, but you can only hold one stock at a time (you have to sell before buying again).

For example, if the prices are [1, 3, 2, 8, 4, 9] and the fee is 2:
- You buy at price 1
- You sell at price 8 and pay a fee of 2, so your profit is 8 - 1 - 2 = 5
- You buy at price 4
- You sell at price 9 and pay a fee of 2, so your profit is 9 - 4 - 2 = 3

Your total profit is 5 + 3 = 8, which is the maximum possible.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What states do we need to keep track of?**
   - We need to know whether we're holding a stock or not at each point.
2. **What decisions can we make on each day?**
   - If we don't have a stock, we can buy one or do nothing.
   - If we have a stock, we can sell it or hold it.
3. **How does the transaction fee affect our decisions?**
   - We need to factor in the fee when we sell, which might make some transactions unprofitable.

👉 These considerations will guide our dynamic programming approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define our two states (holding or not holding a stock)
```java
int[] hold = new int[prices.length];
int[] notHold = new int[prices.length];
```

### Step 2: Initialize the base cases
```java
hold[0] = -prices[0];
notHold[0] = 0;
```

### Step 3: Fill the arrays using the recurrence relation
```java
for (int i = 1; i < prices.length; i++) {
    hold[i] = Math.max(hold[i-1], notHold[i-1] - prices[i]);
    notHold[i] = Math.max(notHold[i-1], hold[i-1] + prices[i] - fee);
}
```

### Step 4: Return the maximum profit (which will be in the not-holding state on the last day)
```java
return notHold[prices.length - 1];
```

---

## 🛠️ Dynamic Programming Solution

```java
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        // Edge case
        if (n <= 1) return 0;
        
        // Create arrays to store maximum profit in two states:
        // hold[i]: maximum profit at day i if we are holding a stock
        // notHold[i]: maximum profit at day i if we are not holding a stock
        int[] hold = new int[n];
        int[] notHold = new int[n];
        
        // Initialize base cases
        hold[0] = -prices[0];    // buy a stock on day 0
        notHold[0] = 0;          // do nothing on day 0
        
        // Fill the arrays
        for (int i = 1; i < n; i++) {
            // If holding a stock on day i, either:
            // 1. We were already holding it from day i-1
            // 2. We buy it on day i (using the profit from not holding on day i-1)
            hold[i] = Math.max(hold[i-1], notHold[i-1] - prices[i]);
            
            // If not holding a stock on day i, either:
            // 1. We were already not holding it from day i-1
            // 2. We sell the stock we were holding on day i-1 (and pay the fee)
            notHold[i] = Math.max(notHold[i-1], hold[i-1] + prices[i] - fee);
        }
        
        // The maximum profit will be in the not-holding state on the last day
        return notHold[n-1];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each price once.
- **Space Complexity:** `O(n)` for the two arrays.

---

## 🔄 Space-Optimized Solution

Since we only need the previous day's states, we can optimize space:

```java
public class BestTimeToBuyAndSellStockWithTransactionFeeOptimized {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        // Edge case
        if (n <= 1) return 0;
        
        // Initialize variables for the two states
        int hold = -prices[0];    // maximum profit if holding a stock
        int notHold = 0;          // maximum profit if not holding a stock
        
        // Process each price
        for (int i = 1; i < n; i++) {
            int prevHold = hold;
            int prevNotHold = notHold;
            
            // Update the two states
            hold = Math.max(prevHold, prevNotHold - prices[i]);
            notHold = Math.max(prevNotHold, prevHold + prices[i] - fee);
        }
        
        // The maximum profit will be in the not-holding state
        return notHold;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each price once.
- **Space Complexity:** `O(1)` as we only use two variables regardless of the input size.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a dynamic programming problem where we need to keep track of two states at each day: whether we're holding a stock or not."
- "For each day, we have the following options:
  - If we're not holding a stock, we can either keep not holding or buy a stock.
  - If we're holding a stock, we can either keep holding or sell it (and pay the transaction fee)."
- "I defined two arrays (or variables in the optimized version):
  - hold[i]: maximum profit at day i if holding a stock
  - notHold[i]: maximum profit at day i if not holding a stock"
- "The recurrence relations are:
  - hold[i] = max(hold[i-1], notHold[i-1] - prices[i])
  - notHold[i] = max(notHold[i-1], hold[i-1] + prices[i] - fee)"
- "The base cases are hold[0] = -prices[0] (buying on day 0) and notHold[0] = 0 (doing nothing on day 0)."
- "For the space-optimized solution, I observed that we only need the previous day's states, so we can use two variables instead of arrays."
- "The time complexity is O(n) as we process each price once, and the space complexity is O(1) for the optimized version."

---

## 🔥 Final Takeaways
- **State transitions are key in dynamic programming problems involving sequences of decisions.**
- **For stock problems, it's often helpful to define states based on whether we're holding a stock or not.**
- **The transaction fee is considered only when selling (as stated in the problem), which affects our profit calculation.**
- **Space optimization is possible by recognizing that we only need the most recent states.**
- **This problem teaches the importance of clearly defining states and transitions in dynamic programming problems.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-fifth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.