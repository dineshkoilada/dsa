# Reverse Linked List

## 📌 Problem Statement

**LeetCode Problem:** [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)  
**Difficulty:** Easy  

**Description:**  
Given the `head` of a **singly linked list**, reverse the list and return its new head.

### **Example 1:**
**Input:**  
```
head = [1,2,3,4,5]
```
**Output:**  
```
[5,4,3,2,1]
```

### **Example 2:**
**Input:**  
```
head = [1,2]
```
**Output:**  
```
[2,1]
```

### **Constraints:**
- The number of nodes in the list is in the range `[0, 5000]`.
- `-5000 <= Node.val <= 5000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **train** with connected compartments, and you want to **reverse the order of compartments**.

For example:
- **[1,2,3,4,5]** → Reverse all compartments → **[5,4,3,2,1]**.
- **[1,2]** → Reverse → **[2,1]**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we reverse the linked list efficiently?**
   - Use **iterative or recursive approaches**.
2. **What happens when we reach the last node?**
   - It should become the **new head**.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! By **reversing pointers** instead of creating a new list.

👉 We need a solution that runs in **O(N) time** with **O(1) space** (for iterative) or **O(N) space** (for recursive).

---

## 🏗️ Brute Force Solution (O(N) Time, O(N) Space) 🚨

```java
import java.util.*;

public class ReverseLinkedListBruteForce {
    public static ListNode reverseList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;
        for (int i = values.size() - 1; i >= 0; i--) {
            newHead.next = new ListNode(values.get(i));
            newHead = newHead.next;
        }
        return dummy.next;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list twice.
- **Space Complexity:** `O(N)`, for storing node values in a list.

🚨 **This is inefficient due to extra memory usage!**

---

## 🚀 Optimized Solution Using Iteration (O(N) Time, O(1) Space)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class ReverseLinkedListIterative {
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list once.
- **Space Complexity:** `O(1)`, since no extra memory is used.

---

## 🚀 Optimized Solution Using Recursion (O(N) Time, O(N) Space)

```java
public class ReverseLinkedListRecursive {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list once.
- **Space Complexity:** `O(N)`, due to recursive call stack.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We reverse the list by **reversing the pointers** between nodes."
- "An **iterative approach** uses `O(1)` space, while **recursion** uses `O(N)`."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N) space`) stores values but is inefficient."
- "Using **pointers to reverse in-place** is the best (`O(N) time, O(1) space`)."

---

## 🔥 Final Takeaways
- **Use iteration for `O(1)` space efficiency.**
- **Recursion simplifies logic but uses `O(N)` space.**
- **Avoid brute-force approaches that require extra storage.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/reverse-linked-list/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-first problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

