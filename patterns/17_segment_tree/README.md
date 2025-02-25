# Segment Tree Pattern

## 🎯 Introduction

Imagine you have a large array of numbers and frequently need to perform operations like finding the sum, minimum, or maximum of elements within a specific range. Recalculating these values from scratch for every query can be inefficient. The **Segment Tree** pattern offers a powerful way to perform such queries and updates efficiently.

A **Segment Tree** is a binary tree structure used for:
- Range queries (sum, minimum, maximum, GCD)
- Point updates (changing a single element)
- Range updates (updating a range of values with lazy propagation)

The Segment Tree is particularly useful for:
- Handling large arrays with frequent updates and queries
- Range sum queries (RSQ) and range minimum queries (RMQ)
- Interval-related problems
- Efficiently managing dynamic data

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you required to perform range-based queries?
   - Do you need to update values frequently while maintaining query efficiency?

2. **Ask Clarifying Questions:**
   - What type of query is required (sum, minimum, maximum)?
   - Are point or range updates allowed?
   - How large is the input array?

3. **Identify Clues for Using a Segment Tree:**
   - The problem requires answering multiple queries over subarrays.
   - The input size is large, and queries/updates need to be efficient.

4. **Predicting if a Segment Tree Is Applicable:**
   - Does the problem involve range queries and frequent updates?
   - Are time complexity constraints too tight for a brute-force solution?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What type of queries are required?
- Are updates frequent?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Are range updates allowed?
- Should lazy propagation be used?

### ✅ **3. Identify Base Cases**
- When the range contains a single element.
- When the query range fully overlaps with the current node's range.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function buildTree(arr, tree, start, end):
    if start == end:
        tree[node] = arr[start]
    else:
        mid = (start + end) / 2
        buildTree(left half)
        buildTree(right half)
        tree[node] = merge(tree[left], tree[right])

function query(tree, start, end, L, R):
    if query range completely outside:
        return identity value (0 for sum, infinity for min)
    if query range completely inside:
        return tree[node]
    return merge(query(left half), query(right half))

function update(tree, start, end, index, value):
    if start == end:
        tree[node] = value
    else:
        mid = (start + end) / 2
        update(left or right half based on index)
        tree[node] = merge(tree[left], tree[right])
```

### ✅ **5. Write the Code Skeleton**
```java
public class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                update(2 * node + 1, start, mid, index, value);
            } else {
                update(2 * node + 2, mid + 1, end, index, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Querying an empty range.
- Updating an index out of bounds.
- Negative numbers in the array.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation    | Time Complexity | Space Complexity |
|--------------|-----------------|------------------|
| Build Tree   | O(n)            | O(n)             |
| Query        | O(log n)        | O(1)             |
| Update       | O(log n)        | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** The tree has height \( O(\log n) \) since it’s a binary tree.
- **Space Complexity:** The tree requires about \( 4n \) space for storage.

---

## 📚 Example 1: Easy Problem - Range Sum Query

**Problem:**
Given an array of integers, answer multiple sum queries over a range.

**Input:** `arr = [1, 3, 5, 7, 9, 11]`, queries = `[0, 2]`, `[1, 3]`

**Expected Output:** `9`, `15`

### 🔑 **Solution Steps**
1. Build a segment tree using the array.
2. For each query, return the sum of elements in the specified range.

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) — Querying a segment tree.
- **Space:** O(n) — Tree storage.

---

## 📚 Example 2: Medium Problem - Range Minimum Query

**Problem:**
Find the minimum element in a range from an array.

**Input:** `arr = [2, 6, 1, 12, 5, 8]`, queries = `[1, 4]`, `[0, 5]`

**Expected Output:** `1`, `1`

### 🔑 **Solution Steps**
1. Build a segment tree where each node stores the minimum value of the range.
2. Query for the minimum value in the range.

### ✅ **Code:**
```java
public class SegmentTreeMin {
    private int[] tree;
    private int n;

    public SegmentTreeMin(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MAX_VALUE;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return Math.min(query(2 * node + 1, start, mid, l, r),
                        query(2 * node + 2, mid + 1, end, l, r));
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) — Each query checks relevant nodes.
- **Space:** O(n) — Space for the segment tree.

---

## 📚 Example 3: Hard Problem - Range Update with Lazy Propagation

**Problem:**
Update all elements in a range by adding a value and handle queries for the sum in a given range efficiently.

**Input:** `arr = [1, 3, 5, 7, 9, 11]`, update range `[1, 3]` by `3`, query `[0, 5]`

**Expected Output:** Updated array = `[1, 6, 8, 10, 9, 11]`, sum query result = `45`

### 🔑 **Solution Steps**
1. Build a segment tree with lazy propagation.
2. Update ranges using lazy propagation.
3. Handle queries efficiently.

### ✅ **Code:**
```java
public class SegmentTreeLazy {
    private int[] tree, lazy;
    private int n;

    public SegmentTreeLazy(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public void updateRange(int l, int r, int value) {
        updateRange(0, 0, n - 1, l, r, value);
    }

    private void updateRange(int node, int start, int end, int l, int r, int value) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (r < start || end < l) return;

        if (l <= start && end <= r) {
            tree[node] += (end - start + 1) * value;
            if (start != end) {
                lazy[2 * node + 1] += value;
                lazy[2 * node + 2] += value;
            }
            return;
        }

        int mid = (start + end) / 2;
        updateRange(2 * node + 1, start, mid, l, r, value);
        updateRange(2 * node + 2, mid + 1, end, l, r, value);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) for both update and query operations.
- **Space:** O(n) — Space for the segment tree and lazy array.

---

## 📚 Key Takeaways

1. Use Segment Trees for problems involving range queries and updates.
2. Segment Trees handle dynamic range queries and updates efficiently with \( O(\log n) \) complexity.
3. Lazy propagation allows for efficient range updates.
4. Segment Trees can be customized for various operations (sum, min, max, GCD).

---

Next, let's dive into the **Kadane Algorithm Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

