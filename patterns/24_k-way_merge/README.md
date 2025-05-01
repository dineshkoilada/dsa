# K-way Merge Pattern

## 🎯 Introduction

Imagine you have multiple sorted lists of items, and you need to combine them into a single sorted list efficiently. The **K-way Merge** pattern is a powerful technique for merging multiple sorted arrays, lists, or other data structures while maintaining the sorted order.

The K-way Merge Pattern is particularly useful for:
- Merging multiple sorted arrays or lists
- External sorting with limited memory
- Handling distributed sorted data
- Finding the kth smallest element across multiple sorted sequences
- Implementing efficient external merge sort

This pattern works best when you have **K sorted sequences** that need to be combined into a single sorted result.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Do you need to merge multiple sorted arrays or lists?
   - Is the result required to be in sorted order?
   - How many arrays/lists (K) are there to merge?

2. **Ask Clarifying Questions:**
   - What is the size of each input array or list?
   - Are there any constraints on memory usage?
   - Are there any duplicate elements across arrays?

3. **Identify Clues for Using K-way Merge:**
   - Multiple sorted input sequences are mentioned
   - Need to find elements that satisfy a condition across multiple sequences
   - Problem requires combining sorted sequences efficiently

4. **Predicting if K-way Merge Is Applicable:**
   - Does the problem involve multiple sorted sequences?
   - Is maintaining sorted order in the result important?
   - Would a simple approach (like merging all and re-sorting) be inefficient?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- How many arrays/lists need to be merged?
- Are they all sorted in the same order (ascending/descending)?
- What is the expected output format?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be done with duplicate elements?
- Are there any constraints on time or space complexity?

### ✅ **3. Identify Base Cases**
- Empty arrays: Skip or consider based on problem requirements
- Single array: Return as is
- Two arrays: Use standard merge algorithm from merge sort

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function kWayMerge(arrays):
    if arrays is empty:
        return empty array
    if arrays has single array:
        return that array
    
    initialize minHeap
    
    // Add first element from each array to the heap
    for each array in arrays:
        if array is not empty:
            add (value, arrayIndex, elementIndex) to minHeap
    
    initialize result array
    
    while minHeap is not empty:
        value, arrayIndex, elementIndex = extract minimum from minHeap
        add value to result
        
        // Add next element from the same array
        if elementIndex + 1 < length of arrays[arrayIndex]:
            add (arrays[arrayIndex][elementIndex + 1], arrayIndex, elementIndex + 1) to minHeap
    
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class KWayMerge {
    public static int[] mergeKSortedArrays(int[][] arrays) {
        // Skip empty input
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        
        // Create a min heap to store the smallest element from each array
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Calculate total length of result array
        int totalLength = 0;
        for (int[] array : arrays) {
            totalLength += array.length;
            // Add first element from each non-empty array
            if (array.length > 0) {
                // Store: [value, arrayIndex, elementIndex]
                minHeap.offer(new int[]{array[0], 0, 0}); 
            }
        }
        
        int[] result = new int[totalLength];
        int resultIndex = 0;
        
        // Process elements from the min heap
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int value = current[0];
            int arrayIndex = current[1];
            int elementIndex = current[2];
            
            // Add the smallest element to result
            result[resultIndex++] = value;
            
            // If there are more elements in the same array, add the next one
            if (elementIndex + 1 < arrays[arrayIndex].length) {
                minHeap.offer(new int[]{
                    arrays[arrayIndex][elementIndex + 1], 
                    arrayIndex, 
                    elementIndex + 1
                });
            }
        }
        
        return result;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Arrays with different lengths
- All empty arrays
- Arrays containing duplicate elements
- Very large K (number of arrays)
- Very long arrays

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Initialization          | O(K)            | O(K)             |
| Heap operations         | O(N log K)      | O(K)             |
| Overall                 | O(N log K)      | O(K + N)         |

**How to derive these complexities:**
- **Time Complexity:** O(N log K) where N is the total number of elements across all arrays, and K is the number of arrays. Each heap operation is O(log K), and we do this N times.
- **Space Complexity:** O(K) for the heap + O(N) for the result array = O(K + N).

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
- **Time:** O(N log K) — Where N is the total number of elements across all arrays
- **Space:** O(K + N) — For the heap and result array

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
    
    // Add the first element of each array to the min heap
    for (int i = 0; i < arrays.length; i++) {
        if (arrays[i].length > 0) {
            // [value, arrayIndex, elementIndex]
            minHeap.offer(new int[]{arrays[i][0], i, 0});
        }
    }
    
    // Extract smallest elements k-1 times
    int count = 0;
    int result = 0;
    
    while (!minHeap.isEmpty() && count < k) {
        int[] current = minHeap.poll();
        int value = current[0];
        int arrayIndex = current[1];
        int elementIndex = current[2];
        
        result = value;
        count++;
        
        // Add the next element from the same array
        if (elementIndex + 1 < arrays[arrayIndex].length) {
            minHeap.offer(new int[]{
                arrays[arrayIndex][elementIndex + 1], 
                arrayIndex, 
                elementIndex + 1
            });
        }
    }
    
    if (count < k) {
        throw new IllegalArgumentException("K is larger than the total number of elements");
    }
    
    return result;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(K log M) — We perform K extractions from a heap of size M
- **Space:** O(M) — For the heap storing one element from each array

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
    // Skip empty input
    if (lists == null || lists.length == 0) {
        return null;
    }
    
    // Create a min heap based on node values
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
    
    // Add the head of each non-empty list to the heap
    for (ListNode head : lists) {
        if (head != null) {
            minHeap.offer(head);
        }
    }
    
    // Create a dummy node to build the result list
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    // Process nodes from the min heap
    while (!minHeap.isEmpty()) {
        // Get the smallest node
        ListNode current = minHeap.poll();
        tail.next = current;
        tail = tail.next;
        
        // If there's a next node in the same list, add it to the heap
        if (current.next != null) {
            minHeap.offer(current.next);
        }
    }
    
    return dummy.next;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N log K) — N is the total number of nodes across all lists
- **Space:** O(K) — For the heap storing one node from each list

---

## 📚 Key Takeaways

1. The K-way Merge pattern uses a **min heap** to efficiently track and extract the minimum element from K sorted sequences.
2. Time complexity is typically **O(N log K)** where N is the total number of elements and K is the number of arrays/lists.
3. This pattern is more efficient than merging all elements and re-sorting when K is large.
4. The pattern can be extended to solve various problems involving multiple sorted data sources.
5. Using a priority queue (heap) is essential for implementing this pattern efficiently.

---

Next, lets dive deep into **subsets**.