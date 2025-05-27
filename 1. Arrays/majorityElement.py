def majorityElement(nums):
    candidate = None
    count = 0

    for num in nums:
        if count == 0:
            candidate = num

        count += 1 if num == candidate else -1

    return candidate


nums = [2, 2, 1, 1, 1, 2, 2]
print(majorityElement(nums))  # Output: 2



""""
 Pseudocode

 For O(n) time and O(1) space, we can use Boyer-Moore Voting Algorithm:

Initialize candidate as None and count as 0.
Iterate through the array:
If count is 0, set candidate to the current element.
If the current element equals candidate, increment count.
Otherwise, decrement count.
Return candidate

"""