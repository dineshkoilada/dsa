# Find the Highest Altitude

## 📌 Problem Statement

**LeetCode Problem:** [1732. Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/)  
**Difficulty:** Easy  

**Description:**
You are given an integer array `gain` where `gain[i]` represents the net gain or loss in altitude between `i` and `i+1`. The altitude starts at `0`, and your task is to find the **highest altitude** achieved.

### **Example 1:**
**Input:** 
```
gain = [-5,1,5,0,-7]
```
**Output:** 
```
1
```

### **Example 2:**
**Input:** 
```
gain = [-4,-3,-2,-1,4,3,2]
```
**Output:** 
```
0
```

### **Constraints:**
- `1 <= gain.length <= 100`
- `-100 <= gain[i] <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you are **hiking a mountain** and you have a list of altitude changes. You start at altitude `0`, and you go **up or down** based on the values in the list. Your goal is to **find the highest altitude you ever reach** during the hike.

For example:
- **[-5,1,5,0,-7]** → Your altitude changes: `0 → -5 → -4 → 1 → 1 → -6`, so the highest altitude is **1**.

Our goal is to **find the highest altitude during the hike**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if all values are negative?**
   - The highest altitude will be `0`, since we start at `0`.
2. **What if there’s only one altitude change?**
   - We simply check if it's positive or negative and update the highest altitude accordingly.
3. **Can we check each altitude one by one?**
   - Yes! We can track the altitude as we move.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Initialize Starting Altitude
```java
int altitude = 0, maxAltitude = 0;
```

### Step 2: Iterate Through `gain` to Track Altitude
```java
for (int change : gain) {
    altitude += change;
    maxAltitude = Math.max(maxAltitude, altitude);
}
```

### Step 3: Return the Maximum Altitude
```java
return maxAltitude;
```

---

## 🛠️ Writing the Brute Force Solution 🚨

```java
public class FindHighestAltitudeBruteForce {
    public static int largestAltitude(int[] gain) {
        int altitude = 0;
        int maxAltitude = 0;
        
        for (int i = 0; i < gain.length; i++) {
            altitude = 0;
            for (int j = 0; j <= i; j++) {
                altitude += gain[j];
            }
            maxAltitude = Math.max(maxAltitude, altitude);
        }
        return maxAltitude;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we compute altitude at every step.
- **Space Complexity:** `O(1)`, since we use only variables.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Prefix Sum (O(N) Time, O(1) Space)

```java
public class FindHighestAltitudeOptimized {
    public static int largestAltitude(int[] gain) {
        int altitude = 0, maxAltitude = 0;
        
        for (int change : gain) {
            altitude += change;
            maxAltitude = Math.max(maxAltitude, altitude);
        }
        
        return maxAltitude;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate once.
- **Space Complexity:** `O(1)`, since we only use a few extra variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We track altitude changes as we move along the `gain` array."
- "We update `altitude` by adding each change and track the maximum altitude reached."
- "This ensures we find the highest altitude efficiently in `O(N)`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach recalculates the altitude at each step (`O(N^2)`), but is too slow."
- "A **prefix sum technique** allows us to efficiently track altitude in `O(N)`."

---

## 🔥 Final Takeaways
- **Use a running sum to track altitude changes efficiently.**
- **Keep track of the maximum altitude while iterating.**
- **Think about edge cases: all negative values, small arrays, large `k`.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/find-the-highest-altitude/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **eighteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

