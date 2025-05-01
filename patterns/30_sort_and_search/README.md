# Sorting and Searching Algorithms Pattern

## 🎯 Introduction

Imagine you're trying to find a specific book in a large, disorganized library. **Sorting and searching algorithms** are the fundamental techniques that help you organize the books (sorting) and then efficiently locate the one you need (searching).

The Sorting and Searching Algorithms Pattern is particularly useful for:
- Organizing data for faster retrieval
- Finding specific elements in a collection
- Analyzing data distribution
- Pre-processing for other algorithms
- Optimizing database operations
- Processing large datasets efficiently

This pattern includes both classic algorithms that have stood the test of time and modern variants optimized for different scenarios.

---

## 🧠 How to Start Thinking About Solving the Problem

1. **Understand the Problem:**
   - What type of data needs to be sorted or searched?
   - Is the data already partially sorted?
   - Are there size constraints or memory limitations?
   - Are there any requirements for stability (preserving original order of equal elements)?

2. **Ask Clarifying Questions:**
   - What's the expected size of the input?
   - Are duplicates allowed or significant?
   - Is the data distribution known (random, nearly sorted, reversed)?
   - Are in-place operations required to save memory?

3. **Identify Clues for Using Specific Algorithms:**
   - Small data sets → Simple algorithms (Insertion Sort, Selection Sort)
   - Large data sets → Efficient algorithms (Merge Sort, Quick Sort, Heap Sort)
   - Nearly sorted data → Adaptive algorithms (Insertion Sort, Tim Sort)
   - Memory constraints → In-place algorithms (Quick Sort, Heap Sort)
   - Need stability → Stable algorithms (Merge Sort, Tim Sort)

4. **Predicting if a Specific Algorithm Is Applicable:**
   - Consider time and space complexity requirements
   - Evaluate the trade-offs between algorithms based on data characteristics

---

## 🏁 Problem-Solving Template

### ✅ **1. Define the Problem Clearly**
- What is being sorted or searched?
- What are the constraints (time, space, stability)?
- What characteristics does the input data have?

### ✅ **2. Ask Questions Before Defining Base Cases**
- Are there any special requirements for handling duplicates?
- Should the solution prioritize average case or worst case performance?
- Is the data expected to have any specific patterns?

### ✅ **3. Identify Base Cases**
- Empty array: Return empty result or appropriate indicator
- Single element array: Already sorted
- Two element array: Simple comparison

### ✅ **4. Write Pseudo-Code for Base Cases**

**For Sorting:**
```
function sort(array):
    if array is empty or has one element:
        return array
    
    select appropriate sorting algorithm based on constraints
    apply the algorithm
    
    return sorted array
```

**For Searching:**
```
function search(array, target):
    if array is empty:
        return -1 (or appropriate value indicating not found)
    
    select appropriate searching algorithm based on array characteristics
    apply the algorithm
    
    return index of target or indication that target was not found
```

### ✅ **5. Write the Code Skeleton**
```java
import java.util.*;

public class SortingAndSearching {
    // Sorting algorithms
    public static void bubbleSort(int[] arr) {
        // Implementation
    }
    
    public static void selectionSort(int[] arr) {
        // Implementation
    }
    
    public static void insertionSort(int[] arr) {
        // Implementation
    }
    
    public static void mergeSort(int[] arr) {
        // Implementation
    }
    
    public static void quickSort(int[] arr) {
        // Implementation
    }
    
    public static void heapSort(int[] arr) {
        // Implementation
    }
    
    public static void countingSort(int[] arr) {
        // Implementation
    }
    
    public static void radixSort(int[] arr) {
        // Implementation
    }
    
    public static void bucketSort(double[] arr) {
        // Implementation
    }
    
    // Searching algorithms
    public static int linearSearch(int[] arr, int target) {
        // Implementation
        return -1;
    }
    
    public static int binarySearch(int[] arr, int target) {
        // Implementation
        return -1;
    }
    
    public static int jumpSearch(int[] arr, int target) {
        // Implementation
        return -1;
    }
    
    public static int interpolationSearch(int[] arr, int target) {
        // Implementation
        return -1;
    }
    
    public static int exponentialSearch(int[] arr, int target) {
        // Implementation
        return -1;
    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double)(target - position[i]) / speed[i];
        }
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
        int fleets = 0;
        double curTime = 0;
        for (int i = 0; i < n; i++) {
            if (cars[i][1] > curTime) {
                fleets++;
                curTime = cars[i][1];
            }
        }
        return fleets;
    }
    
    public static int linearSearchEasy(int[] arr, int target) {
        // For simplicity, reuse the existing linearSearch() logic:
        return linearSearch(arr, target);
    }

    public static int linearSearchMedium(int[] arr, int n) {
        // Find missing number from 1..n
        int sumExpected = n * (n + 1) / 2;
        int sumActual = 0;
        for (int val : arr) {
            sumActual += val;
        }
        return sumExpected - sumActual;
    }

    public static boolean linearSearchHard(int[] arr, int k) {
        // Check duplicates within k distance
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return true;
            }
            set.add(arr[i]);
            if (set.size() > k) {
                set.remove(arr[i - k]);
            }
        }
        return false;
    }

    public static int binarySearchEasy(int[] arr, int target) {
        // Reuse the existing binarySearch() code:
        return binarySearch(arr, target);
    }

    public static int binarySearchMedium(int[] arr, int target) {
        // Return insert position if not found
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static int binarySearchHard(int[] arr) {
        // Find peak in bitonic array
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static int jumpSearchEasy(int[] arr, int target) {
        // Reuse the existing jumpSearch() code:
        return jumpSearch(arr, target);
    }

    public static int jumpSearchMedium(int[] arr, int target) {
        // Same approach but allow dynamic step logic:
        // ...existing code or minor changes to step size...
        return jumpSearch(arr, target);
    }

    public static int jumpSearchHard(int[] arr, int target) {
        // Reuse jumpSearch approach for sparse arrays:
        return jumpSearch(arr, target);
    }

    public static int interpolationSearchEasy(int[] arr, int target) {
        // Reuse the existing interpolationSearch() code:
        return interpolationSearch(arr, target);
    }

    public static int interpolationSearchMedium(int[] arr, int target) {
        // ...existing code or slight tweak...
        return interpolationSearch(arr, target);
    }

    public static int interpolationSearchHard(int[] arr, int target) {
        // ...existing code for large arrays...
        return interpolationSearch(arr, target);
    }

    public static int exponentialSearchEasy(int[] arr, int target) {
        return exponentialSearch(arr, target);
    }

    public static int exponentialSearchMedium(int[] arr, int target) {
        // ...existing code or minor modifications...
        return exponentialSearch(arr, target);
    }

    public static int exponentialSearchHard(int[] arr, int target) {
        // ...existing code for large/unbounded arrays...
        return exponentialSearch(arr, target);
    }
}
```

### ✅ **6. Edge Cases to Consider**
- Empty arrays
- Arrays with duplicates
- Already sorted arrays (best case)
- Reverse sorted arrays (worst case for some algorithms)
- Arrays with all identical elements
- Very large arrays (space complexity becomes critical)
- Arrays with special patterns (alternating, cyclic, etc.)

### ✅ **7. How to Predict Time and Space Complexity**

| Algorithm         | Best Time      | Average Time   | Worst Time     | Space         | Stable | In-Place |
|-------------------|----------------|----------------|----------------|---------------|--------|----------|
| Bubble Sort       | O(n)           | O(n²)          | O(n²)          | O(1)          | Yes    | Yes      |
| Selection Sort    | O(n²)          | O(n²)          | O(n²)          | O(1)          | No     | Yes      |
| Insertion Sort    | O(n)           | O(n²)          | O(n²)          | O(1)          | Yes    | Yes      |
| Merge Sort        | O(n log n)     | O(n log n)     | O(n log n)     | O(n)          | Yes    | No       |
| Quick Sort        | O(n log n)     | O(n log n)     | O(n²)          | O(log n)      | No     | Yes      |
| Heap Sort         | O(n log n)     | O(n log n)     | O(n log n)     | O(1)          | No     | Yes      |
| Counting Sort     | O(n + k)       | O(n + k)       | O(n + k)       | O(n + k)      | Yes    | No       |
| Radix Sort        | O(nk)          | O(nk)          | O(nk)          | O(n + k)      | Yes    | No       |
| Bucket Sort       | O(n + k)       | O(n + k)       | O(n²)          | O(n * k)      | Yes    | No       |
| Linear Search     | O(1)           | O(n/2)         | O(n)           | O(1)          | N/A    | Yes      |
| Binary Search     | O(1)           | O(log n)       | O(log n)       | O(1)          | N/A    | Yes      |
| Jump Search       | O(1)           | O(√n)          | O(√n)          | O(1)          | N/A    | Yes      |
| Interpolation     | O(1)           | O(log log n)   | O(n)           | O(1)          | N/A    | Yes      |
| Exponential       | O(1)           | O(log n)       | O(log n)       | O(1)          | N/A    | Yes      |

**How to derive these complexities:**
- **Time Complexity:** Count the number of comparisons and swaps in nested loops.
- **Space Complexity:** Measure additional memory needed beyond input storage.

---

## 📚 A. Key Sorting Algorithms

### 1. Bubble Sort

**How It Works:**
Repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order.

```java
public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false;
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j+1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }
        // If no swapping occurred in this pass, array is sorted
        if (!swapped) break;
    }
}
```

**When to Use:**
- Small datasets
- Nearly sorted data
- Educational purposes
- When simplicity is more important than efficiency

**Time Complexity:** O(n²) average and worst case, O(n) best case
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Sort Colors

**Problem:**
Given an array `nums` with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent. The colors are represented as integers 0 (red), 1 (white), and 2 (blue).

**Input:** `[2,0,2,1,1,0]`

**Expected Output:** `[0,0,1,1,2,2]`

##### 🔑 **Solution Steps**
1. Use bubble sort to sort the array of colors.
2. Since the range of values is small (just 0, 1, and 2), bubble sort works efficiently.

##### ✅ **Code:**
```java
public void sortColors(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false;
        for (int j = 0; j < n - i - 1; j++) {
            if (nums[j] > nums[j + 1]) {
                // Swap nums[j] and nums[j+1]
                int temp = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = temp;
                swapped = true;
            }
        }
        // If no swapping occurred in this pass, array is sorted
        if (!swapped) break;
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n²) worst case, though with a small range performance is good
- **Space:** O(1) in-place sorting

#### 📚 Example 2: Medium Problem - Sort Array By Parity

**Problem:**
Given an integer array `nums`, move all the even integers at the beginning of the array followed by all the odd integers.

**Input:** `[3,1,2,4]`

**Expected Output:** `[2,4,3,1]` (Note: The relative order among even/odd can vary)

##### 🔑 **Solution Steps**
1. Modify bubble sort to prioritize even numbers over odd numbers
2. Swap elements based on their parity rather than value

##### ✅ **Code:**
```java
public int[] sortArrayByParity(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int evenIndex = 0;
    int oddIndex = n - 1;
    
    for (int num : nums) {
        if (num % 2 == 0) {
            // If even, insert at the beginning
            result[evenIndex++] = num;
        } else {
            // If odd, insert at the end
            result[oddIndex--] = num;
        }
    }
    
    return result;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) - we only need one pass through the array
- **Space:** O(n) - for the result array

#### 📚 Example 3: Hard Problem - Sort Matrix Diagonally

**Problem:**
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. Sort all matrix diagonals in ascending order and return the resulting matrix.

**Input:** 
```
[
  [3,3,1,1],
  [2,2,1,2],
  [1,1,1,2]
]
```

**Expected Output:**
```
[
  [1,1,1,1],
  [1,2,2,2],
  [1,2,3,3]
]
```

##### 🔑 **Solution Steps**
1. Identify all diagonals in the matrix (cells with same value of row-column)
2. Use bubble sort to sort each diagonal
3. Place sorted elements back into the matrix

##### ✅ **Code:**
```java
public int[][] diagonalSort(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    
    // Sort each diagonal starting from first row
    for (int col = 0; col < n; col++) {
        sortDiagonal(mat, 0, col);
    }
    
    // Sort each diagonal starting from first column (except the one already sorted)
    for (int row = 1; row < m; row++) {
        sortDiagonal(mat, row, 0);
    }
    
    return mat;
}

private void sortDiagonal(int[][] mat, int row, int col) {
    int m = mat.length;
    int n = mat[0].length;
    
    // Extract diagonal elements
    List<Integer> diagonal = new ArrayList<>();
    int r = row, c = col;
    while (r < m && c < n) {
        diagonal.add(mat[r][c]);
        r++;
        c++;
    }
    
    // Sort diagonal using bubble sort
    int size = diagonal.size();
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (diagonal.get(j) > diagonal.get(j + 1)) {
                // Swap elements
                int temp = diagonal.get(j);
                diagonal.set(j, diagonal.get(j + 1));
                diagonal.set(j + 1, temp);
            }
        }
    }
    
    // Put sorted elements back to the matrix
    r = row;
    c = col;
    int index = 0;
    while (r < m && c < n) {
        mat[r][c] = diagonal.get(index++);
        r++;
        c++;
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(m * n * min(m, n)²) where each diagonal can be of length min(m, n)
- **Space:** O(min(m, n)) for storing diagonal elements

### 2. Selection Sort

**How It Works:**
Divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list and a sublist of the remaining unsorted items at the back (right) of the list. It iteratively selects the smallest (or largest, depending on sorting order) element from the unsorted sublist, swaps it with the leftmost unsorted element, and moves the sublist boundaries one element to the right.

```java
public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        // Find the minimum element in unsorted array
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        // Swap the found minimum element with the first element of the unsorted part
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}
```

**When to Use:**
- Small datasets
- When memory write is a costly operation (e.g., EEPROM)
- When the algorithm will be run on a read-only list (e.g., linked list)

**Time Complexity:** O(n²) for all cases
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Find Smallest Letter Greater Than Target

**Problem:**
Given a characters array `letters` that is sorted in non-decreasing order and a character `target`, return the smallest character in the array that is larger than `target`.

**Input:** letters = ["c","f","j"], target = "a"

**Expected Output:** "c"

##### 🔑 **Solution Steps**
1. Adapt selection sort principle to find the smallest element greater than the target
2. Iterate through the array once to find this element

##### ✅ **Code:**
```java
public char nextGreatestLetter(char[] letters, char target) {
    char result = letters[0]; // Default to first letter if no greater letter exists
    
    for (char letter : letters) {
        if (letter > target) {
            result = letter;
            break;
        }
    }
    
    return result;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) in worst case
- **Space:** O(1)

#### 📚 Example 2: Medium Problem - Rank Transform of an Array

**Problem:**
Given an array of integers `arr`, replace each element with its rank. The rank represents how large the element is. The rank has the following rules:
- Rank is an integer starting from 1.
- The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
- Rank should be as small as possible.

**Input:** arr = [40,10,20,30]

**Expected Output:** [4,1,2,3]

##### 🔑 **Solution Steps**
1. Create a copy of the array and sort it using selection sort
2. Map each unique value to its rank
3. Replace each element in the original array with its rank

##### ✅ **Code:**
```java
public int[] arrayRankTransform(int[] arr) {
    int n = arr.length;
    if (n == 0) return new int[0];
    
    // Create a copy of the array with original indices
    int[][] pairs = new int[n][2];
    for (int i = 0; i < n; i++) {
        pairs[i][0] = arr[i];
        pairs[i][1] = i;
    }
    
    // Sort using selection sort
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (pairs[j][0] < pairs[minIdx][0]) {
                minIdx = j;
            }
        }
        // Swap the found minimum element with the first element
        int[] temp = pairs[minIdx];
        pairs[minIdx] = pairs[i];
        pairs[i] = temp;
    }
    
    // Assign ranks
    int[] result = new int[n];
    int rank = 1;
    
    for (int i = 0; i < n; i++) {
        // If current element is different from previous, increment rank
        if (i > 0 && pairs[i][0] != pairs[i-1][0]) {
            rank++;
        }
        // Assign rank to original position
        result[pairs[i][1]] = rank;
    }
    
    return result;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n²) due to selection sort
- **Space:** O(n) for the pairs array

#### 📚 Example 3: Hard Problem - Minimum Increment to Make Array Unique

**Problem:**
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
Return the least number of moves to make every value in A unique.

**Input:** [1,2,2]

**Expected Output:** 1 (After 1 move, the array could be [1,2,3])

##### 🔑 **Solution Steps**
1. Sort the array using selection sort
2. For each pair of adjacent elements, if they are equal or the second is smaller, increment the second element to be one more than the first
3. Count the total number of increments needed

##### ✅ **Code:**
```java
public int minIncrementForUnique(int[] A) {
    int n = A.length;
    if (n <= 1) return 0;
    
    // Sort using selection sort
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (A[j] < A[minIdx]) {
                minIdx = j;
            }
        }
        // Swap the found minimum element with the first element
        int temp = A[minIdx];
        A[minIdx] = A[i];
        A[i] = temp;
    }
    
    int moves = 0;
    
    // Make array unique
    for (int i = 1; i < n; i++) {
        if (A[i] <= A[i-1]) {
            int increment = A[i-1] - A[i] + 1;
            A[i] += increment;
            moves += increment;
        }
    }
    
    return moves;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n²) due to selection sort
- **Space:** O(1) as sorting is done in-place

### 3. Insertion Sort

**How It Works:**
Builds a final sorted array one item at a time. It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort. The algorithm divides the list into a sorted and an unsorted region. It iterates over the unsorted region and removes one element at a time, inserting it into the correct position in the sorted region.

```java
public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;
        // Move elements of arr[0..i-1], that are greater than key,
        // to one position ahead of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
```

**When to Use:**
- Small datasets
- Nearly sorted data
- When new elements are continuously being added to the list

**Time Complexity:** Best: O(n), Average/Worst: O(n²)
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Sort Array By Parity

**Problem:**
Given an array of integers `nums`, move all the even integers at the beginning of the array followed by all the odd integers. Return any array that satisfies this condition.

**Input:** nums = [3,1,2,4]

**Expected Output:** [2,4,3,1] (or any array that puts evens before odds)

##### 🔑 **Solution Steps**
1. Apply insertion sort principles, but instead of sorting by value, sort by parity
2. Maintain two sections: even numbers (at the beginning) and odd numbers (at the end)
3. Insert each new element in the appropriate section

##### ✅ **Code:**
```java
public int[] sortArrayByParity(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int evenIndex = 0;
    int oddIndex = n - 1;
    
    for (int num : nums) {
        if (num % 2 == 0) {
            // If even, insert at the beginning
            result[evenIndex++] = num;
        } else {
            // If odd, insert at the end
            result[oddIndex--] = num;
        }
    }
    
    return result;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) - we only need one pass through the array
- **Space:** O(n) - for the result array

#### 📚 Example 2: Medium Problem - Sort Colors

**Problem:**
Given an array `nums` with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue. We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

**Input:** nums = [2,0,2,1,1,0]

**Expected Output:** [0,0,1,1,2,2]

##### 🔑 **Solution Steps**
1. Use an insertion sort approach to categorize elements
2. Keep track of positions for each color
3. Reorder the array in-place

##### ✅ **Code:**
```java
public void sortColors(int[] nums) {
    int n = nums.length;
    int ptr0 = 0;    // Position to place next 0
    int ptr2 = n - 1; // Position to place next 2
    int i = 0;
    
    while (i <= ptr2) {
        if (nums[i] == 0) {
            // Swap with the ptr0 position
            int temp = nums[i];
            nums[i] = nums[ptr0];
            nums[ptr0] = temp;
            ptr0++;
            i++;
        } else if (nums[i] == 2) {
            // Swap with the ptr2 position
            int temp = nums[i];
            nums[i] = nums[ptr2];
            nums[ptr2] = temp;
            ptr2--;
        } else {
            // For 1, just move forward
            i++;
        }
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) - we process each element at most once
- **Space:** O(1) - sorting is done in-place

#### 📚 Example 3: Hard Problem - Car Fleet

**Problem:**
N cars are going to the same destination along a one-lane road. The destination is target miles away. Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target. A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position). A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet. If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet. How many car fleets will arrive at the destination?

**Input:** target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]

**Expected Output:** 3

##### 🔑 **Solution Steps**
1. Sort by position using insertion sort
2. Calculate time to reach target for each car
3. Count fleets based on arrival times

##### ✅ **Code:**
```java
public int carFleet(int target, int[] position, int[] speed) {
    int n = position.length;
    double[][] cars = new double[n][2];
    for (int i = 0; i < n; i++) {
        cars[i][0] = position[i];
        cars[i][1] = (double)(target - position[i]) / speed[i];
    }
    Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
    int fleets = 0;
    double curTime = 0;
    for (int i = 0; i < n; i++) {
        if (cars[i][1] > curTime) {
            fleets++;
            curTime = cars[i][1];
        }
    }
    return fleets;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n²) due to insertion sort
- **Space:** O(n) for the cars array

### 4. Merge Sort

**How It Works:**
Divides the unsorted list into n sublists, each containing one element, and then repeatedly merges sublists to produce new sorted sublists until there is only one sublist remaining.

```java
public static void mergeSort(int[] arr) {
    if (arr.length > 1) {
        mergeSortHelper(arr, 0, arr.length - 1);
    }
}

private static void mergeSortHelper(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        
        // Sort first and second halves
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);
        
        // Merge the sorted halves
        merge(arr, left, mid, right);
    }
}

private static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    
    // Create temp arrays
    int[] L = new int[n1];
    int[] R = new int[n2];
    
    // Copy data to temp arrays
    for (int i = 0; i < n1; i++) {
        L[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[mid + 1 + j];
    }
    
    // Merge the temp arrays back into arr[left..right]
    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    
    // Copy remaining elements of L[] if any
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    
    // Copy remaining elements of R[] if any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}
```

**When to Use:**
- Large datasets
- When stability is required
- When guaranteed worst-case performance is important
- External sorting (for large files that don't fit in memory)

**Time Complexity:** O(n log n) for all cases
**Space Complexity:** O(n)

#### 📚 Example 1: Easy Problem - Merge Sorted Array

**Problem:**
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively. Merge `nums1` and `nums2` into a single array sorted in non-decreasing order. The final sorted array should be stored inside `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to 0 and should be ignored.

**Input:** nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

**Expected Output:** [1,2,2,3,5,6]

##### 🔑 **Solution Steps**
1. Use the merge operation from merge sort
2. Start filling the array from the end to avoid overwriting elements
3. Compare elements from both arrays and place them in the correct order

##### ✅ **Code:**
```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1; // Pointer for nums1
    int p2 = n - 1; // Pointer for nums2
    int p = m + n - 1; // Pointer for merged array (end of nums1)
    
    // While both arrays have elements
    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[p] = nums1[p1];
            p1--;
        } else {
            nums1[p] = nums2[p2];
            p2--;
        }
        p--;
    }
    
    // If nums2 still has elements, copy them to nums1
    while (p2 >= 0) {
        nums1[p] = nums2[p2];
        p2--;
        p--;
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(m + n) - we process each element once
- **Space:** O(1) - for merging in-place

#### 📚 Example 2: Medium Problem - Count of Range Sum

**Problem:**
Given an integer array `nums` and lower and upper boundaries `lower` and `upper`, return the number of range sums that lie in `[lower, upper]` inclusive. Range sum `S(i, j)` is defined as the sum of the elements in `nums` between indices `i` and `j` inclusive, where `i <= j`.

**Input:** nums = [-2,5,-1], lower = -2, upper = 2

**Expected Output:** 3 (The three ranges are: [0,0], [2,2], and [0,2])

##### 🔑 **Solution Steps**
1. Calculate prefix sums to convert range sums to differences
2. Use merge sort's divide and conquer approach to count ranges
3. Count valid ranges during the merge phase

##### ✅ **Code:**
```java
public int countRangeSum(int[] nums, int lower, int upper) {
    int n = nums.length;
    long[] sums = new long[n + 1];
    
    // Calculate prefix sums
    for (int i = 0; i < n; i++) {
        sums[i + 1] = sums[i] + nums[i];
    }
    
    return countWhileMergeSort(sums, 0, n + 1, lower, upper);
}

private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
    if (end - start <= 1) return 0;
    
    int mid = (start + end) / 2;
    int count = countWhileMergeSort(sums, start, mid, lower, upper) + 
               countWhileMergeSort(sums, mid, end, lower, upper);
    
    // Count ranges that span both subarrays
    int j = mid, k = mid;
    for (int i = start; i < mid; i++) {
        // Find the range [j, k) where sums[j] - sums[i] >= lower and sums[k] - sums[i] > upper
        while (j < end && sums[j] - sums[i] < lower) j++;
        while (k < end && sums[k] - sums[i] <= upper) k++;
        count += (k - j);
    }
    
    // Merge the two sorted subarrays
    merge(sums, start, mid, end);
    
    return count;
}

private void merge(long[] sums, int start, int mid, int end) {
    long[] temp = new long[end - start];
    int i = start, j = mid, k = 0;
    
    while (i < mid && j < end) {
        if (sums[i] <= sums[j]) {
            temp[k++] = sums[i++];
        } else {
            temp[k++] = sums[j++];
        }
    }
    
    while (i < mid) temp[k++] = sums[i++];
    while (j < end) temp[k++] = sums[j++];
    
    // Copy back to original array
    for (i = 0; i < temp.length; i++) {
        sums[start + i] = temp[i];
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log² n) where each level of recursion does O(n log n) work 
- **Space:** O(n) for the temporary arrays used in merging

#### 📚 Example 3: Hard Problem - Count of Smaller Numbers After Self

**Problem:**
Given an integer array `nums`, return an integer array `counts` where `counts[i]` is the number of smaller elements to the right of `nums[i]`.

**Input:** nums = [5,2,6,1]

**Expected Output:** [2,1,1,0]

##### 🔑 **Solution Steps**
1. Use a modified merge sort to count inversions
2. Track original indices during sorting
3. Count smaller elements while merging

##### ✅ **Code:**
```java
public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int[][] pairs = new int[n][2]; // [value, original index]
    
    // Initialize pairs
    for (int i = 0; i < n; i++) {
        pairs[i][0] = nums[i];
        pairs[i][1] = i;
    }
    
    // Perform merge sort
    mergeSort(pairs, 0, n, result);
    
    // Convert to List
    List<Integer> counts = new ArrayList<>();
    for (int count : result) {
        counts.add(count);
    }
    
    return counts;
}

private void mergeSort(int[][] pairs, int start, int end, int[] result) {
    if (end - start <= 1) return;
    
    int mid = (start + end) / 2;
    mergeSort(pairs, start, mid, result);
    mergeSort(pairs, mid, end, result);
    merge(pairs, start, mid, end, result);
}

private void merge(int[][] pairs, int start, int mid, int end, int[] result) {
    int[][] temp = new int[end - start][2];
    int i = start, j = mid, k = 0;
    
    while (i < mid && j < end) {
        if (pairs[i][0] <= pairs[j][0]) {
            // When we put an element from the left side, we know 
            // how many elements from the right side are smaller
            result[pairs[i][1]] += (j - mid);
            temp[k++] = pairs[i++];
        } else {
            temp[k++] = pairs[j++];
        }
    }
    
    while (i < mid) {
        // All remaining elements from right are smaller
        result[pairs[i][1]] += (j - mid);
        temp[k++] = pairs[i++];
    }
    
    while (j < end) temp[k++] = pairs[j++];
    
    // Copy back to original array
    for (i = 0; i < temp.length; i++) {
        pairs[start + i] = temp[i];
    }
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) - merge sort complexity
- **Space:** O(n) - for the temporary arrays

### 5. Quick Sort

**How It Works:**
Selects a 'pivot' element and partitions the array around it, then recursively sorts the sub-arrays.

```java
public static void quickSort(int[] arr) {
    quickSortHelper(arr, 0, arr.length - 1);
}

private static void quickSortHelper(int[] arr, int low, int high) {
    if (low < high) {
        // Partition the array and get the pivot position
        int pivot = partition(arr, low, high);
        
        // Recursively sort elements before and after pivot
        quickSortHelper(arr, low, pivot - 1);
        quickSortHelper(arr, pivot + 1, high);
    }
}

private static int partition(int[] arr, int low, int high) {
    // Choose the rightmost element as pivot
    int pivot = arr[high];
    int i = low - 1; // Index of smaller element
    
    for (int j = low; j < high; j++) {
        // If current element is smaller than or equal to pivot
        if (arr[j] <= pivot) {
            i++;
            
            // Swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // Swap arr[i+1] and arr[high] (put the pivot in its correct position)
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1; // Return position of the pivot
}
```

**When to Use:**
- Large datasets
- When average case performance is more important than worst case
- When in-place sorting is required to save memory
- In practice, most efficient for random data

**Time Complexity:** O(n log n) average case, O(n²) worst case
**Space Complexity:** O(log n) due to recursion stack

#### 📚 Example 1: Easy Problem - Sort an Array of Integers
**Problem:**
Given an array of integers, sort them using Quick Sort.

**Input:** [3,1,4,1,5,9]  
**Expected Output:** [1,1,3,4,5,9]

##### 🔑 **Solution Steps**
1. Apply quickSort to the entire array.
2. Recursively partition until fully sorted.

##### ✅ **Code:**
```java
public static void quickSortEasy(int[] arr) {
    quickSortHelper(arr, 0, arr.length - 1);
}

private static void quickSortHelper(int[] arr, int low, int high) {
    if (low < high) {
        // Partition the array and get the pivot position
        int pivot = partition(arr, low, high);
        
        // Recursively sort elements before and after pivot
        quickSortHelper(arr, low, pivot - 1);
        quickSortHelper(arr, pivot + 1, high);
    }
}

private static int partition(int[] arr, int low, int high) {
    // Choose the rightmost element as pivot
    int pivot = arr[high];
    int i = low - 1; // Index of smaller element
    
    for (int j = low; j < high; j++) {
        // If current element is smaller than or equal to pivot
        if (arr[j] <= pivot) {
            i++;
            
            // Swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // Swap arr[i+1] and arr[high] (put the pivot in its correct position)
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1; // Return position of the pivot
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) average, O(n²) worst  
- **Space:** O(log n) recursive stack

#### 📚 Example 2: Medium Problem - Kth Largest Element
**Problem:**
Find the kth largest element in an unsorted array using partition logic from Quick Sort.

**Input:** nums = [3,2,1,5,6,4], k = 2  
**Expected Output:** 5

##### 🔑 **Solution Steps**
1. Partition the array around a pivot.
2. If pivot is in the kth largest position, return it; else recurse on the correct side.

##### ✅ **Code:**
```java
public int findKthLargest(int[] nums, int k) {
    // ...existing code...
    // Use partition to place pivot in correct position.
    // Recur until pivot's index == nums.length - k.
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n) average, O(n²) worst  
- **Space:** O(1) or O(log n) due to recursion

#### 📚 Example 3: Hard Problem - Sort Strings by Custom Comparator
**Problem:**
Given an array of strings, sort them in descending order by length, using Quick Sort.

**Input:** ["apple","pi","sort","quick"]  
**Expected Output:** ["apple","quick","sort","pi"]

##### 🔑 **Solution Steps**
1. Partition strings by comparing their lengths.
2. Recursively apply quick sort to subarrays.

##### ✅ **Code:**
```java
public static void quickSortStrings(String[] arr) {
    quickSortStringsHelper(arr, 0, arr.length - 1);
}

private static void quickSortStringsHelper(String[] arr, int low, int high) {
    if (low < high) {
        // Partition the array and get the pivot position
        int pivot = partitionStrings(arr, low, high);
        
        // Recursively sort elements before and after pivot
        quickSortStringsHelper(arr, low, pivot - 1);
        quickSortStringsHelper(arr, pivot + 1, high);
    }
}

private static int partitionStrings(String[] arr, int low, int high) {
    String pivot = arr[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        // If current string is longer than or equal to pivot
        if (arr[j].length() >= pivot.length()) {
            i++;
            // Swap arr[i] and arr[j]
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // Swap arr[i+1] and arr[high]
    String temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1;
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n) average, O(n²) worst  
- **Space:** O(log n)

### 6. Heap Sort

**How It Works:**
Converts the array into a max heap, then repeatedly extracts the maximum element and rebuilds the heap.

```java
public static void heapSort(int[] arr) {
    int n = arr.length;
    
    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }
    
    // One by one extract elements from heap
    for (int i = n - 1; i > 0; i--) {
        // Move current root to end
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        
        // Call max heapify on the reduced heap
        heapify(arr, i, 0);
    }
}

// To heapify a subtree rooted with node i which is an index in arr[]
private static void heapify(int[] arr, int n, int i) {
    int largest = i; // Initialize largest as root
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    
    // If left child is larger than root
    if (left < n && arr[left] > arr[largest]) {
        largest = left;
    }
    
    // If right child is larger than largest so far
    if (right < n && arr[right] > arr[largest]) {
        largest = right;
    }
    
    // If largest is not root
    if (largest != i) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;
        
        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}
```

**When to Use:**
- When worst-case O(n log n) is required
- When in-place sorting is needed
- When relatively consistent performance is important

**Time Complexity:** O(n log n) for all cases
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Sort an Array of Integers
**Problem:**
Given an array of integers, sort them in ascending order using heap sort.

**Input:** [3,2,1]  
**Expected Output:** [1,2,3]

##### 🔑 **Solution Steps**
1. Convert the array to a max heap.
2. Swap the max element (root) with the last element and reduce the heap size.
3. Repeat until the array is fully sorted.

##### ✅ **Code:**
```java
public static void heapSortEasy(int[] arr) {
    // ...existing code of heapify/heapSort logic...
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n)  
- **Space:** O(1)

#### 📚 Example 2: Medium Problem - Kth Largest Element
**Problem:**
Find the kth largest element in an unsorted array using a max heap.

**Input:** [3,2,1,5,6,4], k = 2  
**Expected Output:** 5

##### 🔑 **Solution Steps**
1. Build a max heap from the given array.
2. Extract the max k-1 times.
3. The next extracted max is the kth largest element.

##### ✅ **Code:**
```java
public int findKthLargestHeap(int[] nums, int k) {
    // ...existing code to build heap...
    // ...pop max k-1 times...
    // return the next max
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n + k log n)  
- **Space:** O(1)

#### 📚 Example 3: Hard Problem - Sort a Nearly Complete Binary Tree
**Problem:**
Given a nearly complete binary tree stored in an array, reorder it using heap sort to ensure its final form is a valid max heap and sorted array.

**Input:** [16,14,10,8,7,9,3,2,4,1]  
**Expected Output:** [1,2,3,4,7,8,9,10,14,16]

##### 🔑 **Solution Steps**
1. Confirm the array representation of a binary tree.
2. Perform heap sort to both fix the heap property and sort the data.
3. The result is a sorted list in ascending order.

##### ✅ **Code:**
```java
public static void sortNearlyCompleteTree(int[] arr) {
    // ...existing code of heapify/heapSort...
}
```

##### ⏱️ **Time and Space Complexity:**
- **Time:** O(n log n)  
- **Space:** O(1)

### 7. Counting Sort

**How It Works:**
Counts the occurrences of each element and reconstructs the sorted array using these counts.

```java
public static void countingSort(int[] arr) {
    if (arr.length == 0) return;
    
    // Find the maximum value in the array
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    
    // Create count array (of size max+1)
    int[] count = new int[max + 1];
    
    // Store count of each element
    for (int i = 0; i < arr.length; i++) {
        count[arr[i]]++;
    }
    
    // Change count[i] so that it contains the position of this element in output array
    for (int i = 1; i <= max; i++) {
        count[i] += count[i - 1];
    }
    
    // Build the output array
    int[] output = new int[arr.length];
    for (int i = arr.length - 1; i >= 0; i--) {
        output[count[arr[i]] - 1] = arr[i];
        count[arr[i]]--;
    }
    
    // Copy the output array to arr
    for (int i = 0; i < arr.length; i++) {
        arr[i] = output[i];
    }
}
```

**When to Use:**
- When the range of input values is not significantly larger than the number of elements
- Integer or character data with a limited range
- As a subroutine in radix sort

**Time Complexity:** O(n + k) where k is the range of input
**Space Complexity:** O(n + k)

#### 📚 Example 1: Easy Problem - Sort Small Positive Integers
**Problem:**
Given an array of small positive integers (range 0 to 9), sort it in ascending order using counting sort.

**Input:** [1,0,2,0,2,1]  
**Expected Output:** [0,0,1,1,2,2]

##### 🔑 **Solution Steps**
1. Count occurrences of each integer.
2. Construct the sorted array from the counts.

##### ✅ **Code:**
```java
public static void countingSortEasy(int[] arr) {
    // ...existing code of countingSort logic...
}
```

#### 📚 Example 2: Medium Problem - Sort Slightly Larger Range
**Problem:**
Given an array of integers in the range [0..1000], use counting sort to sort them in ascending order.

**Input:** [100, 0, 500, 500, 1000, 1]  
**Expected Output:** [0,1,100,500,500,1000]

##### 🔑 **Solution Steps**
1. Create a count array up to the max value (1000).
2. Fill counts, then write back sorted values.

##### ✅ **Code:**
```java
public static void countingSortMedium(int[] arr) {
    // ...existing code of countingSort logic...
}
```

#### 📚 Example 3: Hard Problem - Sort Multiple Buckets
**Problem:**
Given multiple sub-arrays that form a large array of integers in range [0..5000], sort them all at once by combining counts and reconstructing.

**Input Sub-Arrays:** [[1, 3, 3], [10, 0], [5000, 3]]  
**Combined:** [1,3,3,10,0,5000,3]  
**Expected Output:** [0,1,3,3,3,10,5000]

##### 🔑 **Solution Steps**
1. Merge sub-arrays into one large array.
2. Perform standard counting sort across the entire range (0..5000).

##### ✅ **Code:**
```java
public static void countingSortHard(List<int[]> chunks) {
    // ...combine sub-arrays...
    // ...existing code of countingSort logic...
}
```

### 8. Radix Sort

**How It Works:**
Sorts the elements by processing individual digits, starting from the least significant digit to the most significant digit.

```java
public static void radixSort(int[] arr) {
    // Find the maximum number to know the number of digits
    int max = Arrays.stream(arr).max().getAsInt();
    
    // Do counting sort for every digit
    for (int exp = 1; max / exp > 0; exp *= 10) {
        countingSortByDigit(arr, exp);
    }
}

private static void countingSortByDigit(int[] arr, int exp) {
    int n = arr.length;
    int[] output = new int[n];
    int[] count = new int[10]; // 0-9 digits
    
    // Store count of occurrences in count[]
    for (int i = 0; i < n; i++) {
        count[(arr[i] / exp) % 10]++;
    }
    
    // Change count[i] so that it contains actual position of this digit in output[]
    for (int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }
    
    // Build the output array
    for (int i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }
    
    // Copy the output array to arr[]
    for (int i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}
```

**When to Use:**
- When sorting integers or strings with fixed-length
- When the range of values is large but the number of digits is small
- For data with a uniform distribution

**Time Complexity:** O(d * (n + k)) where d is the number of digits and k is the range of a digit (usually 10)
**Space Complexity:** O(n + k)

#### 📚 Example 1: Easy Problem - Sort Small 2-Digit Integers
**Problem:**
Given an array of small integers (range [0..99]), sort them using Radix Sort.

**Input:** [10, 1, 3, 99, 23]  
**Expected Output:** [1, 3, 10, 23, 99]

##### 🔑 **Solution Steps**
1. Apply Radix Sort digit by digit, starting from the least significant digit.
2. Use counting sort as a subroutine for each digit.

##### ✅ **Code:**
```java
public static void radixSortEasy(int[] arr) {
    // ...existing code of the Radix Sort logic...
}
```

#### 📚 Example 2: Medium Problem - Sort Up to 5-Digit Integers
**Problem:**
Given an array of integers (up to 5 digits), sort them in ascending order using Radix Sort.

**Input:** [100, 4500, 9999, 2, 50]  
**Expected Output:** [2, 50, 100, 4500, 9999]

##### 🔑 **Solution Steps**
1. Determine the largest value to know how many digits to sort through.
2. Sort by each digit, from least significant to most significant.

##### ✅ **Code:**
```java
public static void radixSortMedium(int[] arr) {
    // ...existing code of the Radix Sort logic...
}
```

#### 📚 Example 3: Hard Problem - Sort Chunks of Large Integers
**Problem:**
Given multiple chunks of large integers (up to 7 digits), merge them and apply Radix Sort to get one sorted array.

**Input Sub-Arrays:** [[1234567, 9999999], [42], [10000, 500000]]  
**Combined:** [1234567,9999999,42,10000,500000]  
**Expected Output:** [42,10000,500000,1234567,9999999]

##### 🔑 **Solution Steps**
1. Combine sub-arrays into a single array.
2. Run the Radix Sort across all digits.

##### ✅ **Code:**
```java
public static void radixSortHard(List<int[]> chunks) {
    // ...combine sub-arrays into one...
    // ...existing code of the Radix Sort logic...
}
```

### 9. Bucket Sort

**How It Works:**
Divides the range into equal-sized buckets, distributes the elements into the buckets, sorts each bucket, and then concatenates them.

```java
public static void bucketSort(double[] arr) {
    int n = arr.length;
    if (n <= 0) return;
    
    // Create empty buckets
    @SuppressWarnings("unchecked")
    List<Double>[] buckets = new ArrayList[n];
    for (int i = 0; i < n; i++) {
        buckets[i] = new ArrayList<>();
    }
    
    // Add elements into the buckets
    for (int i = 0; i < n; i++) {
        int bucketIndex = (int) (n * arr[i]);
        buckets[bucketIndex].add(arr[i]);
    }
    
    // Sort the elements of each bucket
    for (int i = 0; i < n; i++) {
        Collections.sort(buckets[i]);
    }
    
    // Get the sorted array
    int index = 0;
    for (int i = 0; i < n; i++) {
        for (double num : buckets[i]) {
            arr[index++] = num;
        }
    }
}
```

**When to Use:**
- When input is uniformly distributed over a range
- Floating-point numbers between 0 and 1
- When combined with other sorting algorithms for the individual buckets

**Time Complexity:** O(n + k) average case, O(n²) worst case
**Space Complexity:** O(n + k)

#### 📚 Example 1: Easy Problem - Sort an Array of Floats [0..1]
**Problem:**
Given an array of floating-point numbers in [0..1], sort them using Bucket Sort.

**Input:** [0.25, 0.05, 0.75, 0.30]  
**Expected Output:** [0.05, 0.25, 0.30, 0.75]

##### 🔑 **Solution Steps**
1. Create buckets for each element based on its value * length of array.
2. Sort each bucket, then concatenate.

##### ✅ **Code:**
```java
public static void bucketSortEasy(float[] arr) {
    // ...existing code of the Bucket Sort logic...
}
```

#### 📚 Example 2: Medium Problem - Sort Decimals with Varied Precision
**Problem:**
Given an array of decimals in [0..1] with varying precision, place them in buckets and sort them in ascending order.

**Input:** [0.123, 0.9, 0.0223, 0.567]  
**Expected Output:** [0.0223, 0.123, 0.567, 0.9]

##### 🔑 **Solution Steps**
1. Determine bucket index by multiplying each value by array size.
2. Perform an internal sort on each bucket and merge.

##### ✅ **Code:**
```java
public static void bucketSortMedium(double[] arr) {
    // ...existing code of the Bucket Sort logic...
}
```

#### 📚 Example 3: Hard Problem - Merge and Sort Multiple Float Arrays
**Problem:**
Given several small arrays of float values, combine them into one large array and apply Bucket Sort overall.

**Input Sub-Arrays:** [[0.1, 0.2], [0.25], [0.78, 0.01]]  
**Combined:** [0.1,0.2,0.25,0.78,0.01]  
**Expected Output:** [0.01,0.1,0.2,0.25,0.78]

##### 🔑 **Solution Steps**
1. Combine all arrays into one.
2. Run the standard Bucket Sort flow on this merged array.

##### ✅ **Code:**
```java
public static void bucketSortHard(List<double[]> chunks) {
    // ...combine sub-arrays...
    // ...existing code of the Bucket Sort logic...
}
```

### 10. Tim Sort

**How It Works:**
A hybrid sorting algorithm derived from merge sort and insertion sort, designed to perform well on real-world data.

```java
// Tim Sort is complex to implement from scratch
// For Java, it's the standard Arrays.sort() and Collections.sort() for objects
// Below is a simplified version

public static void timSort(int[] arr) {
    int minRun = calculateMinRun(arr.length);
    
    // Sort individual subarrays of size minRun
    for (int i = 0; i < arr.length; i += minRun) {
        insertionSort(arr, i, Math.min(i + minRun - 1, arr.length - 1));
    }
    
    // Start merging from size minRun
    for (int size = minRun; size < arr.length; size = 2 * size) {
        for (int left = 0; left < arr.length; left += 2 * size) {
            int mid = left + size - 1;
            int right = Math.min(left + 2 * size - 1, arr.length - 1);
            
            if (mid < right) {
                merge(arr, left, mid, right);
            }
        }
    }
}

private static int calculateMinRun(int n) {
    int r = 0;
    while (n >= 64) {
        r |= n & 1;
        n >>= 1;
    }
    return n + r;
}

// Using our existing merge and insertionSort methods
private static void insertionSort(int[] arr, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        int key = arr[i];
        int j = i - 1;
        
        while (j >= left && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
```

**When to Use:**
- Production environments
- Real-world data with various patterns
- When stability is required

**Time Complexity:** O(n log n) average and worst case
**Space Complexity:** O(n)

#### 📚 Example 1: Easy Problem - Sort a Small Nearly Sorted Array

**Problem:**
Given a small array that is already almost sorted, finish sorting it using Tim Sort.

**Input:** [2, 1, 4, 3, 5]  
**Expected Output:** [1, 2, 3, 4, 5]

##### 🔑 **Solution Steps**
1. Set appropriate minRun.
2. Sort each chunk with insertion sort.
3. Merge sorted chunks.

##### ✅ **Code:**
```java
public static void timSortEasy(int[] arr) {
    // ...existing code of the Tim Sort logic...
}
```

#### 📚 Example 2: Medium Problem - Sort a Large Partially Sorted List

**Problem:**
You have a larger array with multiple sorted segments. Use Tim Sort to combine these segments efficiently.

**Input:** [10,12,13,15,1,2,3,7,20,22,30,31]  
**Expected Output:** [1,2,3,7,10,12,13,15,20,22,30,31]

##### 🔑 **Solution Steps**
1. Identify the runs (sorted segments).
2. Perform insertion sort on smaller runs.
3. Merge them in powers of two.

##### ✅ **Code:**
```java
public static void timSortMedium(int[] arr) {
    // ...existing code of the Tim Sort logic...
}
```

#### 📚 Example 3: Hard Problem - Sort an Array with Numerous Small Runs

**Problem:**
A large array has many short runs of data that are nearly sorted but interspersed. Tim Sort should handle them efficiently.

**Input:** [5,6,7,8,1,2,3,4,15,16,11,12,13,14]  
**Expected Output:** [1,2,3,4,5,6,7,8,11,12,13,14,15,16]

##### 🔑 **Solution Steps**
1. Scan the array to locate each run.
2. Use insertion sort on small runs.
3. Merge runs until fully sorted.

##### ✅ **Code:**
```java
public static void timSortHard(int[] arr) {
    // ...existing code of the Tim Sort logic...
}
```

## 📚 B. Key Searching Algorithms

### 1. Linear Search

**How It Works:**
Sequentially checks each element of the array until a match is found or the end of the array is reached.

```java
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i; // Return the index where target was found
        }
    }
    return -1; // Target not found
}
```

**When to Use:**
- Small datasets
- Unsorted arrays
- When simplicity is more important than efficiency
- When the target is likely to be near the beginning

**Time Complexity:** O(n) worst case
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Find First Occurrence
**Problem:**
Find the index of the first occurrence of target in a small unsorted array.

**Input:** [4,2,1], target=2  
**Expected Output:** 1  

##### 🔑 **Solution Steps**
1. Traverse the array from the start.
2. Stop when the target is found.

##### ✅ **Code:**
```java
public static int linearSearchEasy(int[] arr, int target) {
    // For simplicity, reuse the existing linearSearch() logic:
    return linearSearch(arr, target);
}
```

#### 📚 Example 2: Medium Problem - Find a Missing Number
**Problem:**
Given an unsorted list of consecutive numbers from 1..n where one is missing, find the missing number.

**Input:** [1,2,4,5], missing=3  
**Expected Output:** 3  

##### ✅ **Code:**
```java
public static int linearSearchMedium(int[] arr, int n) {
    // Find missing number from 1..n
    int sumExpected = n * (n + 1) / 2;
    int sumActual = 0;
    for (int val : arr) {
        sumActual += val;
    }
    return sumExpected - sumActual;
}
```

#### 📚 Example 3: Hard Problem - Duplicate within K Distance
**Problem:**
Check if an unsorted array has any duplicates within k indices of each other.

**Input:** [1,2,3,1,4,5], k=3  
**Expected Output:** true  

##### ✅ **Code:**
```java
public static boolean linearSearchHard(int[] arr, int k) {
    // Check duplicates within k distance
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
        if (set.contains(arr[i])) {
            return true;
        }
        set.add(arr[i]);
        if (set.size() > k) {
            set.remove(arr[i - k]);
        }
    }
    return false;
}
```

### 2. Binary Search

**How It Works:**
Divides the sorted array in half repeatedly, comparing the target with the middle element.

```java
public static int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        // Check if target is present at mid
        if (arr[mid] == target) {
            return mid;
        }
        
        // If target is greater, ignore left half
        if (arr[mid] < target) {
            left = mid + 1;
        }
        // If target is smaller, ignore right half
        else {
            right = mid - 1;
        }
    }
    
    // Element not present
    return -1;
}
```

**When to Use:**
- Sorted arrays
- Large datasets
- When efficient searching is required

**Time Complexity:** O(log n)
**Space Complexity:** O(1) for iterative, O(log n) for recursive

#### 📚 Example 1: Easy Problem - Find Target
**Problem:**
Search a sorted array for a target that surely exists.

**Input:** [1,2,3,4], target=3  
**Expected Output:** 2  

##### ✅ **Code:**
```java
public static int binarySearchEasy(int[] arr, int target) {
    // Reuse the existing binarySearch() code:
    return binarySearch(arr, target);
}
```

#### 📚 Example 2: Medium Problem - Find Insert Position
**Problem:**
Return the index where a target should be inserted to maintain order if not found.

**Input:** [1,3,5,6], target=2  
**Expected Output:** 1  

##### ✅ **Code:**
```java
public static int binarySearchMedium(int[] arr, int target) {
    // Return insert position if not found
    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return left;
}
```

#### 📚 Example 3: Hard Problem - Find Peak Element
**Problem:**
Given a bitonic array (first increasing then decreasing), find a peak's index.

**Input:** [1,2,3,5,4,2], peak=5  
**Expected Output:** 3  

##### ✅ **Code:**
```java
public static int binarySearchHard(int[] arr) {
    // Find peak in bitonic array
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int mid = (left + right) / 2;
        if (arr[mid] < arr[mid + 1]) left = mid + 1;
        else right = mid;
    }
    return left;
}
```

### 3. Jump Search

**How It Works:**
Jumps ahead by fixed steps and then performs a linear search after finding the block where the target may be present.

```java
public static int jumpSearch(int[] arr, int target) {
    int n = arr.length;
    int step = (int) Math.floor(Math.sqrt(n));
    int prev = 0;
    
    // Finding the block where the element is present (if it is)
    while (arr[Math.min(step, n) - 1] < target) {
        prev = step;
        step += (int) Math.floor(Math.sqrt(n));
        if (prev >= n) {
            return -1; // Element not present
        }
    }
    
    // Linear search for target in the block beginning with prev
    while (arr[prev] < target) {
        prev++;
        
        // If we reach next block or end of array, element is not present
        if (prev == Math.min(step, n)) {
            return -1;
        }
    }
    
    // If element is found
    if (arr[prev] == target) {
        return prev;
    }
    
    return -1; // Element not present
}
```

**When to Use:**
- Sorted arrays
- When binary search might be overkill but linear search is too slow
- When jumping ahead and then checking linearly is more efficient

**Time Complexity:** O(√n)
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Limited Range
**Problem:**
Search a small sorted list with jump size 2.

**Input:** [1,2,3,4], target=3  
**Expected Output:** 2  

##### ✅ **Code:**
```java
public static int jumpSearchEasy(int[] arr, int target) {
    // Reuse the existing jumpSearch() code:
    return jumpSearch(arr, target);
}
```

#### 📚 Example 2: Medium Problem - Searching with Variable Steps
**Problem:**
For a moderately large list, adjust jump sizes dynamically.

##### ✅ **Code:**
```java
public static int jumpSearchMedium(int[] arr, int target) {
    // Same approach but allow dynamic step logic:
    // ...existing code or minor changes to step size...
    return jumpSearch(arr, target);
}
```

#### 📚 Example 3: Hard Problem - Sparse Array with Gaps
**Problem:**
Find target in a heavily gapped sorted list.

##### ✅ **Code:**
```java
public static int jumpSearchHard(int[] arr, int target) {
    // Reuse jumpSearch approach for sparse arrays:
    return jumpSearch(arr, target);
}
```

### 4. Interpolation Search

**How It Works:**
Improves upon binary search by calculating the probable position of the target based on its value and the values at the ends of the array.

```java
public static int interpolationSearch(int[] arr, int target) {
    int low = 0;
    int high = arr.length - 1;
    
    while (low <= high && target >= arr[low] && target <= arr[high]) {
        if (low == high) {
            if (arr[low] == target) return low;
            return -1;
        }
        
        // Formula for probing position
        int pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);
        
        if (arr[pos] == target) {
            return pos;
        }
        
        if (arr[pos] < target) {
            low = pos + 1;
        } else {
            high = pos - 1;
        }
    }
    
    return -1; // Element not present
}
```

**When to Use:**
- Sorted arrays with uniformly distributed values
- When you expect significant performance gains over binary search
- For large arrays with uniform distribution

**Time Complexity:** O(log log n) average case, O(n) worst case
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Uniformly Distributed Small Range
**Problem:**
Search in a small array of evenly spaced numbers.

**Input:** [10,20,30,40], target=30  
**Expected Output:** 2  

##### ✅ **Code:**
```java
public static int interpolationSearchEasy(int[] arr, int target) {
    // Reuse the existing interpolationSearch() code:
    return interpolationSearch(arr, target);
}
```

#### 📚 Example 2: Medium Problem - Missing Value in Distribution
**Problem:**
Find a missing value in an almost uniform distribution.

##### ✅ **Code:**
```java
public static int interpolationSearchMedium(int[] arr, int target) {
    // ...existing code or slight tweak...
    return interpolationSearch(arr, target);
}
```

#### 📚 Example 3: Hard Problem - Large Array with Uniform Distribution
**Problem:**
When array is huge and uniformly distributed, find target quickly.

##### ✅ **Code:**
```java
public static int interpolationSearchHard(int[] arr, int target) {
    // ...existing code for large arrays...
    return interpolationSearch(arr, target);
}
```

### 5. Exponential Search

**How It Works:**
First finds a range where the target may be present by repeatedly doubling the index, then performs a binary search within that range.

```java
public static int exponentialSearch(int[] arr, int target) {
    int n = arr.length;
    
    // If target is the first element
    if (arr[0] == target) {
        return 0;
    }
    
    // Find range for binary search by repeated doubling
    int i = 1;
    while (i < n && arr[i] <= target) {
        i = i * 2;
    }
    
    // Perform binary search on the found range
    return binarySearchRange(arr, target, i / 2, Math.min(i, n - 1));
}

private static int binarySearchRange(int[] arr, int target, int left, int right) {
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        }
        
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1; // Element not present
}
```

**When to Use:**
- Sorted unbounded arrays (when you don't know the size in advance)
- When the target is likely to be nearby (at the beginning)
- Efficient for both small and large datasets

**Time Complexity:** O(log n)
**Space Complexity:** O(1)

#### 📚 Example 1: Easy Problem - Find First Element
**Problem:**
Search for an element known to be near the start.

**Input:** [3,4,5,6], target=3  
**Expected Output:** 0  

##### ✅ **Code:**
```java
public static int exponentialSearchEasy(int[] arr, int target) {
    return exponentialSearch(arr, target);
}
```

#### 📚 Example 2: Medium Problem - Roughly Known Position
**Problem:**
When the approximate index of the target is known, narrow down via exponential range.

##### ✅ **Code:**
```java
public static int exponentialSearchMedium(int[] arr, int target) {
    // ...existing code or minor modifications...
    return exponentialSearch(arr, target);
}
```

#### 📚 Example 3: Hard Problem - Large Unbounded Array
**Problem:**
Find target in a large, unbounded sorted array or stream.

##### ✅ **Code:**
```java
public static int exponentialSearchHard(int[] arr, int target) {
    // ...existing code for large/unbounded arrays...
    return exponentialSearch(arr, target);
}
```
