# Advanced Java Concepts

## 🎯 Introduction

This guide covers advanced Java concepts that enhance the power, flexibility, and efficiency of your applications. These features help write clean, reusable, and concurrent code while also handling exceptions effectively.

Following the **Pareto principle**, we'll focus on the **20% of advanced concepts** that provide **80% of practical utility** for real-world Java development.

---

## 🔍 Generics in Java

Generics enable classes, interfaces, and methods to operate on types specified by the user at runtime, enhancing type safety and reducing runtime errors.

### ✅ Why Use Generics?
- Type safety: Detects errors at compile-time.
- Code reusability: Write a single class or method for different data types.

### 📌 Example:
```java
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello Generics");
        System.out.println(stringBox.getItem());
    }
}
```

### 📊 Common Use Cases:
- Collections (`List<T>`, `Map<K, V>`)
- Comparable and Comparator interfaces

---

## ⚙️ Multithreading and Concurrency

Multithreading allows concurrent execution of two or more threads, improving the application's performance and responsiveness.

### ✅ Why Use Multithreading?
- Efficient use of CPU resources.
- Handles multiple tasks simultaneously.
- Essential for concurrent programming in modern applications.

### 📌 Basic Thread Example:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start();
    }
}
```

### 📌 Using Runnable Interface:
```java
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Runnable thread running");
        Thread thread = new Thread(task);
        thread.start();
    }
}
```

### ⏳ Synchronization:
Ensures that only one thread accesses a resource at a time.

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

### 🔧 Executor Framework:
Handles thread pools efficiently for managing multiple threads.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(() -> System.out.println("Task running"));
        executor.shutdown();
    }
}
```

---

## 🚨 Exception Handling

Exception handling in Java is essential for managing errors gracefully and maintaining robust applications.

### ✅ Types of Exceptions:
- **Checked Exceptions**: Must be handled at compile-time (e.g., `IOException`, `SQLException`).
- **Unchecked Exceptions**: Occur at runtime (e.g., `NullPointerException`, `ArrayIndexOutOfBoundsException`).

### 📌 Basic Try-Catch Block:
```java
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Causes ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("This block always executes");
        }
    }
}
```

### 📌 Custom Exception:
```java
class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) throws AgeException {
        int age = 15;
        if (age < 18) {
            throw new AgeException("You must be at least 18 years old.");
        }
    }
}
```

### 🔄 Best Practices:
- Use specific exceptions.
- Avoid catching generic `Exception` unless necessary.
- Always close resources (use try-with-resources).

---

## 🔗 Streams API and Functional Programming (Java 8+)

Java Streams API processes sequences of elements using functional programming principles.

### ✅ Key Features:
- Declarative style (what to do, not how).
- Supports filtering, mapping, and collecting data.

### 📌 Example:
```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.stream()
             .filter(name -> name.startsWith("A"))
             .forEach(System.out::println);
    }
}
```

### 🔄 Common Stream Operations:
- `filter()`: Filter elements based on a condition.
- `map()`: Transform each element.
- `collect()`: Gather results into a collection.

---

## 📚 Key Takeaways

1. **Generics** improve type safety and code reusability.
2. **Multithreading** allows concurrent execution for better performance.
3. **Exception handling** ensures your code handles errors gracefully.
4. The **Streams API** enables functional-style operations on collections.

---

Up next: Dive into **Data Structures and Algorithms**, where you’ll learn how to use Java's powerful features to solve real-world problems efficiently.

