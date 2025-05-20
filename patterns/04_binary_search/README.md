# Binary Search Pattern 🔍

## 📌 Introduction: The Power of Divide and Conquer

Imagine you have a dictionary, and you're searching for a word. You wouldn't flip through every page one by one—you'd open it near the middle, then decide whether to search in the first or second half. This is the essence of **Binary Search**: divide and conquer until you find the answer.

### 🎬 Real-World Analogies:

1. **Dictionary Lookup** 📖
   - Open the book in the middle, check if the word is before or after, and repeat.
2. **Guess the Number Game** 🎲
   - "Is it higher or lower?" Each guess halves the possible range.
3. **Finding a Song on a Playlist** 🎵
   - Jump to the middle, then to the quarter, then to the eighth, until you find the song.

The binary search technique is your secret weapon when working with:
- 📚 Sorted arrays or lists
- 🏷️ Searching for a specific value or verifying existence
- 🏆 Finding first/last occurrence of an element
- 🧩 Solving optimization problems in a sorted space

### 🎯 Visual Example:
Finding a target in a sorted array:
```
Array:   [1] [3] [5] [7] [9] [11]
Target:  5
Step 1:  Check middle (5) → Found!
```

---

## 🧠 How to Recognize a Binary Search Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Sorted" Clue** 📑
   - Words like "sorted," "ordered," or "monotonic"
   - Example: "Find the index of a target in a sorted array"
2. **The "Halving" Hint** ➗
   - Each step can eliminate half the search space
   - Example: "Find the smallest/largest value meeting a condition"
3. **The "Optimization" Signal** 🎯
   - "Minimize/maximize" or "find the boundary/first/last occurrence"

### 🤔 Essential Questions to Ask:

1. **Search Type Questions:**
   - Are you searching for a specific value, or a boundary (first/last occurrence)?
   - Is the array sorted in ascending or descending order?
2. **Content Questions:**
   - Are there duplicates? Should you return the first or last occurrence?
3. **Edge Case Questions:**
   - What if the array is empty?
   - What should happen if the element is not found?

### 🎨 Visual Problem-Solving Framework:
Legend:  
🟦 = left pointer, 🟥 = right pointer, 🟨 = mid pointer, ⬜ = other elements

```
Step 1: Initialize Pointers
[🟦][⬜][⬜][⬜][⬜][🟥]   // left at start, right at end

Step 2: Check Middle
[🟦][⬜][🟨][⬜][⬜][🟥]   // mid = (left + right) / 2

Step 3: Compare and Narrow
If target < array[mid]:
    [🟦][⬜][🟨][🟥][⬜][⬜]   // Move right pointer left to mid - 1
If target > array[mid]:
    [⬜][🟦][⬜][🟨][⬜][🟥]   // Move left pointer right to mid + 1

Step 4: Repeat 2-3
[⬜][🟦][⬜][🟨][🟥][⬜]   // Pointers move closer, repeat until found or crossed
```
- At each step, the search space halves, making the process efficient.
- Continue until the target is found or left > right (search space exhausted).

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you looking for a specific element, or a boundary (first/last occurrence)?
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

Next, let’s dive into the **Depth-First Search Pattern** to handle problems involving graph traversal, trees, and backtracking!

