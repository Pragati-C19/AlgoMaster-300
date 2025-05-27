import java.util.*;

public class CrackSafe {
    
}

/*
 * Intuitions :
 
    1. let's re read the que 
        - We have a safe that unlocks with a password of n digits
        - Each digit can be from 0 to k - 1
        - But the safe doesn't ask us to type in just one password
        - Instead, it checks everything we type 
        - and after each key we press -> it checks the last n digits we typed to see if those match the correct password.

    2. Example :
        - Let’s say the real password is 345 (so n = 3) and you type this: 012345
        - The safe checks:
            0   -> not enough digits -> ignore
            01  -> still not n digits -> ignore
            012 -> not 345 -> no
            123 -> no
            234 -> no
            345 -> match! safe opens
        - So: we want to type a string that eventually covers every possible combination of n digits from 0 to k-1
    
    3. But there is a issue we don't know the correct pass to say if (currString == Password)
    
    4. return string of minimum length that will unlock the safe at same point of entering it
        - After checking test cases I'm confused now what is the que wants me to return


 * Pattern :
 
    1. We need an graph to store node (n digits) as key and edge (0 to k nums) as value
    2. 
 
 * Pseudo Code :
 



 */