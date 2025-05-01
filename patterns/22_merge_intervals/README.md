# Merge Intervals Pattern

## 🎯 Introduction

Imagine you're trying to schedule meetings in your calendar and need to find free time slots. The **Merge Intervals** pattern is a powerful technique for dealing with interval-based problems, where you need to either merge overlapping intervals or find relations between them.

The Merge Intervals Pattern is particularly useful for:
- Scheduling problems (meeting rooms, appointment conflicts)
- Time series data analysis
- Resource allocation and optimization
- Range-based operations in databases

This pattern works best when you have a collection of intervals where each interval has a start and end point.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Does the problem involve intervals with start and end points?
   - Are you required to merge overlapping intervals?
   - Do you need to find conflicts or gaps between intervals?

2. **Ask Clarifying Questions:**
   - Are the intervals sorted?
   - How should we handle exactly touching intervals? (e.g., [1, 5] and [5, 10])
   - What should be returned if there are no intervals?

3. **Identify Clues for Using Merge Intervals Pattern:**
   - Problem talks about ranges, intervals, or periods
   - Requires finding overlaps or merging overlapping intervals
   - Involves scheduling or time allocation

4. **Predicting if Merge Intervals Is Applicable:**
   - Look for words like "overlap," "merge," "intersect," or "schedule" in the problem
   - The input involves pairs of numbers representing ranges

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are the intervals already sorted?
- How are overlapping intervals defined?
- What is the expected output format?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen with empty input?
- How to handle single intervals?

### ✅ **3. Identify Base Cases**
- Empty array: Return empty array
- Single interval: Return as is

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function mergeIntervals(intervals):
    if intervals is empty:
        return empty array
    
    sort intervals by start time
    
    initialize result with first interval
    
    for each interval in intervals (starting from second):
        if current interval overlaps with last interval in result:
            merge them
        else:
            add current interval to result
    
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        // Handle base cases
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        
        for (int i = 1; i < intervals.length; i++) {
            // Get current interval
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            
            // Get next interval
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];
            
            // If overlapping, merge them
            if (currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Not overlapping, add to result
                currentInterval = intervals[i];
                result.add(currentInterval);
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Intervals are not sorted
- All intervals overlap
- No intervals overlap
- Intervals of length 0 (start equals end)
- Negative values in intervals

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Sorting intervals       | O(n log n)      | O(log n)         |
| Merging process         | O(n)            | O(n)             |
| Overall                 | O(n log n)      | O(n)             |

**How to derive these complexities:**
- **Time Complexity:** Dominated by the sorting step, which is O(n log n). The linear scan to merge intervals is O(n).
- **Space Complexity:** O(n) for storing the merged intervals in the worst case plus O(log n) for the sorting algorithm.

---

## 📚 Example 1: Easy Problem - Merge Overlapping Intervals

**Problem:**
Given a collection of intervals, merge all overlapping intervals.

**Input:**
```
[[1,3],[2,6],[8,10],[15,18]]
```

**Expected Output:**
```
[[1,6],[8,10],[15,18]]
```

### 🔑 **Solution Steps**
1. Sort intervals by start time
2. Initialize result with the first interval
3. For each interval, if it overlaps with the last result interval, merge them; otherwise, add it to result

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Dominated by the sorting operation
- **Space:** O(n) — To store the result

---

## 📚 Example 2: Medium Problem - Insert Interval

**Problem:**
Given a set of non-overlapping intervals and a new interval, insert the interval at the correct position and merge if necessary.

**Input:**
```
Intervals: [[1,3],[6,9]]
New Interval: [2,5]
```

**Expected Output:**
```
[[1,5],[6,9]]
```

### 🔑 **Solution Steps**
1. Initialize result list
2. Add all intervals that come before the new interval
3. Merge the new interval with any overlapping intervals
4. Add all remaining intervals

### ✅ **Code:**
```java
public static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;
    
    // Add all intervals that come before the new interval
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i]);
        i++;
    }
    
    // Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    
    // Add the merged interval
    result.add(newInterval);
    
    // Add all intervals that come after the new interval
    while (i < n) {
        result.add(intervals[i]);
        i++;
    }
    
    return result.toArray(new int[result.size()][]);
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Single pass through the intervals
- **Space:** O(n) — To store the result

---

## 📚 Example 3: Hard Problem - Meeting Rooms II

**Problem:**
Given an array of meeting time intervals consisting of start and end times, find the minimum number of conference rooms required.

**Input:**
```
[[0,30],[5,10],[15,20]]
```

**Expected Output:**
```
2
```

### 🔑 **Solution Steps**
1. Separate start and end times
2. Sort start and end times
3. Use two pointers to compare start and end times
4. Increment room count when a new meeting starts before the earliest ending meeting ends

### ✅ **Code:**
```java
public static int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
        return 0;
    }
    
    int n = intervals.length;
    int[] startTimes = new int[n];
    int[] endTimes = new int[n];
    
    // Extract start and end times
    for (int i = 0; i < n; i++) {
        startTimes[i] = intervals[i][0];
        endTimes[i] = intervals[i][1];
    }
    
    // Sort start and end times
    Arrays.sort(startTimes);
    Arrays.sort(endTimes);
    
    int rooms = 0;
    int endIndex = 0;
    
    // Compare start and end times
    for (int i = 0; i < n; i++) {
        // If the current meeting starts before the earliest ending meeting ends
        if (startTimes[i] < endTimes[endIndex]) {
            // Need a new room
            rooms++;
        } else {
            // Can use the same room, move to the next earliest ending meeting
            endIndex++;
        }
    }
    
    return rooms;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — For sorting the arrays
- **Space:** O(n) — To store separated start and end times

---

## 📚 Key Takeaways

1. Always **sort intervals** by start time before processing them.
2. Use a **greedy approach** to merge intervals as you process them.
3. Consider **separate arrays** for start and end times in complex cases.
4. Time complexity is typically **O(n log n)** due to sorting.
5. The pattern is excellent for optimizing scheduling and resource allocation problems.

---

Next, lets dive deep into **inplace manipulation of linkedlists**.