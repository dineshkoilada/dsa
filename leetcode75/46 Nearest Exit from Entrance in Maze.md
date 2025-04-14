# Nearest Exit from Entrance in Maze

## 📌 Problem Statement

**LeetCode Problem:** [1926. Nearest Exit from Entrance in Maze](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/)  
**Difficulty:** Medium  

**Description:**  
You are given an `m x n` matrix `maze` (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the `entrance` of the maze, where `entrance = [entrancerow, entrancecol]` denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The `entrance` does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

### **Example 1:**
**Input:** 
```
maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
```
**Output:** 
```
1
```
**Explanation:**
The entrance is at (1,2). There are 3 exits in this maze: (0,2), (2,3), and (1,0).
The nearest exit is at (0,2) which requires 1 step.

### **Example 2:**
**Input:** 
```
maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
```
**Output:** 
```
2
```
**Explanation:**
The entrance is at (1,0). There is 1 exit in this maze: (1,2).
The nearest exit is at (1,2) which requires 2 steps.

### **Example 3:**
**Input:** 
```
maze = [[".","+"]], entrance = [0,0]
```
**Output:** 
```
-1
```
**Explanation:**
There are no exits in this maze.

### **Constraints:**
- `maze.length == m`
- `maze[i].length == n`
- `1 <= m, n <= 100`
- `maze[i][j]` is either '.' or '+'.
- `entrance.length == 2`
- `0 <= entrancerow < m`
- `0 <= entrancecol < n`
- `entrance` will always be an empty cell.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're in a maze with walls and open spaces. You start at a specific point called the entrance. Your goal is to find the quickest way out of the maze. An exit is any open space at the edge of the maze (but not the entrance itself).

In each step, you can move one space up, down, left, or right. You can't walk through walls or step outside the maze boundaries. You need to count how many steps it takes to reach the nearest exit from your starting point. If there's no way out, we say "-1".

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we find the shortest path in a maze?**
   - We can use **Breadth-First Search (BFS)** which always finds the shortest path in an unweighted graph.
2. **How do we identify exits?**
   - An exit is an empty cell (denoted by '.') that's at the border of the maze (first/last row or column).
3. **What if there are multiple exits?**
   - BFS will naturally find the closest one first since it explores all paths level by level.

👉 The key insight is to use BFS to explore the maze layer by layer until we find an exit!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up BFS queue and visited set
```java
Queue<int[]> queue = new LinkedList<>();
boolean[][] visited = new boolean[m][n];
queue.offer(new int[]{entrance[0], entrance[1], 0}); // {row, col, steps}
visited[entrance[0]][entrance[1]] = true;
```

### Step 2: Define directions for movement
```java
int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
```

### Step 3: Perform BFS to find shortest path
```java
while (!queue.isEmpty()) {
    int[] current = queue.poll();
    int row = current[0], col = current[1], steps = current[2];
    
    // Check if this is an exit
    if ((row == 0 || row == m - 1 || col == 0 || col == n - 1) && 
        (row != entrance[0] || col != entrance[1])) {
        return steps;
    }
    
    // Explore all 4 directions
    for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        
        // Check if valid move
        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && 
            maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
            queue.offer(new int[]{newRow, newCol, steps + 1});
            visited[newRow][newCol] = true;
        }
    }
}
```

### Step 4: Return -1 if no exit found
```java
return -1;
```

---

## 🛠️ Writing the Complete Solution

```java
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        
        // Add entrance to the queue and mark it as visited
        queue.offer(new int[]{entrance[0], entrance[1], 0}); // {row, col, steps}
        visited[entrance[0]][entrance[1]] = true;
        
        // Directions for moving: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1], steps = current[2];
            
            // Check if this is an exit
            if ((row == 0 || row == m - 1 || col == 0 || col == n - 1) && 
                (row != entrance[0] || col != entrance[1])) {
                return steps;
            }
            
            // Explore all 4 directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check if valid move
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && 
                    maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol, steps + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }
        
        return -1; // No exit found
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m × n)`, where m is the number of rows and n is the number of columns in the maze. In the worst case, we might need to visit every cell in the maze.
- **Space Complexity:** `O(m × n)` for the visited array and queue storage.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This is a classic **shortest path problem** in an unweighted graph, perfect for **Breadth-First Search**."
- "I represented the maze as a graph where each empty cell is a node, and adjacent cells are connected."
- "BFS explores the maze layer by layer, ensuring we find the shortest path to an exit."

The algorithm follows these key steps:
1. **Initialize a queue** with the entrance cell and mark it as visited.
2. **Perform BFS** by exploring all four directions from each cell.
3. **Check exit conditions** for each cell:
   - It must be at the border of the maze.
   - It cannot be the entrance cell itself.
4. **Return the step count** when we find the first exit, or -1 if no exit exists.

If the interviewer asks for **optimizations**, you can say:
- "We could stop adding cells to the queue if they're not exits and all their neighbors have been visited."
- "For very large mazes, we could use a more memory-efficient representation of visited cells."

---

## 🔥 Final Takeaways
- **BFS guarantees the shortest path** in unweighted graphs like this maze problem.
- **Careful handling of edge cases** is important, especially distinguishing the entrance from a valid exit.
- **Visited array prevents cycles** and ensures we don't re-explore cells unnecessarily.
- **Queue-based approach** provides an elegant solution for level-by-level exploration.
- **Direction arrays** simplify code for exploring adjacent cells in grid-based problems.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-seventh problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.