# Contains Duplicate II

## Problem Statement

Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

## Examples

**Example 1:**
```
Input: nums = [1,2,3,1], k = 3
Output: true
Explanation: nums[0] = nums[3] and |0 - 3| = 3 <= 3
```

**Example 2:**
```
Input: nums = [1,0,1,1], k = 1
Output: true
Explanation: nums[2] = nums[3] and |2 - 3| = 1 <= 1
```

**Example 3:**
```
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
Explanation: No duplicates are found within distance k of each other.
```

## Approach: Sliding Window with HashSet

This problem can be solved using a sliding window approach with a HashSet to track elements within the window. The key insight is that for each element, we only care about whether a duplicate exists within the previous `k` elements.

### Algorithm:

1. Use a HashSet to store elements in the current window
2. Maintain a window of size at most `k+1`
3. For each element:
   - If the element already exists in the set, return true
   - Add the element to the set
   - If the window size exceeds `k`, remove the oldest element
4. If no duplicates are found within distance `k`, return false

## Code Solution

```java
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // HashSet to store elements in the current window
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // If current element is in the set, we found a duplicate within distance k
            if (window.contains(nums[i])) {
                return true;
            }
            
            // Add current element to the set
            window.add(nums[i]);
            
            // Remove the oldest element if window size exceeds k
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        
        return false;
    }
}
```

## Alternative Solution: Using HashMap

We can also solve this problem using a HashMap to store the indices of elements:

```java
import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // HashMap to store the most recent index of each element
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            // If element exists in map and is within distance k
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            
            // Update the most recent index of this element
            map.put(nums[i], i);
        }
        
        return false;
    }
}
```

## Complexity Analysis

- **HashSet Approach**:
  - **Time Complexity**: O(n) where n is the length of the array. We process each element once.
  - **Space Complexity**: O(min(n, k+1)) for storing at most k+1 elements in the HashSet.

- **HashMap Approach**:
  - **Time Complexity**: O(n)
  - **Space Complexity**: O(n) in the worst case if all elements are distinct.

## Key Insights

1. This problem applies the sliding window concept with a fixed maximum window size of `k+1`.
2. The HashSet approach maintains a window of the most recent `k` elements, which is appropriate for this problem since we only care about elements within distance `k`.
3. The HashMap approach gives us more flexibility by storing the most recent index of each element, allowing us to directly calculate the distance between duplicates.
4. When using the HashSet approach, it's important to understand that we remove the element that's outside the window (the (i-k)th element), not necessarily the least recently used element.
5. Both approaches have O(n) time complexity, but the HashSet approach may use less space when `k` is much smaller than `n`.
