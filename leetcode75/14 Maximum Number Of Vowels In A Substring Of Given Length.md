# Maximum Number of Vowels in a Substring of Given Length

## 📌 Problem Statement

**LeetCode Problem:** [1456. Maximum Number of Vowels in a Substring of Given Length](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/)  
**Difficulty:** Medium  

**Description:**
Given a string `s` and an integer `k`, return the **maximum number of vowels** found in any contiguous substring of length `k`.

Vowels include `a, e, i, o, u`.

### **Example 1:**
**Input:** 
```
s = "abciiidef", k = 3
```
**Output:** 
```
3
```

### **Example 2:**
**Input:** 
```
s = "aeiou", k = 2
```
**Output:** 
```
2
```

### **Constraints:**
- `1 <= s.length <= 10^5`
- `s` consists of lowercase English letters.
- `1 <= k <= s.length`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **long word**, and you can pick any `k` consecutive letters. Your goal is to find **the most vowels** in any group of `k` letters.

For example:
- **"abciiidef"**, with `k = 3` → The best choice is **"iii"**, which contains **3 vowels**.
- **"aeiou"**, with `k = 2` → The best choice is **"ae"**, **"ei"**, **"io"**, or **"ou"**, each with **2 vowels**.

Our goal is to **find the substring with the most vowels**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there are no vowels in `s`?**
   - The result will be `0`.
2. **What if `k` is equal to `s.length`?**
   - We count all vowels in `s` and return that number.
3. **Can we check all substrings?**
   - No! That would be too slow. Instead, we use a **sliding window**.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define Vowel Set and Count the First `k` Characters
```java
Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
int vowelCount = 0;
for (int i = 0; i < k; i++) {
    if (vowels.contains(s.charAt(i))) {
        vowelCount++;
    }
}
int maxVowels = vowelCount;
```

### Step 2: Slide the Window Over the String
```java
for (int i = k; i < s.length(); i++) {
    if (vowels.contains(s.charAt(i))) {
        vowelCount++;
    }
    if (vowels.contains(s.charAt(i - k))) {
        vowelCount--;
    }
    maxVowels = Math.max(maxVowels, vowelCount);
}
```

### Step 3: Return the Maximum Count
```java
return maxVowels;
```

---

## 🛠️ Writing the Brute Force Solution (O(N*K) Time Complexity) 🚨

```java
public class MaxVowelsBruteForce {
    public static int maxVowels(String s, int k) {
        int maxVowels = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            int count = 0;
            for (int j = i; j < i + k; j++) {
                if ("aeiou".indexOf(s.charAt(j)) != -1) {
                    count++;
                }
            }
            maxVowels = Math.max(maxVowels, count);
        }
        return maxVowels;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N*K)`, since we check every substring.
- **Space Complexity:** `O(1)`, since we use no extra storage.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Sliding Window (O(N) Time, O(1) Space)

```java
import java.util.*;

public class MaxVowelsOptimized {
    public static int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int maxVowels = 0, vowelCount = 0;
        
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                vowelCount++;
            }
        }
        maxVowels = vowelCount;
        
        for (int i = k; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                vowelCount++;
            }
            if (vowels.contains(s.charAt(i - k))) {
                vowelCount--;
            }
            maxVowels = Math.max(maxVowels, vowelCount);
        }
        
        return maxVowels;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, since we only use a few extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **sliding window** to efficiently count vowels in `k`-length substrings."
- "We first count the vowels in the first `k` letters."
- "Then, we slide the window, updating the count as we go."
- "This reduces the time complexity to `O(N)`, making it efficient."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every substring, but is too slow (`O(N*K)`)."
- "The **sliding window technique** is optimal because it avoids redundant computations."

---

## 🔥 Final Takeaways
- **Use a set to check vowels efficiently.**
- **Use sliding window to avoid redundant calculations.**
- **Think about edge cases: no vowels, all vowels, smallest possible `k`.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

