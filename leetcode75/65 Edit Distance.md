# Edit Distance

## 📌 Problem Statement

**LeetCode Problem:** [72. Edit Distance](https://leetcode.com/problems/edit-distance/)  
**Difficulty:** Hard  

**Description:**
Given two strings `word1` and `word2`, return the minimum number of operations required to convert `word1` to `word2`.

You have the following three operations permitted on a word:
- Insert a character
- Delete a character
- Replace a character

### **Example 1:**
**Input:** 
```
word1 = "horse", word2 = "ros"
```
**Output:** 
```
3
```
**Explanation:** 
- horse -> rorse (replace 'h' with 'r')
- rorse -> rose (remove 'r')
- rose -> ros (remove 'e')

### **Example 2:**
**Input:** 
```
word1 = "intention", word2 = "execution"
```
**Output:** 
```
5
```
**Explanation:** 
- intention -> inention (remove 't')
- inention -> enention (replace 'i' with 'e')
- enention -> exention (replace 'n' with 'x')
- exention -> exection (replace 'n' with 'c')
- exection -> execution (insert 'u')

### **Constraints:**
- `0 <= word1.length, word2.length <= 500`
- `word1` and `word2` consist of lowercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're playing a word game. You start with one word, like "horse", and you want to change it to another word, like "ros". You're allowed to do three types of moves:
1. Add a new letter anywhere in the word
2. Remove any letter from the word
3. Change any letter to a different letter

Each move counts as one step. The question asks: what's the smallest number of steps needed to change the first word into the second word?

For example, to change "horse" to "ros":
1. Change 'h' to 'r': "horse" -> "rorse" (1 step)
2. Remove 'r': "rorse" -> "rose" (2 steps total)
3. Remove 'e': "rose" -> "ros" (3 steps total)

So the answer is 3.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we systematically find the minimum number of operations?**
   - We can compare characters at each position and decide the best action.
2. **Can we use recursion or dynamic programming?**
   - Yes, dynamic programming is perfect for this problem.
3. **What are the subproblems we're solving?**
   - We need to find the minimum operations to convert prefixes of word1 to prefixes of word2.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a 2D DP array to store minimum operations for converting prefixes
```java
int m = word1.length();
int n = word2.length();
int[][] dp = new int[m + 1][n + 1];
```

### Step 2: Initialize the base cases (converting to/from an empty string)
```java
// To convert word1[0...i] to empty string, delete all characters
for (int i = 0; i <= m; i++) {
    dp[i][0] = i;
}

// To convert empty string to word2[0...j], insert all characters
for (int j = 0; j <= n; j++) {
    dp[0][j] = j;
}
```

### Step 3: Fill the DP array using the recurrence relation
```java
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1]; // No operation needed
        } else {
            dp[i][j] = 1 + Math.min(
                dp[i - 1][j - 1],  // Replace
                Math.min(
                    dp[i - 1][j],  // Delete
                    dp[i][j - 1]   // Insert
                )
            );
        }
    }
}
```

### Step 4: Return the minimum operations required
```java
return dp[m][n];
```

---

## 🛠️ Dynamic Programming Solution

```java
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Create DP array to store minimum operations
        // dp[i][j] represents the minimum operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        
        // To convert word1[0...i] to empty string, delete all characters
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        // To convert empty string to word2[0...j], insert all characters
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Characters don't match, consider all three operations
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1],  // Replace operation
                        Math.min(
                            dp[i - 1][j],  // Delete operation
                            dp[i][j - 1]   // Insert operation
                        )
                    );
                }
            }
        }
        
        // Return the minimum operations required
        return dp[m][n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` where m and n are the lengths of word1 and word2.
- **Space Complexity:** `O(m * n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the previous row's values, we can optimize space:

```java
public class EditDistanceOptimized {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Ensure word1 is the shorter string to save space
        if (m > n) {
            return minDistance(word2, word1);
        }
        
        // Use only two rows for DP
        int[] prevRow = new int[n + 1];
        int[] currRow = new int[n + 1];
        
        // Initialize the first row (base case for empty word1)
        for (int j = 0; j <= n; j++) {
            prevRow[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            // First element of current row is the base case (converting to empty word2)
            currRow[0] = i;
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    currRow[j] = prevRow[j - 1];
                } else {
                    currRow[j] = 1 + Math.min(
                        prevRow[j - 1],  // Replace
                        Math.min(
                            prevRow[j],   // Delete
                            currRow[j - 1] // Insert
                        )
                    );
                }
            }
            
            // Swap rows for next iteration
            int[] temp = prevRow;
            prevRow = currRow;
            currRow = temp;
        }
        
        return prevRow[n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` where m and n are the lengths of word1 and word2.
- **Space Complexity:** `O(min(m, n))` as we only use two rows.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "The Edit Distance problem, also known as Levenshtein Distance, is a classic dynamic programming problem."
- "I defined dp[i][j] as the minimum number of operations needed to convert the first i characters of word1 to the first j characters of word2."
- "For the recurrence relation, there are two cases:
  1. If the current characters match (word1[i-1] == word2[j-1]), we don't need any new operation: dp[i][j] = dp[i-1][j-1]
  2. If they don't match, we take the minimum of three possibilities:
     - Replace: dp[i-1][j-1] + 1
     - Delete: dp[i-1][j] + 1
     - Insert: dp[i][j-1] + 1"
- "The base cases are:
  - dp[i][0] = i (cost to convert word1[0...i] to empty string)
  - dp[0][j] = j (cost to convert empty string to word2[0...j])"
- "For space optimization, I noticed that we only need the current and previous rows, so I reduced the space complexity from O(m*n) to O(min(m,n))."
- "The time complexity is O(m*n) because we need to fill the entire DP table."

---

## 🔥 Final Takeaways
- **Edit Distance is a fundamental problem with applications in spell checking, DNA analysis, and natural language processing.**
- **The key insight is understanding the recurrence relation based on character comparison and the three available operations.**
- **Dynamic programming helps us avoid redundant calculations and efficiently find the minimum operations.**
- **Space optimization is possible by recognizing that we only need two rows of the DP table at any time.**
- **This problem teaches how to handle string transformation problems using dynamic programming.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/edit-distance/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-sixth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.