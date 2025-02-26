# Merge Strings Alternately

## 📌 Problem Statement

**LeetCode Problem:** [1768. Merge Strings Alternately](https://leetcode.com/problems/merge-strings-alternately/)  
**Difficulty:** Easy  

**Description:**
You are given two strings `word1` and `word2`. Merge the strings by adding letters in alternating order, starting with `word1`. If one string is longer than the other, append the additional letters onto the end of the merged string.

Return the **merged string**.

### **Example 1:**
**Input:** 
```
word1 = "abc", word2 = "pqr"
```
**Output:** 
```
"apbqcr"
```

### **Example 2:**
**Input:** 
```
word1 = "ab", word2 = "pqrs"
```
**Output:** 
```
"apbqrs"
```

### **Example 3:**
**Input:** 
```
word1 = "a", word2 = "zxcv"
```
**Output:** 
```
"azxcv"
```

### **Constraints:**
- `1 <= word1.length, word2.length <= 100`
- `word1` and `word2` consist of lowercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have **two ribbons**, one is **red** and the other is **blue**. You want to make a single ribbon by weaving them together, taking **one piece from red, then one from blue, and repeating** until you run out of one of them. After that, you just attach the remaining pieces from the longer ribbon.

Now, instead of ribbons, imagine you have two **words** made of letters. Your task is to create a **new word** by picking one letter from the first word, then one from the second word, and repeating this process until both words are merged together.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if one word is longer than the other?**
   - We keep adding the remaining letters after the shorter word finishes.
2. **What if one of the words is empty?**
   - The output should just be the other word.
3. **What if both words are empty?**
   - The output should be an empty string.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use **two pointers** to track positions in both words
```java
int i = 0, j = 0;
```

### Step 2: Start a loop to pick letters one-by-one from both words
```java
while (i < word1.length() || j < word2.length()) {
    if (i < word1.length()) {
        merged.append(word1.charAt(i));
        i++;
    }
    if (j < word2.length()) {
        merged.append(word2.charAt(j));
        j++;
    }
}
```

### Step 3: Return the final merged string
```java
return merged.toString();
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class MergeStrings {
    public static String mergeAlternately(String word1, String word2) {
        String result = "";
        int i = 0, j = 0;
        
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                result += word1.charAt(i);
                i++;
            }
            if (j < word2.length()) {
                result += word2.charAt(j);
                j++;
            }
        }
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n + m)`, where `n` and `m` are the lengths of `word1` and `word2`.
- **Space Complexity:** `O(n + m)`, due to string concatenation.

---

## 🚀 Optimized Solution using StringBuilder

```java
public class MergeStringsOptimized {
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();
        int i = 0, j = 0;
        
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                merged.append(word1.charAt(i));
                i++;
            }
            if (j < word2.length()) {
                merged.append(word2.charAt(j));
                j++;
            }
        }
        return merged.toString();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n + m)`, where `n` and `m` are the lengths of `word1` and `word2`.
- **Space Complexity:** `O(n + m)`, but more efficient since `StringBuilder` avoids unnecessary object creation.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I used **two pointers** to iterate through both strings simultaneously."
- "I **appended** letters from each word alternately until one of them finished."
- "If one word was longer, I **added the remaining letters** at the end."
- "The brute-force solution uses direct string concatenation, which is inefficient."
- "The optimized solution uses `StringBuilder`, making it more memory-efficient."

If the interviewer asks for **alternative approaches**, you can say:
- "We could use **recursion**, but it wouldn’t improve performance and would use more memory."
- "Using `String +` inside the loop is bad because it creates unnecessary string objects, making it **O(n²)** in time complexity."

---

## 🔥 Final Takeaways
- **Think of edge cases before coding!**  
- **Always check if a simple loop can solve the problem before using recursion.**  
- **Optimize space by using `StringBuilder` instead of `String +`.**  
- **Explain your thought process clearly in an interview.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/merge-strings-alternately/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **first problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.