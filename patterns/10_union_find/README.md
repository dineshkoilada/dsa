# Union-Find (Disjoint Set Union) Pattern 🤝

## 📌 Introduction: The Power of Grouping

Imagine a school where every student starts in their own club. As the year goes on, clubs merge—sometimes two, sometimes more. How do you quickly check if two students are now in the same club, or efficiently merge two clubs together? This is the magic of the **Union-Find** (Disjoint Set Union, DSU) pattern!

### 🎬 Real-World Analogies:

1. **Friend Circles** 🧑‍🤝‍🧑
   - Each person starts alone. When two become friends, their circles merge. Are you and your friend in the same circle?
   ```
   [A]   [B]   [C]   [D]
    |     |     |     |
   (alone)
   After A-B merge:
   [A-B] [C] [D]
   After B-C merge:
   [A-B-C] [D]
   ```

2. **Passport Control** 🛂
   - Each country is a set. When two countries form a union, their citizens can travel freely. Are two people from the same travel zone?

3. **Social Media Groups** 📱
   - Users join groups, and groups can merge. Who can see each other's posts?

The Union-Find pattern is your go-to for:
- 🔗 Tracking connected components in a network
- 🔄 Merging groups efficiently
- 🔍 Checking if two elements are in the same group
- 🕸️ Detecting cycles in undirected graphs
- 🌉 Kruskal’s algorithm for Minimum Spanning Trees

---

## 🧠 How to Recognize a Union-Find Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Grouping" Clue**
   - "Merge", "union", or "connect" operations between elements
   - "Are these two in the same group?"
2. **The "Dynamic Connectivity" Hint**
   - The network changes over time (edges/nodes added)
   - You need to answer queries about connectivity quickly
3. **The "Cycle Detection" Signal**
   - Detecting cycles in undirected graphs
   - Building a Minimum Spanning Tree (MST)

### 🤔 Essential Questions to Ask:
- Are all elements initially in separate sets?
- How are sets merged? (Pairwise, batch, etc.)
- Are you only merging, or also querying group membership?
- Is the network static or dynamic?

---

## 🎨 Visual Problem-Solving Framework

### Club Merging Visual:
```
Initial:   [A] [B] [C] [D] [E]
Union(A,B): [A-B] [C] [D] [E]
Union(B,C): [A-B-C] [D] [E]
Union(D,E): [A-B-C] [D-E]

Find(A): Trace up to root of [A-B-C]
Find(E): Trace up to root of [D-E]

Are A and C connected? YES (same root)
Are B and D connected? NO (different roots)
```

### Tree/Forest Representation:
```
Each set is a tree. Each node points to its parent. The root is the representative.

   0   1   2   3   4
   |   |   |   |   |
  (0) (1) (2) (3) (4)

After union(0,1):
   0   1   2   3   4
   |   |   |   |   |
  (0)-(1) (2) (3) (4)

After union(1,2):
   0   1   2   3   4
   |   |   |   |   |
  (0)-(1)-(2) (3) (4)

Path compression flattens the tree for fast future queries!
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you tracking connected components?
- Are you merging elements from different groups?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Are elements initially in separate groups?
- Can elements belong to more than one group?

### ✅ **3. Identify Base Cases**
- Initially, every element belongs to its own set.
- Merging sets involves updating the representative (root).

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function initialize(n):
    for each node from 0 to n-1:
        parent[node] = node
        rank[node] = 1

function find(x):
    if parent[x] != x:
        parent[x] = find(parent[x]) // Path compression
    return parent[x]

function union(x, y):
    rootX = find(x)
    rootY = find(y)
    if rootX != rootY:
        if rank[rootX] > rank[rootY]:
            parent[rootY] = rootX
        else if rank[rootX] < rank[rootY]:
            parent[rootX] = rootY
        else:
            parent[rootY] = rootX
            rank[rootX] += 1
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

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
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Union of already connected components.
- Checking connectivity when nodes are isolated.
- Duplicate connections.
- Self-union (union(x, x)).

### ✅ **7. How to Predict Time and Space Complexity**

| Operation     | Time Complexity | Space Complexity |
|---------------|-----------------|------------------|
| Initialization| O(n)            | O(n)             |
| Find          | O(α(n))         | O(1)             |
| Union         | O(α(n))         | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Almost constant time due to path compression and union by rank (α(n) is the inverse Ackermann function, which grows extremely slowly).
- **Space Complexity:** Arrays store parent and rank information.

---

## 📚 Example 1: Easy Problem - Find Connected Components

**Problem:**
Given `n` nodes and a list of edges, count the number of connected components.

**Input:** `n = 5, edges = [[0, 1], [1, 2], [3, 4]]`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Initialize Union-Find for all nodes.
2. Merge nodes that are directly connected.
3. Count distinct parents.

### ✅ **Code:**
```java
public class CountComponents {
    public static int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            uniqueRoots.add(uf.find(i));
        }

        return uniqueRoots.size();
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(E + V) — Union operations for edges and finding roots for vertices.
- **Space:** O(V) — Parent and rank arrays.

---

## 📚 Example 2: Medium Problem - Detect Cycle in an Undirected Graph

**Problem:**
Detect if an undirected graph has a cycle.

**Input:** `n = 4, edges = [[0, 1], [1, 2], [2, 3], [3, 0]]`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. Initialize Union-Find for all nodes.
2. For each edge, check if the two nodes already share the same root.
3. If they do, a cycle exists.

### ✅ **Code:**
```java
public class DetectCycle {
    public static boolean hasCycle(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return true; // Cycle detected
            }
            uf.union(edge[0], edge[1]);
        }
        return false;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(E + V) — Each edge requires constant-time find and union operations.
- **Space:** O(V) — Parent and rank arrays.

---

## 📚 Example 3: Hard Problem - Accounts Merge

**Problem:**
Merge accounts if two accounts share the same email.

**Input:**
```
[
  ["John", "johnsmith@mail.com", "john00@mail.com"],
  ["John", "johnnybravo@mail.com"],
  ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
  ["Mary", "mary@mail.com"]
]
```

**Expected Output:**
```
[
  ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
  ["John", 'johnnybravo@mail.com'],
  ["Mary", 'mary@mail.com']
]
```

### 🔑 **Solution Steps**
1. Map emails to their respective owners.
2. Use Union-Find to merge emails belonging to the same user.
3. Group emails by their root.

### ✅ **Code:**
```java
import java.util.*;

public class AccountsMerge {
    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(10000);
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                emailToName.put(email, name);
                uf.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> components = new HashMap<>();
        for (String email : emailToID.keySet()) {
            int root = uf.find(emailToID.get(email));
            components.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> emails : components.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            emails.add(0, name);
            result.add(emails);
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N log N) — Sorting emails and performing union-find operations.
- **Space:** O(N) — Storing mappings and union-find arrays.

---

## 📚 Key Takeaways

1. Use Union-Find for problems involving merging sets, dynamic connectivity, or cycle detection in undirected graphs.
2. Path compression and union by rank make operations nearly constant time.
3. Visualize sets as trees/forests—flattening trees speeds up queries.
4. Union-Find is a backbone for Kruskal’s MST, connected components, and social network problems.

---

Next, let’s explore the **Topological Sort Pattern** for ordering tasks and resolving dependencies!

