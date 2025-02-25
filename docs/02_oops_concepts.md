# Object-Oriented Programming Concepts (OOPS)

## 🎯 Introduction

Object-Oriented Programming (OOP) is a programming paradigm based on the concept of **objects**—entities that contain data (attributes) and behavior (methods). Java is an object-oriented language, and understanding OOP principles is essential for writing modular, reusable, and maintainable code.

In this guide, we’ll focus on the **20% of core concepts** that will help you understand **80% of OOP in Java**.

---

## 🔍 Classes and Objects

### 📌 What is a Class?
A **class** is a blueprint or template for creating objects. It defines properties (variables) and behaviors (methods) that the objects will have.

### ✅ Example:
```java
public class Person {
    String name;
    int age;

    void introduce() {
        System.out.println("Hi, my name is " + name + " and I am " + age + " years old.");
    }
}
```

### 📌 What is an Object?
An **object** is an instance of a class. It represents a real-world entity.

### ✅ Creating an Object:
```java
public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Alice";
        person.age = 30;
        person.introduce();
    }
}
```

---

## 🎯 Four Pillars of OOP

### 1️⃣ **Encapsulation**
Encapsulation hides internal details and only exposes necessary information through public methods.

**Example:**
```java
public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

### 2️⃣ **Inheritance**
Inheritance allows a class to inherit properties and methods from another class.

**Example:**
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

### 3️⃣ **Polymorphism**
Polymorphism allows objects to take on multiple forms—enabling a single interface to represent different types of behavior.

**Example:**
```java
class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a square");
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        Shape shape = new Circle();
        shape.draw(); // Output: Drawing a circle
    }
}
```

### 4️⃣ **Abstraction**
Abstraction hides complex implementation details and shows only the essential features.

**Example using Abstract Class:**
```java
abstract class Vehicle {
    abstract void move();
}

class Car extends Vehicle {
    void move() {
        System.out.println("Car is moving");
    }
}
```

**Example using Interface:**
```java
interface Drawable {
    void draw();
}

class Rectangle implements Drawable {
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}
```

---

## 🏷️ Interfaces vs. Abstract Classes

| Feature         | Interface                         | Abstract Class                    |
|-----------------|----------------------------------|-----------------------------------|
| Methods         | Only abstract (default/static allowed) | Can have both abstract and concrete methods |
| Variables       | Public, static, final            | Can have instance variables       |
| Inheritance     | Supports multiple inheritance    | Single inheritance                |

**Example of Interface Usage:**
```java
interface Animal {
    void makeSound();
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

---

## 🛠️ Design Principles (SOLID)

1. **Single Responsibility Principle (SRP)**: A class should have only one reason to change.
2. **Open/Closed Principle (OCP)**: Classes should be open for extension but closed for modification.
3. **Liskov Substitution Principle (LSP)**: Derived classes should be substitutable for their base classes.
4. **Interface Segregation Principle (ISP)**: No client should be forced to depend on methods it doesn’t use.
5. **Dependency Inversion Principle (DIP)**: Depend on abstractions, not concrete implementations.

---

## 📚 Key Takeaways

1. **Classes** are blueprints; **objects** are instances.
2. Master the four pillars: **Encapsulation**, **Inheritance**, **Polymorphism**, and **Abstraction**.
3. Use **interfaces** for multiple inheritance and abstraction.
4. Follow **SOLID principles** for clean, scalable code.

---

Up next: Learn about **Advanced Java Concepts** like generics, multithreading, and exception handling to deepen your understanding of Java programming.

