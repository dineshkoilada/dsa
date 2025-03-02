# Unique Number of Occurrences

## 📌 Problem Statement

**LeetCode Problem:** [1207. Unique Number of Occurrences](https://leetcode.com/problems/unique-number-of-occurrences/)  
**Difficulty:** Easy  

**Description:**  
Given an array of integers `arr`, return `true` if the number of occurrences of each value in the array is **unique**, or `false` otherwise.

### **Example 1:**
**Input:**  
```
arr = [1,2,2,1,1,3]
```
**Output:**  
```
true
```

### **Example 2:**
**Input:**  
```
arr = [1,2]
```
**Output:**  
```
false
```

### **Constraints:**
- `1 <= arr.length <= 1000`
- `-1000 <= arr[i] <= 1000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **bag of candies**, and some candies appear more times than others. We need to check if the number of times each candy appears is **unique**. If two different candies appear the same number of times, we return `false`. Otherwise, we return `true`.

For example:
- **arr = [1,2,2,1,1,3]** → `1` appears `3` times, `2` appears `2` times, and `3` appears `1` time. Since all counts (`3,2,1`) are unique, we return `true`.
- **arr = [1,2]** → Both numbers appear once (`1,1`), so we return `false`.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we count occurrences of each number?**
   - We can use a **HashMap**.
2. **How do we check if the counts are unique?**
   - We can store them in a **HashSet**.
3. **Can we do this efficiently?**
   - Yes! Using `O(N)` time complexity.

👉 We need a solution that runs in **O(N)** and uses **O(N) space**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
import java.util.*;

public class UniqueOccurrencesBruteForce {
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> counts = new ArrayList<>(countMap.values());
        
        for (int i = 0; i < counts.size(); i++) {
            for (int j = i + 1; j < counts.size(); j++) {
                if (counts.get(i).equals(counts.get(j))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check every count against every other count.
- **Space Complexity:** `O(N)`, for storing counts.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using HashMap and HashSet (O(N) Time, O(N) Space)

```java
import java.util.*;

public class UniqueOccurrencesOptimized {
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> countSet = new HashSet<>(countMap.values());
        
        return countMap.size() == countSet.size();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the array once.
- **Space Complexity:** `O(N)`, for storing occurrences and unique counts.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **HashMap** to count occurrences of each number."
- "Then, we store the counts in a **HashSet** and check if any counts repeat."
- "This ensures an **O(N) solution with O(N) space complexity**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every count (`O(N^2)`) but is too slow."
- "Sorting first (`O(N log N)`) and checking adjacent counts is an option, but not optimal."
- "Using **HashMap and HashSet** is the best approach (`O(N)`)."

---

## 🔥 Final Takeaways
- **Use HashMap to count occurrences efficiently.**
- **Use HashSet to check uniqueness of occurrences.**
- **Optimize brute force solutions using data structures like HashMap.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/unique-number-of-occurrences/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-first problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

