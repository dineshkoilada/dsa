# Heap / Priority Queue Pattern 🎯

## 📌 Introduction: The Power of Prioritization

Imagine you’re at an airport check-in counter. Passengers with first-class tickets or urgent needs are called ahead of others, regardless of their arrival time. The **Heap / Priority Queue Pattern** is your go-to tool for efficiently managing such prioritized retrievals in programming!

### 🎬 Real-World Analogies:

1. **Airport Boarding Queue** ✈️
   ```
   Passengers: [Economy][Business][First][Economy][First]
   Priority Queue: [First][First][Business][Economy][Economy]
   ```
2. **Hospital Emergency Room** 🏥
   ```
   Patients: [Mild][Critical][Serious][Mild]
   Heap: [Critical][Serious][Mild][Mild]
   ```
3. **Task Scheduler** 🗓️
   ```
   Tasks: [Low][High][Medium][High]
   Heap: [High][High][Medium][Low]
   ```

The heap/priority queue pattern is your secret weapon when you need:
- 📍 Fast access to the smallest/largest element (min/max)
- 🔄 Dynamic insertion and removal with prioritization
- 📊 Real-time processing of top-k elements (e.g., streaming data, leaderboards)
- 🚦 Efficient scheduling or resource allocation

### 🎯 Visual Example:
Finding the 3 largest numbers in a stream:
```
Stream:   [5] [1] [9] [3] [7] [2]
Heap:     [5] → [5,1] → [9,1,5] → [9,3,5] → [9,7,5] → [9,7,5]
Result:   [9,7,5]
```

---

## 🧠 How to Recognize a Heap / Priority Queue Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Top-K" Clue** 🏆
   - "Find the k largest/smallest elements"
   - "Kth largest/smallest element"
   - "Continuously maintain top performers"

2. **The "Dynamic Priority" Hint** ⚡
   - "Insert and remove elements based on priority"
   - "Process items in order of urgency/importance"

3. **The "Streaming/Real-Time" Signal** 🌊
   - "Process a stream of data and always know the top-k"
   - "Merge multiple sorted sources efficiently"

### 🤔 Essential Questions to Ask:

1. **Heap Type Questions:**
   ```
   Min-heap or Max-heap?
   ├── Min-heap: Always get the smallest (e.g., Dijkstra's shortest path)
   └── Max-heap: Always get the largest (e.g., top scores)
   ```
2. **Content Questions:**
   ```
   What are we prioritizing?
   ├── Numbers
   ├── Objects (with custom comparator)
   └── Complex structures (e.g., tasks, patients)
   ```
3. **Edge Case Questions:**
   ```
   What if the input is empty?
   What if k > n?
   Are there duplicate priorities?
   ```

### 🎨 Visual Problem-Solving Framework: “Airport Boarding with Priority Queue”

**Imagine:**
You’re managing an airport boarding queue. Passengers arrive with different ticket classes (priority). You want to always have the top-k most important passengers ready to board.

#### Step-by-Step Visualization

```
Step 1: Start with an empty boarding area (heap)
[   ][   ][   ][   ][   ]   ← No passengers yet

Step 2: Passengers arrive, you add them by priority
[🟦][   ][   ][   ][   ]   ← First passenger (Business)
[🟦][🟦][   ][   ][   ]   ← Next (First Class), heap reorders
[🟦][🟦][🟦][   ][   ]   ← Next (Economy), heap keeps top priorities

Step 3: If more than k passengers, remove the lowest priority
[🟦][🟦][🟦][🟦][   ]   ← Add another (First Class)
[🟦][🟦][🟦][🟦][🟦]   ← Add another (Business)
[🟦][🟦][🟦][🟦][🟦]   ← Add another (Economy), now 6 passengers
Remove lowest: [🟦][🟦][🟦][🟦][🟦]   ← Only top 5 remain

Step 4: Ready to board! The heap contains the k highest-priority passengers
[🟦][🟦][🟦][🟦][🟦]   ← Top-k ready for boarding
```

**Legend:**
🟦 = Passenger (with priority)
[   ] = Empty slot in the heap

---

**Key Takeaway:**
At every step, the heap (boarding area) only keeps the k most important passengers (elements). When a new one arrives, if the area is full, the least important is sent to the waiting area (removed from the heap).

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
        remove element with lowest/highest priority
return heap elements
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class HeapPriorityQueueTemplate {
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
2. Optimize time complexity from O(n log n) to O(n log k) for top-k problems.
3. Min-heaps retrieve the smallest element in O(1), max-heaps retrieve the largest.
4. Heaps are ideal for streaming, scheduling, and merging sorted data.

---

Next, let’s explore the **Bit Manipulation Pattern** for solving problems that involve clever use of binary operations and constraints!

