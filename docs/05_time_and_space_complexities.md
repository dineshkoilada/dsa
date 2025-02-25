# Time and Space Complexities

## 🎯 Introduction

When solving coding problems, it’s not enough to find a working solution—you need to find an **efficient** solution. Understanding **time and space complexities** allows developers to write scalable, optimized code that can handle large inputs effectively.

In this guide, we'll focus on the **20% of concepts** that help cover **80% of efficiency analysis** in programming.

---

## 🔍 What Is Time Complexity?

**Time Complexity** measures the amount of time an algorithm takes to complete relative to the size of the input.

### ✅ Why Is It Important?
- Predicts performance for large inputs.
- Helps compare the efficiency of different algorithms.
- Avoids unnecessary computations.

### 📌 Big O Notation (O-notation)
Big O notation describes the upper bound of the time an algorithm can take in the worst-case scenario.

### 🔢 Common Time Complexities:
| Complexity | Name               | Example Algorithm              | Description                       |
|-----------|--------------------|--------------------------------|-----------------------------------|
| O(1)      | Constant           | Accessing array element        | Time does not depend on input size|
| O(log n)  | Logarithmic        | Binary Search                  | Divides problem size each step    |
| O(n)      | Linear             | Linear Search                  | Grows proportionally with input   |
| O(n log n)| Linearithmic       | Merge Sort, Quick Sort         | Combination of linear and log     |
| O(n²)     | Quadratic          | Bubble Sort, Insertion Sort    | Nested loops over input           |
| O(2^n)    | Exponential        | Recursive Fibonacci            | Doubles steps with each addition  |
| O(n!)     | Factorial          | Traveling Salesman Problem     | Extremely slow; all permutations  |

### 🎯 Real-World Analogy
- **O(1)**: Grabbing a book from a shelf directly.
- **O(log n)**: Finding a word in a dictionary (divide and conquer).
- **O(n)**: Reading every book on a shelf.
- **O(n²)**: Comparing each book with every other book.

### 📌 Example: Time Complexity in Practice
```java
public class TimeComplexityExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        // O(n) - Linear Time
        for (int num : array) {
            System.out.println(num);
        }
    }
}
```

---

## 📊 What Is Space Complexity?

**Space Complexity** measures the amount of memory an algorithm uses relative to the input size.

### ✅ Why Is Space Complexity Important?
- Crucial for memory-constrained environments.
- Helps write optimized code that conserves memory.

### 📌 Common Space Complexities:
| Complexity | Name               | Example Algorithm                  |
|-----------|--------------------|-----------------------------------|
| O(1)      | Constant           | In-place sorting algorithms       |
| O(log n)  | Logarithmic        | Recursive Binary Search           |
| O(n)      | Linear             | Copying elements to a new array   |
| O(n²)     | Quadratic          | Storing a 2D matrix               |

### 🔗 Example: Space Complexity in Practice
```java
public class SpaceComplexityExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int sum = 0; // O(1) space
        for (int num : array) {
            sum += num; // Still O(1) as no extra space is used
        }
        System.out.println("Sum: " + sum);
    }
}
```

---

## 🏎️ Analyzing Time and Space Complexity Step by Step

### ✅ Rules of Thumb:
1. **Drop constants:** O(2n) simplifies to O(n).
2. **Drop lower-order terms:** O(n² + n) simplifies to O(n²).
3. **Loops:** Time complexity grows with each loop.
4. **Nested loops:** Multiply complexities. E.g., two nested O(n) loops result in O(n²).
5. **Recursive calls:** Follow recurrence relations (e.g., Merge Sort: T(n) = 2T(n/2) + O(n)).

### 📌 Example: Nested Loop Complexity
```java
public class NestedLoopExample {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 0; i < n; i++) { // O(n)
            for (int j = 0; j < n; j++) { // O(n)
                System.out.println("i: " + i + ", j: " + j);
            }
        }
    }
} // Overall Time Complexity: O(n²)
```

---

## 🎯 Trade-Off Between Time and Space

Sometimes, optimizing for time can increase space usage and vice versa. This is known as the **time-space trade-off**.

### ✅ Examples:
- **Memoization (Caching)**: Uses extra space to save time in future calculations.
- **In-Place Sorting**: Reduces space at the expense of additional computation.

### 📌 Example: Memoization in Fibonacci Sequence
```java
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static Map<Integer, Integer> cache = new HashMap<>();

    public static int fib(int n) {
        if (n <= 1) return n;
        if (cache.containsKey(n)) return cache.get(n);
        int result = fib(n - 1) + fib(n - 2);
        cache.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Output: 55
    }
}
```

---

## 📚 Key Takeaways

1. **Time Complexity** helps measure how fast an algorithm runs.
2. **Space Complexity** measures the memory used by an algorithm.
3. Simplify complexities using Big O rules.
4. Understand and balance the **time-space trade-off** for optimal performance.

---

Up next: Dive into **Data Types** in Java, understanding primitive types, extended data types, and custom implementations.

