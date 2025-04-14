# Java Fundamentals for Technical Interviews

## 🎯 Introduction

This guide provides the essential Java concepts you'll need for FAANG technical interviews. Mastering these fundamentals is critical for implementing data structures and algorithms efficiently during coding assessments.

---

## 🔍 Basic Syntax and Control Flow

### 📌 Variables and Data Types
```java
// Primitive types
int number = 42;
double decimal = 3.14;
char letter = 'A';
boolean isActive = true;

// Reference types
String text = "Hello, FAANG!";
Integer wrappedInt = Integer.valueOf(100);
```

### 📌 Conditional Statements
```java
// If-else statement
if (number > 50) {
    System.out.println("Number is greater than 50");
} else if (number > 20) {
    System.out.println("Number is between 21 and 50");
} else {
    System.out.println("Number is 20 or less");
}

// Switch statement
switch (letter) {
    case 'A':
        System.out.println("Excellent");
        break;
    case 'B':
        System.out.println("Good");
        break;
    default:
        System.out.println("Other grade");
}
```

### 📌 Loops - Essential for Iterative Algorithms
```java
// For loop (most common in array iterations)
int[] array = {1, 2, 3, 4, 5};
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}

// Enhanced for loop (cleaner for collections)
for (int element : array) {
    System.out.println(element);
}

// While loop (useful for unknown iterations)
int count = 0;
while (count < 5) {
    System.out.println(count);
    count++;
}

// Do-while loop (executes at least once)
int x = 0;
do {
    System.out.println(x);
    x++;
} while (x < 3);
```

---

## 🔧 Arrays and Strings (Common Interview Topics)

### 📌 Arrays
```java
// Declaration and initialization
int[] numbers = new int[5]; // Creates array of size 5 with default values (0)
int[] primes = {2, 3, 5, 7, 11}; // Initialize with values

// Multi-dimensional arrays
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Common operations
System.out.println("Length: " + primes.length);
System.out.println("Element at index 2: " + primes[2]); // Output: 5

// Iterating through 2D array (common in matrix problems)
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

### 📌 Strings
```java
// String creation
String s1 = "Hello";
String s2 = new String("World");
String s3 = s1 + " " + s2; // Concatenation

// Common operations (frequent in interviews)
System.out.println("Length: " + s1.length());
System.out.println("Character at index 1: " + s1.charAt(1)); // Output: e
System.out.println("Substring: " + s1.substring(1, 4)); // Output: ell

// String comparison
boolean areEqual = s1.equals("Hello"); // true (compares content)
boolean isSameRef = (s1 == "Hello");   // may be true (compares reference)

// String immutability (important concept)
s1 = s1 + "!"; // Creates a new string object
```

---

## 🧩 Functions and Methods

### 📌 Method Declaration
```java
public class MathUtil {
    // Basic method
    public int add(int a, int b) {
        return a + b;
    }

    // Method with array parameter (common in interviews)
    public int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Method with variable arguments
    public double average(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
}
```

### 📌 Recursion (Critical for Many Algorithms)
```java
public class Recursion {
    // Factorial calculation
    // This method calculates the factorial of a given number `n` using recursion.
    // The base case is when `n` is 0 or 1, where the factorial is 1.
    // For other values, it recursively multiplies `n` by the factorial of `n - 1`.
    public int factorial(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case
        return n * factorial(n - 1);
    }

    // Fibonacci - often asked in interviews
    // This method computes the nth Fibonacci number using recursion.
    // The base cases are when `n` is 0 or 1, returning `n` directly.
    // For other values, it recursively sums the Fibonacci numbers of `n - 1` and `n - 2`.
    public int fibonacci(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Binary search using recursion
    // This method performs a binary search on a sorted array to find the target element.
    // It takes the array, target value, and the current search bounds (`left` and `right`) as inputs.
    // The base case is when `left` exceeds `right`, indicating the target is not found.
    // It calculates the middle index and compares the target with the middle element.
    // Depending on the comparison, it recursively searches in the left or right half of the array.
    public int binarySearch(int[] array, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        // Check if the middle element is the target
        if (array[mid] == target) {
            return mid;
        }

        // Recursive case: search in the left or right half
        if (array[mid] > target) {
            // Search in left half
            return binarySearch(array, target, left, mid - 1);
        } else {
            // Search in right half
            return binarySearch(array, target, mid + 1, right);
        }
    }
    ```
}
```

---

## 📚 Key Takeaways for Interviews

1. **Master loop variations** - Know when to use each type of loop
2. **Array manipulations** - Be comfortable with indexing, traversal, and nested arrays
3. **String operations** - Understand string immutability and common methods
4. **Method design** - Write clean methods with proper parameter validation
5. **Recursion patterns** - Recognize when to use recursion and how to define base cases

---

Up next: Dive into **Object-Oriented Programming Concepts** which provide the foundation for implementing complex data structures.

