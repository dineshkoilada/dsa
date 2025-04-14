# Search Suggestions System

## 📌 Problem Statement

**LeetCode Problem:** [1268. Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/)  
**Difficulty:** Medium  

**Description:**
You are given an array of strings `products` and a string `searchWord`.

Design a system that suggests at most three product names from `products` after each character of `searchWord` is typed. Suggested products should have common prefix with `searchWord`. If there are more than three products with a common prefix, return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of `searchWord` is typed.

### **Example 1:**
**Input:** 
```
products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
```
**Output:** 
```
[
  ["mobile","moneypot","monitor"],
  ["mobile","moneypot","monitor"],
  ["mouse","mousepad"],
  ["mouse","mousepad"],
  ["mouse","mousepad"]
]
```
**Explanation:** 
- After typing 'm': Products with prefix 'm' are "mobile", "moneypot", "monitor", "mouse", and "mousepad". The three lexicographically minimum are "mobile", "moneypot", and "monitor".
- After typing 'mo': Products with prefix 'mo' are "mobile", "moneypot", "monitor", "mouse", and "mousepad". The three lexicographically minimum are still "mobile", "moneypot", and "monitor".
- After typing 'mou': Products with prefix 'mou' are "mouse" and "mousepad". We show these two.
- After typing 'mous': Products with prefix 'mous' are "mouse" and "mousepad". We show these two.
- After typing 'mouse': Products with prefix 'mouse' are "mouse" and "mousepad". We show these two.

### **Example 2:**
**Input:** 
```
products = ["havana"], searchWord = "havana"
```
**Output:** 
```
[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
```
**Explanation:** 
- The only product that matches each prefix is "havana".

### **Example 3:**
**Input:** 
```
products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
```
**Output:** 
```
[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
```
**Explanation:** 
- After typing 'b': Products with prefix 'b' are "baggage", "bags", "banner", and "box". The three lexicographically minimum are "baggage", "bags", and "banner".
- After typing 'ba': Products with prefix 'ba' are "baggage", "bags", and "banner". The three lexicographically minimum are still "baggage", "bags", and "banner".
- After typing 'bag': Products with prefix 'bag' are "baggage" and "bags". We show these two.
- After typing 'bags': Only "bags" has the prefix 'bags'. We show this one.

### **Constraints:**
- `1 <= products.length <= 1000`
- `1 <= products[i].length <= 3000`
- `1 <= sum(products[i].length) <= 2 * 10^4`
- All strings of `products` are unique.
- `products[i]` consists of lowercase English letters.
- `1 <= searchWord.length <= 1000`
- `searchWord` consists of lowercase English letters.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you're typing a search query on a website, like when you search for something on Amazon or Google. As you type each letter, the website suggests possible words that match what you might be looking for.

For example, if you start typing "b", it might suggest "books", "bicycle", and "basketball". Then if you type "bo", it narrows down to "books", "board games", etc.

In this problem:
1. We have a list of products (like items in an online store).
2. As we type each letter of our search word, we need to show up to 3 suggestions.
3. These suggestions must start with what we've typed so far.
4. If more than 3 products match, we show the 3 that come first in alphabetical order.

Let's say the products are ["cat", "car", "dog", "deer"] and we're searching for "car":
- After typing 'c': We'd suggest ["car", "cat"] (only 2 products start with 'c').
- After typing 'ca': We'd still suggest ["car", "cat"].
- After typing 'car': We'd only suggest ["car"].

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **What data structure should we use to efficiently find words with a common prefix?**
   - We can sort the products array to group words with common prefixes.
   - Alternatively, we could use a Trie (prefix tree) for efficient prefix searching.
2. **How do we ensure we get the lexicographically smallest products?**
   - Sorting the products list will ensure this.
3. **How will we build our suggestion list efficiently for each prefix?**
   - We'll need to search for matching products after each character is typed.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

Let's explore two different approaches to this problem:

### Approach 1: Using Sorting and Binary Search

1. Sort the products array to ensure lexicographical ordering.
2. For each prefix (after typing each character), find the first product that matches the prefix using binary search.
3. Collect up to three products starting from that position that share the same prefix.

```java
// Step 1: Sort the products array
Arrays.sort(products);

// Step 2: For each prefix of searchWord
List<List<String>> result = new ArrayList<>();
for (int i = 1; i <= searchWord.length(); i++) {
    String prefix = searchWord.substring(0, i);
    
    // Find the index of the first product that matches or exceeds the prefix
    int start = Collections.binarySearch(Arrays.asList(products), prefix);
    if (start < 0) start = -start - 1; // Convert binary search negative result
    
    // Collect up to three matching products
    List<String> suggestions = new ArrayList<>();
    for (int j = start; j < Math.min(start + 3, products.length); j++) {
        if (products[j].startsWith(prefix)) {
            suggestions.add(products[j]);
        } else {
            break; // No more matches found
        }
    }
    
    result.add(suggestions);
}
```

### Approach 2: Using a Trie (Prefix Tree)

1. Build a Trie from the products array.
2. For each prefix, navigate to the corresponding node in the Trie.
3. Perform a DFS from that node to collect up to three products that share the prefix.

Let's start with defining our TrieNode:

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    List<String> suggestions = new ArrayList<>();
}
```

Now, let's build our Trie and implement the search:

```java
// Step 1: Build the Trie
TrieNode root = new TrieNode();
for (String product : products) {
    TrieNode node = root;
    for (char c : product.toCharArray()) {
        node = node.children.computeIfAbsent(c, k -> new TrieNode());
        if (node.suggestions.size() < 3) {
            node.suggestions.add(product);
        }
    }
}

// Step 2: Search the Trie for each prefix
List<List<String>> result = new ArrayList<>();
TrieNode node = root;
boolean pathExists = true;

for (char c : searchWord.toCharArray()) {
    if (pathExists && node.children.containsKey(c)) {
        node = node.children.get(c);
        result.add(node.suggestions);
    } else {
        // No products match this prefix
        pathExists = false;
        result.add(new ArrayList<>());
    }
}

return result;
```

---

## 🛠️ Complete Solution with Detailed Comments

### Solution 1: Sorting and Binary Search Approach

```java
class SearchSuggestionsSystem {
    /**
     * Suggests up to three products for each prefix of the search word.
     * Time Complexity: O(n*log(n) + m*log(n) + m*k) where:
     *   - n is the number of products
     *   - m is the length of the search word
     *   - k is the number of suggestions (limited to 3)
     * Space Complexity: O(m*k) for storing the result
     * 
     * @param products Array of product names
     * @param searchWord The search query being typed
     * @return List of suggestions for each prefix of the search word
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Sort the products array to ensure lexicographical ordering
        Arrays.sort(products);
        
        List<List<String>> result = new ArrayList<>();
        
        // Process each prefix of the search word
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            
            // Binary search to find the first product that matches or exceeds the prefix
            int start = binarySearch(products, prefix);
            List<String> suggestions = new ArrayList<>();
            
            // Collect up to three matching products
            for (int i = start; i < Math.min(start + 3, products.length); i++) {
                if (products[i].startsWith(prefix)) {
                    suggestions.add(products[i]);
                } else {
                    break; // No more matches found
                }
            }
            
            result.add(suggestions);
        }
        
        return result;
    }
    
    /**
     * Helper method to find the index of the first product that matches or exceeds the given prefix.
     * 
     * @param products Sorted array of product names
     * @param prefix The current search prefix
     * @return Index of the first product that potentially matches the prefix
     */
    private int binarySearch(String[] products, String prefix) {
        int left = 0;
        int right = products.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].compareTo(prefix) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left; // First position where product >= prefix
    }
}
```

### Solution 2: Trie (Prefix Tree) Approach

```java
class SearchSuggestionsSystem {
    /**
     * TrieNode class to represent each node in the Trie.
     * Each node stores up to 3 suggestions that share the prefix.
     */
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<String> suggestions = new ArrayList<>();
    }
    
    /**
     * Suggests up to three products for each prefix of the search word using a Trie.
     * Time Complexity: O(n*L + m) where:
     *   - n is the number of products
     *   - L is the average length of product names
     *   - m is the length of the search word
     * Space Complexity: O(n*L + m*k) where k is the number of suggestions (limited to 3)
     * 
     * @param products Array of product names
     * @param searchWord The search query being typed
     * @return List of suggestions for each prefix of the search word
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Sort products to ensure lexicographical ordering of suggestions
        Arrays.sort(products);
        
        // Build the Trie
        TrieNode root = new TrieNode();
        for (String product : products) {
            TrieNode node = root;
            for (char c : product.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
                if (node.suggestions.size() < 3) {
                    node.suggestions.add(product);
                }
            }
        }
        
        // Search the Trie for each prefix
        List<List<String>> result = new ArrayList<>();
        TrieNode node = root;
        boolean pathExists = true;
        
        for (char c : searchWord.toCharArray()) {
            if (pathExists && node.children.containsKey(c)) {
                node = node.children.get(c);
                result.add(node.suggestions);
            } else {
                // No products match this prefix
                pathExists = false;
                result.add(new ArrayList<>());
            }
        }
        
        return result;
    }
}
```

### ⏳ Time and Space Complexity Analysis
- **Sorting Approach:**
  - Time Complexity: O(n*log(n) + m*log(n) + m*k) where n is number of products, m is length of searchWord, and k is at most 3
  - Space Complexity: O(m*k) for storing the result
- **Trie Approach:**
  - Time Complexity: O(n*L + m) where n is number of products, L is average product length, m is searchWord length
  - Space Complexity: O(n*L + m*k) where k is at most 3

---

## 🔄 Alternative Implementation Using Two Pointers

Here's another approach that avoids binary search and uses two pointers to find the range of matching products:

```java
public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Arrays.sort(products);
    List<List<String>> result = new ArrayList<>();
    
    for (int i = 0; i < searchWord.length(); i++) {
        String prefix = searchWord.substring(0, i + 1);
        List<String> suggestions = new ArrayList<>();
        
        // Find matching products for the current prefix
        for (String product : products) {
            if (product.startsWith(prefix) && suggestions.size() < 3) {
                suggestions.add(product);
            }
        }
        
        result.add(suggestions);
    }
    
    return result;
}
```

While this is simpler to implement, it's less efficient as we re-scan the entire products array for each prefix. The time complexity is O(n*log(n) + m*n) where m is the length of searchWord and n is the number of products.

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I implemented two solutions to the Search Suggestions System problem:

1. **Sorting and Binary Search:**
   - First, I sort the products array to ensure lexicographical ordering.
   - For each prefix of the search word, I use binary search to efficiently find the first product that matches or exceeds the prefix.
   - Then, I collect up to three products starting from that position that share the prefix.
   - The time complexity is O(n*log(n) + m*log(n) + m*k), where n is the number of products, m is the length of the search word, and k is at most 3.

2. **Trie-based Solution:**
   - I build a Trie (prefix tree) from the sorted products array, storing up to three suggestions at each node.
   - For each prefix of the search word, I navigate to the corresponding node in the Trie.
   - The suggestions stored at that node are already the three lexicographically smallest products with that prefix.
   - The time complexity is O(n*L + m), where L is the average length of product names.

The Trie solution is more efficient for multiple searches with the same product set, as we can reuse the Trie. The sorting approach might be simpler to implement and understand, especially if we only need to perform this search once."

---

## 🔍 Applications and Extensions

This problem resembles real-world autocomplete and search suggestion features:

1. **E-commerce Search:** Online stores suggest products as you type to help you find what you're looking for.

2. **Search Engines:** Google, Bing, and other search engines suggest queries based on what you're typing.

3. **Mobile Keyboards:** Predictive text suggestions use similar techniques.

4. **Code Editors:** IDE suggestions for function names and variables.

Extensions to this problem could include:

- **Fuzzy Matching:** Suggesting products even with slight misspellings.
- **Personalized Suggestions:** Prioritizing suggestions based on user history.
- **Categorized Suggestions:** Grouping suggestions by categories.

---

## 🔥 Final Takeaways

- **Tries and sorting-based approaches are both viable solutions** for prefix-matching problems.
- **The Trie data structure excels at prefix operations** but requires more upfront memory and setup.
- **Sorting combined with binary search can be very efficient** for searching prefixes in a sorted collection.
- **Optimizing for multiple searches** might lead to different design choices than optimizing for a single search.
- **Real-world search suggestion systems are more complex** and often incorporate user behavior, popularity metrics, and fuzzy matching.
- **Understanding when to use each approach** is key to designing efficient search systems.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/search-suggestions-system/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventy-first problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.