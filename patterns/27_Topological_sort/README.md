# Topological Sort Pattern

## 🎯 Introduction

Imagine you need to schedule a sequence of tasks where some tasks must be completed before others can begin. The **Topological Sort** pattern is a powerful algorithm for ordering vertices in a directed graph such that for every directed edge (u, v), vertex u comes before vertex v in the ordering.

The Topological Sort Pattern is particularly useful for:
- Task scheduling with dependencies
- Course prerequisite problems
- Build systems and package dependencies
- Data processing workflows
- Determining a valid sequence in any system with dependencies

This pattern works only for **Directed Acyclic Graphs (DAGs)** - graphs with directed edges and no cycles.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Is there a dependency relationship between elements?
   - Can the problem be modeled as a directed graph?
   - Is finding a valid ordering or sequence part of the solution?

2. **Ask Clarifying Questions:**
   - Is the input guaranteed to be a DAG (no cycles)?
   - What should be returned if cycles exist?
   - How are the dependencies represented in the input?

3. **Identify Clues for Using Topological Sort:**
   - Keywords like "prerequisites," "dependencies," "order," "sequence"
   - Phrases like "X must happen before Y"
   - Relationship constraints that suggest directional flow

4. **Predicting if Topological Sort Is Applicable:**
   - The problem involves ordering based on dependencies
   - The relationships can be modeled as a directed graph
   - A valid sequence is required that respects all constraints

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- How are the vertices and edges represented?
- Is there a specific start or end point required?
- What should be returned if multiple valid orderings exist?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned if a cycle is detected (invalid topological sort)?
- How to handle isolated vertices?

### ✅ **3. Identify Base Cases**
- Empty graph: Return empty result
- Graph with only one vertex: Return that vertex
- Graph with no edges: Any ordering of vertices is valid

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function topologicalSort(graph):
    initialize indegree map for all vertices
    
    // Calculate indegree for each vertex
    for each edge (u,v) in graph:
        increment indegree[v]
    
    // Initialize queue with all vertices that have indegree 0
    initialize queue with vertices having indegree 0
    
    initialize result array
    
    while queue is not empty:
        vertex = dequeue from queue
        add vertex to result
        
        for each neighbor of vertex:
            decrement indegree[neighbor]
            if indegree[neighbor] == 0:
                enqueue neighbor to queue
    
    // Check if all vertices are included in the result
    if result.size == number of vertices:
        return result
    else:
        return "Cycle detected" or empty result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {
        // Create adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Create indegree array
        int[] indegree = new int[vertices];
        
        // Build the graph
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }
        
        // Initialize queue with all vertices that have 0 indegree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> sortedOrder = new ArrayList<>();
        
        // Process until the queue is empty
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            sortedOrder.add(vertex);
            
            // Reduce indegree of neighbors
            for (int neighbor : graph.get(vertex)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check if a valid topological sort exists
        if (sortedOrder.size() != vertices) {
            return new ArrayList<>(); // Cycle detected
        }
        
        return sortedOrder;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Graph with cycles (no valid topological order)
- Disconnected graph (multiple independent components)
- Graph with multiple valid topological orderings
- Empty graph or graph with no edges
- Single vertex graph

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Building the graph      | O(V + E)        | O(V + E)         |
| BFS processing          | O(V + E)        | O(V)             |
| Overall                 | O(V + E)        | O(V + E)         |

**How to derive these complexities:**
- **Time Complexity:** O(V + E) where V is the number of vertices and E is the number of edges. We need to visit each vertex and each edge once.
- **Space Complexity:** O(V + E) for storing the adjacency list, indegree array, queue, and result.

---

## 📚 Example 1: Easy Problem - Course Schedule

**Problem:**
There are n courses labeled from 0 to n-1. Some courses have prerequisites. Determine if it's possible to finish all courses.

**Input:**
```
n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
```

**Expected Output:**
```
true
```

### 🔑 **Solution Steps**
1. Model the problem as a directed graph where each course is a vertex
2. Add an edge from prerequisite course to dependent course
3. Perform topological sort to check if all courses can be taken
4. If the sorted order contains all vertices, return true; otherwise, false

### ✅ **Code:**
```java
public static boolean canFinish(int numCourses, int[][] prerequisites) {
    // Create adjacency list
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
        graph.add(new ArrayList<>());
    }
    
    // Create indegree array
    int[] indegree = new int[numCourses];
    
    // Build the graph
    for (int[] prerequisite : prerequisites) {
        int course = prerequisite[0];
        int prereq = prerequisite[1];
        graph.get(prereq).add(course);
        indegree[course]++;
    }
    
    // Initialize queue with courses that have no prerequisites
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) {
            queue.offer(i);
        }
    }
    
    int count = 0;
    
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;
        
        for (int neighbor : graph.get(course)) {
            indegree[neighbor]--;
            if (indegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }
    
    return count == numCourses;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E) where V is the number of courses and E is the number of prerequisite relationships
- **Space:** O(V + E) for storing the graph and indegree array

---

## 📚 Example 2: Medium Problem - Course Schedule II

**Problem:**
Return the ordering of courses you should take to finish all courses. If it's impossible to finish all courses, return an empty array.

**Input:**
```
n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
```

**Expected Output:**
```
[0,1,2,3] or [0,2,1,3]
```

### 🔑 **Solution Steps**
1. Model the problem as a directed graph
2. Perform topological sort
3. Return the ordering of courses if possible
4. Return empty array if cycle detected

### ✅ **Code:**
```java
public static int[] findOrder(int numCourses, int[][] prerequisites) {
    // Create adjacency list
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
        graph.add(new ArrayList<>());
    }
    
    // Create indegree array
    int[] indegree = new int[numCourses];
    
    // Build the graph
    for (int[] prerequisite : prerequisites) {
        int course = prerequisite[0];
        int prereq = prerequisite[1];
        graph.get(prereq).add(course);
        indegree[course]++;
    }
    
    // Initialize queue with courses that have no prerequisites
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) {
            queue.offer(i);
        }
    }
    
    int[] order = new int[numCourses];
    int index = 0;
    
    while (!queue.isEmpty()) {
        int course = queue.poll();
        order[index++] = course;
        
        for (int neighbor : graph.get(course)) {
            indegree[neighbor]--;
            if (indegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }
    
    if (index != numCourses) {
        return new int[0]; // Cycle detected
    }
    
    return order;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(V + E) — Linear in the number of courses and prerequisites
- **Space:** O(V + E) — For graph, indegree array, and result

---

## 📚 Example 3: Hard Problem - Alien Dictionary

**Problem:**
Given a sorted dictionary of an alien language, find the order of characters in the alien language.

**Input:**
```
words = ["wrt", "wrf", "er", "ett", "rftt"]
```

**Expected Output:**
```
"wertf"
```

### 🔑 **Solution Steps**
1. Build a graph where characters are vertices
2. Add edges based on the first different characters between adjacent words
3. Perform topological sort on the character graph
4. Return the sorted order as a string, or empty string if impossible

### ✅ **Code:**
```java
public static String alienOrder(String[] words) {
    // Create a graph of characters
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    
    // Initialize the graph with all unique characters
    for (String word : words) {
        for (char c : word.toCharArray()) {
            graph.putIfAbsent(c, new ArrayList<>());
            indegree.putIfAbsent(c, 0);
        }
    }
    
    // Build the graph based on adjacent words
    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];
        
        // Check if word2 is a prefix of word1, which is invalid
        if (word1.length() > word2.length() && word1.startsWith(word2)) {
            return "";
        }
        
        // Find the first different character
        for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
            if (word1.charAt(j) != word2.charAt(j)) {
                graph.get(word1.charAt(j)).add(word2.charAt(j));
                indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
                break;
            }
        }
    }
    
    // Perform topological sort
    Queue<Character> queue = new LinkedList<>();
    for (Character c : indegree.keySet()) {
        if (indegree.get(c) == 0) {
            queue.offer(c);
        }
    }
    
    StringBuilder result = new StringBuilder();
    
    while (!queue.isEmpty()) {
        char c = queue.poll();
        result.append(c);
        
        for (char neighbor : graph.get(c)) {
            indegree.put(neighbor, indegree.get(neighbor) - 1);
            if (indegree.get(neighbor) == 0) {
                queue.offer(neighbor);
            }
        }
    }
    
    // Check if all characters are included
    if (result.length() != indegree.size()) {
        return ""; // Cycle detected
    }
    
    return result.toString();
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(C) where C is the total length of all words combined
- **Space:** O(1) or O(U) where U is the number of unique characters in the alien alphabet (at most 26)

---

## 📚 Key Takeaways

1. Topological Sort is used for problems involving **ordering with dependencies**.
2. The algorithm only works on **Directed Acyclic Graphs (DAGs)**.
3. Two main approaches for implementation:
   - **Kahn's Algorithm** (BFS-based, using indegree)
   - **DFS with cycle detection**
4. Time complexity is **O(V + E)** for both approaches.
5. If the topological sort doesn't include all vertices, there is a cycle in the graph.

---

Next, lets dive deep into **Stacks**.