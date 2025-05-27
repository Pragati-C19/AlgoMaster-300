import java.util.*;

public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int windowLen = wordLen * wordCount;
        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> windowMap = new HashMap<>();
            int count = 0;

            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

  
    public static void main(String[] args) {
        FindSubstring solver = new FindSubstring();
        System.out.println(solver.findSubstring("barfoothefoobarman", new String[] {"foo", "bar"})); // Output: [0, 9]
        System.out.println(solver.findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"})); // Output: []
        System.out.println(solver.findSubstring("barfoofoobarthefoobarman", new String[] {"bar", "foo", "the"})); // Output: [6, 9, 12]
    }
}

/*
 * 
 * Length of the Substrings:

Since all words have the same length, the total length of the concatenated substring will be len(words) * len(words[0]).
We need to look for substrings of this exact length in s.
Sliding Window Technique:

Start by sliding a window of length len(words) * len(words[0]) across the string s.
For each window, check if it can be rearranged to match a concatenation of the words.
Count the Words:

Use a hashmap (or dictionary) to keep track of how many times each word appears in the array words.
Then, for each sliding window, use a similar hashmap to track the words in that window and check if it matches the required counts.
 */