# Maximum Subsequence Score

## 📌 Problem Statement

**LeetCode Problem:** [2542. Maximum Subsequence Score](https://leetcode.com/problems/maximum-subsequence-score/)  
**Difficulty:** Medium  

**Description:**
You are given two 0-indexed integer arrays `nums1` and `nums2` of equal length `n` and a positive integer `k`. You must choose a subsequence of indices from `nums1` of length `k`.

For chosen indices `i0`, `i1`, ..., `ik-1`, your score is defined as:

- The sum of the selected elements from `nums1` multiplied by the minimum of the selected elements from `nums2`.

Return the maximum possible score.

A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.

### **Example 1:**
**Input:** 
```
nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
```
**Output:** 
```
12
```
**Explanation:** 
The four possible subsequence scores are:
- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
Therefore, we return the max score, which is 12.

### **Example 2:**
**Input:** 
```
nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
```
**Output:** 
```
30
```
**Explanation:** 
Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.

### **Constraints:**
- `n == nums1.length == nums2.length`
- `1 <= n <= 105`
- `0 <= nums1[i], nums2[i] <= 105`
- `1 <= k <= n`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have two rows of boxes. The first row has numbers representing how much candy each box has. The second row has numbers representing how fresh the candy is (higher number = fresher).

You need to pick `k` boxes and calculate your score like this:
1. Add up all the candy from your chosen boxes
2. Find the least fresh candy among your choices
3. Multiply these two numbers

You want to get the highest possible score!

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What if we just pick the boxes with the most candy?**
   - That might not be optimal if those boxes have very low freshness scores.
2. **What if we just pick the freshest boxes?**
   - That might not be optimal if those boxes have very little candy.
3. **What's our strategy then?**
   - We need to balance the total amount of candy with the minimum freshness.

👉 These are important considerations that help us develop an effective approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Pair each element from nums1 with its corresponding element in nums2
```java
Pair<Integer, Integer>[] pairs = new Pair[n];
for (int i = 0; i < n; i++) {
    pairs[i] = new Pair<>(nums1[i], nums2[i]);
}
```

### Step 2: Sort the pairs by nums2 values in decreasing order
```java
Arrays.sort(pairs, (a, b) -> b.getValue() - a.getValue());
```

### Step 3: Use a min-heap to track the k smallest nums1 values
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
int sum = 0;
```

### Step 4: Process the first k elements
```java
for (int i = 0; i < k; i++) {
    sum += pairs[i].getKey();
    minHeap.offer(pairs[i].getKey());
}
```

### Step 5: Calculate initial score and then try to improve it
```java
long maxScore = (long) sum * pairs[k-1].getValue();
```

---

## 🛠️ Writing the Solution

```java
import java.util.*;

public class MaximumSubsequenceScore {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        
        // Pair up the elements of nums1 and nums2
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        
        // Sort the pairs by nums2 values in decreasing order
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        
        // Use a min-heap to keep track of the k largest nums1 values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        
        // Process the first k elements
        for (int i = 0; i < k; i++) {
            sum += pairs[i][0];
            minHeap.offer(pairs[i][0]);
        }
        
        // Calculate initial score
        long maxScore = sum * pairs[k-1][1];
        
        // Try to improve the score by considering remaining elements
        for (int i = k; i < n; i++) {
            // If the current nums1 value is larger than the minimum in our heap
            if (pairs[i][0] > minHeap.peek()) {
                sum -= minHeap.poll();  // Remove the smallest element
                sum += pairs[i][0];     // Add the new element
                minHeap.offer(pairs[i][0]);
                
                // Update max score if we found a better one
                maxScore = Math.max(maxScore, sum * pairs[i][1]);
            }
        }
        
        return maxScore;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n log n)` for sorting the pairs, plus `O(n log k)` for heap operations.
- **Space Complexity:** `O(n)` for storing the pairs and `O(k)` for the heap.

---

## 🚀 Alternative Implementation

```java
import java.util.*;

public class MaximumSubsequenceScoreAlternative {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices by nums2 values in decreasing order
        Arrays.sort(indices, (i, j) -> nums2[j] - nums2[i]);
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        
        for (int i = 0; i < k; i++) {
            int idx = indices[i];
            sum += nums1[idx];
            minHeap.offer(nums1[idx]);
        }
        
        long maxScore = sum * nums2[indices[k-1]];
        
        for (int i = k; i < n; i++) {
            int idx = indices[i];
            if (nums1[idx] > minHeap.peek()) {
                sum -= minHeap.poll();
                sum += nums1[idx];
                minHeap.offer(nums1[idx]);
                maxScore = Math.max(maxScore, sum * nums2[idx]);
            }
        }
        
        return maxScore;
    }
}
```

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I realized that the minimum value from `nums2` acts as a bottleneck for the score."
- "My approach sorts the elements by their `nums2` values in descending order, so we consider elements with higher `nums2` values first."
- "For each potential minimum `nums2` value, I find the `k` largest elements from `nums1` that have `nums2` values greater than or equal to that minimum."
- "I use a min-heap to efficiently maintain the `k` largest elements from `nums1` as we iterate."
- "The key insight is that by sorting by `nums2` values, we know the current minimum `nums2` value at each step."

---

## 🔥 Final Takeaways
- **The greedy approach with heap works well for this type of problem.**
- **Sorting by the bottleneck value (minimum in this case) is a common technique.**
- **Using a min-heap to maintain the k largest elements is more efficient than sorting at each step.**
- **Be careful about integer overflow - that's why we use long for the sum and result.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/maximum-subsequence-score/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is problem **fifty-one problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.