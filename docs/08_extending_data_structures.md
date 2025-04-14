# Extending Data Structures for FAANG Interviews

## 🎯 Introduction

While Java's built-in collections are sufficient for most interview problems, understanding how to extend and customize data structures can give you a significant advantage in complex FAANG interview scenarios. This guide focuses on practical ways to extend data structures for specialized interview problems.

---

## 🔍 Why Extend Data Structures for Interviews?

Extending data structures in interviews allows you to:

1. **Add specific functionality** needed for particular algorithm problems
2. **Track additional information** that built-in structures don't maintain
3. **Combine multiple data structure operations** into atomic units
4. **Optimize for specific access patterns** that might be tested
5. **Create custom data structures** that interviewers specifically ask for

---

## 📌 Creating Interview-Specific Classes

Many interview problems require custom data structures that don't exist in the standard library.

### 📌 Example: Trie for String Problems
Tries (prefix trees) are common in string search problems but aren't included in Java's standard library.

```java
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isEndOfWord = true;
    }
    
    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }
    
    // Check if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    
    // Helper method to find a node matching prefix
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

// Usage in an interview problem:
// Problem: Design an autocomplete system
class AutocompleteSystem {
    private Trie trie = new Trie();
    
    public AutocompleteSystem(String[] words) {
        for (String word : words) {
            trie.insert(word);
        }
    }
    
    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();
        findAllWordsWithPrefix(prefix, suggestions);
        return suggestions;
    }
    
    private void findAllWordsWithPrefix(String prefix, List<String> suggestions) {
        // Implementation would find all words with given prefix
        // (Full implementation omitted for brevity)
    }
}
```

### 📌 Example: Disjoint Set (Union-Find)
Union-Find isn't included in Java's standard library but is essential for graph connectivity problems.

```java
class DisjointSet {
    private int[] parent;
    private int[] rank;
    
    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        
        // Initialize: each element is its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
    
    // Union with rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;
        
        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    
    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}

// Usage in an interview problem:
// Problem: Number of Connected Components in an Undirected Graph
public int countComponents(int n, int[][] edges) {
    DisjointSet ds = new DisjointSet(n);
    
    // Union connected vertices
    for (int[] edge : edges) {
        ds.union(edge[0], edge[1]);
    }
    
    // Count unique components
    Set<Integer> components = new HashSet<>();
    for (int i = 0; i < n; i++) {
        components.add(ds.find(i));
    }
    
    return components.size();
}
```

---

## 🔄 Extending Built-in Data Structures

Sometimes it's more efficient to extend Java's built-in data structures to add specific functionality.

### 📌 Example: Count-Tracking HashMap
Count occurrences while tracking additional data.

```java
class CountMap<K> extends HashMap<K, Integer> {
    // Increment count for a key
    public void increment(K key) {
        put(key, getOrDefault(key, 0) + 1);
    }
    
    // Decrement count for a key
    public void decrement(K key) {
        int count = getOrDefault(key, 0);
        if (count <= 1) {
            remove(key);
        } else {
            put(key, count - 1);
        }
    }
    
    // Get most frequent key
    public K getMostFrequent() {
        K mostFrequentKey = null;
        int maxCount = 0;
        
        for (Map.Entry<K, Integer> entry : entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentKey = entry.getKey();
            }
        }
        
        return mostFrequentKey;
    }
}

// Usage in an interview problem:
// Problem: Find the most frequent element in an array
public int mostFrequent(int[] nums) {
    CountMap<Integer> counts = new CountMap<>();
    
    for (int num : nums) {
        counts.increment(num);
    }
    
    return counts.getMostFrequent();
}
```

### 📌 Example: SortedNodeMap for Graph Problems
Maintain sorted nodes for graph algorithms.

```java
class SortedNodeMap<T> extends TreeMap<Integer, List<T>> {
    // Add a node with its priority
    public void addNode(int priority, T node) {
        List<T> nodes = computeIfAbsent(priority, k -> new ArrayList<>());
        nodes.add(node);
    }
    
    // Extract the highest priority node
    public T extractHighestPriorityNode() {
        if (isEmpty()) {
            return null;
        }
        
        Map.Entry<Integer, List<T>> highestEntry = lastEntry();
        List<T> nodes = highestEntry.getValue();
        T node = nodes.remove(nodes.size() - 1);
        
        if (nodes.isEmpty()) {
            remove(highestEntry.getKey());
        }
        
        return node;
    }
    
    // Extract the lowest priority node
    public T extractLowestPriorityNode() {
        if (isEmpty()) {
            return null;
        }
        
        Map.Entry<Integer, List<T>> lowestEntry = firstEntry();
        List<T> nodes = lowestEntry.getValue();
        T node = nodes.remove(nodes.size() - 1);
        
        if (nodes.isEmpty()) {
            remove(lowestEntry.getKey());
        }
        
        return node;
    }
}

// Usage in an interview problem:
// Simple A* implementation for path finding
public List<Node> findPath(Node start, Node goal) {
    SortedNodeMap<Node> openSet = new SortedNodeMap<>();
    Set<Node> closedSet = new HashSet<>();
    Map<Node, Node> cameFrom = new HashMap<>();
    
    // Priority = -f_score (negative because TreeMap sorts ascending)
    openSet.addNode(-heuristic(start, goal), start);
    
    // Remaining A* algorithm...
    // (Omitted for brevity)
    
    return reconstructPath(cameFrom, goal);
}

private int heuristic(Node a, Node b) {
    // Calculate Manhattan distance or another heuristic
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
}
```

---

## 🏗️ Implementing Specialized Data Structures

Some interview questions directly ask you to implement a specific data structure from scratch.

### 📌 Example: LRU Cache Implementation
A common interview question requiring a custom data structure combining HashMap and doubly linked list.

```java
class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> cache;
    private Node head; // Most recently used
    private Node tail; // Least recently used
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Initialize dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        // Get node and move to front (most recently used)
        Node node = cache.get(key);
        moveToFront(node);
        
        return node.value;
    }
    
    public void put(int key, int value) {
        // Update existing key
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);
            return;
        }
        
        // Remove least recently used item if at capacity
        if (cache.size() == capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.key);
        }
        
        // Add new node
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addToFront(newNode);
    }
    
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }
}

// Usage in an interview:
// LRUCache cache = new LRUCache(2);
// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.put(4, 4);    // evicts key 1
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4
```

### 📌 Example: Min Stack Implementation
Another common interview question requesting a stack with O(1) minimum element access.

```java
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        
        // MinStack keeps track of the minimum at each level
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        int val = stack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

// Usage in an interview:
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();  // returns -3
// minStack.pop();
// minStack.top();     // returns 0
// minStack.getMin();  // returns -2
```

---

## 🔧 Custom Collections for Specialized Problems

Some interview questions require very specific functionality that can be achieved by combining multiple data structures.

### 📌 Example: Data Stream Median Finder
Finding the median from a data stream is a common interview problem requiring a specialized solution.

```java
class MedianFinder {
    // Max heap for the lower half of numbers
    private PriorityQueue<Integer> lowerHalf;
    
    // Min heap for the upper half of numbers
    private PriorityQueue<Integer> upperHalf;
    
    public MedianFinder() {
        // Max heap for lower half
        lowerHalf = new PriorityQueue<>((a, b) -> b - a);
        
        // Min heap for upper half
        upperHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Add to appropriate heap
        if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
            lowerHalf.offer(num);
        } else {
            upperHalf.offer(num);
        }
        
        // Balance heaps
        if (lowerHalf.size() > upperHalf.size() + 1) {
            upperHalf.offer(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    public double findMedian() {
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        } else {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }
}

// Usage in an interview:
// MedianFinder medianFinder = new MedianFinder();
// medianFinder.addNum(1);
// medianFinder.addNum(2);
// medianFinder.findMedian(); // returns 1.5
// medianFinder.addNum(3);
// medianFinder.findMedian(); // returns 2.0
```

### 📌 Example: Time-Based Key-Value Store
This problem combines TreeMap and HashMap for efficient timestamp-based lookups.

```java
class TimeMap {
    private Map<String, TreeMap<Integer, String>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> timeMap = map.computeIfAbsent(key, k -> new TreeMap<>());
        timeMap.put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> timeMap = map.get(key);
        Integer floorKey = timeMap.floorKey(timestamp);
        
        return (floorKey == null) ? "" : timeMap.get(floorKey);
    }
}

// Usage in an interview:
// TimeMap timeMap = new TimeMap();
// timeMap.set("foo", "bar", 1);
// timeMap.get("foo", 1);      // returns "bar"
// timeMap.get("foo", 3);      // returns "bar" (latest value)
// timeMap.set("foo", "bar2", 4);
// timeMap.get("foo", 4);      // returns "bar2"
// timeMap.get("foo", 5);      // returns "bar2" (latest value)
```

---

## 📚 Key Takeaways for Interviews

1. **Know when to extend** built-in structures vs. when to create from scratch
2. **Understand the tradeoffs** of different data structure combinations
3. **Be ready to implement** common custom structures like Trie, Union-Find, and LRU Cache
4. **Optimize for the primary operations** most relevant to the problem
5. **Use composition** to combine multiple data structures for complex functionality
6. **Track additional metadata** when extending built-in collections
7. **Practice implementing** classic data structures that aren't part of Java's standard library

---

With this comprehensive understanding of Java fundamentals, data structures, algorithms, and how to extend them, you're well-prepared for the data structure and algorithm questions you'll encounter in FAANG technical interviews!

