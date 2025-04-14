# Lowest Common Ancestor of a Binary Tree

## 📌 Problem Statement

**LeetCode Problem:** [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)  
**Difficulty:** Medium  

**Description:**  
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in T that has both `p` and `q` as descendants (where we allow a node to be a descendant of itself)."

### **Example 1:**
**Input:** 
```
root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
```
**Output:** 
```
3
```
**Explanation:** The LCA of nodes 5 and 1 is 3.

### **Example 2:**
**Input:** 
```
root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
```
**Output:** 
```
5
```
**Explanation:** The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

### **Example 3:**
**Input:** 
```
root = [1,2], p = 1, q = 2
```
**Output:** 
```
1
```

### **Constraints:**
- The number of nodes in the tree is in the range `[2, 10^5]`.
- `-10^9 <= Node.val <= 10^9`
- All `Node.val` are unique.
- `p != q`
- `p` and `q` will exist in the tree.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine a family tree with parents, children, grandchildren, etc. The "Lowest Common Ancestor" (LCA) is like finding the youngest common grandparent between two family members.

For example, if we have two cousins, their LCA would be their shared grandparent, not their great-grandparent (who would also be a common ancestor, but not the "lowest" or closest one).

In a binary tree, nodes are connected in a similar way to a family tree. We need to find the lowest (closest to the given nodes) common ancestor of two specific nodes.

The definition allows a node to be its own ancestor. This means if one of our target nodes is the parent of the other, that parent is the lowest common ancestor.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we know when we've found the LCA?**
   - If we find either p or q in a subtree, we mark that subtree as containing a target node.
   - If both p and q are found in different subtrees of a node, that node is the LCA.
   - If one of the nodes (p or q) is the current node and the other is in its subtree, the current node is the LCA.
2. **Can we solve this without storing parent pointers?**
   - Yes, we can use recursion to search both subtrees.
3. **What happens if p and q are not in the same subtree?**
   - We will need to traverse up to find their common ancestor.

👉 A recursive approach allows us to efficiently search both subtrees and identify the LCA!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define a recursive function that searches for p and q
```java
private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base case: if we reach null or find one of the nodes
    if (root == null || root == p || root == q) {
        return root;
    }
    
    // Search in left and right subtrees
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    
    // Decide based on search results
    if (left != null && right != null) {
        return root; // Both nodes found in different subtrees
    }
    
    return (left != null) ? left : right; // Return the non-null result if any
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if we reach null or find one of the nodes
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // Case 1: If both left and right subtrees contain either p or q,
        // then the current node is the LCA
        if (left != null && right != null) {
            return root;
        }
        
        // Case 2: If only one subtree contains either p or q,
        // return that subtree's result
        return (left != null) ? left : right;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. In the worst case, we might need to visit all nodes.
- **Space Complexity:** `O(H)`, where H is the height of the tree, which is used by the recursion stack. In the worst case of a skewed tree, this could be `O(N)`.

---

## 🚀 Alternative Solution Using Parent Pointers

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Create a map to store parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null); // Root has no parent
        
        // BFS to populate parent pointers
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // Continue BFS until we've found both p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        
        // Set to store ancestors of p
        Set<TreeNode> ancestors = new HashSet<>();
        
        // Traverse up from p to root, adding each ancestor to the set
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        
        // Traverse up from q until we find the first common ancestor
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        
        return q;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. We traverse the tree once in BFS and then trace up to root from p and q.
- **Space Complexity:** `O(N)`, for storing parent pointers and ancestors.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I used a **recursive depth-first search** to find the lowest common ancestor."
- "The key insight is that if both target nodes are in different subtrees of a node, that node must be their lowest common ancestor."
- "If we find one of the nodes (p or q), we return it up the recursion tree."
- "If both subtrees return a non-null value, we've found the LCA."
- "If only one subtree returns a non-null value, we pass that value up, as it represents either the LCA or one of our target nodes."

If the interviewer asks for **alternative approaches**:
- "We could also use a **parent pointer technique**, where we first map each node to its parent."
- "Then, we collect all ancestors of p in a set, and traverse up from q until we find the first node that's also an ancestor of p."
- "Both approaches are `O(N)` time complexity, but the second uses more memory for the parent map and ancestor set."

---

## 🔥 Final Takeaways
- **Recursive DFS is elegant** for tree problems where we need to combine results from subtrees.
- **Understanding the definition of LCA** is crucial (especially that a node can be an ancestor of itself).
- **The LCA problem appears in many variants**, so understanding the core technique is valuable.
- **Consider edge cases** like when one node is an ancestor of the other, or when nodes are in the same subtree.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-eighth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.