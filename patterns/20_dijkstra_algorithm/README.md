# Dijkstra's Algorithm Pattern 🚦

## 📌 Introduction: The Power of Shortest Paths

Imagine navigating a city map to find the fastest route from your home to every other place in town. **Dijkstra's Algorithm** is your GPS for graphs—it finds the shortest paths from a single source to all other nodes in a weighted graph with non-negative edge weights.

### 🎬 Real-World Analogies:

1. **GPS Navigation** 🗺️
   - Find the quickest route from your location to all destinations.
2. **Delivery Routing** 🚚
   - Optimize delivery times from a warehouse to every customer.
3. **Internet Packet Routing** 🌐
   - Routers use Dijkstra’s to send data along the fastest path.

Dijkstra's Algorithm is your go-to for:
- 🏁 Single-source shortest path queries
- 🚗 Route optimization in maps and games
- 📡 Network routing protocols

---

## 🧠 How to Recognize a Dijkstra's Algorithm Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Shortest Path" Clue**
   - "Find the shortest path from a source to all nodes"
2. **The "Non-Negative Weights" Hint**
   - All edge weights are non-negative
3. **The "Single Source" Signal**
   - You need distances from one node to all others

### 🤔 Essential Questions to Ask:
- Is the graph weighted and directed?
- Are all weights non-negative?
- What should be returned for unreachable nodes?
- Should the algorithm return the actual path or just the distance?

---

## 🎨 Visual Problem-Solving Framework

### Dijkstra's Step-by-Step:
```
Graph (adjacency matrix):
    0   1   2   3   4   5
0 [ 0   4   0   0   0   0 ]
1 [ 4   0   8   0   0   0 ]
2 [ 0   8   0   7   0   4 ]
3 [ 0   0   7   0   9  14 ]
4 [ 0   0   0   9   0  10 ]
5 [ 0   0   4  14  10   0 ]

Legend:
- 🔵 = Visited node (shortest path found)
- ⚪ = Unvisited node
- Distance array tracks shortest known distances

Step-by-step:
1. Start at source node (distance 0)
2. Update distances to all neighbors
3. Pick the unvisited node with the smallest distance
4. Repeat until all nodes are visited
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Is the graph weighted and directed?
- Are all weights non-negative?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned for unreachable nodes?
- Should the algorithm return the actual path or just the distance?

### ✅ **3. Identify Base Cases**
- Distance from the source node to itself is 0.
- Initialize all other distances to infinity.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function dijkstra(graph, source):
    initialize distance array with infinity
    distance[source] = 0
    initialize priority queue with (0, source)

    while priority queue is not empty:
        current_distance, current_node = extract minimum from priority queue
        for each neighbor:
            if shorter path found:
                update distance
                insert neighbor into priority queue
    return distance array
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class DijkstraAlgorithm {
    public static int[] dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currentDist = current[1];

            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[node][neighbor] != 0) {
                    int newDist = currentDist + graph[node][neighbor];
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        pq.offer(new int[]{neighbor, newDist});
                    }
                }
            }
        }

        return dist;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Graph contains disconnected nodes
- No outgoing edges from the source node
- Graph with self-loops

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Initialization          | O(V)            | O(V)             |
| Extract Min (Heap)      | O((V + E) log V)| O(V)             |
| Distance Updates        | O(E log V)      | O(V)             |

**How to derive these complexities:**
- **Time Complexity:** Based on priority queue operations for all nodes and edges.
- **Space Complexity:** Storage for distance array and priority queue.

---

## 📚 Example 1: Easy Problem - Single Source Shortest Path

**Problem:**
Find the shortest distances from a source node to all other nodes in a graph.

**Input:**
```
Graph (adjacency matrix):
0 4 0 0 0 0
4 0 8 0 0 0
0 8 0 7 0 4
0 0 7 0 9 14
0 0 0 9 0 10
0 0 4 14 10 0
Source = 0
```

**Expected Output:**
```
[0, 4, 12, 19, 21, 11]
```

### 🔑 **Solution Steps**
1. Initialize distance array and priority queue.
2. Loop through all nodes and update distances using neighbors.
3. Return the distance array.

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O((V + E) log V) — Efficient for sparse graphs.
- **Space:** O(V) — Distance array and priority queue.

---

## 📚 Example 2: Medium Problem - Find the Shortest Path

**Problem:**
Find the shortest path (not just distance) from a source node to a target node.

**Input:**
```
Graph (adjacency matrix):
0 1 4
1 0 2
4 2 0
Source = 0
Target = 2
```

**Expected Output:**
```
Shortest path: [0, 1, 2]
Shortest distance: 3
```

### 🔑 **Solution Steps**
1. Track the parent of each node during updates.
2. Backtrack from the target node using the parent array.

### ✅ **Code:**
```java
public static List<Integer> shortestPath(int[][] graph, int source, int target) {
    int n = graph.length;
    int[] dist = new int[n];
    int[] parent = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    dist[source] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[]{source, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int node = current[0];

        for (int neighbor = 0; neighbor < n; neighbor++) {
            if (graph[node][neighbor] != 0) {
                int newDist = dist[node] + graph[node][neighbor];
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    parent[neighbor] = node;
                    pq.offer(new int[]{neighbor, newDist});
                }
            }
        }
    }

    List<Integer> path = new ArrayList<>();
    for (int at = target; at != -1; at = parent[at]) {
        path.add(at);
    }
    Collections.reverse(path);
    return path;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O((V + E) log V) — Efficient for sparse graphs.
- **Space:** O(V) — Distance array, parent array, and priority queue.

---

## 📚 Example 3: Hard Problem - Network Delay Time

**Problem:**
You are given a network of `n` nodes. Each edge is a tuple `(u, v, w)`, representing the time it takes for a signal to travel from node `u` to `v`. Find the time it takes for all nodes to receive the signal from a starting node.

**Input:**
```
Edges = [(2, 1, 1), (2, 3, 1), (3, 4, 1)]
N = 4, K = 2
```

**Expected Output:**
```
2
```

### 🔑 **Solution Steps**
1. Build an adjacency list.
2. Apply Dijkstra's Algorithm from the starting node.
3. Return the maximum time taken to reach any node.

### ✅ **Code:**
```java
public static int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] time : times) {
        graph.computeIfAbsent(time[0], k -> new ArrayList<>()).add(new int[]{time[1], time[2]});
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[]{K, 0});
    Map<Integer, Integer> dist = new HashMap<>();

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int node = current[0], time = current[1];
        if (dist.containsKey(node)) continue;
        dist.put(node, time);
        for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!dist.containsKey(neighbor[0])) {
                pq.offer(new int[]{neighbor[0], time + neighbor[1]});
            }
        }
    }

    if (dist.size() != N) return -1;
    return Collections.max(dist.values());
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O((V + E) log V) — Efficient for sparse graphs.
- **Space:** O(V) — Distance and priority queue.

---

## 📚 Key Takeaways

1. Use Dijkstra’s Algorithm for shortest path problems in graphs with non-negative weights.
2. Time complexity is O((V + E) log V) using a priority queue.
3. It is highly efficient for sparse graphs.
4. The algorithm can be modified to return either distances or actual paths.

---

Next, let's dive deep into **Merge Intervals** for solving problems that involve combining overlapping ranges efficiently!