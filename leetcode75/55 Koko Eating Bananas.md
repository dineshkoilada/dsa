# Koko Eating Bananas

## 📌 Problem Statement

**LeetCode Problem:** [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)  
**Difficulty:** Medium  

**Description:**
Koko loves to eat bananas. There are `n` piles of bananas, the `i`th pile has `piles[i]` bananas. The guards have gone and will come back in `h` hours.

Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses some pile of bananas and eats `k` bananas from that pile. If the pile has less than `k` bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer `k` such that she can eat all the bananas within `h` hours.

### **Example 1:**
**Input:** 
```
piles = [3,6,7,11], h = 8
```
**Output:** 
```
4
```
**Explanation:** 
Koko can eat 4 bananas per hour. She will spend:
- 1 hour eating 3 bananas from the first pile
- 2 hours eating 6 bananas from the second pile (4 + 2)
- 2 hours eating 7 bananas from the third pile (4 + 3)
- 3 hours eating 11 bananas from the fourth pile (4 + 4 + 3)
Total time: 1 + 2 + 2 + 3 = 8 hours, which is exactly the time we have.

### **Example 2:**
**Input:** 
```
piles = [30,11,23,4,20], h = 5
```
**Output:** 
```
30
```
**Explanation:** 
Koko can eat 30 bananas per hour. She will spend:
- 1 hour eating all 30 bananas from the first pile
- 1 hour eating all 11 bananas from the second pile
- 1 hour eating all 23 bananas from the third pile
- 1 hour eating all 4 bananas from the fourth pile
- 1 hour eating all 20 bananas from the fifth pile
Total time: 1 + 1 + 1 + 1 + 1 = 5 hours

### **Example 3:**
**Input:** 
```
piles = [30,11,23,4,20], h = 6
```
**Output:** 
```
23
```

### **Constraints:**
- `1 <= piles.length <= 10^4`
- `piles.length <= h <= 10^9`
- `1 <= piles[i] <= 10^9`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine Koko the monkey loves bananas! She has several piles of bananas and wants to eat them all before the guards come back in `h` hours.

Koko decides on a speed `k`, which means she can eat `k` bananas per hour. If there are fewer than `k` bananas in a pile, she eats the whole pile and then waits until the next hour to start a new pile.

We need to find the smallest speed `k` that allows Koko to eat all the bananas within `h` hours.

For example, if there are piles with [3, 6, 7, 11] bananas and the guards will be back in 8 hours, Koko needs to eat at a speed of at least 4 bananas per hour to finish in time.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What's the range of possible speeds for Koko?**
   - The minimum speed is 1 (eating 1 banana per hour).
   - The maximum speed would be the largest pile (because eating faster than that doesn't save any more time).
2. **How can we check if a given speed is sufficient?**
   - For each pile, we calculate how many hours it takes to eat it at speed `k`. Then we add up all the hours.
3. **How do we find the minimum speed efficiently?**
   - We can use binary search to find the minimum speed that allows Koko to finish in time.

👉 These considerations guide us towards a binary search approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define the range for binary search
```java
int left = 1; // Minimum speed
int right = getMaxPile(piles); // Maximum speed
```

### Step 2: Implement binary search to find minimum speed
```java
while (left < right) {
    int mid = left + (right - left) / 2; // Try this speed
    
    if (canEatAll(piles, mid, h)) {
        // If Koko can eat all bananas at this speed, try a lower speed
        right = mid;
    } else {
        // If not, try a higher speed
        left = mid + 1;
    }
}
```

### Step 3: Check if Koko can eat all bananas at a given speed
```java
private boolean canEatAll(int[] piles, int speed, int h) {
    int totalHours = 0;
    for (int pile : piles) {
        // Math.ceil(pile / (double) speed) gives the hours needed for this pile
        totalHours += (pile + speed - 1) / speed; // Ceiling division
    }
    return totalHours <= h;
}
```

---

## 🛠️ Writing the Linear Search Solution (Brute Force)

```java
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }
        
        // Try every possible speed from 1 to maxPile
        for (int speed = 1; speed <= maxPile; speed++) {
            if (canEatAll(piles, speed, h)) {
                return speed;
            }
        }
        
        // This line should never be reached given the constraints
        return maxPile;
    }
    
    private boolean canEatAll(int[] piles, int speed, int h) {
        int hoursNeeded = 0;
        for (int pile : piles) {
            // Ceiling division to calculate hours needed
            hoursNeeded += (pile + speed - 1) / speed;
        }
        return hoursNeeded <= h;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n * m)` where `n` is the number of piles and `m` is the maximum pile size. For each speed (1 to max pile), we check if Koko can eat all bananas.
- **Space Complexity:** `O(1)` as we only use a few variables.

---

## 🚀 Optimized Solution (Binary Search)

```java
public class KokoEatingBananasOptimized {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        
        // Find the maximum pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        // Binary search for the minimum eating speed
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canEatAll(piles, mid, h)) {
                // If Koko can eat all bananas at this speed, try a lower speed
                right = mid;
            } else {
                // If not, try a higher speed
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canEatAll(int[] piles, int speed, int h) {
        int hoursNeeded = 0;
        for (int pile : piles) {
            // Ceiling division to calculate hours needed for this pile
            hoursNeeded += (pile + speed - 1) / speed;
            
            // If we've already exceeded the time limit, return false
            if (hoursNeeded > h) {
                return false;
            }
        }
        return true;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n * log m)` where `n` is the number of piles and `m` is the maximum pile size. We perform binary search over the range of possible speeds (1 to max pile), and for each speed we check, we need O(n) time to check all piles.
- **Space Complexity:** `O(1)` as we only use a few variables.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "First, I analyzed the problem and realized we need to find the minimum eating speed that allows Koko to finish all bananas within the given time."
- "I observed that the minimum possible speed is 1 and the maximum needed speed is the size of the largest pile."
- "Since we want to minimize the speed, I decided to use binary search to efficiently find the answer."
- "For each potential speed, I check if Koko can eat all bananas within the time limit. If she can, I try a lower speed; if not, I try a higher speed."
- "To check if a given speed works, I calculate how many hours it would take to eat each pile at that speed, and then sum up all the hours. I use ceiling division because Koko has to spend a whole hour on a pile even if she doesn't eat the full `k` bananas."
- "This approach gives us a time complexity of O(n * log m), where n is the number of piles and m is the maximum pile size, which is much more efficient than checking each possible speed one by one."

---

## 🔥 Final Takeaways
- **Binary search can be used beyond sorted arrays.** Here, we're searching for a value (the minimum speed) within a range.
- **For optimization problems that involve minimizing or maximizing a value, consider binary search if the search space has a monotonic property.** In this case, if Koko can eat all bananas at speed k, she can definitely eat them at any speed greater than k.
- **Ceiling division is a common pattern in problems where we need to divide and round up.** We can implement it as `(a + b - 1) / b` for positive integers.
- **Always analyze the range of possible answers.** Here, the speed range is from 1 to the maximum pile size.
- **Early termination** in the `canEatAll` function can improve performance by stopping once we know the current speed is insufficient.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/koko-eating-bananas/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-sixth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.