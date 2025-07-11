# Substring with Concatenation of All Words

## Problem Statement

You are given a string `s` and an array of strings `words` of the same length. Return all starting indices of substring(s) in `s` that is a concatenation of each word in `words` exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

## Examples

**Example 1:**
```
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: The substring starting at index 0 is "barfoo", which is a concatenation of ["bar","foo"] (in any order).
The substring starting at index 9 is "foobar", which is a concatenation of ["foo","bar"] (in any order).
```

**Example 2:**
```
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
```

**Example 3:**
```
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
```

## Approach: Sliding Window with Word Frequencies

This problem requires a more advanced application of the sliding window pattern combined with a hash map to track word frequencies.

### Algorithm:

1. Determine the length of a single word and the total length of all words combined
2. Iterate through possible starting positions of the concatenated substring
3. Use a hash map to track the frequency of each word in the current window
4. Compare the frequency map of the window with the expected frequency map of `words`
5. If they match, add the starting index to the result

## Code Solution

```java
import java.util.*;

public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLength = words[0].length();
        int totalWords = words.length;
        int substringLength = wordLength * totalWords;
        
        // If the entire concatenation is longer than s, no solution exists
        if (substringLength > s.length()) {
            return result;
        }
        
        // Create a frequency map for words
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // Check each possible starting position
        for (int i = 0; i <= s.length() - substringLength; i++) {
            // Create a window frequency map
            Map<String, Integer> windowCount = new HashMap<>();
            boolean valid = true;
            
            // Check each word in the current window
            for (int j = 0; j < totalWords; j++) {
                int wordStart = i + j * wordLength;
                String currentWord = s.substring(wordStart, wordStart + wordLength);
                
                // If current word is not in our list, this window is invalid
                if (!wordCount.containsKey(currentWord)) {
                    valid = false;
                    break;
                }
                
                // Add current word to window count
                windowCount.put(currentWord, windowCount.getOrDefault(currentWord, 0) + 1);
                
                // Check if current word appears more times than it should
                if (windowCount.get(currentWord) > wordCount.get(currentWord)) {
                    valid = false;
                    break;
                }
            }
            
            // If all words were found the correct number of times, this is a valid window
            if (valid) {
                result.add(i);
            }
        }
        
        return result;
    }
}
```

## Optimized Solution: Sliding Window per Word Length

We can optimize the above solution by using multiple sliding windows, one for each possible starting position modulo the word length:

```java
import java.util.*;

public class SubstringWithConcatenationOptimized {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLength = words[0].length();
        int totalWords = words.length;
        int substringLength = wordLength * totalWords;
        
        // Create a frequency map for words
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // Start a sliding window for each possible starting position modulo wordLength
        for (int i = 0; i < wordLength; i++) {
            int left = i;
            int count = 0; // Number of valid words found
            Map<String, Integer> windowCount = new HashMap<>();
            
            for (int right = i; right <= s.length() - wordLength; right += wordLength) {
                String currentWord = s.substring(right, right + wordLength);
                
                // If current word is in our list, process it
                if (wordCount.containsKey(currentWord)) {
                    windowCount.put(currentWord, windowCount.getOrDefault(currentWord, 0) + 1);
                    count++;
                    
                    // Shrink window from the left if we have too many occurrences of this word
                    while (windowCount.get(currentWord) > wordCount.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }
                    
                    // If we found all words, add the starting index to the result
                    if (count == totalWords) {
                        result.add(left);
                        
                        // Move left pointer and update counts
                        String leftWord = s.substring(left, left + wordLength);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }
                } else {
                    // Current word is not in our list, reset window
                    windowCount.clear();
                    count = 0;
                    left = right + wordLength;
                }
            }
        }
        
        return result;
    }
}
```

## Complexity Analysis

- **Basic Solution**:
  - **Time Complexity**: O(N * M * K) where N is the length of string s, M is the number of words, and K is the length of each word.
  - **Space Complexity**: O(M) for storing the word frequency maps.

- **Optimized Solution**:
  - **Time Complexity**: O(N * K) where N is the length of the string and K is the length of each word.
  - **Space Complexity**: O(M) for storing the word frequency maps.

## Key Insights

1. This problem demonstrates an advanced application of the sliding window pattern for finding concatenated substrings.
2. The key insight is to treat words as units rather than individual characters when constructing the window.
3. Using hash maps to track word frequencies allows us to efficiently verify if a window contains a valid concatenation.
4. The optimized solution leverages the fact that all words have the same length to perform more efficient sliding windows.
5. This problem combines techniques from both sliding window and hash map patterns to solve a complex substring matching problem.
