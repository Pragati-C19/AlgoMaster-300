def isPalindrome(s: str) -> bool:
    # Two pointers setup
    left, right = 0, len(s) - 1
    
    while left < right:
        # Skip non-alphanumeric chars
        while left < right and not s[left].isalnum():
            left += 1
        while left < right and not s[right].isalnum():
            right -= 1
        
        # Compare characters (case insensitive)
        if s[left].lower() != s[right].lower():
            return False
        
        # Move pointers inward
        left += 1
        right -= 1

    return True


print(isPalindrome("A man, a plan, a canal: Panama"))  # Output: True  
print(isPalindrome("race a car"))  # Output: False  
print(isPalindrome(" "))  # Output: True  


"""
Two pointers approach
Initialize two pointers:

left starts at the beginning.
right starts at the end.
Loop until pointers meet:

If s[left] is not alphanumeric, move left forward.
If s[right] is not alphanumeric, move right backward.
If both are valid, compare the characters (lowercased).
If they don’t match, return false.
If they match, move both pointers inward.
If loop completes, return true (it’s a palindrome).

"""