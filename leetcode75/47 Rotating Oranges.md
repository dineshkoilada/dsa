# Rotting Oranges

## 📌 Problem Statement

**LeetCode Problem:** [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)  
**Difficulty:** Medium  

**Description:**  
You are given an `m x n` grid where each cell can have one of three values:

- `0` representing an empty cell,
- `1` representing a fresh orange, or
- `2` representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent (up, down, left, right) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return `-1`.

### **Example 1:**
**Input:** 
```
grid = [[2,1,1],[1,1,0],[0,1,1]]
```
**Output:** 
```
4
```

### **Example 2:**
**Input:** 
```
grid = [[2,1,1],[0,1,1],[1,0,1]]
```
**Output:** 
```
-1
```
**Explanation:** The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

### **Example 3:**
**Input:** 
```
grid = [[0,2]]
```
**Output:** 
```
0
```
**Explanation:** Since there are already no fresh oranges at minute 0, the answer is just 0.

### **Constraints:**
- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 10`
- `grid[i][j]` is `0`, `1`, or `2`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine a grid of oranges. Some are fresh, some are rotten, and some spots are empty. Every minute, a fresh orange will rot if it's right next to (up, down, left, or right) a rotten orange.

Your task is to figure out how many minutes it will take for all the oranges to become rotten. If there's a fresh orange that can never become rotten because it's not connected to any rotten orange through other oranges, then return -1.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we represent the spread of rotting through time?**
   - We can use a **Breadth-First Search (BFS)** where each level represents a minute of time.
2. **How do we know when all oranges are rotten?**
   - We need to **count fresh oranges initially** and keep track of how many become rotten.
3. **How do we handle impossible cases?**
   - If after our BFS, there are still fresh oranges left, it's impossible to rot them all.

👉 The key insight is to use BFS to simulate the rotting process minute by minute!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Count fresh oranges and find all initially rotten oranges
```java
int freshCount = 0;
Queue<int[]> queue = new LinkedList<>();

for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
            freshCount++;
        } else if (grid[i][j] == 2) {
            queue.add(new int[]{i, j});
        }
    }
}
```

### Step 2: If there are no fresh oranges, return 0
```java
if (freshCount == 0) return 0;
```

### Step 3: Perform BFS to simulate rotting process
```java
int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
int minutes = 0;

while (!queue.isEmpty() && freshCount > 0) {
    int size = queue.size();
    minutes++;
    
    for (int i = 0; i < size; i++) {
        int[] current = queue.poll();
        
        for (int[] dir : directions) {
            int newRow = current[0] + dir[0];
            int newCol = current[1] + dir[1];
            
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && 
                grid[newRow][newCol] == 1) {
                grid[newRow][newCol] = 2; // Mark as rotten
                freshCount--;
                queue.add(new int[]{newRow, newCol});
            }
        }
    }
}
```

### Step 4: Check if all oranges got rotten
```java
return freshCount == 0 ? minutes : -1;
```

---

## 🛠️ Writing the Complete Solution

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Count fresh oranges and find all initially rotten oranges
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        // If there are no fresh oranges, return 0
        if (freshCount == 0) return 0;
        
        // Directions for moving: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;
        
        // BFS to simulate rotting process
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            minutes++;
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                
                for (int[] dir : directions) {
                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];
                    
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && 
                        grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Mark as rotten
                        freshCount--;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
        
        // Check if all oranges got rotten
        return freshCount == 0 ? minutes : -1;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m × n)`, where m is the number of rows and n is the number of columns in the grid. We might need to visit each cell in the grid once.
- **Space Complexity:** `O(m × n)` for the queue, which in the worst case could contain all cells in the grid.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This problem is a classic **multi-source BFS** where each rotten orange serves as a starting point."
- "I use BFS to simulate the **minute-by-minute rotting process** where each level of BFS represents one minute of time."
- "By processing all rotten oranges at each minute simultaneously, I can determine the minimum time needed."

The algorithm follows these key steps:
1. **Initial scan** to count fresh oranges and identify initially rotten ones.
2. **BFS traversal** to simulate the rotting process, with each level representing one minute.
3. **Track fresh orange count** to know when all oranges have rotted.
4. **Return the result** based on whether all oranges could rot or not.

If the interviewer asks for **optimizations**, you can say:
- "We could use an in-place approach where we update the grid directly, saving space."
- "For a large sparse grid with few oranges, we could use a more compact representation of the grid."

---

## 🔥 Final Takeaways
- **Multi-source BFS** is perfect for simulating processes that spread from multiple origins simultaneously.
- **Level-by-level BFS processing** helps track time or distance in grid-based problems.
- **Counting elements** before and after processing can help determine if a goal is achievable.
- **Direction arrays** make exploring adjacent cells clean and readable.
- **Early termination** when no fresh oranges remain can save computation time.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/rotting-oranges/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-eighth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.