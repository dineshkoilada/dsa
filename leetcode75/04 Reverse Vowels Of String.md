# Reverse Vowels of a String

## 📌 Problem Statement

**LeetCode Problem:** [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/)  
**Difficulty:** Easy  

**Description:**
Given a string `s`, reverse only all the vowels in the string and return it.

The vowels are `'a'`, `'e'`, `'i'`, `'o'`, `'u'`, and their uppercase counterparts.

### **Example 1:**
**Input:**
```
s = "hello"
```
**Output:**
```
"holle"
```

### **Example 2:**
**Input:**
```
s = "leetcode"
```
**Output:**
```
"leotcede"
```

### **Constraints:**
- `1 <= s.length <= 3 * 10^5`
- `s` consists of **printable ASCII characters**.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a **string** of letters, and you want to **swap only the vowels** while keeping the other letters in their original places.

For example, in **"hello"**, the vowels are `e` and `o`. If we swap them, we get **"holle"**.

We are **only swapping vowels**, and everything else stays in the same place.

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What happens if there are no vowels in the string?**
   - The string remains unchanged.
2. **What happens if there's only one vowel?**
   - It stays in place because there’s nothing to swap.
3. **Should we consider uppercase vowels?**
   - Yes! Both uppercase and lowercase vowels need to be swapped.

👉 These are called **edge cases**, and thinking about them **before coding** prevents errors later!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Identify all vowels and store them in a set
```java
Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
```

### Step 2: Use two pointers to swap vowels
```java
int left = 0, right = s.length() - 1;
char[] arr = s.toCharArray();
```

### Step 3: Swap vowels while moving inward
```java
while (left < right) {
    while (left < right && !vowels.contains(arr[left])) {
        left++;
    }
    while (left < right && !vowels.contains(arr[right])) {
        right--;
    }
    char temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
    left++;
    right--;
}
```

### Step 4: Convert back to a string and return
```java
return new String(arr);
```

---

## 🛠️ Writing the Brute Force Solution

```java
public class ReverseVowels {
    public static String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        List<Character> vowelList = new ArrayList<>();
        char[] arr = s.toCharArray();
        
        for (char c : arr) {
            if (vowels.contains(c)) {
                vowelList.add(c);
            }
        }
        
        Collections.reverse(vowelList);
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (vowels.contains(arr[i])) {
                arr[i] = vowelList.get(index++);
            }
        }
        
        return new String(arr);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, where `N` is the length of the string (iterating twice).
- **Space Complexity:** `O(N)`, due to storing vowels separately.

---

## 🚀 Optimized Solution using Two Pointers

```java
public class ReverseVowelsOptimized {
    public static String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            while (left < right && !vowels.contains(arr[left])) left++;
            while (left < right && !vowels.contains(arr[right])) right--;
            
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(arr);
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:** `O(N)`, since each letter is processed at most once.
- **Space Complexity:** `O(1)`, since the input is modified in-place.

---

## 📢 Explaining the Solution to an Interviewer
If asked to explain the approach, you can say:
- "We use a **set** to quickly check if a character is a vowel."
- "We use **two pointers**, one at the start and one at the end, to swap vowels efficiently."
- "If we encounter a non-vowel, we move the pointer inward."
- "When we find two vowels, we swap them and continue until the pointers meet."

If the interviewer asks for **alternative approaches**, you can say:
- "A brute force approach would store vowels in a list and replace them in a second pass, but it’s unnecessary."
- "The two-pointer method is optimal and runs in `O(N)`."

---

## 🔥 Final Takeaways
- **Identify vowels and use two pointers to swap them efficiently.**
- **Always check for uppercase and lowercase vowels.**
- **Avoid extra space by modifying the string in-place.**
- **Think about edge cases: single vowels, no vowels, long strings.**

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/reverse-vowels-of-a-string/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **fifth problem** in our **LeetCode 75 Study Plan**! Let’s move on to the next problem 🚀.

