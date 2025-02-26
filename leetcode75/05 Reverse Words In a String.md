# Reverse Words in a String

## 📌 Problem Statement

**LeetCode Problem:** [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/)  
**Difficulty:** Medium  

**Description:**
Given an input string `s`, reverse the order of the words.

A **word** is defined as a sequence of **non-space** characters. The words in `s` will be separated by at least one space.

Return a string that contains the words in **reverse order** while ensuring that multiple spaces between words are reduced to a **single space**.

### **Example 1:**
**Input:**
```
s = "the sky is blue"
```
**Output:**
```
"blue is sky the"
```

### **Example 2:**
**Input:**
```
s = "  hello world  "
```
**Output:**
```
"world hello"
```

### **Constraints:**
- `1 <= s.length <= 10^4`
- `s` contains **English letters** (upper and lowercase), digits, and spaces.
- There are **at least one** word in `s`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **sentence** written on a board, and you want to **rearrange the words** in reverse order while making sure there are no **extra spaces**.

For example:
- **"the sky is blue"** → **"blue is sky the"**
- **"  hello world  "** → **"world hello"** (extra spaces removed)

We need to **preserve words**, remove **extra spaces**, and **reverse their order**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there are multiple spaces between words?**
   - We need to remove them and keep only **one space** between words.
2. **What happens if there are leading or trailing spaces?**
   - We remove them before reversing the words.
3. **What if there is only one word?**
   - The word stays the same, but we should remove extra spaces.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Trim spaces and split words
```java
String[] words = s.trim().split("\\s+");
```

### Step 2: Reverse the array of words
```java
Collections.reverse(Arrays.asList(words));
```

### Step 3: Join words back into a single string
```java
return String.join(" ", words);
```

---

## 🛠️ Writing the Brute Force Solution

```java
import java.util.*;

public class ReverseWords {
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the length of the string (`split()` and `join()` take `O(N)`).
- **Space Complexity:** `O(N)`, since we store words separately.

---

## 🚀 Optimized Solution using StringBuilder

```java
public class ReverseWordsOptimized {
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') i--; // Skip trailing spaces
            int j = i;
            while (i >= 0 && s.charAt(i) != ' ') i--; // Find word start
            
            if (sb.length() > 0) sb.append(' ');
            sb.append(s.substring(i + 1, j + 1));
        }
        
        return sb.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the string once.
- **Space Complexity:** `O(N)`, for storing the result in `StringBuilder`.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "First, we **trim** leading and trailing spaces."
- "We **split** the string into words while handling multiple spaces."
- "We **reverse** the words and join them back together."
- "For optimization, we use **StringBuilder** to avoid unnecessary space usage."

If the interviewer asks for **alternative approaches**, you can say:
- "Using `String.split()` is simple but uses extra memory."
- "Using `StringBuilder` is more efficient as it constructs the reversed sentence directly."

---

## 🔥 Final Takeaways
- **Remove extra spaces before reversing.**
- **Use `split()` for simplicity, but `StringBuilder` for efficiency.**
- **Think about edge cases: multiple spaces, single-word input, and empty strings.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/reverse-words-in-a-string/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

