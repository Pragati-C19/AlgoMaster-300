import random

def findSecretWord(self, words, master):
         # Helper function to count matching positions between two words
        def count_matches(word1, word2):
            return sum(c1 == c2 for c1, c2 in zip(word1, word2))
        
        # Make up to 10 guesses (Leetcode constraint)
        for _ in range(10):
            # Choose a word — using a random one to avoid worst-case patterns
            guess = random.choice(words)
            matches = master.guess(guess)

            # If we guessed the secret word correctly (6 exact matches)
            if matches == 6:
                return

            # Narrow down the word list: keep words with the same match count
            words = [w for w in words if count_matches(w, guess) == matches]

secret = "acckzz", words = ["acckzz","ccbazz","eiowzz","abcczz"], allowedGuesses = 10
print(findSecretWord(secret, words, allowedGuesses))

"""
Pick a word — Start with a random word from the list.
Call Master.guess() — Get the number of matching positions.
Filter the list — Keep only words with the same number of matches as the guessed word.
Repeat — Continue this process with a new word from the filtered list until you find the secret word.

"""