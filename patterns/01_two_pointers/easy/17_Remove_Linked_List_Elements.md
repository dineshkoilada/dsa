# Remove Linked List Elements (LeetCode #203)

## Problem Statement
Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that has `Node.val == val`, and return the new head.

## Example 1:
```
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

## Example 2:
```
Input: head = [], val = 1
Output: []
```

## Example 3:
```
Input: head = [7,7,7,7], val = 7
Output: []
```

## Constraints:
- The number of nodes in the list is in the range [0, 10^4].
- 1 <= Node.val <= 50
- 0 <= val <= 50

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
    public ListNode removeElements(ListNode head, int val) {
        // Create a dummy head to simplify edge cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // Use two pointers: prev and current
        ListNode prev = dummy;
        ListNode current = head;
        
        while (current != null) {
            if (current.val == val) {
                // Skip the node
                prev.next = current.next;
            } else {
                // Move prev forward
                prev = current;
            }
            // Always move current forward
            current = current.next;
        }
        
        return dummy.next;
    }
}
```

## Time Complexity
O(n) where n is the number of nodes in the linked list.

## Space Complexity
O(1) as we only use pointers and remove nodes in-place.