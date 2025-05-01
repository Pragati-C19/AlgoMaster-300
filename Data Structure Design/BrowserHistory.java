import java.util.*;

public class BrowserHistory {
    
    // ListNide Struct for Doubly LinkedList
    private static class ListNode {
        String val;
        ListNode prev;
        ListNode next;
        ListNode child;

        ListNode(String val) {
            this.val = val;
        }
    }

    // Globally Declare Variables
    ListNode head;
    ListNode currPage;

    public BrowserHistory(String homepage) {
        
        head = new ListNode(homepage);
        head.prev = null;
        head.next = null;

        currPage = head;
        System.out.println("  Initial CurrPage is " + currPage.val);
    }
    
    public void visit(String url) {
        
        ListNode visitURL = new ListNode(url);
        visitURL.prev = currPage;
        visitURL.next = null;

        currPage = visitURL; 
        System.out.println("    -> Updated CurrPage is " + currPage.val);

        return; 
    }
    
    public String back(int steps) {
        
        while (steps > 0) {

            if (currPage.prev == null) {
                return currPage.val;
            }
            
            currPage = currPage.prev;
            steps--;    
        }

        return currPage.val;
    }
    
    public String forward(int steps) {
        
        return currPage.val;
    }

    public static void main(String[] args){

        String homepage = "leetcode.com";
        
        BrowserHistory solution = new BrowserHistory(homepage);

        System.out.println("Final Result : ");
        System.out.println("  1st Iteration adding google.com"); 
        solution.visit("google.com");
        System.out.println("  2nd Iteration adding facebook.com"); 
        solution.visit("facebook.com");
        System.out.println("  3rd Iteration adding youtube.com"); 
        solution.visit("youtube.com");
      
        System.out.println("  4th Iteration : " + solution.back(1));
        System.out.println("  5th Iteration : " + solution.back(1)); 
        System.out.println("  6th Iteration : " + solution.forward(1)); 

        System.out.println("  7th Iteration adding linkedin.com");
        solution.visit("linkedin.com");

        System.out.println("  8th Iteration : " + solution.forward(2)); 
        System.out.println("  9th Iteration : " + solution.back(2)); 
        System.out.println("  10th Iteration : " + solution.back(7)); 

    }
}

/*
 * 
 * Intutions :
 * 
 * 1. we are creating a browing history
 * 2. BrowserHistory(string homepage) Initializes the object with the homepage
 * 3. void visit(string url) Visits url from the current page.
 * 4. string back(int steps) Move steps back in history. 
 * 5. string forward(int steps) Move steps forward in history
 * 6. few things u need to know
 *      - jevha pn back of forward hoil tevha current Page je asel tihun hoil
 * 7. Example 
 *      jr mazyakde history ahe [leetcode, google, facebook, youtube]
 *      currPage = youtube
 *      then me jevha back(1) mhnle 
 *          tevha ans facebook return karel 
 *          update currPage = facebook
 *      then me linkedList add keli new 
 *          tr first currPage chya pudhchi sagli history delete hoil like youtube val nighun jail
 *          then string connect hoil currPage la which is facebook 
 *          update currPage = linkedList
 * 8. I think we need to use doubly linked list      
 * 
 * 
 * Pattern :
 * 
 * 1. ListNode Struct -> for doubly linked list prev val next
 * 2. Globally Declare
 *      ListNode head
 *      currPage
 * 2. BrowserHistory(homepage)
 *      - Store homepage in head of linkedList
 *          head = new ListNode(homepage)
 *      - assign value to head prev and next
 *          head.prev = null
 *          head.next = null
 *      - currPage = head
 * 3. visit(url)
 *      - visit = new ListNode(url)
 *      - visit.prev = currPage
 *      - visit.next = null
 *      - currPage = visit
 * 4. back(steps)
 *      - while(steps > 0)
 *          if(currPage.prev == null) return currPage.val
 *          currPage = currPage.prev
 *          steps--
 *      - return currPage.val
 * 5. forward(steps)
 *      - while(steps > 0)
 *          if(currPage.next == null) return currPage.val
 *          currPage = currPage.next
 *          steps--
 *      - return currPage.val
 * 
 * 
 * 
 * 
 */