# Search in a Binary Search Tree

## 📌 Problem Statement

**LeetCode Problem:** [700. Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/)  
**Difficulty:** Easy  

**Description:**  
You are given the `root` of a binary search tree (BST) and an integer `val`.

Find the node in the BST that the node's value equals `val` and return the subtree rooted with that node. If such a node does not exist, return `null`.

### **Example 1:**
**Input:** 
```
root = [4,2,7,1,3], val = 2
```
**Output:** 
```
[2,1,3]
```

### **Example 2:**
**Input:** 
```
root = [4,2,7,1,3], val = 5
```
**Output:** 
```
[]
```

### **Constraints:**
- The number of nodes in the tree is in the range `[1, 5000]`.
- `1 <= Node.val <= 10^7`
- `root` is a binary search tree.
- `1 <= val <= 10^7`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a special tree called a Binary Search Tree (BST). In this tree, for any node:
- All nodes to the left have smaller values
- All nodes to the right have larger values

For example, in this tree:
```
     4
    / \
   2   7
  / \
 1   3
```

- The root has value 4
- The left subtree has values less than 4 (2, 1, 3)
- The right subtree has values greater than 4 (7)

Your job is to find a node with a specific value. If you find it, return the entire subtree with that node as the root. If you don't find it, return nothing (null).

If we're looking for the value 2, we'd return the subtree:
```
   2
  / \
 1   3
```

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we use the BST property to efficiently search?**
   - If the current node's value is greater than what we're looking for, go left.
   - If it's smaller, go right.
   - If it's equal, we found it!
2. **What's the fastest way to search in a BST?**
   - Use the BST property to eliminate half of the remaining subtree at each step.
3. **What if the value isn't in the tree?**
   - We'll eventually reach a null node, and we should return null.

👉 The BST property gives us a powerful way to search with `O(log N)` efficiency!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Check if current node is null or matches our target
```java
if (root == null || root.val == val) {
    return root;
}
```

### Step 2: Decide which subtree to search based on BST property
```java
if (val < root.val) {
    return searchBST(root.left, val);
} else {
    return searchBST(root.right, val);
}
```

---

## 🛠️ Writing the Recursive Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: either we found the node or reached a leaf
        if (root == null || root.val == val) {
            return root;
        }
        
        // BST property: If target is smaller, go left
        if (val < root.val) {
            return searchBST(root.left, val);
        }
        // BST property: If target is larger, go right
        else {
            return searchBST(root.right, val);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(H)`, where H is the height of the tree. In the worst case of a skewed tree, this would be `O(N)`. In a balanced BST, it would be `O(log N)`.
- **Space Complexity:** `O(H)` for the recursion stack, which corresponds to the height of the tree.

---

## 🚀 Iterative Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Start from the root
        TreeNode current = root;
        
        // Keep searching until we find the node or reach a leaf
        while (current != null && current.val != val) {
            if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        return current;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(H)`, where H is the height of the tree. Same as the recursive approach.
- **Space Complexity:** `O(1)` since we only use a constant amount of extra space regardless of input size.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I leveraged the **Binary Search Tree property** to efficiently search for the target value."
- "At each node, I compare the current value with the target:"
  - "If they're equal, I've found the node and return it."
  - "If the target is smaller, I search in the left subtree."
  - "If the target is larger, I search in the right subtree."
- "This approach has a time complexity of `O(H)`, where H is the height of the tree, which is `O(log N)` for a balanced BST."

If the interviewer asks about recursive vs. iterative approaches:
- "The recursive approach is more concise and mirrors the tree structure naturally."
- "However, the iterative approach uses constant space (`O(1)`) regardless of tree height, which can be an advantage for very deep trees where stack overflow might be a concern."
- "Both approaches have the same time complexity of `O(H)`."

---

## 🔥 Final Takeaways
- **Binary Search Tree property** makes searching very efficient - `O(log N)` for balanced trees.
- **Both recursive and iterative approaches** can solve this problem effectively.
- **Recursion uses stack space** proportional to tree height, while iteration uses constant space.
- **This is a fundamental BST operation** that's used in many more complex algorithms.
- **Remember to check for null nodes** before accessing their values to avoid null pointer exceptions.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/search-in-a-binary-search-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-first problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.