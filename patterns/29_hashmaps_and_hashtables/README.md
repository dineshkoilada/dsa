# HashMap and HashTable Pattern 🎯

## 📌 Introduction: The Power of Instant Lookup

Imagine you have a magical dictionary where you can instantly look up any word and find its meaning without flipping through pages. **HashMaps and HashTables** provide this exact functionality in programming—they offer near-constant time access to values based on their associated keys.

### 🎬 Real-World Analogies:

1. **Magical Dictionary** 📖
   ```
   Word: "apple" → Instantly get its meaning.
   Word: "banana" → Instantly get its meaning.
   ```
2. **Student ID Lookup** 🪪
   ```
   ID: 12345 → Student: Alice
   ID: 67890 → Student: Bob
   ```
3. **Phone Contacts** 📱
   ```
   Name: "Mom" → Number: 555-1234
   Name: "Friend" → Number: 555-5678
   ```

The HashMap/HashTable pattern is your secret weapon when you need:
- 📍 Fast data retrieval based on keys
- 🔄 Counting frequency of elements
- 🧩 Finding duplicates or missing elements
- 🚦 Caching values to avoid repetitive computations
- 🗂️ Grouping or mapping related data

### 🎯 Visual Example:
Counting word frequency:
```
Input: ["apple", "banana", "apple", "orange", "banana", "apple"]
HashMap: {"apple": 3, "banana": 2, "orange": 1}
```

---

## 🧠 How to Recognize a HashMap/HashTable Problem

### 🔍 Key Pattern Recognition Signals:

1. **The "Key-Value Lookup" Clue** 📑
   - "Find/count/group elements by key"
   - "Check if an element exists quickly"

2. **The "Frequency/Counting" Hint** 🔢
   - "Count occurrences"
   - "Find duplicates or missing elements"

3. **The "Mapping/Grouping" Signal** 🗂️
   - "Group anagrams"
   - "Map related data together"

### 🤔 Essential Questions to Ask:

1. **Input Questions:**
   ```
   What are the keys and values?
   Are there duplicate keys or values?
   What should happen if a key is missing?
   ```
2. **Content Questions:**
   ```
   Is order important? (HashMaps are unordered)
   Are there constraints on time/space?
   ```
3. **Edge Case Questions:**
   ```
   What if the input is empty?
   What if there are hash collisions?
   ```

### 🎨 Visual Problem-Solving Framework:

```
Step 1: Initialize an empty HashMap
{}

Step 2: Process each element
{"apple": 1}, {"apple": 1, "banana": 1}, ...

Step 3: Update or retrieve values as needed
{"apple": 2, "banana": 1}, ...

Step 4: Use the map to answer the problem
{"apple": 3, "banana": 2, "orange": 1}
```

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What are the keys and values?
- What operations are needed (lookup, insert, delete)?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How to handle missing keys?
- Are there restrictions on input size?

### ✅ **3. Identify Base Cases**
- Empty input: return empty map or default value
- Single element: process and store as needed

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveWithHashMap(input):
    map = {}
    for element in input:
        update map based on problem
    return result derived from map
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class HashMapPattern {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty input
- Duplicate keys or values
- Null keys/values (if allowed)
- Large input causing hash collisions
- Non-existent keys during lookup

### ✅ **7. How to Predict Time and Space Complexity**

| Operation         | Time Complexity | Space Complexity |
|-------------------|-----------------|------------------|
| Insert/Lookup     | O(1) (avg)      | O(1) (avg)       |
| Process input     | O(n)            | O(n)             |
| Overall           | O(n)            | O(n)             |

**How to derive these complexities:**
- **Time:** Each element is processed once, and each operation is O(1) on average
- **Space:** Map can grow to size n in worst case

---

## 📚 Example 1: Easy Problem - Two Sum

**Problem:**
Given an array of integers and a target sum, return the indices of two numbers such that they add up to the target.

**Input:** nums = [2, 7, 11, 15], target = 9

**Expected Output:** [0, 1]

### 🔑 **Solution Steps**
1. Initialize an empty HashMap
2. For each number, check if complement exists
3. If found, return indices; else, add to map

### ✅ **Code:**
```java
public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
    }
    return new int[] {};
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n)
- **Space:** O(n)

---

## 📚 Example 2: Medium Problem - Group Anagrams

**Problem:**
Given an array of strings, group anagrams together.

**Input:** ["eat", "tea", "tan", "ate", "nat", "bat"]

**Expected Output:** [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]

### 🔑 **Solution Steps**
1. Use sorted string as key in HashMap
2. Group words by their sorted key
3. Return all groups as result

### ✅ **Code:**
```java
public static List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        String key = new String(arr);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }
    return new ArrayList<>(map.values());
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n * k log k)
- **Space:** O(n * k)

---

## 📚 Example 3: Hard Problem - LRU Cache

**Problem:**
Design a Least Recently Used (LRU) cache with O(1) get and put operations.

**Expected Operations:**
```
LRUCache cache = new LRUCache(2);
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);    // returns 1
cache.put(3, 3); // evicts key 2
cache.get(2);    // returns -1
cache.put(4, 4); // evicts key 1
cache.get(1);    // returns -1
cache.get(3);    // returns 3
cache.get(4);    // returns 4
```

### 🔑 **Solution Steps**
1. Use HashMap for O(1) access
2. Use Doubly Linked List to track usage order
3. Move accessed/updated node to head
4. Remove least recently used node when capacity exceeded

### ✅ **Code:**
```java
class LRUCache {
    private Map<Integer, Node> cache;
    private int capacity;
    private Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
            if (cache.size() > capacity) {
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);
            }
        }
    }
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(1) for get and put
- **Space:** O(capacity)

---

## 📚 Key Takeaways

1. HashMaps/HashTables are ideal for problems involving **fast key-value lookups**, **frequency counting**, and **grouping**.
2. They enable O(1) average time for insert, lookup, and delete.
3. Use them for **duplicates, missing elements, grouping, and caching**.
4. Combine with other data structures (like linked lists) for advanced use cases (e.g., LRU cache).
5. Remember: standard HashMaps are unordered—use LinkedHashMap if order matters.
6. For thread safety, use ConcurrentHashMap or Hashtable.

---

Next, let’s explore the **Sort and Search Pattern** for efficient searching and ordering of data!