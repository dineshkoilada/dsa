# Maximum Twin Sum of a Linked List

## 📌 Problem Statement

**LeetCode Problem:** [2130. Maximum Twin Sum of a Linked List](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/)  
**Difficulty:** Medium  

**Description:**  
In a **singly linked list** of even length `n`, the **twin sum** is defined as the sum of a node at index `i` and the node at index `n - 1 - i`. The **maximum twin sum** is the highest possible twin sum.

Return the **maximum twin sum** of the linked list.

### **Example 1:**
**Input:**  
```
head = [5,4,2,1]
```
**Output:**  
```
6
```

### **Example 2:**
**Input:**  
```
head = [4,2,2,3]
```
**Output:**  
```
7
```

### **Constraints:**
- The number of nodes in the list is in the range `[2, 10^5]`.
- `1 <= Node.val <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **train** with compartments, and you want to find the **largest sum of two mirrored compartments**.

For example:
- **[5,4,2,1]** → Pairs are `(5+1, 4+2)` → **Max sum is 6**.
- **[4,2,2,3]** → Pairs are `(4+3, 2+2)` → **Max sum is 7**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we pair the twin nodes efficiently?**
   - Find the **middle of the linked list**.
2. **How do we compare pairs efficiently?**
   - Reverse the second half of the list and compare.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using **fast and slow pointers** and **reversing half the list**.

👉 We need a solution that runs in **O(N) time** with **O(1) space**.

---

## 🏗️ Brute Force Solution (O(N) Time, O(N) Space) 🚨

```java
import java.util.*;

public class MaximumTwinSumBruteForce {
    public static int pairSum(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }
        
        int maxSum = 0;
        int n = values.size();
        for (int i = 0; i < n / 2; i++) {
            maxSum = Math.max(maxSum, values.get(i) + values.get(n - 1 - i));
        }
        return maxSum;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list twice.
- **Space Complexity:** `O(N)`, for storing values in a list.

🚨 **This is inefficient due to extra memory usage!**

---

## 🚀 Optimized Solution Using Fast & Slow Pointers and Reversal (O(N) Time, O(1) Space)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MaximumTwinSumOptimized {
    public static int pairSum(ListNode head) {
        ListNode slow = head, fast = head, prev = null;
        
        // Find middle of the list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half of the list
        while (slow != null) {
            ListNode nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }
        
        // Compare twin sums
        int maxSum = 0;
        while (prev != null) {
            maxSum = Math.max(maxSum, head.val + prev.val);
            head = head.next;
            prev = prev.next;
        }
        return maxSum;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the list three times.
- **Space Complexity:** `O(1)`, since we reverse the list in-place.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We **find the middle** using fast and slow pointers."
- "Then, we **reverse the second half** of the list."
- "Finally, we compare the twin sums while traversing from both ends."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N) space`) stores values but is inefficient."
- "Using **pointer reversal in-place** is the best (`O(N) time, O(1) space`)."

---

## 🔥 Final Takeaways
- **Use fast and slow pointers to find the middle efficiently.**
- **Reverse the second half of the list in-place to optimize space.**
- **Avoid brute-force approaches that require extra storage.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-second problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.