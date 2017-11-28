package project3_phelps;


public class Computer extends Player {
    
    private Card.Ranks favRank1;
    private Card.Ranks favRank2;
    private Card.Ranks favRank3;
    
    public Computer() {
    }

    public Card.Ranks getFavRank1() {
        return favRank1;
    }

    public Card.Ranks getFavRank2() {
        return favRank2;
    }

    public Card.Ranks getFavRank3() {
        return favRank3;
    }
    
    /**
     * This method is used by the Computer Player's Move method.
     * Method checks the Computer Player's hand for any matching cards
     * @param game Game class is used to access the Computer's hand.
     */
    public void checkMatching(Game game) {
        favRank1 = null;
        favRank2 = null;
        favRank3 = null;
        int pairs = 0;
        int matches = 0;
        for (Card.Ranks rank: Card.Ranks.values()) {
            matches = 0;
            for (Card cards: game.getComputerHand()) {
                if (cards.getRank().equals(rank))
                    matches++;
            }
            if (matches == 2 && pairs == 1){
                this.favRank2 = rank;
                break;
            }
            if (matches == 2 && pairs == 0) {
                pairs = 1;
                this.favRank1 = rank;
            }
            if (matches == 3) {
                this.favRank3 = rank;
            }
            
        }
        System.out.println("fav1: " + favRank1 + 
                "\nfav2: " + favRank2 + "\nfav3: " + favRank3);
    }
    
    /**
     * Move method decides what course of action the Computer Player takes for 
     *      their turn.
     * First determines if a card deck or the discard pile is taken
     * Second determines which card to discard from their hand.
     * The matching cards in the Computer Player's hand determines it's actions.
     * @param game Game class is used to access the Computer Player's hand as 
     *      well as the discard pile's showing card.
     */
    @Override
    public void move(Game game) {
        boolean matching = false;
        checkMatching(game);
        if (game.checkDiscardSize() != 0) {
            if (favRank1 == null && favRank2 == null && favRank3 == null) {
                Card.Ranks rank = game.peekDiscard().getRank();
                for (Card card: game.getComputerHand()) {
                    if (card.getRank() == rank)
                        matching = true;
                }
                if (matching == true) {
                    game.computerDrawDiscard();
                    checkMatching(game);
                    discardNotMatching1(game);
                }
                else {
                    game.computerDrawDeck();
                    checkMatching(game);
                    if (favRank1 != null) {
                        discardNotMatching1(game);
                    }
                    else
                        game.computerDiscardCard(0);
                }
            }
            else if (favRank1 != null && favRank2 == null) {
                matching  = false;
                Card.Ranks rank = game.peekDiscard().getRank();
                for (Card card: game.getComputerHand()) {
                    if (card.getRank() == rank)
                        matching = true;
                }
                if (matching == true) {
                    game.computerDrawDiscard();
                    checkMatching(game);
                    if (favRank3 != null)
                        discardNotMatching3(game);
                    else if (favRank2 != null) {
                        discardNotMatching2(game);
                    }
                }
                else {
                    game.computerDrawDeck();
                    checkMatching(game);
                    if (favRank3 != null) {
                        discardNotMatching3(game);
                    }
                    else if (favRank2 != null) {
                        discardNotMatching2(game);
                    }
                    else {
                        discardNotMatching1(game);
                    }
                }
            }
            else if (favRank1 != null && favRank2 != null) {
                matching = false;
                Card.Ranks rank = game.peekDiscard().getRank();
                for (Card card: game.getComputerHand()) {
                    if (card.getRank() == rank)
                        matching = true;
                }
                if (matching == true) {
                    game.computerDrawDiscard();
                    checkMatching(game);
                    discardNotMatching3(game);
                }
                else {
                    game.computerDrawDeck();
                    checkMatching(game);
                    if (favRank3 != null) 
                        discardNotMatching3(game);
                    else
                        discardNotMatching2(game);
                }
            }
            else {
                Card.Ranks rank = game.peekDiscard().getRank();
                if (rank == favRank3) {
                    game.computerDrawDiscard();
                    discardNotMatching3(game);
                }
                    
                else {
                    game.computerDrawDeck();
                    discardNotMatching3(game);
                }
            }
        }
        else {
            if (favRank1 == null && favRank2 == null && favRank3 == null) {
                game.computerDrawDeck();
                checkMatching(game);
                    if (favRank1 != null) {
                        discardNotMatching1(game);
                    }
                    else
                        game.computerDiscardCard(0);
            }
            else if (favRank1 != null && favRank2 == null) {
                game.computerDrawDeck();
                checkMatching(game);
                if (favRank3 != null) {
                    discardNotMatching3(game);
                }
                else if (favRank2 != null) {
                    discardNotMatching2(game);
                }
                else {
                    discardNotMatching1(game);
                }
            }
            else if (favRank1 != null && favRank2 != null) {
                game.computerDrawDeck();
                checkMatching(game);
                if (favRank3 != null) 
                    discardNotMatching3(game);
                else
                    discardNotMatching2(game);
            }
            else {
                game.computerDrawDeck();
                discardNotMatching3(game);
            }
        }
    }
    
    /**
     * These three discard methods determine which card the Computer Player
     *      discards at the end of their turn.
     * Which card it discards is determined by how many matching cards it 
     *      has in it's hand.
     * @param game 
     */
    public void discardNotMatching1(Game game) {
        for (int i = 0; i < game.getComputerHand().size(); i++) {
            if (game.getComputerHand().get(i).getRank() != favRank1) {
                game.computerDiscardCard(i);
                break;
            }
        }
    }
    
    public void discardNotMatching3(Game game) {
        for (int i = 0; i < game.getComputerHand().size(); i++) {
            if (game.getComputerHand().get(i).getRank() != favRank3) {
                game.computerDiscardCard(i);
                break;
            }
        }
    }
    
    public void discardNotMatching2(Game game) {
        for (int i = 0; i < game.getComputerHand().size(); i++) {
            if (game.getComputerHand().get(i).getRank() != favRank1 && 
                    game.getComputerHand().get(i).getRank() != favRank2) {
                game.computerDiscardCard(i);
                break;
            }
        }
    }
}
