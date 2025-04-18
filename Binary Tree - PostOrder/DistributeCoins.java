import java.util.*;

public class DistributeCoins {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Binarry tree is given with N nodes
 * 2. node.val represent number of coins at that node
 * 3. u can only move 1 coins in one move to ur adjacent node
 * 4. u can move coins from parent to child or child to parent
 * 5. Task is to find number of moves required to make every node.val is 1 by
 * moving coins
 * 
 * Things I understand :
 * 1. when (node.val == 1) stop the code and go for next
 * 2. when (node.val > 1) move 1 coin to child node
 * 3. when (node.val < 1) move 1 coin to parent node
 * 4. keep doing this until all nodes have 1 coin
 * 5. return the total number of moves
 * 
 * 
 * Pattern :
 * 
 * 1. Base Case : if(node == null) return 0;
 * 2. If node.val == 1 return 0;
 * 3. Recure to leftSubtree
 * 4. Recure to rightSubtree
 * 5. Visit Node :
 * - Count total coins and then check extra coins
 *      -> left + right + node.val = total coins
 *      -> total coins - 1 = extra coins
 * - moves =+ left + right
 *      -> also here addition will be absolute
 *      -> karan jr left kade 0 ahe node.val then tikdun extra coins -1 yetil ani
 *          moves madhe aplyala addition karaychiye substract nahi extra coins and total
 *          madhe -1 will work but moves madhe nahi so use absolute
 *      -> extra coins kaskay ale asel node kade? left and right ni move kele mhnun na
 *          ani jitke extra coins titke moves zale astil te denyat)
 * - return extra coins = give extra coins to parent node (even if it's
 * positive or negative)
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * // Globally declare move variable
 * minMoves;
 * 
 * function int distributeCoins(root){
 * 
 *      minMoves = 0;
 * 
 *      postOrder(root);
 * 
 *      return minMoves;
 *      
 * }
 * 
 * 
 * function postOrder(node){
 * 
 *      // Base Case : if node == null return 0 extra coins return
 *      if(node == null) return 0
 * 
 *      // Recur to left side
 *      leftSubtree = postOrder(root.left)
 * 
 *      // Recur to right side
 *      rightSubtree = postOrder(root.right)
 * 
 *      // Check total Coins 
 *      totalCoins = node.val + leftSubtree + rightSubtree
 * 
 *      // Check how many extra coin it has (it can be positive or negative)
 *      extraCoins = totalCoins - 1
 * 
 *      // Moves it takes to move those coins from node to node (for here will only check child to parent, -1 will do it's job for parent to child)
 *      minMoves =+ leftSubtree + rightSubtree
 * 
 *      return extraCoins
 * 
 * }
 * 
 */