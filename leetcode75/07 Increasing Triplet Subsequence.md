# Increasing Triplet Subsequence

## 📌 Problem Statement

**LeetCode Problem:** [334. Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/)  
**Difficulty:** Medium  

**Description:**
Given an integer array `nums`, return `true` if there exists a triple of indices `(i, j, k)` such that `i < j < k` and `nums[i] < nums[j] < nums[k]`. If no such indices exist, return `false`.

### **Example 1:**
**Input:** 
```
nums = [1,2,3,4,5]
```
**Output:** 
```
true
```

### **Example 2:**
**Input:** 
```
nums = [5,4,3,2,1]
```
**Output:** 
```
false
```

### **Constraints:**
- `1 <= nums.length <= 5 * 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **list of numbers**, and you want to find **three numbers in increasing order** that appear **one after the other** in the list.

For example:
- **[1,2,3,4,5]** → ✅ **Yes**, because `1 < 2 < 3`.
- **[5,4,3,2,1]** → ❌ **No**, because there’s no increasing order.

Our goal is to check **if we can find three such numbers** anywhere in the array.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if the array has less than three numbers?**
   - We return `false` because we need at least three numbers.
2. **What if the numbers are all decreasing?**
   - We return `false` since no increasing sequence exists.
3. **What if the numbers are randomly placed?**
   - We need to scan through the array carefully.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Keep track of the first two smallest numbers
```java
int first = Integer.MAX_VALUE;
int second = Integer.MAX_VALUE;
```

### Step 2: Iterate through the array and update values
```java
for (int num : nums) {
    if (num <= first) {
        first = num; // Smallest number
    } else if (num <= second) {
        second = num; // Second smallest number
    } else {
        return true; // Found third number
    }
}
```

### Step 3: If no triplet is found, return `false`
```java
return false;
```

---

## 🛠️ Writing the Brute Force Solution (Not Allowed Due to `O(N^3)`) 🚨

```java
public class IncreasingTripletBruteForce {
    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^3)`, since we check every triplet.
- **Space Complexity:** `O(1)`, as no extra data structures are used.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution using Two Variables (O(N) Time, O(1) Space)

```java
public class IncreasingTripletOptimized {
    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num <= first) {
                first = num; // Update smallest number
            } else if (num <= second) {
                second = num; // Update second smallest
            } else {
                return true; // Found third number
            }
        }
        return false;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the array once.
- **Space Complexity:** `O(1)`, since we use only two extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We **track two smallest numbers** and try to find a third that is larger."
- "If we find a number greater than both, we return `true`."
- "This solution runs in `O(N)` time and uses only `O(1)` extra space."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force solution checks every triplet, but is too slow (`O(N^3)`)."
- "A `dp` approach could work, but `O(N)` with two variables is optimal."

---

## 🔥 Final Takeaways
- **Tracking two smallest numbers is key.**
- **If a third number is found, return `true`.**
- **Brute-force is too slow; `O(N)` is the best approach.**
- **Think about edge cases: decreasing arrays, small arrays.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/increasing-triplet-subsequence/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **eighth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.