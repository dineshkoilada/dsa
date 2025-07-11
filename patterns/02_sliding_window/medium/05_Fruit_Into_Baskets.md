# Fruit Into Baskets

## Problem Statement

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array `fruits` where `fruits[i]` is the type of fruit the `i`th tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

- You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
- Starting from any tree, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
- Once you reach a tree with fruit that cannot fit in your baskets, you must stop.

Given the integer array `fruits`, return the maximum number of fruits you can pick.

## Examples

**Example 1:**
```
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
```

**Example 2:**
```
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
```

**Example 3:**
```
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
```

## Approach: Sliding Window with HashMap

This problem can be rephrased as finding the longest subarray that contains at most 2 distinct fruit types. This is a classic application of the sliding window pattern with a variable-size window.

### Algorithm:

1. Use a HashMap to keep track of the frequency of each fruit type in the current window
2. Expand the window to the right as long as we have at most 2 distinct fruit types
3. When we encounter a third fruit type, shrink the window from the left until we have only 2 types
4. Keep track of the maximum window size seen so far

## Code Solution

```java
import java.util.*;

public class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        
        // Use a map to track fruit types and their counts
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int maxFruits = 0;
        int left = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to the window
            fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);
            
            // Shrink window until we have at most 2 fruit types
            while (fruitCount.size() > 2) {
                int leftFruit = fruits[left];
                fruitCount.put(leftFruit, fruitCount.get(leftFruit) - 1);
                
                if (fruitCount.get(leftFruit) == 0) {
                    fruitCount.remove(leftFruit);
                }
                
                left++;
            }
            
            // Update maximum fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}
```

## Optimized Approach: Using Two Pointers and Variables

Since we're limited to exactly 2 fruit types, we can optimize the solution using two variables instead of a HashMap:

```java
public class FruitIntoBasketsOptimized {
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        
        int lastFruit = -1;
        int secondLastFruit = -1;
        int lastFruitCount = 0;
        int maxFruits = 0;
        int currentMax = 0;
        
        for (int fruit : fruits) {
            // If current fruit is one of the two types we're tracking
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currentMax++;
            } else {
                // We have a new fruit type, reset currentMax to include the last run of lastFruit + current fruit
                currentMax = lastFruitCount + 1;
            }
            
            // Update lastFruitCount
            if (fruit == lastFruit) {
                lastFruitCount++;
            } else {
                lastFruitCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }
            
            maxFruits = Math.max(maxFruits, currentMax);
        }
        
        return maxFruits;
    }
}
```

## Complexity Analysis

- **HashMap Approach**:
  - **Time Complexity**: O(n) where n is the number of trees. We process each tree exactly once.
  - **Space Complexity**: O(1) since the HashMap will never contain more than 3 entries (when we're about to shrink the window).

- **Optimized Approach**:
  - **Time Complexity**: O(n) where n is the number of trees.
  - **Space Complexity**: O(1) as we only use a constant amount of variables.

## Key Insights

1. This problem is a classic example of finding the longest subarray with at most K distinct elements (where K = 2).
2. Using a sliding window approach allows us to efficiently track the valid subarrays without generating all possibilities.
3. The HashMap approach is more general and can be extended to handle any number of distinct elements.
4. The optimized approach leverages the specific constraint of having exactly 2 baskets to reduce space complexity.
5. This problem demonstrates how real-world scenarios can be converted into sliding window pattern problems.
