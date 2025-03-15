public class RansomNote  {
    public static boolean canConstruct(String ransomNote, String magazine) {
        // Count frequencies of each character in magazine
        int[] charCount = new int[26];

        for (char c : magazine.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Check if we can form ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (charCount[c - 'a'] == 0) {
                return false;  // Not enough of this character
            }
            charCount[c - 'a']--;
        }

        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";

        System.out.println("Can construct ransom note? " + canConstruct(ransomNote, magazine));
    }
}


/*
 * 
 * def canConstruct(ransomNote, magazine):
    # Count frequencies of each letter in magazine
    magazine_count = count characters in magazine
    
    # Check if ransomNote can be formed
    for char in ransomNote:
        if char not in magazine_count or magazine_count[char] == 0:
            return False
        magazine_count[char] -= 1
    
    return True

 */