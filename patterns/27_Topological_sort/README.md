# Topological Sort Pattern 🎯

## 📌 Introduction: The Power of Dependency Ordering

Imagine you’re building a house: you can’t paint the walls before the walls are built, and you can’t build the walls before the foundation is laid. The **Topological Sort Pattern** is your toolkit for finding a valid order to complete tasks when some tasks depend on others—essential for scheduling, dependency resolution, and workflow management!

### 🎬 Real-World Analogies:

1. **House Construction** 🏠
   ```
   Steps: [Foundation][Walls][Roof][Paint]
   Order: Foundation → Walls → Roof → Paint
   ```
2. **Course Prerequisites** 🎓
   ```
   Courses: [Math][Physics][Algorithms]
   Prereqs: Math → Physics → Algorithms
   ```
3. **Recipe Steps** 🍳
   ```
   Steps: [Chop][Cook][Serve]
   Order: Chop → Cook → Serve
   ```

The Topological Sort pattern is your secret weapon when you need:
- 📍 To order tasks with dependencies
- 🔄 To resolve build/package dependencies
- 🧩 To process workflows or pipelines
- 🚦 To detect cycles in dependency graphs

### 🎯 Visual Example:
Ordering tasks with dependencies:
```
Tasks:   [A, B, C, D]
Edges:   A → B, A → C, B → D, C → D
Order:   A → B → C → D or A → C → B → D
```

---

## 🧠 How to Recognize a Topological Sort Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Dependency" Clue** 📑
   - "Task X must be done before Y"
   - "Prerequisites," "dependencies," "order," "sequence"

2. **The "Directed Graph" Hint** ⚡
   - "Directed edges," "DAG," "no cycles"

3. **The "Valid Sequence" Signal** 🎯
   - "Find a valid order to complete all tasks"
   - "Detect if a cycle exists"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   How are tasks and dependencies represented?
   Is the input guaranteed to be a DAG?
   What should be returned if a cycle exists?
   ```
2. **Content Questions:**
   ```
   Are there multiple valid orderings?
   Are there isolated tasks (no dependencies)?
   ```
3. **Edge Case Questions:**
   ```
   What if the graph is empty or has one node?
   What if there are disconnected components?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Build the graph and indegree map
[🟦][⬜][⬜][⬜]  👈 Each node/task

Step 2: Add nodes with indegree 0 to queue
[🟦][⬜][⬜][⬜]  👈 Ready to process

Step 3: Remove from queue, add to result, decrement neighbors' indegree
[⬜][🟦][⬜][⬜]  👈 Process next available task

Step 4: Repeat until queue is empty
[⬜][⬜][🟦][⬜]  👈 Continue until all tasks are ordered

Step 5: If all nodes processed, return result; else, cycle detected
[🟦][🟦][🟦][🟦]  👈 Valid topological order or report cycle
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- How are tasks and dependencies represented?
- What should be returned if a cycle is detected?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How to handle isolated tasks?
- What if there are multiple valid orderings?

### ✅ **3. Identify Base Cases**
- Empty graph: return empty result
- Single node: return that node
- No edges: any order is valid

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function topologicalSort(graph):
    build indegree map
    queue = nodes with indegree 0
    result = []
    while queue not empty:
        node = queue.pop()
        result.append(node)
        for neighbor in node.neighbors:
            indegree[neighbor]--
            if indegree[neighbor] == 0:
                queue.append(neighbor)
    if result.size == number of nodes:
        return result
    else:
        return "Cycle detected"
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());
        int[] indegree = new int[vertices];
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) if (indegree[i] == 0) queue.offer(i);
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }
        if (order.size() != vertices) return new ArrayList<>(); // Cycle detected
        return order;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Graph with cycles
- Disconnected graph
- Multiple valid orderings
- Empty graph or single node

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Build graph       | O(V + E)        | O(V + E)         |
| BFS processing    | O(V + E)        | O(V)             |
| Overall           | O(V + E)        | O(V + E)         |

**How to derive these complexities:**
- **Time:** Each node and edge is processed once
- **Space:** For adjacency list, indegree array, queue, and result

---

## 📚 Example 1: Easy Problem - Course Schedule

**Problem:**
There are n courses labeled from 0 to n-1. Some courses have prerequisites. Determine if it's possible to finish all courses.

**Input:**
n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]

**Expected Output:**
true

### 🔑 **Solution Steps**
1. Model as a graph, add edges for prerequisites
2. Perform topological sort
3. If all courses are included, return true; else, false

### ✅ **Code:**
```java
public static boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
    int[] indegree = new int[numCourses];
    for (int[] pre : prerequisites) {
        int course = pre[0], prereq = pre[1];
        graph.get(prereq).add(course);
        indegree[course]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);
    int count = 0;
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;
        for (int neighbor : graph.get(course)) {
            indegree[neighbor]--;
            if (indegree[neighbor] == 0) queue.offer(neighbor);
        }
    }
    return count == numCourses;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E)
- **Space:** O(V + E)

---

## 📚 Example 2: Medium Problem - Course Schedule II

**Problem:**
Return the ordering of courses you should take to finish all courses. If impossible, return an empty array.

**Input:**
n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]

**Expected Output:**
[0,1,2,3] or [0,2,1,3]

### 🔑 **Solution Steps**
1. Model as a graph
2. Perform topological sort
3. Return ordering if possible, else empty array

### ✅ **Code:**
```java
public static int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
    int[] indegree = new int[numCourses];
    for (int[] pre : prerequisites) {
        int course = pre[0], prereq = pre[1];
        graph.get(prereq).add(course);
        indegree[course]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);
    int[] order = new int[numCourses];
    int idx = 0;
    while (!queue.isEmpty()) {
        int course = queue.poll();
        order[idx++] = course;
        for (int neighbor : graph.get(course)) {
            indegree[neighbor]--;
            if (indegree[neighbor] == 0) queue.offer(neighbor);
        }
    }
    if (idx != numCourses) return new int[0];
    return order;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E)
- **Space:** O(V + E)

---

## 📚 Example 3: Hard Problem - Alien Dictionary

**Problem:**
Given a sorted dictionary of an alien language, find the order of characters in the alien language.

**Input:**
words = ["wrt", "wrf", "er", "ett", "rftt"]

**Expected Output:**
"wertf"

### 🔑 **Solution Steps**
1. Build a graph of characters
2. Add edges based on first different character between adjacent words
3. Perform topological sort
4. Return order as string, or empty if impossible

### ✅ **Code:**
```java
public static String alienOrder(String[] words) {
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    for (String word : words) {
        for (char c : word.toCharArray()) {
            graph.putIfAbsent(c, new ArrayList<>());
            indegree.putIfAbsent(c, 0);
        }
    }
    for (int i = 0; i < words.length - 1; i++) {
        String w1 = words[i], w2 = words[i+1];
        if (w1.length() > w2.length() && w1.startsWith(w2)) return "";
        for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
            if (w1.charAt(j) != w2.charAt(j)) {
                graph.get(w1.charAt(j)).add(w2.charAt(j));
                indegree.put(w2.charAt(j), indegree.get(w2.charAt(j)) + 1);
                break;
            }
        }
    }
    Queue<Character> queue = new LinkedList<>();
    for (char c : indegree.keySet()) if (indegree.get(c) == 0) queue.offer(c);
    StringBuilder result = new StringBuilder();
    while (!queue.isEmpty()) {
        char c = queue.poll();
        result.append(c);
        for (char neighbor : graph.get(c)) {
            indegree.put(neighbor, indegree.get(neighbor) - 1);
            if (indegree.get(neighbor) == 0) queue.offer(neighbor);
        }
    }
    if (result.length() != indegree.size()) return "";
    return result.toString();
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(C) where C is total length of all words
- **Space:** O(U) where U is number of unique characters

---

## 📚 Key Takeaways

1. Topological Sort is essential for problems involving **ordering with dependencies**.
2. Only works on **Directed Acyclic Graphs (DAGs)**.
3. Two main approaches: **Kahn's Algorithm** (BFS) and **DFS with cycle detection**.
4. Time complexity is **O(V + E)**.
5. If not all nodes are included, a cycle exists.

---

Next, let’s explore the **Stack Pattern** for solving problems involving LIFO order and expression evaluation!