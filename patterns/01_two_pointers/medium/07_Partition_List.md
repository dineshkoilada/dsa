# Partition List (LeetCode #86)

## Problem Statement
Given the `head` of a linked list and a value `x`, partition it such that all nodes less than `x` come before nodes greater than or equal to `x`.

You should preserve the original relative order of the nodes in each of the two partitions.

## Example 1:
```
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
```

## Example 2:
```
Input: head = [2,1], x = 2
Output: [1,2]
```

## Constraints:
- The number of nodes in the list is in the range [0, 200].
- -100 <= Node.val <= 100
- -200 <= x <= 200

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
    public ListNode partition(ListNode head, int x) {
        // Create two dummy nodes for the two partitions
        ListNode beforeDummy = new ListNode(0);
        ListNode afterDummy = new ListNode(0);
        
        // Pointers to track the ends of both partitions
        ListNode before = beforeDummy;
        ListNode after = afterDummy;
        
        // Traverse the original list
        while (head != null) {
            if (head.val < x) {
                // Add to the "before" partition
                before.next = head;
                before = before.next;
            } else {
                // Add to the "after" partition
                after.next = head;
                after = after.next;
            }
            
            // Move to the next node
            head = head.next;
        }
        
        // Terminate the "after" partition
        after.next = null;
        
        // Connect the two partitions
        before.next = afterDummy.next;
        
        return beforeDummy.next;
    }
}
```

## Time Complexity
O(n) where n is the number of nodes in the linked list.

## Space Complexity
O(1) as we only use a constant number of pointers and rearrange the existing nodes.