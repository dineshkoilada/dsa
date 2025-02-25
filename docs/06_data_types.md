# Data Types in Java

## 🎯 Introduction

In Java, **data types** define the kind of data a variable can store. Choosing the right data type is crucial for memory efficiency and optimized performance.

In this guide, we'll focus on the **20% of essential data types** that will help you cover **80% of the use cases** in Java programming.

---

## 🔍 Primitive Data Types

Java has eight built-in **primitive data types** that represent simple values and consume minimal memory.

### ✅ Overview of Primitive Types:

| Data Type | Size (bits) | Default Value | Example Usage                 |
|-----------|------------|---------------|-------------------------------|
| byte      | 8          | 0             | Small integers (-128 to 127)  |
| short     | 16         | 0             | Larger integers               |
| int       | 32         | 0             | Common integer values         |
| long      | 64         | 0L            | Large integers                |
| float     | 32         | 0.0f          | Decimal numbers (single-precision) |
| double    | 64         | 0.0d          | Decimal numbers (double-precision) |
| char      | 16         | ' ' (space)   | Single characters             |
| boolean   | 1          | false         | True/false values             |

### 📌 Example:
```java
public class PrimitiveDataTypes {
    public static void main(String[] args) {
        int number = 100;
        double decimal = 45.67;
        char letter = 'A';
        boolean flag = true;
        System.out.println("Number: " + number + ", Decimal: " + decimal + ", Letter: " + letter + ", Flag: " + flag);
    }
}
```

---

## 🏷️ Extended Data Types (Wrapper Classes)

Java provides **wrapper classes** for each primitive data type, allowing primitives to be used as objects. These are part of the `java.lang` package.

### ✅ Why Use Wrapper Classes?
- Useful in collections (e.g., `ArrayList<Integer>` instead of `int[]`).
- Provide utility methods for conversions and comparisons.
- Allow `null` values (primitives cannot be `null`).

### 📌 Examples of Wrapper Classes:
| Primitive | Wrapper Class |
|-----------|---------------|
| byte      | Byte          |
| short     | Short         |
| int       | Integer       |
| long      | Long          |
| float     | Float         |
| double    | Double        |
| char      | Character     |
| boolean   | Boolean       |

### 📌 Example:
```java
public class WrapperExample {
    public static void main(String[] args) {
        Integer intObj = Integer.valueOf(100);
        Double doubleObj = Double.valueOf(55.5);
        Boolean boolObj = Boolean.TRUE;

        System.out.println("Integer: " + intObj + ", Double: " + doubleObj + ", Boolean: " + boolObj);
    }
}
```

---

## 📊 Custom Data Types (User-Defined Types)

Custom data types allow you to create structures that combine various data types into meaningful entities using **classes**.

### 📌 Example:
```java
public class Employee {
    String name;
    int id;
    double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", ID: " + id + ", Salary: " + salary);
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("John Doe", 101, 75000);
        emp.displayInfo();
    }
}
```

---

## 🔄 Type Casting and Conversion

**Type casting** allows converting a value from one data type to another.

### ✅ Types of Type Casting:
1. **Implicit Casting (Widening Conversion):** Automatically done by Java.
2. **Explicit Casting (Narrowing Conversion):** Requires manual casting.

### 📌 Example:
```java
public class TypeCasting {
    public static void main(String[] args) {
        // Implicit Casting (int to double)
        int num = 10;
        double result = num;
        System.out.println("Implicit Casting: " + result);

        // Explicit Casting (double to int)
        double decimal = 9.7;
        int converted = (int) decimal;
        System.out.println("Explicit Casting: " + converted);
    }
}
```

---

## 🏗️ Type Inference (Java 10+)

Java introduced the `var` keyword for local variable type inference, allowing the compiler to infer the type automatically.

### 📌 Example:
```java
public class TypeInference {
    public static void main(String[] args) {
        var message = "Hello, Java 10!";
        var number = 25;
        System.out.println(message + " Number: " + number);
    }
}
```

---

## 📚 Key Takeaways

1. Java has **8 primitive data types** for efficient memory usage.
2. **Wrapper classes** allow primitives to be used as objects.
3. **Custom data types** enable meaningful entity creation using classes.
4. Use **type casting** and **type inference** to manage data conversions and simplify code.

---

Up next: Explore **Inbuilt Data Structures** in Java, including Lists, Sets, Maps, and their time-space complexities.

