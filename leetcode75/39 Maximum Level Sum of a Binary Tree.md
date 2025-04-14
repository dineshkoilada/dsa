# Maximum Level Sum of a Binary Tree

## 📌 Problem Statement

**LeetCode Problem:** [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/)  
**Difficulty:** Medium  

**Description:**  
Given the `root` of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the **smallest** level `x` such that the sum of all the values of nodes at level `x` is **maximal**.

### **Example 1:**
**Input:** 
```
root = [1,7,0,7,-8,null,null]
```
**Output:** 
```
2
```
**Explanation:** 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + (-8) = -1.
So we return the level with the maximum sum which is level 2.

### **Example 2:**
**Input:** 
```
root = [989,null,10250,98693,-89388,null,null,null,-32127]
```
**Output:** 
```
2
```

### **Constraints:**
- The number of nodes in the tree is in the range `[1, 10^4]`.
- `-10^5 <= Node.val <= 10^5`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a family tree, where the level 1 is yourself, level 2 is your children, level 3 is your grandchildren, and so on. Each person has a certain amount of money (which can be positive or negative).

Your task is to find the level where the total amount of money is the largest. If two levels have the same total (which is the maximum), you should choose the smaller level number.

For example:
- Level 1: You have $1
- Level 2: Your two children have $7 and $0, so total is $7
- Level 3: Your grandchildren have $7 and -$8, so total is -$1

The level with the maximum sum is level 2 (with $7), so the answer is 2.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we calculate the sum of values at each level?**
   - We can use level-order traversal (BFS) to visit nodes level by level.
2. **How do we keep track of the level with the maximum sum?**
   - We can maintain variables to track the maximum sum and its corresponding level.
3. **What if multiple levels have the same maximum sum?**
   - We should return the smallest level number (as specified in the problem).

👉 Level-order traversal is perfect for this problem since we need to process nodes level by level!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up BFS traversal with a queue
```java
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);
```

### Step 2: Process each level and calculate its sum
```java
int maxSum = Integer.MIN_VALUE;
int maxLevel = 1;
int currentLevel = 1;

while (!queue.isEmpty()) {
    int levelSize = queue.size();
    int levelSum = 0;
    
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        levelSum += node.val;
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
    
    if (levelSum > maxSum) {
        maxSum = levelSum;
        maxLevel = currentLevel;
    }
    
    currentLevel++;
}
```

### Step 3: Return the level with the maximum sum
```java
return maxLevel;
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
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currentLevel = 1;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelSum = 0;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxLevel = currentLevel;
            }
            
            currentLevel++;
        }
        
        return maxLevel;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. We visit each node exactly once.
- **Space Complexity:** `O(M)`, where M is the maximum number of nodes at any level. In the worst case, this could be `O(N/2)` for a complete binary tree, which simplifies to `O(N)`.

---

## 🚀 Alternative Solution using DFS

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
    public int maxLevelSum(TreeNode root) {
        // Map to store sum of each level
        Map<Integer, Integer> levelSum = new HashMap<>();
        
        // DFS to calculate sum of each level
        dfs(root, 1, levelSum);
        
        // Find level with maximum sum
        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        
        for (int level : levelSum.keySet()) {
            if (levelSum.get(level) > maxSum) {
                maxSum = levelSum.get(level);
                maxLevel = level;
            }
        }
        
        return maxLevel;
    }
    
    private void dfs(TreeNode node, int level, Map<Integer, Integer> levelSum) {
        if (node == null) return;
        
        // Add current node's value to its level sum
        levelSum.put(level, levelSum.getOrDefault(level, 0) + node.val);
        
        // Process children at next level
        dfs(node.left, level + 1, levelSum);
        dfs(node.right, level + 1, levelSum);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes in the tree. We visit each node exactly once.
- **Space Complexity:** `O(H + L)`, where H is the height of the tree (for the recursion stack) and L is the number of levels. In the worst case, this could be `O(N)`.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I solved this problem using **level-order traversal (BFS)**, which is perfect for processing tree nodes level by level."
- "For each level, I calculate the sum of all node values and compare it with the maximum sum seen so far."
- "If the current level's sum is greater than the maximum, I update both the maximum sum and the level number."
- "The time complexity is `O(N)` since we visit each node once, and the space complexity is `O(M)` where M is the maximum width of the tree."

If the interviewer asks about alternative approaches:
- "We could also use DFS with a HashMap to store the sum for each level."
- "During the DFS, we'd keep track of the current level and add each node's value to its level's sum in the HashMap."
- "After traversing the entire tree, we'd find the level with the maximum sum from the HashMap."
- "Both approaches have `O(N)` time complexity, but BFS is more intuitive for this problem since it naturally processes nodes level by level."

---

## 🔥 Final Takeaways
- **Level-order traversal (BFS)** is excellent for problems that require processing nodes level by level.
- **Track maximum values and their indices** when looking for optimal solutions.
- **Consider edge cases** like trees with negative values or trees with only one node.
- **Remember that level numbering in this problem starts from 1**, not 0.
- **If multiple levels have the same sum, choose the smallest level number** (a detail easily missed).

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fortieth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.