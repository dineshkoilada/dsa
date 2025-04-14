# Longest Common Subsequence

## 📌 Problem Statement

**LeetCode Problem:** [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)  
**Difficulty:** Medium  

**Description:**
Given two strings `text1` and `text2`, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A **subsequence** of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

- For example, "ace" is a subsequence of "abcde".

A **common subsequence** of two strings is a subsequence that is common to both strings.

### **Example 1:**
**Input:** 
```
text1 = "abcde", text2 = "ace"
```
**Output:** 
```
3
```
**Explanation:** 
The longest common subsequence is "ace" and its length is 3.

### **Example 2:**
**Input:** 
```
text1 = "abc", text2 = "abc"
```
**Output:** 
```
3
```
**Explanation:** 
The longest common subsequence is "abc" and its length is 3.

### **Example 3:**
**Input:** 
```
text1 = "abc", text2 = "def"
```
**Output:** 
```
0
```
**Explanation:** 
There is no such common subsequence, so the result is 0.

### **Constraints:**
- `1 <= text1.length, text2.length <= 1000`
- `text1` and `text2` consist of only lowercase English characters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have two sentences. You need to find the longest sequence of letters that appears in both sentences in the same order (but not necessarily consecutive).

For example:
- First sentence: "abcde"
- Second sentence: "ace"

If we look carefully, we can find "a", "c", and "e" in both sentences, and they appear in the same order. So "ace" is a common subsequence with length 3.

Remember, we don't need the letters to be next to each other - we just need them to appear in the same order in both sentences.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How can we systematically find the longest common subsequence?**
   - We can compare characters one by one and build the solution.
2. **What happens when characters match or don't match?**
   - If they match, we can include them in our subsequence.
   - If they don't match, we need to decide which string to advance.
3. **Can we use our previous calculations to solve larger subproblems?**
   - Yes, this is perfect for dynamic programming!

👉 These insights lead us to a dynamic programming approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a 2D DP array
```java
int m = text1.length();
int n = text2.length();
int[][] dp = new int[m + 1][n + 1];
```

### Step 2: Fill the DP array using the recurrence relation
```java
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
}
```

### Step 3: Return the result
```java
return dp[m][n];
```

---

## 🛠️ Dynamic Programming Solution

```java
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // Create DP array
        // dp[i][j] represents the length of LCS for text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match, add 1 to the LCS length
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Characters don't match, take the maximum from either skipping a character 
                    // in text1 or text2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` where m and n are the lengths of text1 and text2.
- **Space Complexity:** `O(m * n)` for the DP array.

---

## 🔄 Space-Optimized Solution

We can optimize space by using only two rows:

```java
public class LongestCommonSubsequenceOptimized {
    public int longestCommonSubsequence(String text1, String text2) {
        // Ensure text1 is shorter for efficiency
        if (text1.length() > text2.length()) {
            return longestCommonSubsequence(text2, text1);
        }
        
        int m = text1.length();
        int n = text2.length();
        
        // We only need two rows
        int[] prevRow = new int[n + 1];
        int[] currRow = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    currRow[j] = prevRow[j - 1] + 1;
                } else {
                    currRow[j] = Math.max(prevRow[j], currRow[j - 1]);
                }
            }
            
            // Swap rows for next iteration
            int[] temp = prevRow;
            prevRow = currRow;
            currRow = temp;
            
            // Clear current row for next iteration
            for (int j = 0; j <= n; j++) {
                currRow[j] = 0;
            }
        }
        
        return prevRow[n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` same as before.
- **Space Complexity:** `O(min(m, n))` as we only use two rows.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "The Longest Common Subsequence is a classic dynamic programming problem."
- "I created a 2D table where dp[i][j] represents the length of the LCS of text1[0...i-1] and text2[0...j-1]."
- "The recurrence relation is:
  1. If the current characters match (text1[i-1] == text2[j-1]), we add 1 to the LCS length of the previous substrings: dp[i][j] = dp[i-1][j-1] + 1
  2. If they don't match, we take the maximum LCS by either skipping a character in text1 or text2: dp[i][j] = max(dp[i-1][j], dp[i][j-1])"
- "The base case is dp[0][j] = dp[i][0] = 0, which means the LCS of any string with an empty string is 0."
- "For space optimization, I noticed that we only need the current and previous rows, so I reduced the space complexity from O(m*n) to O(min(m,n))."
- "The time complexity is O(m*n) because we need to fill the entire DP table."

---

## 🔥 Final Takeaways
- **The LCS problem is a fundamental dynamic programming problem with applications in bioinformatics, text comparison, and version control systems.**
- **The key insight is understanding the recursive relation for matching and non-matching characters.**
- **Dynamic programming helps us avoid redundant calculations by building up the solution from smaller subproblems.**
- **Space optimization is possible by recognizing that we only need two rows of the DP table.**
- **This problem teaches how to handle string comparison problems using dynamic programming.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/longest-common-subsequence/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-eight problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.