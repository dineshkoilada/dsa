# Backtracking Pattern

## 🎯 Introduction

Imagine you are trying to solve a maze. At every decision point, you try one path. If it leads to a dead end, you backtrack and try another route. This problem-solving technique of exploring all possible solutions by making decisions and undoing them when necessary is called **Backtracking**.

Backtracking is particularly useful for:
- Generating permutations and combinations
- Solving puzzles (Sudoku, N-Queens)
- Subset generation
- Pathfinding in constraint-based problems

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you trying to generate all possible solutions?
   - Do you need to explore all configurations until finding the valid one?

2. **Ask Clarifying Questions:**
   - Are duplicates allowed?
   - Are there constraints on possible moves or states?
   - When should we stop exploring?

3. **Identify Clues for Using Backtracking:**
   - The problem requires generating all possible solutions.
   - The solution involves making a sequence of decisions.
   - You need to backtrack upon hitting invalid paths.

4. **Predicting if Backtracking Is Applicable:**
   - Does the problem involve trying out multiple possibilities?
   - Are there constraints that need to be validated dynamically?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are we generating all possibilities or finding the first valid one?
- Are there any constraints to satisfy?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be done if an invalid solution is found?
- When should recursion stop?

### ✅ **3. Identify Base Cases**
- If the current path satisfies the constraints or reaches the required length, add it to the result.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function backtrack(current, choices):
    if current is a valid solution:
        add current to results
        return

    for each choice in choices:
        if choice is valid:
            make choice
            backtrack(updated current, remaining choices)
            undo choice
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class BacktrackingTemplate {
    public static void backtrack(List<Integer> current, List<Integer> choices, List<List<Integer>> results) {
        if (choices.isEmpty()) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < choices.size(); i++) {
            int choice = choices.remove(i);
            current.add(choice);
            backtrack(current, choices, results);
            current.remove(current.size() - 1);
            choices.add(i, choice);
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- No valid solutions exist.
- Duplicate entries in input.
- Constraints that eliminate large portions of the search space.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Generating permutations | O(N!)           | O(N)             |
| Subset generation       | O(2^N)          | O(N)             |
| N-Queens problem        | O(N!)           | O(N)             |

**How to derive these complexities:**
- **Time Complexity:** Depends on the number of possible configurations.
- **Space Complexity:** Depth of the recursion stack and size of the current solution.

---

## 📚 Example 1: Easy Problem - Subsets Generation

**Problem:**
Given a set of integers, generate all possible subsets.

**Input:** `[1, 2, 3]`

**Expected Output:** `[[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]`

### 🔑 **Solution Steps**
1. At each element, decide whether to include it in the subset.
2. Recursively call the function for the next element.
3. Backtrack after including the element.

### ✅ **Code:**
```java
public class SubsetsBacktracking {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n) — Each element has two choices (include or exclude).
- **Space:** O(n) — Recursion stack depth.

---

## 📚 Example 2: Medium Problem - Permutations

**Problem:**
Given a list of distinct numbers, return all possible permutations.

**Input:** `[1, 2, 3]`

**Expected Output:** `[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]`

### 🔑 **Solution Steps**
1. For each element, choose it and mark it as used.
2. Recursively generate permutations with the remaining elements.
3. Backtrack by unmarking the element.

### ✅ **Code:**
```java
public class PermutationsBacktracking {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // Skip already used numbers
                tempList.add(nums[i]);
                backtrack(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N!) — All possible permutations.
- **Space:** O(N) — Recursion stack depth.

---

## 📚 Example 3: Hard Problem - N-Queens Problem

**Problem:**
Place `N` queens on an `N x N` chessboard such that no two queens threaten each other.

**Input:** `n = 4`

**Expected Output:**
```
[".Q..",
 "...Q",
 "Q...",
 "..Q."]
```

### 🔑 **Solution Steps**
1. Place a queen in a valid position on the board.
2. Check that no two queens threaten each other.
3. Recursively place the next queen.
4. Backtrack if placing the queen leads to no valid solutions.

### ✅ **Code:**
```java
public class NQueensBacktracking {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        backtrack(result, board, 0);
        return result;
    }

    private static void backtrack(List<List<String>> result, char[][] board, int row) {
        if (row == board.length) {
            result.add(construct(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(result, board, row + 1);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) if (board[i][col] == 'Q') return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 'Q') return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) if (board[i][j] == 'Q') return false;
        return true;
    }

    private static List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) result.add(new String(row));
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N!) — Each row has N choices.
- **Space:** O(N) — Board and recursion stack.

---

## 📚 Key Takeaways

1. Use backtracking when solving problems that involve exploring all possible solutions.
2. Always backtrack by undoing the last move after each recursive call.
3. Time complexity depends on the number of possible configurations.
4. Backtracking can be combined with pruning techniques to optimize performance.

---

Next, let's dive into the **Greedy Algorithm Pattern** for solving problems that involve making locally optimal choices to find a globally optimal solution!

