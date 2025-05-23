# Subsets Pattern 🎯

## 📌 Introduction: The Power of Combinatorial Exploration

Imagine you’re picking outfits from your wardrobe. For each shirt, you can choose to wear it or not. The **Subsets Pattern** is your toolkit for generating all possible combinations (subsets) of a set—essential for solving problems involving combinations, powersets, and bitmasking!

### 🎬 Real-World Analogies:

1. **Wardrobe Choices** 👕
   ```
   Clothes: [Shirt][Pants][Hat]
   Subsets: [], [Shirt], [Pants], [Hat], [Shirt, Pants], [Shirt, Hat], [Pants, Hat], [Shirt, Pants, Hat]
   ```
2. **Light Switches** 💡
   ```
   Each switch (on/off) represents including/excluding an item.
   ```
3. **Pizza Toppings** 🍕
   ```
   Toppings: [Cheese][Pepperoni][Mushroom]
   All possible pizzas = all possible topping combinations.
   ```

The Subsets pattern is your secret weapon when you need:
- 📍 To generate all possible combinations or subsets
- 🔄 To solve powerset, combination, or bitmasking problems
- 🧩 To explore all possible states or configurations

### 🎯 Visual Example:
Generating all subsets of [1, 2, 3]:
```
Step 1: Start with []
Step 2: Add 1 → [1]
Step 3: Add 2 → [2], [1,2]
Step 4: Add 3 → [3], [1,3], [2,3], [1,2,3]
Result: [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
```

---

## 🧠 How to Recognize a Subsets Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "All Combinations" Clue** 📑
   - "Find all subsets/powersets/combinations"
   - "Generate all possible selections"

2. **The "Include/Exclude" Hint** ⚡
   - "For each element, choose to include or not"
   - "Explore all possible states"

3. **The "Bitmasking" Signal** 🧮
   - "Use binary representation to generate subsets"
   - "Iterate from 0 to 2^n - 1"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   How many elements in the set?
   Are there duplicates?
   Is order important?
   ```
2. **Content Questions:**
   ```
   Are empty subsets allowed?
   Are there constraints on subset size?
   ```
3. **Edge Case Questions:**
   ```
   What if the input is empty?
   What if all elements are the same?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Start with the empty subset
[ ]

Step 2: For each element, branch into two choices
[ ] (exclude), [1] (include)

Step 3: Repeat for all elements
[ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]

Step 4: Collect all subsets
Result: All possible combinations
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you generating all subsets or only those of a certain size?
- Are duplicates allowed?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned for an empty input?
- Are subsets required to be in a specific order?

### ✅ **3. Identify Base Cases**
- If the input is empty, return [[]].
- If only one element, return [[], [element]].

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function generateSubsets(nums):
    result = [[]]
    for num in nums:
        for subset in result.copy():
            result.append(subset + [num])
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int n = result.size();
            for (int i = 0; i < n; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        return result;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Input is empty
- Input has duplicates
- Large input size (exponential number of subsets)

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Generating all subsets | O(2^n * n)   | O(2^n * n)       |

**How to derive these complexities:**
- **Time:** There are 2^n subsets, each can be up to n elements long
- **Space:** All subsets are stored in the result

---

## 📚 Example 1: Easy Problem - Generate All Subsets

**Problem:**
Given a set of distinct integers, return all possible subsets (the powerset).

**Input:** [1,2,3]

**Expected Output:** [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]

### 🔑 **Solution Steps**
1. Start with the empty subset
2. For each number, add it to all existing subsets
3. Collect all new subsets

### ✅ **Code:**
```java
public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int n = result.size();
            for (int i = 0; i < n; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n)
- **Space:** O(2^n * n)

---

## 📚 Example 2: Medium Problem - Subsets with Duplicates

**Problem:**
Given a collection of integers that might contain duplicates, return all possible subsets (the powerset).

**Input:** [1,2,2]

**Expected Output:** [[], [1], [2], [1,2], [2,2], [1,2,2]]

### 🔑 **Solution Steps**
1. Sort the input to group duplicates
2. Track the start and end indices for new subsets
3. Only add new subsets for duplicates to avoid repeats

### ✅ **Code:**
```java
import java.util.*;

public class SubsetsWithDup {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int start = 0, end = 0;
        for (int i = 0; i < nums.length; i++) {
            start = 0;
            if (i > 0 && nums[i] == nums[i-1]) {
                start = end + 1;
            }
            end = result.size() - 1;
            int n = result.size();
            for (int j = start; j < n; j++) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                result.add(subset);
            }
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n)
- **Space:** O(2^n * n)

---

## 📚 Example 3: Hard Problem - Subsets of Fixed Size

**Problem:**
Given a set of distinct integers, return all possible subsets of size k.

**Input:** [1,2,3], k = 2

**Expected Output:** [[1,2], [1,3], [2,3]]

### 🔑 **Solution Steps**
1. Use backtracking to build subsets of size k
2. Add subset to result when size == k

### ✅ **Code:**
```java
import java.util.*;

public class SubsetsOfSizeK {
    public static List<List<Integer>> subsetsOfSizeK(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, k, new ArrayList<>(), result);
        return result;
    }
    private static void backtrack(int[] nums, int start, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, k, current, result);
            current.remove(current.size() - 1);
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(C(n, k) * k)
- **Space:** O(C(n, k) * k)

---

## 📚 Key Takeaways

1. The Subsets pattern is essential for generating all combinations, powersets, and exploring all possible states.
2. Time and space complexity are exponential—be mindful for large n.
3. Use iterative, backtracking, or bitmasking approaches depending on constraints.
4. Subsets are foundational for solving many combinatorial and search problems.

---

Next, let’s explore the **Backtracking Pattern** for solving problems that require exploring all possible paths or configurations!