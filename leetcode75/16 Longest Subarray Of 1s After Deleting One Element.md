# Longest Subarray of 1's After Deleting One Element

## 📌 Problem Statement

**LeetCode Problem:** [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/)  
**Difficulty:** Medium  

**Description:**
Given a binary array `nums`, return the **length of the longest contiguous subarray** of `1`s after **deleting exactly one element**.

### **Example 1:**
**Input:** 
```
nums = [1,1,0,1,1,1]
```
**Output:** 
```
5
```

### **Example 2:**
**Input:** 
```
nums = [1,1,1,1,1]
```
**Output:** 
```
4
```

### **Constraints:**
- `1 <= nums.length <= 10^5`
- `nums[i]` is `0` or `1`.
- There is at least one `0` in `nums`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **row of light switches**, where `1` means **ON** and `0` means **OFF**. You are allowed to **delete exactly one switch** (a `0`). Your goal is to get the **longest stretch of `1`s** in a row.

For example:
- **[1,1,0,1,1,1]** → Best choice is deleting `0`, which results in **five `1`s in a row**.
- **[1,1,1,1,1]** → Since we must delete one element, the best we can do is **four `1`s**.

Our goal is to **find the longest sequence of consecutive `1`s after deleting exactly one `0`**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there’s only one `0`?**
   - We just delete it and return the count of `1`s.
2. **What if all elements are `1`s?**
   - Since we **must delete one element**, we return `nums.length - 1`.
3. **Should we check every possible deletion?**
   - No! That would be too slow. Instead, we use a **sliding window**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int left = 0, right = 0, zeroCount = 0, maxOnes = 0;
```

### Step 2: Expand the Window Until More Than One `0` is Found
```java
while (right < nums.length) {
    if (nums[right] == 0) {
        zeroCount++;
    }
    while (zeroCount > 1) {
        if (nums[left] == 0) {
            zeroCount--;
        }
        left++;
    }
    maxOnes = Math.max(maxOnes, right - left);
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
public class LongestSubarrayOnesBruteForce {
    public static int longestSubarray(int[] nums) {
        int maxOnes = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            boolean deleted = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == 0) {
                    if (deleted) break;
                    deleted = true;
                } else {
                    count++;
                }
            }
            maxOnes = Math.max(maxOnes, count);
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
public class LongestSubarrayOnesOptimized {
    public static int longestSubarray(int[] nums) {
        int left = 0, right = 0, zeroCount = 0, maxOnes = 0;
        
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxOnes = Math.max(maxOnes, right - left);
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
- "We use a **sliding window** to track at most one `0`."
- "If we find more than one `0`, we move `left` until we only have one `0`."
- "This ensures we find the longest valid window in `O(N)`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every subarray but is too slow (`O(N^2)`)."
- "The **sliding window technique** is optimal because it efficiently expands and shrinks the range."

---

## 🔥 Final Takeaways
- **Use sliding window to efficiently track `0`s and `1`s.**
- **Expand the window while `zeroCount <= 1`, shrink otherwise.**
- **Think about edge cases: all `1`s, one `0`, alternating numbers.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventeenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

