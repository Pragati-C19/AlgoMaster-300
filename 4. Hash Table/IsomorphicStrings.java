import java.util.HashMap;

public class IsomorphicStrings  {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check s -> t mapping
            if (mapST.containsKey(charS) && mapST.get(charS) != charT) {
                return false;
            }

            // Check t -> s mapping
            if (mapTS.containsKey(charT) && mapTS.get(charT) != charS) {
                return false;
            }

            // Establish the mappings
            mapST.put(charS, charT);
            mapTS.put(charT, charS);
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";

        System.out.println("Are the strings isomorphic? " + isIsomorphic(s, t));
    }
}

/*
 * 
 * 
 * Function isIsomorphic(s, t):
    If length of s is not equal to length of t:
        Return False
    
    Initialize mapST and mapTS as empty hashmaps

    For i from 0 to length of s:
        charS = s[i]
        charT = t[i]
        
        If charS is already in mapST:
            If mapST[charS] is not equal to charT:
                Return False
        Else:
            mapST[charS] = charT
        
        If charT is already in mapTS:
            If mapTS[charT] is not equal to charS:
                Return False
        Else:
            mapTS[charT] = charS

    Return True

 */
