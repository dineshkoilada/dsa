# Sliding Window Pattern 🎯

## 📌 Introduction: The Power of Focus

Imagine you're sitting on a train, watching the landscape through a window. As the train moves, your window frame stays the same size, but you see different portions of the scenery. This is exactly how the **Sliding Window Pattern** works in programming!

### 🎬 Real-World Analogies:

1. **Train Window** 🚂
   ```
   Landscape: [🌲][🏠][🌳][🏰][⛰️][🌊][🏖️]
   Window 1:  [🌲 🏠 🌳]
   Window 2:     [🏠 🌳 🏰]
   Window 3:        [🌳 🏰 ⛰️]
   ```

2. **Netflix Viewing History** 📺
   ```
   Last 30 days: [Show1][Show2][Show3]...[Show30]
   Today:        Remove[Show1] → Add[Show31]
   ```

3. **Credit Card Statement** 💳
   ```
   3-month spending: [Jan][Feb][Mar]
   Next month:      [Feb][Mar][Apr]
   ```

The sliding window technique is your secret weapon when working with:
- 📍 Subarrays or substrings (like finding the longest substring with unique characters)
- 📏 Fixed-size sequences (like calculating moving averages)
- 🔄 Optimizing calculations over consecutive elements (like maximum sum subarray)
- 📊 Finding maximum/minimum values over a continuous range (like best time to buy/sell stock)

### 🎯 Visual Example:
Finding max sum of 3 consecutive numbers:
```
Array:     [1] [2] [3] [4] [5]
Window 1:   ←[1 + 2 + 3]→        = 6
Window 2:      ←[2 + 3 + 4]→     = 9
Window 3:         ←[3 + 4 + 5]→   = 12
Max Sum: 12
```

---

## 🧠 How to Recognize a Sliding Window Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Consecutive" Clue** 📑
   - Words like "consecutive," "contiguous," or "in-order" elements
   - Example: "Find the longest substring with K distinct characters"
   ```
   String: "AAAHHIBC"
   K = 2
   Window: [AA] → [AAH] → [HI] → [IB] → [BC]
   ```

2. **The "Window Size" Hint** 📏
   - Fixed size: "... of size K"
   - Dynamic size: "at most K distinct"
   ```
   Fixed:    [_ _ _] → [_ _ _] → [_ _ _]
   Dynamic:  [_ _] → [_ _ _] → [_ _ _ _]
   ```

3. **The "Optimization" Signal** 🎯
   - "Maximum/minimum sum"
   - "Longest/shortest substring"
   - "Contains/excludes certain elements"

### 🤔 Essential Questions to Ask:

1. **Window Type Questions:**
   ```
   Fixed or Dynamic?
   ├── Fixed: Array of size 3
   │   └── Example: Moving average of stock prices
   └── Dynamic: At most K distinct chars
       └── Example: Longest substring with K chars
   ```

2. **Content Questions:**
   ```
   What are we tracking?
   ├── Sums
   ├── Counts
   ├── Unique elements
   └── Patterns
   ```

3. **Edge Case Questions:**
   ```
   Input Validation:
   ├── Empty array/string?
   ├── Negative numbers?
   ├── Window size > array size?
   └── Single element?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Initialize Window
[🟦][⬜][⬜][⬜][⬜]  👈 Start with first element

Step 2: Expand Window
[🟦][🟦][🟦][⬜][⬜]  👈 Grow until condition is met

Step 3: Process Window
[🟦][🟦][🟦][⬜][⬜]  👈 Calculate/store result

Step 4: Slide Window
[⬜][🟦][🟦][🟦][⬜]  👈 Move window forward

Step 5: Repeat 2-4
[⬜][⬜][🟦][🟦][🟦]  👈 Until end of array
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are we summing or counting values in a range?
- Should the window expand, shrink, or remain fixed?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should be returned if the array is empty?
- Can the window include negative numbers?
- Are duplicate elements allowed?

### ✅ **3. Identify Base Cases**
- If the array length is shorter than the window size, return an error or a default result.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveSlidingWindow(array, k):
    if array is empty or k > array.length:
        return "Invalid input"

    maxSum = currentSum = sum of first k elements

    for i from k to array.length - 1:
        currentSum += array[i] - array[i - k]  // Slide window
        maxSum = max(maxSum, currentSum)

    return maxSum
```

### ✅ **5. Write the Code Skeleton**
```java
public class SlidingWindowTemplate {
    public static int slidingWindowExample(int[] array, int k) {
        if (array == null || array.length < k) {
            System.out.println("Invalid input.");
            return -1;
        }

        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += array[i];
        }

        int windowSum = maxSum;
        for (int i = k; i < array.length; i++) {
            windowSum += array[i] - array[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- The array is empty.
- The window size is larger than the array length.
- The array contains negative numbers.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation                   | Time Complexity | Space Complexity |
|-----------------------------|-----------------|------------------|
| Initial sum calculation     | O(k)            | O(1)             |
| Sliding the window          | O(n - k)        | O(1)             |
| Total                       | O(n)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** The loop runs `n - k` times after initially summing the first `k` elements.
- **Space Complexity:** We use a constant amount of space since we update the sum in place.

---

## 📚 Example 1: Easy Problem - Maximum Sum of Subarray of Size K

**Problem:**
Given an array of integers and a number `k`, find the maximum sum of any contiguous subarray of size `k`.

**Input:** `[2, 1, 5, 1, 3, 2]`, `k = 3`

**Expected Output:** `9`

### 🔑 **Solution Steps**
1. Calculate the sum of the first `k` elements.
2. Slide the window by subtracting the element leaving the window and adding the new element.
3. Keep track of the maximum sum seen so far.

### ✅ **Code:**
```java
public class MaxSumSubarray {
    public static int maxSum(int[] arr, int k) {
        int maxSum = 0;
        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Traverse the array once.
- **Space:** O(1) — Only variables for sums are used.

---

## 📚 Example 2: Medium Problem - Longest Substring Without Repeating Characters

**Problem:**
Find the length of the longest substring without repeating characters.

**Input:** `"abcabcbb"`

**Expected Output:** `3`

### 🔑 **Solution Steps**
1. Use a HashSet to track characters in the current window.
2. Expand the window by adding new characters.
3. Shrink the window when a duplicate character is found.
4. Keep track of the maximum length.

### ✅ **Code:**
```java
import java.util.*;

public class LongestUniqueSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each character is visited at most twice.
- **Space:** O(k) — The set holds at most `k` characters.

---

## 📚 Example 3: Hard Problem - Longest Substring with At Most Two Distinct Characters

**Problem:**
Find the length of the longest substring that contains at most two distinct characters.

**Input:** `"eceba"`

**Expected Output:** `3`

### 🔑 **Solution Steps**
1. Use a HashMap to store character counts.
2. Expand the window until there are more than two distinct characters.
3. Shrink the window by removing characters until there are only two distinct characters left.
4. Track the maximum length.

### ✅ **Code:**
```java
import java.util.*;

public class LongestSubstringTwoDistinct {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) — Each character is visited at most twice.
- **Space:** O(1) — At most two distinct characters are stored.

---

## 📚 Key Takeaways

1. Use sliding windows for problems that involve consecutive elements, substrings, or subarrays.
2. Optimize time complexity from O(n²) to O(n) by avoiding nested loops.
3. Manage fixed-size windows by adding and subtracting elements as the window moves.
4. For variable-size windows, expand and shrink dynamically based on problem constraints.

---

Next, let’s explore the **Fast and Slow Pointers Pattern** to handle problems related to cycles and linked lists!

