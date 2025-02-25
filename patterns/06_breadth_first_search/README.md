# Breadth-First Search (BFS) Pattern

## 🎯 Introduction

Imagine exploring a maze by checking all possible moves one step at a time before going deeper. This is the core idea of **Breadth-First Search (BFS)**—you explore all nodes at the current depth level before moving to the next level.

BFS is commonly used for:
- Level-order traversal of trees
- Finding the shortest path in unweighted graphs
- Detecting cycles in graphs
- Solving puzzles like sliding puzzles, word ladders, etc.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you required to visit all nodes in order of their depth levels?
   - Are you trying to find the shortest path between two points?

2. **Ask Clarifying Questions:**
   - Is the graph directed or undirected?
   - Is the graph weighted or unweighted?
   - Should nodes be revisited?

3. **Identify Clues for Using BFS:**
   - The problem involves finding the shortest path.
   - The structure is a tree or graph.
   - You need to process elements level by level.

4. **Predicting if BFS Is Applicable:**
   - Does the problem involve exploring nodes in layers?
   - Do you need the shortest path in an unweighted graph?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are we exploring all nodes or searching for the shortest path?
- Should we track levels or depths?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if the starting node is null?
- Should we track visited nodes?

### ✅ **3. Identify Base Cases**
- If the starting node is null, return immediately.
- If the target node is found, return the path length.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function BFS(start):
    if start is null:
        return

    initialize queue
    add start to queue
    mark start as visited

    while queue is not empty:
        node = queue.poll()
        process node
        for each neighbor of node:
            if neighbor is not visited:
                add neighbor to queue
                mark neighbor as visited
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class BFSTemplate {
    public static void bfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println("Visited node: " + node);

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
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

| Operation              | Time Complexity | Space Complexity |
|------------------------|-----------------|------------------|
| BFS traversal          | O(V + E)        | O(V)             |
| Shortest path          | O(V + E)        | O(V)             |

**How to derive these complexities:**
- **Time Complexity:** Each node and edge is visited once in a connected graph.
- **Space Complexity:** The queue and visited array store up to O(V) elements.

---

## 📚 Example 1: Easy Problem - Level Order Traversal of a Binary Tree

**Problem:**
Print nodes of a binary tree level by level.

**Input:**
```
      1
     / \
    2   3
   / \
  4   5
```

**Expected Output:** `[[1], [2, 3], [4, 5]]`

### 🔑 **Solution Steps**
1. Initialize a queue and add the root node.
2. For each level, process all nodes in the queue.
3. Add their children to the queue.

### ✅ **Code:**
```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }

        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each node is visited once.
- **Space:** O(n) — Queue holds all nodes at the current level.

---

## 📚 Example 2: Medium Problem - Shortest Path in an Unweighted Graph

**Problem:**
Find the shortest path from node `0` to node `4`.

**Input:** `n = 5, edges = [[0, 1], [0, 2], [1, 3], [2, 4], [3, 4]]`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Build an adjacency list.
2. Use BFS to find the shortest path length.

### ✅ **Code:**
```java
public class ShortestPathBFS {
    public static int shortestPath(int n, int[][] edges, int start, int end) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(start);
        visited[start] = true;
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                if (node == end) return distance;
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E) — All nodes and edges are processed.
- **Space:** O(V) — Queue and visited array.

---

## 📚 Example 3: Hard Problem - Sliding Puzzle

**Problem:**
Given a 2x3 board, find the least number of moves to reach the solved state.

**Input:** `[[1,2,3],[4,0,5]]`

**Expected Output:** `1`

### 🔑 **Solution Steps**
1. Use BFS to explore all board states.
2. Track visited states.
3. Return the number of moves when the solved state is reached.

### ✅ **Code:**
```java
import java.util.*;

public class SlidingPuzzle {
    public static int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int[] row : board) {
            for (int num : row) {
                start += num;
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int[][] neighbors = {{1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};

        queue.offer(start);
        visited.add(start);
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) return moves;
                int zeroIdx = current.indexOf('0');
                for (int neighbor : neighbors[zeroIdx]) {
                    StringBuilder next = new StringBuilder(current);
                    char temp = next.charAt(neighbor);
                    next.setCharAt(neighbor, '0');
                    next.setCharAt(zeroIdx, temp);
                    String nextState = next.toString();
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N!) — All permutations of the board can be visited.
- **Space:** O(N!) — To store visited states.

---

## 📚 Key Takeaways

1. Use BFS for level-order traversal, shortest path finding, or exploring neighbors layer by layer.
2. BFS works best for finding the shortest paths in unweighted graphs.
3. Time complexity is typically O(V + E) for graphs and O(n) for trees.
4. BFS is ideal for scenarios that require the closest or minimal steps to a goal.

---

Next, let's dive into the **Dynamic Programming Pattern** for solving problems that require breaking them down into overlapping subproblems and using memoization or tabulation!

