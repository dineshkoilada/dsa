# Count Good Nodes in Binary Tree

## 📌 Problem Statement

**LeetCode Problem:** [1448. Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree/)  
**Difficulty:** Medium  

**Description:**  
Given a binary tree `root`, a node X in the tree is named **good** if in the path from root to X there are no nodes with a value greater than X.

Return the number of **good** nodes in the binary tree.

### **Example 1:**
**Input:** 
```
root = [3,1,4,3,null,1,5]
```
**Output:** 
```
4
```
**Explanation:** Nodes in blue are good.
- Root Node (3) is always a good node.
- Node 4 -> (3,4) is the maximum value in the path starting from the root.
- Node 5 -> (3,4,5) is the maximum value in the path.
- Node 3 -> (3,1,3) is the maximum value in the path.

### **Example 2:**
**Input:** 
```
root = [3,3,null,4,2]
```
**Output:** 
```
3
```
**Explanation:** Node 2 -> (3,3,2) is not good, because "3" is higher than it.

### **Example 3:**
**Input:** 
```
root = [1]
```
**Output:** 
```
1
```
**Explanation:** Root Node (1) is a good node.

### **Constraints:**
- The number of nodes in the binary tree is in the range `[1, 10^5]`.
- Each node's value is between `[-10^4, 10^4]`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're climbing a tree, and as you go from the bottom to a specific branch, you keep track of the biggest number you've seen along the way.

A branch is considered **"good"** if the number at that branch is greater than or equal to any number you've seen while climbing to it.

For example:
- If you climb from 3 → 1 → 3, the second "3" is good because it's equal to the biggest number you've seen (which is 3).
- If you climb from 3 → 3 → 2, the "2" is not good because you've already seen a "3" which is bigger.

Our goal is to count all the **"good"** branches in the tree.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we track the maximum value seen so far on a path?**
   - We can pass it down as a parameter during tree traversal.
2. **What if a node's value equals the current maximum?**
   - It's still considered a good node, as the problem states "greater than X."
3. **Do we need to check all paths in the tree?**
   - Yes, we need to traverse the entire tree to check each node.

👉 Using recursion allows us to track the maximum value on each path separately!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define a function to traverse the tree and count good nodes
```java
private int dfs(TreeNode node, int maxSoFar) {
    if (node == null) return 0;
    
    // Is the current node a good node?
    int count = node.val >= maxSoFar ? 1 : 0;
    
    // Update the maximum value seen so far
    maxSoFar = Math.max(maxSoFar, node.val);
    
    // Recursively check left and right subtrees
    count += dfs(node.left, maxSoFar);
    count += dfs(node.right, maxSoFar);
    
    return count;
}
```

### Step 2: Call the function from the root
```java
public int goodNodes(TreeNode root) {
    return dfs(root, Integer.MIN_VALUE);
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
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
    
    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        
        // Is this a good node?
        int count = node.val >= maxSoFar ? 1 : 0;
        
        // Update maximum value seen on this path
        int newMax = Math.max(maxSoFar, node.val);
        
        // Check left and right children
        count += dfs(node.left, newMax);
        count += dfs(node.right, newMax);
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the number of nodes in the tree, as we visit each node exactly once.
- **Space Complexity:** `O(H)`, where `H` is the height of the tree, for the recursion stack. In the worst case, this could be `O(N)` for a skewed tree.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I used **depth-first traversal (DFS)** to visit every node in the tree."
- "At each node, I check if its value is greater than or equal to the maximum value seen so far on this path."
- "If yes, it's a good node, so I increment the counter."
- "I update the maximum value seen and continue to explore both left and right subtrees."
- "This approach ensures that for each node, we correctly know the maximum value in its ancestor path."

If the interviewer asks for **alternative approaches**, you can say:
- "We could also use **breadth-first search (BFS)** with a queue, storing both the node and the maximum value on its path."
- "An iterative DFS using a stack would work similarly to the recursive approach."

---

## 🔥 Final Takeaways
- **Track path information during tree traversal** by passing additional parameters.
- **Use DFS when problems involve path properties** from root to leaf.
- **Initialize with appropriate values** - here we start with `MIN_VALUE` to ensure the root is always counted.
- **Consider edge cases** like empty trees or trees with negative values.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/count-good-nodes-in-binary-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-fifth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.