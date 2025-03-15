def increasingTriplet(nums):
    first = second = float('inf')

    for num in nums:
        if num <= first:
            first = num  # Smallest number
        elif num <= second:
            second = num  # Second smallest number
        else:
            return True  # num > second, found triplet!

    return False


print(increasingTriplet([1, 2, 3, 4, 5]))  # True
print(increasingTriplet([5, 4, 3, 2, 1]))  # False
print(increasingTriplet([2, 1, 5, 0, 4, 6]))  # True


"""
1. Initialize first = inf, second = inf
2. Loop through each number num in nums:
    - If num is smaller than first, update first
    - Else if num is smaller than second (but larger than first), update second
    - Else, if num is larger than both first and second, return True (triplet found)
3. If loop finishes without finding such a triplet, return False

"""