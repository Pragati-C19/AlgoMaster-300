
def removeDuplicates(nums):
    if not nums:
        return 0

    pos = 1

    for i in range(1, len(nums)):
        if nums[i] != nums[i - 1]:
            nums[pos] = nums[i]
            pos += 1

    return pos


nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
k = removeDuplicates(nums)

print("Unique count:", k)
print("Modified array:", nums[:k])


""""
Pseudocode
Initialize a pointer (pos) at 1 (index of the next unique position).
Iterate from the second element (i = 1) to the end of the array:
If nums[i] != nums[i-1] (i.e., a new unique element):
Place nums[i] at nums[pos] and increment pos.
Return pos 

"""