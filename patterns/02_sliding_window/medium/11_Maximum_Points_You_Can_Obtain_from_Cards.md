# Maximum Points You Can Obtain from Cards

## Problem Statement

There are several cards arranged in a row, and each card has an associated number of points. The points are given in an integer array `cardPoints`.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly `k` cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array `cardPoints` and the integer `k`, return the maximum score you can obtain.

## Examples

**Example 1:**
```
Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After drawing 3 cards, your score is 1 + 6 + 5 = 12
```

**Example 2:**
```
Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
```

**Example 3:**
```
Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.
```

## Approach: Sliding Window from a Different Angle

This problem can be approached using a sliding window technique, but from a different angle. The key insight is that if we need to pick `k` cards from either end, then `n-k` cards will be left in the middle as a contiguous block (where `n` is the total number of cards).

Instead of focusing on which cards to pick, we can focus on which cards to leave out. Our goal becomes finding the subarray of length `n-k` with the minimum sum, then subtracting this from the total sum.

### Algorithm:

1. Calculate the total sum of all cards
2. Find the minimum subarray sum of length `n-k`
3. Return `totalSum - minSubarraySum`

## Code Solution

```java
public class MaximumPointsFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        
        // Calculate total sum of all cards
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // If k equals n, we take all cards
        if (k == n) {
            return totalSum;
        }
        
        // Find the window of size (n-k) with minimum sum
        int windowSize = n - k;
        int windowSum = 0;
        
        // Calculate sum of first window
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }
        
        int minWindowSum = windowSum;
        
        // Slide the window and update minimum window sum
        for (int i = windowSize; i < n; i++) {
            windowSum = windowSum + cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        
        // Maximum score = total sum - minimum window sum
        return totalSum - minWindowSum;
    }
}
```

## Alternative Direct Approach

We can also solve this problem more directly by considering the sum of cards taken from both ends:

```java
public class MaximumPointsFromCardsDirect {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        
        // Initialize prefix sum from the beginning
        int[] frontSum = new int[k + 1];
        for (int i = 0; i < k; i++) {
            frontSum[i + 1] = frontSum[i] + cardPoints[i];
        }
        
        // Initialize suffix sum from the end
        int[] backSum = new int[k + 1];
        for (int i = 0; i < k; i++) {
            backSum[i + 1] = backSum[i] + cardPoints[n - 1 - i];
        }
        
        // Find the maximum score by taking i cards from front and (k-i) from back
        int maxScore = 0;
        for (int i = 0; i <= k; i++) {
            int score = frontSum[i] + backSum[k - i];
            maxScore = Math.max(maxScore, score);
        }
        
        return maxScore;
    }
}
```

## Complexity Analysis

- **Sliding Window Approach**:
  - **Time Complexity**: O(n) where n is the number of cards. We iterate through the array at most twice.
  - **Space Complexity**: O(1) as we only use a constant amount of extra space.

- **Direct Approach**:
  - **Time Complexity**: O(k) where k is the number of cards to pick. We need to calculate the prefix and suffix sums up to k.
  - **Space Complexity**: O(k) for storing the prefix and suffix sums.

## Key Insights

1. This problem provides an interesting twist on the sliding window pattern by focusing on what to exclude rather than what to include.
2. By reframing the problem in terms of the minimum sum subarray to exclude, we convert it into a standard sliding window problem.
3. The alternative approach with prefix and suffix sums demonstrates how problems can often be solved in multiple ways.
4. This problem demonstrates the importance of considering the complement of what you're looking for in optimization problems.
5. The solution shows how a seemingly complex card game problem can be reduced to a simpler array manipulation problem.
