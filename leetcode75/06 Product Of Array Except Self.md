# Product of Array Except Self

## 📌 Problem Statement

**LeetCode Problem:** [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)  
**Difficulty:** Medium  

**Description:**
Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the **product of all the elements of `nums` except `nums[i]`**.

The solution must run in **O(N) time complexity** and **cannot use division**.

### **Example 1:**
**Input:** 
```
nums = [1,2,3,4]
```
**Output:** 
```
[24,12,8,6]
```

### **Example 2:**
**Input:** 
```
nums = [-1,1,0,-3,3]
```
**Output:** 
```
[0,0,9,0,0]
```

### **Constraints:**
- `2 <= nums.length <= 10^5`
- `-30 <= nums[i] <= 30`
- The product of any prefix or suffix of `nums` is **guaranteed** to fit in a **32-bit integer**.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **list of numbers**, and for each number, you want to find the product of **all the other numbers** except itself.

For example, if you have **[1,2,3,4]**, the answer should be **[24,12,8,6]** because:
- For **1**, the product of `[2,3,4]` is **24**.
- For **2**, the product of `[1,3,4]` is **12**.
- For **3**, the product of `[1,2,4]` is **8**.
- For **4**, the product of `[1,2,3]` is **6**.

🚨 **Rule:** We cannot use **division** (so we can’t just multiply everything and divide by `nums[i]`).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there is a zero in the array?**
   - All products except the zero’s position will be **zero**.
2. **What happens if there are multiple zeros?**
   - The entire output array will be **zeros**.
3. **Can we do it without using extra space?**
   - Yes! We can **reuse** the output array to save space.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create an array to store the left product for each element
```java
int[] output = new int[nums.length];
output[0] = 1;
for (int i = 1; i < nums.length; i++) {
    output[i] = output[i - 1] * nums[i - 1];
}
```

### Step 2: Traverse from right to multiply by the right product
```java
int right = 1;
for (int i = nums.length - 1; i >= 0; i--) {
    output[i] *= right;
    right *= nums[i];
}
```

### Step 3: Return the output array
```java
return output;
```

---

## 🛠️ Writing the Brute Force Solution (Not Allowed Due to `O(N^2)`) 🚨

```java
public class ProductArrayBruteForce {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since for each number we iterate the entire array.
- **Space Complexity:** `O(N)`, as we store the output separately.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution using Left and Right Products (O(N) Time, O(1) Space)

```java
public class ProductArrayOptimized {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }
        
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] *= right;
            right *= nums[i];
        }
        
        return output;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate the array twice.
- **Space Complexity:** `O(1)`, since we modify the output array directly.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We **first calculate** the left products and store them in an array."
- "Then, we **iterate backward** to multiply each value by the right product."
- "This ensures we don’t use division while maintaining `O(N)` time complexity."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force solution would take `O(N^2)`, which is too slow."
- "Using division would be simpler but violates the problem constraints."
- "This optimized approach balances efficiency and memory use."

---

## 🔥 Final Takeaways
- **Avoid division to comply with constraints.**
- **Use left and right product passes to maintain `O(N)` time complexity.**
- **Minimize space by modifying the output array directly.**
- **Think about edge cases: multiple zeros, single-element arrays, negative numbers.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/product-of-array-except-self/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventh problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.