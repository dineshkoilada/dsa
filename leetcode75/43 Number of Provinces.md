# Number of Provinces

## 📌 Problem Statement

**LeetCode Problem:** [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/)  
**Difficulty:** Medium  

**Description:**  
There are `n` cities. Some of them are connected, while some are not. If city `a` is connected directly with city `b`, and city `b` is connected directly with city `c`, then city `a` is connected indirectly with city `c`.

A **province** is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an `n x n` matrix `isConnected` where `isConnected[i][j] = 1` if the `i`th city and the `j`th city are directly connected, and `isConnected[i][j] = 0` otherwise.

Return the total number of **provinces**.

### **Example 1:**
**Input:** 
```
isConnected = [[1,1,0],[1,1,0],[0,0,1]]
```
**Output:** 
```
2
```
**Explanation:**  
There are 3 cities. Cities 0 and 1 are connected, forming one province. City 2 is a separate province. So there are 2 provinces.

### **Example 2:**
**Input:** 
```
isConnected = [[1,0,0],[0,1,0],[0,0,1]]
```
**Output:** 
```
3
```
**Explanation:**  
There are 3 cities. Each city is a separate province since there are no connections between them.

### **Constraints:**
- `1 <= n <= 200`
- `n == isConnected.length`
- `n == isConnected[i].length`
- `isConnected[i][j]` is `1` or `0`.
- `isConnected[i][i] == 1`
- `isConnected[i][j] == isConnected[j][i]`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a bunch of cities on a map. Some cities have roads connecting them directly, and some don't.

If city A has a road to city B, and city B has a road to city C, then you can travel from city A to city C (even though there's no direct road between A and C).

A "province" is a group of cities where you can travel between any two cities in the group (directly or indirectly), but you can't travel to any city outside the group.

The problem gives you a map showing which cities are connected by direct roads. You need to count how many separate provinces there are.

For example:
- If cities 0, 1, and 2 are all connected, they form 1 province.
- If cities 0 and 1 are connected, but city 2 is alone, that's 2 provinces.
- If all three cities have no roads between them, that's 3 separate provinces.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we identify a province?**
   - A province is a connected component in the graph of cities.
2. **How can we find all cities in a province?**
   - We can use graph traversal (DFS or BFS) from any unvisited city.
3. **How do we count the number of provinces?**
   - Each time we start a new traversal, we've found a new province.

👉 This is a classic "count the number of connected components in a graph" problem!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up a visited array to track cities we've explored
```java
boolean[] visited = new boolean[n];
```

### Step 2: Iterate through each city and start DFS if unvisited
```java
int provinces = 0;
for (int i = 0; i < n; i++) {
    if (!visited[i]) {
        dfs(isConnected, visited, i);
        provinces++;
    }
}
```

### Step 3: Define the DFS function to explore all connected cities
```java
private void dfs(int[][] isConnected, boolean[] visited, int city) {
    visited[city] = true;
    for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
        if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
            dfs(isConnected, visited, neighbor);
        }
    }
}
```

---

## 🛠️ DFS Solution

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }
        
        return provinces;
    }
    
    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n²)`, where n is the number of cities. We might need to check all connections for each city.
- **Space Complexity:** `O(n)` for the visited array and the recursion stack.

---

## 🚀 BFS Solution

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Start BFS from this unvisited city
                queue.offer(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int city = queue.poll();
                    
                    // Check all neighbors
                    for (int neighbor = 0; neighbor < n; neighbor++) {
                        if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                            queue.offer(neighbor);
                            visited[neighbor] = true;
                        }
                    }
                }
                
                // We've explored all cities in one province
                provinces++;
            }
        }
        
        return provinces;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n²)`, same as the DFS solution.
- **Space Complexity:** `O(n)` for the visited array and the queue.

---

## 🔥 Union-Find Solution

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        
        // Union connected cities
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        
        // Count number of provinces (distinct root parents)
        return uf.count;
    }
    
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            
            // Initialize each city as its own parent
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        // Find root parent with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        // Union by rank
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n² * α(n))`, where α(n) is the inverse Ackermann function, which grows very slowly and is practically constant. For all practical purposes, the time complexity is effectively `O(n²)`.
- **Space Complexity:** `O(n)` for the parent and rank arrays.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This problem asks us to **count the number of connected components** in an undirected graph, where each component represents a province."
- "I implemented three different solutions: **DFS, BFS, and Union-Find**."

For the **DFS** approach:
- "I use depth-first search to explore all cities in a province starting from any unvisited city."
- "Each time I need to start a new DFS, I've found a new province that wasn't connected to previous ones."
- "The DFS efficiently visits all cities that are directly or indirectly connected."

For the **BFS** approach:
- "I use breadth-first search to explore cities level by level, starting from any unvisited city."
- "Like DFS, each time I need to start a new BFS, I've found a new province."

For the **Union-Find** approach:
- "I use the Union-Find data structure to efficiently track connected components."
- "Initially, each city is its own province. When I find a connection between cities, I union their sets."
- "At the end, the number of distinct sets equals the number of provinces."

If the interviewer asks which approach is better:
- "All three approaches have similar time complexities: `O(n²)` for this adjacency matrix representation."
- "DFS and BFS are more intuitive and straightforward to implement."
- "Union-Find provides more efficient operations for dynamic graphs where connections are being added, though that's not necessary for this problem."

---

## 🔥 Final Takeaways
- **Connected components in a graph** can be found using DFS, BFS, or Union-Find.
- **Marking visited nodes** is crucial to avoid processing the same node multiple times.
- **Adjacency matrix** provides direct access to check if two cities are connected: `isConnected[i][j] == 1`.
- **Union-Find** is a powerful data structure for connected components problems, especially with dynamic graphs.
- **The number of times we need to start a new traversal** equals the number of disconnected components.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/number-of-provinces/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-fourth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.