# Container With Most Water

## 📌 Problem Statement

**LeetCode Problem:** [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)  
**Difficulty:** Medium  

**Description:**
You are given an integer array `height` where `height[i]` represents the height of a vertical line at index `i`. Find two lines that, together with the x-axis, form a container that holds the **most water**.

Return the **maximum amount of water** the container can store.

### **Example 1:**
**Input:** 
```
height = [1,8,6,2,5,4,8,3,7]
```
**Output:** 
```
49
```

### **Example 2:**
**Input:** 
```
height = [1,1]
```
**Output:** 
```
1
```

### **Constraints:**
- `2 <= height.length <= 10^5`
- `0 <= height[i] <= 10^4`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have **sticks** of different heights lined up on the ground. You want to pick **two sticks** and pour water between them. The amount of water depends on the **shorter stick** because the water will spill over if one is taller than the other.

For example:
- **[1,8,6,2,5,4,8,3,7]** → The best choice is **sticks at index 1 and 8** to hold **49 units of water**.

Our goal is to **find the two sticks** that can hold the most water.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if all sticks are the same height?**
   - The best solution is picking the **two farthest sticks**.
2. **What if there are only two sticks?**
   - We just calculate the water between them.
3. **Should we check every possible pair?**
   - No! That would be too slow. We need a **smarter approach**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int left = 0, right = height.length - 1;
```

### Step 2: Calculate the Area and Move the Pointer
```java
while (left < right) {
    int width = right - left;
    int area = width * Math.min(height[left], height[right]);
```

### Step 3: Move the Pointer That Limits the Water
```java
if (height[left] < height[right]) {
    left++;
} else {
    right--;
}
```

### Step 4: Return the Maximum Area
```java
return maxArea;
```

---

## 🛠️ Writing the Brute Force Solution (Not Efficient) 🚨

```java
public class ContainerWithMostWaterBruteForce {
    public static int maxArea(int[] height) {
        int maxWater = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int water = (j - i) * Math.min(height[i], height[j]);
                maxWater = Math.max(maxWater, water);
            }
        }
        return maxWater;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check every pair.
- **Space Complexity:** `O(1)`, since we use no extra space.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Two Pointers (O(N) Time, O(1) Space)

```java
public class ContainerWithMostWaterOptimized {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            int width = right - left;
            int water = width * Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, water);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we only iterate once.
- **Space Complexity:** `O(1)`, since we use two pointers.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two pointers**, one at the start and one at the end."
- "We calculate the water trapped between them."
- "We move the pointer pointing to the **smaller height** to find a taller stick."
- "This ensures we check the best possible containers in `O(N)` time."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks all pairs, but is too slow (`O(N^2)`)."
- "A **two-pointer approach** is optimal because it moves towards better solutions faster."

---

## 🔥 Final Takeaways
- **Water is limited by the shorter stick!**
- **Use a two-pointer approach to find the best solution efficiently.**
- **Think about edge cases: small inputs, all equal heights, varying heights.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/container-with-most-water/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twelfth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.