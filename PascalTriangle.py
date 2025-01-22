def calculate_value(row, col):
    """
    Recursive method to get value at (row, col) in Pascal's Triangle.
    """
    if col == 0 or col == row:  # Base cases
        return 1
    return calculate_value(row - 1, col - 1) + calculate_value(row - 1, col)  # Recursive step


def print_pascal_triangle(num_rows):
    """
    Method to print Pascal's Triangle with the specified number of rows.
    """
    # Determine the maximum width of the largest number
    max_value = calculate_value(num_rows - 1, num_rows // 2)
    max_width = len(str(max_value))

    for row in range(num_rows):
        # Print leading spaces for alignment
        print(" " * ((num_rows - row - 1) * max_width), end="")

        # Print values in the current row with formatting
        for col in range(row + 1):
            print(f"{calculate_value(row, col):{max_width * 2}}", end="")

        print()  # Move to the next row


if __name__ == "__main__":
    num_rows = 13
    print(f"Pascal's Triangle with {num_rows} rows:")
    print_pascal_triangle(num_rows)
