# Object-Oriented Programming for Technical Interviews

## 🎯 Introduction

Object-Oriented Programming (OOP) is fundamental for Java-based technical interviews at companies like FAANG. Understanding OOP principles helps you structure efficient solutions for complex problems and enables you to leverage Java's extensive libraries effectively.

---

## 🔍 Classes and Objects in Interview Context

### 📌 Class Structure for Technical Interviews
```java
// Basic class structure used in many interview solutions
public class TreeNode {
    // Properties - keep accessible for interview simplicity
    int val;
    TreeNode left;
    TreeNode right;
    
    // Constructor
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```

### 📌 Creating a Custom Data Structure (Common in Interviews)
```java
// Custom data structure for LRU Cache (frequent interview question)
class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> cache;
    private Node head, tail;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        remove(node);
        add(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        if (cache.size() == capacity) {
            remove(tail.prev);
        }
        add(new Node(key, value));
    }
    
    private void add(Node node) {
        cache.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    private void remove(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
```

---

## 🎯 Four Pillars of OOP in Interview Context

### 1️⃣ **Encapsulation for Clean Solutions**
Encapsulation helps create self-contained components that keep related data and operations together.

```java
class Solution {
    // Properties are encapsulated
    private int[] nums;
    private int target;
    
    // Public method providing controlled access
    public int[] twoSum(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        return findPair();
    }
    
    // Private helper method (encapsulated functionality)
    private int[] findPair() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
```

### 2️⃣ **Inheritance for Problem Solutions**
Inheritance allows you to extend functionality of existing classes, especially useful for graph and tree problems.

```java
// Base Node class
class Node {
    int val;
    Node next;
    
    Node(int val) {
        this.val = val;
    }
}

// Extended for interview-specific needs
class EnhancedNode extends Node {
    int count; // extra property for solving frequency problems
    
    EnhancedNode(int val) {
        super(val);
        this.count = 1;
    }
    
    void increment() {
        this.count++;
    }
}
```

### 3️⃣ **Polymorphism in DSA Solutions**
Polymorphism helps create flexible algorithms that can handle multiple data types.

```java
// Interface for graph traversal strategies
interface TraversalStrategy {
    List<Integer> traverse(int[][] graph, int start);
}

// Implementations using polymorphism
class BFSTraversal implements TraversalStrategy {
    @Override
    public List<Integer> traverse(int[][] graph, int start) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }
}

class DFSTraversal implements TraversalStrategy {
    @Override
    public List<Integer> traverse(int[][] graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        dfs(graph, start, visited, result);
        return result;
    }
    
    private void dfs(int[][] graph, int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, result);
            }
        }
    }
}
```

### 4️⃣ **Abstraction in Problem Solving**
Abstraction helps manage complexity by hiding implementation details.

```java
/**
 * Abstract class that defines the template method pattern for sorting algorithms.
 * This class provides a common structure for all sorting implementations 
 * while allowing subclasses to implement their specific sorting logic.
 */
abstract class SortingAlgorithm {
    /**
     * Template method that defines the algorithm structure.
     * This method is final to ensure the algorithm structure remains consistent.
     * 
     * @param array The array to be sorted
     * @throws NullPointerException if array is null
     */
    public final void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        
        // Sort implementation can vary by subclass
        performSort(array);
    }
    
    /**
     * Abstract method to be implemented by concrete sorting algorithms.
     * Each subclass must provide its specific sorting implementation.
     * 
     * @param array The array to be sorted
     */
    protected abstract void performSort(int[] array);
}

/**
 * Concrete implementation of the QuickSort algorithm.
 * QuickSort is a divide-and-conquer algorithm that works by
 * selecting a 'pivot' element and partitioning the array around it.
 */
class QuickSort extends SortingAlgorithm {
    /**
     * Implements the required sorting method using QuickSort algorithm.
     * 
     * @param array The array to be sorted
     */
    @Override
    protected void performSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    /**
     * Recursively sorts the array using the QuickSort algorithm.
     * 
     * @param array The array to be sorted
     * @param low   The starting index of the subarray
     * @param high  The ending index of the subarray
     */
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Find the partition index and sort subarrays recursively
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }
    
    /**
     * Partitions the array around a pivot element.
     * All elements less than the pivot are moved to its left,
     * and all elements greater than the pivot are moved to its right.
     * 
     * @param array The array to be partitioned
     * @param low   The starting index of the subarray to partition
     * @param high  The ending index of the subarray to partition
     * @return      The index of the pivot element after partitioning
     */
    private int partition(int[] array, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = array[high];
        // Index of smaller element
        int i = low - 1;
        
        // Compare each element with the pivot
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                // Increment index of smaller element
                i++;
                swap(array, i, j);
            }
        }
        
        // Place the pivot element at its correct position
        swap(array, i + 1, high);
        return i + 1;
    }
    
    /**
     * Swaps two elements in an array.
     * 
     * @param array The array containing elements to swap
     * @param i     The index of the first element
     * @param j     The index of the second element
     */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
```

---

## 🏷️ Interfaces and Abstract Classes for Interview Solutions

### 📌 Using Interfaces to Define Behavior
```java
/**
 * Interface defining the behavior of a cache data structure.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
interface Cache<K, V> {
    /**
     * Returns the value associated with the specified key in this cache,
     * or null if there is no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         null if this cache contains no mapping for the key
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in this cache.
     * If the cache previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Returns true if this cache contains a mapping for the specified key.
     *
     * @param key the key whose presence in this cache is to be tested
     * @return true if this cache contains a mapping for the specified key
     */
    boolean containsKey(K key);

    /**
     * Removes the mapping for a key from this cache if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    void remove(K key);
}

/**
 * Implementation of a Least Frequently Used (LFU) Cache.
 * This cache evicts the least frequently used item when capacity is reached.
 * If multiple items have the same frequency, it evicts the least recently used one among them.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
class LFUCache<K, V> implements Cache<K, V> {
    /**
     * Node class to store key-value pairs with frequency and access information.
     */
    private class Node {
        K key;
        V value;
        int frequency;
        Node prev, next;
        
        /**
         * Constructs a new node with the specified key and value.
         *
         * @param key   the key
         * @param value the value
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.frequency = 1; // New nodes start with frequency 1
        }
    }

    /**
     * FrequencyList is a doubly linked list containing nodes with the same frequency.
     */
    private class FrequencyList {
        int frequency;
        Node head, tail;
        
        /**
         * Constructs a new frequency list with the specified frequency.
         *
         * @param frequency the frequency value for this list
         */
        FrequencyList(int frequency) {
            this.frequency = frequency;
            head = new Node(null, null); // Dummy head
            tail = new Node(null, null); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }
        
        /**
         * Adds a node to this frequency list.
         *
         * @param node the node to add
         */
        void addNode(Node node) {
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
        }
        
        /**
         * Removes a node from this frequency list.
         *
         * @param node the node to remove
         */
        void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        
        /**
         * Checks if this frequency list is empty.
         *
         * @return true if this list contains no nodes, false otherwise
         */
        boolean isEmpty() {
            return head.next == tail;
        }
        
        /**
         * Returns the least recently used node in this frequency list.
         *
         * @return the least recently used node
         */
        Node getLRUNode() {
            if (isEmpty()) {
                return null;
            }
            return tail.prev;
        }
    }

    private final int capacity;
    private int minFrequency;
    private final Map<K, Node> keyNodeMap;
    private final Map<Integer, FrequencyList> frequencyListMap;
    
    /**
     * Constructs a new LFU Cache with the specified capacity.
     *
     * @param capacity the capacity of the cache
     * @throws IllegalArgumentException if capacity is negative
     */
    public LFUCache(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
        this.minFrequency = 0;
        this.keyNodeMap = new HashMap<>();
        this.frequencyListMap = new HashMap<>();
    }
    
    /**
     * Returns the value associated with the specified key in this cache,
     * or null if there is no mapping for the key.
     * This operation will increase the frequency of the accessed key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         null if this cache contains no mapping for the key
     */
    @Override
    public V get(K key) {
        if (!keyNodeMap.containsKey(key)) {
            return null;
        }
        
        Node node = keyNodeMap.get(key);
        updateNode(node);
        return node.value;
    }
    
    /**
     * Associates the specified value with the specified key in this cache.
     * If the cache previously contained a mapping for the key, the old
     * value is replaced. If adding a new key would cause the cache to exceed
     * its capacity, the least frequently used item is evicted.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }
        
        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            updateNode(node);
        } else {
            if (keyNodeMap.size() >= capacity) {
                // Remove the least frequently used item
                FrequencyList minFreqList = frequencyListMap.get(minFrequency);
                Node lruNode = minFreqList.getLRUNode();
                minFreqList.removeNode(lruNode);
                keyNodeMap.remove(lruNode.key);
                
                // Clean up empty frequency list
                if (minFreqList.isEmpty()) {
                    frequencyListMap.remove(minFrequency);
                }
            }
            
            // Add new node with frequency 1
            minFrequency = 1;
            Node newNode = new Node(key, value);
            keyNodeMap.put(key, newNode);
            
            FrequencyList freqList = frequencyListMap.computeIfAbsent(1, f -> new FrequencyList(f));
            freqList.addNode(newNode);
        }
    }
    
    /**
     * Returns true if this cache contains a mapping for the specified key.
     *
     * @param key the key whose presence in this cache is to be tested
     * @return true if this cache contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(K key) {
        return keyNodeMap.containsKey(key);
    }
    
    /**
     * Removes the mapping for a key from this cache if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    @Override
    public void remove(K key) {
        if (!keyNodeMap.containsKey(key)) {
            return;
        }
        
        Node node = keyNodeMap.get(key);
        FrequencyList freqList = frequencyListMap.get(node.frequency);
        freqList.removeNode(node);
        keyNodeMap.remove(key);
        
        // Clean up empty frequency list
        if (freqList.isEmpty() && minFrequency == node.frequency) {
            frequencyListMap.remove(node.frequency);
            updateMinFrequency();
        }
    }
    
    /**
     * Updates a node's frequency after it has been accessed.
     *
     * @param node the node to update
     */
    private void updateNode(Node node) {
        FrequencyList oldFreqList = frequencyListMap.get(node.frequency);
        oldFreqList.removeNode(node);
        
        // Update minFrequency if needed
        if (node.frequency == minFrequency && oldFreqList.isEmpty()) {
            minFrequency++;
        }
        
        // Increase node's frequency
        node.frequency++;
        
        // Add node to the new frequency list
        FrequencyList newFreqList = frequencyListMap.computeIfAbsent(
            node.frequency, f -> new FrequencyList(f)
        );
        newFreqList.addNode(node);
    }
    
    /**
     * Updates the minimum frequency after a node removal.
     */
    private void updateMinFrequency() {
        if (keyNodeMap.isEmpty()) {
            minFrequency = 0;
            return;
        }
        
        // Find the new minimum frequency
        minFrequency = Integer.MAX_VALUE;
        for (int frequency : frequencyListMap.keySet()) {
            minFrequency = Math.min(minFrequency, frequency);
        }
    }
}

/**
 * Example usage of LFUCache
 */
public class LFUCacheExample {
    public static void main(String[] args) {
        LFUCache<Integer, String> cache = new LFUCache<>(2);
        
        // Add items to cache
        cache.put(1, "One");
        cache.put(2, "Two");
        
        // Access key 1 to increase its frequency
        System.out.println(cache.get(1)); // Output: One
        
        // Add key 3, which will evict key 2 (least frequently used)
        cache.put(3, "Three");
        
        // Key 2 should be evicted
        System.out.println(cache.get(2)); // Output: null
        
        // Keys 1 and 3 should still be in cache
        System.out.println(cache.get(1)); // Output: One
        System.out.println(cache.get(3)); // Output: Three
    }
}
```

---

## 📚 Key OOP Patterns for Interviews

1. **Singleton Pattern** - Useful for managing global state

### Singleton Pattern - When and How to Use

The Singleton Pattern ensures that a class has only one instance and provides a global point of access to it. This is particularly useful in scenarios where a single instance is required to coordinate actions across the system.

#### When to Use:
1. **Global State Management**: Use the Singleton pattern to manage shared resources like configuration settings, logging, or thread pools.
2. **Database Connections**: Ensure only one connection pool instance exists to manage database connections efficiently.
3. **Caching**: Maintain a single cache instance to store frequently accessed data.
4. **Multithreaded Environments**: Use it to avoid creating multiple instances of a resource-intensive object.

#### How to Use:
The following example demonstrates a thread-safe Singleton implementation:

```java
class Singleton {
    // Volatile ensures visibility of changes to variables across threads
    private static volatile Singleton instance;

    // Private constructor prevents instantiation from other classes
    private Singleton() {}

    // Double-checked locking for thread safety and performance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

#### Key Points:
- **Thread Safety**: Use `synchronized` or other thread-safe mechanisms to ensure only one instance is created in multithreaded environments.
- **Lazy Initialization**: The instance is created only when it is needed, saving resources.
- **Global Access**: The `getInstance` method provides a single point of access to the instance.

#### Example Usage:
```java
public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("Singleton instance: " + singleton);
    }
}
```
This ensures that the same instance of `Singleton` is used throughout the application, making it ideal for managing shared resources or global states.

2. **Factory Pattern** - Create objects without specifying exact class
    ```java
    // Factory Pattern Example: Search Algorithm Selector
    interface SearchAlgorithm {
        int search(int[] array, int target);
    }

    class LinearSearch implements SearchAlgorithm {
        @Override
        public int search(int[] array, int target) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == target) return i;
            }
            return -1;
        }
    }

    class BinarySearch implements SearchAlgorithm {
        @Override
        public int search(int[] array, int target) {
            int left = 0, right = array.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (array[mid] == target) return mid;
                if (array[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }
    }

    class SearchFactory {
        // Factory method to return appropriate search algorithm
        public static SearchAlgorithm getSearchAlgorithm(boolean isSorted) {
            return isSorted ? new BinarySearch() : new LinearSearch();
        }
    }

    // Usage Example
    public class Main {
        public static void main(String[] args) {
            int[] array = {1, 3, 5, 7, 9};
            int target = 5;

            // Get appropriate search algorithm based on array sorting
            SearchAlgorithm searchAlgorithm = SearchFactory.getSearchAlgorithm(true);
            int result = searchAlgorithm.search(array, target);

            System.out.println("Target found at index: " + result);
        }
    }
    ```

---

## 📚 OOP Interview Tips

1. **Keep classes focused** - Each class should have a single responsibility
2. **Use interfaces** for defining behaviors that multiple classes can implement
3. **Choose composition over inheritance** when designing solutions
4. **Apply encapsulation** to hide complex implementation details
5. **Understand when to use abstract classes vs interfaces**

---

Up next: Explore **Advanced Java Concepts** for technical interviews, including collections, generics, and functional programming.

