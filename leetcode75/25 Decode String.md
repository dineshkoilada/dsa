# Decode String

## 📌 Problem Statement

**LeetCode Problem:** [394. Decode String](https://leetcode.com/problems/decode-string/)  
**Difficulty:** Medium  

**Description:**  
Given an encoded string, return its decoded version. The encoding rule is: `k[encoded_string]`, meaning that the encoded string inside the brackets is repeated exactly `k` times. You may assume that `k` is always a **positive integer**.

### **Example 1:**
**Input:**  
```
s = "3[a]2[bc]"
```
**Output:**  
```
"aaabcbc"
```

### **Example 2:**
**Input:**  
```
s = "3[a2[c]]"
```
**Output:**  
```
"accaccacc"
```

### **Constraints:**
- `1 <= s.length <= 30,000`
- `s` consists of digits, lowercase English letters, and square brackets `[]`.
- It is **guaranteed** that `s` is a **valid** encoded string.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **magic book** where a number before a word tells you how many times to repeat it.
- **"3[a]"** → means **"aaa"**.
- **"2[bc]"** → means **"bcbc"**.
- **"3[a2[c]]"** → means **"accaccacc"**, since `2[c]` means `"cc"`, and `3[aCC]` gives `"accaccacc"`.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we handle nested brackets?**
   - Use a **Stack** to store characters and numbers.
2. **How do we repeat strings efficiently?**
   - Store substrings until a `]` is encountered.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using a **Stack-based approach**.

👉 We need a solution that runs in **O(N) time** with **O(N) space**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
public class DecodeStringBruteForce {
    public static String decodeString(String s) {
        while (s.contains("[")) {
            int lastOpen = s.lastIndexOf("[");
            int firstClose = s.indexOf("]", lastOpen);
            
            String repeatPart = s.substring(lastOpen + 1, firstClose);
            int repeatTimes = 0, i = lastOpen - 1;
            while (i >= 0 && Character.isDigit(s.charAt(i))) {
                repeatTimes = repeatTimes * 10 + (s.charAt(i) - '0');
                i--;
            }
            
            StringBuilder sb = new StringBuilder(s.substring(0, i + 1));
            sb.append(repeatPart.repeat(repeatTimes));
            sb.append(s.substring(firstClose + 1));
            s = sb.toString();
        }
        return s;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since each expansion creates a new string.
- **Space Complexity:** `O(N)`, for storing intermediate strings.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Stack (O(N) Time, O(N) Space)

```java
import java.util.*;

public class DecodeStringOptimized {
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                stringStack.push(currentString);
                count = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                StringBuilder decodedString = stringStack.pop();
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(c);
            }
        }
        
        return currentString.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since each character is processed once.
- **Space Complexity:** `O(N)`, for storing stack data.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two stacks**: one for numbers and one for substrings."
- "When we see a `]`, we **pop** the last substring and repeat it the required number of times."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N^2)`) repeatedly replaces substrings but is too slow."
- "Using a **Stack-based approach** is the best (`O(N)`)."

---

## 🔥 Final Takeaways
- **Use Stack to efficiently decode nested structures.**
- **Avoid repeated string manipulations to improve efficiency.**
- **Optimize brute-force approaches using data structures like Stack.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/decode-string/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-sixth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

