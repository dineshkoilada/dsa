# Binary Tree Right Side View

## 📌 Problem Statement

**LeetCode Problem:** [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)  
**Difficulty:** Medium  

**Description:**  
Given the `root` of a binary tree, imagine yourself standing on the **right side** of it, return the values of the nodes you can see ordered from top to bottom.

### **Example 1:**
**Input:** 
```
root = [1,2,3,null,5,null,4]
```
**Output:** 
```
[1,3,4]
```

### **Example 2:**
**Input:** 
```
root = [1,null,3]
```
**Output:** 
```
[1,3]
```

### **Example 3:**
**Input:** 
```
root = []
```
**Output:** 
```
[]
```

### **Constraints:**
- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're standing on the right side of a tree looking at it. From this position, you can only see the rightmost node at each level of the tree.

For example, if we have a tree like this:
```
    1
   / \
  2   3
   \   \
    5   4
```

Standing on the right, you would see:
- Level 1: Node with value 1
- Level 2: Node with value 3
- Level 3: Node with value 4

So the right side view would be [1, 3, 4].

Our job is to find all these visible nodes from the right side and return their values in order from top to bottom.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we identify which node is visible from the right?**
   - The rightmost node at each level will be visible.
2. **What traversal should we use?**
   - We can use either BFS (level by level) or DFS (right child first).
3. **How do we handle empty trees?**
   - Return an empty list.

👉 We have two main approaches: level order traversal (BFS) or a right-first DFS!

---

## 🏗️ Breaking the Problem into Steps with Code

### Approach 1: Level Order Traversal (BFS)

#### Step 1: Set up queue for BFS and result list
```java
List<Integer> result = new ArrayList<>();
Queue<TreeNode> queue = new LinkedList<>();
if (root != null) queue.offer(root);
```

#### Step 2: Process nodes level by level
```java
while (!queue.isEmpty()) {
    int levelSize = queue.size();
    
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        
        // If this is the last node in the level, add to result
        if (i == levelSize - 1) {
            result.add(node.val);
        }
        
        // Add children to queue
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

### Approach 2: Depth-First Search (Right to Left)

#### Step 1: Define a recursive DFS function
```java
private void dfs(TreeNode node, int level, List<Integer> result) {
    if (node == null) return;
    
    // If this is the first node we've seen at this level
    if (level == result.size()) {
        result.add(node.val);
    }
    
    // Visit right first, then left
    dfs(node.right, level + 1, result);
    dfs(node.left, level + 1, result);
}
```

#### Step 2: Call DFS from the root
```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
}
```

---

## 🛠️ BFS Solution

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // If this is the last node in the level, add to result
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                // Add children to queue
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. Each node is processed exactly once.
- **Space Complexity:** `O(D)`, where D is the maximum width of the tree. In the worst case, this could be `O(N/2)` for a complete binary tree, which simplifies to `O(N)`.

---

## 🚀 DFS Solution

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> result) {
        if (node == null) return;
        
        // If this is the first node we've encountered at this level
        if (level == result.size()) {
            result.add(node.val);
        }
        
        // Visit right first, then left
        dfs(node.right, level + 1, result);
        dfs(node.left, level + 1, result);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. Each node is processed exactly once.
- **Space Complexity:** `O(H)`, where H is the height of the tree, for the recursion stack. In the worst case of a skewed tree, this could be `O(N)`.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I implemented two solutions: a **BFS level-order traversal** and a **DFS right-first traversal**."

For the **BFS** approach:
- "I use a queue to traverse the tree level by level."
- "For each level, I only add the rightmost node's value to the result."
- "This guarantees we capture exactly what we would see from the right side."

For the **DFS** approach:
- "I use a pre-order DFS but visit the right child first, then the left."
- "I keep track of the level of each node during traversal."
- "The first node I encounter at each level will be the rightmost node visible from the right side."
- "This approach is more memory-efficient for tall trees compared to BFS."

If the interviewer asks which is better:
- "BFS is more intuitive for this problem and might be faster for wide, shallow trees."
- "DFS uses less memory for tall, thin trees and might be faster for those cases."
- "Both are valid `O(N)` time complexity solutions."

---

## 🔥 Final Takeaways
- **Level-order traversal (BFS)** is a natural fit for problems that involve levels of a tree.
- **Modified DFS traversal orders** can often simplify problems (like right-first DFS here).
- **Understanding the problem visually** helps formulate the correct approach.
- **Always handle edge cases** like empty trees.
- **Consider both recursive and iterative approaches** for tree problems.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/binary-tree-right-side-view/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-ninth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.