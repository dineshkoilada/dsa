# Cyclic Sort Pattern

## 🎯 Introduction

Imagine you need to efficiently sort a range of numbers where each number falls within a specific range. The **Cyclic Sort** pattern is an elegant technique for sorting arrays containing numbers in a given range (typically 1 to n) with minimal operations.

The Cyclic Sort Pattern is particularly useful for:
- Sorting arrays containing numbers in a limited range (often 1 to n)
- Finding missing numbers in an array
- Finding duplicate numbers in an array
- Problems involving array rearrangement based on indices
- Problems asking for in-place sorting with O(n) time complexity

This pattern works best when the array contains numbers from a consecutive range, and the goal is to place each number at its correct index.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Does the array contain numbers in a specific range?
   - Are you asked to find missing or duplicate numbers?
   - Is in-place sorting a requirement?

2. **Ask Clarifying Questions:**
   - What is the range of numbers in the array?
   - Are there any duplicates in the array?
   - What's the expected behavior for edge cases (empty arrays, etc.)?

3. **Identify Clues for Using Cyclic Sort:**
   - The array contains numbers in a range from 1 to n or 0 to n-1
   - The problem involves finding missing or duplicate elements
   - In-place sorting is preferred or required
   - The problem mentions "minimal swaps" or "optimal sorting"

4. **Predicting if Cyclic Sort Is Applicable:**
   - If each number in the array should be at a specific position based on its value
   - If the problem can be solved by ensuring each number is at its "correct" index

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What is the range of numbers in the array?
- What should be the final arrangement after sorting?
- Are we dealing with 1-indexed or 0-indexed values?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How should edge cases like empty arrays be handled?
- What should be done with duplicate numbers if present?

### ✅ **3. Identify Base Cases**
- Empty array: Return as is
- Single element array: Already sorted

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function cyclicSort(nums):
    i = 0
    while i < length of nums:
        correct_position = nums[i] - 1  // For 1 to n range
        if nums[i] != nums[correct_position]:
            swap nums[i] and nums[correct_position]
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
            // Find the correct position (for numbers 1 to n)
            int correctIndex = nums[i] - 1;
            
            // If the number is not at its correct position, swap
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
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
- Numbers out of the expected range
- Duplicate elements in the array
- Empty or single-element arrays
- Arrays that are already sorted

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Iterating through array | O(n)            | O(1)             |
| Swapping elements       | O(1) per swap   | O(1)             |
| Overall                 | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** O(n) because each element will be placed in its correct position with at most n-1 swaps.
- **Space Complexity:** O(1) because the sorting is done in-place without additional data structures.

---

## 📚 Example 1: Easy Problem - Sort an Array from 1 to n

**Problem:**
Sort an array containing numbers from 1 to n where each number appears exactly once.

**Input:**
```
[3, 1, 5, 4, 2]
```

**Expected Output:**
```
[1, 2, 3, 4, 5]
```

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
        int correctIndex = nums[i] - 1;
        if (nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
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
- **Time:** O(n) — Each element is placed in its correct position with at most n-1 swaps
- **Space:** O(1) — In-place sorting without additional data structures

---

## 📚 Example 2: Medium Problem - Find the Missing Number

**Problem:**
Given an array containing n distinct numbers in the range [0, n], find the one number that is missing from the array.

**Input:**
```
[3, 0, 1]
```

**Expected Output:**
```
2
```

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
        // Skip numbers that are out of range or already in correct position
        if (nums[i] < nums.length && nums[i] != i) {
            swap(nums, i, nums[i]);
        } else {
            i++;
        }
    }
    
    // Find the first number missing from its index
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i) {
            return i;
        }
    }
    
    // If all numbers are in correct positions, the missing number is n
    return nums.length;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Sorting takes O(n) and the final iteration takes O(n)
- **Space:** O(1) — In-place operations

---

## 📚 Example 3: Hard Problem - Find All Duplicate Numbers

**Problem:**
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n], find all the integers that appear twice.

**Input:**
```
[4, 3, 2, 7, 8, 2, 3, 1]
```

**Expected Output:**
```
[2, 3]
```

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
        // Calculate the correct position (1-indexed)
        int correctIndex = nums[i] - 1;
        
        // If the element is not at its correct position, swap
        if (nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }
    
    // Find all elements that are not in their correct positions
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            duplicates.add(nums[i]);
        }
    }
    
    return duplicates;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Cyclic sort takes O(n) and the final iteration takes O(n)
- **Space:** O(1) — Excluding the output array, only constant extra space is used

---

## 📚 Key Takeaways

1. Cyclic Sort is ideal for arrays containing numbers in a **specific range** (typically 1 to n or 0 to n-1).
2. The pattern relies on the insight that each number should be at a **specific index** based on its value.
3. It achieves **O(n) time complexity** with **O(1) space complexity** by using in-place swaps.
4. This pattern is particularly effective for finding **missing numbers**, **duplicate numbers**, or **misplaced numbers** in arrays.
5. Always consider whether the input is **0-indexed** or **1-indexed** when implementing the pattern.

---

Next, lets dive deep into **Topological Sort**.