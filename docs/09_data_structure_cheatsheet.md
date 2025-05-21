# Data Structure & Algorithm Cheat Sheet for Interviews

## Overview

This document provides a concise, enterprise-grade reference for essential data structure operations and advanced algorithms, tailored for Java technical interviews and real-world enterprise development. Each section includes a brief description, best practices, and idiomatic Java code snippets.

---

## 1. Heap / Priority Queue

**Purpose:** Efficiently retrieve the minimum or maximum element. Used in Top K, scheduling, and graph algorithms.

**Best Practices:**
- Use `PriorityQueue` for min-heap (default) and custom comparators for max-heap or custom order.
- Prefer `Comparator.comparing` for objects in enterprise code.

```java
// Min Heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
// Max Heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
// Heapify an array
int[] arr = {4, 1, 7};
PriorityQueue<Integer> heap = new PriorityQueue<>();
for (int num : arr) heap.add(num);
// Custom comparator for objects
PriorityQueue<Employee> pq = new PriorityQueue<>(Comparator.comparing(Employee::getSalary));
```

---

## 2. Segment Tree (Range Query & Update)

**Purpose:** Support efficient range queries and updates (sum, min, max) in O(log n) time.

**Best Practices:**
- Use for large, mutable arrays with frequent range queries.
- Encapsulate logic in a dedicated class.

```java
public class SegmentTree {
    private final int[] tree;
    private final int n;
    public SegmentTree(int[] input) {
        n = input.length;
        tree = new int[4 * n];
        build(input, 0, 0, n - 1);
    }
    private void build(int[] arr, int node, int l, int r) {
        if (l == r) { tree[node] = arr[l]; return; }
        int m = (l + r) / 2;
        build(arr, 2*node+1, l, m); build(arr, 2*node+2, m+1, r);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }
    public int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int m = (l + r) / 2;
        return query(2*node+1, l, m, ql, qr) + query(2*node+2, m+1, r, ql, qr);
    }
    public void update(int node, int l, int r, int idx, int val) {
        if (l == r) { tree[node] = val; return; }
        int m = (l + r) / 2;
        if (idx <= m) update(2*node+1, l, m, idx, val);
        else update(2*node+2, m+1, r, idx, val);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }
}
```

---

## 3. Binary Indexed Tree (Fenwick Tree)

**Purpose:** Efficient prefix sum queries and updates in O(log n) time.

**Best Practices:**
- Use for dynamic cumulative frequency or sum queries.

```java
public class FenwickTree {
    private final int[] bit;
    public FenwickTree(int n) { bit = new int[n+1]; }
    public void update(int i, int val) { for (; i < bit.length; i += i & -i) bit[i] += val; }
    public int query(int i) { int sum = 0; for (; i > 0; i -= i & -i) sum += bit[i]; return sum; }
}
```

---

## 4. Bit Manipulation

**Purpose:** Optimize space and time for state compression, math, and set operations.

**Best Practices:**
- Use Java's built-in methods (`Integer.bitCount`, etc.) for clarity.

| Operation         | Code Example                |
|-------------------|----------------------------|
| Set bit k         | x |= (1 << k);             |
| Clear bit k       | x &= ~(1 << k);            |
| Toggle bit k      | x ^= (1 << k);             |
| Check bit k       | (x & (1 << k)) != 0        |
| Count set bits    | Integer.bitCount(x)        |
| Lowest set bit    | x & -x                     |
| Remove lowest bit | x & (x - 1)                |

---

## 5. Matrix Traversal

**Purpose:** Solve grid, pathfinding, and DP problems.

**Best Practices:**
- Use enhanced for-loops for readability.
- Encapsulate traversal logic in utility methods for reuse.

```java
// Row-wise
for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) ...
// Column-wise
for (int j = 0; j < n; j++) for (int i = 0; i < m; i++) ...
// Spiral: Use boundaries (top, bottom, left, right)
// Zigzag/Diagonal
for (int d = 0; d < m + n - 1; d++) ...
```

---

## 6. Set Operations

**Purpose:** Uniqueness, fast lookups, and set algebra.

**Best Practices:**
- Use `HashSet` for performance, `TreeSet` for order.

```java
Set<Integer> a = new HashSet<>(Arrays.asList(1,2,3));
Set<Integer> b = new HashSet<>(Arrays.asList(3,4,5));
a.addAll(b);      // Union
a.retainAll(b);   // Intersection
a.removeAll(b);   // Difference
```

---

## 7. Reservoir Sampling

**Purpose:** Randomly select k items from a stream of unknown size.

**Best Practices:**
- Use `Random` for reproducibility in enterprise code.

```java
int result = -1, count = 0;
Random rand = new Random();
while (hasNext()) {
    int x = next();
    count++;
    if (rand.nextInt(count) == 0) result = x;
}
```

---

## 8. Randomized Algorithms (QuickSelect)

**Purpose:** Find the k-th smallest/largest element in expected O(n) time.

**Best Practices:**
- Use for selection problems where full sort is unnecessary.

```java
int quickSelect(int[] arr, int k) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int p = partition(arr, l, r);
        if (p == k) return arr[p];
        else if (p < k) l = p + 1;
        else r = p - 1;
    }
    return arr[l];
}
```

---

## 9. Game Theory (Minimax)

**Purpose:** Decision making in two-player games.

**Best Practices:**
- Use memoization to optimize recursive minimax.

```java
int minimax(int depth, boolean isMaximizing) {
    if (gameOver()) return evaluate();
    if (isMaximizing) {
        int best = Integer.MIN_VALUE;
        for (Move m : moves()) best = Math.max(best, minimax(depth+1, false));
        return best;
    } else {
        int best = Integer.MAX_VALUE;
        for (Move m : moves()) best = Math.min(best, minimax(depth+1, true));
        return best;
    }
}
```

---

## Key Takeaways

- Use idiomatic Java and encapsulate logic in classes or utility methods.
- Prefer interfaces (`List`, `Set`, `Map`) for flexibility.
- Leverage Java's standard library for reliability and maintainability.
- Use this cheat sheet for quick recall and as a supplement to the detailed DSA and pattern documentation in this repository.
