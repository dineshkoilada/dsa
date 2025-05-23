# Cyclic Sort Pattern 🎯

## 📌 Introduction: The Power of In-Place Rearrangement

Imagine you’re organizing numbered tickets at a movie theater, and each ticket should be placed in its matching slot. The **Cyclic Sort Pattern** is your go-to tool for efficiently sorting arrays containing numbers in a specific range (often 1 to n or 0 to n-1) with minimal swaps and in-place operations!

### 🎬 Real-World Analogies:

1. **Ticket Booth Sorting** 🎟️
   ```
   Tickets: [3][1][5][4][2]
   Slots:   [1][2][3][4][5]
   Place each ticket in its correct slot with minimal moves.
   ```
2. **Locker Assignment** 🏫
   ```
   Each student has a locker number; swap until everyone is in their correct locker.
   ```
3. **Parking Lot Shuffle** 🚗
   ```
   Cars are parked in random spots; move each car to its assigned spot efficiently.
   ```

The Cyclic Sort pattern is your secret weapon when you need:
- 📍 To sort arrays with numbers in a known range
- 🔄 To find missing or duplicate numbers
- 🧩 To rearrange elements in-place with O(n) time and O(1) space

### 🎯 Visual Example:
Sorting [3, 1, 5, 4, 2]:
```
Step 1: [3, 1, 5, 4, 2]  (3 is not at index 2, swap with 5)
Step 2: [5, 1, 3, 4, 2]  (5 is not at index 4, swap with 2)
Step 3: [2, 1, 3, 4, 5]  (2 is not at index 1, swap with 1)
Step 4: [1, 2, 3, 4, 5]  (sorted!)
```

---

## 🧠 How to Recognize a Cyclic Sort Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Range Constraint" Clue** 📑
   - "Array contains numbers from 1 to n" or "0 to n-1"
   - "Find missing/duplicate/misplaced numbers"

2. **The "In-Place/Minimal Swaps" Hint** ⚡
   - "Sort with O(1) extra space"
   - "Rearrange elements efficiently"

3. **The "Correct Index" Signal** 🎯
   - "Each number should be at index (number - 1)"
   - "Place elements at their correct positions"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   What is the range of numbers?
   Are there duplicates or missing numbers?
   Is the array 0-indexed or 1-indexed?
   ```
2. **Content Questions:**
   ```
   Should the result be sorted in-place?
   What should be done with out-of-range numbers?
   ```
3. **Edge Case Questions:**
   ```
   What if the array is empty or has one element?
   What if numbers are already sorted?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Start at index 0
[🟦][⬜][⬜][⬜][⬜]  👈 Check if number is at correct index

Step 2: If not, swap with correct index
[⬜][🟦][⬜][⬜][⬜]  👈 Place number at its correct position

Step 3: Repeat for all indices
[⬜][⬜][🟦][⬜][⬜]  👈 Continue until all numbers are placed

Step 4: Array is sorted or ready for missing/duplicate checks
[🟦][🟦][🟦][🟦][🟦]  👈 All numbers in correct slots
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What is the range of numbers?
- What is the expected final arrangement?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How should edge cases be handled?
- What about duplicates or out-of-range values?

### ✅ **3. Identify Base Cases**
- Empty array: return as is
- Single element: already sorted

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function cyclicSort(nums):
    i = 0
    while i < length of nums:
        correct = nums[i] - 1
        if nums[i] != nums[correct]:
            swap(nums, i, correct)
        else:
            i++
    return nums
```

### ✅ **5. Write the Code Skeleton**
```java
public class CyclicSort {
    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct);
            } else {
                i++;
            }
        }
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Numbers out of range
- Duplicates
- Empty or single-element arrays
- Already sorted arrays

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Iteration/Swaps   | O(n)            | O(1)             |
| Overall           | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time:** Each element is swapped at most once to its correct position
- **Space:** Sorting is done in-place

---

## 📚 Example 1: Easy Problem - Sort an Array from 1 to n

**Problem:**
Sort an array containing numbers from 1 to n where each number appears exactly once.

**Input:**
[3, 1, 5, 4, 2]

**Expected Output:**
[1, 2, 3, 4, 5]

### 🔑 **Solution Steps**
1. Iterate through the array
2. For each number, calculate its correct index (number - 1)
3. If the number is not at its correct index, swap it
4. Continue until all elements are in correct positions

### ✅ **Code:**
```java
public static void cyclicSort(int[] nums) {
    int i = 0;
    while (i < nums.length) {
        int correct = nums[i] - 1;
        if (nums[i] != nums[correct]) {
            swap(nums, i, correct);
        } else {
            i++;
        }
    }
}
private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(1)

---

## 📚 Example 2: Medium Problem - Find the Missing Number

**Problem:**
Given an array containing n distinct numbers in the range [0, n], find the one number that is missing from the array.

**Input:**
[3, 0, 1]

**Expected Output:**
2

### 🔑 **Solution Steps**
1. Use cyclic sort to place each number at its correct index
2. After sorting, iterate through the array
3. The first index i where nums[i] != i is the missing number
4. If no mismatch is found, the missing number is n

### ✅ **Code:**
```java
public static int findMissingNumber(int[] nums) {
    int i = 0;
    while (i < nums.length) {
        if (nums[i] < nums.length && nums[i] != i) {
            swap(nums, i, nums[i]);
        } else {
            i++;
        }
    }
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i) {
            return i;
        }
    }
    return nums.length;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(1)

---

## 📚 Example 3: Hard Problem - Find All Duplicate Numbers

**Problem:**
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n], find all the integers that appear twice.

**Input:**
[4, 3, 2, 7, 8, 2, 3, 1]

**Expected Output:**
[2, 3]

### 🔑 **Solution Steps**
1. Apply the cyclic sort pattern to place numbers at their correct indices
2. For duplicate numbers, only one can be placed at the correct index
3. After sorting, iterate through the array
4. If nums[i] != i + 1, then nums[i] is a duplicate

### ✅ **Code:**
```java
public static List<Integer> findDuplicates(int[] nums) {
    List<Integer> duplicates = new ArrayList<>();
    int i = 0;
    while (i < nums.length) {
        int correct = nums[i] - 1;
        if (nums[i] != nums[correct]) {
            swap(nums, i, correct);
        } else {
            i++;
        }
    }
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            duplicates.add(nums[i]);
        }
    }
    return duplicates;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(1) (excluding output)

---

## 📚 Key Takeaways

1. Cyclic Sort is ideal for arrays containing numbers in a **specific range** (typically 1 to n or 0 to n-1).
2. The pattern relies on the insight that each number should be at a **specific index** based on its value.
3. It achieves **O(n) time complexity** with **O(1) space complexity** by using in-place swaps.
4. This pattern is particularly effective for finding **missing numbers**, **duplicate numbers**, or **misplaced numbers** in arrays.
5. Always consider whether the input is **0-indexed** or **1-indexed** when implementing the pattern.

---

Next, let’s explore the **Topological Sort Pattern** for solving problems involving dependencies and directed graphs!