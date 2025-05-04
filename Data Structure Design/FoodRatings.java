import java.util.*;

public class FoodRatings {
    
    private static calss FoodList{

        String foodName;
        int rating;

        FoodList(String foodName, int rating){
            this.foodName = foodName;
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "(" + foodName + ", " + rating + ")";
        }
    }
    
    // Gloablly Declare Variable 


    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        
    }
    
    public void changeRating(String food, int newRating) {
        
    }
    
    public String highestRated(String cuisine) {
        
    }

    public static void main(String[] args){

        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisine = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};

        FoodRatings solution = new FoodRatings(foods, cuisine, ratings);

        System.out.println("Final Result : ");
        System.out.println("  1st Iteration : " + solution.highestRated("korean") + "\n");          // kimchi
        System.out.println("  2nd Iteration : " + solution.highestRated("japanese") + "\n");        // ramen
        System.out.println("  3rd Iteration : " + solution.changeRating("sushi", 16); + "\n");       
        System.out.println("  4th Iteration : " + solution.highestRated("japanese") + "\n");        // sushi
        System.out.println("  5th Iteration : " + solution.changeRating("ramen", 16); + "\n");       
        System.out.println("  6th Iteration : " + solution.highestRated("japanese") + "\n");        // ramen
        
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. Modify rating of food items
 * 2. Return Highest rated food item
 * 3. FoodRatings(String[] foods, String[] cuisines, int[] ratings)
 *      - Initialize the system 
 *      - food items decribed by 
 *          foods -> is the name of ith food
 *          cuisines -> is the type of cuisine of ith food
 *          ratings -> initial rating of ith food
 * 4. void changeRating(String food, int newRating)
 *      - change the rating of food
 * 5. String highestRated(String cuisine)
 *      - returns the name of food which has highest rating in cuisine
 *      - if ratings are same then return lexicographically ordered first one
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. So I'm thinking of using Hashmap to store <cursion, List<food, rating>>
 *      - see rn I'm a bit confused abt should I use map or list there.. bcoz I need a sorted list when I'll check for highestRating
 * 
 * 1st Appraoch is brute force 
 * 
 * 1. Create a FoodRating Class
 *      String foodName
 *      int rating
 * 
 * 2. FoodRatings(String[] foods, String[] cuisines, int[] ratings)
 *      - if(!cuisine not present in map) -> add it in map
 *      - else add values to those cuisine
 *      - I need to use 3 different array here? first stores cuisine then stores food then stores rating?
 *      - I can do one thing that create this 3 thing as one array
 *      - then try to add it in hashSet
 * 
 *      - see I go it how can I do it
 *      - we know that n == foods.length == cuisines.length == ratings.length
 *      - so me just ekach for loop lavel (i = 0 to n)
 *          - check if cuisine exist in map
 *          - if not then map.add(cuisine[i], new ArraysLis<>)
 *          - if yes then map.get(cuisine[i]).add(new FoodRating {food[i], rating[i]})
 * 
 * 3. void changeRating(String food, int newRating)
 *      - if I used map insead of list it's easy just say .put
 *      - if I used list then we need to use for loop
 *          if(list.get(i).foodName == food) 
 *      - then change it's rating
 *          list.get(i).rating = newRating
 * 
 * 3. String highestRated(String cuisine)
 *      - if(map.contains(cuisine))
 *      - listOfFood = map.get(cuisine)
 *      - mala highest rating haviye tr me ya list la sort karte by highest rating at start
 *          Arrays.sort(listOfFodd, (a, b) -> {
 *              if(a.rating == b.rating) return a.food.compareTo(b.food)      // sort by foodname if ratings are equal
 *              return Integer.compare(b.rating, a.rating)       // Sort by rating descending
 *          })
 *      - String highestRated = listOfFood.get(0)
 *      - return highestRated
 *          
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */