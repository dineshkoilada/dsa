# Bit Manipulation Pattern

## 🎯 Introduction

Imagine turning switches on and off, where each switch corresponds to a bit in a number. Bits are the building blocks of data, and manipulating them efficiently can solve problems in clever and optimized ways. The **Bit Manipulation Pattern** uses operations directly on binary representations of numbers to perform tasks efficiently.

Bit Manipulation is particularly useful for:
- Checking or flipping individual bits
- Counting set bits (ones) in a number
- Finding unique elements in arrays
- Generating subsets using bit masks
- Efficient mathematical computations

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Can the problem be solved by representing elements in binary form?
   - Do you need to track or count specific bits?

2. **Ask Clarifying Questions:**
   - Are you looking for a unique number or pair?
   - Is the operation related to even/odd checks, powers of two, or binary representations?

3. **Identify Clues for Using Bit Manipulation:**
   - The problem involves checking, toggling, or counting specific elements.
   - Constraints suggest the need for constant-time operations.
   - Subset generation or parity checks are involved.

4. **Predicting if Bit Manipulation Is Applicable:**
   - Does the problem require space-efficient operations?
   - Can the solution be optimized by using binary operations?

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you dealing with individual bits?
- Are you performing mathematical operations that can be optimized?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Are you looking for unique elements?
- Are constraints on memory usage important?

### ✅ **3. Identify Base Cases**
- An empty input should return zero.
- Check conditions for zero or negative numbers.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function bitManipulation(input):
    initialize result
    for each element in input:
        perform bitwise operation
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
public class BitManipulation {
    public static int performBitOperation(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // Example: XOR operation
        }
        return result;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Input contains duplicates.
- Input size is zero.
- Large input values.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                  | Time Complexity | Space Complexity |
|----------------------------|-----------------|------------------|
| Checking a bit            | O(1)            | O(1)             |
| Setting/Clearing a bit    | O(1)            | O(1)             |
| Counting set bits         | O(log n)        | O(1)             |
| Generating all subsets    | O(2^n)          | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Bitwise operations are constant-time.
- **Space Complexity:** Bitwise manipulations don't require additional space.

---

## 📚 Example 1: Easy Problem - Check if a Number Is Power of Two

**Problem:**
Determine if a number is a power of two.

**Input:** `n = 16`

**Expected Output:** `true`

### 🔑 **Solution Steps**
1. A number `n` is a power of two if it has only one bit set.
2. Use the condition `n > 0 && (n & (n - 1)) == 0`.

### ✅ **Code:**
```java
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(1) — Bitwise operation.
- **Space:** O(1) — No extra memory used.

---

## 📚 Example 2: Medium Problem - Single Number

**Problem:**
Find the element that appears only once in an array where every other element appears twice.

**Input:** `[4, 1, 2, 1, 2]`

**Expected Output:** `4`

### 🔑 **Solution Steps**
1. Use XOR operation to cancel out pairs.
2. The result will be the number that appears once.

### ✅ **Code:**
```java
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Iterate through the array once.
- **Space:** O(1) — Only one variable for the result.

---

## 📚 Example 3: Hard Problem - Subsets Generation

**Problem:**
Generate all possible subsets of a given set.

**Input:** `[1, 2, 3]`

**Expected Output:** `[[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]`

### 🔑 **Solution Steps**
1. Each subset can be represented by a binary number.
2. For each number from `0` to `2^n - 1`, generate a subset using the bits set.

### ✅ **Code:**
```java
import java.util.*;

public class SubsetsWithBits {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int total = 1 << nums.length; // 2^n subsets
        for (int i = 0; i < total; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(2^n * n) — Generating all subsets.
- **Space:** O(2^n) — Storing all subsets.

---

## 📚 Key Takeaways

1. Use Bit Manipulation for problems involving binary representations and efficient space usage.
2. Common operations:
   - **Set a bit:** `n | (1 << k)`
   - **Clear a bit:** `n & ~(1 << k)`
   - **Toggle a bit:** `n ^ (1 << k)`
   - **Check a bit:** `(n & (1 << k)) != 0`
3. Useful for subset generation, checking power of two, and finding unique elements.
4. Bitwise operations are highly efficient with constant-time complexity.

---

Next, let's dive into the **Trie Prefix Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

