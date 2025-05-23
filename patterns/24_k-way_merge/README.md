# K-way Merge Pattern 🎯

## 📌 Introduction: The Power of Seamless Merging

Imagine you’re at a library with several sorted stacks of books, and you want to create one perfectly sorted shelf by picking the next smallest book from any stack. The **K-way Merge Pattern** is your go-to tool for efficiently combining multiple sorted arrays, lists, or data streams into a single sorted result—without losing order or wasting time!

### 🎬 Real-World Analogies:

1. **Library Book Sorting** 📚
   ```
   Stacks: [A][C][E], [B][D][F], [G][H][I]
   Merge:  [A][B][C][D][E][F][G][H][I]
   ```
2. **Airport Baggage Claim** 🛄
   ```
   Multiple conveyor belts (sorted by arrival time), merge into one pickup line.
   ```
3. **Multi-Source News Feed** 📰
   ```
   Merge sorted news from different sources into a single timeline.
   ```

The K-way Merge pattern is your secret weapon when you need:
- 📍 To merge multiple sorted arrays/lists/streams
- 🔄 To process distributed or external sorted data
- 🏆 To find the kth smallest/largest element across sorted sources
- 🚦 To implement efficient external merge sort

### 🎯 Visual Example:
Merging 3 sorted arrays:
```
Arrays:   [1 4 7]
          [2 5 8]
          [3 6 9]

Step 1: [1] (from first array)
Step 2: [1 2] (from second array)
Step 3: [1 2 3] (from third array)
Step 4: [1 2 3 4] (from first array)
...
Result:  [1 2 3 4 5 6 7 8 9]
```

---

## 🧠 How to Recognize a K-way Merge Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Multiple Sorted Inputs" Clue** 📑
   - "Merge K sorted arrays/lists/streams"
   - "Combine sorted data from different sources"
   - "Find the kth smallest/largest element across sorted sequences"

2. **The "Efficiency" Hint** ⚡
   - "External sorting with limited memory"
   - "Distributed or chunked sorted data"

3. **The "Priority Queue/Heap" Signal** 🏗️
   - "Always pick the smallest/largest among current heads"
   - "Use a min-heap or max-heap to track candidates"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   How many arrays/lists/streams are there?
   Are all inputs sorted in the same order?
   What is the expected output format?
   ```
2. **Content Questions:**
   ```
   Are there duplicate elements?
   Are the arrays/lists of different lengths?
   Are there memory or time constraints?
   ```
3. **Edge Case Questions:**
   ```
   What if some arrays are empty?
   What if K is very large?
   What if all arrays are empty?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Initialize Min-Heap with First Elements
[🟦][🟦][🟦]  👈 One from each array

Step 2: Extract Min, Add to Result
[🟦][🟦]      👈 Remove smallest, add next from same array

Step 3: Repeat Until Heap is Empty
[🟦]         👈 Always keep at most K elements in heap

Step 4: Result is Fully Merged and Sorted
[]           👈 All arrays merged into one
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- How many arrays/lists/streams?
- Are all sorted in the same order?
- What is the output format?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be done with duplicates?
- Are there constraints on time/space?

### ✅ **3. Identify Base Cases**
- All arrays empty: return empty result
- Single array: return as is
- Two arrays: use standard merge

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function kWayMerge(arrays):
    if arrays is empty:
        return []
    if arrays has one array:
        return that array
    initialize minHeap
    for each array:
        if not empty: add (value, arrayIndex, elementIndex) to minHeap
    result = []
    while minHeap not empty:
        value, arrIdx, elemIdx = minHeap.pop()
        result.append(value)
        if elemIdx + 1 < arrays[arrIdx].length:
            minHeap.add((arrays[arrIdx][elemIdx+1], arrIdx, elemIdx+1))
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class KWayMerge {
    public static int[] mergeKSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return new int[0];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int totalLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            totalLength += arrays[i].length;
            if (arrays[i].length > 0) {
                minHeap.offer(new int[]{arrays[i][0], i, 0});
            }
        }
        int[] result = new int[totalLength];
        int idx = 0;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            result[idx++] = curr[0];
            int arrIdx = curr[1], elemIdx = curr[2];
            if (elemIdx + 1 < arrays[arrIdx].length) {
                minHeap.offer(new int[]{arrays[arrIdx][elemIdx+1], arrIdx, elemIdx+1});
            }
        }
        return result;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Arrays with different lengths
- All arrays empty
- Arrays with duplicates
- Very large K
- Very long arrays

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Initialization    | O(K)            | O(K)             |
| Heap operations   | O(N log K)      | O(K)             |
| Overall           | O(N log K)      | O(K + N)         |

**How to derive these complexities:**
- **Time:** O(N log K), N = total elements, K = arrays/lists
- **Space:** O(K) for heap, O(N) for result

---

## 📚 Example 1: Easy Problem - Merge K Sorted Arrays

**Problem:**
Merge K sorted arrays into a single sorted array.

**Input:**
```
[
  [1, 4, 7],
  [2, 5, 8],
  [3, 6, 9]
]
```

**Expected Output:**
```
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```

### 🔑 **Solution Steps**
1. Create a min heap to track the smallest element from each array
2. Initialize the heap with the first element from each array
3. Repeatedly extract the minimum element from the heap, add it to the result
4. Add the next element from the array that contributed the min element
5. Repeat until the heap is empty

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N log K)
- **Space:** O(K + N)

---

## 📚 Example 2: Medium Problem - Find Kth Smallest Element in M Sorted Arrays

**Problem:**
Given M sorted arrays, find the Kth smallest element among all the arrays.

**Input:**
```
Arrays:
[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10]
]
K = 5
```

**Expected Output:**
```
4 (The 5th smallest element is 4)
```

### 🔑 **Solution Steps**
1. Use a min heap to keep track of the smallest elements
2. Add first elements from all arrays to the heap
3. Extract from the heap K times to find the Kth element

### ✅ **Code:**
```java
public static int findKthSmallest(int[][] arrays, int k) {
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < arrays.length; i++) {
        if (arrays[i].length > 0) {
            minHeap.offer(new int[]{arrays[i][0], i, 0});
        }
    }
    int count = 0, result = 0;
    while (!minHeap.isEmpty() && count < k) {
        int[] curr = minHeap.poll();
        result = curr[0];
        int arrIdx = curr[1], elemIdx = curr[2];
        count++;
        if (elemIdx + 1 < arrays[arrIdx].length) {
            minHeap.offer(new int[]{arrays[arrIdx][elemIdx+1], arrIdx, elemIdx+1});
        }
    }
    if (count < k) throw new IllegalArgumentException("K is larger than total elements");
    return result;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(K log M)
- **Space:** O(M)

---

## 📚 Example 3: Hard Problem - Merge K Sorted Linked Lists

**Problem:**
Merge K sorted linked lists into one sorted linked list.

**Input:**
```
[
  1->4->5,
  1->3->4,
  2->6
]
```

**Expected Output:**
```
1->1->2->3->4->4->5->6
```

### 🔑 **Solution Steps**
1. Create a min heap to track the smallest node from each list
2. Initialize the heap with the head of each list
3. Build the result list by repeatedly extracting the minimum node
4. Add the next node from the list that contributed the min node

### ✅ **Code:**
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
    for (ListNode head : lists) {
        if (head != null) minHeap.offer(head);
    }
    ListNode dummy = new ListNode(0), tail = dummy;
    while (!minHeap.isEmpty()) {
        ListNode curr = minHeap.poll();
        tail.next = curr;
        tail = tail.next;
        if (curr.next != null) minHeap.offer(curr.next);
    }
    return dummy.next;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N log K)
- **Space:** O(K)

---

## 📚 Key Takeaways

1. The K-way Merge pattern uses a **min heap** to efficiently track and extract the minimum element from K sorted sequences.
2. Time complexity is typically **O(N log K)** where N is the total number of elements and K is the number of arrays/lists.
3. This pattern is more efficient than merging all elements and re-sorting when K is large.
4. The pattern can be extended to solve various problems involving multiple sorted data sources.
5. Using a priority queue (heap) is essential for implementing this pattern efficiently.

---

Next, let’s dive into the **Subsets Pattern** for generating all possible combinations and subsets efficiently!