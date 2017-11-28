package project3_phelps;

import java.util.Scanner;


public class User extends Player{
    
    public User() {
    }
    
    public String toString(String cards) {
        return cards;
    }

    /**
     * Move method for the player used during this program's prior version
     * This method is not used as the JavaFX application replaces it's functions
     * @param game 
     */
    @Override
    public void move(Game game) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Your hand consists of:\n" + game.displayUserHand());
        
        System.out.println("Will you draw from: 1.Deck 2.Discard");
        int choice = keyboard.nextInt();
        if (choice == 1) 
            game.userDrawDeck();
        else
            game.userDrawDiscard();
            
        System.out.println("Your hand now consists of:\n" + game.displayUserHand());
        game.displayUserHand();
        
        System.out.println("Which card will you discard?");
        choice = keyboard.nextInt();
        game.userDiscardCard(choice);
    }
    
}
    
