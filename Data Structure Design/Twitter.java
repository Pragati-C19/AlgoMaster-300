import java.util.*;

public class Twitter {
    
    private static class TweetTime {
        
        int tweetId;
        int globalTime;

        TweetTime(int tweetId, int globalTime){
            this.tweetId = tweetId;
            this.globalTime = globalTime;
        }

        @Override
        public String toString() {
            return "(" + tweetId + ", " + globalTime + ")";
        }
    }

    // Globally Declare Variable 
    Map<Integer, Set<Integer>> followersMap;
    Map<Integer, List<TweetTime>> tweetMap;
    int globalTime;

    public Twitter() {
        
        // Assign values to global variables
        followersMap = new HashMap<>();
        tweetMap = new HashMap<>();
        globalTime = 0;

    }
    
    public void postTweet(int userId, int tweetId) {
        
        // add userId and tweetId to tweetMap
        if (!tweetMap.containsKey(userId)) {
            tweetMap.put(userId, new ArrayList<>());
        }
        tweetMap.get(userId).add(new TweetTime(tweetId, globalTime));
        System.out.println("    -> TweetMap : " + tweetMap);

        // add userId in it's followerList to get feed easily later
        if (!followersMap.containsKey(userId)) {
            followersMap.put(userId, new HashSet<>());
        }
        followersMap.get(userId).add(userId);
        System.out.println("    -> FollowerMap : " + followersMap);

        globalTime++;

        return;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        
        PriorityQueue<TweetTime> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.globalTime, b.globalTime));

        for (Integer followerId : followersMap.get(userId)) {
            
            List<TweetTime> tweetList = tweetMap.get(followerId);
            System.out.println("    -> getting news feed for " + userId + "'s follower " + followerId + " : " + tweetList);

            for (int i = 0; i < tweetList.size(); i++) {
                
                minHeap.add(tweetList.get(i));
                System.out.println("      -> updated minHeap " + minHeap);

                if (minHeap.size() > 10) {
                    minHeap.poll();
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            
            TweetTime newsFeed = minHeap.poll();
            System.out.println("    -> news Feed : " + newsFeed);

            result.add(newsFeed.tweetId);
        }

        return result;

    }
    
    public void follow(int followerId, int followeeId) {
        
        followersMap.get(followerId).add(followeeId);
        System.out.println("    -> Adding followee in FollowerMap : " + followersMap);

        return;
    }
    
    public void unfollow(int followerId, int followeeId) {
        
        followersMap.get(followerId).remove(followeeId);
        System.out.println("    -> Removing followee from FollowerMap : " + followersMap);

        return;
    }

    public static void main(String[] args){

        Twitter solution = new Twitter();

        System.out.println("Final Result : ");

        solution.postTweet(1, 5);
        System.out.println("  1st Iteration \n");

        System.out.println("  2nd Iteration : " + solution.getNewsFeed(1) + "\n");    // 1

        solution.follow(1, 2);
        System.out.println("  3rd Iteration \n");

        solution.postTweet(2, 6);
        System.out.println("  4th Iteration \n");

        System.out.println("  5th Iteration : " + solution.getNewsFeed(1) + "\n");    // 1

        solution.unfollow(1, 2);
        System.out.println("  6th Iteration \n");

        System.out.println("  7th Iteration : " + solution.getNewsFeed(1) + "\n");    // 1

    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Design a simplified version of twitter
 * 2. Twitter() -> initialize the objects
 * 3. void postTweet(int userId, int tweetId) ->
 *      - composes a new tweet with id tweetId for that userID
 *      - tweetID will be unique
 *      - I think to store this i can use hashmap or treemap where key is tweetID and value is userID
 * 4. List<Integer> getNewsFeed(int userId)
 *      - get 10 most recents tweetID's in the user's news feed
 *      - each item in the news feed must be posted by the users who the user followed or by user themself
 *      - tweets must be order from most recent to leaset recent
 * 5. void follow(int followerId, int followeeId)
 *      - followerID starting to folllow followeeID
 *      - maybe it's like adding id in that userID's followerList
 * 6. void unfollow(int followerId, int followeeId)
 *      - followerID starting to unfolllow followeeID
 *      - remove that id from it's followerList
 * 
 * 
 * Pattern :
 * 
 * Brute force thinking
 * 1. I want to store 2 thins for 1 user that is {tweets and followers}
 * 
 * 2. jr me ek followerList val array banavla for each user
 *      - BASE CASE : if followerID == FolloweeID then return
 *      - and tyat tyanche followers cha track thevla
 *      - it's like followerMap<userID, List<followers>>
 *      - ohk using List<Integer> can add duplicates I don't want that so let's change it do hashset
 *      - followersMap<userID, Set<followers>>
 * 
 * 4. jr mala feeds baghayche astil users che think abt all first
 *      - to store userID and followers mala vatal I need a map<UserID, List<tweetID>>
 *      - jevha me postTweet mhnel.. tevha me to tweet tyach id sobt tyala je follow kartay tyanchya List madhe pn add karel
 *      - mg jevha get news karel tevha tya userchya list la reverse order madhe return karel till length 10
 *      - nahitr queue madhe pn add karel to for most recent.. heap madhe fact 10 add hotil jr sixe 10 chya vr geli tr FIFO method ni jo first gelela to kadhun takel me.. 
 *      - jevha mala getTweets karayche asel tevha me queue check karel.. tyatle 10 deil but before that me check karel ki it's for this user or not.. 
 *
 * 5. he varchi thinking right ahe but yat ek gost add karan is better -> Time
 *      - minHeap banav jo by Time sort hoil
 *      - tweets sobt tweets cha post time add kar tweetMap<userID, List<Tweet, Time>>
 *      - postTweeets madhe fact userId chya List<Tweet, Time> madhe tweets and tyaveles cha time add karaycha
 *      - jevha getnews karaychi asel tevha
 *          self vale tweets adhi add kr tweetsMap madhun in heap 
 *          minHeap automatically sort karel minTime vale varti
 *          then check karayche if that user follows anyone 
 *          and add those tweets in heap too
 *          heap chi size 10 chya vr geli tr 
 *         
 *          
 * 
 * 
 * 
 * Pseudo code :
 * 
 * 1. Tweet Class -> to store tweet and time in teetMap
 *       - int tweetID
 *       - int globalTime
 * 
 * 2. Globally Declare variables
 *      - tweetMap<Integer, List<Tweet>>
 *      - followerMap<Integer, Set<Integer>>
 *      - globalTime 
 * 
 * 3. Twitter() 
 *      - tweetMap = new hashmap
 *      - followerMap = new hashmap
 *      - globalTime = 0
 * 
 * 4. void postTweet(int userId, int tweetId)
 *      - if(!tweetMap.containsKey(userId)) 
 *          tweetMap.put(userId, new ArrayList)
 * 
 *      - tweetMap.get(userId).add(new Tweet(tweetId, globalTime))
 *    
 *  -> Initially add userItself in that follow list, I know que says A user cannot follow himself. 
 *      but we are not doing it by follow unfollow commad.. we are normally adding it at start.. 
 *      it will be easy when we try to show tweets
 *      - if(!followMap.containsKey(userId)) 
 *          followMap.put(userId, new Hashset)
 *      - followMap.get(userId).add(userId)
 *      
 *      - time++
 * 
 * 
 * 5. List<Integer> getNewsFeed(int userId)
 *      - create a minHeap sorting by globalTime
 * 
 *      - Check if there any followerId for that userId?
 *          if(followerId : followMap.get(userId))
 * 
 *      - if yes then take tweets of that followerId add it in minHeap
 *          List<Tweet> tweetList = tweetMap.get(followerId)
 *          - for(i = 0 to tweetList.size)
 *              minHeap.add(tweetList(i))
 *              if(minHeap.size > 10)
 *                  minHeap.poll
 * 
 *      - List<Integer> result
 *      - while(minHeap.isEmpty)
 *          newsFeed = minHeap.poll()
 *          result.add(newsFeed.tweetId)
 *      - return result   
 * 
 * 6. void follow(int followerId, int followeeId)
 *      - followerMap.get(followerId).add(followeeID)
 * 
 * 7. void unfollow(int followerId, int followeeId)
 *      - followerMap.get(followerId).remove(followeeID)
 * 
 * 
 */