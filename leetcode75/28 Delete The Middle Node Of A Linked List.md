# Delete the Middle Node of a Linked List

## 📌 Problem Statement

**LeetCode Problem:** [2095. Delete the Middle Node of a Linked List](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/)  
**Difficulty:** Medium  

**Description:**  
Given the `head` of a **singly linked list**, delete the **middle node**, and return the `head` of the modified linked list.

- If there are `n` nodes in the linked list and `n` is **odd**, delete the `(n // 2) + 1` node.
- If `n` is **even**, delete the `(n // 2)` node.

### **Example 1:**
**Input:**  
```
head = [1,3,4,7,1,2,6]
```
**Output:**  
```
[1,3,4,1,2,6]
```

### **Example 2:**
**Input:**  
```
head = [1,2,3,4]
```
**Output:**  
```
[1,2,4]
```

### **Constraints:**
- The number of nodes in the list is in the range `[2, 10^5]`.
- `1 <= Node.val <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **train** with connected compartments. Your task is to **remove the middle compartment** while keeping the train connected.
- If there are **7 compartments**, remove the **4th one**.
- If there are **6 compartments**, remove the **3rd one**.

For example:
- **[1,3,4,7,1,2,6]** → Remove `7` → **[1,3,4,1,2,6]**.
- **[1,2,3,4]** → Remove `3` → **[1,2,4]**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we find the middle node efficiently?**
   - Use the **fast and slow pointer** technique.
2. **How do we remove the middle node?**
   - Keep track of the previous node and change its `next` pointer.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using **one pass with two pointers**.

👉 We need a solution that runs in **O(N) time** with **O(1) space**.

---

## 🏗️ Brute Force Solution (O(N) Time, O(N) Space) 🚨

```java
import java.util.*;

public class DeleteMiddleNodeBruteForce {
    public static ListNode deleteMiddle(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }
        
        int mid = values.size() / 2;
        values.remove(mid);
        
        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;
        for (int val : values) {
            newHead.next = new ListNode(val);
            newHead = newHead.next;
        }
        return dummy.next;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list twice.
- **Space Complexity:** `O(N)`, for storing values.

🚨 **This is inefficient due to extra memory usage!**

---

## 🚀 Optimized Solution Using Fast and Slow Pointers (O(N) Time, O(1) Space)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class DeleteMiddleNodeOptimized {
    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode slow = head, fast = head, prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        prev.next = slow.next;
        return head;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list once.
- **Space Complexity:** `O(1)`, since no extra memory is used.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two pointers**: a fast one moving twice as fast as a slow one."
- "When fast reaches the end, slow is at the **middle**."
- "We remove the middle node by updating the previous node’s pointer."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N) space`) stores values but is inefficient."
- "Using **fast and slow pointers** is the best (`O(N) time, O(1) space`)."

---

## 🔥 Final Takeaways
- **Use Fast and Slow pointers to efficiently find the middle node.**
- **Optimize brute-force approaches by modifying the linked list directly.**
- **Keep track of the previous node to remove the middle node efficiently.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-ninth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

