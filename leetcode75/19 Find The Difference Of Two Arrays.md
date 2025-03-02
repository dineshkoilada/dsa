# Find the Difference of Two Arrays

## 📌 Problem Statement

**LeetCode Problem:** [2215. Find the Difference of Two Arrays](https://leetcode.com/problems/find-the-difference-of-two-arrays/)  
**Difficulty:** Easy  

**Description:**  
Given two integer arrays `nums1` and `nums2`, return a list `answer` of size `2` where:
- `answer[0]` is a list of **distinct** integers in `nums1` which are **not** in `nums2`.
- `answer[1]` is a list of **distinct** integers in `nums2` which are **not** in `nums1`.

### **Example 1:**
**Input:**  
```
nums1 = [1,2,3], nums2 = [2,4,6]
```
**Output:**  
```
[[1,3],[4,6]]
```

### **Example 2:**
**Input:**  
```
nums1 = [1,2,3,3], nums2 = [1,1,2,2]
```
**Output:**  
```
[[3], []]
```

### **Constraints:**
- `1 <= nums1.length, nums2.length <= 1000`
- `-1000 <= nums1[i], nums2[i] <= 1000`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you and your friend each have a bag of marbles. Some marbles are the same, but some are unique to each of you. Our job is to find:
- The marbles **you have but your friend doesn’t**.
- The marbles **your friend has but you don’t**.

For example:
- **nums1 = [1,2,3]**, **nums2 = [2,4,6]** → You have `[1,3]` your friend doesn’t, and your friend has `[4,6]` you don’t.
- **nums1 = [1,2,3,3]**, **nums2 = [1,1,2,2]** → You have `[3]`, and your friend has nothing new.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Can there be duplicate numbers?**
   - Yes, but we only care about **distinct** values.
2. **How do we efficiently check if a number is in another list?**
   - We can use a **set** for quick lookups.
3. **Can we do this in one pass?**
   - Yes, using sets!

👉 We need a solution that runs in **O(N)** and uses **O(N) space**.

---

## 🏗️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
import java.util.*;

public class DifferenceOfTwoArraysBruteForce {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();
        
        for (int num : nums1) {
            boolean found = false;
            for (int n : nums2) {
                if (num == n) {
                    found = true;
                    break;
                }
            }
            if (!found && !diff1.contains(num)) {
                diff1.add(num);
            }
        }
        
        for (int num : nums2) {
            boolean found = false;
            for (int n : nums1) {
                if (num == n) {
                    found = true;
                    break;
                }
            }
            if (!found && !diff2.contains(num)) {
                diff2.add(num);
            }
        }
        
        return Arrays.asList(diff1, diff2);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check every number against every other number.
- **Space Complexity:** `O(N)`, for storing the output lists.

🚨 **This is too slow for large inputs!**

---

## 🚀 Optimized Solution Using Sets (O(N) Time, O(N) Space)

```java
import java.util.*;

public class DifferenceOfTwoArraysOptimized {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        
        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();
        
        for (int num : set1) {
            if (!set2.contains(num)) {
                diff1.add(num);
            }
        }
        
        for (int num : set2) {
            if (!set1.contains(num)) {
                diff2.add(num);
            }
        }
        
        return Arrays.asList(diff1, diff2);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through each array once.
- **Space Complexity:** `O(N)`, for storing unique values.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use **two sets** to store unique elements from each array."
- "We then iterate through the sets and check which numbers are missing in the other set."
- "This ensures an **O(N) solution with O(N) space complexity**."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every number (`O(N^2)`) but is too slow."
- "Sorting both arrays first and using two pointers is `O(N log N)`, but not optimal."
- "Using **sets for lookups** is the best approach (`O(N)`)."

---

## 🔥 Final Takeaways
- **Use sets to efficiently check for missing elements.**
- **Think about duplicates—sets automatically remove them!**
- **Optimize brute force solutions using data structures like HashSet.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/find-the-difference-of-two-arrays/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **twentieth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

