# Java Fundamentals

## 🎯 Introduction

This guide covers essential Java programming concepts that form the foundation for advanced topics like data structures, algorithms, and object-oriented programming. Understanding these core concepts allows you to write efficient, clean, and scalable code.

The goal is to explain 20% of the most impactful concepts that will help you understand 80% of Java's practical usage.

---

## 🔍 Functions and Methods

In Java, **functions** are known as **methods** and are used to perform specific tasks.

### 📌 Syntax of a Method:
```java
public returnType methodName(parameters) {
    // Method body
    return value;
}
```

### ✅ Example:
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

- **public**: Access modifier
- **int**: Return type
- **add**: Method name
- **(int a, int b)**: Parameters

### 🔄 Calling a Method:
```java
Calculator calc = new Calculator();
int result = calc.add(5, 3);
System.out.println("Sum: " + result);
```

---

## 🔀 Method Overloading and Overriding

### 📌 Method Overloading
Creating multiple methods with the same name but different parameters.

```java
public class Printer {
    public void print(String text) {
        System.out.println(text);
    }
    public void print(int number) {
        System.out.println(number);
    }
}
```

### 📌 Method Overriding
Rewriting a parent class's method in a child class to provide specific behavior.

```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

### 🎯 Why Use Them?
- **Overloading** improves code readability.
- **Overriding** enables runtime polymorphism.

---

## 🏷️ Anonymous Classes and Lambdas

### 📌 Anonymous Classes
Used to declare and instantiate a class simultaneously.

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running an anonymous class");
    }
};
task.run();
```

### 📌 Lambda Expressions (Introduced in Java 8)
Short and concise way of writing functions.

```java
Runnable lambdaTask = () -> System.out.println("Running a lambda expression");
lambdaTask.run();
```

### ✅ Benefits:
- Reduces boilerplate code.
- Makes the code more readable and concise.

---

## 🔗 Code Examples

### Example 1: Simple Calculator with Overloading
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public double add(double a, double b) {
        return a + b;
    }
}
```

### Example 2: Polymorphism Through Overriding
```java
class Vehicle {
    void move() {
        System.out.println("Vehicle is moving");
    }
}

class Car extends Vehicle {
    @Override
    void move() {
        System.out.println("Car is driving");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.move();
    }
}
```

---

## 📚 Key Takeaways

1. **Methods** encapsulate reusable code blocks.
2. **Overloading** allows methods with the same name but different parameters.
3. **Overriding** enables subclasses to provide specific implementations.
4. **Anonymous classes** and **lambdas** simplify code, especially for functional programming.

---

Up next: Dive deeper into **Object-Oriented Programming Concepts (OOPS)** to understand how Java leverages classes, objects, inheritance, and more!

