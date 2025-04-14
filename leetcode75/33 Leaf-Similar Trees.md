# Leaf-Similar Trees

## 📌 Problem Statement

**LeetCode Problem:** [872. Leaf-Similar Trees](https://leetcode.com/problems/leaf-similar-trees/)  
**Difficulty:** Easy  

**Description:**  
Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a **leaf value sequence**.

For example, in the given tree `[3,5,1,6,2,9,8,null,null,7,4]`, the leaf value sequence is `(6, 7, 4, 9, 8)`.

Two binary trees are considered **leaf-similar** if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

### **Example 1:**
**Input:** 
```
root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
```
**Output:** 
```
true
```

### **Example 2:**
**Input:** 
```
root1 = [1,2,3], root2 = [1,3,2]
```
**Output:** 
```
false
```

### **Constraints:**
- The number of nodes in each tree will be in the range `[1, 200]`.
- Both of the given trees will have values in the range `[0, 200]`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine two trees in a forest. We only care about the **leaves** on these trees - the very end parts where there are no more branches coming out.

If we walk around the first tree from left to right and write down all the leaf values, we get a list. Then we do the same with the second tree and get another list. If both lists are exactly the same, then the trees are **leaf-similar**.

For example:
- First tree leaves: `6, 7, 4, 9, 8`
- Second tree leaves: `6, 7, 4, 9, 8`
- Since both lists are the same, the trees are leaf-similar.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we find all leaves in a tree?**
   - We can use a **tree traversal** to visit all nodes.
   - A node is a leaf if both its left and right children are `null`.
2. **How do we ensure left-to-right order?**
   - We can use **in-order** or **pre-order** traversal.
3. **What if the trees have different structures but the same leaf sequence?**
   - That's fine! We only care about the leaf values and their order.

👉 Let's think step-by-step to find a solution!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define a function to collect leaf values in order
```java
private void collectLeaves(TreeNode root, List<Integer> leaves) {
    if (root == null) return;
    
    // Check if this is a leaf node
    if (root.left == null && root.right == null) {
        leaves.add(root.val);
        return;
    }
    
    // Traverse left and right subtrees
    collectLeaves(root.left, leaves);
    collectLeaves(root.right, leaves);
}
```

### Step 2: Collect leaves from both trees
```java
List<Integer> leaves1 = new ArrayList<>();
List<Integer> leaves2 = new ArrayList<>();

collectLeaves(root1, leaves1);
collectLeaves(root2, leaves2);
```

### Step 3: Compare the leaf sequences
```java
return leaves1.equals(leaves2);
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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        
        collectLeaves(root1, leaves1);
        collectLeaves(root2, leaves2);
        
        return leaves1.equals(leaves2);
    }
    
    private void collectLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;
        
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        
        collectLeaves(root.left, leaves);
        collectLeaves(root.right, leaves);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N₁ + N₂)`, where `N₁` and `N₂` are the number of nodes in both trees. We traverse each node exactly once.
- **Space Complexity:** `O(L₁ + L₂)` for storing the leaf values, where `L₁` and `L₂` are the number of leaves in both trees. Plus `O(H₁ + H₂)` for the recursion stack, where `H₁` and `H₂` are the heights of the trees.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I used **depth-first traversal** to collect all leaf nodes from left to right."
- "A node is considered a leaf if both its left and right children are null."
- "After collecting leaves from both trees, I simply compared the two lists to check if they're identical."
- "This approach has a time complexity of `O(N₁ + N₂)` as we need to visit every node once in both trees."

If the interviewer asks for **alternative approaches**, you can say:
- "We could also use a single pass approach where we simultaneously traverse both trees and compare leaves as we find them."
- "We could use iterative traversal with stacks instead of recursion to save stack space."

---

## 🔥 Final Takeaways
- **Tree problems often require traversal** - know your pre-order, in-order, and post-order!
- **Define clearly what makes a leaf node** - in this case, no children.
- **Use built-in list comparison** when available instead of manually comparing elements.
- **Watch out for edge cases** like empty trees or trees with only one node.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/leaf-similar-trees/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-fourth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.