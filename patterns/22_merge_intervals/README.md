# Merge Intervals Pattern 🧩

## 📌 Introduction: The Power of Combining Ranges

Imagine you’re scheduling meetings and want to combine overlapping time slots into a single block. The **Merge Intervals** pattern helps you efficiently combine overlapping or adjacent intervals—like tidying up a messy calendar!

### 🎬 Real-World Analogies:

1. **Meeting Scheduler** 📅
   - Merge overlapping meetings into one continuous block.
2. **Road Construction** 🚧
   - Combine adjacent roadwork zones to minimize disruptions.
3. **DNA Sequencing** 🧬
   - Merge overlapping gene segments to reconstruct the full sequence.

The Merge Intervals pattern is your go-to for:
- 🗓️ Combining overlapping or adjacent intervals
- 📊 Data compression and range simplification
- 🏗️ Scheduling and resource allocation

---

## 🧠 How to Recognize a Merge Intervals Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Overlapping Ranges" Clue**
   - "Merge all overlapping intervals"
2. **The "Interval List" Hint**
   - Input is a list of intervals or ranges
3. **The "Simplification" Signal**
   - The goal is to reduce or combine intervals

### 🤔 Essential Questions to Ask:
- Are intervals sorted or unsorted?
- Should adjacent intervals be merged?
- What should happen with fully contained intervals?

---

## 🎨 Visual Problem-Solving Framework

### Merge Intervals Step-by-Step:
```
Intervals: [1,3] [2,6] [8,10] [15,18]

Sort by start:
[1,3] [2,6] [8,10] [15,18]

Step 1: Compare [1,3] and [2,6] → Overlap → Merge to [1,6]
Step 2: Compare [1,6] and [8,10] → No overlap → Keep both
Step 3: Compare [8,10] and [15,18] → No overlap → Keep both

Result: [1,6] [8,10] [15,18]
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you merging overlapping or adjacent intervals?
- Is the input sorted?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen with fully contained intervals?
- Should adjacent intervals be merged?

### ✅ **3. Identify Base Cases**
- No intervals or only one interval: return as is.
- All intervals are non-overlapping: return as is.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function mergeIntervals(intervals):
    if intervals is empty or has one interval:
        return intervals
    sort intervals by start
    merged = []
    for interval in intervals:
        if merged is empty or merged[-1].end < interval.start:
            merged.append(interval)
        else:
            merged[-1].end = max(merged[-1].end, interval.end)
    return merged
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                merged.add(current);
                current = intervals[i];
            }
        }
        merged.add(current);
        return merged.toArray(new int[merged.size()][]);
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty input
- Intervals that are fully contained within others
- Adjacent intervals

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Sorting           | O(n log n)      | O(n)             |
| Merging           | O(n)            | O(n)             |
| Total             | O(n log n)      | O(n)             |

**How to derive these complexities:**
- **Time Complexity:** Sorting dominates, followed by a single pass to merge.
- **Space Complexity:** Output list may store all intervals in the worst case.

---

## 📚 Example 1: Easy Problem - Merge Overlapping Intervals

**Problem:**
Given a collection of intervals, merge all overlapping intervals.

**Input:** `[[1,3],[2,6],[8,10],[15,18]]`

**Expected Output:** `[[1,6],[8,10],[15,18]]`

### 🔑 **Solution Steps**
1. Sort intervals by start.
2. Iterate and merge overlapping intervals.

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Sorting dominates.
- **Space:** O(n) — Output list.

---

## 📚 Example 2: Medium Problem - Insert and Merge Interval

**Problem:**
Insert a new interval into a list of non-overlapping intervals and merge if necessary.

**Input:** `intervals = [[1,3],[6,9]], newInterval = [2,5]`

**Expected Output:** `[[1,5],[6,9]]`

### 🔑 **Solution Steps**
1. Add the new interval and sort.
2. Merge as in the basic problem.

### ✅ **Code:**
```java
public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> all = new ArrayList<>(Arrays.asList(intervals));
        all.add(newInterval);
        all.sort(Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (current[1] >= all.get(i)[0]) {
                current[1] = Math.max(current[1], all.get(i)[1]);
            } else {
                merged.add(current);
                current = all.get(i);
            }
        }
        merged.add(current);
        return merged.toArray(new int[merged.size()][]);
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Sorting and merging.
- **Space:** O(n) — Output list.

---

## 📚 Example 3: Hard Problem - Employee Free Time

**Problem:**
Given a schedule for multiple employees, find the free time intervals common to all.

**Input:** `[[[1,2],[5,6]],[[1,3]],[[4,10]]]`

**Expected Output:** `[[3,4]]`

### 🔑 **Solution Steps**
1. Flatten all intervals into a single list.
2. Merge overlapping intervals.
3. Find gaps between merged intervals.

### ✅ **Code:**
```java
public class EmployeeFreeTime {
    public static List<int[]> employeeFreeTime(List<List<int[]>> schedule) {
        List<int[]> all = new ArrayList<>();
        for (List<int[]> emp : schedule) all.addAll(emp);
        all.sort(Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (current[1] >= all.get(i)[0]) {
                current[1] = Math.max(current[1], all.get(i)[1]);
            } else {
                merged.add(current);
                current = all.get(i);
            }
        }
        merged.add(current);
        List<int[]> free = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            if (merged.get(i-1)[1] < merged.get(i)[0]) {
                free.add(new int[]{merged.get(i-1)[1], merged.get(i)[0]});
            }
        }
        return free;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) — Sorting and merging.
- **Space:** O(n) — Output list.

---

## 📚 Key Takeaways

1. Use Merge Intervals for problems involving overlapping or adjacent ranges.
2. Always sort intervals before merging.
3. Time complexity is O(n log n) due to sorting, with a single pass for merging.
4. Consider edge cases like empty input, fully contained, or adjacent intervals.

---

Next, let's explore the **Cyclic Sort Pattern** for efficiently sorting and placing elements in their correct positions!