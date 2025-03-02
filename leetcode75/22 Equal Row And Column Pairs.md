# Equal Row and Column Pairs

## 📌 Problem Statement

**LeetCode Problem:** [2352. Equal Row and Column Pairs](https://leetcode.com/problems/equal-row-and-column-pairs/)  
**Difficulty:** Medium  

**Description:**  
Given an `n x n` integer matrix `grid`, return the **number of pairs** `(r, c)` where row `r` and column `c` are identical.

### **Example 1:**
**Input:**  
```
grid = [[3,2,1],
        [1,7,6],
        [2,7,7]]
```
**Output:**  
```
1
```

### **Example 2:**
**Input:**  
```
grid = [[3,1,2,2],
        [1,4,4,5],
        [2,4,2,2],
        [2,4,2,2]]
```
**Output:**  
```
3
```

### **Constraints:**
- `n == grid.length == grid[i].length`
- `1 <= n <= 200`
- `1 <= grid[i][j] <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **table of numbers** and you want to find how many **rows are identical to columns**. We need to count all these matching pairs.

For example:
- **grid = [[3,2,1], [1,7,6], [2,7,7]]** → Only `row 2` and `column 2` are the same.
- **grid = [[3,1,2,2], [1,4,4,5], [2,4,2,2], [2,4,2,2]]** → There are `3` matching row-column pairs.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we compare a row and a column?**
   - Convert both into lists and check if they are equal.
2. **Can we store rows and compare efficiently?**
   - Yes! Using a **HashMap**.
3. **What’s the best way to check all pairs?**
   - Nested loops can work but can be optimized.

👉 We need a solution that runs in **O(N^2)** efficiently.

---

## 🏗️ Writing the Brute Force Solution (O(N^3) Time Complexity) 🚨

```java
import java.util.*;

public class EqualRowColumnPairsBruteForce {
    public static int equalPairs(int[][] grid) {
        int count = 0;
        int n = grid.length;
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                boolean match = true;
                for (int i = 0; i < n; i++) {
                    if (grid[r][i] != grid[i][c]) {
                        match = false;
                        break;
                    }
                }
                if (match) count++;
            }
        }
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^3)`, since we check each row-column pair.
- **Space Complexity:** `O(1)`, since we use only variables.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using HashMap (O(N^2) Time, O(N^2) Space)

```java
import java.util.*;

public class EqualRowColumnPairsOptimized {
    public static int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowMap = new HashMap<>();
        int count = 0;
        
        for (int[] row : grid) {
            String key = Arrays.toString(row);
            rowMap.put(key, rowMap.getOrDefault(key, 0) + 1);
        }
        
        for (int c = 0; c < n; c++) {
            int[] col = new int[n];
            for (int r = 0; r < n; r++) {
                col[r] = grid[r][c];
            }
            
            String key = Arrays.toString(col);
            count += rowMap.getOrDefault(key, 0);
        }
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we store and compare rows and columns.
- **Space Complexity:** `O(N^2)`, for storing row mappings.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We store each **row as a string** in a HashMap."
- "Then, we extract **each column as a string** and check if it exists in the HashMap."
- "This allows an **O(N^2) solution instead of O(N^3)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every pair (`O(N^3)`) but is too slow."
- "Using a HashMap lets us count occurrences efficiently (`O(N^2)`)."

---

## 🔥 Final Takeaways
- **Use HashMap to store row frequency and compare with columns.**
- **Avoid brute-force comparisons for better efficiency.**
- **Optimize nested loops with efficient data structures.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/equal-row-and-column-pairs/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-third problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

