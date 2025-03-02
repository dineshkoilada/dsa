# Dota2 Senate

## 📌 Problem Statement

**LeetCode Problem:** [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate/)  
**Difficulty:** Medium  

**Description:**  
In the Dota2 Senate, two parties, **Radiant ('R')** and **Dire ('D')**, fight for the majority. The senate is represented as a **string** of 'R' and 'D', where:
- Each senator can ban **one opposing senator**.
- The game proceeds in rounds until one party remains.
- Senators ban opponents in **seating order**, cycling through the list repeatedly.
- The winner is the party that bans all the other party's senators.

Return `"Radiant"` if Radiant wins, or `"Dire"` if Dire wins.

### **Example 1:**
**Input:**  
```
senate = "RD"
```
**Output:**  
```
"Radiant"
```

### **Example 2:**
**Input:**  
```
senate = "RDD"
```
**Output:**  
```
"Dire"
```

### **Constraints:**
- `1 <= senate.length <= 10^4`
- `senate[i]` is either `'R'` or `'D'`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine two teams, **Radiant ('R')** and **Dire ('D')**, are taking turns kicking out members from the opposing team.
- They start in a **line**, and each senator bans the next **opponent** they see.
- If a senator is banned, they cannot vote in future rounds.
- The process repeats **until only one team remains**.

For example:
- **"RD"** → Radiant bans Dire first → **Radiant wins**.
- **"RDD"** → Radiant bans one Dire, but the second Dire bans Radiant → **Dire wins**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we decide who gets banned?**
   - Senators act in **seating order**.
2. **How do we track who is still in the game?**
   - Use a **Queue** to manage voting rounds.
3. **Can we do this efficiently in `O(N)`?**
   - Yes! Using **two Queues (one for 'R', one for 'D')**.

👉 We need a solution that runs in **O(N) time** with **O(N) space**.

---

## 🏗️ Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
public class Dota2SenateBruteForce {
    public static String predictPartyVictory(String senate) {
        StringBuilder sb = new StringBuilder(senate);
        while (sb.indexOf("R") != -1 && sb.indexOf("D") != -1) {
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == 'R') {
                    int banIndex = sb.indexOf("D");
                    if (banIndex != -1) sb.deleteCharAt(banIndex);
                } else {
                    int banIndex = sb.indexOf("R");
                    if (banIndex != -1) sb.deleteCharAt(banIndex);
                }
            }
        }
        return sb.indexOf("R") != -1 ? "Radiant" : "Dire";
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since each round iterates over the string.
- **Space Complexity:** `O(N)`, for storing the string.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Queues (O(N) Time, O(N) Space)

```java
import java.util.*;

public class Dota2SenateOptimized {
    public static String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int n = senate.length();
        
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }
        
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();
            if (rIndex < dIndex) {
                radiant.add(rIndex + n);
            } else {
                dire.add(dIndex + n);
            }
        }
        
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since each senator votes once.
- **Space Complexity:** `O(N)`, for queue storage.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two Queues** to track senators' positions."
- "Each round, the senator with the earlier position bans the other."
- "This ensures an **O(N) solution instead of O(N^2)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N^2)`) removes banned senators, but it’s too slow."
- "Using **Queues** is the best (`O(N)`)."

---

## 🔥 Final Takeaways
- **Use Queues to track senators and simulate turns efficiently.**
- **Optimize brute-force approaches with data structures like Queue.**
- **Ensure fairness by processing senators in the order they appear.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/dota2-senate/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-eighth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

