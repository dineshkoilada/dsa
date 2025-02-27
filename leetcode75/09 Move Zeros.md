# Move Zeros

## 📌 Problem Statement

**LeetCode Problem:** [283. Move Zeros](https://leetcode.com/problems/move-zeroes/)  
**Difficulty:** Easy  

**Description:**
Given an integer array `nums`, move all `0`'s to the **end** while maintaining the **relative order** of the **non-zero** elements. 

Modify `nums` **in-place** without making a copy.

### **Example 1:**
**Input:** 
```
nums = [0,1,0,3,12]
```
**Output:** 
```
[1,3,12,0,0]
```

### **Example 2:**
**Input:** 
```
nums = [0]
```
**Output:** 
```
[0]
```

### **Constraints:**
- `1 <= nums.length <= 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **row of toy blocks** with some **empty spaces (0s)** in between. Your goal is to **move all empty spaces** to the end while keeping the other blocks in the same order.

For example:
- **[0,1,0,3,12]** → **[1,3,12,0,0]**
- **[0,0,1]** → **[1,0,0]**

We need to **do this in-place**, meaning we **cannot use extra space** for a new array.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there are no zeros?**
   - The array remains unchanged.
2. **What if all elements are zeros?**
   - The array remains unchanged.
3. **Can we swap elements instead of using extra space?**
   - Yes, using a two-pointer approach!

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Use Two Pointers
```java
int left = 0;
```

### Step 2: Iterate Through the Array
```java
for (int right = 0; right < nums.length; right++) {
```

### Step 3: Swap Non-Zero Elements to the Left
```java
if (nums[right] != 0) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
    left++;
}
```

### Step 4: The array is now modified in place!
```java
return;
```

---

## 🛠️ Writing the Brute Force Solution (Not Allowed Due to Extra Space) 🚨

```java
import java.util.*;

public class MoveZerosBruteForce {
    public static int[] moveZeros(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num != 0) {
                list.add(num);
            }
        }
        while (list.size() < nums.length) {
            list.add(0);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the array once.
- **Space Complexity:** `O(N)`, since we use an extra list.

🚨 **This approach is not allowed because it uses extra space!**

---

## 🚀 Optimized Solution Using Two Pointers (O(N) Time, O(1) Space)

```java
public class MoveZerosOptimized {
    public static void moveZeroes(int[] nums) {
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through the array once.
- **Space Complexity:** `O(1)`, since we modify the array in-place.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **two-pointer technique** to move non-zero elements forward."
- "As we find a non-zero element, we **swap it with the leftmost zero**."
- "This ensures all zeros are moved to the end while keeping the order."

If the interviewer asks for **alternative approaches**, you can say:
- "A naive approach creates a new array, but that uses extra space."
- "The two-pointer approach is optimal as it runs in `O(N)` and modifies the array in-place."

---

## 🔥 Final Takeaways
- **Use a two-pointer approach to swap non-zero elements forward.**
- **Modify the array in-place instead of creating a new one.**
- **Think about edge cases: arrays with no zeros or all zeros.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/move-zeroes/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **tenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.