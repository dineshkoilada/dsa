# Math & Geometry Pattern 📐

## 📌 Introduction: The Power of Shapes and Numbers

Imagine designing a game where characters move around a grid, or calculating the area covered by different objects on a map. The **Math & Geometry Pattern** helps you solve problems that involve numbers, spatial relationships, or geometric shapes—turning abstract math into real-world solutions!

### 🎬 Real-World Analogies:

1. **Robot on a Grid** 🤖
   - A robot moves north, south, east, or west. How far does it travel? What area does it cover?
2. **Land Surveying** 🗺️
   - Calculating the area of a plot using its corner coordinates (Shoelace formula).
3. **Clock Arithmetic** ⏰
   - Hours wrap around after 12—just like modulo operations in programming.

The Math & Geometry pattern is your secret weapon for:
- 🔢 Number theory (primes, GCD, modulo)
- 📏 Distance, area, and angle calculations
- 🟦 Working with grids, circles, rectangles, and polygons
- 🧮 Handling large numbers and precision

---

## 🧠 How to Recognize a Math & Geometry Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Spatial" Clue**
   - "Grid", "distance", "area", "angle", or "coordinates" in the problem
2. **The "Number Theory" Hint**
   - Primes, GCD, modulo, or divisibility
3. **The "Precision" Signal**
   - Floating-point calculations, rounding, or large numbers

### 🤔 Essential Questions to Ask:
- Are you calculating distances, areas, or angles?
- Are you working with integers, floating points, or large numbers?
- Should floating-point precision be considered?
- Is there symmetry or a geometric property that can simplify the problem?

---

## 🎨 Visual Problem-Solving Framework

### Geometry Visuals:
```
Distance between points:
A(x1, y1)      B(x2, y2)
   *------------*
      |\
      | \
      |  \
      |___\

Area of a polygon (Shoelace):
Connect the dots in order, then apply the formula!

Modulo (Clock arithmetic):
12 + 5 hours = 5 o'clock (modulo 12)
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you calculating distances, areas, or angles?
- Are you working with integers, floating points, or large numbers?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Should floating-point precision be considered?
- Is there symmetry in the problem?

### ✅ **3. Identify Base Cases**
- Zero dimensions or coordinates.
- Undefined operations (e.g., division by zero).

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function calculateDistance(x1, y1, x2, y2):
    return sqrt((x2 - x1)^2 + (y2 - y1)^2)

function isPrime(n):
    if n <= 1:
        return false
    for i from 2 to sqrt(n):
        if n % i == 0:
            return false
    return true
```

### ✅ **5. Write the Code Skeleton**
```java
public class MathGeometry {
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Division by zero
- Negative inputs for square roots
- Precision errors with floating-point numbers

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                  | Time Complexity | Space Complexity |
|----------------------------|-----------------|------------------|
| Distance Calculation       | O(1)            | O(1)             |
| Prime Number Check         | O(√n)           | O(1)             |
| GCD (Euclidean Algorithm)  | O(log min(a, b))| O(1)             |
| Area of Polygon            | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Depends on mathematical operations involved.
- **Space Complexity:** Usually constant unless working with additional data structures.

---

## 📚 Example 1: Easy Problem - Check if a Number Is Prime

**Problem:**
Determine if a number is prime.

**Input:** `n = 17`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. A prime number is divisible only by 1 and itself.
2. Check divisibility from 2 up to the square root of `n`.

### ✅ **Code:**
```java
public class PrimeCheck {
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(√n) — Only checking divisibility up to the square root of `n`.
- **Space:** O(1) — Only constant space used.

---

## 📚 Example 2: Medium Problem - Calculate Euclidean Distance

**Problem:**
Given two points `(x1, y1)` and `(x2, y2)`, calculate the distance between them.

**Input:** `(x1, y1) = (1, 2)`, `(x2, y2) = (4, 6)`

**Expected Output:** `5.0`

### 🔑 **Solution Steps**
1. Use the distance formula: \( \sqrt{(x2 - x1)^2 + (y2 - y1)^2} \)

### ✅ **Code:**
```java
public class EuclideanDistance {
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(1) — Basic arithmetic operations.
- **Space:** O(1) — No extra memory used.

---

## 📚 Example 3: Hard Problem - Area of a Polygon

**Problem:**
Find the area of a polygon given its vertices in order.

**Input:**
```
points = [
    (0, 0),
    (4, 0),
    (4, 3),
    (0, 3)
]
```

**Expected Output:** `12.0`

### 🔑 **Solution Steps**
1. Use the **Shoelace formula**:
   - \( \text{Area} = 0.5 * | \sum_{i=0}^{n-1} (x_i * y_{i+1} - x_{i+1} * y_i) | \)
2. Close the loop by setting `x[n] = x[0]` and `y[n] = y[0]`.

### ✅ **Code:**
```java
public class PolygonArea {
    public static double area(int[][] points) {
        int n = points.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[(i + 1) % n][0], y2 = points[(i + 1) % n][1];
            sum += (x1 * y2) - (x2 * y1);
        }
        return Math.abs(sum) / 2.0;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each vertex is processed once.
- **Space:** O(1) — Only constant space used.

---

## 📚 Key Takeaways

1. Use the Math & Geometry Pattern for problems involving spatial relationships, calculations, and numerical optimizations.
2. Efficiently handle number theory problems with prime checks, GCD, and modulo operations.
3. Geometry-based problems often require formulas for distance, area, and angles.
4. Precision and edge cases, like division by zero or floating-point errors, should always be considered.

---

Next, let's dive into the **Segment Tree Pattern** for solving problems that involve range queries and updates efficiently!

