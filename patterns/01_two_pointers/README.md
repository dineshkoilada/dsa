# Two Pointers Pattern ✌️

## 📌 Introduction: The Power of Dual Focus

Imagine you’re reading a book using both hands. One hand starts from the beginning, and the other starts from the end. You flip through the pages until both hands meet at a specific spot. This is the idea behind the **Two Pointers Pattern**—you use two markers that move toward each other (or sometimes in the same direction) to solve problems more efficiently.

### 🎬 Real-World Analogies:
1. **Book Reading** 📖
   - One hand at the start, one at the end, flipping pages toward the center.
2. **Race Track** 🏁
   - Two runners starting at opposite ends, running toward each other.
3. **Mirror Check** 🪞
   - Checking if a word is a palindrome by comparing letters from both ends.

The two pointers technique is your secret weapon when working with:
- 📍 Sorted arrays or lists
- 🔄 Searching for pairs that meet a condition
- 🔁 Reversing or rearranging elements
- 🗑️ Removing duplicates from sorted arrays

---

## 🧠 How to Recognize a Two Pointers Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Pairwise" Clue** 👥
   - Words like "pair," "triplet," or "subarray"
   - Example: "Find two numbers that add up to a target"
2. **The "Opposite Ends" Hint** ↔️
   - Need to scan from both ends or compare elements at different positions
   - Example: "Is the array a palindrome?"
3. **The "In-Place Manipulation" Signal** 🔄
   - Problems that require rearranging or modifying the array without extra space
   - Example: "Reverse the array in place"

### 🤔 Essential Questions to Ask:
1. **Pointer Movement Questions:**
   - Should pointers move toward each other or in the same direction?
   - What condition determines which pointer to move?
2. **Content Questions:**
   - What are we tracking? (sum, max area, duplicates, etc.)
3. **Edge Case Questions:**
   - What if the array is empty or has one element?
   - Are duplicates allowed?
   - What if no valid pair exists?

### 🎨 Visual Problem-Solving Framework:
```
Step 1: Initialize Pointers
[🟦][⬜][⬜][⬜][🟥]  👈 Start with both ends

Step 2: Compare/Process
[🟦][⬜][⬜][⬜][🟥]  👈 Check condition (sum, match, etc.)

Step 3: Move Pointers
[⬜][🟦][⬜][🟥][⬜]  👈 Move left/right pointer based on logic

Step 4: Repeat 2-3
[⬜][⬜][🟦][🟥][⬜]  👈 Until pointers meet or cross
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are we looking for a pair, sum, or in-place change?
- Should the pointers move toward each other or in the same direction?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should the function return if the array is empty?
- How should duplicate elements be treated?
- Should we stop when the pointers meet?

### ✅ **3. Identify Base Cases**
- If the array is empty or has only one element, return immediately (no valid pair exists).
- If the pointers cross each other, stop the loop.

### ✅ **4. Write Pseudo-Code for Base Cases**
```
function solveTwoPointers(array):
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

### 🔍 **Why Two Pointers Work Without Sorting**
While many two-pointer problems require sorted arrays, this problem is a special case where sorting isn't needed:

1. **Position-Dependent Calculation**:
   - The area formula depends on both height and width: `area = min(height[left], height[right]) * (right - left)`
   - Sorting would destroy the positional relationship needed for the width calculation
2. **Optimal Movement Strategy**:
   - Moving the pointer with the shorter height is always the optimal choice
   - This is because the width decreases with each step, so our only chance to increase area is to find a taller height
3. **Complete Coverage**:
   - Starting from the widest possible container and working inward guarantees we consider all potential maximum containers
4. **Handles Duplicates Naturally**:
   - Duplicate heights don't affect the algorithm's correctness
   - Each potential container is evaluated exactly once based on its position

This problem demonstrates that the two-pointer pattern can be effective even when the array is neither sorted nor contains unique elements, as long as there's a clear strategy for pointer movement that guarantees finding the optimal solution.

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — The loop makes at most `n` iterations since each step brings the two pointers closer together.
- **Space:** O(1) — No extra space is used aside from variables.

**Why:**
- The algorithm checks each possible container size by moving one pointer at a time and never revisits any index.

---

## 📚 Key Takeaways

1. Use two pointers for problems involving pairs, subarrays, or in-place modifications.
2. Optimize time complexity from O(n²) to O(n) by avoiding nested loops.
3. Manage pointers by moving them based on problem-specific logic (sum, match, area, etc.).
4. The pattern works for both sorted and unsorted arrays if you have a clear movement strategy.
5. Predict the **time and space complexity** logically by analyzing pointer movement and memory usage.

---

Next, let’s explore the **Sliding Window Pattern** to tackle problems involving subarrays and substrings!