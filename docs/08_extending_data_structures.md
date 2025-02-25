# Extending Data Structures in Java

## 🎯 Introduction

While Java provides a comprehensive set of inbuilt data structures, there are situations where customizing or extending these structures becomes necessary. Extending data structures allows developers to:

- Add new functionalities.
- Optimize specific operations for performance.
- Implement custom business logic that built-in structures don't support.

This guide focuses on the **20% of techniques** that provide **80% of the practical benefits** of extending and customizing Java's data structures.

---

## 🔍 Why Extend Data Structures?

Extending existing data structures is useful when:

1. You need custom behavior for certain operations.
2. You want to add additional constraints (e.g., only allowing positive integers).
3. You need optimized versions of common operations.
4. You want to track additional data, like operation counts.

---

## 🏗️ Extending List Structures

Let's extend **ArrayList** to add custom logging whenever an element is added or removed.

### 📌 Example: Custom Logging ArrayList
```java
import java.util.ArrayList;

public class LoggingArrayList<E> extends ArrayList<E> {
    @Override
    public boolean add(E element) {
        System.out.println("Adding element: " + element);
        return super.add(element);
    }

    @Override
    public E remove(int index) {
        System.out.println("Removing element at index: " + index);
        return super.remove(index);
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        LoggingArrayList<String> list = new LoggingArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.remove(0);
    }
}
```

---

## 📌 Extending Set Structures

Let's extend **HashSet** to count the number of successful insertions.

### 📌 Example: CountingHashSet
```java
import java.util.HashSet;

public class CountingHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            addCount++;
        }
        return added;
    }

    public int getAddCount() {
        return addCount;
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        CountingHashSet<String> set = new CountingHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // Duplicate won't increase count
        System.out.println("Total elements added: " + set.getAddCount());
    }
}
```

---

## 🗺️ Extending Map Structures

Let's extend **HashMap** to log every insertion and update.

### 📌 Example: LoggingHashMap
```java
import java.util.HashMap;

public class LoggingHashMap<K, V> extends HashMap<K, V> {
    @Override
    public V put(K key, V value) {
        System.out.println("Inserting key: " + key + " with value: " + value);
        return super.put(key, value);
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        LoggingHashMap<String, Integer> map = new LoggingHashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Apple", 3); // Overwrite existing value
    }
}
```

---

## ⏳ Custom Queue Implementations

You can extend **PriorityQueue** to reverse the order of priorities.

### 📌 Example: ReversePriorityQueue
```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class ReversePriorityQueue<E> extends PriorityQueue<E> {
    public ReversePriorityQueue() {
        super(Comparator.reverseOrder());
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        ReversePriorityQueue<Integer> queue = new ReversePriorityQueue<>();
        queue.add(10);
        queue.add(5);
        queue.add(20);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll()); // Should print in descending order
        }
    }
}
```

---

## 🛠️ Implementing Custom Data Structures

Sometimes, it's necessary to create entirely new data structures from scratch.

### 📌 Example: Custom Stack Implementation
```java
public class CustomStack {
    private int[] stack;
    private int top;
    private int capacity;

    public CustomStack(int size) {
        stack = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int item) {
        if (top == capacity - 1) {
            throw new RuntimeException("Stack Overflow");
        }
        stack[++top] = item;
    }

    public int pop() {
        if (top == -1) {
            throw new RuntimeException("Stack Underflow");
        }
        return stack[top--];
    }

    public int peek() {
        if (top == -1) {
            throw new RuntimeException("Stack is Empty");
        }
        return stack[top];
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        CustomStack stack = new CustomStack(5);
        stack.push(10);
        stack.push(20);
        System.out.println("Top Element: " + stack.peek());
        System.out.println("Removed Element: " + stack.pop());
    }
}
```

---

## 📚 Key Takeaways

1. Extend existing data structures to add custom functionalities.
2. Override methods to track operations or enforce constraints.
3. Create entirely new data structures for specialized needs.
4. Optimize data handling based on specific use cases.

---

With a solid understanding of extending data structures, you're now ready to dive into more advanced concepts like algorithm optimization, design patterns, and concurrency in Java!

