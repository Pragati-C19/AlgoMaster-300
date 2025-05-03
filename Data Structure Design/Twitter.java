import java.util.*;

public class Twitter {
    
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
 * 2. jr me ek followerList val array banavla for each user
 *      - and tyat tyanche followers cha track thevla
 *      - it's like followerMap<userID, List<followers>>
 *      - ohk using List<Integer> can add duplicates I don't want that so let's change it do hashset
 *      - followersMap<userID, Set<followers>>
 * 3. void follow(int followerId, int followeeId)
 *      - followerMap.get(followerId).add(followeeID)
 * 4. void unfollow(int followerId, int followeeId)
 *      - followerMap.get(followerId).remove(followeeID)
 * 
 * 
 * 
 * Pseudo code :
 * 
 * 
 * 
 * 
 */