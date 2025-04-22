# Remove Nth Node From End of List (LeetCode #19)

## Problem Statement
Given the `head` of a linked list, remove the `n`th node from the end of the list and return its head.

## Example 1:
```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
```

## Example 2:
```
Input: head = [1], n = 1
Output: []
```

## Example 3:
```
Input: head = [1,2], n = 1
Output: [1]
```

## Constraints:
- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Use two pointers
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Advance first pointer so that the gap between first and second is n nodes apart
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
}
```

## Time Complexity
O(n) where n is the number of nodes in the linked list.

## Space Complexity
O(1) as we only use two pointers.