# LeetCode Patterns Practice

## 📋 Introduction

Welcome to the **LeetCode Patterns Practice** repository! This project is designed to help you master essential problem-solving patterns that frequently appear in coding interviews, especially for roles at top tech companies like Meta, Google, Netflix, and Apple.

The primary goal of this repository is to provide:
- A structured way to practice coding problems based on common patterns.
- Clear explanations and Java code solutions for each pattern.
- A beginner-friendly learning path to strengthen your understanding of algorithms and data structures (DSA).

Whether you're just starting your coding journey or sharpening your skills for technical interviews, this project will help you build confidence and improve your problem-solving abilities.

## 🎯 Why Focus on Patterns?

Many coding interview questions follow common problem-solving patterns. Understanding these patterns will:
- Help you recognize problem types faster.
- Improve your efficiency in finding solutions.
- Increase your chances of success in technical interviews.

Instead of solving random problems, focusing on patterns allows you to develop a strategy that covers 80% of questions with just 20% of the effort—following the Pareto principle.

## 🏎️ Importance of Time Complexity and Space Complexity

In coding interviews and real-world software development, it's not just about solving the problem—it's about solving it efficiently.

**Time Complexity** refers to how the execution time of an algorithm grows relative to the input size. Writing time-efficient code helps:
- Handle large datasets effectively.
- Improve application performance.
- Meet industry standards for scalable solutions.

Common time complexities include:
- **O(1)** – Constant time: The execution time does not depend on the input size.
- **O(log n)** – Logarithmic time: Reduces the problem size at each step (e.g., Binary Search).
- **O(n)** – Linear time: Grows proportionally with the input size.
- **O(n log n)** – Linearithmic time: Often seen in efficient sorting algorithms (e.g., Merge Sort, Quick Sort).
- **O(n²)** – Quadratic time: Common in nested loops (e.g., Bubble Sort).
- **O(n³)** – Cubic time: Seen in problems with three levels of nested loops.
- **O(2^n)** – Exponential time: Algorithms that solve all subsets of the input (e.g., Backtracking problems).
- **O(n!)** – Factorial time: Found in permutation-based problems (e.g., Traveling Salesman Problem).

**Space Complexity** measures the amount of memory an algorithm uses relative to the input size. Optimizing space usage is essential for:
- Memory-constrained environments (like mobile devices).
- Improving application efficiency.
- Avoiding memory leaks and overflow issues.

Common space complexities:
- **O(1)** – Constant space: Memory usage remains the same regardless of input size.
- **O(log n)** – Logarithmic space: Often occurs with recursive algorithms that divide the input.
- **O(n)** – Linear space: Memory usage grows proportionally with the input size.
- **O(n log n)** – Linearithmic space: Seen in certain divide-and-conquer algorithms.
- **O(n²)** – Quadratic space: Common in problems involving 2D arrays (e.g., dynamic programming tables).

When solving problems, always consider:
- Can the algorithm be optimized to reduce time or space usage?
- Are there trade-offs between time and space efficiency?

Understanding these complexities is crucial for writing optimal code and succeeding in technical interviews.

## 🔍 What Will You Find in This Repository?

This repository is organized by problem-solving patterns, grouped for easier learning and practice:

### 1. Array & String Patterns
- **Two Pointers**: Efficiently narrow down solutions using two pointers.
- **Sliding Window**: Handle problems involving subarrays or substrings.
- **Prefix Sum / Suffix Sum**: Precompute sums for efficient range queries.
- **Sorting Algorithms**: Implement and use various sorting techniques.
- **Hashing & Frequency Counting**: Use hash maps/sets for fast lookups and counting.
- **String Manipulation & Pattern Matching**: Work with substrings, anagrams, and regular expressions.
- **Intervals**: Handle problems involving overlapping intervals, merging, or scheduling.
- **Cyclic Sort**: Sort numbers when elements are in a known range (e.g., 1 to n).

### 2. Linked List Patterns
- **Fast & Slow Pointers (Tortoise and Hare)**: Detect cycles and middle elements in linked lists.
- **Linked List Manipulation**: Solve problems involving singly or doubly linked lists.
- **Reversal Patterns**: Reverse entire or part of a linked list.

### 3. Stack & Queue Patterns
- **Stack-based Problems (Monotonic Stack)**: Use stacks to solve problems like Next Greater Element, histogram area, etc.
- **Queue-based Problems**: Use queues for level order traversal, sliding window maximum, etc.
- **Deque (Double-Ended Queue) Patterns**: Efficiently solve problems requiring access from both ends (e.g., sliding window maximum).

### 4. Tree & Graph Patterns
- **Depth-First Search (DFS)**: Explore tree or graph structures deeply.
- **Breadth-First Search (BFS)**: Explore all neighbors before diving deeper.
- **Graph Traversal (DFS/BFS/Topological Sort)**: Explore and process nodes in graphs.
- **Union Find (Disjoint Set Union)**: Efficiently handle dynamic connectivity problems.
- **Shortest Path Algorithms (Dijkstra, Floyd-Warshall, Bellman-Ford, A*)**: Find shortest paths in graphs.
- **Minimum Spanning Tree (Kruskal, Prim)**: Connect all nodes in a graph with minimum cost.
- **Segment Tree / Binary Indexed Tree (Fenwick Tree)**: Efficiently perform range queries and updates.
- **Trie (Prefix Tree)**: Efficiently store and search strings, often used for autocomplete features.
- **Matrix Traversal**: Navigate and process elements in 2D arrays.
- **Tree Traversal Variants**: Inorder, Preorder, Postorder, Level Order traversals.
- **Lowest Common Ancestor (LCA)**: Find the lowest common ancestor in trees.

### 5. Recursion & Backtracking Patterns
- **Recursion**: Solve problems by breaking them into smaller subproblems.
- **Backtracking**: Explore all possibilities for problems like permutations and combinations.
- **Backtracking with Memoization**: Optimize backtracking by storing results.
- **Divide and Conquer**: Break problems into smaller independent subproblems.
- **Subsets & Permutations**: Generate all possible combinations or arrangements.
- **State Space Search**: Explore all possible states (e.g., N-Queens, Sudoku).

### 6. Dynamic Programming & Greedy
- **Dynamic Programming (DP)**: Solve complex problems by breaking them into simpler subproblems and storing results.
- **Greedy Algorithms**: Make the most optimal choice at each step to ensure global optimization.
- **Tabulation & Memoization**: Bottom-up and top-down DP approaches.
- **Knapsack Patterns**: Solve problems involving selection under constraints.

### 7. Math & Miscellaneous Patterns
- **Bit Manipulation**: Solve problems involving binary representations and operations.
- **Counting & Combinatorics**: Solve problems involving counting, arrangements, or probability.
- **Number Theory (Primes, GCD, etc.)**: Solve problems involving mathematical properties.
- **Top K Elements (Heap/Priority Queue)**: Efficiently access the largest or smallest element or maintain top K elements.
- **Sweep Line Algorithm**: Solve interval and geometry problems efficiently.
- **Reservoir Sampling**: Randomly select k items from a stream of unknown size.
- **Randomized Algorithms**: Use randomness to solve problems efficiently (e.g., QuickSelect).
- **Game Theory Patterns**: Solve problems involving optimal moves (e.g., Minimax, NIM game).

This structure helps you focus on one group at a time, making your practice more effective and memorable.

### 🔄 Sorting Algorithms
- **Quick Sort**:
  - Easy: Implementation on small arrays
  - Medium: Optimizing pivot selection
  - Hard: Handling nearly sorted arrays and duplicates
- **Heap Sort**:
  - Easy: Basic implementation
  - Medium: Building a min/max heap
  - Hard: Optimizing for specific use cases
- **Counting Sort**:
  - Easy: Sorting small integer ranges
  - Medium: Implementing stable counting sort
  - Hard: Handling negative numbers and large ranges
- **Radix Sort**:
  - Easy: Sorting integers
  - Medium: Implementing for different bases
  - Hard: Sorting strings and floating-point numbers
- **Bucket Sort**:
  - Easy: Uniform distributions
  - Medium: Non-uniform distributions
  - Hard: Dynamic bucket allocation
- **Tim Sort**:
  - Easy: Understanding the algorithm
  - Medium: Basic implementation
  - Hard: Optimizing galloping mode and merge strategy

Each pattern folder contains:
- A clear explanation of the concept.
- Java code solutions categorized by difficulty (Easy, Medium, Hard).
- Sample test cases for validation.

## 🚀 How to Get Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/dineshkoilada/dsa.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd dsa
   ```
3. **Explore a pattern folder:**
   - Read the explanations in the `README.md` inside each pattern folder.
   - Start solving problems beginning with the easy ones.

4. **Run Java Solutions:**
   - Compile the Java file:
     ```bash
     javac patterns/two_pointers/easy/problem1.java
     ```
   - Run the program:
     ```bash
     java patterns.two_pointers.easy.problem1
     ```

## 📖 Learning Strategy

1. **Start with easy problems** for each pattern to build foundational understanding.
2. **Move to medium and hard problems** as you gain confidence.
3. **Review explanations** and write down the core ideas behind each solution.
4. **Practice regularly** to reinforce your understanding and improve problem-solving speed.

## 🤝 Contributing

If you'd like to contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new pattern solution'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## 🙏 Acknowledgments

This repository is inspired by the coding patterns commonly discussed in resources like:
- [LeetCode Patterns](https://seanprashad.com/leetcode-patterns/)
- [Educative's Grokking the Coding Interview](https://www.educative.io/courses/grokking-the-coding-interview)

Happy coding and good luck with your interviews! 💻🚀

