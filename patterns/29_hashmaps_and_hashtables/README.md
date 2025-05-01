# HashMap and HashTable Pattern

## 🎯 Introduction

Imagine you have a magical dictionary where you can instantly look up any word and find its meaning without flipping through pages. **HashMaps and HashTables** provide this exact functionality in programming - they offer near-constant time access to values based on their associated keys.

The HashMap/HashTable Pattern is particularly useful for:
- Fast data retrieval based on keys
- Counting frequency of elements
- Finding duplicate or missing elements
- Caching values to avoid repetitive computations
- Implementing complex data structures like LRU cache
- Grouping or mapping related data

This pattern works best when you need to quickly access, insert, or check for the existence of data based on a key.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - Do you need to count occurrences of elements?
   - Is there a need to look up values based on a specific key?
   - Are you trying to detect duplicates or find specific elements?
   - Do you need to group related elements together?

2. **Ask Clarifying Questions:**
   - What types of keys and values are involved?
   - Should the solution handle collisions (when two keys hash to the same location)?
   - Are there any constraints on time or space complexity?
   - Is the ordering of elements important? (HashMaps don't guarantee order)

3. **Identify Clues for Using the HashMap/HashTable Pattern:**
   - Keywords like "frequency," "count," "group," "find duplicates," "pairs"
   - Problems requiring O(1) lookup time
   - Questions involving matching or mapping elements
   - Need to transform data without losing the relationship between elements

4. **Predicting if HashMap/HashTable Is Applicable:**
   - If the problem requires quick lookups or element counting
   - If you need to find relationships between elements in an array or string
   - If the solution benefits from key-value associations

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What keys and values will be stored in the HashMap?
- What operations need to be performed (lookup, insertion, deletion)?
- Is there a specific goal to achieve using the HashMap?

### ✅ **2. Ask Questions Before Defining Base Cases**
- How should edge cases like non-existent keys be handled?
- Are there restrictions on the size of the input data?
- What is the expected output format?

### ✅ **3. Identify Base Cases**
- Empty input: Initialize an empty HashMap
- Single element: Process and store as needed

### ✅ **4. Write Pseudo-Code for Base Cases**

```
function solveWithHashMap(input):
    initialize hash map
    
    for each element in input:
        process element and update hash map
        (e.g., count frequency, store element with key, etc.)
    
    use hash map to derive the solution
    (e.g., find elements meeting certain criteria)
    
    return result
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.HashMap;
import java.util.Map;

public class HashMapSolution {
    public static Result solveWithHashMap(int[] nums) {
        // Initialize HashMap
        Map<Key, Value> map = new HashMap<>();
        
        // Process input and populate the map
        for (int num : nums) {
            // Update map based on problem requirements
            // Example: Count frequency
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Use the map to derive the solution
        Result result = processMap(map);
        
        return result;
    }
    
    private static Result processMap(Map<Key, Value> map) {
        // Process the map to find the solution
        // This depends on the specific problem
        return new Result();
    }
    
    // Define Result class based on problem requirements
    static class Result {
        // Result fields and methods
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty input arrays or strings
- Duplicate keys or values
- Null keys or values (if allowed by the HashMap implementation)
- Very large inputs that might cause hash collisions
- Non-existent keys during lookups
- Case sensitivity with string keys
- Hash function limitations

### ✅ **7. How to Predict Time and Space Complexity**

| Operation               | Time Complexity | Space Complexity |
|-------------------------|-----------------|------------------|
| Insertion (average)     | O(1)            | O(1)             |
| Lookup (average)        | O(1)            | O(1)             |
| Deletion (average)      | O(1)            | O(1)             |
| Worst case for all ops  | O(n)            | O(1)             |
| Overall for n elements  | O(n)            | O(n)             |

**How to derive these complexities:**
- **Time Complexity:** O(n) for processing all elements, but individual operations are typically O(1) in the average case.
- **Space Complexity:** O(n) in the worst case when all elements need to be stored in the HashMap.

---

## 📚 Example 1: Easy Problem - Two Sum

**Problem:**
Given an array of integers and a target sum, return the indices of two numbers such that they add up to the target.

**Input:**
```
nums = [2, 7, 11, 15], target = 9
```

**Expected Output:**
```
[0, 1] (because nums[0] + nums[1] = 2 + 7 = 9)
```

### 🔑 **Solution Steps**
1. Initialize an empty HashMap to store elements and their indices
2. Iterate through the array
3. For each element, calculate its complement (target - current element)
4. If the complement exists in the HashMap, return the current index and the complement's index
5. Otherwise, add the current element and its index to the HashMap

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
    
    return new int[] {}; // No solution found
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) - We traverse the array once
- **Space:** O(n) - In the worst case, we store all elements in the HashMap

---

## 📚 Example 2: Medium Problem - Group Anagrams

**Problem:**
Given an array of strings, group anagrams together. An anagram is a word formed by rearranging the letters of another word.

**Input:**
```
["eat", "tea", "tan", "ate", "nat", "bat"]
```

**Expected Output:**
```
[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
```

### 🔑 **Solution Steps**
1. Initialize a HashMap where keys are sorted strings and values are lists of original strings
2. Iterate through the input strings
3. For each string, sort its characters to create a key
4. Add the original string to the list associated with that key
5. Return all the lists as the result

### ✅ **Code:**
```java
public static List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    
    for (String str : strs) {
        // Create a sorted key to identify anagrams
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        String key = new String(charArray);
        
        // Add the string to its anagram group
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(str);
    }
    
    // Convert map values to the result list
    return new ArrayList<>(map.values());
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(n * k log k) where n is the number of strings and k is the maximum string length (sorting each string)
- **Space:** O(n * k) for storing all strings in the HashMap

---

## 📚 Example 3: Hard Problem - LRU Cache

**Problem:**
Design and implement a data structure for a Least Recently Used (LRU) cache. It should support get and put operations with O(1) time complexity.

- get(key): Get the value of the key if it exists in the cache, otherwise return -1
- put(key, value): Update or insert the value if the key exists. When the cache reaches its capacity, invalidate the least recently used item before inserting a new item.

**Expected Operations:**
```
LRUCache cache = new LRUCache(2); // capacity = 2
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

### 🔑 **Solution Steps**
1. Use a combination of HashMap and Doubly Linked List
2. The HashMap maps keys to nodes in the linked list for O(1) access
3. The doubly linked list maintains the order of usage (most recent at head, least recent at tail)
4. For get operations, move the accessed node to the head of the list
5. For put operations, add new nodes at the head and remove from the tail when capacity is exceeded

### ✅ **Code:**
```java
class LRUCache {
    private Map<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0); // Dummy head
        tail = new Node(0, 0); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // Move to head (mark as most recently used)
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update existing key
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            // Add new key
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            
            // Check capacity
            if (cache.size() > capacity) {
                // Remove least recently used (from tail)
                Node lru = tail.prev;
                removeNode(lru);
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
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
```

### ⏱️ **Time and Space Complexity:**
- **Time:** O(1) for both get and put operations
- **Space:** O(capacity) for storing up to 'capacity' key-value pairs

---

## 📚 Key Takeaways

1. HashMaps provide **near-constant time** access for insertions, lookups, and deletions.
2. They are excellent for **frequency counting**, **finding duplicates**, and **grouping related elements**.
3. Use HashMaps when you need to **establish relationships** between data elements or need quick lookups.
4. Be mindful of the **hash function quality** - poor hash functions can lead to collisions and degraded performance.
5. HashMaps can be **combined with other data structures** (like linked lists, trees) to create more complex and efficient solutions.
6. Remember that **standard HashMaps don't guarantee order** - use LinkedHashMap if order is important.
7. For **thread safety** requirements, consider using ConcurrentHashMap or Hashtable in Java.

---

Next, lets dive deep into **Sort and Search Algorithms**.