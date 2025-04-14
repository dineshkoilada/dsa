# Letter Combinations of a Phone Number

## 📌 Problem Statement

**LeetCode Problem:** [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)  
**Difficulty:** Medium  

**Description:**
Given a string containing digits from `2-9` inclusive, return all possible letter combinations that the number could represent. Return the answer in **any order**.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

![Phone Keypad](https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)

### **Example 1:**
**Input:** 
```
digits = "23"
```
**Output:** 
```
["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

### **Example 2:**
**Input:** 
```
digits = ""
```
**Output:** 
```
[]
```

### **Example 3:**
**Input:** 
```
digits = "2"
```
**Output:** 
```
["a","b","c"]
```

### **Constraints:**
- `0 <= digits.length <= 4`
- `digits[i]` is a digit in the range `['2', '9']`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have an old phone with buttons, where each number button also has some letters on it:
- Button 2 has letters "abc"
- Button 3 has letters "def"
- Button 4 has letters "ghi"
- And so on...

Now, if someone presses a sequence of buttons (like "23"), we want to find all the possible letter combinations they could be trying to type. For example:
- From button 2, we can get 'a', 'b', or 'c'
- From button 3, we can get 'd', 'e', or 'f'

So all possible combinations would be: "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", and "cf".

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How many combinations will we have?**
   - If the first button has X letters and the second has Y letters, we'll have X × Y combinations.
2. **What's a systematic way to generate all combinations?**
   - We can use recursion or backtracking to build combinations one character at a time.
3. **What if the input is empty?**
   - We should return an empty list.

👉 These considerations will help us approach the problem systematically!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Create a mapping from digits to letters
```java
Map<Character, String> digitToLetters = new HashMap<>();
digitToLetters.put('2', "abc");
digitToLetters.put('3', "def");
digitToLetters.put('4', "ghi");
digitToLetters.put('5', "jkl");
digitToLetters.put('6', "mno");
digitToLetters.put('7', "pqrs");
digitToLetters.put('8', "tuv");
digitToLetters.put('9', "wxyz");
```

### Step 2: Use backtracking to generate all combinations
```java
List<String> result = new ArrayList<>();
if (digits.length() == 0) return result;
backtrack(0, new StringBuilder(), digits, digitToLetters, result);
return result;
```

### Step 3: Implement the backtracking function
```java
private void backtrack(int index, StringBuilder current, String digits, 
                      Map<Character, String> digitToLetters, List<String> result) {
    if (index == digits.length()) {
        result.add(current.toString());
        return;
    }
    
    String letters = digitToLetters.get(digits.charAt(index));
    for (char letter : letters.toCharArray()) {
        current.append(letter);
        backtrack(index + 1, current, digits, digitToLetters, result);
        current.deleteCharAt(current.length() - 1); // backtrack
    }
}
```

---

## 🛠️ Complete Solution using Backtracking

```java
import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // Map each digit to its corresponding letters
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
        
        // Start backtracking from the first digit
        backtrack(0, new StringBuilder(), digits, digitToLetters, result);
        return result;
    }
    
    private void backtrack(int index, StringBuilder current, String digits,
                          Map<Character, String> digitToLetters, List<String> result) {
        // If we've processed all digits, add the current combination to the result
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get all letters for the current digit
        String letters = digitToLetters.get(digits.charAt(index));
        
        // Try each letter for the current digit
        for (char letter : letters.toCharArray()) {
            // Add the current letter to our combination
            current.append(letter);
            
            // Recurse to the next digit
            backtrack(index + 1, current, digits, digitToLetters, result);
            
            // Backtrack by removing the last letter
            current.deleteCharAt(current.length() - 1);
        }
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(4^n)` where n is the length of the digits string. In the worst case (when all digits are 7 or 9), each digit can map to 4 letters. So for n digits, we have 4^n combinations.
- **Space Complexity:** `O(n)` for the recursion stack and to store the current combination.

---

## 🔄 Iterative Solution

```java
import java.util.*;

public class LetterCombinationsIterative {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // Map each digit to its corresponding letters
        String[] digitToLetters = new String[] {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };
        
        // Initialize result with an empty string
        result.add("");
        
        // For each digit in the input
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            String letters = digitToLetters[digit];
            
            // Create a new result list to store combinations with the current digit
            List<String> newResult = new ArrayList<>();
            
            // For each existing combination, add each letter of the current digit
            for (String combination : result) {
                for (char letter : letters.toCharArray()) {
                    newResult.add(combination + letter);
                }
            }
            
            // Replace the result with the new combinations
            result = newResult;
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(4^n)` where n is the length of the digits string, for the same reason as the backtracking solution.
- **Space Complexity:** `O(4^n)` to store the result.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "This problem is a perfect candidate for backtracking, where we build the combinations one character at a time."
- "First, I created a mapping from each digit to its corresponding letters using a HashMap."
- "Then, I used a backtracking algorithm that works as follows:
  - For each digit, we try all possible letters it maps to.
  - For each letter, we add it to our current combination and recursively process the next digit.
  - After processing all digits, we add the complete combination to our result.
  - Finally, we backtrack by removing the last letter and try the next letter for the current digit."
- "This systematically explores all possible combinations of letters."
- "I also implemented an iterative solution that builds combinations by appending each new letter to all existing combinations, which some interviewers might prefer."
- "Both solutions have the same time complexity of O(4^n) in the worst case, where n is the number of digits."

---

## 🔥 Final Takeaways
- **Backtracking is a powerful technique for generating combinations or permutations.**
- **When using backtracking, remember to add the result only when a complete combination is formed.**
- **Don't forget to backtrack (remove the last character) before trying the next possibility.**
- **An iterative approach can sometimes be clearer and avoid recursion overhead.**
- **Be careful with edge cases like empty input strings.**
- **This problem teaches how to systematically generate all possibilities when each position has multiple choices.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/letter-combinations-of-a-phone-number/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifty-seven problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.