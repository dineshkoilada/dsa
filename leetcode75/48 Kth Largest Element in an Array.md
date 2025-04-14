# Kth Largest Element in an Array

## 📌 Problem Statement

**LeetCode Problem:** [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)  
**Difficulty:** Medium  

**Description:**  
Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array.

Note that it is the `kth` largest element in the sorted order, not the `kth` distinct element.

Can you solve it without sorting?

### **Example 1:**
**Input:** 
```
nums = [3,2,1,5,6,4], k = 2
```
**Output:** 
```
5
```

### **Example 2:**
**Input:** 
```
nums = [3,2,3,1,2,4,5,5,6], k = 4
```
**Output:** 
```
4
```

### **Constraints:**
- `1 <= k <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a group of numbers in no particular order. If you were to put them in order from biggest to smallest, the task is to find the "kth" biggest number.

For example, if you have the numbers [3,2,1,5,6,4] and k = 2, you would arrange them as [6,5,4,3,2,1] and then pick the 2nd number, which is 5.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What's the simplest way to find the kth largest element?**
   - We could **sort** the array and return the kth element from the end.
2. **Can we do better than sorting the entire array?**
   - Yes! We can use a **min-heap** of size k to keep track of only the k largest elements.
3. **What about using a faster selection algorithm?**
   - The **QuickSelect** algorithm can find the kth largest element in O(n) average time.

👉 There are multiple approaches, each with different time and space complexity tradeoffs!

---

## 🏗️ Breaking the Problem into Steps with Code

### Approach 1: Sorting (Simple but not optimal)
```java
Arrays.sort(nums);
return nums[nums.length - k];
```

### Approach 2: Using a Min Heap of size k
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

for (int num : nums) {
    minHeap.add(num);
    if (minHeap.size() > k) {
        minHeap.poll();  // Remove the smallest element if heap size exceeds k
    }
}

return minHeap.peek();  // The root is the kth largest element
```

### Approach 3: QuickSelect Algorithm (Most efficient)
```java
return quickSelect(nums, 0, nums.length - 1, nums.length - k);
```

---

## 🛠️ Writing the Solution using a Min Heap

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Create a min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Add elements to the heap, maintaining only k largest elements
        for (int num : nums) {
            minHeap.add(num);
            
            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // The root of the heap is the kth largest element
        return minHeap.peek();
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n log k)`, where n is the length of the array and k is the parameter. We perform n heap operations, each taking O(log k) time.
- **Space Complexity:** `O(k)` for storing the heap.

---

## 🚀 Optimized Solution using QuickSelect Algorithm

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Convert k to 0-indexed (finding the index of the kth largest element)
        int targetIndex = nums.length - k;
        
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }
    
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        // Base case: if the list contains only one element
        if (left == right) {
            return nums[left];
        }
        
        // Choose a pivot (here we use the rightmost element)
        int pivotIndex = partition(nums, left, right);
        
        if (pivotIndex == targetIndex) {
            // We've found the kth largest element
            return nums[pivotIndex];
        } else if (pivotIndex > targetIndex) {
            // The kth largest is on the left of the pivot
            return quickSelect(nums, left, pivotIndex - 1, targetIndex);
        } else {
            // The kth largest is on the right of the pivot
            return quickSelect(nums, pivotIndex + 1, right, targetIndex);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];  // Choose the rightmost element as pivot
        int i = left;  // Index of smaller element
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                // Swap nums[i] and nums[j]
                swap(nums, i, j);
                i++;
            }
        }
        
        // Swap nums[i] and nums[right] (put the pivot in its final sorted position)
        swap(nums, i, right);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` average case, where n is the length of the array. In the worst case, it could be O(n²), but the average performance is linear.
- **Space Complexity:** `O(log n)` average case for the recursion stack.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "I've considered three approaches to solve this problem: sorting, using a min-heap, and using the QuickSelect algorithm."

For the **heap-based approach**:
- "I maintain a min-heap of size k, which contains the k largest elements seen so far."
- "As I process each element, I add it to the heap and remove the smallest element if the heap size exceeds k."
- "After processing all elements, the smallest element in the heap (the root) is the kth largest element overall."
- "This approach has a time complexity of O(n log k), which is efficient for large arrays with small k."

For the **QuickSelect approach**:
- "The QuickSelect algorithm is based on the partitioning idea from QuickSort, but only explores one side of the partition."
- "I first determine the target index of the kth largest element in a sorted array, which is (length - k)."
- "In each step, I partition the array around a pivot and check if the pivot landed at the target index."
- "If it matches, I've found the answer. If not, I recursively search the appropriate side of the partition."
- "This gives an average time complexity of O(n), which is optimal for this problem."

If asked about **tradeoffs**:
- "Sorting is the simplest approach but has O(n log n) time complexity."
- "The heap-based solution is O(n log k), which is more efficient than sorting when k << n."
- "QuickSelect has O(n) average time complexity, making it the most efficient, but it has a worst case of O(n²) and modifies the input array."

---

## 🔥 Final Takeaways
- **Selection algorithms** like QuickSelect can find elements in specific positions without fully sorting.
- **Heap data structures** are excellent for maintaining a set of the k largest/smallest elements.
- **Consider space/time tradeoffs** - sometimes using extra space (like a heap) can improve time complexity.
- **Understand the difference between average and worst-case performance** - QuickSelect is O(n) on average but can be O(n²) in the worst case.
- **Be aware of whether you can modify the input array** - some approaches require in-place modifications.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/kth-largest-element-in-an-array/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **forty-ninth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.