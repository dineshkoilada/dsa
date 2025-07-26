from typing import List

def zero_striping(board: List[List[int]]) -> List[List[int]]:
    """
    Sets entire rows and columns to zero if any element in them is zero.

    Args:
        board (List[List[int]]): 2D list of integers.

    Returns:
        List[List[int]]: The modified board after zero striping.

    Example:
        >>> zero_striping([
        ...     [1, 2, 0, 4],
        ...     [5, 0, 7, 8],
        ...     [9, 10, 11, 12],
        ...     [0, 14, 15, 16]
        ... ])
        [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 12], [0, 0, 0, 0]]

    Time Complexity:
        O(m * n), where m is the number of rows and n is the number of columns.
    Space Complexity:
        O(m + n), for storing the sets of rows and columns to zero.
    """
    rows = len(board)
    cols = len(board[0]) if rows > 0 else 0

    # Step 1: Identify rows and columns to be zeroed
    zero_rows = set()
    zero_cols = set()

    for r in range(rows):
        for c in range(cols):
            if board[r][c] == 0:
                zero_rows.add(r)
                zero_cols.add(c)

    # Step 2: Zero out identified rows
    for r in zero_rows:
        for c in range(cols):
            board[r][c] = 0

    # Step 3: Zero out identified columns
    for c in zero_cols:
        for r in range(rows):
            board[r][c] = 0

    return board


def main():
    """
    Demonstrates the usage of zero_striping with sample inputs.
    """
    board = [
        [1, 2, 0, 4],
        [5, 0, 7, 8],
        [9, 10, 11, 12],
        [0, 14, 15, 16]
    ]
    
    print("Original Board:")
    for row in board:
        print(row)

    modified_board = zero_striping([row[:] for row in board])
    
    print("\nModified Board after Zero Striping:")
    for row in modified_board:
        print(row)


if __name__ == "__main__":
    main()