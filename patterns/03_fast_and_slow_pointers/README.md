# Fast and Slow Pointers Pattern

## 🎯 Introduction

Imagine you’re running on a circular track with a friend. You both start at the same point, but you run faster than your friend. If the track loops, eventually, you’ll catch up and meet again. This concept is the foundation of the **Fast and Slow Pointers Pattern**—also known as the **Tortoise and Hare Algorithm**.

This pattern is particularly useful for:
- Detecting cycles in linked lists
- Finding the starting point of a loop
- Determining the middle element of a linked list
- Checking if a number is a happy number

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Is there a need to detect a loop or repeated sequence?
   - Do you need to find a middle element efficiently?

2. **Ask Clarifying Questions:**
   - Does the list have cycles?
   - Is it a singly or doubly linked list?
   - What should happen if no cycle is detected?

3. **Identify Clues for Using Fast and Slow Pointers:**
   - You need to detect a cycle in a linked list.
   - You need to find a middle point in a sequence efficiently.

4. **Predicting if Fast and Slow Pointers Is Applicable:**
   - Is there a possibility of a cycle in the data structure?
   - Are you trying to find a midpoint without knowing the length beforehand?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are we detecting cycles?
- Are we looking for a specific point in a sequence?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned if the input is empty?
- Should the function return the node where the cycle starts?

### ✅ **3. Identify Base Cases**
- If the list is empty or has only one node, return immediately.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function detectCycle(head):
    if head is null or head.next is null:
        return false

    slow = head
    fast = head

    while fast != null and fast.next != null:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return true

    return false
```

### ✅ **5. Write the Code Skeleton**
```java
public class FastSlowPointersTemplate {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The linked list is empty.
- The linked list contains only one node.
- No cycle is present.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                | Time Complexity | Space Complexity |
|--------------------------|-----------------|------------------|
| Detecting cycle          | O(n)            | O(1)             |
| Finding middle element   | O(n)            | O(1)             |
| Detecting loop start     | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Both pointers traverse the list. In the worst-case scenario, they will traverse the list length `n`.
- **Space Complexity:** Only two pointers are used, regardless of the list size.

---

## 📚 Example 1: Easy Problem - Find Middle Node of Linked List

**Problem:**
Given a singly linked list, find its middle node.

**Input:** `1 -> 2 -> 3 -> 4 -> 5`

**Expected Output:** `3`

### 🔑 **Solution Steps**
1. Initialize both `slow` and `fast` pointers at the head.
2. Move `slow` by one step and `fast` by two steps.
3. When `fast` reaches the end, `slow` will be at the middle.

### ✅ **Code:**
```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MiddleOfLinkedList {
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Every node is visited once.
- **Space:** O(1) — Only two pointers are used.

---

## 📚 Example 2: Medium Problem - Detect a Cycle in a Linked List

**Problem:**
Given a linked list, determine if it contains a cycle.

**Input:** `1 -> 2 -> 3 -> 4 -> 2 (cycle)`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. Initialize both `slow` and `fast` pointers at the head.
2. Move `slow` one step and `fast` two steps.
3. If `slow` and `fast` meet, there is a cycle.
4. If `fast` reaches the end, there’s no cycle.

### ✅ **Code:**
```java
public class DetectCycle {
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each node is visited at most twice.
- **Space:** O(1) — Only two pointers are used.

---

## 📚 Example 3: Hard Problem - Find the Starting Node of a Cycle

**Problem:**
Given a linked list that contains a cycle, return the node where the cycle begins.

**Input:** `1 -> 2 -> 3 -> 4 -> 2 (cycle starts at node 2)`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Use the previous cycle detection algorithm.
2. After detecting the cycle, reset one pointer to the head.
3. Move both pointers one step at a time.
4. The point where they meet is the start of the cycle.

### ✅ **Code:**
```java
public class CycleStartNode {
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each node is visited at most twice.
- **Space:** O(1) — Only two pointers are used.

---

## 📚 Key Takeaways

1. Use **Fast and Slow Pointers** for problems related to cycles and midpoints in linked lists.
2. This pattern efficiently detects cycles in O(n) time and O(1) space.
3. Use two pointers moving at different speeds to uncover hidden structures within data.
4. This technique also helps find starting points of loops without extra space.

---

Next, let’s explore the **Binary Search Pattern** for solving problems that require efficient searching in sorted arrays or ranges!

