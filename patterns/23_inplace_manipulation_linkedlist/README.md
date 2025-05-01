# In-place Manipulation of Linked Lists Pattern

## 🎯 Introduction

Imagine you're working with a chain of connected nodes and need to rearrange them without using extra space. The **In-place Manipulation of Linked Lists** pattern is a powerful technique for solving problems that require modifying linked lists in their original memory location without using additional data structures.

The In-place Manipulation of Linked Lists Pattern is particularly useful for:
- Reversing linked lists or parts of linked lists
- Detecting and removing cycles
- Merging or partitioning linked lists
- Reordering nodes based on specific criteria
- Removing duplicates from sorted linked lists

This pattern works best when you need to efficiently manipulate linked lists while minimizing space usage.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Does the problem involve manipulating a linked list?
   - Can the manipulation be done in-place?
   - Are there constraints on using additional space?

2. **Ask Clarifying Questions:**
   - What is the size of the linked list?
   - Can we modify the values in the nodes or only the pointers?
   - Are there any special requirements for handling edge cases (empty list, single node, etc.)?

3. **Identify Clues for Using In-place Manipulation:**
   - Problem requires rearranging or modifying a linked list
   - Constraints mention O(1) space complexity
   - Keywords like "reverse," "swap," "reorder," or "rearrange"

4. **Predicting if In-place Manipulation Is Applicable:**
   - The problem requires modifying the structure of a linked list
   - Additional space usage needs to be minimized
   - The solution involves pointer manipulation rather than creating new nodes

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Is this a singly linked list or doubly linked list?
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
        // Save the next pointer to avoid losing the rest of the list
        next = current.next
        
        // Manipulation logic (reverse, swap, etc.)
        perform manipulation on current node
        
        // Move to the next node
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
        // Base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Set up pointers
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        
        // Manipulation logic
        while (current != null) {
            next = current.next;    // Save next
            current.next = prev;    // Reverse the pointer
            prev = current;         // Move prev forward
            current = next;         // Move current forward
        }
        
        // The new head is the previous last node
        return prev;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty linked list
- Linked list with a single node
- Linked list with cycles
- Very long linked lists (potential stack overflow with recursive approaches)
- When reversing segments, be careful about connecting the segments properly

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Traversal               | O(n)            | O(1)             |
| Node manipulation       | O(1) per node   | O(1)             |
| Overall                 | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** We typically need to visit each node exactly once, leading to O(n) time complexity.
- **Space Complexity:** The key advantage of in-place manipulation is the O(1) space complexity, as we only use a constant number of pointers regardless of input size.

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

1. In-place manipulation is about **modifying the pointers** of a linked list without using extra data structures.
2. Always be careful with **edge cases** like empty lists and single nodes.
3. When manipulating pointers, be sure to **save next references** before modifying them to avoid losing parts of the list.
4. Use multiple pointers (prev, current, next) to keep track of nodes during manipulation.
5. This pattern excels when there are **space constraints** or when efficiency is paramount.

---

Next, lets dive deep into **K-way merge**.