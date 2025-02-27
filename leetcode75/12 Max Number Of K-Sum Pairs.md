# Max Number of K-Sum Pairs

## 📌 Problem Statement

**LeetCode Problem:** [1679. Max Number of K-Sum Pairs](https://leetcode.com/problems/max-number-of-k-sum-pairs/)  
**Difficulty:** Medium  

**Description:**
You are given an integer array `nums` and an integer `k`. Find the **maximum number of pairs** such that the sum of each pair equals `k`.

Each number **can only be used once** in a pair.

### **Example 1:**
**Input:** 
```
nums = [1,2,3,4], k = 5
```
**Output:** 
```
2
```

### **Example 2:**
**Input:** 
```
nums = [3,1,3,4,3], k = 6
```
**Output:** 
```
1
```

### **Constraints:**
- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^9`
- `1 <= k <= 10^9`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **bag of numbers** and a **target sum `k`**. You need to **find as many pairs** as possible where **two numbers add up to `k`**, but you can only use each number **once**.

For example:
- **[1,2,3,4]**, with `k = 5` → **2 pairs** (`(1,4)` and `(2,3)`).
- **[3,1,3,4,3]**, with `k = 6` → **1 pair** (`(3,3)`).

Our goal is to **find the maximum number of pairs**.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if no two numbers add up to `k`?**
   - The answer is `0` because no pairs exist.
2. **What if we have more numbers than needed?**
   - We should pick the best pairs first.
3. **Can we sort the array or use a hashmap to speed things up?**
   - Yes! Sorting or using a hashmap helps us find pairs faster.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Sort the Array and Use Two Pointers
```java
Arrays.sort(nums);
int left = 0, right = nums.length - 1;
int pairs = 0;
```

### Step 2: Move Pointers to Find Valid Pairs
```java
while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == k) {
        pairs++;
        left++;
        right--;
    } else if (sum < k) {
        left++;
    } else {
        right--;
    }
}
```

### Step 3: Return the Maximum Pairs Found
```java
return pairs;
```

---

## 🛠️ Writing the Brute Force Solution (O(N^2) Time Complexity) 🚨

```java
import java.util.*;

public class MaxKSumPairsBruteForce {
    public static int maxOperations(int[] nums, int k) {
        int pairs = 0;
        boolean[] used = new boolean[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (!used[i] && !used[j] && nums[i] + nums[j] == k) {
                    pairs++;
                    used[i] = used[j] = true;
                    break;
                }
            }
        }
        return pairs;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N^2)`, since we check every pair.
- **Space Complexity:** `O(N)`, due to the `used` array.

🚨 **This approach is too slow for large inputs!**

---

## 🚀 Optimized Solution Using HashMap (O(N) Time, O(N) Space)

```java
import java.util.*;

public class MaxKSumPairsOptimized {
    public static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int pairs = 0;
        
        for (int num : nums) {
            int complement = k - num;
            if (freq.getOrDefault(complement, 0) > 0) {
                pairs++;
                freq.put(complement, freq.get(complement) - 1);
            } else {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }
        
        return pairs;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since we iterate through `nums` once.
- **Space Complexity:** `O(N)`, since we store numbers in a hashmap.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **hashmap** to store the count of numbers."
- "For each number, we check if its complement (`k - num`) exists in the hashmap."
- "If it does, we form a pair and decrease the count; otherwise, we store the number."
- "This ensures we find pairs efficiently in `O(N)`."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute-force approach checks every pair, but it's too slow (`O(N^2)`)."
- "Sorting and using two pointers works, but takes `O(N log N)`."
- "A **hashmap solution** is optimal for `O(N)` time complexity."

---

## 🔥 Final Takeaways
- **Use a hashmap for fast pair lookup.**
- **A two-pointer approach works but requires sorting (`O(N log N)`).**
- **Brute-force (`O(N^2)`) is too slow for large inputs.**
- **Think about edge cases: No valid pairs, duplicates, and large numbers.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/max-number-of-k-sum-pairs/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **thirteenth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

