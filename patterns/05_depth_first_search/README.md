# Depth-First Search (DFS) Pattern 🌲

## 📌 Introduction: The Power of Deep Exploration

Imagine exploring a maze where you always choose a path and follow it until you hit a dead-end, then backtrack and try a different route. This is the essence of the **Depth-First Search (DFS) Pattern**—explore as deeply as possible along each branch before backtracking.

### 🎬 Real-World Analogies:

1. **Maze Exploration** 🧩
   - Go down one path until you can't go further, then backtrack and try another.
2. **Filing Cabinet** 🗄️
   - Open a drawer, look through every folder inside before moving to the next drawer.
3. **Family Tree** 🌳
   - Trace a lineage as far as possible before returning to explore another branch.

The DFS technique is your secret weapon when working with:
- 🌳 Traversing or searching through trees and graphs
- 🔄 Backtracking problems (e.g., permutations, combinations, puzzles)
- 🗺️ Pathfinding algorithms
- 🔁 Detecting cycles in graphs
- 🏗️ Topological sorting

### 🎯 Visual Example:
Exploring a binary tree in pre-order:
```
      1
     / \
    2   3
   / \
  4   5

DFS Path: 1 → 2 → 4 (backtrack) → 5 (backtrack) → 3
```

---

## 🧠 How to Recognize a DFS Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Explore All Paths" Clue** 🛤️
   - Words like "all paths," "all combinations," or "traverse every node"
   - Example: "Find all possible solutions to a puzzle"
2. **The "Backtracking" Hint** 🔙
   - The need to undo choices and try alternatives
   - Example: "Permutations, combinations, Sudoku, N-Queens"
3. **The "Tree/Graph" Signal** 🌳
   - The data structure is a tree or graph
   - Example: "Traverse all nodes in a tree or graph"

### 🤔 Essential Questions to Ask:

1. **Traversal Type Questions:**
   - Are you exploring all nodes or just finding a specific path?
   - Should the solution consider all possible outcomes?
2. **Content Questions:**
   - Is the graph/tree directed or undirected?
   - Are there cycles? Should nodes be visited more than once?
3. **Edge Case Questions:**
   - What should happen if the graph/tree is empty?
   - Should we track visited nodes?

### 🎨 Visual Problem-Solving Framework:
Imagine a tree as a set of branching tunnels. DFS is like sending a robot down each tunnel as far as it can go before turning back. Let's visualize this with a tree diagram and a path trace:

```
      1
     / \
    2   3
   / \
  4   5

DFS Traversal (pre-order):

1
|
2
|\
4 5

Traversal Path:
1 → 2 → 4 ← 2 → 5 ← 2 ← 1 → 3 ← 1

Legend:
→ : move deeper (recursive call)
← : backtrack (return from recursion)

Step-by-step:
- Start at 1, go to 2, then to 4 (deepest left branch)
- Backtrack to 2, then go to 5
- Backtrack to 2, then to 1, then to 3
- Backtrack to 1 (done)

This shows how DFS explores one branch fully before returning to explore others, using recursion or an explicit stack.
```
- The diagram and path trace together illustrate the depth-first nature and backtracking of DFS.
- This approach helps you see both the structure and the order of exploration.

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you exploring all nodes or just finding a specific path?
- Should the solution consider all possible outcomes?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if the graph/tree is empty?
- Should we track visited nodes?

### ✅ **3. Identify Base Cases**
- If the starting node is null, return immediately.
- If the target node is found, stop the search.

### ✅ **4. Write Pseudo-Code for Base Cases**
```
function DFS(node, visited):
    if node is null:
        return

    mark node as visited
    for each neighbor of node:
        if neighbor is not visited:
            DFS(neighbor, visited)
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class DFSTemplate {
    public static void dfs(int node, boolean[] visited, List<List<Integer>> graph) {
        visited[node] = true;
        System.out.println("Visited node: " + node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, graph);
            }
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The graph or tree is empty.
- The graph contains cycles.
- The graph is disconnected.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| DFS traversal           | O(V + E)        | O(V)             |
| Backtracking problems   | O(N!)           | O(N)             |

**How to derive these complexities:**
- **Time Complexity:** Each node and edge is visited once in a connected graph.
- **Space Complexity:** The recursion stack can go as deep as the number of nodes (`O(V)`).

---

## 📚 Example 1: Easy Problem - Depth-First Traversal of a Binary Tree

**Problem:**
Print all nodes of a binary tree in pre-order traversal (root, left, right).

**Input:**
```
      1
     / \
    2   3
   / \
  4   5
```

**Expected Output:** `1 2 4 5 3`

### 🔑 **Solution Steps**
1. Visit the current node.
2. Recursively traverse the left subtree.
3. Recursively traverse the right subtree.

### ✅ **Code:**
```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeDFS {
    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each node is visited once.
- **Space:** O(h) — `h` is the height of the tree (O(log n) for balanced trees).

---

## 📚 Example 2: Medium Problem - Number of Connected Components in an Undirected Graph

**Problem:**
Given an undirected graph, find the number of connected components.

**Input:** `n = 5, edges = [[0, 1], [1, 2], [3, 4]]`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Create an adjacency list for the graph.
2. Use DFS to mark all nodes in a component.
3. Count the number of times DFS starts from an unvisited node.

### ✅ **Code:**
```java
public class ConnectedComponents {
    public static int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, graph);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int node, boolean[] visited, List<List<Integer>> graph) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) dfs(neighbor, visited, graph);
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E) — Each node and edge is visited once.
- **Space:** O(V) — Visited array and recursion stack.

---

## 📚 Example 3: Hard Problem - Word Search

**Problem:**
Given a 2D board and a word, check if the word exists in the grid. You can move horizontally or vertically.

**Input:**
```
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
], word = "ABCCED"
```

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. For each cell, start DFS if the first letter matches.
2. Mark visited cells temporarily.
3. Backtrack if the current path doesn’t lead to a solution.

### ✅ **Code:**
```java
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index))
            return false;

        char temp = board[i][j];
        board[i][j] = '#'; // Mark as visited

        boolean found = dfs(board, word, i+1, j, index+1) ||
                        dfs(board, word, i-1, j, index+1) ||
                        dfs(board, word, i, j+1, index+1) ||
                        dfs(board, word, i, j-1, index+1);

        board[i][j] = temp; // Backtrack
        return found;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N * 4^L) — `N` is the number of cells, and `L` is the length of the word.
- **Space:** O(L) — The recursion depth is proportional to the word's length.

---

## 📚 Key Takeaways

1. Use DFS for tree/graph traversal, exploring as deep as possible before backtracking.
2. DFS is ideal for exploring all possible configurations in backtracking problems.
3. Time complexity is typically O(V + E) for graphs and O(n) for trees.
4. Backtracking helps solve problems where all possibilities must be explored, such as puzzles and permutations.

---

Next, let’s dive into the **Breadth-First Search Pattern** for solving problems that require level-order traversal or finding the shortest path!

