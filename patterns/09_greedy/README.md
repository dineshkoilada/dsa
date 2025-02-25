# Greedy Algorithm Pattern

## 🎯 Introduction

Imagine you’re collecting coins to reach a specific amount. You always pick the largest coin available first to minimize the total number of coins needed. This is the essence of the **Greedy Algorithm**—making the locally optimal choice at each step in the hope of finding a global optimum.

The Greedy Algorithm pattern is particularly useful for:
- Optimization problems (minimizing or maximizing values)
- Interval scheduling problems
- Resource allocation and distribution
- Huffman coding and compression algorithms

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you trying to maximize or minimize something?
   - Is making a local optimal choice at each step valid for achieving the global optimum?

2. **Ask Clarifying Questions:**
   - Are there any constraints on selections?
   - Will a local decision always lead to the best overall solution?

3. **Identify Clues for Using Greedy Algorithms:**
   - The problem involves optimization.
   - You can make decisions incrementally.
   - Choosing the best local option leads to an overall solution.

4. **Predicting if Greedy Is Applicable:**
   - Does the problem have the **greedy-choice property**? (A global optimum can be reached by choosing local optima.)
   - Does the problem have **optimal substructure**? (The solution can be built from solutions to subproblems.)

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you minimizing or maximizing something?
- Are you allowed to make decisions incrementally?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if no valid selection is possible?
- Are there constraints that invalidate the greedy choice?

### ✅ **3. Identify Base Cases**
- The solution starts with an empty result.
- Continue until the problem's requirements are met.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function greedyAlgorithm(input):
    sort input based on a greedy condition
    initialize result
    for each item in input:
        if including item doesn't violate constraints:
            add item to result
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class GreedyTemplate {
    public static int greedyExample(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int num : nums) {
            if (isValid(num)) {
                result += num;
            }
        }
        return result;
    }

    private static boolean isValid(int num) {
        // Define constraint logic here
        return true;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- No valid selections available.
- Duplicate values in the input.
- Input data is already optimized.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Sorting elements        | O(n log n)      | O(1) or O(n)     |
| Selection process       | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Sorting dominates with O(n log n), followed by a linear pass.
- **Space Complexity:** Depends on whether sorting is in-place.

---

## 📚 Example 1: Easy Problem - Coin Change (Minimum Coins)

**Problem:**
Given coins of different denominations and a total amount, find the minimum number of coins needed to make up the amount.

**Input:** `coins = [1, 2, 5]`, `amount = 11`

**Expected Output:** `3` (5 + 5 + 1)

### 🔑 **Solution Steps**
1. Sort the coin denominations in descending order.
2. Use the largest denomination until it exceeds the amount.
3. Subtract from the amount and count coins used.

### ✅ **Code:**
```java
public class CoinChangeGreedy {
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }
        return amount == 0 ? count : -1;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Due to sorting.
- **Space:** O(1) — Only variables used for counting.

---

## 📚 Example 2: Medium Problem - Activity Selection

**Problem:**
Given `n` activities with start and end times, select the maximum number of activities that don't overlap.

**Input:** `start = [1, 3, 0, 5, 8, 5]`, `end = [2, 4, 6, 7, 9, 9]`

**Expected Output:** `4`

### 🔑 **Solution Steps**
1. Sort activities by their ending time.
2. Select the first activity.
3. For each remaining activity, select it if it starts after the last selected activity ends.

### ✅ **Code:**
```java
import java.util.*;

public class ActivitySelection {
    public static int maxActivities(int[] start, int[] end) {
        int n = start.length;
        List<int[]> activities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            activities.add(new int[]{start[i], end[i]});
        }
        activities.sort(Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int lastEnd = activities.get(0)[1];
        for (int i = 1; i < n; i++) {
            if (activities.get(i)[0] >= lastEnd) {
                count++;
                lastEnd = activities.get(i)[1];
            }
        }
        return count;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Due to sorting by end times.
- **Space:** O(n) — Storing activities.

---

## 📚 Example 3: Hard Problem - Huffman Encoding

**Problem:**
Given characters and their frequencies, build a Huffman tree and find the minimum cost of encoding.

**Input:** `chars = ['a', 'b', 'c', 'd', 'e', 'f']`, `freq = [5, 9, 12, 13, 16, 45]`

**Expected Output:** Minimum total cost of encoding

### 🔑 **Solution Steps**
1. Create a priority queue (min-heap) for characters sorted by frequency.
2. Merge two smallest frequencies until one node remains.
3. The final cost is the sum of all merge operations.

### ✅ **Code:**
```java
import java.util.*;

public class HuffmanEncoding {
    static class Node implements Comparable<Node> {
        int freq;
        Node left, right;

        Node(int freq) {
            this.freq = freq;
        }

        public int compareTo(Node other) {
            return this.freq - other.freq;
        }
    }

    public static int huffmanCost(int[] freq) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        for (int f : freq) heap.add(new Node(f));

        int totalCost = 0;
        while (heap.size() > 1) {
            Node left = heap.poll();
            Node right = heap.poll();
            Node merged = new Node(left.freq + right.freq);
            totalCost += merged.freq;
            heap.add(merged);
        }
        return totalCost;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Due to priority queue operations.
- **Space:** O(n) — Storing nodes in the priority queue.

---

## 📚 Key Takeaways

1. Use the Greedy Algorithm pattern for optimization problems that require making locally optimal decisions.
2. The greedy-choice property ensures that making local decisions leads to the global optimum.
3. Time complexity typically involves sorting (O(n log n)) followed by a linear pass.
4. Greedy algorithms are faster than dynamic programming but only applicable if the problem satisfies the greedy-choice property.

---

Next, let's dive into the **Graph Pattern** for solving problems involving connected components, shortest paths, and other graph-related challenges!

