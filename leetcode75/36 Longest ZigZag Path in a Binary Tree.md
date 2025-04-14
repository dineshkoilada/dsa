# Longest ZigZag Path in a Binary Tree

## 📌 Problem Statement

**LeetCode Problem:** [1372. Longest ZigZag Path in a Binary Tree](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/)  
**Difficulty:** Medium  

**Description:**  
You are given the `root` of a binary tree.

A ZigZag path for a binary tree is defined as follows:
- Choose any node in the binary tree and a direction (right or left).
- If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
- Change the direction from right to left or from left to right.
- Repeat the second and third steps until you can't move in the tree.

The zigzag length is defined as the number of nodes visited - 1 (excluding the starting node).

Return the longest ZigZag path contained in the tree.

### **Example 1:**
**Input:** 
```
root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
```
**Output:** 
```
3
```
**Explanation:** Longest ZigZag path: 1 -> 1 -> 1 -> 1.

### **Example 2:**
**Input:** 
```
root = [1,1,1,null,1,null,null,1,1,null,1]
```
**Output:** 
```
4
```
**Explanation:** Longest ZigZag path: 1 -> 1 -> 1 -> 1 -> 1.

### **Example 3:**
**Input:** 
```
root = [1]
```
**Output:** 
```
0
```

### **Constraints:**
- The number of nodes in the tree is in the range `[1, 5 * 10^4]`.
- `1 <= Node.val <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're playing a game where you climb a tree by zigzagging. Starting from any branch, you have to follow these rules:
- Choose a direction: left or right.
- Move in that direction to the next branch.
- Switch direction: if you went left, now you must go right; if you went right, now you must go left.
- Keep going until you can't move anymore because there's no branch to go to.

The zigzag path length is counted as the number of moves you make (or branches you visit minus 1).

For example, if you visit 4 branches in a zigzag pattern (left, right, left), your path length would be 3.

Your goal is to find the longest possible zigzag path in the tree.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Can we start the zigzag path from any node?**
   - Yes, we can start from any node in the tree.
2. **Do we need to keep track of the direction we're coming from?**
   - Yes, we need to know if we're coming from left or right to ensure we zigzag properly.
3. **Can we reuse the same traversal logic for all nodes?**
   - Yes, with recursion we can apply the same logic to every node.

👉 A key insight is that at each node, we have two possible directions to explore, and we need to track the longest zigzag path seen so far!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define a recursive function to explore paths
```java
private void dfs(TreeNode node, int left, int right, int[] maxLength) {
    if (node == null) return;
    
    // Update the maximum zigzag length
    maxLength[0] = Math.max(maxLength[0], Math.max(left, right));
    
    // Explore left child
    dfs(node.left, 0, left + 1, maxLength);
    
    // Explore right child
    dfs(node.right, right + 1, 0, maxLength);
}
```

### Step 2: Initialize and call the function
```java
public int longestZigZag(TreeNode root) {
    int[] maxLength = new int[1];
    dfs(root, 0, 0, maxLength);
    return maxLength[0];
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
    public int longestZigZag(TreeNode root) {
        int[] maxLength = new int[1];
        dfs(root, 0, 0, maxLength);
        return maxLength[0];
    }
    
    private void dfs(TreeNode node, int left, int right, int[] maxLength) {
        if (node == null) return;
        
        // Update the maximum zigzag length
        maxLength[0] = Math.max(maxLength[0], Math.max(left, right));
        
        // Explore left child (coming from right)
        dfs(node.left, 0, left + 1, maxLength);
        
        // Explore right child (coming from left)
        dfs(node.right, right + 1, 0, maxLength);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. We visit each node exactly once.
- **Space Complexity:** `O(H)`, where H is the height of the tree, for the recursion stack.

---

## 🚀 Alternative Solution using Pair or Object

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
    private int maxLength = 0;
    
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        
        // Start DFS from root with both directions
        dfs(root.left, 1, true);  // Going left
        dfs(root.right, 1, false); // Going right
        
        return maxLength;
    }
    
    private void dfs(TreeNode node, int length, boolean isLeft) {
        if (node == null) return;
        
        // Update maximum length
        maxLength = Math.max(maxLength, length);
        
        // Continue zigzag path
        if (isLeft) {
            // If we came from left, we can only go right to continue zigzag
            dfs(node.right, length + 1, false);
            // Or we can start a new zigzag path going left
            dfs(node.left, 1, true);
        } else {
            // If we came from right, we can only go left to continue zigzag
            dfs(node.left, length + 1, true);
            // Or we can start a new zigzag path going right
            dfs(node.right, 1, false);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree.
- **Space Complexity:** `O(H)`, where H is the height of the tree, for the recursion stack.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I used **depth-first search (DFS)** to explore all possible zigzag paths in the tree."
- "For each node, I track two values: the length of the zigzag path if we go left, and the length if we go right."
- "When moving to a child node, I update these lengths based on the direction change."
- "At each step, I update the global maximum zigzag path length."
- "This approach ensures we explore all possible zigzag paths starting from any node."

If the interviewer asks for more details:
- "The key insight is that for a zigzag path, we need to alternate directions."
- "If we move left from a node, the next move must be right, and vice versa."
- "By tracking separate length counters for left and right moves, we can ensure we're following zigzag patterns correctly."

---

## 🔥 Final Takeaways
- **DFS is a powerful technique for exploring paths** in trees.
- **Keep track of the direction in recursive functions** when path direction matters.
- **Use parameters efficiently** to pass state down the recursion tree.
- **Consider all possible starting points** when the problem allows paths to start anywhere.
- **Watch out for edge cases** like single nodes or unbalanced trees.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-seventh problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.