# Daily Temperatures

## 📌 Problem Statement

**LeetCode Problem:** [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)  
**Difficulty:** Medium  

**Description:**
Given an array of integers `temperatures` representing daily temperatures, return an array `answer` such that `answer[i]` is the number of days you have to wait after the `i`th day to get a warmer temperature. If there is no future day for which this is possible, keep `answer[i] == 0`.

### **Example 1:**
**Input:** 
```
temperatures = [73,74,75,71,69,72,76,73]
```
**Output:** 
```
[1,1,4,2,1,1,0,0]
```
**Explanation:** 
- For `temperatures[0] = 73`, the next warmer temperature is `temperatures[1] = 74`, which appears 1 day later. So `answer[0] = 1`.
- For `temperatures[1] = 74`, the next warmer temperature is `temperatures[2] = 75`, which appears 1 day later. So `answer[1] = 1`.
- For `temperatures[2] = 75`, the next warmer temperature is `temperatures[6] = 76`, which appears 4 days later. So `answer[2] = 4`.
- For `temperatures[3] = 71`, the next warmer temperature is `temperatures[5] = 72`, which appears 2 days later. So `answer[3] = 2`.
- For `temperatures[4] = 69`, the next warmer temperature is `temperatures[5] = 72`, which appears 1 day later. So `answer[4] = 1`.
- For `temperatures[5] = 72`, the next warmer temperature is `temperatures[6] = 76`, which appears 1 day later. So `answer[5] = 1`.
- For `temperatures[6] = 76`, there is no warmer temperature in the future. So `answer[6] = 0`.
- For `temperatures[7] = 73`, there is no warmer temperature in the future. So `answer[7] = 0`.

### **Example 2:**
**Input:** 
```
temperatures = [30,40,50,60]
```
**Output:** 
```
[1,1,1,0]
```
**Explanation:**
- Each day gets warmer than the previous one except for the last day.

### **Example 3:**
**Input:** 
```
temperatures = [30,60,90]
```
**Output:** 
```
[1,1,0]
```

### **Constraints:**
- `1 <= temperatures.length <= 10^5`
- `30 <= temperatures[i] <= 100`

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're tracking the weather every day. For each day, you want to know: "How many days do I have to wait until it gets warmer than today?"

For example, if the temperatures for a week are [73, 74, 75, 71, 69, 72, 76, 73]:
- On day 1, it's 73°F. The next day it's 74°F, which is warmer, so you wait 1 day.
- On day 2, it's 74°F. The next day it's 75°F, which is warmer, so you wait 1 day.
- On day 3, it's 75°F. You have to wait until day 7 when it's 76°F, which is 4 days later.

And so on for each day. If there's no warmer day in the future, the answer is 0.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How can we avoid checking every future day for each temperature?**
   - A stack could help us process temperatures more efficiently.
2. **Can we solve this problem in reverse?**
   - Yes, we could work backwards from the end, which provides another approach.
3. **What information do we need to track?**
   - We need to track both the temperature values and their positions (days).

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Approach 1: Using a Stack (Monotonic Stack)

The key insight is to use a stack to keep track of indices of temperatures that we haven't found a warmer day for yet.

```java
// Initialize the answer array with zeros
int[] answer = new int[temperatures.length];
// Create a stack to store indices of temperatures
Stack<Integer> stack = new Stack<>();

// Process each temperature
for (int i = 0; i < temperatures.length; i++) {
    // While the stack is not empty and current temperature is warmer than temperature at stack's top index
    while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        int prevIndex = stack.pop();
        answer[prevIndex] = i - prevIndex; // Calculate days waited
    }
    // Push current index to the stack
    stack.push(i);
}

return answer;
```

### Approach 2: Working Backwards

Another approach is to iterate from the end of the array:

```java
int n = temperatures.length;
int[] answer = new int[n];
// Start from the second-to-last day
for (int i = n - 2; i >= 0; i--) {
    int next = i + 1;
    // Keep moving forward until finding a warmer day or hitting the end
    while (next < n && temperatures[next] <= temperatures[i]) {
        if (answer[next] == 0) {
            next = n; // No warmer day found
        } else {
            next = next + answer[next]; // Jump to the next warmer day
        }
    }
    // If we found a warmer day, calculate the wait time
    if (next < n) {
        answer[i] = next - i;
    }
}
return answer;
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
/**
 * Solution to the Daily Temperatures problem using a monotonic stack.
 * Time Complexity: O(n) where n is the length of temperatures array
 * Space Complexity: O(n) in the worst case for the stack
 */
class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n]; // Initialize answer array with default values 0
        
        // Using a monotonic stack to track indices of temperatures
        // that we haven't found a warmer temperature for yet
        Stack<Integer> stack = new Stack<>();
        
        // Process each temperature from left to right
        for (int i = 0; i < n; i++) {
            // While the stack is not empty and current temperature is warmer
            // than the temperature at the index at the top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop(); // Get the index of the colder temperature
                answer[prevIndex] = i - prevIndex; // Calculate number of days to wait
            }
            
            // Push the current index onto the stack
            stack.push(i);
        }
        
        // For all remaining indices in the stack, there is no warmer temperature,
        // so the default value of 0 in the answer array is correct
        
        return answer;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(n)` where n is the length of the temperatures array. Although we have a nested while loop, each element is pushed and popped from the stack at most once.
- **Space Complexity:** `O(n)` in the worst case when temperatures are in decreasing order, all indices would be stored in the stack.

---

## 🔄 Alternative Approach: Working Backwards with Optimization

This alternative approach uses dynamic programming to avoid unnecessary checks:

```java
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] answer = new int[n];
    
    // Start from the second-to-last day and work backwards
    for (int i = n - 2; i >= 0; i--) {
        int j = i + 1;
        
        // Use previous results to skip ahead
        while (j < n && temperatures[j] <= temperatures[i]) {
            if (answer[j] == 0) {
                // No warmer day for j, so no warmer day for i either
                j = n;
                break;
            }
            // Jump to the next temperature that is warmer than temperatures[j]
            j += answer[j];
        }
        
        // If we found a warmer temperature, calculate the wait time
        if (j < n) {
            answer[i] = j - i;
        }
    }
    
    return answer;
}
```

This solution has the same time and space complexity but takes a different approach.

---

## 📊 Visual Representation of the Stack Approach

For better understanding, let's trace through the example [73, 74, 75, 71, 69, 72, 76, 73]:

1. Start with empty stack and answer [0, 0, 0, 0, 0, 0, 0, 0]
2. Process 73: Push index 0 to stack. Stack: [0]
3. Process 74: 74 > 73, so pop 0, answer[0] = 1-0 = 1. Push 1. Stack: [1]
4. Process 75: 75 > 74, so pop 1, answer[1] = 2-1 = 1. Push 2. Stack: [2]
5. Process 71: 71 < 75, so push 3. Stack: [2, 3]
6. Process 69: 69 < 71, so push 4. Stack: [2, 3, 4]
7. Process 72: 72 > 69, pop 4, answer[4] = 5-4 = 1. 72 > 71, pop 3, answer[3] = 5-3 = 2. 72 < 75, push 5. Stack: [2, 5]
8. Process 76: 76 > 72, pop 5, answer[5] = 6-5 = 1. 76 > 75, pop 2, answer[2] = 6-2 = 4. Push 6. Stack: [6]
9. Process 73: 73 < 76, push 7. Stack: [6, 7]
10. End: Stack has [6, 7], which correspond to temperatures with no warmer day ahead, so answer[6] and answer[7] remain 0.

Final result: [1, 1, 4, 2, 1, 1, 0, 0]

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I approached this problem using a monotonic stack, which is particularly effective for 'next greater element' type problems. Here's my strategy:

1. **Initialize:** Create an answer array filled with zeros and a stack to store indices.

2. **Process Each Temperature:**
   - For each temperature, while the stack is not empty and the current temperature is warmer than the temperature at the index at the top of the stack:
     - Pop the index from the stack.
     - Calculate the wait time as the difference between the current index and the popped index.
   - Push the current index onto the stack.

3. **Handle Remaining Indices:**
   - Any indices left in the stack represent days that don't have a warmer day in the future.
   - These entries in the answer array remain 0, which is our default value.

4. **Efficiency:**
   - The time complexity is O(n) because each index is pushed and popped at most once.
   - The space complexity is O(n) in the worst case when all temperatures are in decreasing order.

This approach is efficient because it avoids the need to search future days for each temperature multiple times, instead leveraging previously computed information stored in the stack."

---

## 🔍 Applications and Extensions

The monotonic stack pattern used in this problem has several important applications:

1. **Stock Span Problem:** Finding consecutive days where stock price is less than or equal to today's price.

2. **Next Greater/Smaller Element:** Finding the next greater or smaller element for each element in an array.

3. **Histogram Area:** Finding the largest rectangle area in a histogram.

4. **Trapping Rain Water:** Calculating how much water can be trapped between buildings of varying heights.

This pattern is particularly useful when you need to find the next or previous greater/smaller element in a linear time complexity.

---

## 🔥 Final Takeaways

- **Monotonic stacks are powerful tools** for problems involving next/previous greater/smaller elements.
- **The key insight is to use the stack to avoid redundant checking** of the same elements multiple times.
- **Working backwards (right-to-left) can provide an alternative approach** for certain problems.
- **The problem demonstrates efficient use of auxiliary data structures** to reduce time complexity from O(n²) to O(n).
- **By storing indices rather than values in the stack**, we can easily calculate the distance between positions.
- **This pattern appears frequently in interview questions**, so understanding it well can help with many similar problems.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/daily-temperatures/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventy-fourth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.