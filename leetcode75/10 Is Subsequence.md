# Is Subsequence

## 📌 Problem Statement

**LeetCode Problem:** [392. Is Subsequence](https://leetcode.com/problems/is-subsequence/)  
**Difficulty:** Easy  

**Description:**
Given two strings `s` and `t`, return `true` if `s` is a **subsequence** of `t`, or `false` otherwise.

A subsequence of a string is a sequence of characters that appear in the same relative order, but not necessarily **contiguously**.

### **Example 1:**
**Input:** 
```
s = "abc", t = "ahbgdc"
```
**Output:** 
```
true
```

### **Example 2:**
**Input:** 
```
s = "axc", t = "ahbgdc"
```
**Output:** 
```
false
```

### **Constraints:**
- `0 <= s.length <= 100`
- `0 <= t.length <= 10^4`
- `s` and `t` consist **only** of lowercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **big word** (`t`) and a **small word** (`s`). You need to check if the small word's letters appear **in the same order** inside the big word, **even if there are extra letters in between**.

For example:
- **"abc"** in **"ahbgdc"** → ✅ **Yes**, because `a` → `b` → `c` appear in order.
- **"axc"** in **"ahbgdc"** → ❌ **No**, because `x` is missing.

Our goal is to check **if `s` appears inside `t` in the correct order**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if `s` is empty?**
   - It is always a subsequence of `t`, so we return `true`.
2. **What happens if `t` is empty but `s` is not?**
   - We return `false` because there’s nothing to match.
3. **What if `s` and `t` are the same?**
   - We return `true` because every string is a subsequence of itself.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int sIndex = 0, tIndex = 0;
```

### Step 2: Iterate Through `t` and Match Characters
```java
while (sIndex < s.length() && tIndex < t.length()) {
    if (s.charAt(sIndex) == t.charAt(tIndex)) {
        sIndex++;
    }
    tIndex++;
}
```

### Step 3: If `sIndex` Reaches `s.length()`, `s` is a Subsequence
```java
return sIndex == s.length();
```

---

## 🛠️ Writing the Brute Force Solution (Not Optimal) 🚨

```java
public class IsSubsequenceBruteForce {
    public static boolean isSubsequence(String s, String t) {
        int index = -1;
        
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N * M)`, where `N` is the length of `s` and `M` is the length of `t`.
- **Space Complexity:** `O(1)`, since we only use a few variables.

🚨 **This approach is slow for large inputs!**

---

## 🚀 Optimized Solution Using Two Pointers (O(N) Time, O(1) Space)

```java
public class IsSubsequenceOptimized {
    public static boolean isSubsequence(String s, String t) {
        int sIndex = 0, tIndex = 0;
        
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        
        return sIndex == s.length();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(M)`, since we iterate through `t` once.
- **Space Complexity:** `O(1)`, since we only use two pointers.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two pointers**, one for `s` and one for `t`."
- "As we iterate through `t`, we check if the current character matches `s[sIndex]`."
- "If all characters of `s` appear in order, we return `true`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach searches for each letter individually, but it's too slow."
- "Using a **two-pointer approach** keeps it efficient at `O(M)`."

---

## 🔥 Final Takeaways
- **Use two pointers to check character order efficiently.**
- **Think about edge cases: empty `s`, empty `t`, identical strings.**
- **Brute-force is too slow; `O(M)` is the best approach.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/is-subsequence/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **eleventh problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.