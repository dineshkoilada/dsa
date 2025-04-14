# Guess Number Higher or Lower

## 📌 Problem Statement

**LeetCode Problem:** [374. Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/)  
**Difficulty:** Easy  

**Description:**
We are playing the Guess Game. The game is as follows:

I pick a number from `1` to `n`. You have to guess which number I picked.

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API `int guess(int num)`, which returns three possible results:
- `-1`: Your guess is higher than the number I picked (i.e. `num > pick`).
- `1`: Your guess is lower than the number I picked (i.e. `num < pick`).
- `0`: your guess is equal to the number I picked (i.e. `num == pick`).

Return the number that I picked.

### **Example 1:**
**Input:** 
```
n = 10, pick = 6
```
**Output:** 
```
6
```

### **Example 2:**
**Input:** 
```
n = 1, pick = 1
```
**Output:** 
```
1
```

### **Example 3:**
**Input:** 
```
n = 2, pick = 1
```
**Output:** 
```
1
```

### **Constraints:**
- `1 <= n <= 2^31 - 1`
- `1 <= pick <= n`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine we're playing a guessing game. I'm thinking of a number between 1 and n (like 1 to 100). Each time you guess a number, I'll tell you if your guess is too high, too low, or exactly right. Your job is to figure out my number with as few guesses as possible.

For example, if I'm thinking of the number 6 and you guess 8, I'll tell you "too high". If you guess 4, I'll tell you "too low". When you guess 6, I'll say "correct!"

The computer has a function called `guess(int num)` that tells you if your guess is too high (-1), too low (1), or correct (0).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What's the most efficient way to guess a number?**
   - Think about how you'd play a guessing game in real life.
2. **If we have a range from 1 to n, what's a good first guess?**
   - Maybe somewhere in the middle?
3. **After each guess, how can we narrow down our search range?**
   - If our guess is too high, we know the answer must be lower.

👉 These considerations point us towards the binary search algorithm!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up our search range
```java
int left = 1;
int right = n;
```

### Step 2: Use binary search to find the number
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    int result = guess(mid);
    
    if (result == 0) {
        return mid; // Found the number
    } else if (result == -1) {
        right = mid - 1; // Number is lower than our guess
    } else {
        left = mid + 1; // Number is higher than our guess
    }
}
```

---

## 🛠️ Writing the Brute Force Solution

```java
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        for (int i = 1; i <= n; i++) {
            if (guess(i) == 0) {
                return i;
            }
        }
        return -1; // This line should never be reached given the problem constraints
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` in the worst case, we check every number from 1 to n.
- **Space Complexity:** `O(1)`, we only use a few variables.

---

## 🚀 Optimized Solution (Binary Search)

```java
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        
        while (left <= right) {
            // Use this calculation to avoid integer overflow
            int mid = left + (right - left) / 2;
            
            int result = guess(mid);
            
            if (result == 0) {
                return mid; // Found the answer
            } else if (result == -1) {
                right = mid - 1; // The number is lower
            } else {
                left = mid + 1; // The number is higher
            }
        }
        
        return -1; // This line should never be reached given the problem constraints
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(log n)`, as we divide our search space in half with each comparison.
- **Space Complexity:** `O(1)`, we only use a few variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I recognized this as a classic binary search problem, where we're searching for a specific value within a sorted range."
- "I started with a search range from 1 to n, and in each step, I checked the middle value."
- "Depending on the feedback from the `guess` function, I narrowed my search to either the left or right half."
- "If the guess is correct, I return the number. Otherwise, I adjust my search range accordingly."
- "This approach is optimal because binary search has O(log n) time complexity, which is much faster than checking each number linearly."
- "I also used the formula `left + (right - left) / 2` to calculate the middle to avoid potential integer overflow, which could happen with large values of n."

---

## 🔥 Final Takeaways
- **Binary search is excellent for problems where you're searching in a sorted range.**
- **With binary search, we reduce the search space by half with each comparison, giving us O(log n) time complexity.**
- **Be careful with integer overflow when calculating the middle of a range. Use `left + (right - left) / 2` instead of `(left + right) / 2`.**
- **This problem reinforces the importance of understanding APIs and how to use them effectively.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/guess-number-higher-or-lower/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-third problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.