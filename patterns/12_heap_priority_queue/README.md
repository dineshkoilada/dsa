# Heap / Priority Queue Pattern

## 🎯 Introduction

Imagine you’re managing a to-do list where the most urgent task always gets done first. The **Heap** or **Priority Queue** pattern is designed for such scenarios where retrieving the smallest or largest element efficiently is crucial.

A **Heap** is a specialized binary tree-based data structure that satisfies the heap property:
- **Max-Heap:** The parent node is greater than or equal to its children.
- **Min-Heap:** The parent node is less than or equal to its children.

The Heap/Priority Queue Pattern is particularly useful for:
- Finding the `k` largest or smallest elements
- Scheduling tasks (like CPU scheduling)
- Implementing Dijkstra’s algorithm
- Real-time data stream processing

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Do you need to frequently retrieve the minimum or maximum element?
   - Are you maintaining a dynamic set of elements where quick insertion and deletion are required?

2. **Ask Clarifying Questions:**
   - Should elements be retrieved in sorted order?
   - How large is the dataset? Is memory a concern?

3. **Identify Clues for Using a Heap:**
   - You need to track the top `k` elements in a collection.
   - The problem involves dynamic insertion and deletion with prioritization.
   - You’re implementing a priority-based system.

4. **Predicting if a Heap Is Applicable:**
   - Does the problem involve keeping track of the largest/smallest element dynamically?
   - Are insertion and deletion operations frequent?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you retrieving elements based on priority?
- Is it a max-heap or a min-heap?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if the heap is empty?
- Are there duplicate priorities?

### ✅ **3. Identify Base Cases**
- If the heap is empty, return an appropriate default.
- If no elements meet the priority, return an empty result.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
initialize priority queue (min-heap or max-heap)
for each element in input:
    add element to heap
    if heap size exceeds k:
        remove element with lowest priority
return heap elements
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class HeapPriorityQueue {
    public static List<Integer> findKSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Input array is empty.
- `k` is greater than the length of the input array.
- Duplicate elements.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                 | Time Complexity | Space Complexity |
|---------------------------|-----------------|------------------|
| Insertion into heap       | O(log k)        | O(k)             |
| Deletion from heap        | O(log k)        | O(k)             |
| Building heap from array  | O(n log k)      | O(k)             |

**How to derive these complexities:**
- **Time Complexity:** Maintaining a heap of size `k` means insertion and deletion take O(log k).
- **Space Complexity:** The heap will store at most `k` elements.

---

## 📚 Example 1: Easy Problem - Find K Smallest Elements

**Problem:**
Find the `k` smallest elements from an unsorted array.

**Input:** `nums = [3, 2, 1, 5, 6, 4]`, `k = 2`

**Expected Output:** `[1, 2]`

### 🔑 **Solution Steps**
1. Use a max-heap to keep track of the smallest elements.
2. Add elements to the heap.
3. If the heap size exceeds `k`, remove the largest element.

### ✅ **Code:**
```java
public class KSmallestElements {
    public static List<Integer> findKSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log k) — Each insertion and deletion takes O(log k).
- **Space:** O(k) — Stores up to `k` elements.

---

## 📚 Example 2: Medium Problem - Kth Largest Element in an Array

**Problem:**
Find the `k`th largest element in an unsorted array.

**Input:** `nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]`, `k = 4`

**Expected Output:** `4`

### 🔑 **Solution Steps**
1. Use a min-heap of size `k` to store the largest elements seen so far.
2. Add elements to the heap.
3. If the heap size exceeds `k`, remove the smallest element.

### ✅ **Code:**
```java
public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log k) — Each insertion and deletion takes O(log k).
- **Space:** O(k) — Stores up to `k` elements.

---

## 📚 Example 3: Hard Problem - Merge K Sorted Lists

**Problem:**
Merge `k` sorted linked lists into a single sorted list.

**Input:**
```
lists = [
  1 -> 4 -> 5,
  1 -> 3 -> 4,
  2 -> 6
]
```

**Expected Output:** `1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6`

### 🔑 **Solution Steps**
1. Use a min-heap to track the current smallest element from each list.
2. Extract the smallest element and add its next node to the heap.
3. Continue until the heap is empty.

### ✅ **Code:**
```java
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) heap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) {
                heap.offer(node.next);
            }
        }

        return dummy.next;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N log k) — Each insertion and deletion takes O(log k), where N is the total number of nodes.
- **Space:** O(k) — Stores up to `k` elements in the heap.

---

## 📚 Key Takeaways

1. Use a Heap/Priority Queue when you need quick access to the smallest or largest elements.
2. A min-heap allows retrieval of the smallest element in O(1) time, while a max-heap allows retrieval of the largest.
3. Time complexity typically involves O(log k) for insertions and deletions.
4. Heaps are particularly useful for solving dynamic problems involving real-time data or priority-based execution.

---

Next, let's dive into the **Bit Manipulation Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

