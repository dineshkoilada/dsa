# Stack Pattern 🎯

## 📌 Introduction: The Power of LIFO

Imagine you’re stacking plates in a cafeteria – you can only add or remove from the top of the stack. The **Stack Pattern** is your go-to tool for solving problems where the most recently added element needs to be processed first (Last-In-First-Out, LIFO)!

### 🎬 Real-World Analogies:

1. **Plate Stack** 🍽️
   ```
   Plates: [Bottom][ ][ ][ ][Top]
   Add/remove only from the top.
   ```
2. **Undo Feature in Text Editor** 📝
   ```
   Actions: [Type][Delete][Paste]
   Undo: Remove last action first.
   ```
3. **Browser Back Button** 🌐
   ```
   Pages: [Home][Search][Article]
   Back: Go to the most recently visited page.
   ```

The Stack pattern is your secret weapon when you need:
- 📍 To process elements in reverse order (LIFO)
- 🔄 To manage nested or hierarchical relationships
- 🧩 To evaluate expressions, parse syntax, or track state
- 🚦 To implement undo/redo or backtracking

### 🎯 Visual Example:
Validating parentheses:
```
Input:   ( [ { } ] )
Stack:   ( → [ → { → } pop → ] pop → ) pop
Result:  Stack empty → Valid
```

---

## 🧠 How to Recognize a Stack Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Nested/Matching" Clue** 📑
   - "Balanced parentheses/brackets/braces"
   - "Matching pairs" or "nested structures"

2. **The "Reverse Order" Hint** 🔄
   - "Undo/redo operations"
   - "Backtracking" or "reverse processing"

3. **The "State Tracking" Signal** 🧩
   - "Remember previous state/context"
   - "Expression evaluation or syntax parsing"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   What elements are being pushed/popped?
   Are there constraints on stack size?
   What should happen if the stack is empty?
   ```
2. **Content Questions:**
   ```
   Are there nested or hierarchical relationships?
   Is order of processing important?
   ```
3. **Edge Case Questions:**
   ```
   What if the input is empty?
   What if there are unmatched elements?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Initialize an empty stack
[   ]

Step 2: Process each element
[🟦] (push), [🟦][🟦] (push), [🟦] (pop)

Step 3: Continue until input is processed
[   ] (stack empty → valid)

Step 4: Check final stack state
[   ] (empty = success, not empty = error)
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What elements go on the stack?
- What triggers a push or pop?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned if the stack is empty?
- Are there invalid or unmatched cases?

### ✅ **3. Identify Base Cases**
- Empty input: stack remains empty
- Unmatched pop: invalid

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveStackProblem(input):
    stack = []
    for element in input:
        if (push condition):
            stack.push(element)
        else if (pop condition):
            if stack is empty or not matching:
                return false
            stack.pop()
    return stack is empty
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.Stack;

public class StackPattern {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
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
}
```

### ✅ **6. Edge Cases to Consider**
- Empty input
- Stack becomes empty during processing
- Unmatched or extra elements
- Nested structures

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Push/Pop/Peek     | O(1)            | O(1)             |
| Process input     | O(n)            | O(n)             |
| Overall           | O(n)            | O(n)             |

**How to derive these complexities:**
- **Time:** Each element is pushed/popped at most once
- **Space:** Stack can grow to size n in worst case

---

## 📚 Example 1: Easy Problem - Valid Parentheses

**Problem:**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

**Input:** "()[]{}"

**Expected Output:** true

### 🔑 **Solution Steps**
1. Initialize an empty stack
2. Push opening brackets
3. Pop and check for matching closing brackets
4. If stack is empty at the end, string is valid

### ✅ **Code:**
```java
public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
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
- **Time:** O(n)
- **Space:** O(n)

---

## 📚 Example 2: Medium Problem - Evaluate Reverse Polish Notation

**Problem:**
Evaluate a Reverse Polish Notation (postfix) expression.

**Input:** ["2", "1", "+", "3", "*"]

**Expected Output:** 9

### 🔑 **Solution Steps**
1. Initialize stack for operands
2. Push numbers, pop and evaluate operators
3. Final result is top of stack

### ✅ **Code:**
```java
public static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
            int b = stack.pop(), a = stack.pop();
            switch (token) {
                case "+": stack.push(a + b); break;
                case "-": stack.push(a - b); break;
                case "*": stack.push(a * b); break;
                case "/": stack.push(a / b); break;
            }
        } else {
            stack.push(Integer.parseInt(token));
        }
    }
    return stack.pop();
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(n)

---

## 📚 Example 3: Hard Problem - Largest Rectangle in Histogram

**Problem:**
Given an array of integers representing the histogram's bar height, find the area of the largest rectangle in the histogram.

**Input:** [2, 1, 5, 6, 2, 3]

**Expected Output:** 10

### 🔑 **Solution Steps**
1. Use stack to track indices of bars
2. Pop and calculate area when current bar is lower
3. Update max area

### ✅ **Code:**
```java
public static int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0, i = 0;
    while (i < heights.length) {
        if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
            stack.push(i++);
        } else {
            int top = stack.pop();
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, heights[top] * width);
        }
    }
    while (!stack.isEmpty()) {
        int top = stack.pop();
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        maxArea = Math.max(maxArea, heights[top] * width);
    }
    return maxArea;
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(n)

---

## 📚 Key Takeaways

1. Stacks are ideal for problems involving **LIFO order**, **matching pairs**, or **nested structures**.
2. Always check if the stack is **empty before popping**.
3. The **initialization** and **final state** of the stack are critical.
4. Stacks are natural for **expression parsing**, **syntax validation**, and **monotonic sequences**.
5. Stack-based solutions often reduce time complexity from quadratic to linear.

---

Next, let’s explore the **HashMap/HashTable Pattern** for solving problems involving fast lookups and key-value associations!