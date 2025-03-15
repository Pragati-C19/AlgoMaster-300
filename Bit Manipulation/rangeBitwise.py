def rangeBitwiseAnd(left, right):
    shift = 0
    
    # Find common prefix
    while left < right:
        left >>= 1
        right >>= 1
        shift += 1

    # Shift back the common prefix
    return left << shift

print(rangeBitwiseAnd(5,7))