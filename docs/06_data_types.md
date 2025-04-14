# Data Types in Java for Technical Interviews

## 🎯 Introduction

Understanding Java's data types and their performance characteristics is essential for coding interviews. This guide covers the key data type concepts you need to know to solve algorithm problems efficiently at FAANG interviews.

---

## 🔍 Primitive Data Types for Interviews

Primitive data types are the basic building blocks in Java. During interviews, you'll frequently use these types for algorithm implementation.

### ✅ Key Primitives for Interviews:

| Data Type | Size | Range | Interview Usage |
|-----------|------|-------|-----------------|
| int | 32 bits | -2³¹ to 2³¹-1 | Most common for array indices, counting, etc. |
| long | 64 bits | -2⁶³ to 2⁶³-1 | Large numbers, preventing overflow |
| char | 16 bits | 0 to 65,535 | String manipulation, character counting |
| boolean | 1 bit | true/false | Condition flags, visited markers |
| double | 64 bits | ~±1.8×10³⁰⁸ | When precise calculations are needed |

### 📌 Integer Operations in Interviews
```java
// Bit manipulation (common in interviews)
int setBit(int num, int position) {
    return num | (1 << position); // Set bit at position to 1
}

int clearBit(int num, int position) {
    return num & ~(1 << position); // Clear bit at position to 0
}

boolean checkBit(int num, int position) {
    return (num & (1 << position)) != 0; // Check if bit is set
}

// Integer overflow checks (important for edge cases)
public int safeAdd(int a, int b) {
    // Check for overflow
    if (b > 0 && a > Integer.MAX_VALUE - b) {
        return Integer.MAX_VALUE; // Handle overflow
    } else if (b < 0 && a < Integer.MIN_VALUE - b) {
        return Integer.MIN_VALUE; // Handle underflow
    }
    return a + b;
}
```

### 📌 Char Operations for String Problems
```java
// Character classification (useful for parsing)
boolean isUpperCase(char c) {
    return c >= 'A' && c <= 'Z';
}

boolean isLowerCase(char c) {
    return c >= 'a' && c <= 'z';
}

boolean isDigit(char c) {
    return c >= '0' && c <= '9';
}

// Converting char to int
int digitValue(char c) {
    return c - '0';  // Converts '0' to 0, '1' to 1, etc.
}

// Character frequency counting (very common in interviews)
int[] getFrequencyMap(String s) {
    int[] freq = new int[26]; // For lowercase letters
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }
    return freq;
}
```

---

## 🏷️ Wrapper Classes in Interviews

Wrapper classes are often used in collections and generics during interview solutions.

### ✅ Wrapper Classes vs Primitives
| Primitive | Wrapper | When to Use Wrapper |
|-----------|---------|---------------------|
| int | Integer | With collections (ArrayList<Integer>) |
| long | Long | When null values are possible |
| char | Character | With generic algorithms |
| boolean | Boolean | As object references |

### 📌 Auto-boxing and Unboxing
```java
// Auto-boxing (primitive to wrapper)
int primitive = 42;
Integer wrapper = primitive; // Automatically boxed

// Unboxing (wrapper to primitive)
Integer wrapperValue = Integer.valueOf(100);
int primitiveValue = wrapperValue; // Automatically unboxed

// Performance consideration for interviews
public void performanceExample() {
    // This is inefficient due to repeated boxing/unboxing
    Integer sum = 0;
    for (int i = 0; i < 1000000; i++) {
        sum += i; // Each addition involves boxing/unboxing
    }
    
    // More efficient version
    int efficientSum = 0;
    for (int i = 0; i < 1000000; i++) {
        efficientSum += i;
    }
    Integer result = efficientSum; // Single boxing operation
}
```

### 📌 Utility Methods in Wrapper Classes
```java
// Useful methods for interview problems
Integer.parseInt("123");       // String to int
Integer.toString(456);         // int to String
Integer.bitCount(7);           // Count set bits (returns 3 for 7 (111 in binary))
Character.isLetterOrDigit('A'); // Check if character is letter or digit
Boolean.parseBoolean("true");  // String to boolean
```

---

## 📊 String Type in Interviews

Strings are among the most common data types in interview problems, especially at companies like Amazon and Microsoft.

### 📌 String Operations for Interviews
```java
// String creation and manipulation
String s1 = "interview";
String s2 = new String("practice");

// String methods commonly used in interviews
char firstChar = s1.charAt(0);                // 'i'
int length = s1.length();                     // 9
boolean contains = s1.contains("view");       // true
String sub = s1.substring(0, 5);              // "inter"
String[] parts = "a,b,c".split(",");          // ["a", "b", "c"]
String joined = String.join("-", parts);      // "a-b-c"

// Case conversion
String upper = s1.toUpperCase();              // "INTERVIEW"
String lower = upper.toLowerCase();           // "interview"

// Comparison (be careful with == vs equals())
boolean isEqual = s1.equals("interview");     // true
boolean isSameRef = (s1 == "interview");      // may be true (string pool)
boolean ignoreCase = s1.equalsIgnoreCase("INTERVIEW"); // true
```

### 📌 String Builder for Efficient String Concatenation
```java
// Efficient string building (avoid + in loops)
public String buildString(int n) {
    // Bad practice for interviews - O(n²) time complexity
    String result = "";
    for (int i = 0; i < n; i++) {
        result += "a"; // Creates a new string each time
    }
    return result;
    
    // Better practice - O(n) time complexity
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
        sb.append("a");
    }
    return sb.toString();
}

// Common StringBuilder operations in interviews
StringBuilder sb = new StringBuilder();
sb.append("data");          // Add to end
sb.insert(0, "meta");       // Insert at position
sb.reverse();               // Reverse the string
sb.deleteCharAt(0);         // Delete character
sb.replace(0, 3, "new");    // Replace range
String result = sb.toString(); // Convert to string
```

---

## 🧮 Arrays in Interviews

Arrays are fundamental to many algorithms and are extensively tested in technical interviews.

### 📌 Array Operations for Interviews
```java
// Array creation
int[] numbers = new int[5];      // Creates array with default values (0)
int[] primes = {2, 3, 5, 7, 11}; // Initialization with values
int[][] matrix = new int[3][3];  // 2D array

// Array copying techniques
int[] copy1 = Arrays.copyOf(primes, primes.length); // Full copy
int[] copy2 = Arrays.copyOfRange(primes, 1, 4);     // Partial copy [3, 5, 7]

// Array utility methods
Arrays.sort(numbers);                // Sorts in-place
int index = Arrays.binarySearch(primes, 5); // Binary search (array must be sorted)
boolean areEqual = Arrays.equals(primes, copy1); // Deep equality check
Arrays.fill(numbers, 1);            // Fill array with value
```

### 📌 Multidimensional Array Traversal
```java
// Row traversal (cache-friendly)
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = i * j;
    }
}

// Common matrix operations in interviews
// 1. Diagonal traversal
void diagonalTraversal(int[][] matrix) {
    int n = matrix.length;
    // Main diagonal (top-left to bottom-right)
    for (int i = 0; i < n; i++) {
        System.out.print(matrix[i][i] + " ");
    }
    // Anti-diagonal (top-right to bottom-left)
    for (int i = 0; i < n; i++) {
        System.out.print(matrix[i][n - 1 - i] + " ");
    }
}

// 2. Spiral traversal (common interview question)
List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix.length == 0) return result;
    
    int rowBegin = 0, rowEnd = matrix.length - 1;
    int colBegin = 0, colEnd = matrix[0].length - 1;
    
    while (rowBegin <= rowEnd && colBegin <= colEnd) {
        // Traverse Right
        for (int j = colBegin; j <= colEnd; j++) {
            result.add(matrix[rowBegin][j]);
        }
        rowBegin++;
        
        // Traverse Down
        for (int j = rowBegin; j <= rowEnd; j++) {
            result.add(matrix[j][colEnd]);
        }
        colEnd--;
        
        // Traverse Left
        if (rowBegin <= rowEnd) {
            for (int j = colEnd; j >= colBegin; j--) {
                result.add(matrix[rowEnd][j]);
            }
            rowEnd--;
        }
        
        // Traverse Up
        if (colBegin <= colEnd) {
            for (int j = rowEnd; j >= rowBegin; j--) {
                result.add(matrix[j][colBegin]);
            }
            colBegin++;
        }
    }
    return result;
}
```

---

## 🔄 Type Conversion in Interviews

Type conversion is critical for many algorithm implementations and helps prevent subtle bugs.

### 📌 Common Type Conversions
```java
// String to primitive
int intValue = Integer.parseInt("123");       // 123
long longValue = Long.parseLong("9876543210"); // 9876543210
double doubleValue = Double.parseDouble("3.14"); // 3.14
boolean boolValue = Boolean.parseBoolean("true"); // true

// Primitive to String
String fromInt = Integer.toString(456);       // "456"
String fromLong = Long.toString(9876543210L); // "9876543210"
String fromDouble = Double.toString(3.14);    // "3.14"
String fromBoolean = Boolean.toString(true);  // "true"

// Numeric conversion
long longFromInt = (long) 42;                 // 42L
int intFromLong = (int) 9876543210L;          // Truncated value
double doubleFromInt = (double) 123;          // 123.0

// Character-numeric conversions
char charFromInt = (char) 65;                 // 'A'
int intFromChar = (int) 'A';                  // 65
```

### 📌 Type Conversion Pitfalls in Interviews
```java
// Integer overflow
int maxValue = Integer.MAX_VALUE;            // 2147483647
int overflow = maxValue + 1;                 // -2147483648 (underflow)

// Solution: use long for larger numbers
long noOverflow = (long) maxValue + 1;       // 2147483648

// Precision loss
double largeDouble = 1e20;
int truncated = (int) largeDouble;           // Integer.MAX_VALUE or overflow

// String-number conversion failures
try {
    int value = Integer.parseInt("abc");     // NumberFormatException
} catch (NumberFormatException e) {
    System.out.println("Invalid number format");
}
```

---

## 📚 Key Takeaways for Interviews

1. **Understand primitive limits** - Be aware of integer overflow and edge cases
2. **Master string operations** - Efficient creation, manipulation, and comparison
3. **Use StringBuilder** for string concatenation in loops
4. **Leverage wrapper utility methods** for parsing and conversions
5. **Be comfortable with array manipulations** - copying, sorting, searching
6. **Know when to use which data type** to optimize memory and performance

---

Up next: Master **Inbuilt Data Structures** that will help you implement efficient solutions in Java interview problems.

