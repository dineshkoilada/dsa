# Successful Pairs of Spells and Potions

## 📌 Problem Statement

**LeetCode Problem:** [2300. Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/)  
**Difficulty:** Medium  

**Description:**
You are given two positive integer arrays `spells` and `potions`, of length `n` and `m` respectively, where `spells[i]` represents the strength of the `i`th spell and `potions[j]` represents the strength of the `j`th potion.

You are also given an integer `success`. A spell and potion pair is considered **successful** if the **product** of their strengths is **at least** `success`.

Return an integer array `pairs` of length `n` where `pairs[i]` is the number of **potions** that will form a successful pair with the `i`th spell.

### **Example 1:**
**Input:** 
```
spells = [5,1,3], potions = [1,2,3,4,5], success = 7
```
**Output:** 
```
[4,0,3]
```
**Explanation:** 
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.

### **Example 2:**
**Input:** 
```
spells = [3,1,2], potions = [8,5,8], success = 16
```
**Output:** 
```
[2,0,2]
```
**Explanation:** 
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful. 
Thus, [2,0,2] is returned.

### **Constraints:**
- `n == spells.length`
- `m == potions.length`
- `1 <= n, m <= 10^5`
- `1 <= spells[i], potions[j] <= 10^5`
- `1 <= success <= 10^10`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're a wizard with different spells, and each spell has a certain strength. You also have potions, and each potion has its own strength. 

When you combine a spell with a potion, their strengths multiply. For example, if a spell has strength 5 and a potion has strength 3, then together they have a strength of 5 × 3 = 15.

You want to know, for each spell, how many potions can be combined with it to create a strength that's at least a certain number (let's call it the "success" number).

For example, if your first spell has strength 5, and you have potions with strengths [1, 2, 3, 4, 5], and your success number is 7:
- Spell (5) × Potion (1) = 5 (less than 7, not successful)
- Spell (5) × Potion (2) = 10 (more than 7, successful!)
- Spell (5) × Potion (3) = 15 (more than 7, successful!)
- Spell (5) × Potion (4) = 20 (more than 7, successful!)
- Spell (5) × Potion (5) = 25 (more than 7, successful!)

So, your first spell can be successfully combined with 4 potions.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **Is there a more efficient way than checking every spell with every potion?**
   - Can we sort the potions to make our task easier?
2. **Once we've sorted the potions, can we use binary search to find successful pairs?**
   - Given a spell of strength `s`, what's the minimum potion strength needed?
3. **How can we calculate the minimum potion strength needed for success?**
   - If `s` × `p` ≥ success`, then `p` ≥ `success / s`

👉 These considerations guide us towards an efficient binary search approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Sort the potions array for binary search
```java
Arrays.sort(potions);
```

### Step 2: For each spell, find how many potions form a successful pair
```java
int[] pairs = new int[spells.length];
for (int i = 0; i < spells.length; i++) {
    int spell = spells[i];
    int minPotion = (int) Math.ceil((double) success / spell);
    
    // Find the index of the first potion with strength >= minPotion
    int index = binarySearch(potions, minPotion);
    
    // Calculate how many potions are successful
    pairs[i] = potions.length - index;
}
```

### Step 3: Implement binary search to find the first potion with strength >= minPotion
```java
private int binarySearch(int[] potions, int target) {
    int left = 0;
    int right = potions.length;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (potions[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class SuccessfulPairsOfSpellsAndPotions {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                long product = (long) spells[i] * potions[j];
                if (product >= success) {
                    count++;
                }
            }
            pairs[i] = count;
        }
        
        return pairs;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n * m)` where `n` is the length of `spells` and `m` is the length of `potions`. We check each spell with each potion.
- **Space Complexity:** `O(n)` for the output array.

---

## 🚀 Optimized Solution (Using Binary Search)

```java
import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotionsOptimized {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        
        // Sort the potions array
        Arrays.sort(potions);
        
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            
            // Calculate minimum potion strength needed
            // Be careful with integer division, we need to ceil the result
            // to find the smallest potion that gives a successful pair
            long minPotionStrength = (success + spell - 1) / spell; // Ceiling division
            
            // If minPotionStrength exceeds the maximum potion strength, no successful pairs
            if (minPotionStrength > potions[m - 1]) {
                pairs[i] = 0;
                continue;
            }
            
            // If minPotionStrength is less than or equal to the minimum potion strength, all potions form successful pairs
            if (minPotionStrength <= potions[0]) {
                pairs[i] = m;
                continue;
            }
            
            // Binary search to find the first potion with strength >= minPotionStrength
            int index = binarySearch(potions, minPotionStrength);
            pairs[i] = m - index;
        }
        
        return pairs;
    }
    
    // Binary search to find the index of the first element >= target
    private int binarySearch(int[] arr, long target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(m * log(m) + n * log(m))` where `m * log(m)` is for sorting the potions and `n * log(m)` is for performing binary search for each spell.
- **Space Complexity:** `O(n)` for the output array.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I recognized that checking every spell against every potion would be inefficient, especially given the constraints of the problem."
- "My key insight was that if we sort the potions array, we can use binary search to efficiently find how many potions form a successful pair with each spell."
- "For a given spell strength `s`, a potion with strength `p` forms a successful pair if `s * p >= success`."
- "Rearranging, we get `p >= success / s`. So for each spell, I need to find how many potions have a strength at least `success / s`."
- "After sorting the potions array, I use binary search to find the index of the first potion with strength at least `success / s`. Then, the number of successful pairs is the total number of potions minus this index."
- "This approach is much more efficient, reducing the time complexity from O(n * m) to O(m * log(m) + n * log(m))."

---

## 🔥 Final Takeaways
- **Sorting followed by binary search is a powerful technique for problems that involve finding elements that meet a certain condition.**
- **Be careful with integer division and consider using ceiling division when needed.**
- **When dealing with potential integer overflow, use long data type for calculations.**
- **Consider edge cases where all or none of the potions form successful pairs with a spell.**
- **Binary search is particularly useful when searching for a threshold value in a sorted array.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-fourth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.