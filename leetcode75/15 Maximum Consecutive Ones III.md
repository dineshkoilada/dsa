# Maximum Consecutive Ones III

## 📌 Problem Statement

**LeetCode Problem:** [1004. Maximum Consecutive Ones III](https://leetcode.com/problems/maximum-consecutive-ones-iii/)  
**Difficulty:** Medium  

**Description:**
Given a binary array `nums` and an integer `k`, return the **maximum number of consecutive `1`s** that can be obtained by flipping at most `k` `0`s.

### **Example 1:**
**Input:** 
```
nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
```
**Output:** 
```
6
```

### **Example 2:**
**Input:** 
```
nums = [0,0,1,1,0,0,1,1,1,0,0,0,1,1,1,1], k = 3
```
**Output:** 
```
10
```

### **Constraints:**
- `1 <= nums.length <= 10^5`
- `nums[i]` is `0` or `1`.
- `0 <= k <= nums.length`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **row of light switches**, where `1` means **ON** and `0` means **OFF**. You can flip **at most `k` switches** from `0` to `1`. Your goal is to get the **longest stretch of `1`s** in a row.

For example:
- **[1,1,1,0,0,0,1,1,1,1,0]**, with `k = 2` → Best choice is flipping two `0`s to get **six `1`s in a row**.

Our goal is to **find the longest sequence of consecutive `1`s after flipping `k` `0`s**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there are no `0`s?**
   - The answer is just the length of `nums`.
2. **What if `k` is `0`?**
   - The answer is simply the longest sequence of existing `1`s.
3. **Should we check every possible flip?**
   - No! That would be too slow. Instead, we use a **sliding window**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int left = 0, right = 0, zeroCount = 0, maxOnes = 0;
```

### Step 2: Expand the Window Until `k` Flips Are Used
```java
while (right < nums.length) {
    if (nums[right] == 0) {
        zeroCount++;
    }
    while (zeroCount > k) {
        if (nums[left] == 0) {
            zeroCount--;
        }
        left++;
    }
    maxOnes = Math.max(maxOnes, right - left + 1);
    right++;
}
```

### Step 3: Return the Maximum Length of Consecutive `1`s
```java
return maxOnes;
```

---

## 🛠️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
public class MaxConsecutiveOnesBruteForce {
    public static int longestOnes(int[] nums, int k) {
        int maxOnes = 0;
        for (int i = 0; i < nums.length; i++) {
            int zeroCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > k) {
                    break;
                }
                maxOnes = Math.max(maxOnes, j - i + 1);
            }
        }
        return maxOnes;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check every subarray.
- **Space Complexity:** `O(1)`, since we use no extra storage.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Sliding Window (O(N) Time, O(1) Space)

```java
public class MaxConsecutiveOnesOptimized {
    public static int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, zeroCount = 0, maxOnes = 0;
        
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxOnes = Math.max(maxOnes, right - left + 1);
            right++;
        }
        
        return maxOnes;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, since we only use a few extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **sliding window** to track at most `k` flips."
- "If we exceed `k` flips, we move `left` until the window is valid again."
- "This ensures we find the longest valid window in `O(N)`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every subarray but is too slow (`O(N^2)`)."
- "The **sliding window technique** is optimal because it efficiently expands and shrinks the range."

---

## 🔥 Final Takeaways
- **Use sliding window to efficiently count `0`s flipped to `1`s.**
- **Expand the window when `zeroCount <= k`, shrink it otherwise.**
- **Think about edge cases: all `1`s, all `0`s, `k = 0`.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-consecutive-ones-iii/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

