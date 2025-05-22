# Graph Algorithms Pattern 🌐

## 📌 Introduction: The Power of Connections

Imagine a network of cities connected by roads. If you want to find the shortest route between two cities, check if there's a connection, or plan the most efficient delivery route, you're working with a **Graph**! A graph is a collection of nodes (vertices) and edges (connections), and graph algorithms help you navigate, analyze, and optimize these relationships.

### 🎬 Real-World Analogies:

1. **City Maps & GPS** 🗺️
   - Cities are nodes, roads are edges. Find the shortest or fastest route!
2. **Social Networks** 👥
   - People are nodes, friendships are edges. Who's in your friend circle? How many degrees of separation?
3. **Internet Routing** 🌍
   - Routers are nodes, cables are edges. How does your message travel from your computer to a server?

The Graph Algorithms pattern is your secret weapon for:
- 🔗 Finding connected components
- 🛣️ Shortest path algorithms (Dijkstra’s, Bellman-Ford)
- 🔄 Detecting cycles in directed and undirected graphs
- 🏗️ Topological sorting
- 🌲 Minimum spanning tree problems (Kruskal’s, Prim’s)

---

## 🧠 How to Recognize a Graph Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Network" Clue**
   - "Find the shortest path", "check connectivity", "detect cycles"
2. **The "Node & Edge" Hint**
   - Problem involves entities and their relationships
3. **The "Traversal" Signal**
   - You need to visit or explore all nodes/edges

### 🤔 Essential Questions to Ask:
- Is the graph directed or undirected?
- Is it weighted or unweighted? Any negative weights?
- Is the graph connected or can it have multiple components?
- Are you looking for traversal, pathfinding, or structure detection?

---

## 🎨 Visual Problem-Solving Framework

### Graph Traversal Visual:
```
Nodes: [A]---[B]---[C]
         \     |
         [D]--[E]

BFS: Explore level by level (like ripples in a pond)
DFS: Dive deep along a path before backtracking

Legend:
- 🔵 = Visited node
- ⚪ = Unvisited node

Step-by-step:
1. Start at node A (mark 🔵)
2. Visit all neighbors (B, D)
3. Continue to next unvisited neighbor...
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Is the graph directed or undirected?
- Is it weighted or unweighted?
- What are you trying to find: traversal, path, cycle, or structure?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned if the graph is empty?
- Are duplicate/self-loop edges allowed?
- Can there be negative cycles?

### ✅ **3. Identify Base Cases**
- The graph is empty.
- The node has no neighbors.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function graphAlgorithm(graph):
    initialize data structures (visited, queue, stack, etc.)
    for each node in graph:
        if node is not visited:
            process node (DFS/BFS, etc.)
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class GraphTemplate {
    public static void traverseGraph(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }
    }

    private static void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
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
- Graph has disconnected components
- Graph has self-loops
- Graph contains negative-weight edges

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                | Time Complexity | Space Complexity |
|--------------------------|-----------------|------------------|
| Breadth-First Search     | O(V + E)        | O(V)             |
| Depth-First Search       | O(V + E)        | O(V)             |
| Dijkstra’s Algorithm     | O((V + E) log V)| O(V)             |
| Bellman-Ford Algorithm   | O(V * E)        | O(V)             |
| Kruskal’s Algorithm      | O(E log E)      | O(V)             |
| Prim’s Algorithm         | O((V + E) log V)| O(V)             |

**How to derive these complexities:**
- **Time Complexity:** Depends on the number of vertices `V` and edges `E`.
- **Space Complexity:** Depends on the data structures used (visited array, queue, or priority queue).

---

## 📚 Example 1: Easy Problem - Number of Connected Components

**Problem:**
Given an undirected graph, count the number of connected components.

**Input:** `n = 5, edges = [[0, 1], [1, 2], [3, 4]]`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Build an adjacency list.
2. Use DFS to mark all nodes in the same component.
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
- **Time:** O(V + E) — All nodes and edges are visited once.
- **Space:** O(V) — Visited array and recursion stack.

---

## 📚 Example 2: Medium Problem - Shortest Path in Weighted Graph (Dijkstra's Algorithm)

**Problem:**
Find the shortest path from a starting node to all other nodes in a weighted graph.

**Input:** `n = 5, edges = [[0,1,2],[0,2,4],[1,2,1],[1,3,7],[2,4,3],[3,4,1]]`, `start = 0`

**Expected Output:** `[0, 2, 3, 9, 6]`

### 🔑 **Solution Steps**
1. Build an adjacency list.
2. Use a priority queue to always expand the node with the smallest known distance.
3. Update distances for neighboring nodes.

### ✅ **Code:**
```java
import java.util.*;

public class DijkstraShortestPath {
    public static int[] dijkstra(int n, int[][] edges, int start) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];

            if (distance > distances[node]) continue;

            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int newDist = distance + neighbor[1];
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }
        return distances;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O((V + E) log V) — Due to priority queue operations.
- **Space:** O(V) — Distance array and priority queue.

---

## 📚 Example 3: Hard Problem - Detect Cycle in Directed Graph

**Problem:**
Given a directed graph, detect if it contains a cycle.

**Input:** `n = 4, edges = [[0,1],[1,2],[2,3],[3,1]]`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. Use DFS with an additional recursion stack to detect back edges.
2. If a node is visited twice in the same recursion stack, a cycle exists.

### ✅ **Code:**
```java
public class DetectCycleDirectedGraph {
    public static boolean hasCycle(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, visited, recStack)) return true;
        }
        return false;
    }

    private static boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) return true;
        if (visited[node]) return false;

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : graph.get(node)) {
            if (dfs(neighbor, graph, visited, recStack)) return true;
        }
        recStack[node] = false;
        return false;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E) — Each node and edge is processed once.
- **Space:** O(V) — Visited and recursion stack arrays.

---

## 📚 Key Takeaways

1. Use Graph algorithms for problems involving networks, connections, or paths.
2. Choose DFS for deep exploration and BFS for level-wise traversal or shortest paths in unweighted graphs.
3. For weighted shortest paths, use Dijkstra’s or Bellman-Ford algorithms depending on edge weights.
4. Detect cycles in directed graphs using DFS and a recursion stack.

---

Next, let's dive into the **Math Geometry Pattern** for solving problems related to connectivity, cycles, and merging components efficiently!

