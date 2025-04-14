# Combination Sum III

## 📌 Problem Statement

**LeetCode Problem:** [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)  
**Difficulty:** Medium  

**Description:**
Find all valid combinations of `k` numbers that sum up to `n` such that the following conditions are true:

- Only numbers 1 through 9 are used.
- Each number is used **at most once**.

Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

### **Example 1:**
**Input:** 
```
k = 3, n = 7
```
**Output:** 
```
[[1,2,4]]
```
**Explanation:** 
1 + 2 + 4 = 7
There are no other valid combinations.

### **Example 2:**
**Input:** 
```
k = 3, n = 9
```
**Output:** 
```
[[1,2,6],[1,3,5],[2,3,4]]
```
**Explanation:** 
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.

### **Example 3:**
**Input:** 
```
k = 4, n = 1
```
**Output:** 
```
[]
```
**Explanation:** 
There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combinations.

### **Constraints:**
- `2 <= k <= 9`
- `1 <= n <= 60`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have the numbers 1 through 9 written on cards. You need to pick exactly `k` cards where:
1. You can't pick the same number twice.
2. The sum of the numbers on your cards must be exactly `n`.

Your job is to find all the different ways you can pick these cards to make the sum `n`.

For example, if you need to pick 3 cards that sum up to 7, there's only one way: you pick 1, 2, and 4 (because 1 + 2 + 4 = 7).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What's our approach going to be?**
   - We can use backtracking to try different combinations of numbers.
2. **How do we ensure we don't use a number more than once?**
   - We'll only consider numbers that are greater than the last number we used.
3. **How do we know when to stop?**
   - If we've picked `k` numbers and their sum is `n`, we've found a valid combination.
   - If we've picked `k` numbers but their sum isn't `n`, or if the sum exceeds `n`, we need to backtrack.

👉 These considerations will guide our backtracking approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up our backtracking framework
```java
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(1, k, n, new ArrayList<>(), result);
    return result;
}
```

### Step 2: Implement the backtracking function
```java
private void backtrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result) {
    // Base cases
    if (target < 0 || current.size() > k) return;
    if (current.size() == k && target == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    // Try all numbers from start to 9
    for (int i = start; i <= 9; i++) {
        current.add(i);
        backtrack(i + 1, k, target - i, current, result);
        current.remove(current.size() - 1); // backtrack
    }
}
```

---

## 🛠️ Complete Solution using Backtracking

```java
import java.util.*;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int k, int remaining, List<Integer> current, List<List<Integer>> result) {
        // If we've picked k numbers and their sum is n, we've found a valid combination
        if (current.size() == k && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // If we've picked too many numbers or the remaining sum is negative, backtrack
        if (current.size() > k || remaining < 0) {
            return;
        }
        
        // Try all possible numbers from start to 9
        for (int i = start; i <= 9; i++) {
            // Add the current number to our combination
            current.add(i);
            
            // Continue building the combination with the next number
            backtrack(i + 1, k, remaining - i, current, result);
            
            // Backtrack by removing the last number
            current.remove(current.size() - 1);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(C(9,k) * k)` where C(9,k) is the binomial coefficient, representing the number of ways to choose k items from a set of 9. For each combination, we spend O(k) time to copy it to the result.
- **Space Complexity:** `O(k)` for the recursion stack and to store the current combination.

---

## 🔄 Optimization: Early Pruning

We can optimize our solution by pruning branches early when we know they can't lead to a valid solution.

```java
import java.util.*;

public class CombinationSumIIIOptimized {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, int k, int remaining, List<Integer> current, List<List<Integer>> result) {
        // Base cases for backtracking
        if (remaining < 0 || current.size() > k) return;
        if (current.size() == k && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Calculate the minimum possible sum with the remaining spots
        int minSum = 0;
        for (int i = 0; i < k - current.size(); i++) {
            minSum += (start + i);
        }
        if (minSum > remaining) return; // Early pruning
        
        // Calculate the maximum possible sum with the remaining spots
        int maxSum = 0;
        for (int i = 0; i < k - current.size(); i++) {
            maxSum += (9 - i);
        }
        if (maxSum < remaining) return; // Early pruning
        
        // Try all possible next numbers
        for (int i = start; i <= 9; i++) {
            current.add(i);
            backtrack(i + 1, k, remaining - i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** Still `O(C(9,k) * k)` in the worst case, but with pruning, many branches are cut early, making it much faster in practice.
- **Space Complexity:** `O(k)` for the recursion stack and to store the current combination.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a perfect problem for backtracking because we need to find all valid combinations that satisfy certain conditions."
- "My approach is to build combinations one number at a time, starting from 1 and considering each number up to 9."
- "For each position in the combination, I try all possible numbers that could be placed there."
- "At each step, I check if we've reached a valid solution (k numbers that sum to n) or if we need to backtrack."
- "I optimize by ensuring we don't use a number more than once (by only considering larger numbers after picking a number) and by early pruning when we can determine that a partial solution can't lead to a valid result."
- "The early pruning works by calculating the minimum and maximum possible sums we could get if we filled the remaining spots with the smallest or largest possible numbers. If these don't include our target, we can prune the branch."
- "This gives us an efficient solution that finds all valid combinations without unnecessary exploration."

---

## 🔥 Final Takeaways
- **Backtracking is a systematic approach to find all solutions in combinatorial problems.**
- **Early pruning can significantly improve performance by avoiding branches that can't lead to valid solutions.**
- **When using backtracking, always make sure to properly undo changes before exploring a new branch.**
- **The combination of constraints (numbers 1-9, each used at most once, sum equals n) makes this problem well-suited for backtracking.**
- **Understanding the concept of state space and how to navigate it efficiently is crucial for solving such problems.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/combination-sum-iii/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-eight problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.