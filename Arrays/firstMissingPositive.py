def firstMissingPositive(nums):
    n = len(nums)

    # Step 1: Place each number in its correct spot (nums[i] == i+1)
    for i in range(n):
        while 1 <= nums[i] <= n and nums[nums[i] - 1] != nums[i]:
            nums[nums[i] - 1], nums[i] = nums[i], nums[nums[i] - 1]

    # Step 2: Find the first missing positive
    for i in range(n):
        if nums[i] != i + 1:
            return i + 1

    # Step 3: If all numbers are placed correctly
    return n + 1


# Test cases
print(firstMissingPositive([1, 2, 0]))  # Output: 3
print(firstMissingPositive([3, 4, -1, 1]))  # Output: 2
print(firstMissingPositive([7, 8, 9, 11, 12]))  # Output: 1


"""
1. Loop through the array:
   - If nums[i] is in the range [1, n] and nums[i] ≠ nums[nums[i] - 1], swap nums[i] with nums[nums[i] - 1]
   - Otherwise, move to the next number

2. After placing numbers correctly:
   - Loop through the array again
   - If nums[i] != i + 1, return i + 1 (first missing positive)

3. If all numbers are in place from 1 to n, return n + 1

"""