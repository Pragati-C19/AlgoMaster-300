def longestCommonPrefix(strs):
    # Edge case: empty array
    if not strs:
        return ""
    
    # Start with the first string as the prefix
    prefix = strs[0]
    
    # Compare prefix with each string
    for string in strs[1:]:
        # Reduce the prefix until it's a prefix of the string
        while not string.startswith(prefix):
            prefix = prefix[:-1]
            # If prefix becomes empty, no common prefix exists
            if not prefix:
                return ""
    
    return prefix


print(longestCommonPrefix(["flower", "flow", "flight"]))  # Output: "fl"
print(longestCommonPrefix(["dog", "racecar", "car"]))      # Output: ""
print(longestCommonPrefix(["apple", "ape", "april"]))      # Output: "ap"


"""
Take the first string as the initial prefix.
Compare it with each subsequent string:
Shorten the prefix if the string doesn’t start with it.
Keep shortening until you find a match or end up with an empty prefix ("").
Return the final prefix — or "" if nothing matches.
"""