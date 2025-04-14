# Java Collections Framework for FAANG Interviews

## 🎯 Introduction

Java's Collections Framework provides powerful data structures that are essential for solving algorithm problems in FAANG interviews. This guide covers the most frequently used collection classes, their operations, and time complexities - knowledge that is directly applicable to technical interviews.

---

## 🔍 Overview of Java Collections for Interviews

The Java Collections Framework consists of interfaces and their implementations. Understanding which implementation to use for a given problem is critical for interview success.

### 📌 Collection Hierarchy for Interview Reference
```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector (rarely used in interviews)
├── Set (Interface)
│   ├── HashSet
│   ├── LinkedHashSet
│   └── TreeSet
└── Queue (Interface)
    ├── LinkedList
    ├── PriorityQueue
    └── ArrayDeque
```

```
Map (Interface)
├── HashMap
├── LinkedHashMap
├── TreeMap
└── Hashtable (rarely used in interviews)
```

### 📌 When to Use Which Collection in Interviews
| Data Structure | Use When You Need | Avoid When | Common Interview Questions |
|----------------|-------------------|------------|----------------------------|
| ArrayList | Fast access by index, dynamic size | Frequent insertions/deletions in middle | Two pointers, sliding window |
| LinkedList | Frequent insertions/deletions | Random access by index | Queue implementations, LRU cache |
| HashSet | Fast lookups, no duplicates | Ordered elements | Two Sum, Contains Duplicate |
| TreeSet | Ordered elements, range queries | Fast insertion priority | Closest Points, Range Search |
| HashMap | Key-value mapping, fast lookups | Ordered keys | Frequency Counter, LRU Cache |
| TreeMap | Ordered keys, ceiling/floor operations | Fast insertion priority | Range Problems, Interval Problems |
| PriorityQueue | Finding min/max repeatedly | Random access | K Largest Elements, Merge K Sorted Lists |

---

## 📋 List Interface for Interviews

Lists maintain elements in insertion order and allow duplicates. They are fundamental for many interview questions.

### ✅ ArrayList vs LinkedList for Interviews
| Operation | ArrayList | LinkedList | Interview Insight |
|-----------|-----------|------------|-------------------|
| get(i) | O(1) | O(n) | Use ArrayList for frequent random access |
| add(E) at end | O(1) amortized | O(1) | Both good for adding at end |
| add(i, E) | O(n) | O(n) | LinkedList faster if i is near start/end |
| remove(i) | O(n) | O(n) | LinkedList faster if i is near start/end |
| contains(E) | O(n) | O(n) | Both require linear search |
| Memory | Less overhead | More overhead (pointers) | ArrayList more memory efficient |

### 📌 ArrayList - Most Common for Interviews
```java
// ArrayList initialization
List<Integer> list1 = new ArrayList<>();
List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
List<Integer> list3 = new ArrayList<>(10); // Initial capacity

// Common operations in interviews
list1.add(42);                // Add element
list1.add(0, 99);             // Insert at position
list1.get(0);                 // O(1) access
list1.set(0, 100);            // Update element
list1.remove(0);              // Remove by index
list1.remove(Integer.valueOf(42)); // Remove by value
list1.size();                 // Get size
list1.isEmpty();              // Check if empty
list1.contains(100);          // Check existence
list1.indexOf(100);           // Find first occurrence

// Sorting a list (common in interviews)
Collections.sort(list1);      // Natural ordering
Collections.sort(list1, Collections.reverseOrder()); // Custom order
list1.sort((a, b) -> b - a);  // Lambda comparator for reverse order

// Converting between array and list (frequent in interviews)
Integer[] array = list1.toArray(new Integer[0]);
List<Integer> newList = Arrays.asList(array); // Fixed-size view
List<Integer> modifiableList = new ArrayList<>(Arrays.asList(array));
```

### 📌 Interview Application: Two Sum Problem
```java
// Using ArrayList to solve Two Sum problem
public int[] twoSum(int[] nums, int target) {
    List<Integer> numsList = new ArrayList<>();
    for (int num : nums) {
        numsList.add(num);
    }
    
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        int complementIdx = numsList.indexOf(complement);
        if (complementIdx != -1 && complementIdx != i) {
            return new int[]{i, complementIdx};
        }
    }
    return new int[]{};
}

// More efficient solution using HashMap
public int[] twoSumOptimized(int[] nums, int target) {
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

---

## 📌 Set Interface for Interviews

Sets are essential for solving problems that require uniqueness or fast lookups.

### ✅ Set Implementations Comparison
| Set Type | Implementation | Order | Performance | Use Case |
|----------|---------------|-------|-------------|----------|
| HashSet | Hash table | Unordered | O(1) operations | Fast lookups |
| LinkedHashSet | Hash table + Linked list | Insertion order | O(1) operations | Ordered iteration with fast lookups |
| TreeSet | Red-black tree | Natural order | O(log n) operations | Sorted elements, range queries |

### 📌 HashSet - Most Common in Interviews
```java
// HashSet initialization
Set<String> set1 = new HashSet<>();
Set<String> set2 = new HashSet<>(Arrays.asList("a", "b", "c"));

// Common operations in interviews
set1.add("apple");           // Add element
set1.remove("apple");        // Remove element
boolean contains = set1.contains("banana"); // Check existence
int size = set1.size();      // Get size
boolean isEmpty = set1.isEmpty(); // Check if empty
set1.clear();                // Remove all elements

// Iteration (when order doesn't matter)
for (String item : set1) {
    System.out.println(item);
}
```

### 📌 TreeSet - For Ordered Sets in Interviews
```java
// TreeSet initialization
Set<Integer> treeSet = new TreeSet<>();
treeSet.addAll(Arrays.asList(5, 3, 1, 4, 2));

// Special operations useful in interviews
TreeSet<Integer> ts = (TreeSet<Integer>) treeSet;
Integer first = ts.first();           // Smallest element
Integer last = ts.last();             // Largest element
Integer ceiling = ts.ceiling(3);      // Smallest element ≥ 3
Integer floor = ts.floor(3);          // Largest element ≤ 3
Integer higher = ts.higher(3);        // Smallest element > 3
Integer lower = ts.lower(3);          // Largest element < 3
```

### 📌 Interview Application: Contains Duplicate
```java
// Problem: Determine if an array contains duplicates
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (seen.contains(num)) {
            return true;
        }
        seen.add(num);
    }
    return false;
}

// Alternative one-liner solution
public boolean containsDuplicateOneLiner(int[] nums) {
    return nums.length > new HashSet<>(Arrays.stream(nums).boxed().collect(Collectors.toList())).size();
}
```

---

## 🗺️ Map Interface for Interviews

Maps are one of the most important data structures for interview questions, especially for optimizing brute force solutions.

### ✅ Map Implementations Comparison
| Map Type | Implementation | Order | Performance | Use Case |
|----------|---------------|-------|-------------|----------|
| HashMap | Hash table | Unordered | O(1) operations | Most common, fast lookups |
| LinkedHashMap | Hash table + Linked list | Insertion order | O(1) operations | LRU cache, ordered operations |
| TreeMap | Red-black tree | Key natural order | O(log n) operations | Range queries, ceiling/floor |

### 📌 HashMap - A FAANG Interview Favorite
```java
// HashMap initialization
Map<String, Integer> map1 = new HashMap<>();
Map<String, Integer> map2 = new HashMap<>(Map.of("a", 1, "b", 2, "c", 3));

// Common operations in interviews
map1.put("apple", 5);            // Add or update entry
int value = map1.get("apple");   // Get value (returns null if key not found)
value = map1.getOrDefault("banana", 0); // Get with default value
boolean containsKey = map1.containsKey("apple"); // Check key
boolean containsValue = map1.containsValue(5); // Check value
map1.remove("apple");            // Remove entry
int size = map1.size();          // Get size
boolean isEmpty = map1.isEmpty(); // Check if empty

// Iterating through a map (3 common ways in interviews)
// 1. Iterate through entries
for (Map.Entry<String, Integer> entry : map1.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// 2. Iterate through keys
for (String key : map1.keySet()) {
    System.out.println(key + ": " + map1.get(key));
}

// 3. Iterate through values
for (Integer val : map1.values()) {
    System.out.println(val);
}
```

### 📌 TreeMap - For Ordered Maps in Interviews
```java
// TreeMap initialization
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(3, "three");
treeMap.put(1, "one");
treeMap.put(2, "two");

// Special operations useful in interviews
Map.Entry<Integer, String> firstEntry = treeMap.firstEntry();
Map.Entry<Integer, String> lastEntry = treeMap.lastEntry();
Map.Entry<Integer, String> ceilingEntry = treeMap.ceilingEntry(2); // Entry with smallest key ≥ 2
Map.Entry<Integer, String> floorEntry = treeMap.floorEntry(2); // Entry with largest key ≤ 2
Map.Entry<Integer, String> higherEntry = treeMap.higherEntry(2); // Entry with smallest key > 2
Map.Entry<Integer, String> lowerEntry = treeMap.lowerEntry(2); // Entry with largest key < 2
```

### 📌 LinkedHashMap - For LRU Cache in Interviews
```java
// LRU Cache implementation (very common interview question)
class LRUCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // Third parameter 'true' for access-order (LRU behavior)
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }
}
```

### 📌 Interview Application: Word Frequency Counter
```java
// Problem: Find the frequency of each word in a text
public Map<String, Integer> wordFrequency(String text) {
    Map<String, Integer> freqMap = new HashMap<>();
    String[] words = text.toLowerCase().split("\\W+");
    
    for (String word : words) {
        if (word.isEmpty()) continue;
        freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
    }
    
    return freqMap;
}

// Extension: Find the k most frequent words
public List<String> topKFrequent(String text, int k) {
    Map<String, Integer> freqMap = wordFrequency(text);
    
    // Create a priority queue that sorts words by their frequency
    PriorityQueue<String> pq = new PriorityQueue<>(
        (w1, w2) -> freqMap.get(w2) - freqMap.get(w1)
    );
    
    pq.addAll(freqMap.keySet());
    
    List<String> result = new ArrayList<>();
    for (int i = 0; i < k && !pq.isEmpty(); i++) {
        result.add(pq.poll());
    }
    
    return result;
}
```

---

## ⏳ Queue Interface for Interviews

Queues are essential for BFS algorithms, producer-consumer patterns, and processing elements in order.

### ✅ Queue Implementations Comparison
| Queue Type | Implementation | Features | Use Case |
|------------|---------------|----------|----------|
| LinkedList | Doubly linked list | Standard FIFO queue | BFS, multi-producer/consumer |
| ArrayDeque | Resizable array | Double-ended queue | More efficient than LinkedList |
| PriorityQueue | Binary heap | Priority-based order | Dijkstra's, K-th largest element |

### 📌 Queue and Deque Operations
```java
// Queue initialization
Queue<String> queue = new LinkedList<>();
Deque<String> deque = new ArrayDeque<>();

// Standard queue operations
queue.add("first");         // Add to end (throws exception if full)
queue.offer("second");      // Add to end (returns false if full)
String head = queue.peek(); // View head (without removing)
String removed = queue.poll(); // Remove and return head
boolean isEmpty = queue.isEmpty(); // Check if empty
int size = queue.size();    // Get size

// Deque operations (double-ended queue)
deque.addFirst("first");    // Add to front
deque.addLast("last");      // Add to end
String first = deque.peekFirst(); // View front
String last = deque.peekLast();   // View end
first = deque.pollFirst();  // Remove from front
last = deque.pollLast();    // Remove from end
```

### 📌 PriorityQueue for Interview Problems
```java
// Min heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.add(5);
minHeap.add(2);
minHeap.add(8);
// Elements come out in order: 2, 5, 8

// Max heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
maxHeap.add(5);
maxHeap.add(2);
maxHeap.add(8);
// Elements come out in order: 8, 5, 2

// Priority queue with custom objects
PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> t1.priority - t2.priority);

class Task {
    String name;
    int priority;
    
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
```

### 📌 Interview Application: Binary Tree Level Order Traversal
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        result.add(currentLevel);
    }
    
    return result;
}
```

### 📌 Interview Application: K Largest Elements
```java
public List<Integer> findKLargest(int[] nums, int k) {
    // Using min heap of size k
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    // Add first k elements
    for (int i = 0; i < k; i++) {
        minHeap.add(nums[i]);
    }
    
    // For remaining elements, if larger than heap top, replace top
    for (int i = k; i < nums.length; i++) {
        if (nums[i] > minHeap.peek()) {
            minHeap.poll(); // Remove smallest
            minHeap.add(nums[i]); // Add new element
        }
    }
    
    // Convert heap to list
    List<Integer> result = new ArrayList<>(minHeap);
    return result;
}
```

---

## 🔄 Collections Utility Methods for Interviews

The `Collections` class provides various utility methods that are useful in interviews.

### 📌 Frequently Used Methods
```java
// List Operations
List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7));
Collections.sort(list);                    // Sort in natural order
Collections.reverse(list);                 // Reverse the list
Collections.shuffle(list);                 // Randomize order
Collections.swap(list, 0, 1);              // Swap elements
int frequency = Collections.frequency(list, 1); // Count occurrences
int min = Collections.min(list);           // Find minimum
int max = Collections.max(list);           // Find maximum
Collections.fill(list, 0);                 // Replace all elements

// Specialized Collections
List<String> syncList = Collections.synchronizedList(new ArrayList<>()); // Thread-safe list
Set<String> singleton = Collections.singleton("one");  // Immutable single-element set
List<String> emptyList = Collections.emptyList();      // Immutable empty list
```

### 📌 Binary Search in Sorted Collections
```java
List<Integer> sortedList = Arrays.asList(1, 2, 4, 7, 9, 11);
int index = Collections.binarySearch(sortedList, 7); // Returns 3
int notFoundIndex = Collections.binarySearch(sortedList, 8); // Returns -5 (insertion point)

// Custom comparator for binary search
Comparator<Integer> reverseOrder = Collections.reverseOrder();
Collections.sort(sortedList, reverseOrder);  // Sort in reverse
int revIndex = Collections.binarySearch(sortedList, 7, reverseOrder); // Need to use same comparator
```

---

## 📚 Key Takeaways for Interviews

1. **Choose the right collection** based on the operations you need to perform
2. **Use HashMap for lookups** - critical for optimizing brute-force solutions
3. **Use PriorityQueue for k-th element problems** - heap operations are often elegant solutions
4. **Use TreeMap/TreeSet for range queries** - more efficient than sorting in some cases
5. **Know the time complexities** of common operations for every collection
6. **Be familiar with utility methods** that can simplify your code during interviews
7. **Use LinkedHashMap for LRU caches** - a common interview question

---

Up next: Learn about **Extending Data Structures** in Java to create custom implementations for specialized interview problems.

