# Delete Node in a BST

## 📌 Problem Statement

**LeetCode Problem:** [450. Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/)  
**Difficulty:** Medium  

**Description:**  
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:
1. Search for a node to remove.
2. If the node is found, delete the node.

### **Example 1:**
**Input:** 
```
root = [5,3,6,2,4,null,7], key = 3
```
**Output:** 
```
[5,4,6,2,null,null,7]
```
**Explanation:** Given key to delete is 3. So we find the node with value 3 and delete it. One valid answer is [5,4,6,2,null,null,7], shown in the example. Another valid answer is [5,2,6,null,4,null,7].

### **Example 2:**
**Input:** 
```
root = [5,3,6,2,4,null,7], key = 0
```
**Output:** 
```
[5,3,6,2,4,null,7]
```
**Explanation:** The tree does not contain a node with value = 0.

### **Example 3:**
**Input:** 
```
root = [], key = 0
```
**Output:** 
```
[]
```

### **Constraints:**
- The number of nodes in the tree is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- Each node has a unique value.
- `root` is a valid binary search tree.
- `-10^5 <= key <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a special tree called a Binary Search Tree (BST). In this tree:
- For any node, all values in the left subtree are smaller
- All values in the right subtree are larger

Your job is to remove a specific node with a given value, while keeping the tree as a valid BST. This means after removal, all the BST properties must still be true.

Deleting a node can be tricky because we need to maintain the BST structure. There are three cases to consider:
1. The node has no children (easy - just remove it)
2. The node has one child (replace the node with its child)
3. The node has two children (replace with the smallest node from its right subtree or the largest node from its left subtree)

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we find the node to delete?**
   - Use the BST property (left < node < right) to navigate to the target node.
2. **What if the node has no children?**
   - Simply remove it by returning null to its parent.
3. **What if the node has one child?**
   - Replace the node with its child.
4. **What if the node has two children?**
   - Find the successor (smallest node in right subtree) or predecessor (largest node in left subtree).
   - Replace the node's value with the successor/predecessor's value.
   - Delete the successor/predecessor node (which is guaranteed to have at most one child).

👉 The key insight is handling the three cases differently!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Search for the node to delete
```java
if (root == null) return null;

if (key < root.val) {
    root.left = deleteNode(root.left, key);
} else if (key > root.val) {
    root.right = deleteNode(root.right, key);
} else {
    // Found the node to delete
    // Handle deletion based on the three cases
}
```

### Step 2: Handle the three deletion cases
```java
// Case 1: No children or only one child
if (root.left == null) {
    return root.right;
}
if (root.right == null) {
    return root.left;
}

// Case 3: Two children
// Find the inorder successor (smallest node in right subtree)
TreeNode successor = findMin(root.right);
// Replace current node's value with successor's value
root.val = successor.val;
// Delete the successor
root.right = deleteNode(root.right, successor.val);
```

### Step 3: Define helper function to find minimum value node
```java
private TreeNode findMin(TreeNode node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}
```

---

## 🛠️ Writing the Complete Solution

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
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: If tree is empty
        if (root == null) return null;
        
        // Search for the node to delete
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node found, handle deletion based on cases
            
            // Case 1: Node with no children or only right child
            if (root.left == null) {
                return root.right;
            }
            
            // Case 2: Node with only left child
            if (root.right == null) {
                return root.left;
            }
            
            // Case 3: Node with two children
            // Find the inorder successor (smallest node in right subtree)
            TreeNode successor = findMin(root.right);
            // Replace current node's value with successor's value
            root.val = successor.val;
            // Delete the successor
            root.right = deleteNode(root.right, successor.val);
        }
        
        return root;
    }
    
    // Helper function to find the minimum value node in a BST
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(H)`, where H is the height of the tree. In a balanced BST, this is `O(log N)`, but in worst case (skewed tree), it could be `O(N)`.
- **Space Complexity:** `O(H)` for the recursion stack, where H is the height of the tree.

---

## 🚀 Alternative Approach: Using Predecessor

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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node found
            
            // Case 1 & 2: No children or one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            
            // Case 3: Two children
            // Find the predecessor (largest node in left subtree)
            TreeNode predecessor = findMax(root.left);
            root.val = predecessor.val;
            // Delete the predecessor
            root.left = deleteNode(root.left, predecessor.val);
        }
        
        return root;
    }
    
    private TreeNode findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(H)`, same as the first approach.
- **Space Complexity:** `O(H)`, same as the first approach.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I tackled this problem using the standard algorithm for deleting a node in a BST, which handles three distinct cases:"

1. **Node with no children**: "If the node has no children, we can simply remove it by returning null to its parent."

2. **Node with one child**: "If the node has only one child, we replace the node with its child by returning that child to the parent."

3. **Node with two children**: "The most complex case is when a node has two children. Here, I find the inorder successor (smallest node in the right subtree), replace the target node's value with the successor's value, and then recursively delete the successor."

If the interviewer asks why this approach works:
- "This approach maintains the BST property because when a node has two children, its inorder successor is guaranteed to be larger than all nodes in the left subtree and smaller than all other nodes in the right subtree."
- "By moving the successor's value to the target node and then deleting the successor (which is guaranteed to have at most one child), we effectively maintain the ordering property of the BST."

---

## 🔥 Final Takeaways
- **BST deletion requires handling three cases** carefully.
- **Using inorder successor or predecessor** is a key technique for handling nodes with two children.
- **Recursion simplifies the implementation** by handling the tree restructuring implicitly.
- **Keep the BST property in mind** at all times: left subtree < node < right subtree.
- **Edge cases are important**: empty trees, nodes not found, and handling root deletion.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/delete-node-in-a-bst/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-second problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.