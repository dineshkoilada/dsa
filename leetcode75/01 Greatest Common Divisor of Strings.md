# Greatest Common Divisor of Strings

## 📌 Problem Statement

**LeetCode Problem:** [1071. Greatest Common Divisor of Strings](https://leetcode.com/problems/greatest-common-divisor-of-strings/)  
**Difficulty:** Easy  

**Description:**
Given two strings `str1` and `str2`, return the **largest string `X`** such that `X` is a **prefix** of both `str1` and `str2`, and `X` can be **repeated** some number of times to obtain both `str1` and `str2`.

### **Example 1:**
**Input:**
```
str1 = "ABCABC", str2 = "ABC"
```
**Output:**
```
"ABC"
```

### **Example 2:**
**Input:**
```
str1 = "ABABAB", str2 = "ABAB"
```
**Output:**
```
"AB"
```

### **Example 3:**
**Input:**
```
str1 = "LEET", str2 = "CODE"
```
**Output:**
```
""
```

### **Constraints:**
- `1 <= str1.length, str2.length <= 1000`
- `str1` and `str2` consist of uppercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have **two strings made of LEGO bricks**, and you want to find the **biggest LEGO piece** that can build both strings by repeating itself. That biggest piece is the **GCD (Greatest Common Divisor) of Strings**.

For example, if you have:
- "ABCABC" (6 bricks)
- "ABC" (3 bricks)

The biggest LEGO piece that can repeat and form both is **"ABC"**.

If two LEGO strings don’t fit together, the answer is just an **empty string** ("").

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if the two strings are exactly the same?**
   - The answer is just that string itself.
2. **What happens if they share no common pattern?**
   - The answer should be an empty string.
3. **Can a smaller repeating unit be used to construct both strings?**
   - Yes! That’s what we’re trying to find.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Check if the strings share a common pattern
```java
if (!(str1 + str2).equals(str2 + str1)) return "";
```

### Step 2: Find the Greatest Common Divisor (GCD) of the lengths
```java
int gcdLength = gcd(str1.length(), str2.length());
```

### Step 3: Return the prefix of `str1` up to `gcdLength`
```java
return str1.substring(0, gcdLength);
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class GCDOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the sum of both string lengths (concatenation check).
- **Space Complexity:** `O(1)`, as we only use a few integer variables.

---

## 🚀 Optimized Solution using GCD

```java
public class GCDOfStringsOptimized {
    public static String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }
    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the sum of the string lengths.
- **Space Complexity:** `O(1)`, as it only uses a few variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I first check if `str1 + str2` equals `str2 + str1`. If not, there's no common divisor, so return `""`."
- "If they are valid for division, I compute the `gcd` of their lengths."
- "The prefix of `str1` up to the `gcd` length is the answer."
- "Using `gcd()` function ensures we efficiently find the largest repeating unit."

If the interviewer asks for **alternative approaches**, you can say:
- "We could check all possible prefixes and see which one can form both strings, but that would be slower."
- "The optimized approach leverages the mathematical `gcd` function for efficiency."

---

## 🔥 Final Takeaways
- **Understanding pattern repetition is key!**
- **The GCD trick helps us find the longest repeating unit.**
- **Using `gcd()` makes the solution efficient.**
- **Always check if concatenated versions are equal before proceeding.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/greatest-common-divisor-of-strings/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **second problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

