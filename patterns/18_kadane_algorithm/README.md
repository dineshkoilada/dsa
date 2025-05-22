# Kadane's Algorithm Pattern ⚡

## 📌 Introduction: The Power of Maximum Subarrays

Imagine you're tracking your daily profits and losses in the stock market. You want to find the best period to buy and sell—when your gains are maximized. This is where **Kadane's Algorithm** shines: it finds the subarray with the maximum sum in a single pass, making it a dynamic programming classic!

### 🎬 Real-World Analogies:

1. **Stock Market Profits** 📈
   - Find the streak of days with the highest net gain.
2. **Weather Patterns** 🌦️
   - Find the longest stretch of days with improving weather (maximum temperature increase).
3. **Game High Scores** 🎮
   - Find the best run in a sequence of scores for the highest total.

Kadane's Algorithm is your go-to for:
- 💰 Maximum sum of contiguous subarrays
- 📊 Tracking gains and losses over time
- 🔄 Finding the largest difference or variation in sequences

---

## 🧠 How to Recognize a Kadane's Algorithm Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Maximum Subarray" Clue**
   - "Find the subarray with the largest sum"
2. **The "Contiguous" Hint**
   - The subarray must be contiguous (no skipping elements)
3. **The "Running Total" Signal**
   - You need to track a running sum or product

### 🤔 Essential Questions to Ask:
- Can the array contain negative numbers?
- Is the result allowed to be zero or negative?
- Are there constraints on subarray length?

---

## 🎨 Visual Problem-Solving Framework

### Kadane's Step-by-Step:
```
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Running sum (currentSum):
Start: 0
Step 1: max(-2, 0 + -2) = -2
Step 2: max(1, -2 + 1) = 1
Step 3: max(-3, 1 + -3) = -2
Step 4: max(4, -2 + 4) = 4
Step 5: max(-1, 4 + -1) = 3
Step 6: max(2, 3 + 2) = 5
Step 7: max(1, 5 + 1) = 6 ← maxSum
Step 8: max(-5, 6 + -5) = 1
Step 9: max(4, 1 + 4) = 5

Legend:
- currentSum: running sum at each step
- maxSum: highest value seen so far
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you looking for the maximum sum of a contiguous subarray?
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
- All elements are negative
- Array contains only one element
- Array is empty

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
3. Time complexity is always O(n), and space complexity is O(1).
4. Consider edge cases, such as when all elements are negative or when the array contains only one element.

---

Next, let's dive into the **Floyd Warshall Pattern** for solving all-pairs shortest path problems efficiently!

