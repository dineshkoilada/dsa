# Maximum Average Subarray I

## 📌 Problem Statement

**LeetCode Problem:** [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)  
**Difficulty:** Easy  

**Description:**
Given an integer array `nums` and an integer `k`, find the **maximum average value** of any contiguous subarray of length `k`.

Return the **maximum average** as a floating-point number.

### **Example 1:**
**Input:** 
```
nums = [1,12,-5,-6,50,3], k = 4
```
**Output:** 
```
12.75
```

### **Example 2:**
**Input:** 
```
nums = [5], k = 1
```
**Output:** 
```
5.0
```

### **Constraints:**
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
- `1 <= k <= nums.length`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have **a row of numbers**, and you can pick any `k` consecutive numbers. Your goal is to find the **largest possible average** of these numbers.

For example:
- **[1,12,-5,-6,50,3]** with `k = 4` → The best subarray is `[12,-5,-6,50]`, which gives an **average of 12.75**.

Our goal is to **find the subarray that has the highest average value**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if all numbers are negative?**
   - The average will also be negative, and we need to find the **least negative**.
2. **What if `k` is equal to `nums.length`?**
   - We just return the average of the entire array.
3. **Can we check all subarrays?**
   - No! That would be too slow. Instead, we use a **sliding window**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Calculate the First `k` Elements Sum
```java
int sum = 0;
for (int i = 0; i < k; i++) {
    sum += nums[i];
}
int maxSum = sum;
```

### Step 2: Slide the Window Over the Array
```java
for (int i = k; i < nums.length; i++) {
    sum += nums[i] - nums[i - k];
    maxSum = Math.max(maxSum, sum);
}
```

### Step 3: Return the Maximum Average
```java
return (double) maxSum / k;
```

---

## 🛠️ Writing the Brute Force Solution (O(N*K) Time Complexity) 🚨

```java
public class MaxAverageSubarrayBruteForce {
    public static double findMaxAverage(int[] nums, int k) {
        double maxAvg = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxAvg = Math.max(maxAvg, (double) sum / k);
        }
        return maxAvg;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N*K)`, since we iterate over all possible subarrays.
- **Space Complexity:** `O(1)`, since we use no extra storage.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Sliding Window (O(N) Time, O(1) Space)

```java
public class MaxAverageSubarrayOptimized {
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        
        return (double) maxSum / k;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, since we only use a few extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **sliding window** to efficiently calculate sums of `k` elements."
- "We first calculate the sum of the first `k` elements."
- "Then, we move the window one step at a time, subtracting the outgoing element and adding the incoming one."
- "This reduces the time complexity to `O(N)`, making it efficient."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every subarray, but is too slow (`O(N*K)`)."
- "The **sliding window technique** is optimal because it avoids recomputing sums repeatedly."

---

## 🔥 Final Takeaways
- **Use sliding window to keep track of sums efficiently.**
- **Only update the sum by removing the old element and adding the new one.**
- **Think about edge cases: small arrays, all negative numbers, large `k`.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-average-subarray-i/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fourteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.