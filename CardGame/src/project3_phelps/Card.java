package project3_phelps;


public class Card {
    public enum Ranks {
        Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
    }
    
    public enum Suits {
        Clubs, Diamonds, Hearts, Spades;
    }
    
    private Suits suit;
    private Ranks rank;
    private String name;

    public Card() {
    }
    
    public Card (Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
        this.name = this.rank + " of " + this.suit;
    }

    public Suits getSuit() {
        return suit;
    }

    public Ranks getRank() {
        return rank;
    }
    
    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
