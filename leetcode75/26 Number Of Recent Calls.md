# Number of Recent Calls

## 📌 Problem Statement

**LeetCode Problem:** [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/)  
**Difficulty:** Easy  

**Description:**  
You have a `RecentCounter` class that counts recent requests within the last `3000` milliseconds (3 seconds). Implement the `RecentCounter` class:
- `RecentCounter()` Initializes the counter.
- `int ping(int t)` Adds a new request at time `t` and returns the number of requests in the past `3000` milliseconds.

### **Example 1:**
**Input:**  
```
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);
recentCounter.ping(100);
recentCounter.ping(3001);
recentCounter.ping(3002);
```
**Output:**  
```
[1, 2, 3, 3]
```

### **Constraints:**
- `1 <= t <= 10^9`
- Calls to `ping` are **strictly increasing**.
- At most `10^4` calls to `ping` will be made.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you are counting **how many messages** you received in the last **3 seconds**.
- Each time someone sends a message, you write down the time.
- Then, you check **how many messages were sent in the last 3 seconds**.

For example:
- If messages are sent at **1, 100, 3001, 3002**, then:
  - At `1`, only `1` message exists.
  - At `100`, messages at `[1, 100]` exist.
  - At `3001`, messages at `[1, 100, 3001]` exist.
  - At `3002`, messages at `[100, 3001, 3002]` exist (since `1` is more than 3000ms old).

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we store request times?**
   - Use a **Queue** to track the request times.
2. **How do we remove old requests?**
   - Remove all times **older than 3000ms** from the queue.
3. **Can we do this efficiently?**
   - Yes! Using a **Queue with O(1) insertion and removal**.

👉 We need a solution that runs in **O(1) time per ping** with **O(N) space**.

---

## 🏗️ Brute Force Solution (O(N) Time Complexity) 🚨

```java
import java.util.*;

public class RecentCounterBruteForce {
    private List<Integer> requests;

    public RecentCounterBruteForce() {
        requests = new ArrayList<>();
    }
    
    public int ping(int t) {
        requests.add(t);
        int count = 0;
        for (int time : requests) {
            if (time >= t - 3000) {
                count++;
            }
        }
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the list.
- **Space Complexity:** `O(N)`, since we store request times.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Queue (O(1) Time, O(N) Space)

```java
import java.util.*;

public class RecentCounterOptimized {
    private Queue<Integer> queue;

    public RecentCounterOptimized() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        queue.add(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(1)`, since we only enqueue and dequeue.
- **Space Complexity:** `O(N)`, for storing request times.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **Queue** to store request times."
- "For each `ping(t)`, we **enqueue** the request and remove old requests."
- "This ensures an **O(1) solution instead of O(N)**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach (`O(N)`) checks all times but is too slow."
- "Using a **Queue** is the best (`O(1)`)."

---

## 🔥 Final Takeaways
- **Use a Queue to efficiently track recent requests.**
- **Remove outdated requests in `O(1)`.**
- **Optimize brute-force solutions with data structures like Queue.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/number-of-recent-calls/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twenty-seventh problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.