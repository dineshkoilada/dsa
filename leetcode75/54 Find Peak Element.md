# Find Peak Element

## 📌 Problem Statement

**LeetCode Problem:** [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/)  
**Difficulty:** Medium  

**Description:**
A peak element is an element that is strictly greater than its neighbors.

Given a **0-indexed** integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index to **any of the peaks**.

You may imagine that `nums[-1] = nums[n] = -∞`. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in `O(log n)` time.

### **Example 1:**
**Input:** 
```
nums = [1,2,3,1]
```
**Output:** 
```
2
```
**Explanation:** 
3 is a peak element and your function should return the index number 2.

### **Example 2:**
**Input:** 
```
nums = [1,2,1,3,5,6,4]
```
**Output:** 
```
5
```
**Explanation:** 
Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

### **Constraints:**
- `1 <= nums.length <= 1000`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `nums[i] != nums[i + 1]` for all valid `i`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a row of mountains with different heights. A peak is a mountain that is taller than the mountains right next to it. We want to find the position of any peak in this row of mountains.

Let's say we have mountains with heights [1, 2, 3, 1]. The mountain with height 3 is a peak because it's taller than both its neighbors (2 and 1).

In our problem, if a mountain is at the edge of the row, we only need to compare it with its one neighbor (because there's no mountain on the other side).

We need to find a peak efficiently, without checking every mountain one by one.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Is there always a peak in the array?**
   - Yes! Think about it: if the array is strictly increasing, the last element is a peak (because the element "outside" the array is considered negative infinity). If the array is strictly decreasing, the first element is a peak.
2. **Can we use the requirement for O(log n) time as a hint?**
   - O(log n) often suggests a binary search approach. Can we use binary search to find a peak?
3. **How can we decide which half of the array to search in?**
   - If we're at a position and the element to the right is bigger, then there must be a peak to the right. Why? Because either the array keeps increasing (making the last element a peak) or it eventually decreases (creating a peak).

👉 These insights lead us to a binary search approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Initialize binary search boundaries
```java
int left = 0;
int right = nums.length - 1;
```

### Step 2: Perform binary search
```java
while (left < right) {
    int mid = left + (right - left) / 2;
    
    if (nums[mid] > nums[mid + 1]) {
        // Peak is in the left half
        right = mid;
    } else {
        // Peak is in the right half
        left = mid + 1;
    }
}
```

### Step 3: Return the peak index
```java
return left; // At this point, left == right and we've found a peak
```

---

## 🛠️ Writing the Linear Search Solution (Brute Force)

```java
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean isPeak = true;
            
            // Check left neighbor (if it exists)
            if (i > 0 && nums[i] <= nums[i - 1]) {
                isPeak = false;
            }
            
            // Check right neighbor (if it exists)
            if (i < nums.length - 1 && nums[i] <= nums[i + 1]) {
                isPeak = false;
            }
            
            if (isPeak) {
                return i;
            }
        }
        
        // We'll never reach here because there will always be a peak
        return -1;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` as we check each element in the worst case.
- **Space Complexity:** `O(1)` as we only use a few variables.

---

## 🚀 Optimized Solution (Binary Search)

```java
public class FindPeakElementOptimized {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                // Peak is in the left half, including mid
                right = mid;
            } else {
                // Peak is in the right half, excluding mid
                left = mid + 1;
            }
        }
        
        // At this point, left == right and we've found a peak
        return left;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(log n)` as we divide the search space in half with each step.
- **Space Complexity:** `O(1)` as we only use a few variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I noticed that we needed an O(log n) solution, which immediately made me think of binary search."
- "First, I realized that a peak must always exist in the array. If the array is increasing, the last element is a peak. If it's decreasing, the first element is a peak."
- "I then came up with a binary search approach where at each step, I check if the mid element is greater than its right neighbor."
- "If it is, then a peak must exist in the left half of the array (including mid). Why? Because either mid itself is a peak or there's another peak to the left of mid."
- "If not, then a peak must exist in the right half of the array. Why? Because either the array keeps increasing (making the rightmost element a peak) or it eventually decreases (creating a peak)."
- "By consistently narrowing down the search space in half, we achieve the required O(log n) time complexity."

---

## 🔥 Final Takeaways
- **Binary search isn't just for finding a specific value in a sorted array.** It can be used whenever we can eliminate half of the search space based on certain conditions.
- **The key insight is recognizing how to decide which half to search.** If the mid element is greater than its right neighbor, a peak exists in the left half. Otherwise, a peak exists in the right half.
- **Remember that a peak always exists in an array with different adjacent elements.** This guarantee allows us to confidently narrow down our search.
- **The problem constraints (nums[i] != nums[i+1]) simplify our task.** Without this, we'd need to handle plateaus (where multiple adjacent elements have the same value).
- **Edge cases are handled implicitly by the binary search approach.** For a single element array, it's automatically a peak.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/find-peak-element/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-fifth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.