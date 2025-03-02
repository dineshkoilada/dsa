# Determine If Two Strings Are Close

## 📌 Problem Statement

**LeetCode Problem:** [1657. Determine If Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close/)  
**Difficulty:** Medium  

**Description:**  
Two strings are considered **close** if we can perform the following operations to make them equal:
1. **Swap any two existing characters** (i.e., transformation is allowed but not addition/removal).
2. **Transform one character into another, provided both exist in the string** (frequency change is allowed, but the set of characters must be the same).

Return `true` if `word1` and `word2` are **close**, otherwise return `false`.

### **Example 1:**
**Input:**  
```
word1 = "abc", word2 = "bca"
```
**Output:**  
```
true
```

### **Example 2:**
**Input:**  
```
word1 = "a", word2 = "aa"
```
**Output:**  
```
false
```

### **Constraints:**
- `1 <= word1.length, word2.length <= 10^5`
- `word1` and `word2` consist of lowercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have two sets of **LEGO bricks**. You can swap bricks around or change some colors, but you **can’t add or remove bricks**. If both sets can be arranged to look exactly the same, they are **close**.

For example:
- **word1 = "abc"**, **word2 = "bca"** → We can swap letters, so they are **close** (`true`).
- **word1 = "a"**, **word2 = "aa"** → We can’t add a letter, so they are **not close** (`false`).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Do both words have the same letters?**
   - If not, return `false`.
2. **Can we rearrange frequencies to match?**
   - If yes, return `true`.
3. **How do we check efficiently?**
   - Using frequency counts and sorting.

👉 We need a solution that runs in **O(N log N) or better**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
import java.util.*;

public class CloseStringsBruteForce {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        
        for (char c : word1.toCharArray()) set1.add(c);
        for (char c : word2.toCharArray()) set2.add(c);
        
        if (!set1.equals(set2)) return false;
        
        Map<Character, Integer> freq1 = new HashMap<>();
        Map<Character, Integer> freq2 = new HashMap<>();
        
        for (char c : word1.toCharArray()) freq1.put(c, freq1.getOrDefault(c, 0) + 1);
        for (char c : word2.toCharArray()) freq2.put(c, freq2.getOrDefault(c, 0) + 1);
        
        List<Integer> list1 = new ArrayList<>(freq1.values());
        List<Integer> list2 = new ArrayList<>(freq2.values());
        
        Collections.sort(list1);
        Collections.sort(list2);
        
        return list1.equals(list2);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N log N)`, due to sorting frequency lists.
- **Space Complexity:** `O(N)`, for storing character counts.

🚨 **Sorting can be slow for large inputs!**

---

## 🚀 Optimized Solution Using Frequency Arrays (O(N) Time, O(1) Space)

```java
import java.util.*;

public class CloseStringsOptimized {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for (char c : word1.toCharArray()) freq1[c - 'a']++;
        for (char c : word2.toCharArray()) freq2[c - 'a']++;
        
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] > 0) != (freq2[i] > 0)) return false;
        }
        
        Arrays.sort(freq1);
        Arrays.sort(freq2);
        
        return Arrays.equals(freq1, freq2);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, as sorting only requires `O(26 log 26) ≈ O(1)` time.
- **Space Complexity:** `O(1)`, since arrays are fixed in size (`26` letters).

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We first check if the two words have the **same characters**."
- "Then, we ensure that their **frequency distributions** match."
- "Instead of sorting, we use a **frequency array for O(N) efficiency**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach compares all permutations (`O(N!)`), which is too slow."
- "Sorting frequency lists works in `O(N log N)`, but `O(N)` solutions exist using arrays."

---

## 🔥 Final Takeaways
- **Use frequency counts to compare character distributions efficiently.**
- **Sorting is useful but avoidable with constant-space frequency arrays.**
- **Optimize brute-force approaches by leveraging HashMaps and arrays.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/determine-if-two-strings-are-close/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-second problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.