# Binary Search Pattern

## 🎯 Introduction

Imagine you have a dictionary, and you're searching for a word. You wouldn't flip through every page one by one—you'd open it near the middle, then decide whether to search in the first or second half. This is the essence of **Binary Search**: divide and conquer until you find the answer.

The **Binary Search Pattern** is particularly useful when:
- You need to search in a sorted array or list
- You want to minimize search time
- You need to find an element's position or verify its existence
- Solving optimization problems where the answer lies in a sorted space

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Are you searching for a specific element?
   - Is the array sorted?

2. **Ask Clarifying Questions:**
   - What should happen if the element is not found?
   - Is the array sorted in ascending or descending order?
   - Are there duplicates? If so, should you return the first or last occurrence?

3. **Identify Clues for Using Binary Search:**
   - The problem involves sorted data.
   - You're trying to find the smallest/largest element meeting a condition.

4. **Predicting if Binary Search Is Applicable:**
   - Does the problem involve sorted data?
   - Can the problem be reduced by half in each step?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you looking for a specific element?
- Do you need the first or last occurrence?
- Are you minimizing or maximizing a condition?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if the array is empty?
- Should duplicates be considered?

### ✅ **3. Identify Base Cases**
- If the array is empty, return -1 (element not found).

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function binarySearch(array, target):
    left = 0
    right = array.length - 1

    while left <= right:
        mid = left + (right - left) // 2
        if array[mid] == target:
            return mid
        else if array[mid] < target:
            left = mid + 1
        else:
            right = mid - 1

    return -1
```

### ✅ **5. Write the Code Skeleton**
```java
public class BinarySearchTemplate {
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The array is empty.
- The target is not present.
- The array contains duplicate elements.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Basic search            | O(log n)        | O(1)             |
| First/last occurrence   | O(log n)        | O(1)             |
| Search in rotated array | O(log n)        | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Each step eliminates half of the remaining elements.
- **Space Complexity:** No extra space is needed; only variables for left, right, and mid.

---

## 📚 Example 1: Easy Problem - Basic Binary Search

**Problem:**
Given a sorted array, find the index of a target number.

**Input:** `[1, 3, 5, 7, 9, 11]`, target = `5`

**Expected Output:** `2`

### 🔑 **Solution Steps**
1. Set `left` to 0 and `right` to the last index.
2. Calculate `mid` as `(left + right) / 2`.
3. If `mid` element equals the target, return `mid`.
4. If the target is greater, move `left` to `mid + 1`.
5. If the target is smaller, move `right` to `mid - 1`.

### ✅ **Code:**
```java
public class BasicBinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) — The search space halves at every step.
- **Space:** O(1) — Only a few variables are used.

---

## 📚 Example 2: Medium Problem - Find First and Last Position of Element in Sorted Array

**Problem:**
Given a sorted array, find the starting and ending position of a given target value.

**Input:** `[5, 7, 7, 8, 8, 10]`, target = `8`

**Expected Output:** `[3, 4]`

### 🔑 **Solution Steps**
1. Use binary search to find the first occurrence.
2. Use binary search again to find the last occurrence.

### ✅ **Code:**
```java
public class FindFirstLastPosition {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = findBound(nums, target, true);
        result[1] = findBound(nums, target, false);
        return result;
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, bound = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) — Each search takes logarithmic time.
- **Space:** O(1) — No extra memory required.

---

## 📚 Example 3: Hard Problem - Search in Rotated Sorted Array

**Problem:**
Search for a target value in a rotated sorted array.

**Input:** `[4,5,6,7,0,1,2]`, target = `0`

**Expected Output:** `4`

### 🔑 **Solution Steps**
1. Use binary search to find the rotation point.
2. Perform a binary search in the appropriate subarray.

### ✅ **Code:**
```java
public class RotatedSortedArraySearch {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(log n) — The array is still being divided in half each time.
- **Space:** O(1) — Only variables for pointers are used.

---

## 📚 Key Takeaways

1. Use binary search when dealing with sorted data or when the problem involves finding a specific value efficiently.
2. Each iteration eliminates half of the possible elements.
3. Variations of binary search can handle finding first/last occurrences, searching in rotated arrays, and solving optimization problems.
4. Binary search reduces time complexity from O(n) to O(log n) for search problems.

---

Next, let's dive into the **Depth-First Search Pattern** to handle problems involving graph traversal, trees, and backtracking!

