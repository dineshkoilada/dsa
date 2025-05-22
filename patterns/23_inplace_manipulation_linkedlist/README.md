# In-place Manipulation of Linked Lists Pattern 🔄

## 📌 Introduction: The Power of Pointer Play

Imagine you have a chain of paperclips and want to rearrange them—reverse, reorder, or remove some—without using any extra paperclips. The **In-place Manipulation of Linked Lists** pattern lets you efficiently modify linked lists by changing pointers, not by creating new nodes or using extra space!

### 🎬 Real-World Analogies:

1. **Paperclip Chain** 🖇️
   - Rearranging, reversing, or removing paperclips without adding new ones.
2. **Train Cars** 🚂
   - Reordering train cars by changing their connections, not by adding/removing cars.
3. **Bead Necklace** 📿
   - Swapping or reversing beads by re-threading, not by making a new necklace.

This pattern is your go-to for:
- 🔁 Reversing linked lists or segments
- 🧹 Removing duplicates or cycles
- 🔀 Reordering or partitioning nodes
- 🏎️ Achieving O(1) space solutions for linked list problems

---

## 🧠 How to Recognize an In-place Linked List Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Pointer Manipulation" Clue**
   - "Reverse", "swap", "reorder", or "remove" nodes
2. **The "O(1) Space" Hint**
   - Constraints mention constant space or in-place
3. **The "No Extra Structures" Signal**
   - Solution must not use arrays, stacks, or new nodes

### 🤔 Essential Questions to Ask:
- Is this a singly or doubly linked list?
- Can you modify node values, or only pointers?
- What should happen with empty or single-node lists?
- Are there cycles or special edge cases?

---

## 🎨 Visual Problem-Solving Framework

### In-place Reversal Example:
```
Original: 1 → 2 → 3 → 4 → 5 → NULL

Step-by-step:
1. prev = NULL, current = 1
2. next = 2, current.next = prev (NULL), prev = 1, current = 2
3. next = 3, current.next = prev (1), prev = 2, current = 3
4. ...
Result: 5 → 4 → 3 → 2 → 1 → NULL

Legend:
- prev: previous node
- current: node being processed
- next: node to process next
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Is this a singly or doubly linked list?
- Are the node values significant for the manipulation?
- What is the expected structure after manipulation?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen with an empty list?
- What should happen with a single node?
- Are there any specific constraints on time or space complexity?

### ✅ **3. Identify Base Cases**
- Empty list: Return null or handle accordingly
- Single node: May be the base case or require special handling

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function manipulateLinkedList(head):
    if head is null or head.next is null:
        return head // Base case: empty list or single node
    // Set up necessary pointers
    initialize pointers (prev, current, next, etc.)
    // Perform the manipulations
    while current is not null:
        next = current.next
        // Manipulation logic (reverse, swap, etc.)
        perform manipulation on current node
        update pointers
    return new head
```

### ✅ **5. Write the Code Skeleton**
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class InPlaceLinkedListManipulation {
    // Example: Reverse a linked list
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty linked list
- Linked list with a single node
- Linked list with cycles
- Very long linked lists (potential stack overflow with recursion)
- When reversing segments, be careful about connecting the segments properly

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Traversal               | O(n)            | O(1)             |
| Node manipulation       | O(1) per node   | O(1)             |
| Overall                 | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Each node is visited once.
- **Space Complexity:** Only a constant number of pointers are used.

---

## 📚 Example 1: Easy Problem - Reverse a Linked List

**Problem:**
Reverse a singly linked list.

**Input:**
```
1 -> 2 -> 3 -> 4 -> 5 -> NULL
```

**Expected Output:**
```
5 -> 4 -> 3 -> 2 -> 1 -> NULL
```

### 🔑 **Solution Steps**
1. Initialize three pointers: prev (null), current (head), and next
2. Iterate through the list, reversing each pointer
3. Return prev as the new head

### ✅ **Code:**
```java
public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    ListNode next = null;
    
    while (current != null) {
        next = current.next;      // Store next node
        current.next = prev;      // Reverse pointer
        prev = current;           // Move prev forward
        current = next;           // Move current forward
    }
    
    return prev;                  // New head
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — We visit each node exactly once
- **Space:** O(1) — Only using a constant amount of extra space

---

## 📚 Example 2: Medium Problem - Reorder List

**Problem:**
Given a singly linked list L: L0→L1→...→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...

**Input:**
```
1 -> 2 -> 3 -> 4 -> 5
```

**Expected Output:**
```
1 -> 5 -> 2 -> 4 -> 3
```

### 🔑 **Solution Steps**
1. Find the middle of the linked list
2. Reverse the second half
3. Merge the two halves by alternating nodes

### ✅ **Code:**
```java
public static void reorderList(ListNode head) {
    if (head == null || head.next == null) {
        return;
    }
    
    // Find the middle of the linked list
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Reverse the second half
    ListNode prev = null;
    ListNode current = slow.next;
    slow.next = null;  // Cut off the first half
    
    while (current != null) {
        ListNode next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    
    // Merge the two halves
    ListNode first = head;
    ListNode second = prev;
    
    while (second != null) {
        ListNode temp1 = first.next;
        ListNode temp2 = second.next;
        
        first.next = second;
        second.next = temp1;
        
        first = temp1;
        second = temp2;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Three passes through the list (find middle, reverse, merge)
- **Space:** O(1) — Only using pointer variables

---

## 📚 Example 3: Hard Problem - Sort List

**Problem:**
Sort a linked list in O(n log n) time using constant space complexity.

**Input:**
```
4 -> 2 -> 1 -> 3
```

**Expected Output:**
```
1 -> 2 -> 3 -> 4
```

### 🔑 **Solution Steps**
1. Use Merge Sort algorithm (divide and conquer)
2. Split the list into two halves
3. Recursively sort both halves
4. Merge the sorted halves

### ✅ **Code:**
```java
public static ListNode sortList(ListNode head) {
    // Base case
    if (head == null || head.next == null) {
        return head;
    }
    
    // Find the middle
    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = null;
    
    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Cut the list into two halves
    prev.next = null;
    
    // Recursively sort both halves
    ListNode left = sortList(head);
    ListNode right = sortList(slow);
    
    // Merge the sorted halves
    return merge(left, right);
}

private static ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            tail.next = l1;
            l1 = l1.next;
        } else {
            tail.next = l2;
            l2 = l2.next;
        }
        tail = tail.next;
    }
    
    // Attach remaining nodes
    if (l1 != null) {
        tail.next = l1;
    } else {
        tail.next = l2;
    }
    
    return dummy.next;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Merge sort algorithm
- **Space:** O(log n) — Stack space due to recursion, but O(1) auxiliary space for the actual manipulation

---

## 📚 Key Takeaways

1. In-place manipulation is about modifying the pointers of a linked list without using extra data structures.
2. Always be careful with edge cases like empty lists and single nodes.
3. When manipulating pointers, be sure to save next references before modifying them to avoid losing parts of the list.
4. Use multiple pointers (prev, current, next) to keep track of nodes during manipulation.
5. This pattern excels when there are space constraints or when efficiency is paramount.

---

Next, let's dive deep into **K-way merge** for efficiently merging multiple sorted lists!