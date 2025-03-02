# Removing Stars From a String

## 📌 Problem Statement

**LeetCode Problem:** [2390. Removing Stars From a String](https://leetcode.com/problems/removing-stars-from-a-string/)  
**Difficulty:** Medium  

**Description:**  
You are given a string `s` containing lowercase English letters and `'*'` characters. Your task is to remove all `'*'` characters in such a way that each `'*'` removes the closest **non-star** character before it. Return the final string after processing.

### **Example 1:**
**Input:**  
```
s = "leet**cod*e"
```
**Output:**  
```
"lecoe"
```

### **Example 2:**
**Input:**  
```
s = "erase*****"
```
**Output:**  
```
""
```

### **Constraints:**
- `1 <= s.length <= 10^5`
- `s` consists of lowercase English letters and `'*'`.
- The input will always be valid (i.e., there will be at least as many non-star characters as stars).

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **magic eraser** that can erase the **last letter** you wrote when you see a `'*'`. Your task is to apply this rule and find what remains after all erasures.

For example:
- **s = "leet**cod*e"** → The first `'*'` removes `t`, the second removes `e`, and `'*'` removes `d`, leaving **"lecoe"**.
- **s = "erase*****"** → Every letter is erased, so the final result is an **empty string**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we efficiently remove characters?**
   - We can use a **Stack** to keep track of characters.
2. **What happens when we see a `'*'`?**
   - We remove the **top character** from the stack.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using a **Stack or StringBuilder**.

👉 We need a solution that runs in **O(N) time** with **O(N) space**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
public class RemovingStarsBruteForce {
    public static String removeStars(String s) {
        StringBuilder sb = new StringBuilder(s);
        
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '*') {
                sb.deleteCharAt(i);
                sb.deleteCharAt(i - 1);
                i -= 2;
            }
        }
        
        return sb.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since deleting characters in a StringBuilder is `O(N)`, leading to `O(N^2)` overall.
- **Space Complexity:** `O(N)`, for storing the string.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Stack (O(N) Time, O(N) Space)

```java
import java.util.*;

public class RemovingStarsOptimized {
    public static String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '*') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the string once.
- **Space Complexity:** `O(N)`, for storing the final string.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We iterate through the string and use a **StringBuilder** as a stack."
- "When we see a `'*'`, we **remove the last character** added."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach modifies the string (`O(N^2)`) but is too slow."
- "Using a **Stack or StringBuilder** gives `O(N)` efficiency."

---

## 🔥 Final Takeaways
- **Use a Stack (or StringBuilder) to efficiently remove characters.**
- **Avoid nested loops when modifying strings dynamically.**
- **Optimize brute-force approaches by using data structures like Stack.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/removing-stars-from-a-string/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-fourth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

