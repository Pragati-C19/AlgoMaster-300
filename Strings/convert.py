def convert(s: str, numRows: int) -> str:
    # Edge case: No zigzag needed if numRows is 1
    if numRows == 1 or numRows >= len(s):
        return s
    
    # Create a list of empty strings for each row
    rows = [''] * numRows
    current_row = 0
    direction = -1  # Controls the zigzag
    
    # Traverse each character in the string
    for char in s:
        # Add the character to the current row
        rows[current_row] += char
        
        # Change direction when hitting the top or bottom row
        if current_row == 0 or current_row == numRows - 1:
            direction *= -1
            
        # Move to the next row in the current direction
        current_row += direction
    
    # Concatenate all rows into one final string
    return ''.join(rows)


print(convert("PAYPALISHIRING", 3))  # Output: "PAHNAPLSIIGYIR"
print(convert("PAYPALISHIRING", 4))  # Output: "PINALSIGYAHRPI"
print(convert("A", 1))                # Output: "A"


"""

P     I    N  
A   L S  I G  
Y A   H R  
P     I  

Edge Case: If numRows == 1, return the string directly — no zigzag happens.
Initialize rows: Create an array of strings for each row.
Traverse the string:
Move down row by row until you hit the bottom.
Reverse direction and move back up until you hit the top.
Repeat this zigzag until the string is done.
Combine rows: Concatenate all rows to form the final result.
"""