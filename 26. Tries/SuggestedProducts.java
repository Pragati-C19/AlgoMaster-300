import java.util.*;

public class SuggestedProducts {
    

    // Driver Function
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        
        List<List<String>> result = new ArrayList<>();
        String prefix = "";

        Arrays.sort(products);
        System.out.println("    Sorted Products List : " + Arrays.toString(products));

        for(char ch : searchWord.toCharArray()){

            prefix += ch;
            System.out.println("    ~ Prefix we are checking : " + prefix);

            List<String> currList = new ArrayList<>();

            int start = binarySearch(products, prefix);

            for (int i = start; i < Math.min( start + 3, products.length); i++) {
                
                System.out.println("      -> Product we are checking : " + products[i]);

                // startsWith is an inbuild Function
                if(products[i].startsWith(prefix)){
                    currList.add(products[i]);
                }

                System.out.println("      Current list So far : " + currList);
            }

            result.add(currList);
            System.out.println("    Result list So far : " + result);   
            
        }

        return result;
    }

    // Helper Function : Instead of scanning every product for every character typed, use binary search to find the range of matching products
    private int binarySearch(String[] products, String prefix){

        int left = 0;
        int right = products.length;

        while (left < right) {
            
            int mid = (left + right) / 2;

            if (products[mid].compareTo(prefix) < 0) {
                // "app".compareTo("apple")   →  < 0 (means "app" is *before* "apple")
                left = mid + 1;
            }
            else {
                // "apple".compareTo("app")   →  > 0 (means "apple" is *after* "app")
                right = mid;
            }

        }

        return left;
    }


    public static void main(String[] args){

        SuggestedProducts solution = new SuggestedProducts();

        String[] products1 = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord1 = "mouse";
        System.out.println("-> Result 1 : " + solution.suggestedProducts(products1, searchWord1) + "\n");

        String[] products2 = {"havana"};
        String searchWord2 = "havana";
        System.out.println("-> Result 2 : " + solution.suggestedProducts(products2, searchWord2) + "\n");

    }
}


/**
 * 
 //?    We can use binary search here.. left right and mid thing.. fo rnot searching all elemnts of produts
 *  if (products[mid].compareTo(prefix) < 0) {
 *       -> Move to the right half (because products[mid] is lexicographically less than prefix)
 *   } else if (products[mid].compareTo(prefix) > 0) {
 *       -> Move to the left half (because products[mid] is lexicographically greater than prefix)
 *   } else {
 *       -> products[mid] is equal to prefix (match found), so we can stop or process this match
 *       -> If compareTo() returns 0, it means the current word matches the prefix.
 *  }
 *  
 * Intuitions :
 * 
 * 1. suggest 3 product names from products after each char of searchWOrd
 * 2. suggest products should have common prefix with searchWord
 * 3. if there are more than 3 products with same prefix then return first 3 lexicographically minimun products
 * 4. Return list of suggested products after each charecter of searchWord
 * 
 * 5. So basically mala products cha array dilay and ek search word dilay
 * 6. me first letter ghenar search word ch ani check karel ki te ahe ka array madhe?
 * - if yes then add 3 whole word in the result array
 * - if words are greater than 3 then add first 3 words
 * - if words are less than 3 then add all words
 * 
 * 
 * Pattern :
 * 
 * 1. you need to sort string array to get lexicographically order
 * 2. assign a result array to store result
 * 3. assign a prefix to store char one by one 
 * 4. for loop (ch : searchWord) 
 *      - prefix.add(ch)
 *      - create a curr array (creating here means getting new value for every new prefix)
 *      - for loop (int i from 0 to products.length)
 *          - If the products[i] startsWith(prefix) -> add that product in curr
 *      - result.add(curr)
 * 5. return result
 * 
 * Pseudo Code :
 * 
 * 
 * function suggestedProducts (products, searchWord){
 * 
 *      products.sort()
 *      result = new Array
 *      prefix = ""
 * 
 *      for (ch : searchWord){
 * 
 *          -> Adding ch in prefix 
 *          prefix =+ ch
 *          
 *          -> creating a curr to store products
 *          curr = new array
 * 
 *          for (int i = 0; i < products.length; i++){
 *              if(products[i].startWith(prefix)) -> curr.add(products[i])
 *          }
 * 
 *          -> Add curr in result
 * 
 *          if(curr.length > 3){
 *              for(int i = 0 ; i < 3; i++){
 *                  result.add(curr[i])
 *              }
 *          }
 *          else{
 *              result.add(curr)
 *          }
 *          
 *      }
 * 
 *      return result;
 * }
 * 
 */