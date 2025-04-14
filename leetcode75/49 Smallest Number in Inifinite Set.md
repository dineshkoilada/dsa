# Smallest Number in Infinite Set

## 📌 Problem Statement

**LeetCode Problem:** [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set/)  
**Difficulty:** Medium  

**Description:**  
You have a set which contains all positive integers `[1, 2, 3, 4, 5, ...]`.

Implement the `SmallestInfiniteSet` class:

- `SmallestInfiniteSet()` Initializes the SmallestInfiniteSet object to contain all positive integers.
- `int popSmallest()` Removes and returns the smallest integer contained in the infinite set.
- `void addBack(int num)` Adds a positive integer `num` back into the infinite set, if it is not already in the infinite set.

### **Example 1:**
**Input:**
```
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
```
**Output:**
```
[null, null, 1, 2, 3, null, 1, 4, 5]
```
**Explanation:**
```
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change.
smallestInfiniteSet.popSmallest(); // return 1, [1,2,3,4,5,...] becomes [2,3,4,5,...]
smallestInfiniteSet.popSmallest(); // return 2, [2,3,4,5,...] becomes [3,4,5,...]
smallestInfiniteSet.popSmallest(); // return 3, [3,4,5,...] becomes [4,5,...]
smallestInfiniteSet.addBack(1);    // 1 is added back, [4,5,...] becomes [1,4,5,...]
smallestInfiniteSet.popSmallest(); // return 1, [1,4,5,...] becomes [4,5,...]
smallestInfiniteSet.popSmallest(); // return 4, [4,5,...] becomes [5,...]
smallestInfiniteSet.popSmallest(); // return 5, [5,...] becomes [6,...]
```

### **Constraints:**
- `1 <= num <= 1000`
- At most `1000` calls will be made to `popSmallest` and `addBack`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have all the counting numbers (1, 2, 3, 4, 5, ...) in a special box. You can do two things with this box:

1. Take out the smallest number that's still in the box.
2. Put a number back into the box if it's not already there.

For example, if you take out 1, 2, and 3, your box now has (4, 5, 6, ...). If you put 2 back in, your box now has (2, 4, 5, 6, ...). If you take the smallest number now, you'll get 2, and your box will have (4, 5, 6, ...).

You need to build this special box that keeps track of what numbers are in and out of the box.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we efficiently track the smallest number in our set?**
   - We can use a **min-heap** to always have quick access to the smallest element.
2. **How do we know what numbers have been removed?**
   - We can keep a **separate set** to track which numbers have been "popped" out.
3. **Can we optimize our approach for popSmallest?**
   - Yes! Instead of tracking removed numbers, we can track current numbers and keep a counter for the smallest available integer.

👉 The key insight is to efficiently manage the "heap" of available numbers without storing the entire infinite set!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Initialize our data structures
```java
class SmallestInfiniteSet {
    private int currentSmallest;  // Tracks the smallest integer in the infinite portion
    private PriorityQueue<Integer> addedBack;  // Min-heap for numbers that were added back
    private Set<Integer> presentInHeap;  // To check if a number is already in the heap
    
    public SmallestInfiniteSet() {
        currentSmallest = 1;  // Start with 1 as the smallest
        addedBack = new PriorityQueue<>();
        presentInHeap = new HashSet<>();
    }
}
```

### Step 2: Implement popSmallest
```java
public int popSmallest() {
    int result;
    
    // If we have numbers that were added back, return the smallest among them
    if (!addedBack.isEmpty() && addedBack.peek() < currentSmallest) {
        result = addedBack.poll();
        presentInHeap.remove(result);
    } else {
        // Otherwise, return the current smallest from the infinite portion
        result = currentSmallest++;
    }
    
    return result;
}
```

### Step 3: Implement addBack
```java
public void addBack(int num) {
    // Only add back if: 
    // 1. The number is smaller than currentSmallest (it was popped)
    // 2. It's not already in our heap
    if (num < currentSmallest && !presentInHeap.contains(num)) {
        addedBack.offer(num);
        presentInHeap.add(num);
    }
}
```

---

## 🛠️ Writing the Complete Solution

```java
class SmallestInfiniteSet {
    private int currentSmallest;  // Tracks the smallest integer in the infinite portion
    private PriorityQueue<Integer> addedBack;  // Min-heap for numbers that were added back
    private Set<Integer> presentInHeap;  // To check if a number is already in the heap

    public SmallestInfiniteSet() {
        currentSmallest = 1;  // Start with 1 as the smallest
        addedBack = new PriorityQueue<>();
        presentInHeap = new HashSet<>();
    }
    
    public int popSmallest() {
        int result;
        
        // If we have numbers that were added back, and the smallest one is smaller than currentSmallest
        if (!addedBack.isEmpty() && addedBack.peek() < currentSmallest) {
            result = addedBack.poll();
            presentInHeap.remove(result);
        } else {
            // Otherwise, return the current smallest from the infinite portion
            result = currentSmallest++;
        }
        
        return result;
    }
    
    public void addBack(int num) {
        // Only add back if: 
        // 1. The number is smaller than currentSmallest (it was popped)
        // 2. It's not already in our heap
        if (num < currentSmallest && !presentInHeap.contains(num)) {
            addedBack.offer(num);
            presentInHeap.add(num);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** 
  - `popSmallest()`: `O(log n)` where n is the number of elements added back (heap operations).
  - `addBack(num)`: `O(log n)` to add to the heap, and `O(1)` to check the set.
  - Constructor: `O(1)`.
- **Space Complexity:** `O(n)` for storing the heap and set of added-back numbers.

---

## 🚀 Alternative Solution using TreeSet

```java
class SmallestInfiniteSet {
    private int currentSmallest;  // Tracks the smallest integer in the infinite portion
    private TreeSet<Integer> addedNumbers;  // TreeSet automatically keeps numbers sorted

    public SmallestInfiniteSet() {
        currentSmallest = 1;  // Start with 1 as the smallest
        addedNumbers = new TreeSet<>();
    }
    
    public int popSmallest() {
        int result;
        
        // If we have numbers that were added back, and the smallest one is smaller than currentSmallest
        if (!addedNumbers.isEmpty() && addedNumbers.first() < currentSmallest) {
            result = addedNumbers.first();
            addedNumbers.remove(result);
        } else {
            // Otherwise, return the current smallest from the infinite portion
            result = currentSmallest++;
        }
        
        return result;
    }
    
    public void addBack(int num) {
        // Only add back if the number is smaller than currentSmallest (it was popped)
        if (num < currentSmallest) {
            addedNumbers.add(num);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis (TreeSet Solution)
- **Time Complexity:** 
  - `popSmallest()`: `O(log n)` for TreeSet operations.
  - `addBack(num)`: `O(log n)` to add to the TreeSet.
  - Constructor: `O(1)`.
- **Space Complexity:** `O(n)` for storing the TreeSet of added-back numbers.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I've implemented the `SmallestInfiniteSet` class using a combination of a min-heap and a counter."
- "Instead of actually storing the infinite set, I track the current smallest number that hasn't been removed yet."
- "For numbers that get added back, I store them in a min-heap for quick access to the smallest one."

If asked about **the data structures chosen**, you can say:
- "The min-heap (PriorityQueue) allows me to efficiently find the smallest number that was added back in O(log n) time."
- "I use a HashSet for O(1) checks to see if a number is already in the heap to avoid duplicates."
- "The counter (currentSmallest) efficiently keeps track of what numbers were removed from the infinite portion."

If asked for **alternative approaches**, you can say:
- "I could also use a TreeSet, which keeps elements sorted and offers O(log n) operations for all our needs."
- "Another approach could be to use a bitmap to track removed numbers if the range is limited enough."

---

## 🔥 Final Takeaways
- **Clever use of data structures** can help avoid storing an "infinite" set.
- **Min-heaps (PriorityQueues)** are excellent for applications requiring frequent access to the minimum element.
- **HashSets** provide O(1) lookups, which can complement heaps for checking membership.
- **TreeSets** can be a good alternative when you need a self-balancing BST with sorted order.
- **Consider space-time tradeoffs** - our approach uses extra space but provides efficient operations.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/smallest-number-in-infinite-set/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fiftieth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.