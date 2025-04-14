# Time and Space Complexities for Technical Interviews

## 🎯 Introduction

Understanding time and space complexity is crucial for FAANG interviews, where efficiency and optimization are highly valued. Interviewers expect candidates to analyze the performance of their solutions and suggest potential improvements.

This guide focuses on time and space complexity analysis that will help you succeed in technical interviews.

---

## 🔍 Big O Notation for Algorithm Analysis

Big O notation describes the upper bound of an algorithm's runtime or space usage as the input size grows.

### 📌 Time Complexity Hierarchy (From Fastest to Slowest)
```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
```

### 📌 Common Time Complexities in Interviews
| Complexity  | Name        | Examples in Interview Questions |
|-------------|-------------|---------------------------------|
| O(1)        | Constant    | Hash table lookups, array access by index |
| O(log n)    | Logarithmic | Binary search, balanced BST operations |
| O(n)        | Linear      | Array traversal, linear search |
| O(n log n)  | Linearithmic| Efficient sorting (merge sort, quicksort), heap operations |
| O(n²)       | Quadratic   | Nested loops, bubble/selection sort |
| O(2ⁿ)       | Exponential | Recursive solutions without memoization |
| O(n!)       | Factorial   | Generating all permutations |

### 📌 Visual Growth Rate Comparison
Imagine an algorithm processing 100 elements:
- O(1): 1 operation (constant)
- O(log n): ~7 operations (very efficient)
- O(n): 100 operations (scales linearly)
- O(n log n): ~700 operations (efficient for sorting)
- O(n²): 10,000 operations (becomes inefficient with large inputs)
- O(2ⁿ): 2¹⁰⁰ operations (computationally infeasible)

---

## 📊 Analyzing Algorithms for Interviews

### 📌 Time Complexity Analysis Rules

1. **Drop constants**: O(2n) → O(n), O(3) → O(1)
   ```java
   // This is O(n), not O(3n)
   for (int i = 0; i < array.length; i++) {          // O(n)
       System.out.print(array[i]);                   // O(1)
   }
   for (int i = 0; i < array.length; i++) {          // O(n)
       System.out.print(array[i] * 2);               // O(1)
   }
   for (int i = 0; i < array.length; i++) {          // O(n)
       System.out.print(array[i] + 5);               // O(1)
   }
   ```

2. **Drop lower-order terms**: O(n² + n) → O(n²)
   ```java
   // This is O(n²), not O(n² + n)
   for (int i = 0; i < array.length; i++) {          // O(n)
       for (int j = 0; j < array.length; j++) {      // O(n)
           System.out.print(array[i] + array[j]);    // O(1)
       }
   }
   for (int i = 0; i < array.length; i++) {          // O(n)
       System.out.print(array[i]);                   // O(1)
   }
   ```

3. **Analyze loops carefully**:
   ```java
   // O(n) - Linear time
   for (int i = 0; i < n; i++) {
       // O(1) operations
   }
   
   // O(n²) - Quadratic time
   for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
           // O(1) operations
       }
   }
   
   // O(log n) - Logarithmic time
   for (int i = 1; i < n; i *= 2) {
       // O(1) operations
   }
   ```

4. **Analyze recursive calls**: Count the number of recursive calls and their cost
   ```java
   // Binary search: O(log n)
   int binarySearch(int[] array, int target, int left, int right) {
       if (left > right) return -1;
       
       int mid = left + (right - left) / 2;
       if (array[mid] == target) return mid;
       
       if (array[mid] > target)
           return binarySearch(array, target, left, mid - 1);
       else
           return binarySearch(array, target, mid + 1, right);
   }
   ```

---

## 🚀 Space Complexity Analysis for Interviews

Space complexity measures the amount of memory an algorithm needs relative to input size.

### 📌 Common Space Complexities
| Complexity | Description | Example |
|------------|-------------|---------|
| O(1) | Constant space | Fixed number of variables regardless of input size |
| O(n) | Linear space | Arrays or lists proportional to input |
| O(n²) | Quadratic space | 2D matrices, adjacency matrices for graphs |

### 📌 Space Analysis in Common Algorithms
```java
// O(1) space - In-place array reversal
public void reverseArray(int[] array) {
    int left = 0, right = array.length - 1;
    while (left < right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
        left++;
        right--;
    }
    // Only uses a constant amount of extra space
}

// O(n) space - Creating a copy
public int[] arrayCopy(int[] array) {
    int[] result = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        result[i] = array[i];
    }
    return result;
    // Creates a new array of size n
}

// O(n) space - Recursive call stack
public int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
    // Uses O(n) space in the call stack
}
```

---

## ⚖️ Time-Space Tradeoffs in Interview Questions

Many interview questions have multiple solutions with different time-space tradeoffs.

### 📌 Example: Two-Sum Problem
```java
// Solution 1: O(n²) time, O(1) space
public int[] twoSum_BruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                return new int[] {i, j};
            }
        }
    }
    return new int[] {};
}

// Solution 2: O(n) time, O(n) space
public int[] twoSum_Optimized(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] {map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[] {};
}
```

### 📌 Example: Fibonacci Sequence
```java
// Solution 1: O(2ⁿ) time, O(n) space (recursive call stack)
public int fibonacci_Recursive(int n) {
    if (n <= 1) return n;
    return fibonacci_Recursive(n-1) + fibonacci_Recursive(n-2);
}

// Solution 2: O(n) time, O(n) space
public int fibonacci_Memoization(int n) {
    Map<Integer, Integer> memo = new HashMap<>();
    return fibMemo(n, memo);
}

private int fibMemo(int n, Map<Integer, Integer> memo) {
    if (n <= 1) return n;
    if (memo.containsKey(n)) return memo.get(n);
    
    int result = fibMemo(n-1, memo) + fibMemo(n-2, memo);
    memo.put(n, result);
    return result;
}

// Solution 3: O(n) time, O(1) space
public int fibonacci_Iterative(int n) {
    if (n <= 1) return n;
    
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        int sum = a + b;
        a = b;
        b = sum;
    }
    return b;
}
```

---

## 🧠 Recognizing Common Time Complexities in Interviews

### ✅ Identifying O(log n) Algorithms
- Problem size is reduced by a factor (usually ½) in each step
- Common examples: Binary search, binary search tree operations
```java
// Binary search: O(log n)
while (left <= right) {
    mid = left + (right - left) / 2;
    if (array[mid] == target) return mid;
    if (array[mid] < target) left = mid + 1;
    else right = mid - 1;
}
```

### ✅ Identifying O(n log n) Algorithms
- Divide-and-conquer algorithms often have this complexity
- Common examples: Efficient sorting algorithms
```java
// Merge sort: O(n log n)
void mergeSort(int[] array, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);         // T(n/2)
        mergeSort(array, mid + 1, right);    // T(n/2)
        merge(array, left, mid, right);      // O(n)
    }
}
```

### ✅ Identifying O(2ⁿ) or Exponential Algorithms
- Solutions that consider all subsets of the input
- Common examples: Recursive Fibonacci, generating power sets
```java
// Recursive calculation without memoization: O(2ⁿ)
int recursiveFib(int n) {
    if (n <= 1) return n;
    return recursiveFib(n-1) + recursiveFib(n-2);
}
```

---

## 📝 Interview Strategy for Complexity Analysis

### 📌 Step-by-Step Approach
1. **Understand the problem** and its constraints
2. **Implement a solution** with clear algorithm steps
3. **Analyze time and space complexity**
4. **Optimize if necessary**, considering time-space tradeoffs
5. **Communicate complexity analysis** to the interviewer

### 📌 Red Flags for Interviewers
- Unable to analyze algorithm complexity
- Suggesting inefficient solutions without recognizing better alternatives
- Not considering edge cases or input constraints

### 📌 Interview Best Practices
- Always discuss the time and space complexity of your solution
- If your initial solution is inefficient, acknowledge it and explain optimization ideas
- When optimizing from O(n²) to O(n), explain what insight allows for the improvement
- Be prepared to justify your analysis with reasoning

---

## 📚 Key Takeaways for Interviews

1. **Master Big O analysis** - Know how to calculate and simplify complexity expressions
2. **Understand common algorithm complexities** - Especially sorting, searching, and data structure operations
3. **Recognize time-space tradeoffs** - Be ready to discuss multiple approaches
4. **Practice analyzing recursive algorithms** - Understand the technique of recurrence relations
5. **Communicate efficiency clearly** - Interviewers value candidates who can articulate complexity analysis

---

## 🔄 Transitioning to Data Types

Understanding time and space complexity is just one part of the puzzle. To implement efficient algorithms, you also need to select the right data types that support your performance requirements.

Up next: Explore **Data Types in Java** to understand their performance characteristics and appropriate use cases.

