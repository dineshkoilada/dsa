# Find Pivot Index

## 📌 Problem Statement

**LeetCode Problem:** [724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)  
**Difficulty:** Easy  

**Description:**
Given an array of integers `nums`, return the **pivot index**. The pivot index is the index where the sum of all the numbers **to the left** is equal to the sum of all the numbers **to the right**. If no such index exists, return `-1`. If there are multiple pivot indices, return the **leftmost** one.

### **Example 1:**
**Input:** 
```
nums = [1,7,3,6,5,6]
```
**Output:** 
```
3
```

### **Example 2:**
**Input:** 
```
nums = [1,2,3]
```
**Output:** 
```
-1
```

### **Constraints:**
- `1 <= nums.length <= 10^4`
- `-1000 <= nums[i] <= 1000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **seesaw**, and you want to find a position where both sides balance perfectly. The numbers in the list are like weights on the seesaw. Our goal is to find the **index where the left side and right side are equal**.

For example:
- **[1,7,3,6,5,6]** → The pivot index is `3` because `1+7+3 = 5+6`.
- **[1,2,3]** → No pivot exists since no index balances the sum.

Our goal is to **find the pivot index where left sum = right sum**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there's only one number?**
   - The answer is `0`, since there are no numbers to the left or right.
2. **What if no pivot exists?**
   - We return `-1`.
3. **Can we check all indices one by one?**
   - Yes, but that would be too slow! Instead, we use a **prefix sum approach**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Compute Total Sum of Array
```java
int totalSum = 0;
for (int num : nums) {
    totalSum += num;
}
```

### Step 2: Iterate Through the Array and Check Left Sum
```java
int leftSum = 0;
for (int i = 0; i < nums.length; i++) {
    if (leftSum == totalSum - leftSum - nums[i]) {
        return i;
    }
    leftSum += nums[i];
}
```

### Step 3: If No Pivot is Found, Return `-1`
```java
return -1;
```

---

## 🛠️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
public class FindPivotIndexBruteForce {
    public static int pivotIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0, rightSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }
            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check sums for every index.
- **Space Complexity:** `O(1)`, since we use only variables.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Prefix Sum (O(N) Time, O(1) Space)

```java
public class FindPivotIndexOptimized {
    public static int pivotIndex(int[] nums) {
        int totalSum = 0, leftSum = 0;
        
        for (int num : nums) {
            totalSum += num;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        
        return -1;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, since we only use a few extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We first compute the **total sum** of the array."
- "We then iterate through the array, keeping track of the **left sum**."
- "If at any index, `leftSum == totalSum - leftSum - nums[i]`, that’s the pivot."
- "This ensures we find the pivot index efficiently in `O(N)`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every index but is too slow (`O(N^2)`)."
- "A **prefix sum technique** allows us to efficiently check balances in `O(N)`."

---

## 🔥 Final Takeaways
- **Use prefix sum to efficiently check balance points.**
- **Keep track of left sum and use the total sum for comparisons.**
- **Think about edge cases: no pivot, single number, multiple pivots.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/find-pivot-index/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **nineteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

