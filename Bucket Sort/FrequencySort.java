public class FrequencySort {

}

/*
 * 
 * Intuitions :
 * 
 * 1. Count frequencies of each character (using a HashMap).
 * Example: For "tree", count = {t:1, r:1, e:2}
 * 2️. Sort characters by frequency —
 * - One approach: Use a priority queue (max-heap)
 * - Another (faster) approach: Bucket sort
 * 3️. Rebuild the string
 * 
 * Pattern :
 * 
 * 1. Frequency counting is a must (HashMap or Counter).
 * 2. Sorting — we need characters to appear based on frequency, not
 * lexicographical order.
 * 3. Output string — characters must appear together (e.g., "ee" not "eetr").
 * 
 * Pseudo Code :
 * 
 * function frequencySort(s):
 * 1. Count frequencies of each character using a HashMap.
 * 2. Create a "bucket" list, where index = frequency, and value = list of
 * characters with that frequency.
 * 3. Build the result string from the bucket, starting from the highest
 * frequency.
 * 4. Return the result string.
 * 
 * Lexicographical order Means - 
 * "apple" comes before "banana" (because 'a' is before 'b').
 * "abc" comes before "abd" (same prefix, but 'c' is before 'd').
 * "Aardvark" comes before "apple" (because 'A' is before 'a').
 * 
 * 
 */