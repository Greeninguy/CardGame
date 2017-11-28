package project3_phelps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Game {
    
    private LinkedList<Card> deck = new LinkedList<Card>();
    private LinkedList<Card> discard = new LinkedList<Card>();
    private ArrayList<Card> userHand = new ArrayList<Card>();
    private ArrayList<Card> computerHand = new ArrayList<Card>();
    
    public Game() {
    }
    
    /**
     * This method fills the deck with a full set of cards.
     */
    public void fillDeck() {
        for (Card.Suits suit: Card.Suits.values()) {
            for (Card.Ranks rank: Card.Ranks.values()) {
                Card card = new Card(suit, rank);
                deck.add(card);
            }
        }
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    public void fillHands() {
        for (int i = 0; i < 4; i++) {
            userHand.add(deck.poll());
            computerHand.add(deck.poll());
        }
    }
    
    public int checkSize() {
        return deck.size();
    }
    
    public void userDrawDeck() {
        userHand.add(deck.poll());
    }
    
    public void computerDrawDeck() {
        computerHand.add(deck.poll());
        System.out.println("drew from deck now hand is:\n" + displayComputerHand());
    }
    
    public void userDrawDiscard() {
        userHand.add(discard.pop());
    }
    
    public void computerDrawDiscard() {
        computerHand.add(discard.pop());
        System.out.println("drew from discard now hand is:\n" + displayComputerHand());
    }
    
    public void userDiscardCard(int i) {
        discard.push((Card)userHand.remove(i));
    }
    
    public void computerDiscardCard(int i) {
        System.out.println("discarded " + computerHand.get(i));
        discard.push((Card)computerHand.remove(i));
    }
    
    /**
     * This method is used to transfer the cards from the discard pile into the
     *      deck.
     * Used for when the deck is out of cards.
     */
    public void resetDeck() {
        int size = discard.size();
        for (int i = 0; i < size; i++) {
            deck.add(discard.pop());
        }
        shuffleDeck();
    }
    
    public Card peekDiscard() {
        return discard.peek();
    }
    
    public String displayUserHand() {
        int i = 0;
        String string = "";
        for (Card card: userHand) {
            string += i + ":" + card.getName() + "\n";
            i++;
        }
        return string;
    }
    
    public String displayComputerHand() {
        int i = 0;
        String string = "";
        for (Card card: computerHand) {
            string += i + ":" + card.getName() + "\n";
            i++;
        }
        return string;
    }
    
    public boolean checkWinner(ArrayList<Card> hand) {
        int matching = 0;
        for (Card.Ranks rank: Card.Ranks.values()) {
            matching = 0;
            for (Card card: hand) {
                if (card.getRank().equals(rank))
                    matching++;
            }
            if (matching == 4) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> getComputerHand() {
        return computerHand;
    }

    public ArrayList<Card> getUserHand() {
        return userHand;
    }
    
    public int checkDeckSize() {
    return deck.size();
    }
    
    public int checkDiscardSize() {
        return discard.size();
    }
}
