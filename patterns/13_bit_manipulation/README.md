# Bit Manipulation Pattern 🎯

## 📌 Introduction: The Power of Binary

Imagine a row of light switches, each representing a bit in a number. Flipping, checking, or combining these switches lets you control data at the most fundamental level. The **Bit Manipulation Pattern** is your toolkit for solving problems by operating directly on the binary representations of numbers—fast, memory-efficient, and sometimes surprisingly elegant!

### 🎬 Real-World Analogies:

1. **Light Switches** 💡
   ```
   Number: 13 (1101)
   Switches: [ON][ON][OFF][ON]
   Flip, check, or combine switches to change the number.
   ```
2. **Safe Combination Locks** 🔒
   ```
   Each dial (bit) can be set independently to unlock a pattern.
   ```
3. **Team Selection with Badges** 🏅
   ```
   Each badge (bit) represents a skill. Combine badges to form teams with specific skills.
   ```

Bit manipulation is your secret weapon when you need:
- 📍 To check, set, clear, or toggle individual bits
- 🔄 To count set bits (ones) or trailing zeros
- 🧩 To find unique elements or pairs in arrays
- 🧮 To generate all subsets (bitmasking)
- 🚀 To optimize space and time for mathematical or combinatorial problems

### 🎯 Visual Example:
Checking if a number is a power of two:
```
Number: 8 (1000)
8 & (8 - 1): 1000 & 0111 = 0000 → Power of two!
```

---

## 🧠 How to Recognize a Bit Manipulation Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Binary/Bitwise" Clue** 🧮
   - "Check if a number is a power of two"
   - "Count the number of 1s in binary representation"
   - "Find the unique element in an array"
   - "Generate all possible subsets"

2. **The "Constant-Time Operation" Hint** ⚡
   - "Optimize for speed or space"
   - "No extra data structures allowed"

3. **The "Masking/Toggle" Signal** 🎭
   - "Set, clear, or toggle specific bits"
   - "Use bitwise AND, OR, XOR, NOT"

### 🤔 Essential Questions to Ask:

1. **Bitwise Operation Questions:**
   ```
   Which bitwise operation is most suitable?
   ├── AND (&): Masking bits
   ├── OR (|): Setting bits
   ├── XOR (^): Toggling or finding unique
   └── NOT (~): Flipping bits
   ```
2. **Content Questions:**
   ```
   What does each bit represent?
   ├── Flags
   ├── Subsets
   ├── Parity (even/odd)
   └── Unique identifiers
   ```
3. **Edge Case Questions:**
   ```
   What if the input is zero or negative?
   Are there duplicate or missing bits?
   Is the input size very large?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Represent Data in Binary
[1][0][1][1][0][0][1][0]  👈 Visualize bits

Step 2: Apply Bitwise Operation
[1][0][1][1][0][0][1][0]
AND/OR/XOR/NOT

Step 3: Interpret Result
[0][0][1][0][0][0][1][0]  👈 Extract answer
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you working with individual bits or binary representations?
- Is there a mathematical or combinatorial optimization possible?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen for zero or negative inputs?
- Are there constraints on memory or speed?

### ✅ **3. Identify Base Cases**
- If the input is empty or zero, return a default value.
- If the operation is not applicable, handle gracefully.

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
- Input contains duplicates or negatives.
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
- **Time Complexity:** Bitwise operations are constant-time; subset generation is exponential.
- **Space Complexity:** Bitwise manipulations are space-efficient.

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

1. Use Bit Manipulation for problems involving binary representations, flags, and efficient space usage.
2. Common operations:
   - **Set a bit:** `n | (1 << k)`
   - **Clear a bit:** `n & ~(1 << k)`
   - **Toggle a bit:** `n ^ (1 << k)`
   - **Check a bit:** `(n & (1 << k)) != 0`
3. Useful for subset generation, checking power of two, and finding unique elements.
4. Bitwise operations are highly efficient with constant-time complexity.

---

Next, let's dive into the **Trie Prefix Pattern** for solving problems that involve searching and matching prefixes efficiently!

