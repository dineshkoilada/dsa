# Longest Nice Substring

## Problem Statement

A string `s` is **nice** if, for every letter of the alphabet that `s` contains, both its uppercase and lowercase versions appear in `s`. For example, "abABB" is nice because 'a', 'b', 'A', and 'B' appear in it, and all uppercase and lowercase letters of 'a' and 'b' also appear. However, "abA" is not nice because 'b' appears but 'B' does not.

Given a string `s`, return the longest substring of `s` that is **nice**. If there are multiple such substrings, return the one that occurs earliest. If there is no nice substring, return an empty string.

## Examples

**Example 1:**
```
Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.
```

**Example 2:**
```
Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
```

**Example 3:**
```
Input: s = "c"
Output: ""
Explanation: There are no nice substrings.
```

## Approach 1: Brute Force with Sliding Window

We can use a sliding window approach to check all possible substrings and find the longest nice one. For each substring, we'll verify if it's nice by checking if both uppercase and lowercase versions of each letter appear.

### Algorithm:

1. Generate all possible substrings of `s`
2. For each substring, check if it's nice
3. Keep track of the longest nice substring found
4. Return the longest nice substring or an empty string if none is found

## Code Solution 1: Brute Force

```java
public class LongestNiceSubstring {
    public String longestNiceSubstring(String s) {
        String result = "";
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isNice(substring) && substring.length() > result.length()) {
                    result = substring;
                }
            }
        }
        
        return result;
    }
    
    private boolean isNice(String s) {
        Set<Character> set = new HashSet<>();
        
        // Add all characters to the set
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        
        // Check if both uppercase and lowercase versions exist
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c) && !set.contains(Character.toUpperCase(c))) {
                return false;
            }
            if (Character.isUpperCase(c) && !set.contains(Character.toLowerCase(c))) {
                return false;
            }
        }
        
        return true;
    }
}
```

## Approach 2: Divide and Conquer

A more efficient approach uses divide and conquer. We split the string at characters that don't have both uppercase and lowercase counterparts in the string.

### Algorithm:

1. Find all characters in the string that don't have both uppercase and lowercase counterparts
2. If there are no such characters, the entire string is nice, so return it
3. Otherwise, split the string at these characters
4. Recursively find the longest nice substring in each part
5. Return the longest nice substring found

## Code Solution 2: Divide and Conquer

```java
public class LongestNiceSubstringDivideConquer {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";
        
        Set<Character> set = new HashSet<>();
        
        // Add all characters to the set
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        
        // Find the first character that doesn't have both uppercase and lowercase versions
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If uppercase or lowercase version doesn't exist, split at this position
            if (!set.contains(Character.toUpperCase(c)) || !set.contains(Character.toLowerCase(c))) {
                String s1 = longestNiceSubstring(s.substring(0, i));
                String s2 = longestNiceSubstring(s.substring(i + 1));
                
                // Return the longer substring
                return s1.length() >= s2.length() ? s1 : s2;
            }
        }
        
        // If we get here, the entire string is nice
        return s;
    }
}
```

## Complexity Analysis

- **Brute Force Approach**:
  - **Time Complexity**: O(n³) where n is the length of the string. We check O(n²) substrings, and each check is O(n).
  - **Space Complexity**: O(n) for storing the substring and HashSet.

- **Divide and Conquer Approach**:
  - **Time Complexity**: O(n log n) in the average case, where n is the length of the string.
  - **Space Complexity**: O(n) for the recursive call stack and HashSet.

## Key Insights

1. This problem introduces the concept of a "nice" string, which requires both uppercase and lowercase versions of each letter to be present.
2. The brute force approach checks all possible substrings, which is intuitive but inefficient.
3. The divide and conquer approach is more efficient by splitting the string at characters that don't satisfy the "nice" property.
4. When identifying "non-nice" characters, we only need to check if both uppercase and lowercase versions exist in the set, not in the substring currently being examined.
5. The solution ensures we return the earliest occurring substring if there are multiple longest nice substrings.
