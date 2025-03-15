def reverseWords(s):
    # Step 1: Split words by spaces (handles multiple spaces automatically)
    words = s.strip().split()
    
    # Step 2: Reverse the list of words
    reversed_words = words[::-1]
    
    # Step 3: Join the words back into a string with a single space separator
    return " ".join(reversed_words)

print(reverseWords("the sky is blue"))        # Output: "blue is sky the"
print(reverseWords("  hello world  "))         # Output: "world hello"
print(reverseWords("a good   example"))        # Output: "example good a"
print(reverseWords("  singleWord  "))          # Output: "singleWord"
print(reverseWords("   "))                     # Output: "" (edge case with only spaces)


"""
Trim spaces using .strip().
Split words by spaces and handle multiple spaces automatically with .split().
Reverse the word list using slicing ([::-1]).
Join the words back into a single string with one space between them.

"""