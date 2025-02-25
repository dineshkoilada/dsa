# Inbuilt Data Structures in Java

## 🎯 Introduction

Java provides a rich set of inbuilt data structures through the `java.util` package. These structures help manage and organize data efficiently, providing built-in functionalities for common operations like insertion, deletion, searching, and sorting.

This guide focuses on the **20% of inbuilt data structures** that cover **80% of real-world usage** in Java applications, with a comprehensive focus on their operations.

---

## 🔍 Collections Framework Overview

Java's **Collections Framework** provides a standard architecture for handling groups of objects. The core interfaces include:

- **List**: Ordered collection (e.g., ArrayList, LinkedList).
- **Set**: Unordered collection of unique elements (e.g., HashSet, TreeSet).
- **Map**: Key-value pairs (e.g., HashMap, TreeMap).
- **Queue**: Elements in FIFO order (e.g., LinkedList, PriorityQueue).

Each data structure offers different performance characteristics, making them suitable for specific scenarios.

---

## 📋 List Interface

A **List** is an ordered collection that allows duplicate elements. Elements can be accessed by their index.

### ✅ Common Implementations:
- **ArrayList**: Resizable array, fast random access.
- **LinkedList**: Doubly-linked list, faster insertions/deletions.

### 📌 Common Operations:
- Add elements (`add()`, `add(index, element)`)
- Remove elements (`remove(index)`, `remove(Object)`)
- Update elements (`set(index, element)`)
- Search elements (`contains()`, `indexOf()`)
- Iterate over elements (using loops, iterator, forEach)

### 📌 Example:
```java
import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Removing elements
        list.remove("Banana");
        list.remove(0);

        // Updating elements
        list.set(0, "Date");

        // Searching for elements
        System.out.println("Contains Cherry? " + list.contains("Cherry"));

        // Iterating through elements
        for (String fruit : list) {
            System.out.println(fruit);
        }
    }
}
```

### 📊 Time and Space Complexities:
| Operation | ArrayList | LinkedList |
|-----------|-----------|-----------|
| Access    | O(1)      | O(n)      |
| Insertion | O(n)      | O(1)      |
| Deletion  | O(n)      | O(1)      |

---

## 📌 Set Interface

A **Set** is a collection that contains no duplicate elements.

### ✅ Common Implementations:
- **HashSet**: Unordered set with O(1) time complexity for add, remove, and contains.
- **TreeSet**: Maintains a sorted order using a Red-Black tree.

### 📌 Common Operations:
- Add elements (`add()`)
- Remove elements (`remove()`)
- Check membership (`contains()`)
- Iteration (for-each loop)

### 📌 Example:
```java
import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // Duplicate won't be added

        // Checking membership
        System.out.println("Contains Banana? " + set.contains("Banana"));

        // Removing an element
        set.remove("Banana");

        // Iterating through the set
        for (String fruit : set) {
            System.out.println(fruit);
        }
    }
}
```

### 📊 Time and Space Complexities:
| Operation | HashSet | TreeSet |
|-----------|---------|---------|
| Add       | O(1)    | O(log n)|
| Remove    | O(1)    | O(log n)|
| Contains  | O(1)    | O(log n)|

---

## 🗺️ Map Interface

A **Map** is an object that maps keys to values. Each key is unique.

### ✅ Common Implementations:
- **HashMap**: Unordered, allows `null` values.
- **TreeMap**: Maintains a sorted order of keys.
- **LinkedHashMap**: Maintains insertion order.

### 📌 Common Operations:
- Add key-value pairs (`put()`)
- Remove entries by key (`remove()`)
- Update values (`put()` again with the same key)
- Check for key/value (`containsKey()`, `containsValue()`)
- Iterate through keys, values, and entries

### 📌 Example:
```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        // Updating values
        map.put("Apple", 5);

        // Checking for keys and values
        System.out.println("Contains Banana? " + map.containsKey("Banana"));
        System.out.println("Value for Cherry: " + map.get("Cherry"));

        // Iterating through map entries
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 📊 Time and Space Complexities:
| Operation | HashMap | TreeMap | LinkedHashMap |
|-----------|---------|---------|---------------|
| Insert    | O(1)    | O(log n)| O(1)          |
| Delete    | O(1)    | O(log n)| O(1)          |
| Search    | O(1)    | O(log n)| O(1)          |

---

## ⏳ Queue Interface

A **Queue** follows the First-In-First-Out (FIFO) principle.

### ✅ Common Implementations:
- **LinkedList**: Implements Queue and Deque.
- **PriorityQueue**: Elements are ordered by their natural ordering or comparator.

### 📌 Common Operations:
- Add elements (`offer()` or `add()`)
- Remove elements (`poll()` or `remove()`)
- Peek at the front element (`peek()`)
- Check size and emptiness (`size()`, `isEmpty()`)

### 📌 Example:
```java
import java.util.*;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        // Peeking at the front element
        System.out.println("First element: " + queue.peek());

        // Removing elements in FIFO order
        while (!queue.isEmpty()) {
            System.out.println("Removed: " + queue.poll());
        }
    }
}
```

### 📊 Time and Space Complexities:
| Operation | LinkedList | PriorityQueue |
|-----------|------------|---------------|
| Add       | O(1)       | O(log n)      |
| Remove    | O(1)       | O(log n)      |
| Peek      | O(1)       | O(1)          |

---

## 📚 Key Takeaways

1. Use **List** for ordered collections with duplicates.
2. Use **Set** for unique elements with no duplicates.
3. Use **Map** for key-value pairs.
4. Use **Queue** for FIFO operations.
5. Perform efficient operations by understanding time and space complexities.

---

Up next: Learn how to **Extend Data Structures** in Java by creating custom implementations and enhancing built-in structures for advanced use cases.

