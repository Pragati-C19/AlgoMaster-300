def moveZeroes(nums):
    pos = 0

    # Move non-zero elements to the front
    for i in range(len(nums)):
        if nums[i] != 0:
            nums[pos] = nums[i]
            pos += 1

    # Fill the rest with zeros
    for i in range(pos, len(nums)):
        nums[i] = 0


nums = [0, 1, 0, 3, 12]
moveZeroes(nums)
print(nums)

"""
    Psuedo Code:

    1. Initialize a position pointer (pos) to track where the next non-zero should go.
    2. Loop through the array:
    3. If the current element is non-zero, place it at nums[pos] and increment pos.
    4. After the loop, fill the remaining positions with 0s.
"""