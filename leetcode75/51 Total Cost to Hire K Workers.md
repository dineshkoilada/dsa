# Total Cost to Hire K Workers

## 📌 Problem Statement

**LeetCode Problem:** [2462. Total Cost to Hire K Workers](https://leetcode.com/problems/total-cost-to-hire-k-workers/)  
**Difficulty:** Medium  

**Description:**
You are given a 0-indexed integer array `costs` where `costs[i]` is the cost of hiring the `i`th worker.

You are also given two integers `k` and `candidates`. We want to hire exactly `k` workers according to the following rules:

- You will run `k` sessions and hire exactly one worker in each session.
- In each hiring session, choose the worker with the lowest cost from either the first `candidates` workers or the last `candidates` workers. Break the tie by the smallest index.
  - For example, if `costs = [3,2,7,7,1,2]` and `candidates = 2`, then in the first hiring session, we will choose the 4th worker (index 3) because they have the lowest cost `[3,2,7,7,1,2]`.
  - In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index `[3,2,7,7,2]`. Please note that the indexing may be changed in the process.
- If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
- A worker can only be chosen once.

Return the total cost to hire exactly `k` workers.

### **Example 1:**
**Input:** 
```
costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
```
**Output:** 
```
11
```
**Explanation:** 
We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
- In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
- In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last candidates of the first two hiring rounds.

### **Example 2:**
**Input:** 
```
costs = [1,2,4,1], k = 3, candidates = 3
```
**Output:** 
```
4
```
**Explanation:** 
We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last candidates.
- In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
- In the third hiring round there are less than candidates = 3 workers remaining, so we choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.

### **Constraints:**
- `1 <= costs.length <= 10^5`
- `1 <= costs[i] <= 10^5`
- `1 <= k, candidates <= costs.length`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're the captain of a team, and you need to choose `k` players for your team from a line of kids. Each kid has a number on their shirt that shows how much it costs to add them to your team.

You have a special rule: in each round, you can only look at the first `candidates` kids and the last `candidates` kids in line. You must pick the kid with the lowest cost. If two kids have the same cost, you pick the one who's closer to the beginning of the line.

Once you pick a kid, they leave the line, and the remaining kids stay in order. You keep doing this until you've picked exactly `k` kids.

Your goal is to find the total cost of picking these `k` kids.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What if there are fewer than `candidates` kids left in the line?**
   - Then we consider all remaining kids in that section.
2. **What if the same kid appears in both the first and last `candidates` sections?**
   - We only count them once when determining the minimum cost.
3. **What's the best strategy to efficiently find the minimum cost kid in each round?**
   - Using minimum heaps will be helpful.

👉 These considerations help us plan our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Set up two priority queues (min heaps) for the first and last candidates
```java
PriorityQueue<int[]> headWorkers = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
PriorityQueue<int[]> tailWorkers = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
```

### Step 2: Initialize the heaps with the first and last candidates
```java
for (int i = 0; i < Math.min(candidates, costs.length); i++) {
    headWorkers.offer(new int[]{costs[i], i});
}
for (int i = Math.max(candidates, costs.length - candidates); i < costs.length; i++) {
    if (i >= candidates) { // Avoid duplicates
        tailWorkers.offer(new int[]{costs[i], i});
    }
}
```

### Step 3: Hire k workers, picking from either head or tail segment
```java
int totalCost = 0;
int nextHead = candidates;
int nextTail = costs.length - candidates - 1;

for (int i = 0; i < k; i++) {
    // Logic to select the worker with the lowest cost
    // Update total cost
    // Replenish the heaps if needed
}
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class TotalCostToHireKWorkers {
    public long totalCost(int[] costs, int k, int candidates) {
        long totalCost = 0;
        
        for (int hired = 0; hired < k; hired++) {
            int minCost = Integer.MAX_VALUE;
            int minIndex = -1;
            
            // Check the first candidates workers
            for (int i = 0; i < Math.min(candidates, costs.length); i++) {
                if (costs[i] != Integer.MAX_VALUE && costs[i] < minCost) {
                    minCost = costs[i];
                    minIndex = i;
                }
            }
            
            // Check the last candidates workers
            for (int i = Math.max(0, costs.length - candidates); i < costs.length; i++) {
                if (costs[i] != Integer.MAX_VALUE && (costs[i] < minCost || 
                    (costs[i] == minCost && i < minIndex))) {
                    minCost = costs[i];
                    minIndex = i;
                }
            }
            
            // Add the cost and mark the worker as hired
            totalCost += minCost;
            costs[minIndex] = Integer.MAX_VALUE;
        }
        
        return totalCost;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(k * candidates)`, as we scan the first and last candidates workers in each of the `k` hiring sessions.
- **Space Complexity:** `O(1)`, as we only use a few variables and modify the input array.

---

## 🚀 Optimized Solution (Using Two Heaps)

```java
import java.util.*;

public class TotalCostToHireKWorkersOptimized {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        
        // Create two min heaps for the first and last candidates
        PriorityQueue<int[]> headWorkers = new PriorityQueue<>((a, b) -> 
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        PriorityQueue<int[]> tailWorkers = new PriorityQueue<>((a, b) -> 
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        // Add the first and last candidates to the respective heaps
        for (int i = 0; i < Math.min(candidates, n); i++) {
            headWorkers.offer(new int[]{costs[i], i});
        }
        
        for (int i = n - 1; i >= Math.max(n - candidates, candidates); i--) {
            tailWorkers.offer(new int[]{costs[i], i});
        }
        
        long totalCost = 0;
        int nextHead = candidates;
        int nextTail = n - candidates - 1;
        
        for (int hired = 0; hired < k; hired++) {
            // If one heap is empty, hire from the other heap
            if (headWorkers.isEmpty()) {
                totalCost += tailWorkers.poll()[0];
            } else if (tailWorkers.isEmpty()) {
                totalCost += headWorkers.poll()[0];
            }
            // Otherwise, hire the worker with the lowest cost 
            else {
                int[] headTop = headWorkers.peek();
                int[] tailTop = tailWorkers.peek();
                
                if (headTop[0] <= tailTop[0]) {
                    totalCost += headWorkers.poll()[0];
                    
                    // Replenish the head heap if possible
                    if (nextHead <= nextTail) {
                        headWorkers.offer(new int[]{costs[nextHead], nextHead});
                        nextHead++;
                    }
                } else {
                    totalCost += tailWorkers.poll()[0];
                    
                    // Replenish the tail heap if possible
                    if (nextHead <= nextTail) {
                        tailWorkers.offer(new int[]{costs[nextTail], nextTail});
                        nextTail--;
                    }
                }
            }
        }
        
        return totalCost;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(k * log(candidates))` as we perform `k` heap operations, each taking `O(log(candidates))` time.
- **Space Complexity:** `O(candidates)` for storing the two priority queues.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I recognized that we need to efficiently find the minimum cost worker from two sections of the array in each hiring session."
- "To optimize this, I used two priority queues (min heaps): one for the head section and one for the tail section."
- "Initially, I populate the heaps with the first and last `candidates` workers."
- "In each hiring session, I pick the worker with the lower cost from the two heaps."
- "After removing a worker, I replenish the respective heap with the next available worker from that section."
- "This way, I maintain the invariant that both heaps always contain the available candidates from their respective sections."
- "Using heaps gives us logarithmic time complexity for finding the minimum cost worker, which is more efficient than scanning the entire section each time."

---

## 🔥 Final Takeaways
- **Using appropriate data structures like priority queues can significantly improve efficiency.**
- **Breaking the problem into two separate heaps allows us to efficiently handle the "first `candidates`" and "last `candidates`" sections.**
- **Pay attention to edge cases like when sections overlap or when there are fewer than `candidates` workers remaining.**
- **Remember to replenish the heaps as workers get hired to maintain the candidates window.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/total-cost-to-hire-k-workers/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is problem **fifty-second problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.