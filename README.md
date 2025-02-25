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

This repository is organized by problem-solving patterns such as:

- **Two Pointers**: Efficiently narrow down solutions using two pointers.
- **Sliding Window**: Handle problems involving subarrays or substrings.
- **Fast & Slow Pointers**: Detect cycles and middle elements in linked lists.
- **Binary Search**: Find elements in sorted arrays efficiently.
- **Depth-First Search (DFS)**: Explore tree or graph structures deeply.
- **Breadth-First Search (BFS)**: Explore all neighbors before diving deeper.
- **Dynamic Programming (DP)**: Solve complex problems by breaking them into simpler subproblems.
- **Backtracking**: Explore all possibilities for problems like permutations and combinations.
- **Greedy Algorithms**: Make the most optimal choice at each step to ensure global optimization.
- **Union Find (Disjoint Set Union)**: Efficiently handle dynamic connectivity problems.
- **Topological Sort**: Handle problems on directed acyclic graphs (DAGs) to determine order of execution.
- **Heap / Priority Queue**: Efficiently access the largest or smallest element.
- **Bit Manipulation**: Solve problems involving binary representations and operations.
- **Trie (Prefix Tree)**: Efficiently store and search strings, often used for autocomplete features.
- **Graph Algorithms**: Cover advanced algorithms like Dijkstra’s and Floyd-Warshall for shortest paths.

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
   cd leetcode-patterns-practice
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

