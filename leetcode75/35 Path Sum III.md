# Path Sum III

## 📌 Problem Statement

**LeetCode Problem:** [437. Path Sum III](https://leetcode.com/problems/path-sum-iii/)  
**Difficulty:** Medium  

**Description:**  
Given the `root` of a binary tree and an integer `targetSum`, return the number of paths where the sum of the values along the path equals `targetSum`.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

### **Example 1:**
**Input:** 
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
```
**Output:** 
```
3
```
**Explanation:**
The paths that sum to 8 are shown.

### **Example 2:**
**Input:** 
```
root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
```
**Output:** 
```
3
```

### **Constraints:**
- The number of nodes in the tree is in the range `[0, 1000]`.
- `-10^9 <= Node.val <= 10^9`
- `-1000 <= targetSum <= 1000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a tree, and each branch has a number on it. You want to find all the paths (going from a parent branch to child branches) where adding up the numbers gives you a specific sum.

The important things to remember are:
- A path can start from any branch (not just the top branch).
- A path can end at any branch (not just the bottom branches).
- A path must go downward (from parent to child).

For example, if we have a tree and want to find paths that sum to 8, there might be some paths like:
- [5, 3], where 5 + 3 = 8
- [5, 2, 1], where 5 + 2 + 1 = 8
- [-3, 11], where -3 + 11 = 8

Our goal is to count how many such paths exist in the tree.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Can a path start from any node in the tree?**
   - Yes, a path can start from any node.
2. **Can we have negative numbers in the tree?**
   - Yes, node values can be positive, negative, or zero.
3. **Can we solve this by checking all possible paths?**
   - Yes, but there's a more efficient approach using a technique called "prefix sum."

👉 Let's explore two approaches: a straightforward DFS and a more optimized prefix sum approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Approach 1: Double DFS (Straightforward)

#### Step 1: Explore all possible starting nodes
```java
private int pathSum(TreeNode root, int targetSum) {
    if (root == null) return 0;
    
    // Count paths starting from current node
    int pathsFromRoot = countPaths(root, targetSum);
    
    // Count paths in left and right subtrees
    int pathsFromLeft = pathSum(root.left, targetSum);
    int pathsFromRight = pathSum(root.right, targetSum);
    
    return pathsFromRoot + pathsFromLeft + pathsFromRight;
}
```

#### Step 2: Count paths starting from a specific node
```java
private int countPaths(TreeNode node, long remaining) {
    if (node == null) return 0;
    
    // Does this node complete a path?
    int count = (node.val == remaining) ? 1 : 0;
    
    // Continue checking for paths
    count += countPaths(node.left, remaining - node.val);
    count += countPaths(node.right, remaining - node.val);
    
    return count;
}
```

### Approach 2: Prefix Sum (Optimized)

#### Step 1: Use a HashMap to track prefix sums
```java
private int pathSumPrefixSum(TreeNode root, int targetSum) {
    Map<Long, Integer> prefixSumCount = new HashMap<>();
    prefixSumCount.put(0L, 1);  // Empty prefix sum
    return prefixSumDFS(root, 0, targetSum, prefixSumCount);
}
```

#### Step 2: Perform DFS with prefix sum tracking
```java
private int prefixSumDFS(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
    if (node == null) return 0;
    
    // Update current sum with this node
    currentSum += node.val;
    
    // Check if there's a prefix sum that gives us the target
    int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);
    
    // Add current sum to the prefix sum map
    prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
    
    // Check left and right subtrees
    count += prefixSumDFS(node.left, currentSum, targetSum, prefixSumCount);
    count += prefixSumDFS(node.right, currentSum, targetSum, prefixSumCount);
    
    // Restore the map when going back up (backtracking)
    prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
    
    return count;
}
```

---

## 🛠️ Writing the Brute Force Solution

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
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        
        // Count paths starting from current node
        int pathsFromRoot = countPaths(root, targetSum);
        
        // Count paths in left and right subtrees
        int pathsFromLeft = pathSum(root.left, targetSum);
        int pathsFromRight = pathSum(root.right, targetSum);
        
        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }
    
    private int countPaths(TreeNode node, long remaining) {
        if (node == null) return 0;
        
        // Does this node complete a path?
        int count = (node.val == remaining) ? 1 : 0;
        
        // Continue checking for paths
        count += countPaths(node.left, remaining - node.val);
        count += countPaths(node.right, remaining - node.val);
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N²)` in the worst case, where N is the number of nodes. For each node, we might traverse all its descendants.
- **Space Complexity:** `O(H)` for the recursion stack, where H is the height of the tree.

---

## 🚀 Optimized Solution using Prefix Sum

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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1);  // Empty prefix sum
        return prefixSumDFS(root, 0, targetSum, prefixSumCount);
    }
    
    private int prefixSumDFS(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) return 0;
        
        // Update current sum with this node
        currentSum += node.val;
        
        // Check if there's a prefix sum that gives us the target
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);
        
        // Add current sum to the prefix sum map
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        
        // Check left and right subtrees
        count += prefixSumDFS(node.left, currentSum, targetSum, prefixSumCount);
        count += prefixSumDFS(node.right, currentSum, targetSum, prefixSumCount);
        
        // Restore the map when going back up (backtracking)
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where N is the number of nodes. We visit each node exactly once.
- **Space Complexity:** `O(H + N)`, where H is the height of the tree (recursion stack) and N is the number of nodes (HashMap size in the worst case).

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I implemented two solutions: a straightforward double-DFS approach and an optimized prefix sum approach."
- "The double-DFS solution checks paths starting from every node in the tree, but it's inefficient with `O(N²)` time complexity."
- "The optimized prefix sum approach uses a HashMap to track the running sum along each path, giving us `O(N)` time complexity."
- "The key insight is that if we have two prefix sums `prefixSum1` and `prefixSum2` where their difference is `targetSum`, then the path between them sums to `targetSum`."

If the interviewer asks for more details on the prefix sum approach:
- "As we traverse the tree, we maintain a running sum (`currentSum`) from the root to the current node."
- "If `currentSum - targetSum` exists in our prefix sum map, it means there's a subpath ending at the current node that sums to `targetSum`."
- "We use backtracking to ensure we only count paths that go downwards."

---

## 🔥 Final Takeaways
- **Prefix sum is a powerful technique** for subarray/subpath sum problems.
- **Use HashMaps to store prefix sums** for efficient lookups.
- **Remember to handle backtracking** when using prefix sums in tree traversal.
- **Be careful with integer overflow** - use `long` for sums if necessary.
- **Consider edge cases** like negative numbers, zero values, and empty trees.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/path-sum-iii/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirty-sixth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.