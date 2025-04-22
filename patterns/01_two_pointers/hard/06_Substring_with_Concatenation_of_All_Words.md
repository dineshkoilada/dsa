# Substring with Concatenation of All Words (LeetCode #30)

## Problem Statement
You are given a string `s` and an array of strings `words`. All the strings of `words` are of the same length.

A concatenated substring in `s` is a substring that contains all the strings of any permutation of `words` concatenated.

- For example, if `words = ["ab","cd","ef"]`, then `"abcdef"`, `"abefcd"`, `"cdabef"`, `"cdefab"`, `"efabcd"`, and `"efcdab"` are all concatenated strings. `"acdbef"` is not a concatenated substring because it is not the concatenation of any permutation of `words`.

Return the starting indices of all the concatenated substrings in `s`. You can return the answer in any order.

## Example 1:
```
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```

## Example 2:
```
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
```

## Example 3:
```
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
```

## Constraints:
- 1 <= s.length <= 10^4
- s consists of lowercase English letters.
- 1 <= words.length <= 5000
- 1 <= words[i].length <= 30
- words[i] consists of lowercase English letters.

## Two Pointers Approach
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLength = words[0].length();
        int wordCount = words.length;
        int windowSize = wordLength * wordCount;
        
        if (s.length() < windowSize) {
            return result;
        }
        
        // Create a frequency map for words
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
        
        // Slide the window for each possible starting point
        for (int i = 0; i <= s.length() - windowSize; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j = 0; // Word index
            
            while (j < wordCount) {
                int wordStart = i + j * wordLength;
                String currentWord = s.substring(wordStart, wordStart + wordLength);
                
                // If the current word is not in our required words or we've seen too many
                if (!wordCountMap.containsKey(currentWord) || 
                    seenWords.getOrDefault(currentWord, 0) >= wordCountMap.get(currentWord)) {
                    break;
                }
                
                // Add the current word to seen words
                seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);
                j++;
            }
            
            // If we've seen all words in the right frequency, add the starting index
            if (j == wordCount) {
                result.add(i);
            }
        }
        
        return result;
    }
}
```

## Time Complexity
O(n * m * k) where n is the length of the string s, m is the length of each word, and k is the number of words.

## Space Complexity
O(k) for the hash maps used to count the frequency of words.