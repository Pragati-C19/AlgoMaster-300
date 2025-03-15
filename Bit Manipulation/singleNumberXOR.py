def singleNumber(nums):
    # Step 1: XOR all numbers
    xor_result = 0
    for num in nums:
        xor_result ^= num
    
    # Step 2: Find rightmost set bit
    diff_bit = xor_result & -xor_result
    
    # Step 3: Partition numbers into two groups
    x, y = 0, 0
    for num in nums:
        if num & diff_bit:
            x ^= num
        else:
            y ^= num
    
    return [x, y]

print(singleNumber([1,2,1,3,2,5]))


"""
1. XOR all numbers together:

The result will be x ^ y, where x and y are the two unique numbers (because duplicates cancel out to 0).
2. Find the rightmost set bit (difference bit):

This bit must differ between x and y (because x ^ y has at least one 1 bit).
3. Partition numbers into two groups:

Group 1: Numbers with this bit set.
Group 2: Numbers without this bit set.
"""