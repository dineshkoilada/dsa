# Two Pointers Pattern

## 🎯 Introduction

Imagine you’re reading a book using both hands. One hand starts from the beginning, and the other starts from the end. You flip through the pages until both hands meet at a specific spot. This is the idea behind the **Two Pointers Pattern**—you use two markers that move toward each other (or sometimes in the same direction) to solve problems more efficiently.

This pattern is particularly useful when working with:
- Sorted arrays or lists
- Searching for pairs that meet a condition
- Reversing or rearranging elements
- Removing duplicates from sorted arrays

---

## 🧠 How to Start Thinking About Solving the Problem

Before diving into code, take a moment to understand the problem fully. Here’s a guide for approaching any problem:

1. **Understand the Problem:**
   - What is being asked?
   - What is the expected output?
   - What is the format of the input?

2. **Ask Clarifying Questions:**
   - Are duplicates allowed?
   - Is the array sorted?
   - What should happen if there’s no valid output?

3. **Identify Clues for Using Two Pointers:**
   - The problem mentions a sorted array or list.
   - You need to find pairs, subarrays, or reverse elements.
   - You're comparing elements from opposite ends.

4. **Predicting if Two Pointers Is Applicable:**
   - Does the problem involve scanning from both ends?
   - Can moving two markers help find the solution more efficiently than nested loops?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
Ask yourself:
- What are you looking for? (A pair, a sum, etc.)
- Should the pointers move toward each other or in the same direction?
- What conditions should move the pointers?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should the function return if the array is empty?
- How should duplicate elements be treated?
- Should we stop when the pointers meet?

### ✅ **3. Identify Base Cases**
- If the array is empty or has only one element, return immediately (no valid pair exists).
- If the pointers cross each other, stop the loop.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveProblem(array):
    if array is empty or has only one element:
        return "No solution available"

    left = 0
    right = array.length - 1

    while left < right:
        if condition_met(array[left], array[right]):
            return [array[left], array[right]]
        else if need_bigger_value:
            left++
        else:
            right--

    return "No valid pair found"
```

### ✅ **5. Write the Code Skeleton**
```java
public class TwoPointersTemplate {
    public static void twoPointersExample(int[] array, int target) {
        if (array == null || array.length < 2) {
            System.out.println("No valid pair found.");
            return;
        }

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int sum = array[left] + array[right];

            if (sum == target) {
                System.out.println("Pair found: " + array[left] + ", " + array[right]);
                return;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println("No valid pair found.");
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The array is empty.
- The array contains negative numbers.
- No valid pair exists.
- The array has duplicates.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                | Time Complexity | Space Complexity |
|--------------------------|-----------------|------------------|
| Traversing the array     | O(n)            | O(1)             |
| Finding pairs or triplets| O(n)            | O(1)             |
| Sorting the array        | O(n log n)      | O(log n)         |

**How to derive these complexities:**
- **Time Complexity:** The loop runs at most `n` times since, with each iteration, either the `left` or `right` pointer moves closer to the other.
- **Space Complexity:** We use a constant amount of memory—only two variables (`left` and `right`), no additional data structures.

---

## 📚 Example 1: Easy Problem - Find a Pair That Sums to Target

**Problem:**
Given a sorted array of integers, find two numbers that add up to a specific target.

**Input:** `[2, 7, 11, 15]`, target = 9

**Expected Output:** `[2, 7]`

### 🔑 **Solution Steps**
1. **Base Case:**
   - If the array is empty or has fewer than two elements, return immediately.
2. **Initialize Pointers:**
   - `left` pointer starts at index 0.
   - `right` pointer starts at the last index.
3. **Move Pointers:**
   - If the sum equals the target, return the pair.
   - If the sum is smaller than the target, move `left` forward.
   - If the sum is greater than the target, move `right` backward.

### ✅ **Code:**
```java
public class TwoSumSortedArray {
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — The loop runs at most `n` times since the two pointers converge toward the center.
- **Space:** O(1) — No additional data structures are used; only two pointers.

---

## 📚 Example 2: Medium Problem - Reverse a String In-Place

**Problem:**
Given a character array, reverse the elements in place.

**Input:** `['h', 'e', 'l', 'l', 'o']`

**Expected Output:** `['o', 'l', 'l', 'e', 'h']`

### 🔑 **Solution Steps**
1. Initialize two pointers, one at the start and one at the end.
2. Swap the characters at both pointers.
3. Move the pointers inward until they meet.

### ✅ **Code:**
```java
public class ReverseString {
    public static void reverse(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each element is visited once.
- **Space:** O(1) — The reversal happens in place with no additional space.

**Why:** Every swap moves the pointers inward and runs for half the length of the array, which is still O(n).

---

## 📚 Example 3: Hard Problem - Container With Most Water

**Problem:**
Given an array of heights, find two lines that together with the x-axis form a container that holds the most water.

**Input:** `[1,8,6,2,5,4,8,3,7]`

**Expected Output:** `49`

### 🔑 **Solution Steps**
1. Use two pointers at the start and end of the array.
2. Calculate the area between the two lines.
3. Move the shorter line inward to find a larger area.

### ✅ **Code:**
```java
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — The loop makes at most `n` iterations since each step brings the two pointers closer together.
- **Space:** O(1) — No extra space is used aside from variables.

**Why:**
- The algorithm checks each possible container size by moving one pointer at a time and never revisits any index.

---

## 📚 Key Takeaways

1. Always **define the problem** and ask **clarifying questions** before coding.
2. Establish clear **base cases** and verify all edge conditions.
3. Use two pointers when dealing with sorted arrays and pairwise comparisons.
4. This pattern significantly reduces time complexity compared to nested loops.
5. Predict the **time and space complexity** logically by analyzing how many times each operation runs and whether additional memory is used.

---

Next, let's dive into the **Sliding Window Pattern** to tackle problems involving subarrays and substrings!