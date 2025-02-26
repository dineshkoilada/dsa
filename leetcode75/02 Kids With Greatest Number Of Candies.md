# Kids With the Greatest Number of Candies

## 📌 Problem Statement

**LeetCode Problem:** [1431. Kids With the Greatest Number of Candies](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/)  
**Difficulty:** Easy  

**Description:**
You are given an array `candies` where `candies[i]` represents the number of candies a kid has. You are also given an integer `extraCandies`, which you can give to any kid.

Return a boolean array `result` where `result[i]` is `true` if, after giving `extraCandies` to the `i-th` kid, they have the **greatest number of candies** among all kids.

### **Example 1:**
**Input:**
```
candies = [2,3,5,1,3], extraCandies = 3
```
**Output:**
```
[true, true, true, false, true]
```

### **Example 2:**
**Input:**
```
candies = [4,2,1,1,2], extraCandies = 1
```
**Output:**
```
[true, false, false, false, false]
```

### **Constraints:**
- `2 <= candies.length <= 100`
- `1 <= candies[i] <= 100`
- `1 <= extraCandies <= 50`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you and your friends each have a different number of candies. You also have some **extra candies** that you can give to any of your friends. Your task is to find out **which friends can have the most candies** after getting some extra.

If a friend already has **5 candies** and the most candies in the group is **5**, they are already one of the greatest. If they get **extra candies**, they will **definitely still be the greatest**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if all kids already have the same number of candies?**
   - Then everyone will always be the greatest.
2. **What if the extra candies are zero?**
   - Then only the current maximum remains the greatest.
3. **What if we give all extra candies to just one kid?**
   - That kid will definitely be the greatest.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Find the current maximum number of candies
```java
int maxCandies = 0;
for (int candy : candies) {
    maxCandies = Math.max(maxCandies, candy);
}
```

### Step 2: Check if each kid can reach or exceed the max with extra candies
```java
for (int i = 0; i < candies.length; i++) {
    result[i] = (candies[i] + extraCandies >= maxCandies);
}
```

### Step 3: Return the result array
```java
return result;
```

---

## 🛠️ Writing the Brute Force Solution

```java
import java.util.*;

public class KidsWithCandies {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int maxCandies = 0;
        
        for (int candy : candies) {
            maxCandies = Math.max(maxCandies, candy);
        }
        
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the number of kids (2 loops but independent, so still `O(N)`).
- **Space Complexity:** `O(N)`, because we store results in an extra list.

---

## 🚀 Optimized Solution (Same Complexity, Clean Implementation)

```java
import java.util.*;

public class KidsWithCandiesOptimized {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Arrays.stream(candies).max().getAsInt();
        List<Boolean> result = new ArrayList<>();
        
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we only loop through the array twice.
- **Space Complexity:** `O(N)`, since we store the results in a list.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "First, we find the maximum number of candies any kid currently has."
- "Then, we check if adding `extraCandies` to each kid makes them equal or greater than this max."
- "We return a list where each kid gets `true` if they can be the greatest, otherwise `false`."

If the interviewer asks for **alternative approaches**, you can say:
- "We could sort the array and check against the second-highest value, but that would take `O(N log N)`, which is unnecessary."
- "Using `Arrays.stream().max()` is a concise way to find the max value."

---

## 🔥 Final Takeaways
- **Find the maximum value first!**
- **Loop through once more to compare each kid’s new total.**
- **Optimize readability using Java Streams (if applicable).**
- **Think about edge cases (e.g., when all values are the same).**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **third problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

