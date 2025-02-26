# Can Place Flowers

## 📌 Problem Statement

**LeetCode Problem:** [605. Can Place Flowers](https://leetcode.com/problems/can-place-flowers/)  
**Difficulty:** Easy  

**Description:**
You have a flowerbed represented as an integer array `flowerbed`, containing `0` and `1`, where:
- `0` means an empty spot.
- `1` means a spot already occupied by a flower.

You are also given an integer `n`, which represents the number of new flowers you need to plant. Flowers cannot be planted in adjacent spots.

Return `true` if `n` new flowers can be planted without violating the no-adjacent-flowers rule; otherwise, return `false`.

### **Example 1:**
**Input:**
```
flowerbed = [1,0,0,0,1], n = 1
```
**Output:**
```
true
```

### **Example 2:**
**Input:**
```
flowerbed = [1,0,0,0,1], n = 2
```
**Output:**
```
false
```

### **Constraints:**
- `1 <= flowerbed.length <= 2 * 10^4`
- `flowerbed[i]` is `0` or `1`.
- `0 <= n <= flowerbed.length`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **row of plant pots**. Some pots already have flowers (`1`), and some are empty (`0`).

You have a few more flowers, and you want to **plant them** in the empty pots. But there’s a rule: **no two flowers can be planted next to each other**. Your task is to see **if you can plant `n` flowers while following the rule**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if `n` is `0`?**
   - We can always return `true` because we don’t need to plant anything.
2. **What happens if the flowerbed is full (`1` everywhere)?**
   - We can’t plant anything, so return `false`.
3. **What if there are enough empty spaces but they are all next to each other?**
   - We must check whether there is enough **space between** the plants.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Loop through the flowerbed
```java
for (int i = 0; i < flowerbed.length; i++) {
```

### Step 2: Check if the current spot is empty (`0`) and its neighbors are also empty
```java
if (flowerbed[i] == 0 &&
    (i == 0 || flowerbed[i - 1] == 0) &&
    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
```

### Step 3: Plant a flower (`1`) and decrement `n`
```java
flowerbed[i] = 1;
n--;
```

### Step 4: If `n == 0`, return `true`
```java
if (n == 0) return true;
```

### Step 5: If we finish looping and `n > 0`, return `false`
```java
return n <= 0;
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 &&
                (i == 0 || flowerbed[i - 1] == 0) &&
                (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
                if (n == 0) return true;
            }
        }
        return n <= 0;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we traverse the flowerbed once.
- **Space Complexity:** `O(1)`, as we modify the input in place without extra storage.

---

## 🚀 Optimized Solution (Same Complexity, More Efficient Code)

```java
public class CanPlaceFlowersOptimized {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int len = flowerbed.length;
        
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0 &&
                (i == 0 || flowerbed[i - 1] == 0) &&
                (i == len - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
                if (count >= n) return true;
            }
        }
        return count >= n;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, as no additional data structures are used.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We iterate through the flowerbed, checking if we can plant a flower at an empty spot."
- "We ensure that adjacent spots are also empty before planting."
- "If we manage to plant `n` flowers, we return `true`; otherwise, `false`."

If the interviewer asks for **alternative approaches**, you can say:
- "We could use recursion, but it wouldn’t improve performance."
- "A two-pointer approach isn’t needed because we’re only scanning for empty spots."

---

## 🔥 Final Takeaways
- **Understand the adjacency constraint before planting.**
- **Keep track of `n` and return early if all flowers are planted.**
- **Think about edge cases: empty flowerbeds, full flowerbeds, and isolated spaces.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/can-place-flowers/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fourth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

