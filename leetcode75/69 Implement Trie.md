# Implement Trie (Prefix Tree)

## 📌 Problem Statement

**LeetCode Problem:** [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)  
**Difficulty:** Medium  

**Description:**
A Trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

- `Trie()` Initializes the trie object.
- `void insert(String word)` Inserts the string `word` into the trie.
- `boolean search(String word)` Returns `true` if the string `word` is in the trie (i.e., was inserted before), and `false` otherwise.
- `boolean startsWith(String prefix)` Returns `true` if there is a previously inserted string `word` that has the prefix `prefix`, and `false` otherwise.

### **Example 1:**

```
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
```

### **Constraints:**
- `1 <= word.length, prefix.length <= 2000`
- `word` and `prefix` consist only of lowercase English letters.
- At most `3 * 10^4` calls in total will be made to `insert`, `search`, and `startsWith`.

---

## 📌 Understanding the Question (Explaining to a 10-year-old)

Imagine you have a dictionary where you need to look up words very quickly. A Trie is like a special dictionary tree that helps you find words super fast!

Think of it as a tree where:
- Each branch represents a letter
- As you walk down the tree, you collect letters to form words
- If you reach a special mark on a node, that means the letters you've collected so far form a complete word

For example, if we insert "cat" and "car" in our Trie, it would look something like:

```
     Root
     /
    c
   /
  a
 / \
t   r (Both 't' and 'r' would be marked as end of words)
```

Now, we need to create this tree structure and implement three operations:
1. Insert a new word into the tree
2. Check if a word exists in the tree
3. Check if any word in the tree starts with a given prefix

---

## 🧠 Encouraging the Kid to Think

Before we start coding, let's **ask some questions**:
1. **How should we represent the tree nodes?**
   - Each node needs to store links to child nodes (one for each possible character).
   - Each node should indicate if it represents the end of a word.
2. **How do we efficiently search for words or prefixes?**
   - We'll navigate through the tree one character at a time.
3. **What's the difference between searching for a complete word versus a prefix?**
   - When searching for a word, the final node must be marked as an end of a word.
   - When searching for a prefix, we just need to verify the path exists in the tree.

👉 These considerations will guide our approach!

---

## 🏗️ Breaking the Problem into Steps with Code

### Step 1: Define the TrieNode class
```java
class TrieNode {
    TrieNode[] children;  // Links to child nodes (one for each letter a-z)
    boolean isEndOfWord;  // Indicates if this node represents the end of a word
    
    public TrieNode() {
        children = new TrieNode[26];  // 26 lowercase English letters
        isEndOfWord = false;
    }
}
```

### Step 2: Implement the Trie class with a root node
```java
class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();  // Initialize the root node
    }
}
```

### Step 3: Implement the insert method
```java
public void insert(String word) {
    TrieNode current = root;
    
    // Traverse the trie character by character
    for (char c : word.toCharArray()) {
        int index = c - 'a';  // Convert character to index (0-25)
        
        // If link doesn't exist, create a new node
        if (current.children[index] == null) {
            current.children[index] = new TrieNode();
        }
        
        // Move to the child node
        current = current.children[index];
    }
    
    // Mark the current node as end of word
    current.isEndOfWord = true;
}
```

### Step 4: Implement the search method
```java
public boolean search(String word) {
    TrieNode node = searchPrefix(word);
    
    // Return true only if the node exists and is marked as end of word
    return node != null && node.isEndOfWord;
}
```

### Step 5: Implement the startsWith method
```java
public boolean startsWith(String prefix) {
    // We just need to check if the prefix exists in the trie
    return searchPrefix(prefix) != null;
}
```

### Step 6: Implement the helper searchPrefix method
```java
private TrieNode searchPrefix(String word) {
    TrieNode current = root;
    
    // Traverse the trie character by character
    for (char c : word.toCharArray()) {
        int index = c - 'a';
        
        // If link doesn't exist, return null
        if (current.children[index] == null) {
            return null;
        }
        
        // Move to the child node
        current = current.children[index];
    }
    
    return current;
}
```

---

## 🛠️ Complete Solution with Detailed Comments

```java
class Trie {
    /**
     * A TrieNode represents each node in the Trie.
     * It contains links to child nodes and a flag indicating if it's the end of a word.
     */
    class TrieNode {
        TrieNode[] children;  // Links to child nodes (one for each letter a-z)
        boolean isEndOfWord;  // Indicates if this node represents the end of a word
        
        public TrieNode() {
            children = new TrieNode[26];  // 26 lowercase English letters
            isEndOfWord = false;
        }
    }
    
    private TrieNode root;  // Root of the Trie
    
    /**
     * Initialize the Trie with an empty root node.
     */
    public Trie() {
        root = new TrieNode();
    }
    
    /**
     * Inserts a word into the trie.
     * Time Complexity: O(m), where m is the length of the word
     * 
     * @param word The word to insert
     */
    public void insert(String word) {
        TrieNode current = root;
        
        // Traverse the trie character by character
        for (char c : word.toCharArray()) {
            int index = c - 'a';  // Convert character to index (0-25)
            
            // If link doesn't exist, create a new node
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            // Move to the child node
            current = current.children[index];
        }
        
        // Mark the current node as end of word
        current.isEndOfWord = true;
    }
    
    /**
     * Returns if the word is in the trie.
     * Time Complexity: O(m), where m is the length of the word
     * 
     * @param word The word to search for
     * @return true if the word exists in the trie, false otherwise
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        
        // Return true only if the node exists and is marked as end of word
        return node != null && node.isEndOfWord;
    }
    
    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * Time Complexity: O(m), where m is the length of the prefix
     * 
     * @param prefix The prefix to search for
     * @return true if any word with this prefix exists, false otherwise
     */
    public boolean startsWith(String prefix) {
        // We just need to check if the prefix path exists in the trie
        return searchPrefix(prefix) != null;
    }
    
    /**
     * Helper method to search for a word or prefix in the trie.
     * 
     * @param word The word or prefix to search for
     * @return The node corresponding to the last character if found, null otherwise
     */
    private TrieNode searchPrefix(String word) {
        TrieNode current = root;
        
        // Traverse the trie character by character
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            
            // If link doesn't exist, return null
            if (current.children[index] == null) {
                return null;
            }
            
            // Move to the child node
            current = current.children[index];
        }
        
        return current;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

### ⏳ Time and Space Complexity Analysis
- **Time Complexity:**
  - `insert()`: O(m) where m is the length of the word
  - `search()`: O(m) where m is the length of the word
  - `startsWith()`: O(m) where m is the length of the prefix
- **Space Complexity:**
  - O(n * m) where n is the number of words and m is the average length of words
  - In the worst case, for completely distinct words, this could be O(n * m)
  - For highly overlapping words, the space complexity is much better

---

## 🔄 Alternative Implementation Using a HashMap

Instead of using a fixed-size array for children, we can use a HashMap to save space when the alphabet is large or sparse:

```java
class Trie {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        
        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }
}
```

---

## 📢 Explaining the Solution to an Interviewer

If asked to explain my approach, I would say:

"I implemented a Trie, also known as a prefix tree, which is a tree-based data structure optimized for storing and retrieving strings.

1. **Data Structure Design:**
   - I created a `TrieNode` class with two main components:
     - An array of 26 child nodes (one for each lowercase letter)
     - A boolean flag to mark if the node represents the end of a word

2. **Core Operations:**
   - **Insert:** I traverse the trie character by character, creating new nodes as needed and marking the final node as an end of word.
   - **Search:** I traverse the trie looking for the word and return true only if the final node exists and is marked as an end of word.
   - **StartsWith:** Similar to search, but I only check if the path exists in the trie without verifying if it's a complete word.

3. **Efficiency:**
   - Both insert and search operations have time complexity O(m), where m is the length of the word.
   - The space complexity is O(n * m) in the worst case, where n is the number of words and m is their average length, but could be much better with overlapping prefixes.

4. **Optimizations:**
   - I created a helper method `searchPrefix` to avoid code duplication between search and startsWith.
   - For larger or sparse alphabets, we could use a HashMap instead of a fixed-size array to save space."

---

## 🌳 Trie Applications and Advantages

Tries have several important applications and advantages:

1. **Autocomplete/Predictive Text:** Suggests words as users type based on common prefixes.

2. **Spell Checking:** Checks if a word exists in a dictionary efficiently.

3. **IP Routing:** Used in routers for longest prefix matching.

4. **Word Games:** Efficiently validates words in games like Boggle or Scrabble.

5. **Advantages over Hash Tables:**
   - Faster for prefix-based operations (e.g., finding all words starting with "app")
   - No hash collisions
   - More space-efficient for storing words with common prefixes

6. **Advantages over Binary Search Trees:**
   - O(m) lookup time regardless of dictionary size (m is word length)
   - More efficient for prefix operations

---

## 🔥 Final Takeaways

- **Tries excel at string operations**, particularly for prefix lookups.
- **The time complexity for operations is O(m)** where m is the length of the word, not the size of the dictionary.
- **Space efficiency comes from sharing prefixes** - words like "apple" and "application" share storage for their common prefix.
- **Each node typically needs links to 26 children** for lowercase English letters, but this can be optimized with a HashMap for sparse alphabets.
- **Marking end-of-word nodes is crucial** to distinguish between full words and mere prefixes.
- **Tries are foundational data structures** for more complex algorithms like text compression, regex matching, and suffix trees.

---

## 📜 Disclaimer
This problem and its description are sourced from [LeetCode](https://leetcode.com/problems/implement-trie-prefix-tree/). LeetCode owns the copyright to the problem statements. This document is intended for **educational purposes** only, to help learners understand and solve the problem effectively.

---

This is the **seventieth problem** in our **LeetCode 75 Study Plan**! Let's move on to the next problem 🚀.