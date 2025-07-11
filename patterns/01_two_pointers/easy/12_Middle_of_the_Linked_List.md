# Middle of the Linked List (LeetCode #876)

## Problem Statement
Given the `head` of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

## Example 1:
```
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
```

## Example 2:
```
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
```

## Constraints:
- The number of nodes in the list is in the range [1, 100].
- 1 <= Node.val <= 100

## Two Pointers Approach (Fast and Slow)
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
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // When fast reaches the end, slow will be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
```

## Time Complexity
O(n) where n is the number of nodes in the linked list.

## Space Complexity
O(1) as we only use two pointers.