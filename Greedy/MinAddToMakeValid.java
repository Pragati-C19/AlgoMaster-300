import java.util.*;

public class MinAddToMakeValid {
    
    public int minAddToMakeValid(String s) {
        
        int moveCount = 0;
        int openParenthesisCount = 0;
        int closeParenthesisCount = 0;

        for (char ch : s.toCharArray()) {
            
            System.out.println("Checking Char : " + ch);

            if (ch == '(') {
                openParenthesisCount++;
            }

            if (ch == ')') {
                closeParenthesisCount++;
            }
        }

        System.out.println("    -> Count of open and close parenthesis :  " + openParenthesisCount + " , " + closeParenthesisCount);

        moveCount = openParenthesisCount - closeParenthesisCount;
        System.out.println("Move Count is : " + moveCount);

        return Math.abs(moveCount);
    }

    public static void main(String[] args){

        MinAddToMakeValid solution = new MinAddToMakeValid();

        String s1 = "())";
        System.out.println("Result 1 : " + solution.minAddToMakeValid(s1) + "\n");

        String s2 = "(((";
        System.out.println("Result 2 : " + solution.minAddToMakeValid(s2) + "\n");

    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Parentheses string is valid if below conditions are true
 *      - if String is empty -> it's valid
 *      - It can be written as (A concatenated with B), where A and B are valid strings
 *      - It can be written as (A), where A is a valid string
 * 2. tbh I don't understand last 2 points
 * 3. u have given a parenthesis string
 * 4. in one move, u can insert a parenthesis at any position of the string
 *      - example : s = "()))"
 *          u can add ( at any position -> like here I added at index 1 "(()))"
 *          or ) at any position        -> like here I added at index 3 "())))"
 * 5. return the number of moves required to make s valid
 * 
 * 
 * Pattern :
 * 
 * 1. Trace example :
 * 
 *      example : ())
 *      - so parentheses valid hoil jevha he string (()) ashi banel
 *      - as in equal number of ( and ) in string asel tevha
 *      - so ata ithe tr dusra kahi option nahiye valid karayla..
 *      - for index 0 -> (()) , )())
 *      - for index 1 -> (()) , ()))
 *      - for index 2 -> ())) , ()()
 * 
 * 
 * What I understand
 * 
 * 1. jr maza openParenthesisCount == closeParenthesisCount asel tr ti string valid ahe
 * 2. count open and close parenthesis of a given string ())
 *      open = 1 and close = 2
 * 3. now say how many parenthesis we need to make it valid? 
 *      2 - 1 = 1 open 
 * 4. count that move and return it
 * 
 * 
 * check second example too
 *        String = (((
 *          open = 3 and close = 0
 *          diff = 3 - 0 = 3
 *          return 3
 *          
 *      
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */