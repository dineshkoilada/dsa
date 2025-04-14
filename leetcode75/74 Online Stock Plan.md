# Online Stock Span

## 📌 Problem Statement

**LeetCode Problem:** [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/)  
**Difficulty:** Medium  

**Description:**
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.

- For example, if the price of a stock over the next 7 days were `[100,80,60,70,60,75,85]`, then the stock spans would be `[1,1,1,2,1,4,6]`.

Implement the `StockSpanner` class:

- `StockSpanner()` Initializes the object of the class.
- `int next(int price)` Returns the span of the stock's price given that today's price is `price`.

### **Example 1:**
**Input:** 
```
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
```
**Output:** 
```
[null, 1, 1, 1, 2, 1, 4, 6]
```
**Explanation:** 
```
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4
stockSpanner.next(85);  // return 6
```

### **Constraints:**
- `1 <= price <= 10^5`
- At most `10^4` calls will be made to `next`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're tracking the price of your favorite toy every day. For each day, you want to know: "How many days in a row, including today, has the toy been this price or cheaper?"

This is called the "span" for today's price.

For example, if the prices were [100, 80, 60, 70, 60, 75, 85]:
- Day 1: Price is 100. This is the first day, so span = 1.
- Day 2: Price is 80. It's cheaper than the day before, so span = 1.
- Day 3: Price is 60. It's cheaper than day 2, so span = 1.
- Day 4: Price is 70. It's more expensive than day 3, but still cheaper than day 1 and 2. Looking back, we see [70, 60], so span = 2.
- Day 5: Price is 60. It's cheaper than day 4, so span = 1.
- Day 6: Price is 75. Looking back, we see [75, 60, 70, 60], so span = 4.
- Day 7: Price is 85. Looking back, we see [85, 75, 60, 70, 60, 80], so span = 6.

Instead of giving you the entire list of prices at once, the problem asks you to calculate the span one day at a time as new prices come in.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How can we efficiently calculate the span without checking every previous day?**
   - A stack could help us skip unnecessary comparisons.
2. **What information do we need to store for each price?**
   - We need both the price and its corresponding span.
3. **Can we reuse previous span calculations?**
   - Yes, this is where the stack becomes particularly useful.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Initialize the StockSpanner class with a stack
```java
class StockSpanner {
    // Stack to store pairs of (price, span)
    Stack<int[]> stack;

    public StockSpanner() {
        stack = new Stack<>();
    }
}
```

### Step 2: Implement the next method
```java
public int next(int price) {
    // Start with span of 1 (today)
    int span = 1;
    
    // Pop elements from stack while they're less than or equal to current price
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
        // Add the span of the popped element to our current span
        span += stack.pop()[1];
    }
    
    // Push the current price and its span to the stack
    stack.push(new int[]{price, span});
    
    return span;
}
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
class StockSpanner {
    /**
     * Stack to store pairs of [price, span]
     * - price: the stock price on a particular day
     * - span: the span of that price (number of consecutive days with price <= this price)
     */
    private Stack<int[]> stack;

    /**
     * Initialize the StockSpanner with an empty stack.
     */
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    /**
     * Returns the span of the stock's price for the current day.
     * The span is defined as the maximum number of consecutive days 
     * (starting from today and going backward) for which the stock price
     * was less than or equal to today's price.
     * 
     * Time Complexity: O(1) amortized - Each element is pushed and popped at most once
     * Space Complexity: O(n) where n is the number of calls to next()
     * 
     * @param price Today's stock price
     * @return The span of today's price
     */
    public int next(int price) {
        // Start with span of 1 (today)
        int span = 1;
        
        // Pop elements from stack while they're less than or equal to current price
        // This effectively "merges" the spans of days with prices <= today's price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            // Add the span of the popped element to our current span
            span += stack.pop()[1];
        }
        
        // Push the current price and its span to the stack
        stack.push(new int[]{price, span});
        
        return span;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** Amortized `O(1)` per call to `next()`. Although there's a while loop, each element is pushed and popped at most once across all calls, giving us an amortized constant time per operation.
- **Space Complexity:** `O(n)` where n is the number of calls to `next()`. In the worst case (strictly increasing prices), all prices would be stored in the stack.

---

## 📊 Visual Walkthrough of the Example

Let's trace through the example [100, 80, 60, 70, 60, 75, 85]:

1. `next(100)`:
   - Stack is empty, span = 1
   - Push [100, 1] to stack
   - Return 1

2. `next(80)`:
   - 80 < 100, so don't pop anything
   - Push [80, 1] to stack (Stack: [100,1], [80,1])
   - Return 1

3. `next(60)`:
   - 60 < 80, so don't pop anything
   - Push [60, 1] to stack (Stack: [100,1], [80,1], [60,1])
   - Return 1

4. `next(70)`:
   - 70 > 60, so pop [60,1], span = 1 + 1 = 2
   - 70 < 80, so don't pop anything
   - Push [70, 2] to stack (Stack: [100,1], [80,1], [70,2])
   - Return 2

5. `next(60)`:
   - 60 < 70, so don't pop anything
   - Push [60, 1] to stack (Stack: [100,1], [80,1], [70,2], [60,1])
   - Return 1

6. `next(75)`:
   - 75 > 60, so pop [60,1], span = 1 + 1 = 2
   - 75 > 70, so pop [70,2], span = 2 + 2 = 4
   - 75 < 80, so don't pop anything
   - Push [75, 4] to stack (Stack: [100,1], [80,1], [75,4])
   - Return 4

7. `next(85)`:
   - 85 > 75, so pop [75,4], span = 1 + 4 = 5
   - 85 > 80, so pop [80,1], span = 5 + 1 = 6
   - 85 < 100, so don't pop anything
   - Push [85, 6] to stack (Stack: [100,1], [85,6])
   - Return 6

Final result: [1, 1, 1, 2, 1, 4, 6]

---

## 🔄 Alternative Implementation: Without Using a Stack Class

We can implement the same solution using an array and an index pointer:

```java
class StockSpanner {
    private List<Integer> prices;
    private List<Integer> spans;

    public StockSpanner() {
        prices = new ArrayList<>();
        spans = new ArrayList<>();
    }
    
    public int next(int price) {
        int span = 1;
        int i = prices.size() - 1;
        
        while (i >= 0 && prices.get(i) <= price) {
            span += spans.get(i);
            i -= spans.get(i);
        }
        
        prices.add(price);
        spans.add(span);
        
        return span;
    }
}
```

This approach has the same time and space complexity as the stack-based solution but uses different data structures.

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I approached this problem using a monotonic stack pattern, which is well-suited for calculating spans efficiently. Here's my thought process:

1. **Data Structure Choice:**
   - I use a stack to store pairs of [price, span].
   - This allows me to maintain a decreasing sequence of prices and their corresponding spans.

2. **Calculating Spans:**
   - For each new price, I start with a span of 1 (today).
   - I then check the stack for previous prices that are less than or equal to today's price.
   - For each such price, I pop it from the stack and add its span to my current span.
   - This effectively 'merges' consecutive days with prices less than or equal to today's price.

3. **Efficiency:**
   - The time complexity is amortized O(1) per call to next().
   - While there's a while loop, each element is pushed and popped at most once across all calls.
   - The space complexity is O(n) in the worst case.

4. **Key Insight:**
   - By storing both the price and its span in the stack, I avoid rechecking days that have already been accounted for.
   - When I pop an element from the stack, I add its span to my current span, skipping over multiple days at once.

This solution efficiently implements the stock span algorithm with optimal time complexity for an online (streaming) scenario."

---

## 🔍 Related Problems and Applications

The stock span problem is a classic example of using monotonic stacks. It has several related problems and applications:

1. **Maximum Histogram Area:** Finding the largest rectangle in a histogram uses a similar approach.

2. **Next Greater/Smaller Element:** Finding the next greater or smaller element for each element in an array.

3. **Daily Temperatures:** Finding how many days you have to wait for a warmer temperature (a variant of the Next Greater Element problem).

4. **Financial Analysis:** Used in calculating moving averages and trend analysis in financial markets.

5. **Time Series Analysis:** Similar techniques can be used for analyzing trends in time series data.

These problems all share the common pattern of using stacks to avoid redundant comparisons and achieve linear time complexity.

---

## 🔥 Final Takeaways

- **The monotonic stack pattern is ideal for span/range problems** where we need to find consecutive elements satisfying certain conditions.
- **Storing additional information with each stack element** (like the span) can dramatically improve efficiency.
- **Amortized analysis** helps us understand the true efficiency of operations that may occasionally be expensive but are cheap on average.
- **This problem demonstrates the power of preprocessing** to avoid redundant work.
- **Online algorithms** (that process data one item at a time) often require different approaches than offline algorithms (that have access to all data at once).
- **The solution elegantly handles streaming data**, making it suitable for real-time applications.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/online-stock-span/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventy-fifth problem** in our **LeetCode 75 Study Plan**🚀.