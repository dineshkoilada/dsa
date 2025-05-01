# Stack Pattern

## 🎯 Introduction

Imagine you're stacking plates in a cafeteria – you can only add or remove from the top of the stack. The **Stack** data structure operates on the same principle: Last-In-First-Out (LIFO), making it perfect for solving problems where the most recently added element needs to be processed first.

The Stack Pattern is particularly useful for:
- Tracking elements with nested or hierarchical relationships
- Managing function calls and recursion
- Expression evaluation and syntax parsing
- Implementing undo mechanisms
- Depth-first traversals of trees and graphs
- Problems requiring a "reverse order" processing

This pattern works best when you need to keep track of state or context, especially when nested structures are involved.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Does the problem involve tracking elements in a specific order?
   - Is there a need to process elements in a reversed or nested manner?
   - Does the problem involve matching pairs or balancing structures?

2. **Ask Clarifying Questions:**
   - What should happen when the stack is empty?
   - Is there a maximum size constraint for the stack?
   - How should we handle edge cases like invalid expressions?

3. **Identify Clues for Using the Stack Pattern:**
   - Keywords like "balanced," "nested," "matching," or "most recent"
   - Problems involving brackets, parentheses, or syntax validation
   - Situations where you need to remember the processing state
   - Problems requiring backtracking or undoing operations

4. **Predicting if Stack Is Applicable:**
   - If processing in reverse order is needed
   - If the problem involves nested structures or pairs
   - If you need to remember previous decisions to make current ones

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What elements need to be stored in the stack?
- What operations need to be performed when pushing or popping?
- What is the desired outcome based on stack operations?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned when the stack is empty?
- Are there specific conditions to check before pushing/popping?

### ✅ **3. Identify Base Cases**
- Empty input: Initialize an empty stack
- Stack operations on empty stack: Handle properly (e.g., check before pop)

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveStackProblem(input):
    initialize an empty stack
    
    for each element in input:
        if (condition for pushing):
            push element or transformed element to stack
        else if (condition for popping):
            process and pop from stack
        else:
            perform other operations based on problem requirement
    
    // Process any remaining elements in stack if needed
    while stack is not empty:
        process stack elements
        
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.Stack;

public class StackSolution {
    public static String solveWithStack(String input) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        
        for (char c : input.toCharArray()) {
            // Push condition
            if (isPushCondition(c)) {
                stack.push(c);
            } 
            // Pop and process condition
            else if (isPopCondition(c) && !stack.isEmpty()) {
                char top = stack.pop();
                processPopped(top, c, result);
            }
            // Other processing
            else {
                processOther(c, result);
            }
        }
        
        // Process any remaining elements in stack
        while (!stack.isEmpty()) {
            result.append(processRemaining(stack.pop()));
        }
        
        return result.toString();
    }
    
    private static boolean isPushCondition(char c) {
        // Define when to push based on problem
        return false;
    }
    
    private static boolean isPopCondition(char c) {
        // Define when to pop based on problem
        return false;
    }
    
    private static void processPopped(char top, char current, StringBuilder result) {
        // Process popped element
    }
    
    private static void processOther(char c, StringBuilder result) {
        // Handle other cases
    }
    
    private static char processRemaining(char c) {
        // Process remaining stack elements
        return c;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty input string or array
- Stack becomes empty during processing
- Unbalanced expressions or missing closing elements
- Maximum size constraints on the stack (if applicable)
- Nested structures with multiple levels of depth
- Invalid input that breaks expected patterns

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Push                    | O(1)            | O(1)             |
| Pop                     | O(1)            | O(1)             |
| Peek                    | O(1)            | O(1)             |
| Processing entire input | O(n)            | O(n)             |
| Overall                 | O(n)            | O(n)             |

**How to derive these complexities:**
- **Time Complexity:** O(n) where n is the input size, as we typically process each element once.
- **Space Complexity:** O(n) in the worst case when all elements are pushed onto the stack.

---

## 📚 Example 1: Easy Problem - Valid Parentheses

**Problem:**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. A string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

**Input:**
```
"()[]{}"
```

**Expected Output:**
```
true
```

### 🔑 **Solution Steps**
1. Initialize an empty stack
2. Iterate through each character in the string
3. If it's an opening bracket, push it onto the stack
4. If it's a closing bracket, check if the stack is empty or if the top element matches the corresponding opening bracket
5. If the stack is empty at the end, the string is valid

### ✅ **Code:**
```java
public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) {
                return false;
            }
            
            char top = stack.pop();
            
            if ((c == ')' && top != '(') || 
                (c == '}' && top != '{') || 
                (c == ']' && top != '[')) {
                return false;
            }
        }
    }
    
    return stack.isEmpty();
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) where n is the length of the string
- **Space:** O(n) for storing characters in the stack

---

## 📚 Example 2: Medium Problem - Evaluate Reverse Polish Notation

**Problem:**
Evaluate a Reverse Polish Notation (postfix) expression. Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

**Input:**
```
["2", "1", "+", "3", "*"]
```

**Expected Output:**
```
9 (because ((2 + 1) * 3) = 9)
```

### 🔑 **Solution Steps**
1. Initialize an empty stack for operands
2. Iterate through each token in the expression
3. If the token is a number, push it onto the stack
4. If the token is an operator, pop the two top elements, perform the operation, and push the result back to the stack
5. Return the final element in the stack as the result

### ✅ **Code:**
```java
public static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    
    for (String token : tokens) {
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
            int num2 = stack.pop();
            int num1 = stack.pop();
            
            switch (token) {
                case "+":
                    stack.push(num1 + num2);
                    break;
                case "-":
                    stack.push(num1 - num2);
                    break;
                case "*":
                    stack.push(num1 * num2);
                    break;
                case "/":
                    stack.push(num1 / num2);
                    break;
            }
        } else {
            stack.push(Integer.parseInt(token));
        }
    }
    
    return stack.pop();
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) where n is the number of tokens
- **Space:** O(n) in the worst case for storing operands

---

## 📚 Example 3: Hard Problem - Largest Rectangle in Histogram

**Problem:**
Given an array of integers representing the histogram's bar height, find the area of the largest rectangle in the histogram.

**Input:**
```
[2, 1, 5, 6, 2, 3]
```

**Expected Output:**
```
10 (because the maximum area is formed by the 5 and 6 height bars)
```

### 🔑 **Solution Steps**
1. Use a stack to keep track of indices of bars in increasing order of height
2. For each bar, while the current bar's height is less than the bar at the top of the stack:
   - Pop the top of the stack
   - Calculate the area with the popped bar as the smallest bar
   - Update the maximum area if needed
3. Push the current index onto the stack
4. Process remaining bars in the stack

### ✅ **Code:**
```java
public static int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    int i = 0;
    
    while (i < heights.length) {
        // If stack is empty or current height is higher than the top of stack, push index
        if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
            stack.push(i++);
        } else {
            // If current height is lower, calculate area with the bar at top of stack as smallest
            int topIndex = stack.pop();
            
            // Calculate width based on whether stack is empty
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            
            // Calculate area and update max
            int area = heights[topIndex] * width;
            maxArea = Math.max(maxArea, area);
        }
    }
    
    // Process remaining bars in stack
    while (!stack.isEmpty()) {
        int topIndex = stack.pop();
        
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        int area = heights[topIndex] * width;
        maxArea = Math.max(maxArea, area);
    }
    
    return maxArea;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each bar is pushed and popped at most once
- **Space:** O(n) — For the stack in the worst case

---

## 📚 Key Takeaways

1. Stacks are excellent for problems involving **matching pairs**, **nested structures**, or operations that need to be performed in **reverse order**.
2. Always check if the stack is **empty before popping** to avoid exceptions.
3. The **initialization** and **final state** of the stack are often critical to the correctness of the solution.
4. For problems involving **parsing expressions** or **syntax validation**, stacks are a natural choice.
5. When using stacks for monotonic sequences (like in the histogram problem), you can achieve **linear time complexity** for otherwise quadratic problems.

---

Next, lets dive deep into **HashMaps and HashTables**.