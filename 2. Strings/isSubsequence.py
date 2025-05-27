def isSubsequence(s, t):
    i, j = 0, 0

    # Traverse both strings
    while j < len(t):
        if i < len(s) and s[i] == t[j]:
            i += 1  # Move i on match
        j += 1  # Always move j

    return i == len(s)  # True if all of s was matched


print(isSubsequence("abc", "ahbgdc"))  # Output: True
print(isSubsequence("axc", "ahbgdc"))   # Output: False



""""
1. Initialize two pointers: i = 0 (s), j = 0 (t)

2. While j < t.length:
   - If s[i] == t[j], move i (match found)
   - Always move j (keep scanning t)

3. If i == s.length (matched all chars of s), return true
4. Otherwise, return false


"""