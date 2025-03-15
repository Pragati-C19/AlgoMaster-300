
def rotate(nums, k):
    n = len(nums)
    k = k % n  # Handle cases where k > n

    # Reverse helper function
    def reverse(start, end):
        while start < end:
            nums[start], nums[end] = nums[end], nums[start]
            start += 1
            end -= 1

    # Perform the three reversals
    reverse(0, n - 1)
    reverse(0, k - 1)
    reverse(k, n - 1)


nums = [1, 2, 3, 4, 5, 6, 7]
k = 3
rotate(nums, k)
print(nums)  # Output: [5, 6, 7, 1, 2, 3, 4]


""""
Pseudocode

k = k % len(nums)
Reverse the entire array.
Reverse the first k elements.
Reverse the remaining elements from k onward.
"""