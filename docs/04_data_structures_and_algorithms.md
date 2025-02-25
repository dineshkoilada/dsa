# Data Structures and Algorithms (DSA)

## 🎯 Introduction

Data Structures and Algorithms (DSA) form the foundation of computer science and software development. They are essential for building efficient, scalable, and optimized applications.

Understanding DSA is not just about solving problems—it's about solving them efficiently using the best possible resources in terms of time and space. This guide will help you master the **20% of DSA concepts** that will cover **80% of coding interview questions**.

---

## 🔍 What Are Data Structures?

A **data structure** is a specialized format for organizing, processing, and storing data. Different data structures are suited for different kinds of applications, and understanding their strengths and weaknesses is key.

### ✅ Common Types of Data Structures:

1. **Linear Data Structures:**
   - Arrays
   - Linked Lists
   - Stacks
   - Queues

2. **Non-Linear Data Structures:**
   - Trees
   - Graphs

3. **Hash-Based Structures:**
   - Hash Tables (HashMap, HashSet)

4. **Specialized Structures:**
   - Heaps (PriorityQueue)
   - Tries

### 📌 Example: Using an Array
```java
public class ArrayExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println(num);
        }
    }
}
```

---

## 🔗 What Are Algorithms?

An **algorithm** is a step-by-step procedure to solve a problem efficiently.

### ✅ Characteristics of a Good Algorithm:
- **Correctness**: Should provide accurate output for all valid inputs.
- **Efficiency**: Uses minimal time and memory resources.
- **Finiteness**: Terminates after a finite number of steps.
- **Determinism**: Produces the same output for the same input every time.

### 📌 Example: Binary Search Algorithm
```java
public class BinarySearch {
    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
```

---

## 🎯 Real-World Analogies

- **Array**: Like a bookshelf—access by position.
- **Stack**: Like a stack of plates—Last In, First Out (LIFO).
- **Queue**: Like a line at a ticket counter—First In, First Out (FIFO).
- **Tree**: Like a family tree—hierarchical structure.
- **Graph**: Like a social network—connections between nodes.

---

## ⚡ Essential Algorithms to Master

1. **Sorting Algorithms**
   - Bubble Sort (O(n²))
   - Merge Sort (O(n log n))
   - Quick Sort (O(n log n))

2. **Searching Algorithms**
   - Linear Search (O(n))
   - Binary Search (O(log n))

3. **Graph Algorithms**
   - Depth-First Search (DFS) (O(V + E))
   - Breadth-First Search (BFS) (O(V + E))
   - Dijkstra’s Algorithm (O((V + E) log V))

4. **Dynamic Programming (DP)**
   - Fibonacci Sequence
   - Longest Common Subsequence (LCS)
   - Knapsack Problem

5. **Backtracking Algorithms**
   - Permutations
   - N-Queens Problem

---

## 📊 Time and Space Complexity (Quick Recap)

- **O(1)**: Constant Time (e.g., Accessing an element by index in an array)
- **O(log n)**: Logarithmic Time (e.g., Binary Search)
- **O(n)**: Linear Time (e.g., Linear Search)
- **O(n log n)**: Linearithmic Time (e.g., Merge Sort)
- **O(n²)**: Quadratic Time (e.g., Bubble Sort)

**Space Complexity** follows similar notations and is critical when handling large datasets.

---

## 📌 Example: Implementing a Stack

```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // 30
        stack.pop();
        System.out.println("After pop: " + stack.peek()); // 20
    }
}
```

---

## 📚 Key Takeaways

1. **Data Structures** help organize and manage data efficiently.
2. **Algorithms** provide a systematic approach to solving problems.
3. Choosing the right data structure or algorithm significantly improves performance.
4. Master basic DSA concepts before diving into advanced topics like dynamic programming and graph theory.

---

Up next: Understand **Time and Space Complexities** in-depth to learn how to evaluate the efficiency of your code.

