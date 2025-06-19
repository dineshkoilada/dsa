from typing import List

def triplet_sum(nums: List[int], target: int) -> List[List[int]]:
    """
    Finds all unique triplets in an array that add up to the target sum using the two-pointer technique.

    Args:
        nums (List[int]): A list of integers (not necessarily sorted).
        target (int): The target sum to find.

    Returns:
        List[List[int]]: A list of unique triplets (as lists) whose sum is equal to target. Returns an empty list if no such triplet exists.

    Example:
        >>> triplet_sum([1, 2, 3, 4, 5], 9)
        [[1, 3, 5], [2, 3, 4]]

    Time Complexity:
        O(n^2), where n is the number of elements in nums. The outer loop runs n times and the inner two-pointer search runs in O(n).

    Space Complexity:
        O(k), where k is the number of triplets found (for the result list).
    """
    nums_sorted = sorted(nums)
    result = []
    n = len(nums_sorted)
    for i in range(n - 2):
        if i > 0 and nums_sorted[i] == nums_sorted[i-1]:
            continue
        left, right = i + 1, n -1
        current_target = target - nums_sorted[i]
        while left < right:
            current_sum = nums_sorted[left] + nums_sorted[right]
            if current_sum == current_target:
                result.append([nums_sorted[i], nums_sorted[left], nums_sorted[right]])
                while left < right and nums_sorted[left] == nums_sorted[left + 1]:
                    left += 1
                while left < right and nums_sorted[right] == nums_sorted[right - 1]:
                    right -= 1
                left += 1
                right -= 1
            elif current_sum < current_target:
                left += 1
            else:
                right -= 1
    return result


def main():
    """
    Demonstrates the usage of triplet_sum with a sample input.
    """
    nums = [1, 2, 3, 4, 5]  # Input can be unsorted
    target = 9
    result = triplet_sum(nums, target)
    print("Result:", result)


if __name__ == "__main__":
    main()
