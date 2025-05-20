# Greedy Algorithm Pattern 💰

## 📌 Introduction: The Power of Local Choices

Imagine you’re collecting coins to reach a specific amount. You always pick the largest coin available first to minimize the total number of coins needed. This is the essence of the **Greedy Algorithm**—making the locally optimal choice at each step in the hope of finding a global optimum.

### 🎬 Real-World Analogies:

1. **Coin Change** 🪙
   - Always pick the largest coin first to reach the target amount with the fewest coins.
2. **Interval Scheduling** 📅
   - Always pick the meeting that ends earliest to fit in the most meetings.
3. **Packing a Bag** 🎒
   - Always pick the item with the highest value-to-weight ratio to maximize value.

The Greedy Algorithm pattern is your secret weapon for:
- 🏆 Optimization problems (minimizing or maximizing values)
- 📅 Interval scheduling problems
- 📦 Resource allocation and distribution
- 🗜️ Huffman coding and compression algorithms

### 🎯 Visual Example:
Making change for 11 with coins [1, 2, 5]:
```
Amount: 11
Pick 5 → 6 left
Pick 5 → 1 left
Pick 1 → 0 left
Coins used: [5, 5, 1]
```
- At each step, pick the largest coin that does not exceed the remaining amount.

---

## 🧠 How to Recognize a Greedy Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Local Optimum" Clue** 🥇
   - The problem can be solved by making the best choice at each step.
   - Example: "Pick the largest/smallest/earliest/latest item."
2. **The "Sorting" Hint** 🔢
   - Sorting the input helps make the best choice at each step.
   - Example: "Sort intervals by end time."
3. **The "No Reconsideration" Signal** 🚫
   - Once a choice is made, it is never changed.
   - Example: "Once a coin is picked, it is not replaced."

### 🤔 Essential Questions to Ask:

1. **Problem Type Questions:**
   - Are you minimizing or maximizing something?
   - Are you allowed to make decisions incrementally?
2. **Content Questions:**
   - Are there constraints that invalidate the greedy choice?
   - Will a local decision always lead to the best overall solution?
3. **Edge Case Questions:**
   - What if no valid selection is possible?
   - Are there duplicate values in the input?

### 🎨 Visual Problem-Solving Framework:
```
Step 1: Sort or prioritize input based on greedy criteria
[5][2][1]  // Largest to smallest for coin change

Step 2: Make the best local choice
Pick 5 → Remaining: 6
Pick 5 → Remaining: 1
Pick 1 → Remaining: 0

Step 3: Repeat until the goal is reached or no valid choices remain
[5, 5, 1]  // Solution found
```
- At each step, make the best local choice and update the state.
- Greedy algorithms do not backtrack or reconsider previous choices.

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

Next, let’s dive into the **Graph Pattern** for solving problems involving connected components, shortest paths, and other graph-related challenges!

