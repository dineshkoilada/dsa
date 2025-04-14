# House Robber

## 📌 Problem Statement

**LeetCode Problem:** [198. House Robber](https://leetcode.com/problems/house-robber/)  
**Difficulty:** Medium  

**Description:**
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

### **Example 1:**
**Input:** 
```
nums = [1,2,3,1]
```
**Output:** 
```
4
```
**Explanation:** 
Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

### **Example 2:**
**Input:** 
```
nums = [2,7,9,3,1]
```
**Output:** 
```
12
```
**Explanation:** 
Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

### **Constraints:**
- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're playing a game where you're a robber who wants to steal money from houses on a street. Each house has some money, and you want to steal as much as possible. But there's a rule: you can't rob two houses that are next to each other, or an alarm will go off and you'll get caught!

Your job is to figure out which houses to rob to get the most money without getting caught. For example, if the houses have money like this: [1,2,3,1], you should rob the first house (1) and the third house (3) to get a total of 4, which is the most you can get.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What choices do we have at each house?**
   - We can either rob the current house (and skip the next one) or skip the current house (and have the option to rob the next one).
2. **How do we decide which houses to rob?**
   - We need to compare the money we get by robbing the current house and skipping the next one versus skipping the current house.
3. **Can we use dynamic programming for this?**
   - Yes! We can use dynamic programming to keep track of the maximum money we can get up to each house.

👉 These considerations will help us develop an efficient solution!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a dynamic programming array to store maximum money
```java
int n = nums.length;
if (n == 0) return 0;
if (n == 1) return nums[0];

int[] dp = new int[n];
```

### Step 2: Set the base cases
```java
dp[0] = nums[0];
dp[1] = Math.max(nums[0], nums[1]);
```

### Step 3: Fill the DP array using the recurrence relation
```java
for (int i = 2; i < n; i++) {
    dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
}
```

### Step 4: Return the maximum amount of money
```java
return dp[n-1];
```

---

## 🛠️ Dynamic Programming Solution

```java
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        
        // Handle edge cases
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        // Create array to store maximum money at each house
        int[] dp = new int[n];
        
        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        // Fill the dp array
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        
        // Return the maximum amount of money
        return dp[n-1];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each house once.
- **Space Complexity:** `O(n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the maximum money for the two previous houses at any point, we can optimize the space complexity:

```java
public class HouseRobberOptimized {
    public int rob(int[] nums) {
        int n = nums.length;
        
        // Handle edge cases
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        // Initialize variables to store the maximum money
        int twoHousesBack = nums[0];
        int oneHouseBack = Math.max(nums[0], nums[1]);
        int current = oneHouseBack;
        
        // Calculate maximum money for each house
        for (int i = 2; i < n; i++) {
            current = Math.max(oneHouseBack, twoHousesBack + nums[i]);
            twoHousesBack = oneHouseBack;
            oneHouseBack = current;
        }
        
        // Return the maximum amount of money
        return current;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we process each house once.
- **Space Complexity:** `O(1)` as we only use three variables regardless of the input size.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a dynamic programming problem where we need to make decisions that maximize our profit while respecting certain constraints."
- "At each house, we have two choices: rob it or skip it. If we rob it, we can't rob the adjacent house. If we skip it, we can rob the next house."
- "The key insight is that the maximum money we can get up to house i is the maximum of:
  - The maximum money up to house i-1 (which means we skip house i)
  - The maximum money up to house i-2 plus the money in house i (which means we rob house i)"
- "This gives us the recurrence relation: dp[i] = max(dp[i-1], dp[i-2] + nums[i])."
- "I used a bottom-up dynamic programming approach, starting from the base cases and building up to the final solution."
- "For space optimization, I noticed that we only need to keep track of the maximum money for the two previous houses at any point, so I reduced the space complexity from O(n) to O(1)."

---

## 🔥 Final Takeaways
- **Dynamic programming is excellent for optimization problems with overlapping subproblems.**
- **The recurrence relation is key: dp[i] = max(dp[i-1], dp[i-2] + nums[i]).**
- **Space optimization is often possible by recognizing that we only need a limited amount of previous state.**
- **Always handle edge cases carefully - here, we need special handling for empty arrays and arrays with just one or two elements.**
- **This problem teaches the concept of making optimal decisions under constraints and how to use dynamic programming to solve such problems efficiently.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/house-robber/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-one problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.