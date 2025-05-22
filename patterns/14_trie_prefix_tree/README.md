# Trie (Prefix Tree) Pattern 🌳

## 📌 Introduction: The Power of Prefixes

Imagine you're using your phone's contact search. As you type each letter, the list of suggestions narrows down instantly. This is the magic of the **Trie** (pronounced "try") or **Prefix Tree**—a tree-like data structure that makes searching for words and prefixes lightning fast!

### 🎬 Real-World Analogies:

1. **Phonebook Autocomplete** 📱
   ```
   Type: "A" → [Alice, Alan, Amy]
   Type: "Al" → [Alice, Alan]
   Type: "Ali" → [Alice]
   ```
2. **Dictionary Index** 📖
   - Flip to the section for "pre-" and instantly find all words starting with "pre".
3. **File System Paths** 💻
   - Each folder is a node; subfolders and files branch out from there.

The Trie pattern is your secret weapon for:
- 🔍 Fast prefix and word searches
- 📝 Autocomplete and spell-check
- 🎮 Word games (Boggle, Scrabble)
- 🌐 Longest prefix matching (e.g., IP routing)

---

## 🧠 How to Recognize a Trie Problem

### 🔍 Key Pattern Recognition Signals:
1. **The "Prefix" Clue**
   - "Starts with", "autocomplete", or "find all words with prefix"
2. **The "Dictionary" Hint**
   - Efficiently storing and searching a large set of words
3. **The "Shared Path" Signal**
   - Many words share common beginnings (prefixes)

### 🤔 Essential Questions to Ask:
- Are you searching for whole words or just prefixes?
- Are insertions, deletions, or prefix checks required?
- Are all inputs lowercase/uppercase? Is case sensitivity important?
- Are duplicate words allowed?

---

## 🎨 Visual Problem-Solving Framework

### Trie Visualization:
```
Insert: "cat", "car", "dog"

        (root)
        /    \
      c       d
     /         \
    a           o
   / \           \
  t   r           g
(end) (end)     (end)

- Each node is a character.
- Paths from root to leaf form words.
- End-of-word flags mark valid words.
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- Are you searching for whole words or prefixes?
- Are insertions and deletions required?

### ✅ **2. Ask Questions Before Defining Base Cases**
- What should happen if the prefix or word doesn't exist?
- Are empty strings valid input?

### ✅ **3. Identify Base Cases**
- If the trie is empty, return false for any search.
- If inserting a word, create nodes for each new character.

### ✅ **4. Write Pseudo-Code for Base Cases**

```
class TrieNode:
    children: map of character -> TrieNode
    isEndOfWord: boolean

function insert(word):
    start from root
    for each character in word:
        if character not in current node's children:
            add new TrieNode
        move to the next node
    mark the last node as end of word

function search(word):
    start from root
    for each character in word:
        if character not found:
            return false
    return isEndOfWord flag at the last node

function startsWith(prefix):
    start from root
    for each character in prefix:
        if character not found:
            return false
    return true
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return true;
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty strings as input.
- Searching for a non-existing word.
- Inserting duplicate words.

### ✅ **7. How to Predict Time and Space Complexity**

| Operation     | Time Complexity | Space Complexity |
|---------------|-----------------|------------------|
| Insert        | O(m)            | O(m)             |
| Search        | O(m)            | O(1)             |
| StartsWith    | O(m)            | O(1)             |

**How to derive these complexities:**
- **Time Complexity:** Each character of the word (length `m`) is processed once.
- **Space Complexity:** New nodes are created only when necessary.

---

## 📚 Example 1: Easy Problem - Implement Trie (Prefix Tree)

**Problem:**
Design a trie with insert, search, and startsWith methods.

**Input:**
```
insert("apple")
search("apple") -> true
search("app") -> false
startsWith("app") -> true
insert("app")
search("app") -> true
```

**Expected Output:**
```
true
false
true
true
```

### 🔑 **Solution Steps**
1. Initialize the Trie.
2. Insert words and check for their presence or prefixes.

### ✅ **Code:**
(Same as code skeleton above)

### ⏱️ **Time and Space Complexity:**
- **Time:** O(m) — Each character is processed once.
- **Space:** O(m) — Each new word adds nodes.

---

## 📚 Example 2: Medium Problem - Word Search II

**Problem:**
Find all words from a given list in a 2D board.

**Input:**
```
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
```

**Expected Output:** `["eat","oath"]`

### 🔑 **Solution Steps**
1. Build a Trie using the word list.
2. Use DFS to explore valid words in the board.
3. Mark visited cells and backtrack.

### ✅ **Code:**
```java
public class WordSearch {
    private Set<String> result;
    private Trie trie;

    public List<String> findWords(char[][] board, String[] words) {
        result = new HashSet<>();
        trie = new Trie();
        for (String word : words) trie.insert(word);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, "", trie);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int i, int j, String path, Trie trie) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') return;
        path += board[i][j];
        if (!trie.startsWith(path)) return;
        if (trie.search(path)) result.add(path);

        char temp = board[i][j];
        board[i][j] = '#';
        dfs(board, i + 1, j, path, trie);
        dfs(board, i - 1, j, path, trie);
        dfs(board, i, j + 1, path, trie);
        dfs(board, i, j - 1, path, trie);
        board[i][j] = temp;
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N * 4^L) — N words, exploring up to 4 directions for each letter.
- **Space:** O(N + L) — Trie storage and recursion stack.

---

## 📚 Example 3: Hard Problem - Replace Words

**Problem:**
Replace words in a sentence using the shortest root word from a dictionary.

**Input:**
```
dictionary = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
```

**Expected Output:**
```
"the cat was rat by the bat"
```

### 🔑 **Solution Steps**
1. Insert dictionary words into a Trie.
2. For each word in the sentence, search for the shortest prefix.
3. Replace the word if a prefix is found.

### ✅ **Code:**
```java
public class ReplaceWords {
    public static String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) trie.insert(word);

        StringBuilder result = new StringBuilder();
        for (String word : sentence.split(" ")) {
            TrieNode node = trie.root;
            StringBuilder replacement = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (node.children.containsKey(c) && !node.isEndOfWord) {
                    replacement.append(c);
                    node = node.children.get(c);
                } else break;
            }
            result.append((node.isEndOfWord) ? replacement.toString() : word).append(" ");
        }

        return result.toString().trim();
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(N * L) — N words, each with length L.
- **Space:** O(N * L) — Trie size.

---

## 📚 Key Takeaways

1. Use Trie for efficiently handling word searches, prefixes, and autocomplete functionalities.
2. Insert, search, and prefix-check operations run in O(m) time, where `m` is the length of the word.
3. Ideal for problems involving string manipulation, dictionary lookups, and prefix matching.
4. Tries are space-efficient for overlapping prefixes but can consume more memory for sparse datasets.

---

Next, let's dive into the **Graph Algorithms Pattern** for solving problems that involve finding subarrays or substrings within specific constraints!

