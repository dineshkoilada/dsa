# String Compression

## 📌 Problem Statement

**LeetCode Problem:** [443. String Compression](https://leetcode.com/problems/string-compression/)  
**Difficulty:** Medium  

**Description:**
Given an array of characters `chars`, compress it using the following rules:
- Use the **same character order** but replace consecutive repeating characters with the character followed by its count.
- If a character appears **only once**, keep it as is.
- The compressed string should be stored **in the input array `chars`** and return the **new length** of the array.
- The compression should be done **in-place**, using `O(1)` extra space.

### **Example 1:**
**Input:**
```
chars = ["a","a","b","b","c","c","c"]
```
**Output:**
```
6, chars = ["a","2","b","2","c","3"]
```

### **Example 2:**
**Input:**
```
chars = ["a"]
```
**Output:**
```
1, chars = ["a"]
```

### **Example 3:**
**Input:**
```
chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
```
**Output:**
```
4, chars = ["a","b","1","2"]
```

### **Constraints:**
- `1 <= chars.length <= 2000`
- `chars[i]` is a **lowercase English letter**.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **long string** made of repeated letters, like `"aaabbccc"`. Instead of writing every letter, we want to **shorten it** by counting the number of times each letter appears in a row.

For example:
- **"aaabbccc"** → **"a2b2c3"**
- **"abcd"** → **"abcd"** (No repeats, so no compression needed)
- **"aabbbbbbbbbb"** → **"a2b10"**

Our goal is to **modify the given array in-place** and return the **new length** of the compressed string.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if all characters are unique?**
   - The output remains the same as the input.
2. **What if a character appears more than 9 times?**
   - We **store the full number** (e.g., `b12` instead of `b1,2`).
3. **Do we need to return a new array?**
   - No, we modify the given array and return its new length.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int write = 0, read = 0;
```

### Step 2: Iterate through the array and count consecutive characters
```java
while (read < chars.length) {
    char currentChar = chars[read];
    int count = 0;
    while (read < chars.length && chars[read] == currentChar) {
        count++;
        read++;
    }
```

### Step 3: Write the character and its count if greater than 1
```java
chars[write++] = currentChar;
if (count > 1) {
    for (char c : String.valueOf(count).toCharArray()) {
        chars[write++] = c;
    }
}
```

### Step 4: Return the new length
```java
return write;
```

---

## 🛠️ Writing the Brute Force Solution (Not Allowed Due to Extra Space) 🚨

```java
import java.util.*;

public class StringCompressionBruteForce {
    public static String compress(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == currentChar) {
                count++;
                i++;
            }
            result.append(currentChar);
            if (count > 1) {
                result.append(count);
            }
        }
        return result.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the string once.
- **Space Complexity:** `O(N)`, since we use extra space for the new string.

🚨 **This approach does not work in-place, so it's not valid!**

---

## 🚀 Optimized Solution Using Two Pointers (O(N) Time, O(1) Space)

```java
public class StringCompressionOptimized {
    public static int compress(char[] chars) {
        int write = 0, read = 0;
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            while (read < chars.length && chars[read] == currentChar) {
                count++;
                read++;
            }
            
            chars[write++] = currentChar;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        
        return write;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the array once.
- **Space Complexity:** `O(1)`, since we modify the input array in-place.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We **use two pointers**: one to read and count characters, the other to write the compressed version."
- "If a character appears more than once, we append the **character and count**."
- "The solution runs in `O(N)` time and modifies the array in-place."

If the interviewer asks for **alternative approaches**, you can say:
- "A naive approach would use a `StringBuilder`, but it requires extra space."
- "Using a **two-pointer approach** keeps the solution efficient."

---

## 🔥 Final Takeaways
- **Modify the array in-place and return the new length.**
- **Use two pointers: one to read, one to write compressed characters.**
- **Think about edge cases: single letters, long repeats, unique letters.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/string-compression/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **ninth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

