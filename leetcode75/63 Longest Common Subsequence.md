# Longest Common Subsequence

## 📌 Problem Statement

**LeetCode Problem:** [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)  
**Difficulty:** Medium  

**Description:**
Given two strings `text1` and `text2`, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

- For example, "ace" is a subsequence of "abcde".

A common subsequence of two strings is a subsequence that is common to both strings.

### **Example 1:**
**Input:** 
```
text1 = "abcde", text2 = "ace" 
```
**Output:** 
```
3
```
**Explanation:** The longest common subsequence is "ace" and its length is 3.

### **Example 2:**
**Input:** 
```
text1 = "abc", text2 = "abc"
```
**Output:** 
```
3
```
**Explanation:** The longest common subsequence is "abc" and its length is 3.

### **Example 3:**
**Input:** 
```
text1 = "abc", text2 = "def"
```
**Output:** 
```
0
```
**Explanation:** There is no such common subsequence, so the result is 0.

### **Constraints:**
- `1 <= text1.length, text2.length <= 1000`
- `text1` and `text2` consist of only lowercase English characters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have two words, like "abcde" and "ace". A subsequence is when you take some letters from a word, keeping them in the same order. For example, "ace" is a subsequence of "abcde" because you can remove 'b' and 'd' and still get "ace".

A common subsequence is a sequence of letters that appears in both words (in the same order). For this problem, we want to find the longest such common subsequence.

For "abcde" and "ace", the longest common subsequence is "ace" which has length 3.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we identify common subsequences?**
   - We need to find characters that appear in both strings in the same order.
2. **Can we use a greedy approach?**
   - No, we need to consider all possibilities to find the longest common subsequence.
3. **Is dynamic programming applicable here?**
   - Yes! We can build the solution step by step by considering all possible prefixes of both strings.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a 2D DP array to store the length of LCS for different prefixes
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

### Step 3: Return the length of the longest common subsequence
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
        
        // Create DP array to store the length of LCS
        // dp[i][j] represents the length of LCS for text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // If current characters match, extend the LCS
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // If current characters don't match, take the best of two options
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Return the length of the longest common subsequence
        return dp[m][n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` where m and n are the lengths of the two strings.
- **Space Complexity:** `O(m * n)` for the dp array.

---

## 🔄 Space-Optimized Solution

Since we only need the values from the current row and the previous row, we can optimize space by using just two rows:

```java
public class LongestCommonSubsequenceOptimized {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // Ensure text1 is the shorter string to save space
        if (m > n) {
            return longestCommonSubsequence(text2, text1);
        }
        
        // Use two rows for DP
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // Swap rows for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;
            
            // Reset current row
            Arrays.fill(curr, 0);
        }
        
        return prev[n];
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * n)` where m and n are the lengths of the two strings.
- **Space Complexity:** `O(min(m, n))` as we only use two rows of the smaller string's length.

---

## 🚶 Building the Actual Subsequence

While the problem only asks for the length of the LCS, sometimes we might want to recover the actual subsequence:

```java
public class LongestCommonSubsequenceWithRecovery {
    public String getLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // Create DP array
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Recover the LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        // Reverse to get the correct order
        return lcs.reverse().toString();
    }
}
```

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "The Longest Common Subsequence (LCS) problem is a classic dynamic programming problem."
- "The key insight is to build the solution incrementally by considering prefixes of both strings."
- "I defined dp[i][j] as the length of the LCS for text1[0...i-1] and text2[0...j-1]."
- "For each pair of characters, there are two cases:
  1. If the current characters match (text1[i-1] == text2[j-1]), we extend the LCS from the previous state: dp[i][j] = dp[i-1][j-1] + 1
  2. If they don't match, we take the maximum of excluding the current character from either string: dp[i][j] = max(dp[i-1][j], dp[i][j-1])"
- "This gives us the recurrence relation that forms the basis of our dynamic programming approach."
- "I also implemented a space-optimized version that reduces the space complexity from O(m*n) to O(min(m,n)) by only keeping two rows of the DP table."
- "The time complexity is O(m*n) because we need to fill the entire DP table."

---

## 🔥 Final Takeaways
- **The LCS problem is a classic DP problem with applications in string comparison, file diffing, and bioinformatics.**
- **The key is understanding the recurrence relation: dp[i][j] = dp[i-1][j-1] + 1 if characters match, otherwise max(dp[i-1][j], dp[i][j-1]).**
- **Space optimization is possible by using only two rows instead of the full DP table.**
- **The LCS problem can be extended to recover the actual subsequence, not just its length.**
- **This problem teaches how to use dynamic programming for string-related problems and how to optimize space in DP solutions.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/longest-common-subsequence/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **sixty-fourth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.