# Evaluate Division

## 📌 Problem Statement

**LeetCode Problem:** [399. Evaluate Division](https://leetcode.com/problems/evaluate-division/)  
**Difficulty:** Medium  

**Description:**  
You are given an array of variable pairs `equations` and an array of real numbers `values`, where `equations[i] = [Ai, Bi]` and `values[i]` represent the equation `Ai / Bi = values[i]`. Each `Ai` or `Bi` is a string that represents a single variable.

You are also given some `queries`, where `queries[j] = [Cj, Dj]` represents the `jth` query where you must find the answer for `Cj / Dj = ?`.

Return the answers to all queries. If a single answer cannot be determined, return `-1.0`.

### **Example 1:**
**Input:** 
```
equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
```
**Output:** 
```
[6.00000,0.50000,-1.00000,1.00000,-1.00000]
```
**Explanation:**  
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
We can calculate:
- a / c = (a / b) * (b / c) = 2.0 * 3.0 = 6.0
- b / a = 1 / (a / b) = 1 / 2.0 = 0.5
- a / e and x / x: cannot be determined, so we return -1.0
- a / a = 1.0 (any number divided by itself is 1)

### **Example 2:**
**Input:** 
```
equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
```
**Output:** 
```
[3.75000,0.40000,5.00000,0.20000]
```

### **Constraints:**
- `1 <= equations.length <= 20`
- `equations[i].length == 2`
- `1 <= Ai.length, Bi.length <= 5`
- `values.length == equations.length`
- `0.0 < values[i] <= 20.0`
- `1 <= queries.length <= 20`
- `queries[i].length == 2`
- `1 <= Cj.length, Dj.length <= 5`
- `Ai, Bi, Cj, Dj` consist of lowercase English letters and digits.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine we have some rules about how numbers are related. For example, if we know that "a" is 2 times "b" and "b" is 3 times "c", then we can figure out that "a" is 6 times "c".

These rules are like equations: a/b = 2 means "a" is 2 times "b".

Now, people will ask questions like "What is a divided by c?" and we need to use our rules to figure out the answers. If we can't figure out an answer from our rules, we say "-1".

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we connect relationships between variables?**
   - We can build a **graph** where each variable is a node, and the division relationship is a directed edge with weight.
2. **How do we calculate indirect relationships?**
   - If a/b = 2 and b/c = 3, then a/c = 2 × 3 = 6. We're multiplying values along a **path in the graph**.
3. **What if we need to calculate b/a when we only know a/b?**
   - We can take the **reciprocal**: b/a = 1/(a/b) = 1/2 = 0.5.

👉 The key insight is that we're working with a graph where edges represent division relationships!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Build a graph where nodes are variables and edges represent division relationships
```java
Map<String, Map<String, Double>> graph = new HashMap<>();
for (int i = 0; i < equations.size(); i++) {
    String a = equations.get(i).get(0);
    String b = equations.get(i).get(1);
    double value = values[i];
    
    // Add both directions to the graph
    graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, value);
    graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / value);
}
```

### Step 2: For each query, perform DFS to find the path and calculate the result
```java
for (List<String> query : queries) {
    String start = query.get(0);
    String end = query.get(1);
    
    if (!graph.containsKey(start) || !graph.containsKey(end)) {
        result.add(-1.0);
        continue;
    }
    
    Set<String> visited = new HashSet<>();
    double[] answer = new double[]{-1.0};
    dfs(graph, start, end, 1.0, visited, answer);
    result.add(answer[0]);
}
```

### Step 3: Implement the DFS function to calculate division result
```java
private void dfs(Map<String, Map<String, Double>> graph, String start, String end, 
                double product, Set<String> visited, double[] result) {
    if (visited.contains(start)) return;
    if (start.equals(end)) {
        result[0] = product;
        return;
    }
    
    visited.add(start);
    for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
        dfs(graph, neighbor.getKey(), end, product * neighbor.getValue(), visited, result);
    }
}
```

---

## 🛠️ Writing the Complete Solution

```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, 
                               List<List<String>> queries) {
        // Build the graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];
            
            // Add both directions to the graph
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, value);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / value);
        }
        
        // Process queries using DFS
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            
            // If either variable is not in the graph, result is -1.0
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                result[i] = -1.0;
                continue;
            }
            
            // Special case: if start equals end
            if (start.equals(end)) {
                result[i] = 1.0;
                continue;
            }
            
            // DFS to find path from start to end
            Set<String> visited = new HashSet<>();
            result[i] = dfs(graph, start, end, 1.0, visited);
        }
        
        return result;
    }
    
    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, 
                     double product, Set<String> visited) {
        visited.add(start);
        
        // If no neighbors or already visited all neighbors
        if (!graph.containsKey(start)) {
            return -1.0;
        }
        
        // Check all neighbors
        for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
            String next = neighbor.getKey();
            if (next.equals(end)) {
                return product * neighbor.getValue();
            }
            
            if (!visited.contains(next)) {
                double result = dfs(graph, next, end, product * neighbor.getValue(), visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }
        
        return -1.0;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(Q * (V + E))`, where Q is the number of queries, V is the number of variables, and E is the number of equations. For each query, we might need to traverse the entire graph.
- **Space Complexity:** `O(V + E)` for the graph storage and visited set.

---

## 🚀 Optimized Solution using Union-Find

```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, 
                               List<List<String>> queries) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Double> weight = new HashMap<>();
        
        // Initialize DSU
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            
            // Add nodes to DSU if they don't exist
            if (!parent.containsKey(a)) {
                parent.put(a, a);
                weight.put(a, 1.0);
            }
            if (!parent.containsKey(b)) {
                parent.put(b, b);
                weight.put(b, 1.0);
            }
            
            // Union the nodes
            union(parent, weight, a, b, values[i]);
        }
        
        // Process queries
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            
            if (!parent.containsKey(a) || !parent.containsKey(b)) {
                result[i] = -1.0;
            } else {
                String rootA = find(parent, weight, a);
                String rootB = find(parent, weight, b);
                
                if (!rootA.equals(rootB)) {
                    result[i] = -1.0;
                } else {
                    result[i] = weight.get(a) / weight.get(b);
                }
            }
        }
        
        return result;
    }
    
    private String find(Map<String, String> parent, Map<String, Double> weight, String x) {
        if (!parent.get(x).equals(x)) {
            String oldParent = parent.get(x);
            parent.put(x, find(parent, weight, oldParent));
            weight.put(x, weight.get(x) * weight.get(oldParent));
        }
        return parent.get(x);
    }
    
    private void union(Map<String, String> parent, Map<String, Double> weight, 
                      String a, String b, double value) {
        String rootA = find(parent, weight, a);
        String rootB = find(parent, weight, b);
        
        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
            weight.put(rootA, value * weight.get(b) / weight.get(a));
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(Q + E)`, where Q is the number of queries and E is the number of equations. Union-Find operations are nearly constant time with path compression.
- **Space Complexity:** `O(V)` for storing the parent and weight maps.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I approached this problem using a **graph-based model** where variables are nodes and divisions are weighted edges."
- "For each equation a/b = v, I added two edges: from a to b with weight v, and from b to a with weight 1/v."
- "For each query, I performed a **DFS or Union-Find** to calculate the division result, which is the product of weights along the path."

If the interviewer asks for **alternative approaches**, you can say:
- "I've shown both a DFS approach and a Union-Find optimization."
- "DFS is more intuitive but can be slower for large graphs with many queries."
- "Union-Find with path compression offers better amortized performance for multiple queries."

---

## 🔥 Final Takeaways
- **Graph representation** is perfect for modeling relationships between variables.
- **Bidirectional edges** with reciprocal weights handle forward and backward division.
- **DFS or BFS** works well for finding paths and calculating results.
- **Union-Find** optimization can improve performance when dealing with many queries.
- **Edge cases** to consider include self-division (a/a = 1) and undefined variables.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/evaluate-division/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-sixth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.