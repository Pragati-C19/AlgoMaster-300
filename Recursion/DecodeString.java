import java.util.*;

public class DecodeString {

    public String decodeString(String s) {
        
    }

    public static void main(String[] args){
        DecodeString solution = new DecodeString();

        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";

        System.out.println("Output1: " + solution.decodeString(s1) + "\n"); // "aaabcbc"
        System.out.println("Output2: " + solution.decodeString(s2) + "\n"); // "accaccacc"
        System.out.println("Output3: " + solution.decodeString(s3) + "\n"); // "abcabccdcdcdef"

    }

}

/**
 * 
 * Intuitions :
 * 
 * 1. The problem is asking us to decode a string that has been encoded with a
 * specific pattern.
 * 2. Nested patterns require a stack to remember the previous states
 * 3. When we encounter a number (k), we know a repetition follows.
 * 
 * Pattern :
 * 
 * 1. Digit (0-9): Build the number (could be multi-digit like 23[abc]).
 * 2. [: Push current string and number onto stack, start a new segment.
 * 3. ]: Pop from stack, repeat the segment, and append to the previous string.
 * 4. letter: Append to the current string.
 * 
 * Example :
 * 
 * 3[a2[c]]
 * 
 * Read 3 → hold 3.
 * Hit [ → push empty string and 3 onto stack.
 * Read a → build string a.
 * Hit 2 → hold 2.
 * Hit [ → push a and 2 onto stack.
 * Read c → build string c.
 * Hit ] → pop 2 and a, form a + cc = acc.
 * Hit ] → pop 3 and "", form accaccacc.
 * 
 * Final result: "accaccacc"
 * 
 * Pseudo Code :
 * 
 * function decodeString(s):
 * stack = []
 * current_string = ""
 * num = 0
 * 
 * for char in s:
 * if char is digit:
 * num = num * 10 + int(char) # Handle multi-digit numbers
 * elif char is '[':
 * stack.push((current_string, num))
 * current_string = ""
 * num = 0
 * elif char is ']':
 * last_string, repeat_count = stack.pop()
 * current_string = last_string + current_string * repeat_count
 * else:
 * current_string += char
 * 
 * return current_string
 * 
 * 
 */
