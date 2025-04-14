# Reorder Routes to Make All Paths Lead to the City Zero

## 📌 Problem Statement

**LeetCode Problem:** [1466. Reorder Routes to Make All Paths Lead to the City Zero](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/)  
**Difficulty:** Medium  

**Description:**  
There are `n` cities numbered from `0` to `n-1` and `n-1` roads such that there is only one way to travel between two different cities (this network forms a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by `connections` where `connections[i] = [a, b]` represents a road from city `a` to city `b`.

This year, there will be a big event in the capital (city `0`), and many people want to travel to this city. Your task consists of reorienting some roads such that each city can visit the city `0`. The cost of reorienting a road is `1`.

Return the minimum number of roads that you need to reorient.

### **Example 1:**
**Input:** 
```
n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
```
**Output:** 
```
3
```
**Explanation:**  
Change the direction of edges shown in red such that each node can reach the node 0 (capital).

### **Example 2:**
**Input:** 
```
n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
```
**Output:** 
```
2
```
**Explanation:**  
Change the direction of edges shown in red such that each node can reach the node 0 (capital).

### **Example 3:**
**Input:** 
```
n = 3, connections = [[1,0],[2,0]]
```
**Output:** 
```
0
```

### **Constraints:**
- `2 <= n <= 5 * 10^4`
- `connections.length == n-1`
- `connections[i].length == 2`
- `0 <= connections[i][0], connections[i][1] <= n-1`
- `connections[i][0] != connections[i][1]`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have several cities connected by one-way roads, like a big tree. Each road lets you travel in only one direction (from city A to city B, but not from B to A).

There's going to be a big event in city 0 (the capital), and people from all other cities want to go there. But some roads are pointing in the wrong direction! You need to figure out how many roads you need to flip so that everyone can reach the capital.

For each road you flip, it costs 1 point. Your goal is to find the minimum number of roads you need to flip so that everyone can reach city 0.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we know if a road needs to be reoriented?**
   - If a road points away from the capital (city 0), it needs to be flipped.
2. **How can we determine which way each road should point?**
   - We can start from city 0 and explore outward to determine the correct direction.
3. **How do we handle the tree structure?**
   - We need to use graph traversal (DFS or BFS) considering both original and flipped directions.

👉 The key insight is that we need to count roads that currently point in the wrong direction (away from city 0)!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Build the undirected graph with original directions
```java
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>());
}

// Store roads [a,b] as a->b (positive) in the graph
for (int[] conn : connections) {
    adj.get(conn[0]).add(conn[1]);      // Original direction (positive)
    adj.get(conn[1]).add(-conn[0]);     // Reverse direction (negative)
}
```

### Step 2: Perform DFS/BFS from city 0 to count reorientations needed
```java
boolean[] visited = new boolean[n];
int count = 0;

Queue<Integer> queue = new LinkedList<>();
queue.offer(0);
visited[0] = true;

while (!queue.isEmpty()) {
    int city = queue.poll();
    
    for (int neighbor : adj.get(city)) {
        int nextCity = Math.abs(neighbor);
        if (!visited[nextCity]) {
            // If original direction is away from city 0, we need to reorient
            if (neighbor > 0) {
                count++;
            }
            visited[nextCity] = true;
            queue.offer(nextCity);
        }
    }
}
```

### Step 3: Return the count of roads that need to be reoriented
```java
return count;
```

---

## 🛠️ BFS Solution

```java
class Solution {
    public int minReorder(int n, int[][] connections) {
        // Build the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Store roads [a,b] as a->b (positive) and b->a (negative) in the graph
        // Positive means original direction, negative means reverse
        for (int[] conn : connections) {
            adj.get(conn[0]).add(conn[1]);      // Original direction
            adj.get(conn[1]).add(-conn[0]);     // Reverse direction
        }
        
        // BFS from city 0
        boolean[] visited = new boolean[n];
        int count = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int city = queue.poll();
            
            for (int neighbor : adj.get(city)) {
                int nextCity = Math.abs(neighbor);
                if (!visited[nextCity]) {
                    // If neighbor > 0, it means we're following the original direction
                    // which is away from city 0, so we need to reorient
                    if (neighbor > 0) {
                        count++;
                    }
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)`, where n is the number of cities. We visit each city once.
- **Space Complexity:** `O(n)` for the adjacency list, visited array, and queue.

---

## 🚀 DFS Solution

```java
class Solution {
    public int minReorder(int n, int[][] connections) {
        // Build the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Store roads [a,b] as a->b (positive) and b->a (negative) in the graph
        for (int[] conn : connections) {
            adj.get(conn[0]).add(conn[1]);      // Original direction
            adj.get(conn[1]).add(-conn[0]);     // Reverse direction (indicated by negative)
        }
        
        // DFS from city 0
        boolean[] visited = new boolean[n];
        return dfs(adj, visited, 0);
    }
    
    private int dfs(List<List<Integer>> adj, boolean[] visited, int city) {
        visited[city] = true;
        int count = 0;
        
        for (int neighbor : adj.get(city)) {
            int nextCity = Math.abs(neighbor);
            if (!visited[nextCity]) {
                // If we're following original direction, we need to reorient
                if (neighbor > 0) {
                    count++;
                }
                count += dfs(adj, visited, nextCity);
            }
        }
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)`, where n is the number of cities. We visit each city once.
- **Space Complexity:** `O(n)` for the adjacency list, visited array, and recursion stack.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This problem is asking us to **reorient roads** so that all cities can reach city 0."
- "The key insight is to **identify which roads need to be flipped** - these are the roads that currently point away from city 0."
- "I used a **graph traversal approach** (both BFS and DFS work) with the following strategy:"

1. **Building the Graph:**
   - "I stored each road [a,b] twice: once as a→b (positive) and once as b→a (negative)."
   - "The sign helps me track the original direction of the road."

2. **Traversing from City 0:**
   - "Starting from city 0, I explore all reachable cities using BFS/DFS."
   - "When I traverse from city A to city B, if the edge is positive (original direction), it means the road points away from city 0 and needs to be reoriented."
   - "If the edge is negative, it means the road already points toward city 0 (no reorientation needed)."

3. **Counting Reorientations:**
   - "Each time I follow a positive edge to an unvisited city, I increment my reorientation counter."

If the interviewer asks why this approach works:
- "The problem forms a tree, so there's exactly one path from each city to city 0."
- "By traversing outward from city 0, I'm finding the 'correct' path to each city."
- "Any road that points in the opposite direction of this path needs to be flipped."

---

## 🔥 Final Takeaways
- **Represent directional edges carefully** - using positive/negative values is an elegant way to track original directions.
- **Graph traversal from the destination** helps determine which edges need to be reoriented.
- **Trees have exactly one path** between any two nodes - this guarantees a unique solution.
- **BFS and DFS are both valid approaches** for this problem, choose the one you're most comfortable with.
- **Take advantage of the problem constraints** - knowing the network forms a tree eliminates many edge cases.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-fifth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.