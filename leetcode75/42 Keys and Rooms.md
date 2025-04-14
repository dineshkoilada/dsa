# Keys and Rooms

## 📌 Problem Statement

**LeetCode Problem:** [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms/)  
**Difficulty:** Medium  

**Description:**  
There are `n` rooms labeled from `0` to `n-1` and all the rooms are locked except for room `0`. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array `rooms` where `rooms[i]` is the set of keys that you can obtain if you visited room `i`, return `true` if you can visit all the rooms, or `false` otherwise.

### **Example 1:**
**Input:** 
```
rooms = [[1],[2],[3],[]]
```
**Output:** 
```
true
```
**Explanation:**  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.
Since we were able to visit every room, we return true.

### **Example 2:**
**Input:** 
```
rooms = [[1,3],[3,0,1],[2],[0]]
```
**Output:** 
```
false
```
**Explanation:**  
We can't enter room number 2 since we don't have any key for it.

### **Constraints:**
- `n == rooms.length`
- `2 <= n <= 1000`
- `0 <= rooms[i].length <= 1000`
- `1 <= sum(rooms[i].length) <= 3000`
- `0 <= rooms[i][j] < n`
- All the values of `rooms[i]` are unique.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're in a house with many rooms, but all the doors are locked except for the first room (room 0). Inside each room, you might find keys that can unlock other rooms.

For example, if you enter room 0 and find a key labeled "1", you can now open room 1. When you enter room 1, you might find more keys that let you open even more rooms.

Your goal is to figure out if you can visit ALL the rooms in the house. If there's even one room you can't get to because you don't have its key, then the answer is "no" (false). If you can reach all rooms, the answer is "yes" (true).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we track which rooms we've visited?**
   - We can use a set or array to mark rooms as visited.
2. **How do we model collecting keys?**
   - When we visit a room, we can add all its keys to our collection.
3. **How do we know if we've visited all rooms?**
   - We can count the total number of rooms we've visited and compare it to the total number of rooms.

👉 This is a graph traversal problem! Each room is a node, and the keys represent directed edges to other nodes.

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up a queue for BFS and a set to track visited rooms
```java
Queue<Integer> queue = new LinkedList<>();
boolean[] visited = new boolean[rooms.size()];
int count = 0;
```

### Step 2: Start from room 0
```java
queue.offer(0);
visited[0] = true;
count++;
```

### Step 3: Perform BFS to visit rooms and collect keys
```java
while (!queue.isEmpty()) {
    int currentRoom = queue.poll();
    
    // Explore all keys in the current room
    for (int key : rooms[currentRoom]) {
        if (!visited[key]) {
            // Use the key to visit a new room
            visited[key] = true;
            queue.offer(key);
            count++;
        }
    }
}
```

### Step 4: Check if we've visited all rooms
```java
return count == rooms.size();
```

---

## 🛠️ BFS Solution

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // Set up queue for BFS and track visited rooms
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[rooms.size()];
        int count = 0;
        
        // Start from room 0
        queue.offer(0);
        visited[0] = true;
        count++;
        
        // BFS to visit rooms and collect keys
        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            
            // Explore all keys in the current room
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    // Use the key to visit a new room
                    visited[key] = true;
                    queue.offer(key);
                    count++;
                }
            }
        }
        
        // Check if we've visited all rooms
        return count == rooms.size();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N + E)`, where N is the number of rooms (nodes) and E is the total number of keys (edges). We visit each room once and process each key once.
- **Space Complexity:** `O(N)` for the queue and visited array, where N is the number of rooms.

---

## 🚀 DFS Solution

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        
        // Start DFS from room 0
        dfs(rooms, 0, visited);
        
        // Check if all rooms are visited
        for (boolean v : visited) {
            if (!v) return false;
        }
        
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int room, boolean[] visited) {
        // Mark current room as visited
        visited[room] = true;
        
        // Visit all rooms we can unlock from this room
        for (int key : rooms.get(room)) {
            if (!visited[key]) {
                dfs(rooms, key, visited);
            }
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N + E)`, same as the BFS solution.
- **Space Complexity:** `O(N)` for the visited array and recursion stack, where N is the number of rooms.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I recognized this as a **graph traversal problem** where rooms are nodes and keys are directed edges between nodes."
- "We need to determine if all nodes are reachable from the starting node (room 0)."
- "I implemented both **BFS and DFS solutions**:"

For the **BFS** approach:
- "I use a queue to track rooms to visit and a boolean array to mark rooms as visited."
- "Starting from room 0, I collect keys (visit adjacent nodes) and continue the process."
- "Finally, I check if the number of visited rooms equals the total number of rooms."

For the **DFS** approach:
- "I recursively explore rooms starting from room 0, marking each visited room."
- "After the traversal, I check if all rooms are marked as visited."
- "Both approaches correctly determine if all rooms are reachable."

If the interviewer asks which approach is better:
- "Both BFS and DFS have the same time and space complexity for this problem: `O(N + E)` time and `O(N)` space."
- "BFS might be slightly more intuitive for this problem because it mimics the process of collecting keys and opening rooms level by level."
- "DFS is more concise in code and uses the call stack instead of an explicit queue."

---

## 🔥 Final Takeaways
- **Graph traversal problems** can appear in disguise - recognize that rooms and keys form a directed graph.
- **Both BFS and DFS** are effective for determining reachability in a graph.
- **Visiting and marking nodes** ensures we don't process the same node multiple times.
- **Counting visited nodes** is an effective way to check if all nodes were visited.
- **Remember to handle the edge case** where we start with access to room 0.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/keys-and-rooms/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-third problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.