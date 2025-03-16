public class DeckRevealedIncreasing {

}

/*
 * 
 * Intuitions :
 * 
 * 1. Goal is to reveal the cards in increasing order
 * 2. but the revealing process is tricky because of the
 * "reveal one, move one to the bottom" pattern.
 * 
 * 3. Start with an empty deck.
 * 4. Place the largest card first.
 * 5. For every remaining card (from second largest to smallest):
 * - Move the current deck's last card to the front (simulate the reverse of the
 * second step).
 * - Place the next card at the front.
 * 6. Repeat until all cards are placed.
 * 
 * 
 * Pattern :
 * 
 * For deck = [17,13,11,2,3,5,7], sorted becomes [2,3,5,7,11,13,17].
 * 
 * Reverse building the deck:
 * 
 * Start with the largest card 17.
 * Move 17 to front and add 13 → [13, 17].
 * Move 17 to front and add 11 → [11, 17, 13].
 * Move 13 to front and add 7 → [7, 13, 11, 17].
 * Move 17 to front and add 5 → [5, 17, 7, 13, 11].
 * Move 11 to front and add 3 → [3, 11, 5, 17, 7, 13].
 * Move 13 to front and add 2 → [2, 13, 3, 11, 5, 17, 7]
 * 
 * 
 * Psuedo Code :
 * 
 * 1. Sort deck in ascending order.
 * 2. Create an empty queue for results.
 * 3. Add the largest card to the result deck.
 * 4. For each remaining card (2nd largest to smallest):
 * a) Move the last card in the deck to the front.
 * b) Add the current card to the front.
 * 5. Return the result deck.
 * 
 * 
 */