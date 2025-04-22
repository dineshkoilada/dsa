# Palindrome Linked List (LeetCode #234)

## Problem Statement
Given the `head` of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

## Example 1:
```
Input: head = [1,2,2,1]
Output: true
```

## Example 2:
```
Input: head = [1,2]
Output: false
```

## Constraints:
- The number of nodes in the list is in the range [1, 10^5].
- 0 <= Node.val <= 9

## Two Pointers Approach
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
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
        
        // Reverse the second half of the linked list
        ListNode secondHalf = reverseList(slow.next);
        
        // Compare the first and second half
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean result = true;
        
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // Restore the list (optional)
        slow.next = reverseList(secondHalf);
        
        return result;
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
}
```

## Time Complexity
O(n) where n is the number of nodes in the linked list.

## Space Complexity
O(1) as we only use pointers and modify the list in-place.