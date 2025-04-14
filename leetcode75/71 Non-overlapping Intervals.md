# Non-overlapping Intervals

## 📌 Problem Statement

**LeetCode Problem:** [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)  
**Difficulty:** Medium  

**Description:**
Given an array of intervals `intervals` where `intervals[i] = [starti, endi]`, return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

### **Example 1:**
**Input:** 
```
intervals = [[1,2],[2,3],[3,4],[1,3]]
```
**Output:** 
```
1
```
**Explanation:** 
- [1,3] can be removed and the rest of the intervals are non-overlapping.

### **Example 2:**
**Input:** 
```
intervals = [[1,2],[1,2],[1,2]]
```
**Output:** 
```
2
```
**Explanation:** 
- You need to remove two [1,2] intervals to make the rest of the intervals non-overlapping.

### **Example 3:**
**Input:** 
```
intervals = [[1,2],[2,3]]
```
**Output:** 
```
0
```
**Explanation:** 
- The intervals are already non-overlapping.

### **Constraints:**
- `1 <= intervals.length <= 10^5`
- `intervals[i].length == 2`
- `-5 * 10^4 <= starti < endi <= 5 * 10^4`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a list of activities with start and end times. Some of these activities overlap with each other, meaning you can't do both. Your goal is to figure out the minimum number of activities you need to cancel so that none of the remaining activities overlap with each other.

For example:
- Activity 1: 1pm to 2pm
- Activity 2: 2pm to 3pm
- Activity 3: 3pm to 4pm
- Activity 4: 1pm to 3pm

Activities 1, 2, and 3 don't overlap with each other (Activity 1 ends exactly when Activity 2 starts, and so on). But Activity 4 overlaps with both Activities 1 and 2. So, you need to remove at least one activity - Activity 4 - to make the schedule conflict-free.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How do we identify when two intervals overlap?**
   - Two intervals [a,b] and [c,d] overlap if a < d and c < b.
2. **What's a good strategy for minimizing the number of intervals to remove?**
   - We can sort the intervals by their end times and prioritize keeping intervals that end earlier.
3. **Why does greedy approach work here?**
   - By keeping intervals with earlier end times, we leave more room for future non-overlapping intervals.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Sort the intervals by their end times
```java
// Sort intervals by end time
Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
```

### Step 2: Use a greedy approach to find non-overlapping intervals
```java
int count = 0;        // Number of intervals to remove
int end = Integer.MIN_VALUE;  // End time of the last included interval

for (int[] interval : intervals) {
    if (interval[0] >= end) {
        // This interval doesn't overlap with the last included interval
        end = interval[1];  // Update the end time
    } else {
        // This interval overlaps, so we must remove it
        count++;
    }
}

return count;
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
class NonOverlappingIntervals {
    /**
     * Finds the minimum number of intervals to remove to make the rest non-overlapping.
     * This solution uses a greedy approach, keeping intervals that end earlier.
     * 
     * Time Complexity: O(n log n) where n is the number of intervals (due to sorting)
     * Space Complexity: O(1) or O(n) depending on the sorting implementation
     * 
     * @param intervals Array of intervals where intervals[i] = [start_i, end_i]
     * @return Minimum number of intervals to remove
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort intervals by their end times
        // This allows us to greedily select intervals that end earlier
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;        // Number of intervals to remove
        int end = Integer.MIN_VALUE;  // End time of the last included interval
        
        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                // Current interval starts after or at the same time as the previous interval ends
                // So we can include this interval without overlap
                end = interval[1];  // Update the end time
            } else {
                // Current interval overlaps with the previous one
                // We must remove this interval
                count++;
            }
        }
        
        return count;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n log n)` due to sorting the intervals. The subsequent linear scan is O(n).
- **Space Complexity:** `O(1)` for the algorithm itself, though the sorting algorithm may use O(log n) to O(n) extra space depending on the implementation.

---

## 🔄 Alternative Approach: Sort by Start Time

Another way to approach this problem is to sort by start time:

```java
public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
        return 0;
    }
    
    // Sort intervals by their start times
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    
    int count = 0;
    int end = intervals[0][1];  // End time of the first interval
    
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < end) {
            // Overlap detected
            count++;
            // Keep the interval with the earlier end time
            end = Math.min(end, intervals[i][1]);
        } else {
            // No overlap, update end time
            end = intervals[i][1];
        }
    }
    
    return count;
}
```

While this approach also works, sorting by end time is generally more efficient for this problem, as it allows a simpler greedy algorithm.

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I approached this problem using a greedy algorithm, which works well for interval scheduling problems. Here's my thought process:

1. **Sort the intervals by their end times:**
   - This is the key insight. By prioritizing intervals that end earlier, we maximize the room for including more non-overlapping intervals later.

2. **Iterate through the sorted intervals:**
   - Keep track of the end time of the last included interval.
   - For each current interval:
     - If it starts at or after the end time of the last included interval, we can include it without causing overlap.
     - If it starts before the end time of the last included interval (causing overlap), we must remove it.

3. **Count the intervals to remove:**
   - Each time we encounter an overlap, we increment our counter of intervals to remove.

4. **Proof of correctness:**
   - This greedy approach works because by always selecting the interval with the earliest end time, we leave the maximum amount of time available for future intervals.
   - This minimizes the number of intervals we need to remove.

The time complexity is O(n log n) due to the sorting step, and the space complexity is O(1) for the algorithm itself, though the sorting may use additional space depending on implementation."

---

## 🔍 Real-World Applications

This problem has direct applications in various real-world scenarios:

1. **Meeting Room Scheduling:** Determining the minimum number of meetings to reschedule to avoid conflicts.

2. **Task Scheduling:** When multiple tasks require exclusive access to a resource, finding the optimal schedule.

3. **Network Packet Scheduling:** Minimizing dropped packets in constrained bandwidth situations.

4. **Resource Allocation:** In systems where resources cannot be shared, maximizing the number of tasks that can be completed.

5. **Air Traffic Control:** Scheduling landing and takeoff slots to avoid conflicts.

The greedy approach of selecting activities that finish earliest is a fundamental concept in activity selection problems and has broad applications in optimization.

---

## 🔄 Connection to Other Problems

This problem is related to several other interval-based problems:

1. **Merge Intervals:** Instead of removing overlapping intervals, we merge them.

2. **Meeting Rooms II:** Determines the minimum number of meeting rooms required.

3. **Maximum Number of Events That Can Be Attended:** Similar greedy approach of choosing events by end time.

4. **Task Scheduler:** Involves scheduling tasks with constraints.

Understanding the solution to this problem provides insights into a whole class of scheduling and interval-based problems.

---

## 🔥 Final Takeaways

- **Greedy algorithms often work well for interval scheduling problems.**
- **The key insight is sorting by end times**, which maximizes the potential for including more intervals.
- **When dealing with overlapping intervals**, consider:
  1. Sorting by start time vs. end time
  2. Which interval to keep when overlaps occur
  3. How to track and count overlaps efficiently
- **The approach is intuitive**: finish activities as early as possible to leave room for more activities.
- **This problem illustrates the importance of proper ordering** in optimization problems.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/non-overlapping-intervals/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventy-second problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.