def zeroFilledSubarray(nums):
    count = 0
    total_subarrays = 0

    for num in nums:
        if num == 0:
            count += 1
        else:
            total_subarrays += count * (count + 1) // 2
            count = 0

    # Handle trailing zeros
    total_subarrays += count * (count + 1) // 2
    return total_subarrays


nums = [1, 3, 0, 0, 2, 0, 0, 4]
print(zeroFilledSubarray(nums))  # Output: 6


""""
function zeroFilledSubarray(nums):
    count = 0
    total_subarrays = 0

    for num in nums:
        if num == 0:
            count += 1
        else:
            total_subarrays += (count * (count + 1)) / 2
            count = 0

    total_subarrays += (count * (count + 1)) / 2  # Handle ending zeros
    return total_subarrays


"""