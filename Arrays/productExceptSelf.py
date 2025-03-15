
def productExceptSelf(nums):
    n = len(nums)
    answer = [1] * n

    # First pass: prefix products
    prefix = 1
    for i in range(n):
        answer[i] = prefix
        prefix *= nums[i]

    # Second pass: suffix products
    suffix = 1
    for i in range(n-1, -1, -1):
        answer[i] *= suffix
        suffix *= nums[i]

    return answer


nums = [1, 2, 3, 4]
print(productExceptSelf(nums))  # Output: [24, 12, 8, 6]


""""
Pseudocode
Initialize answer[] with 1s (this will store our result).
First pass (Left to Right):
Track the prefix product (product of elements before i), store it in answer[i].
Second pass (Right to Left):
Track the suffix product (product of elements after i) and multiply it into answer[i].
Return answer[]
"""