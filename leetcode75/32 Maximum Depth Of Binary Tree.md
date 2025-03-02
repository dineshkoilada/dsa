# Maximum Depth of a Binary Tree

## 📌 Problem Statement

**LeetCode Problem:** [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)  
**Difficulty:** Easy  

**Description:**  
Given the `root` of a binary tree, return its **maximum depth**.

- The **maximum depth** of a binary tree is the number of nodes along the longest path from the root node down to the farthest leaf node.

### **Example 1:**
**Input:**  
```
root = [3,9,20,null,null,15,7]
```
**Output:**  
```
3
```

### **Example 2:**
**Input:**  
```
root = [1,null,2]
```
**Output:**  
```
2
```

### **Constraints:**
- The number of nodes in the tree is in the range `[0, 10^4]`.
- `-100 <= Node.val <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **treehouse** where each branch leads to another **treehouse**. The **maximum depth** is how many levels you need to climb **to reach the farthest treehouse**.

For example:
- **[3,9,20,null,null,15,7]** → The longest path is `3 → 20 → 15` or `3 → 20 → 7` → **Depth = 3**.
- **[1,null,2]** → The longest path is `1 → 2` → **Depth = 2**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we explore all paths efficiently?**
   - Use **recursion** or **BFS/DFS**.
2. **What happens if the tree is empty?**
   - Return `0`.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using **recursion or level-order traversal**.

👉 We need a solution that runs in **O(N) time** with **O(N) space** (worst case for recursion stack).

---

## 🏗️ Brute Force Solution (O(N) Time, O(N) Space) 🚨

```java
public class MaximumDepthBruteForce {
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we visit each node once.
- **Space Complexity:** `O(N)`, for the recursion stack in the worst case (skewed tree).

🚨 **This is optimal, but recursion can use extra memory!**

---

## 🚀 Optimized Solution Using BFS (O(N) Time, O(N) Space)

```java
import java.util.*;

public class MaximumDepthBFS {
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we visit each node once.
- **Space Complexity:** `O(N)`, for storing nodes in the queue.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **recursion** to find the maximum depth by exploring all paths."
- "Alternatively, we can use **BFS (level-order traversal)** to count levels."
- "Both approaches run in **O(N) time**."

If the interviewer asks for **alternative approaches**, you can say:
- "Recursion (`O(N)`) is intuitive but can use extra stack space."
- "BFS (`O(N)`) avoids recursion issues but requires a queue."

---

## 🔥 Final Takeaways
- **Use recursion for a simple, elegant solution.**
- **Use BFS for an iterative, stack-safe approach.**
- **Avoid unnecessary computations by returning early for `null` nodes.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-depth-of-binary-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-third problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

