# Asteroid Collision

## 📌 Problem Statement

**LeetCode Problem:** [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/)  
**Difficulty:** Medium  

**Description:**  
We are given an array `asteroids` of integers representing asteroids in a row. For each asteroid, the absolute value represents its size, and the sign represents its direction:
- **Positive** (`+`) moves **right**.
- **Negative** (`-`) moves **left**.

Collisions occur when a **right-moving asteroid** meets a **left-moving asteroid**. The larger asteroid survives, and if they are equal, both are destroyed. Return the state of asteroids after all collisions.

### **Example 1:**
**Input:**  
```
asteroids = [5,10,-5]
```
**Output:**  
```
[5,10]
```

### **Example 2:**
**Input:**  
```
asteroids = [8,-8]
```
**Output:**  
```
[]
```

### **Constraints:**
- `2 <= asteroids.length <= 10^4`
- `-1000 <= asteroids[i] <= 1000`
- `asteroids[i] != 0`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine **asteroids** flying through space. Some move **right (`+`)** and some move **left (`-`)**. If two asteroids collide:
- The **bigger one wins**.
- If they are **equal**, both explode.
- If they move in the **same direction**, they never collide.

For example:
- **[5,10,-5]** → `10` survives because it is bigger than `-5`.
- **[8,-8]** → Both explode because they are equal.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we check for collisions?**
   - When a **right-moving asteroid** meets a **left-moving asteroid**.
2. **How do we handle multiple collisions?**
   - Use a **Stack** to keep track of active asteroids.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! By processing asteroids one by one using a **Stack**.

👉 We need a solution that runs in **O(N) time** with **O(N) space**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
import java.util.*;

public class AsteroidCollisionBruteForce {
    public static int[] asteroidCollision(int[] asteroids) {
        List<Integer> result = new ArrayList<>(Arrays.asList(asteroids[0]));
        
        for (int i = 1; i < asteroids.length; i++) {
            boolean destroyed = false;
            while (!result.isEmpty() && result.get(result.size() - 1) > 0 && asteroids[i] < 0) {
                if (Math.abs(result.get(result.size() - 1)) < Math.abs(asteroids[i])) {
                    result.remove(result.size() - 1);
                } else if (Math.abs(result.get(result.size() - 1)) == Math.abs(asteroids[i])) {
                    result.remove(result.size() - 1);
                    destroyed = true;
                    break;
                } else {
                    destroyed = true;
                    break;
                }
            }
            if (!destroyed) result.add(asteroids[i]);
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since checking collisions in a loop is inefficient.
- **Space Complexity:** `O(N)`, for storing asteroid states.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Stack (O(N) Time, O(N) Space)

```java
import java.util.*;

public class AsteroidCollisionOptimized {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            boolean alive = true;
            
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                }
                alive = false;
                break;
            }
            
            if (alive) stack.push(asteroid);
        }
        
        return stack.stream().mapToInt(i -> i).toArray();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since each asteroid is processed once.
- **Space Complexity:** `O(N)`, for storing the stack.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **Stack** to keep track of active asteroids."
- "When a right-moving asteroid meets a left-moving one, we compare their sizes and update the stack."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N^2)`) checks all collisions but is too slow."
- "Using a **Stack** is the best approach (`O(N)`)."

---

## 🔥 Final Takeaways
- **Use a Stack to handle asteroid collisions efficiently.**
- **Avoid nested loops by leveraging a Stack structure.**
- **Optimize brute-force approaches using data structures like Stack.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/asteroid-collision/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-fifth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

