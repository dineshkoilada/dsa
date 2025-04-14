# Data Structures and Algorithms for FAANG Interviews

## 🎯 Introduction

Data Structures and Algorithms (DSA) form the foundation of technical interviews at FAANG companies. This guide focuses on the most frequently tested DSA concepts that appear in coding interviews at top tech companies.

---

## 🔍 Arrays and Strings (Most Common in Interviews)

Arrays and strings are the most common data structures in coding interviews, appearing in approximately 40% of questions.

### 📌 Array Techniques
```java
public class ArrayTechniques {
    /**
     * Two-pointer technique (often used in sorted arrays)
     * Time Complexity: O(n) where n is the number of elements in the array
     * Space Complexity: O(1)
     *
     * @param nums A sorted array of integers
     * @param target The target sum to find
     * @return An array containing indices of the two numbers that add up to target
     */
    public static int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum == target) {
                return new int[] { left, right };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[] { -1, -1 };
    }
    
    /**
     * Sliding window technique - Find maximum sum subarray of fixed size k
     * Time Complexity: O(n) where n is the number of elements in the array
     * Space Complexity: O(1)
     *
     * @param nums An array of integers
     * @param k The size of the sliding window
     * @return The maximum sum of any subarray of size k
     */
    public static int maxSubarraySum(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k]; // Add next, remove first
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    /**
     * Prefix Sum technique (for range queries)
     * Time Complexity: O(n) for building, O(1) for queries
     * Space Complexity: O(n)
     *
     * @param nums An array of integers
     * @return An array where each element is the sum of all elements before it
     */
    public static int[] buildPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        return prefixSum;
    }
    
    /**
     * Query sum of elements in a range using prefix sum
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param prefixSum Precomputed prefix sum array
     * @param left Starting index (inclusive)
     * @param right Ending index (inclusive)
     * @return The sum of elements from index left to right
     */
    public static int rangeSum(int[] prefixSum, int left, int right) {
        if (left == 0) {
            return prefixSum[right];
        }
        return prefixSum[right] - prefixSum[left - 1];
    }
    
    /**
     * Binary search in sorted array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param nums A sorted array of integers
     * @param target The value to search for
     * @return The index of target if found, -1 otherwise
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevents overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Find maximum subarray sum using Kadane's algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums An array of integers
     * @return The maximum subarray sum
     */
    public static int kadaneMaxSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either start a new subarray or extend the existing one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update the maximum sum found so far
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * Dutch National Flag algorithm for sorting array with three distinct values
     * Common application: Sort an array of 0s, 1s, and 2s
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums An array containing only values 0, 1, and 2
     */
    public static void dutchNationalFlag(int[] nums) {
        int low = 0;        // For elements with value 0
        int mid = 0;        // For elements with value 1
        int high = nums.length - 1;   // For elements with value 2
        
        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    // Swap with the low pointer
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    // No need to swap, just move the mid pointer
                    mid++;
                    break;
                case 2:
                    // Swap with the high pointer
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }
    
    /**
     * Helper method to swap elements in an array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 📌 String Manipulation Techniques
```java
public class StringTechniques {
    /**
     * Check if a string is a palindrome
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(1)
     *
     * @param s Input string to check
     * @return True if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Count the frequency of each character in a string
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(k) where k is the number of unique characters
     *
     * @param s Input string
     * @return A map with characters as keys and their frequencies as values
     */
    public static Map<Character, Integer> getCharFrequency(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        return freqMap;
    }
    
    /**
     * Check if two strings are anagrams of each other
     * Time Complexity: O(n) where n is the length of the strings
     * Space Complexity: O(1) since we use a fixed size array
     *
     * @param s1 First string
     * @param s2 Second string
     * @return True if the strings are anagrams, false otherwise
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] count = new int[26]; // Assuming lowercase letters only
        
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Reverse words in a string
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(n)
     *
     * @param s Input string with words separated by spaces
     * @return String with words reversed
     */
    public static String reverseWords(String s) {
        // Split the string by spaces
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        // Append words in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Find the longest substring without repeating characters
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(min(m, n)) where m is the size of the character set
     *
     * @param s Input string
     * @return Length of the longest substring without repeating characters
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        
        // Sliding window approach
        for (int start = 0, end = 0; end < n; end++) {
            char currentChar = s.charAt(end);
            
            // If character is already in current window, move start pointer
            if (charMap.containsKey(currentChar)) {
                start = Math.max(start, charMap.get(currentChar) + 1);
            }
            
            // Update maximum length
            maxLength = Math.max(maxLength, end - start + 1);
            
            // Store the index of the character
            charMap.put(currentChar, end);
        }
        
        return maxLength;
    }
    
    /**
     * Convert Roman numeral to integer
     * Time Complexity: O(n) where n is the length of the input string
     * Space Complexity: O(1)
     *
     * @param s Roman numeral string
     * @return Integer value
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int result = 0;
        int prevValue = 0;
        
        // Traverse from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(s.charAt(i));
            
            // If current value is greater or equal to previous, add it
            // Otherwise subtract it (for cases like IV, IX, etc.)
            if (currentValue >= prevValue) {
                result += currentValue;
            } else {
                result -= currentValue;
            }
            
            prevValue = currentValue;
        }
        
        return result;
    }
    
    /**
     * Longest common prefix among an array of strings
     * Time Complexity: O(S) where S is the sum of all characters in all strings
     * Space Complexity: O(1)
     *
     * @param strs Array of strings
     * @return The longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Take first string as reference
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            // Find the common prefix between prefix and current string
            while (strs[i].indexOf(prefix) != 0) {
                // Reduce prefix by one character from end
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If prefix becomes empty, return empty string
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
}
```

---

## 🔄 Linked Lists (15-20% of Interview Questions)

Linked list problems test your understanding of pointers and node manipulation.

### 📌 Basic Operations and Common Patterns
```java
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedListOperations {
    /**
     * Reverse a linked list (very common interview question)
     * Time Complexity: O(n) where n is the length of the list
     * Space Complexity: O(1)
     *
     * @param head Head node of the linked list
     * @return Head of the reversed linked list
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }
    
    /**
     * Detect cycle using Floyd's Tortoise and Hare algorithm
     * Time Complexity: O(n) where n is the length of the list
     * Space Complexity: O(1)
     *
     * @param head Head node of the linked list
     * @return True if a cycle exists, false otherwise
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move one step
            fast = fast.next.next;     // Move two steps
            
            if (slow == fast) {        // Cycle detected
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Find the starting point of a cycle in a linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head Head node of the linked list
     * @return The node where the cycle begins, or null if no cycle exists
     */
    public static ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Detect if there is a cycle
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        // No cycle
        if (!hasCycle) {
            return null;
        }
        
        // Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * Find middle of linked list
     * Time Complexity: O(n) where n is the length of the list
     * Space Complexity: O(1)
     *
     * @param head Head node of the linked list
     * @return The middle node (if odd length) or the second middle node (if even length)
     */
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    /**
     * Merge two sorted lists
     * Time Complexity: O(n+m) where n and m are the lengths of the two lists
     * Space Complexity: O(1)
     *
     * @param l1 Head of the first sorted list
     * @param l2 Head of the second sorted list
     * @return Head of the merged sorted list
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        // Attach remaining nodes
        current.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
    
    /**
     * Remove the nth node from the end of the list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head Head of the linked list
     * @param n Position from the end (1-indexed) to remove
     * @return New head of the linked list after removal
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Advance first pointer by n+1 steps
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove the nth node from the end
        second.next = second.next.next;
        
        return dummy.next;
    }
    
    /**
     * Check if a linked list is a palindrome
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head Head of the linked list
     * @return True if the linked list is a palindrome, false otherwise
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse the second half
        ListNode secondHalfHead = reverseList(slow.next);
        
        // Compare first and second half nodes
        ListNode p1 = head;
        ListNode p2 = secondHalfHead;
        
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // Restore the list (optional)
        slow.next = reverseList(secondHalfHead);
        
        return result;
    }
    
    /**
     * Reorder list: L0→L1→...→Ln-1→Ln to L0→Ln→L1→Ln-1→L2→Ln-2→...
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head Head of the linked list
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse the second half
        ListNode secondHalfHead = reverseList(slow.next);
        slow.next = null; // Split the list
        
        // Merge the two halves
        ListNode p1 = head;
        ListNode p2 = secondHalfHead;
        
        while (p2 != null) {
            ListNode tempNext1 = p1.next;
            ListNode tempNext2 = p2.next;
            
            p1.next = p2;
            p2.next = tempNext1;
            
            p1 = tempNext1;
            p2 = tempNext2;
        }
    }
}
```

---

## 🌲 Trees and Graphs (20-25% of Interview Questions)

Tree and graph problems are very common in FAANG interviews, especially at Google and Facebook.

### 📌 Binary Tree Traversals
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeTraversals {
    /**
     * Inorder traversal (Left -> Root -> Right)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return List of values in inorder traversal order
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }
    
    private static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }
    
    /**
     * Preorder traversal (Root -> Left -> Right)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return List of values in preorder traversal order
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }
    
    private static void preorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }
    
    /**
     * Postorder traversal (Left -> Right -> Root)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return List of values in postorder traversal order
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }
    
    private static void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }
    
    /**
     * Level order traversal (BFS)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(w) where w is the maximum width of the tree
     *
     * @param root Root of the binary tree
     * @return List of lists, each inner list contains values at one level
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
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
    
    /**
     * Iterative inorder traversal using stack
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return List of values in inorder traversal order
     */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Visit the top node in the stack
            current = stack.pop();
            result.add(current.val);
            
            // Move to the right child
            current = current.right;
        }
        
        return result;
    }
    
    /**
     * Maximum depth (height) of a binary tree
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return The maximum depth of the tree
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * Check if a binary tree is symmetric (mirror of itself)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return True if the tree is symmetric, false otherwise
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isMirror(root.left, root.right);
    }
    
    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        
        if (t1 == null || t2 == null) {
            return false;
        }
        
        return (t1.val == t2.val) &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }
}
```

### 📌 Binary Search Tree Operations
```java
public class BSTOperations {
    /**
     * Insert a value into a Binary Search Tree
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) due to recursion stack
     *
     * @param root Root node of the BST
     * @param val Value to insert
     * @return Root of the modified BST
     */
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    
    /**
     * Search for a value in a Binary Search Tree
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) due to recursion stack
     *
     * @param root Root node of the BST
     * @param val Value to search for
     * @return Node containing the value, or null if not found
     */
    public static TreeNode search(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        
        if (val < root.val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }
    
    /**
     * Check if a binary tree is a valid Binary Search Tree
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     *
     * @param root Root of the binary tree
     * @return True if the tree is a valid BST, false otherwise
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    
    private static boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        
        if ((lower != null && node.val <= lower) || 
            (upper != null && node.val >= upper)) {
            return false;
        }
        
        return isValidBST(node.left, lower, node.val) && 
               isValidBST(node.right, node.val, upper);
    }
    
    /**
     * Delete a node with the given value from a BST
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) due to recursion stack
     *
     * @param root Root of the BST
     * @param val Value to delete
     * @return Root of the modified BST
     */
    public static TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        
        // Find the node to delete
        if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else {
            // Case 1: Leaf node (no children)
            if (root.left == null && root.right == null) {
                return null;
            }
            
            // Case 2: Node with only one child
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            
            // Case 3: Node with two children
            // Find the inorder successor (smallest node in right subtree)
            root.val = findMinValue(root.right);
            
            // Delete the inorder successor
            root.right = deleteNode(root.right, root.val);
        }
        
        return root;
    }
    
    /**
     * Find the minimum value in a BST
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) due to recursion stack
     *
     * @param root Root of the BST
     * @return The minimum value in the BST
     */
    private static int findMinValue(TreeNode root) {
        int minValue = root.val;
        while (root.left != null) {
            minValue = root.left.val;
            root = root.left;
        }
        return minValue;
    }
    
    /**
     * Find the kth smallest element in a BST
     * Time Complexity: O(h + k) where h is the height of the tree
     * Space Complexity: O(h) due to recursion stack
     *
     * @param root Root of the BST
     * @param k The value of k
     * @return The kth smallest element in the BST
     */
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>();
        inorder(root, inorderList);
        return inorderList.get(k - 1);
    }
    
    private static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }
    
    /**
     * Convert a sorted array to a balanced BST
     * Time Complexity: O(n) where n is the number of elements in the array
     * Space Complexity: O(log n) due to recursion stack
     *
     * @param nums Sorted array
     * @return Root of the balanced BST
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructBST(nums, 0, nums.length - 1);
    }
    
    private static TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        // Always choose the middle element as root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        // Recursively construct left and right subtrees
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);
        
        return root;
    }
}
```

### 📌 Graph Traversal Algorithms
```java
public class GraphAlgorithms {
    // Depth-First Search (DFS)
    public static void dfs(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }
    
    // Breadth-First Search (BFS)
    public static void bfs(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
    
    // Dijkstra's Algorithm for shortest path
    public static int[] dijkstra(List<List<int[]>> graph, int start) {
        int n = graph.size();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];
            
            if (distance > distances[node]) {
                continue;
            }
            
            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];
                
                if (distances[node] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[node] + weight;
                    pq.offer(new int[]{neighbor, distances[neighbor]});
                }
            }
        }
        
        return distances;
    }
}
```

---

## 🔄 Stacks and Queues (10-15% of Interview Questions)

Stacks and queues are fundamental for problems involving parsing, BFS, and maintaining state.

### 📌 Stack Applications
```java
public class StackProblems {
    // Valid Parentheses (very common interview question)
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("+")) {
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    // Knapsack problem (0/1)
    public static int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]], 
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
}
```

---

## 🔄 Recursion and Backtracking (10% of Interview Questions)

Recursion and backtracking are powerful techniques for combinatorial problems.

### 📌 Common Recursion Problems
```java
public class RecursionAndBacktracking {
    // Generate all permutations of an array
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result, new boolean[nums.length]);
        return result;
    }
    
    private static void backtrack(int[] nums, List<Integer> current, 
                                 List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, current, result, used);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
    
    // Generate all subsets
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsets(int[] nums, int index, List<Integer> current,
                                      List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    // N-Queens problem
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        solveNQueens(board, 0, result);
        return result;
    }
    
    private static void solveNQueens(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(constructSolution(board));
            return;
        }
        
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                solveNQueens(board, row + 1, result);
                board[row][col] = '.';
            }
        }
    }
    
    private static boolean isValid(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    private static List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
```

---

## 📚 Key Takeaways for Interviews

1. **Master array and string techniques** - Two pointers, sliding window, prefix sum
2. **Understand tree traversals** - In-order, pre-order, post-order, level-order
3. **Know graph algorithms** - DFS, BFS, shortest path algorithms
4. **Practice linked list manipulations** - Reversing, finding cycles, merging
5. **Implement stack & queue problems** - Parenthesis matching, monotonic stack
6. **Recognize DP patterns** - Memoization, tabulation, common problems
7. **Apply backtracking** - Generate combinations, permutations, solve constraints

---

Up next: Learn about **Time and Space Complexities** to analyze the efficiency of your solutions - a critical aspect of FAANG interviews.

