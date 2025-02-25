# Kadane's Algorithm Pattern

## 🎯 Introduction

Imagine you're analyzing stock prices and want to find the best time to buy and sell to maximize your profit. This requires finding the subarray (or subsequence) with the maximum sum. **Kadane's Algorithm** is a dynamic programming technique that helps solve such problems efficiently by iterating through the array and maintaining a running maximum.

The Kadane's Algorithm Pattern is particularly useful for:
- Finding the maximum sum of contiguous subarrays
- Solving problems involving gains and losses over time
- Finding the largest difference or variation in numerical sequences

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you required to find the largest sum of a contiguous subarray?
   - Does the problem involve tracking running totals or variations?

2. **Ask Clarifying Questions:**
   - Can the array contain negative numbers?
   - Is the result allowed to be zero or negative?
   - Are there any constraints on subarray length?

3. **Identify Clues for Using Kadane’s Algorithm:**
   - The problem involves finding a subarray with the maximum sum.
   - You need an efficient solution for large arrays.

4. **Predicting if Kadane's Algorithm Is Applicable:**
   - Does the problem involve finding a contiguous subarray with maximum sum?
   - Are negative numbers involved?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you looking for the maximum sum of a subarray?
- Are negative sums valid?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Is the entire array negative?
- Should the subarray length be at least one?

### ✅ **3. Identify Base Cases**
- Initialize `max_sum` as the smallest possible integer.
- Initialize `current_sum` as 0.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function kadane(arr):
    max_sum = -infinity
    current_sum = 0
    for element in arr:
        current_sum = max(element, current_sum + element)
        max_sum = max(max_sum, current_sum)
    return max_sum
```

### ✅ **5. Write the Code Skeleton**
```java
public class KadaneAlgorithm {
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int num : nums) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- All elements are negative.
- Array contains only one element.
- Array is empty.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation              | Time Complexity | Space Complexity |
|------------------------|-----------------|------------------|
| Finding max subarray   | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Each element is visited once.
- **Space Complexity:** Only a few variables are used, irrespective of array size.

---

## 📚 Example 1: Easy Problem - Maximum Sum Subarray

**Problem:**
Find the maximum sum of a contiguous subarray.

**Input:** `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

**Expected Output:** `6`

**Explanation:** Subarray `[4, -1, 2, 1]` has the largest sum.

### 🔑 **Solution Steps**
1. Initialize `maxSum` to smallest integer value.
2. Loop through each element and update running sums.
3. Keep track of the global maximum.

### ✅ **Code:**
```java
public class MaxSumSubarray {
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int num : nums) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — One pass through the array.
- **Space:** O(1) — Only a few variables used.

---

## 📚 Example 2: Medium Problem - Maximum Product Subarray

**Problem:**
Find the contiguous subarray within an array that has the largest product.

**Input:** `nums = [2, 3, -2, 4]`

**Expected Output:** `6`

**Explanation:** Subarray `[2, 3]` has the largest product.

### 🔑 **Solution Steps**
1. Track both the maximum and minimum product ending at the current index.
2. Update global maximum after considering the current element.

### ✅ **Code:**
```java
public class MaxProductSubarray {
    public static int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = maxProduct;
            maxProduct = Math.max(nums[i], Math.max(maxProduct * nums[i], minProduct * nums[i]));
            minProduct = Math.min(nums[i], Math.min(temp * nums[i], minProduct * nums[i]));
            result = Math.max(result, maxProduct);
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — One pass through the array.
- **Space:** O(1) — Only a few variables used.

---

## 📚 Example 3: Hard Problem - Maximum Sum Circular Subarray

**Problem:**
Find the maximum sum of a circular subarray.

**Input:** `nums = [1, -2, 3, -2]`

**Expected Output:** `3`

**Explanation:** The non-circular subarray `[3]` has the maximum sum.

### 🔑 **Solution Steps**
1. Use Kadane’s Algorithm to find the max subarray sum.
2. Compute the total sum of the array.
3. Use a modified Kadane’s Algorithm to find the minimum subarray sum.
4. The result is the maximum of `maxSubarraySum` and `totalSum - minSubarraySum`.

### ✅ **Code:**
```java
public class MaxCircularSubarray {
    public static int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int currentMax = 0;
        int minSum = Integer.MAX_VALUE;
        int currentMin = 0;

        for (int num : nums) {
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
            totalSum += num;
        }

        return maxSum > 0 ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Single pass through the array.
- **Space:** O(1) — Only a few variables used.

---

## 📚 Key Takeaways

1. Use Kadane’s Algorithm for efficiently finding the maximum sum of a contiguous subarray.
2. Kadane’s variations can handle problems like maximum product subarrays and circular arrays.
3. Time complexity is always \( O(n) \), and space complexity is \( O(1) \).
4. Consider edge cases, such as when all elements are negative or when the array contains only one element.

---

Next, let's dive into the **Floyd Warshall Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

