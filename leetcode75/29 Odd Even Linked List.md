# Odd Even Linked List

## 📌 Problem Statement

**LeetCode Problem:** [328. Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)  
**Difficulty:** Medium  

**Description:**  
Given the `head` of a **singly linked list**, group all nodes with **odd indices** together followed by the nodes with **even indices**, and return the reordered list.

- The **first node** is considered **odd**, the **second node** is considered **even**, and so on.
- Maintain the **relative order** of nodes within both odd and even groups.

### **Example 1:**
**Input:**  
```
head = [1,2,3,4,5]
```
**Output:**  
```
[1,3,5,2,4]
```

### **Example 2:**
**Input:**  
```
head = [2,1,3,5,6,4,7]
```
**Output:**  
```
[2,3,6,7,1,5,4]
```

### **Constraints:**
- The number of nodes in the linked list is in the range `[0, 10^4]`.
- `-10^6 <= Node.val <= 10^6`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **train** with connected compartments, and you want to **separate odd and even-numbered compartments** while keeping their relative order the same.

For example:
- **[1,2,3,4,5]** → Move all **odd-positioned** compartments to the front → **[1,3,5,2,4]**.
- **[2,1,3,5,6,4,7]** → **Odd indices first, then even indices** → **[2,3,6,7,1,5,4]**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we group odd and even nodes efficiently?**
   - Use **two pointers**: one for odd-indexed nodes and one for even-indexed nodes.
2. **How do we maintain relative order?**
   - Link odd-indexed nodes together and even-indexed nodes together.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! By traversing the list once and modifying pointers.

👉 We need a solution that runs in **O(N) time** with **O(1) space**.

---

## 🏗️ Brute Force Solution (O(N) Time, O(N) Space) 🚨

```java
import java.util.*;

public class OddEvenLinkedListBruteForce {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        
        ListNode temp = head;
        int index = 1;
        
        while (temp != null) {
            if (index % 2 == 1) {
                odd.add(temp.val);
            } else {
                even.add(temp.val);
            }
            temp = temp.next;
            index++;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;
        
        for (int val : odd) {
            newHead.next = new ListNode(val);
            newHead = newHead.next;
        }
        for (int val : even) {
            newHead.next = new ListNode(val);
            newHead = newHead.next;
        }
        
        return dummy.next;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list twice.
- **Space Complexity:** `O(N)`, for storing node values in lists.

🚨 **This is inefficient due to extra memory usage!**

---

## 🚀 Optimized Solution Using Pointers (O(N) Time, O(1) Space)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class OddEvenLinkedListOptimized {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode odd = head, even = head.next, evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;
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
- "We use **two pointers** to separate odd and even nodes."
- "Odd nodes are linked first, followed by even nodes."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N) space`) stores values but is inefficient."
- "Using **pointers to rearrange nodes** is the best (`O(N) time, O(1) space`)."

---

## 🔥 Final Takeaways
- **Use two pointers to efficiently rearrange odd and even nodes.**
- **Optimize brute-force approaches by modifying the linked list directly.**
- **Maintain the original relative order of nodes.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/odd-even-linked-list/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirtieth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

