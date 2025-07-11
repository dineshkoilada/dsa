# Count Number of Nice Subarrays

## Problem Statement

Given an array of integers `nums` and an integer `k`, a subarray is called **nice** if there are exactly `k` odd numbers in it.

Return the number of nice subarrays.

## Examples

**Example 1:**
```
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only nice subarrays are [1,1,2,1] and [1,2,1,1].
```

**Example 2:**
```
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd number in the array.
```

**Example 3:**
```
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
```

## Approach: Sliding Window with Prefix Sum

This problem can be solved using a sliding window approach or by using a prefix sum technique. Here we'll demonstrate both approaches.

### Algorithm (Sliding Window with At Most Technique):

Similar to the "Subarrays with K Different Integers" problem, we can solve this by calculating:
- Number of subarrays with at most `k` odd numbers
- Number of subarrays with at most `k-1` odd numbers
- The difference gives us the number of subarrays with exactly `k` odd numbers

1. Create a function to count subarrays with at most `k` odd numbers
2. Return `atMostK(nums, k) - atMostK(nums, k-1)`

## Code Solution (Sliding Window)

```java
public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    private int atMostK(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int oddCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // If current number is odd, increase odd count
            if (nums[right] % 2 == 1) {
                oddCount++;
            }
            
            // Shrink window while we have more than k odd numbers
            while (oddCount > k) {
                if (nums[left] % 2 == 1) {
                    oddCount--;
                }
                left++;
            }
            
            // Add count of valid subarrays ending at right
            count += right - left + 1;
        }
        
        return count;
    }
}
```

## Alternative Approach: Prefix Sum with HashMap

Another way to approach this problem is to use a prefix sum technique with a HashMap to track the number of odd numbers seen so far:

```java
import java.util.*;

public class CountNumberOfNiceSubarraysHashMap {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int oddCount = 0;
        Map<Integer, Integer> prefixCount = new HashMap<>();
        
        // Initialize with 0 odd numbers seen with frequency 1
        prefixCount.put(0, 1);
        
        for (int num : nums) {
            // Increment oddCount if current number is odd
            if (num % 2 == 1) {
                oddCount++;
            }
            
            // If we've seen (oddCount - k) before, it means there's a subarray with k odd numbers
            count += prefixCount.getOrDefault(oddCount - k, 0);
            
            // Update frequency of current oddCount
            prefixCount.put(oddCount, prefixCount.getOrDefault(oddCount, 0) + 1);
        }
        
        return count;
    }
}
```

## Optimization: Using an Array Instead of HashMap

Since we're only counting odd numbers, and the count can only increase by at most the length of the array, we can use an array instead of a HashMap for better performance:

```java
public class CountNumberOfNiceSubarraysOptimized {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int oddCount = 0;
        int[] prefixCount = new int[nums.length + 1];
        
        // Initialize with 0 odd numbers seen with frequency 1
        prefixCount[0] = 1;
        
        for (int num : nums) {
            // Increment oddCount if current number is odd
            if (num % 2 == 1) {
                oddCount++;
            }
            
            // If we've seen (oddCount - k) before, it means there's a subarray with k odd numbers
            if (oddCount >= k) {
                count += prefixCount[oddCount - k];
            }
            
            // Update frequency of current oddCount
            prefixCount[oddCount]++;
        }
        
        return count;
    }
}
```

## Complexity Analysis

- **Sliding Window Approach**:
  - **Time Complexity**: O(n) where n is the length of the array. We call the `atMostK` function twice, and each call processes the array once.
  - **Space Complexity**: O(1) as we only use a constant amount of extra space.

- **Prefix Sum Approach**:
  - **Time Complexity**: O(n) where n is the length of the array.
  - **Space Complexity**: O(n) for the HashMap or array to store prefix counts.

## Key Insights

1. This problem demonstrates how sliding window can be combined with parity tracking to count subarrays with specific properties.
2. The technique of using the difference between "at most k" and "at most k-1" is a powerful pattern for finding "exactly k" cases.
3. The prefix sum approach shows an alternative way to solve the problem by tracking cumulative occurrences.
4. This problem can be viewed as a special case of "Subarrays with K Different Integers" where we're only concerned with the parity (odd/even) of integers.
5. The solution demonstrates how mathematical insights (like parity tracking) can simplify complex counting problems in arrays.
