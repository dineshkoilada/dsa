# Topological Sort Pattern

## 🎯 Introduction

Imagine you have a list of tasks, and some tasks must be completed before others (like prerequisites in a course). How do you figure out a valid order to complete all tasks? The **Topological Sort** pattern helps solve problems involving dependencies in **Directed Acyclic Graphs (DAGs)**.

Topological Sort is particularly useful for:
- Scheduling tasks with dependencies (e.g., course scheduling)
- Build systems (resolving dependencies between modules)
- Detecting cycles in directed graphs
- Determining a valid order of tasks in project management

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are there dependencies between elements?
   - Does the order of execution matter?

2. **Ask Clarifying Questions:**
   - Is the graph directed?
   - Does the graph have cycles (must be acyclic for topological sorting)?
   - Are all tasks connected, or are there isolated nodes?

3. **Identify Clues for Using Topological Sort:**
   - You need to determine the order of tasks or events.
   - Dependencies exist, and an order must be followed.
   - Detect cycles in a directed graph.

4. **Predicting if Topological Sort Is Applicable:**
   - Is the graph directed and acyclic?
   - Does the problem require ordering elements based on dependencies?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Is the graph directed?
- Are there dependencies that must be respected?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Are there any isolated nodes?
- Can dependencies form a cycle?

### ✅ **3. Identify Base Cases**
- No dependencies (return any order).
- A cycle in the graph means no valid topological sort exists.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function topologicalSort(graph):
    initialize in-degree of all nodes to 0
    for each node in graph:
        for each neighbor:
            increase in-degree of neighbor by 1
    initialize queue with nodes having in-degree 0
    while queue is not empty:
        node = dequeue from queue
        add node to result list
        for each neighbor:
            decrease in-degree by 1
            if in-degree becomes 0:
                add neighbor to queue
    if result size equals number of nodes:
        return result
    else:
        return error (cycle detected)
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class TopologicalSort {
    public static List<Integer> topoSort(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != n) {
            throw new IllegalStateException("Cycle detected");
        }

        return result;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The graph contains a cycle.
- Isolated nodes.
- Multiple valid topological orders.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                  | Time Complexity | Space Complexity |
|----------------------------|-----------------|------------------|
| Building graph             | O(E + V)        | O(E + V)         |
| Topological Sort (BFS/DFS) | O(E + V)        | O(V)             |

**How to derive these complexities:**
- **Time Complexity:** Each node and edge is visited once.
- **Space Complexity:** Space for the graph and in-degree array.

---

## 📚 Example 1: Easy Problem - Course Schedule

**Problem:**
Determine if you can finish all courses given prerequisites.

**Input:** `numCourses = 2, prerequisites = [[1, 0]]`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. Build a graph based on prerequisites.
2. Perform topological sort to check for cycles.

### ✅ **Code:**
```java
public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visited++;
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return visited == numCourses;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(E + V) — Each course and prerequisite is processed once.
- **Space:** O(E + V) — Storing the graph and in-degree array.

---

## 📚 Example 2: Medium Problem - Course Schedule II

**Problem:**
Return the order of courses to take to complete all prerequisites.

**Input:** `numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]`

**Expected Output:** `[0, 1, 2, 3]` or `[0, 2, 1, 3]`

### 🔑 **Solution Steps**
1. Build the graph using prerequisites.
2. Use topological sort to return a valid order.

### ✅ **Code:**
```java
public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[index++] = course;
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return index == numCourses ? order : new int[0];
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(E + V) — Each node and edge is processed once.
- **Space:** O(E + V) — Storing the graph and order array.

---

## 📚 Example 3: Hard Problem - Alien Dictionary

**Problem:**
Determine the order of letters in an alien language based on sorted words.

**Input:** `words = ["wrt", "wrf", "er", "ett", "rftt"]`

**Expected Output:** `"wertf"`

### 🔑 **Solution Steps**
1. Build a graph based on the relative order of characters.
2. Perform topological sort on the graph.

### ✅ **Code:**
```java
public class AlienDictionary {
    public static String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
                char c1 = first.charAt(j);
                char c2 = second.charAt(j);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    inDegree.put(c2, inDegree.get(c2) + 1);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return sb.length() == inDegree.size() ? sb.toString() : "";
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N) — Each letter and edge is processed once.
- **Space:** O(N) — Storing the graph and in-degree array.

---

## 📚 Key Takeaways

1. Use Topological Sort for problems involving dependencies in directed graphs.
2. Detect cycles by checking if a valid topological sort exists.
3. Use Kahn’s algorithm (BFS) or DFS for topological sorting.
4. Applicable to scheduling, ordering tasks, and dependency resolution problems.

---

Next, let's dive into the **Heap/Priority Queue Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

