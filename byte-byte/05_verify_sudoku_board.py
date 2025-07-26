from typing import List

def verify_sudoku_board(board: List[List[int]]) -> bool:
    """
    Verifies if a given Sudoku board is valid.

    Args:
        board (List[List[int]]): A 9x9 Sudoku board represented as a list of lists, where each inner list contains integers from 0 to 9.

    Returns:
        bool: True if the board is valid, False otherwise.

    Example:
        >>> verify_sudoku_board([
        ...     [5, 3, 0, 0, 7, 0, 0, 0, 0],
        ...     [6, 0, 0, 1, 9, 5, 0, 0, 0],
        ...     [0, 9, 8, 0, 0, 0, 0, 6, 0],
        ...     [8, 0, 0, 0, 6, 0, 0, 0, 3],
        ...     [4, 0, 0, 8, 0, 3, 0, 0, 1],
        ...     [7, 0, 0, 0, 2, 0, 0, 0, 6],
        ...     [0, 6, 0, 0, 0, 2, 8, 4],
        ...     [2, 8, 7], [3]
        ... ])
        True

    Time Complexity:
        O(n^2), where n is the size of the board (9 in this case). Each cell is checked once.

    Space Complexity:
        O(n), for storing sets of seen numbers.
    """
    row_sets = [set() for _ in range(9)]
    col_sets = [set() for _ in range(9)]
    subgrid_sets = [set() for _ in range(9)]
    for i in range(9):
        for j in range(9):
            num = board[i][j]
            if num != 0:  # Skip empty cells
                if (num in row_sets[i] or
                    num in col_sets[j] or
                    num in subgrid_sets[(i // 3) * 3 + (j // 3)]):
                    return False
                row_sets[i].add(num)
                col_sets[j].add(num)
                subgrid_sets[(i // 3) * 3 + (j // 3)].add(num)
    return True

def main():
    """
    Demonstrates the usage of verify_sudoku_board with sample inputs.
    """
    valid_board = [
        [5, 3, 0, 0, 7, 0, 0, 0, 0],
        [6, 0, 0, 1, 9, 5, 0, 0, 0],
        [0, 9, 8, 0, 0, 0, 0, 6, 0],
        [8, 0, 0, 0, 6, 0, 0, 0, 3],
        [4, 0, 0, 8, 0, 3, 0, 0, 1],
        [7, 0, 0, 0, 2, 0, 0, 0, 6],
        [0, 6, 0, 0, 0, 0, 2, 8, 0],
        [0, 0, 0, 4, 1, 9, 0, 0, 5],
        [0, 0, 0, 0, 8, 0, 0, 7, 9]
    ]
    invalid_board = [
        [5, 3, 0, 0, 7, 0, 0, 0, 0],
        [6, 0, 0, 1, 9, 5, 0, 0, 0],
        [0, 9, 8, 0, 0, 0, 0, 6, 0],
        [8, 0, 0, 0, 6, 0, 0, 0, 3],
        [4, 0, 0, 8, 0, 3, 0, 0, 1],
        [7, 0, 0, 0, 2, 0, 0, 0, 6],
        [0, 6, 0, 0, 0, 0, 2, 8, 0],
        [0, 0, 0, 4, 1, 9, 0, 0, 5],
        [0, 0, 0, 0, 8, 0, 0, 7, 5]  # Duplicate 5 in last row
    ]
    print("Valid board is valid:", verify_sudoku_board(valid_board))
    print("Invalid board is valid:", verify_sudoku_board(invalid_board))

if __name__ == "__main__":
    main()