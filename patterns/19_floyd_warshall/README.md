# Floyd-Warshall Algorithm Pattern

## 🎯 Introduction

Imagine you’re managing a network where you need to determine the shortest paths between all pairs of nodes—like finding the shortest flight routes between every pair of cities. The **Floyd-Warshall Algorithm** is an efficient dynamic programming approach for solving this problem.

The Floyd-Warshall Pattern is particularly useful for:
- Finding shortest paths between all pairs of nodes
- Detecting negative cycles in a graph
- Calculating transitive closures
- Evaluating reachability between nodes in directed graphs

This algorithm works with **weighted directed graphs**, including graphs with negative edge weights (as long as there are no negative cycles).

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you looking for the shortest paths between all pairs of nodes?
   - Are negative weights present?

2. **Ask Clarifying Questions:**
   - Is the graph directed or undirected?
   - Are there negative edge weights?
   - Can the graph have negative cycles?

3. **Identify Clues for Using Floyd-Warshall:**
   - The problem requires shortest paths between **all pairs of nodes**.
   - The graph can have negative weights.
   - You need to check for negative cycles.

4. **Predicting if Floyd-Warshall Is Applicable:**
   - Does the problem involve all-pairs shortest paths?
   - Is the graph dense, meaning there are many edges?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are there negative edges?
- Are self-loops allowed?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Should unreachable nodes be marked with infinity?
- What should happen if a negative cycle is detected?

### ✅ **3. Identify Base Cases**
- Distance from a node to itself is 0.
- Distance between directly connected nodes is their edge weight.
- Distance between unconnected nodes is infinity.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function floydWarshall(graph):
    n = number of nodes
    initialize distance matrix with graph weights
    for k from 0 to n-1:
        for i from 0 to n-1:
            for j from 0 to n-1:
                distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])
    return distance matrix
```

### ✅ **5. Write the Code Skeleton**
```java
public class FloydWarshall {
    public static void floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        // Initialize distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else if (graph[i][j] != 0) dist[i][j] = graph[i][j];
                else dist[i][j] = Integer.MAX_VALUE / 2; // Avoid overflow
            }
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist);
    }

    private static void printSolution(int[][] dist) {
        for (int[] row : dist) {
            for (int value : row) {
                if (value == Integer.MAX_VALUE / 2) {
                    System.out.print("INF ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println();
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Negative cycles in the graph.
- Self-loops with non-zero weights.
- Unreachable nodes.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                  | Time Complexity | Space Complexity |
|----------------------------|-----------------|------------------|
| Initialization             | O(n^2)          | O(n^2)           |
| All-Pairs Shortest Paths   | O(n^3)          | O(n^2)           |

**How to derive these complexities:**
- **Time Complexity:** Three nested loops over all nodes.
- **Space Complexity:** Storing an \( n \times n \) matrix.

---

## 📚 Example 1: Easy Problem - All Pairs Shortest Path

**Problem:**
Find the shortest paths between all pairs of nodes in a graph.

**Input:**
```
Graph:
0 -> 1 (weight 3)
0 -> 2 (weight 8)
1 -> 2 (weight 2)
2 -> 3 (weight 1)
3 -> 1 (weight 4)
```

**Expected Output:**
```
0 3 5 6
INF 0 2 3
INF INF 0 1
INF 4 6 0
```

### 🔑 **Solution Steps**
1. Initialize the distance matrix.
2. Use the Floyd-Warshall algorithm to update distances.
3. Print the final distance matrix.

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n^3) — Three nested loops over all nodes.
- **Space:** O(n^2) — Distance matrix.

---

## 📚 Example 2: Medium Problem - Detect Negative Cycles

**Problem:**
Detect if the graph contains a negative-weight cycle.

**Input:**
```
Graph:
0 -> 1 (weight 1)
1 -> 2 (weight -1)
2 -> 0 (weight -1)
```

**Expected Output:**
```
Graph contains a negative cycle
```

### 🔑 **Solution Steps**
1. Run Floyd-Warshall Algorithm.
2. Check if any `dist[i][i]` becomes negative.

### ✅ **Code:**
```java
public static boolean detectNegativeCycle(int[][] graph) {
    int n = graph.length;
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            dist[i][j] = (i == j) ? 0 : (graph[i][j] != 0 ? graph[i][j] : Integer.MAX_VALUE / 2);
        }
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        if (dist[i][i] < 0) return true; // Negative cycle detected
    }

    return false;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n^3) — Standard Floyd-Warshall run.
- **Space:** O(n^2) — Distance matrix.

---

## 📚 Example 3: Hard Problem - Transitive Closure of a Graph

**Problem:**
Find the transitive closure of a graph, i.e., determine whether a path exists between all pairs of nodes.

**Input:**
```
Graph:
0 -> 1
1 -> 2
2 -> 0
3 -> 2
```

**Expected Output:**
```
1 1 1 0
1 1 1 0
1 1 1 0
1 1 1 1
```

### 🔑 **Solution Steps**
1. Initialize reachability matrix.
2. Apply Floyd-Warshall logic for transitive closure.

### ✅ **Code:**
```java
public static void transitiveClosure(int[][] graph) {
    int n = graph.length;
    boolean[][] reach = new boolean[n][n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            reach[i][j] = (graph[i][j] != 0);
        }
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
            }
        }
    }

    // Print transitive closure
    for (boolean[] row : reach) {
        for (boolean value : row) {
            System.out.print((value ? 1 : 0) + " ");
        }
        System.out.println();
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n^3) — Three nested loops.
- **Space:** O(n^2) — Reachability matrix.

---

## 📚 Key Takeaways

1. Use Floyd-Warshall for efficiently computing shortest paths between all pairs of nodes.
2. The algorithm can detect negative cycles.
3. Useful for transitive closure problems in directed graphs.
4. Time complexity is \( O(n^3) \), making it best suited for dense graphs or small datasets.

---

Next, let's dive into the **Dijkstra Algorithm Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!