# Subsets Pattern

## 🎯 Introduction

Imagine you have a set of items, and you need to explore all possible combinations or arrangements. The **Subsets Pattern** is a powerful technique for generating all possible subsets (power set) of a given set, with or without additional constraints.

The Subsets Pattern is particularly useful for:
- Generating all possible combinations of elements
- Finding all valid solutions to problems with multiple choices
- Solving problems related to permutations and combinations
- Exploring all possible states in a decision space
- Combinatorial optimization problems

This pattern works best when you need to **consider every possible subset** of a collection of items.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Does the problem involve finding all possible combinations or subsets?
   - Are there constraints on the subsets to be generated?
   - Do you need to track specific properties across subsets?

2. **Ask Clarifying Questions:**
   - Is the input set sorted or does order matter?
   - Are there duplicate elements in the input?
   - Are there size constraints on the subsets?
   - Should the output include the empty set?

3. **Identify Clues for Using Subsets Pattern:**
   - Problem asks for "all possible combinations"
   - Keywords like "subsets," "combinations," or "power set" appear
   - You need to make a sequence of inclusion/exclusion decisions

4. **Predicting if Subsets Pattern Is Applicable:**
   - The problem requires exploring all possibilities
   - The solution space grows exponentially with input size
   - Each element has a binary decision: include or exclude

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What are the input elements and their types?
- Are there any constraints on the output subsets?
- Should the output include empty set and/or full set?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How to handle duplicates if present?
- Does the order of elements matter?
- Are there any constraints on subset size?

### ✅ **3. Identify Base Cases**
- Empty input: Return a list containing only an empty subset
- Base case in recursion: When we've processed all elements

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function findSubsets(nums):
    initialize result with empty list
    add empty list to result (the empty subset)
    
    for each num in nums:
        make a copy of all existing subsets in result
        for each copy:
            add current num to it
            add modified copy to result
            
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class SubsetsGenerator {
    // Iterative approach
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start with empty subset
        result.add(new ArrayList<>());
        
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                // Create a new subset by adding current number to existing subset
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        
        return result;
    }
    
    // Recursive approach
    public static List<List<Integer>> findSubsetsRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset
        result.add(new ArrayList<>(current));
        
        // Explore further by adding each remaining element
        for (int i = index; i < nums.length; i++) {
            // Include current element
            current.add(nums[i]);
            // Recursively find subsets with current element included
            backtrack(nums, i + 1, current, result);
            // Exclude current element (backtrack)
            current.remove(current.size() - 1);
        }
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty set input
- Input with duplicate elements
- Very large input sets (consider the exponential growth of the output)
- Memory limitations when generating all subsets
- Requirements for specific ordering or filtering of the output

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Generating all subsets  | O(2^n * n)      | O(2^n * n)       |
| Backtracking (per state)| O(n)            | O(n)             |
| Overall                 | O(2^n * n)      | O(2^n * n)       |

**How to derive these complexities:**
- **Time Complexity:** O(2^n * n) because there are 2^n possible subsets, and it might take O(n) time to copy each subset (in the average case).
- **Space Complexity:** O(2^n * n) for storing all subsets. Each subset could have up to n elements.

---

## 📚 Example 1: Easy Problem - Find All Subsets

**Problem:**
Generate all possible subsets of a set of distinct integers.

**Input:**
```
[1, 2, 3]
```

**Expected Output:**
```
[
  [],
  [1],
  [2],
  [1, 2],
  [3],
  [1, 3],
  [2, 3],
  [1, 2, 3]
]
```

### 🔑 **Solution Steps**
1. Start with an empty subset
2. For each element in the input:
   - Create new subsets by adding the current element to each existing subset
   - Add these new subsets to the result
3. Return all subsets

### ✅ **Code:**
```java
public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>()); // Add empty subset
    
    for (int num : nums) {
        int size = result.size();
        for (int i = 0; i < size; i++) {
            List<Integer> subset = new ArrayList<>(result.get(i));
            subset.add(num);
            result.add(subset);
        }
    }
    
    return result;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n) — There are 2^n subsets, and each might need O(n) time to create
- **Space:** O(2^n * n) — To store all subsets

---

## 📚 Example 2: Medium Problem - Subsets with Duplicates

**Problem:**
Generate all possible subsets of a set of integers that might contain duplicates.

**Input:**
```
[1, 2, 2]
```

**Expected Output:**
```
[
  [],
  [1],
  [2],
  [1, 2],
  [2, 2],
  [1, 2, 2]
]
```

### 🔑 **Solution Steps**
1. Sort the input array to group duplicates
2. Use backtracking with a check to avoid duplicate subsets
3. Skip duplicate elements at the same level of recursion

### ✅ **Code:**
```java
public static List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums); // Sort to handle duplicates
    backtrackWithDuplicates(nums, 0, new ArrayList<>(), result);
    return result;
}

private static void backtrackWithDuplicates(int[] nums, int start, List<Integer> current, 
                                           List<List<Integer>> result) {
    result.add(new ArrayList<>(current));
    
    for (int i = start; i < nums.length; i++) {
        // Skip duplicates at the same level
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        
        // Include current element
        current.add(nums[i]);
        // Recursively find subsets starting from next position
        backtrackWithDuplicates(nums, i + 1, current, result);
        // Exclude current element (backtrack)
        current.remove(current.size() - 1);
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n) — In the worst case (all distinct elements)
- **Space:** O(2^n * n) — For storing all unique subsets

---

## 📚 Example 3: Hard Problem - Combination Sum

**Problem:**
Find all unique combinations of candidates where the candidate numbers sum to a target.
Each number in candidates may only be used once in the combination.

**Input:**
```
candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
```

**Expected Output:**
```
[
  [1, 1, 6],
  [1, 2, 5],
  [1, 7],
  [2, 6]
]
```

### 🔑 **Solution Steps**
1. Sort the input array to handle duplicates and enable pruning
2. Use backtracking to find combinations that sum to target
3. Skip duplicates at the same level to avoid duplicate combinations
4. Use a running sum to track progress toward the target

### ✅ **Code:**
```java
public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates); // Sort for handling duplicates
    backtrackCombinationSum(candidates, target, 0, new ArrayList<>(), result, 0);
    return result;
}

private static void backtrackCombinationSum(int[] candidates, int target, int start, 
                                           List<Integer> current, List<List<Integer>> result, 
                                           int currentSum) {
    if (currentSum == target) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    if (currentSum > target) {
        return; // Pruning: sum exceeds target
    }
    
    for (int i = start; i < candidates.length; i++) {
        // Skip duplicates at same level
        if (i > start && candidates[i] == candidates[i - 1]) {
            continue;
        }
        
        // Pruning: if adding this number exceeds target, all future numbers will too
        if (currentSum + candidates[i] > target) {
            break;
        }
        
        // Include current element
        current.add(candidates[i]);
        // Recursively find combinations with current sum + current element
        backtrackCombinationSum(candidates, target, i + 1, current, 
                               result, currentSum + candidates[i]);
        // Exclude current element (backtrack)
        current.remove(current.size() - 1);
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n) — In the worst case, we might need to consider all possible subsets
- **Space:** O(target) — For recursion stack and current combination (excluding the output)

---

## 📚 Key Takeaways

1. The Subsets pattern uses **backtracking or iteration** to systematically generate all possible subsets.
2. For problems with **duplicates**, sort the input array and skip duplicates at the same level of recursion.
3. Use **pruning techniques** to avoid exploring unnecessary paths when constraints are present.
4. The time and space complexity is typically **O(2^n * n)** due to the exponential number of subsets.
5. This pattern can be extended to solve various combinatorial problems by adding constraints.

---

Next, lets dive deep into **Cyclic Sort**.