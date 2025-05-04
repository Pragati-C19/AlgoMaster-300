import java.util.*;

public class FoodRatings {
    
    private static class FoodList implements Comparable<FoodList>{

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

        // Added compare statement in class itself for TreeSet

        public int compareTo(FoodList other) {
            // Sort by descending rating, then ascending name
            if (this.rating != other.rating)
                return Integer.compare(other.rating, this.rating);
            return this.foodName.compareTo(other.foodName);
        }
    }
    
    // Gloablly Declare Variable
    /*
        foodToCuisine -> which can tell food's cuisine
        foodToRating -> which can tell food's rating
        cuisineMap -> it stores cuisine as key and TreeSet<FoodList> as value
    */ 
        
    Map<String, TreeSet<FoodList>> cuisineMap;
    Map<String, Integer> foodToRatingMap;
    Map<String, String> foodToCuisineMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        
        int n = foods.length;

        // Initialize the map
        cuisineMap = new HashMap<>(); 
        foodToRatingMap = new HashMap<>();
        foodToCuisineMap = new HashMap<>();

        // now add value in it
        for (int i = 0; i < n; i++) {
            
            if (!cuisineMap.containsKey(cuisines[i])) {
                cuisineMap.put(cuisines[i], new TreeSet<>());
            }

            cuisineMap.get(cuisines[i]).add(new FoodList(foods[i], ratings[i]));

            foodToCuisineMap.put(foods[i], cuisines[i]);
            foodToRatingMap.put(foods[i], ratings[i]);
        }

        System.out.println("CuisineMap: " + cuisineMap);
        System.out.println("FoodToRatingMap: " + foodToRatingMap);
        System.out.println("FoodToCuisineMap: " + foodToCuisineMap);

    }
    
    public void changeRating(String food, int newRating) {
        
        
        return;
    }
    
    public String highestRated(String cuisine) {
        
        TreeSet<FoodList> foodListFromCuisine = cuisineMap.get(cuisine);
        System.out.println("    -> Food List for specific cuisine is " + foodListFromCuisine);
        
        String highestRatedFoodInCuisine = foodListFromCuisine.first().foodName;
        
        return highestRatedFoodInCuisine;
    }

    public static void main(String[] args){

        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisine = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};

        FoodRatings solution = new FoodRatings(foods, cuisine, ratings);

        System.out.println("\nFinal Result : ");
        System.out.println("  1st Iteration : " + solution.highestRated("korean") + "\n");          // kimchi
        System.out.println("  2nd Iteration : " + solution.highestRated("japanese") + "\n");        // ramen
        solution.changeRating("sushi", 16);
        System.out.println("  3rd Iteration... \n");       
        System.out.println("  4th Iteration : " + solution.highestRated("japanese") + "\n");        // sushi
        solution.changeRating("ramen", 16);
        System.out.println("  5th Iteration... \n");       
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
 * Improvements :
 *     
 * 1. When I tried to do changeRating that time I realized that it's not easy to find food without knowing it's cursine
 * 2. Let's use a new map which will store food and rating
 * 3. If I do above then it will give problem in HighestRating
 * 
 * 4. So I need 4 Maps
 *      foodToCuisine -> which can tell food's cuisine
 *      foodToRating -> which can tell food's rating
 *      cuisineMap -> it stores cuisine as key and TreeSet<FooList> as value
 *      
 * 
 *      
 * Pseudo Code :
 * 
 * 
 * 
 */