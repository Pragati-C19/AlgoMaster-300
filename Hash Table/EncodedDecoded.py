class Solution:
    def __init__(self):
        # Create two dictionaries to store URL mappings
        self.url_to_code = {}
        self.code_to_url = {}
        self.base = "http://tinyurl.com/"
        self.counter = 0

    def encode(self, longUrl):
        """
        Encodes a URL to a shortened URL.
        """
        # If URL is already encoded, return the existing short URL
        if longUrl in self.url_to_code:
            return self.base + self.url_to_code[longUrl]
        
        # Generate a unique code using a counter
        code = str(self.counter)
        self.counter += 1

        # Store the mappings in both directions
        self.url_to_code[longUrl] = code
        self.code_to_url[code] = longUrl

        # Return the complete shortened URL
        return self.base + code

    def decode(self, shortUrl):
        """
        Decodes a shortened URL to its original URL.
        """
        # Extract the unique code from the short URL
        code = shortUrl.replace(self.base, "")
        
        # Return the original URL if found, otherwise return an empty string
        return self.code_to_url.get(code, "")


# Testing the Solution
if __name__ == "__main__":
    solution = Solution()
    
    # Test Case 1
    long_url = "https://leetcode.com/problems/design-tinyurl"
    short_url = solution.encode(long_url)
    print("Shortened URL:", short_url)
    print("Decoded URL:", solution.decode(short_url))
    
    # Test Case 2 (Re-encoding the same URL)
    repeat_short_url = solution.encode(long_url)
    print("Repeated Shortened URL:", repeat_short_url)
    
    # Test Case 3 (Different URL)
    another_url = "https://example.com"
    short_url2 = solution.encode(another_url)
    print("Another Shortened URL:", short_url2)
    print("Decoded Another URL:", solution.decode(short_url2))


