from typing import List

def largest_container(heights: List[int]) -> int:
    """
    Calculates the maximum area of water that can be contained between two lines.

    The function uses the two-pointer technique to efficiently find the largest area that can be formed by any two lines in the input list, where the area is determined by the shorter of the two lines and the distance between them.

    Args:
        heights (List[int]): List of non-negative integers representing the heights of vertical lines on the x-axis.

    Returns:
        int: The maximum area of water that can be contained.

    Example:
        >>> largest_container([1,8,6,2,5,4,8,3,7])
        49

    Time Complexity:
        O(n), where n is the number of elements in heights. Each element is visited at most once by either pointer.

    Space Complexity:
        O(1), as the algorithm uses only a constant amount of extra space regardless of input size.
    """
    left, right = 0, len(heights) - 1
    max_area = 0
    while left < right:
        area = min(heights[left], heights[right]) * (right - left)
        max_area = max(max_area, area)
        if heights[left] < heights[right]:
            left += 1
        else:
            right -= 1
    return max_area

def main():
    """
    Demonstrates the usage of largest_container with a sample input.
    """
    heights = [1,8,6,2,5,4,8,3,7]  # Example heights
    result = largest_container(heights)
    print("Maximum area of water that can be contained:", result)   

if __name__ == "__main__":
    main()