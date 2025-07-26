from typing import List

def pair_sum_unsorted_two_pass(nums: List[int], target: int) -> List[int]:
    """
    Finds two numbers in an unsorted array that add up to the target sum using a hash map.

    Args:
        nums (List[int]): A list of integers (unsorted).
        target (int): The target sum to find.

    Returns:
        List[int]: 1-based indices of the two numbers whose sum is equal to target. Returns an empty list if no such pair exists.

    Example:
        >>> pair_sum_unsorted_two_pass([2, 7, 11, 15], 9)
        [1, 2]

    Time Complexity:
        O(n), where n is the number of elements in nums. Each element is visited once.

    Space Complexity:
        O(n), for storing the hash map of seen numbers.
    """
    num_map = {}
    for i, num in enumerate(nums):
        complement = target - num
        if complement in num_map and num_map[complement] != i:  
            return [num_map[complement], i]  
        num_map[num] = i
    return []

def main():
    """
    Demonstrates the usage of pair_sum_unsorted_two_pass with sample inputs.
    """
    test_cases = [
        ([2, 7, 11, 15], 9),
        ([3, 2, 4], 6),
        ([1, 5, 3, 7], 8),
        ([1, 2, 3, 4], 8),  # No valid pair
    ]
    for nums, target in test_cases:
        result = pair_sum_unsorted_two_pass(nums, target)
        print(f"nums={nums}, target={target} -> {result}")

if __name__ == "__main__":
    main()