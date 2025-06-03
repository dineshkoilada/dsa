def is_palindrome_valid(s: str) -> bool:
    """
    Checks if the given string is a valid palindrome.

    A valid palindrome reads the same backward as forward, ignoring spaces, punctuation, and case.

    Args:
        s (str): The string to check.

    Returns:
        bool: True if the string is a valid palindrome, False otherwise.

    Example:
        >>> is_palindrome_valid("A man, a plan, a canal: Panama")
        True
        >>> is_palindrome_valid("hello")
        False

    Time Complexity:
        O(n), where n is the length of the string. Each character is checked at most once.

    Space Complexity:
        O(1), as the algorithm uses only a constant amount of extra space regardless of input size.
    """
    left, right = 0, len(s) - 1
    while left < right:
        # Move left pointer to the next valid character
        while left < right and not s[left].isalnum():
            left += 1
        # Move right pointer to the previous valid character
        while left < right and not s[right].isalnum():
            right -= 1
        # Compare characters in a case-insensitive manner
        if s[left].lower() != s[right].lower():
            return False
        left += 1
        right -= 1
    return True


def main():
    """
    Demonstrates the usage of is_palindrome_valid with multiple sample inputs.
    """
    test_cases = [
        "A man, a plan, a canal: Panama",
        "racecar",
        "No lemon, no melon",
        "hello",
        "Was it a car or a cat I saw?"
    ]
    for s in test_cases:
        print(f'"{s}" -> {is_palindrome_valid(s)}')


if __name__ == "__main__":
    main()
