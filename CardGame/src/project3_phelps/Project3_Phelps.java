/*
 * Project 3 for CS-1181.
 * This program practices the use of Stack Queues and JavaFX.
 * This program simulates a card game that the user can play againt the computer.
 */
package project3_phelps;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Jimmie Phelps
 * CS1181-01
 * Instructor: M. Cheatham
 * Project 3
 */

/**
 * Application starts a Game, which the player makes the first move.
 * The players takes turn drawing a card from either the deck of the discard pile.
 * Each player discards a card into the discard pile at the end of their turn.
 * The game is over when a player has 4 of a kind in their hand.
 */
public class Project3_Phelps extends Application {
    
    boolean someoneWon = false;
    
    @Override
    public void start(Stage primaryStage) {
        
        //Starts game, and fills deck and player hands with cards
        String userVictory = "Game over: You have won.";
        String computerVictory = "Game over: Computer has won.";
        Game game = new Game();
        Player user = new User();
        Player computer = new Computer();
        game.fillDeck();
        game.shuffleDeck();
        game.fillHands();
        
        //Creation of the main Vertical Box Pane
        VBox mainPane = new VBox();
        mainPane.setSpacing(50);
        
        //Horizontal Box holds the Computer Player's hand
        HBox computerPane = new HBox();
        computerPane.setPadding(new Insets(30, 50, 0, 50));
        computerPane.setSpacing(15);
        Button compCard1 = new Button("Card Back");
        compCard1.setMinSize(150, 200);
        compCard1.setDisable(true);
        Button compCard2 = new Button("Card Back");
        compCard2.setMinSize(150, 200);
        compCard2.setDisable(true);
        Button compCard3 = new Button("Card Back");
        compCard3.setMinSize(150, 200);
        compCard3.setDisable(true);
        Button compCard4 = new Button("Card Back");
        compCard4.setMinSize(150, 200);
        compCard4.setDisable(true);
        Button compCard5 = new Button("Card Back");
        compCard5.setMinSize(150, 200);
        compCard5.setVisible(false);
        compCard5.setDisable(true);
        computerPane.getChildren().addAll(compCard1, compCard2, compCard3, 
                compCard4, compCard5);
        mainPane.getChildren().add(computerPane);
        
        //Horizontal Box holds the Deck, Discard Pile, and the Output Label
        HBox drawDecks = new HBox();
        drawDecks.setPadding(new Insets(50, 150, 50, 100));
        drawDecks.setSpacing(50);
        
        Button deckPile = new Button("Deck");
        deckPile.setMinSize(150, 200);
        
        
        Button discardPile = new Button("Discard Pile");
        discardPile.setMinSize(150, 200);
        discardPile.setVisible(false);
        discardPile.setDisable(true);
        
        Label output = new Label("Start of new Game\nDraw a card from the deck");
        
        drawDecks.getChildren().addAll(deckPile, discardPile, output);
        mainPane.getChildren().add(drawDecks);
        
        //Horizontal Box holds the User's hand
        HBox userPane = new HBox();
        userPane.setPadding(new Insets(0, 50, 30, 50));
        userPane.setSpacing(15);
        
        Button userCard1 = new Button(game.getUserHand().get(0).getName());
        userCard1.setMinSize(150, 200);
        userCard1.setDisable(true);
        Button userCard2 = new Button(game.getUserHand().get(1).getName());
        userCard2.setMinSize(150, 200);
        userCard2.setDisable(true);
        Button userCard3 = new Button(game.getUserHand().get(2).getName());
        userCard3.setMinSize(150, 200);
        userCard3.setDisable(true);
        Button userCard4 = new Button(game.getUserHand().get(3).getName());
        userCard4.setMinSize(150, 200);
        userCard4.setDisable(true);
        Button userCard5 = new Button();
        userCard5.setMinSize(150, 200);
        userCard5.setDisable(true);
        userCard5.setVisible(false);
        userPane.getChildren().addAll(userCard1, userCard2, userCard3, 
                userCard4, userCard5);
        mainPane.getChildren().add(userPane);
        
        //For the rare case that any player starts off with 4 of a kind
        someoneWon = game.checkWinner(game.getUserHand());
        if (someoneWon == true) {
            discardPile.setDisable(true);
            deckPile.setDisable(true);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            output.setText(userVictory);
        }
        someoneWon = game.checkWinner(game.getComputerHand());
        if (someoneWon == true) {
            discardPile.setDisable(true);
            deckPile.setDisable(true);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            compCard1.setText(game.getComputerHand().get(0).getName());
            compCard2.setText(game.getComputerHand().get(1).getName());
            compCard3.setText(game.getComputerHand().get(2).getName());
            compCard4.setText(game.getComputerHand().get(3).getName());
            compCard1.setVisible(true);
            compCard2.setVisible(true);
            compCard3.setVisible(true);
            compCard4.setVisible(true);
            output.setText(computerVictory);
        }
        
        /**
         * User can choose to pick a card from the discard pile at the start of
         *      their turn. 
         * This button is unavailable if the discard pile is empty.
         */
        discardPile.setOnAction(e -> {
            String string = "";
            deckPile.setDisable(true);
            discardPile.setDisable(true);
            
            string += "You drew a " + game.peekDiscard().getName() + 
                    ".\nPick a card to discard.";
            game.userDrawDiscard();
            if (game.checkDiscardSize() == 0)
                discardPile.setVisible(false);
            
            someoneWon = game.checkWinner(game.getUserHand());
            if (someoneWon == true) {
                userCard5.setVisible(true);
                userCard5.setText(game.getUserHand().get(4).getName());
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                output.setText(userVictory);
            }
            else {
                discardPile.setText(game.peekDiscard().getName());
                output.setText(string);
                userCard5.setVisible(true);
                userCard5.setText(game.getUserHand().get(4).getName());
                userCard1.setDisable(false);
                userCard2.setDisable(false);
                userCard3.setDisable(false);
                userCard4.setDisable(false);
                userCard5.setDisable(false);
            }
        });
        
        /**
         * Or the user can also draw a card from the deck at the start of their turn.
         */
        deckPile.setOnAction(e -> {
            String string = "";
            deckPile.setDisable(true);
            discardPile.setDisable(true);
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            game.userDrawDeck();
            
            someoneWon = game.checkWinner(game.getUserHand());
            if (someoneWon == true) {
                userCard5.setVisible(true);
                userCard5.setText(game.getUserHand().get(4).getName());
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                output.setText(userVictory);
            }
            else {
                string += "You drew a " + game.getUserHand().get(4).getName() +
                    ".\nPick a card to discard.";
                output.setText(string);
                userCard5.setVisible(true);
                userCard5.setText(game.getUserHand().get(4).getName());
                userCard1.setDisable(false);
                userCard2.setDisable(false);
                userCard3.setDisable(false);
                userCard4.setDisable(false);
                userCard5.setDisable(false);
            }
        });
        
        /**
         * At the end of the user's turn the player must choose one card from
         *      their hand to put into the discard pile.
         * The computer player makes their turn after the player discards a card.
         * After the computer player is finished it is the start of the user's
         *      turn.
         * Each of the userCard methods have the same function.
         */
        userCard1.setOnAction(e -> {
            userCard5.setVisible(false);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            
            String string = "You discarded " + game.getUserHand().get(0).getName() + ".\n";
            string += "Computer's turn. ";
            output.setText(string);
            
            game.userDiscardCard(0);
            userCard1.setText(game.getUserHand().get(0).getName());
            userCard2.setText(game.getUserHand().get(1).getName());
            userCard3.setText(game.getUserHand().get(2).getName());
            userCard4.setText(game.getUserHand().get(3).getName());
            discardPile.setVisible(true);
            discardPile.setText(game.peekDiscard().getName());
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            int deckSize = game.checkDeckSize();
            computer.move(game);
            someoneWon = game.checkWinner(game.getComputerHand());
            discardPile.setText(game.peekDiscard().getName());
            if (someoneWon == true) {
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                compCard1.setText(game.getComputerHand().get(0).getName());
                compCard2.setText(game.getComputerHand().get(1).getName());
                compCard3.setText(game.getComputerHand().get(2).getName());
                compCard4.setText(game.getComputerHand().get(3).getName());
                compCard1.setVisible(true);
                compCard2.setVisible(true);
                compCard3.setVisible(true);
                compCard4.setVisible(true);
                
                output.setText(computerVictory);
            }
            else {
                if (deckSize == game.checkDeckSize()) 
                    string += "Computer drew from discard pile.\n";
                else
                    string += "Computer drew from the deck.\n";
                
                string += "Computer discarded " + game.peekDiscard().getName() + ".\n";
                discardPile.setDisable(false);
                deckPile.setDisable(false);
                string += "Your turn.\nDraw either from the discard pile or the deck.";
                output.setText(string);
            }
        });
        
        userCard2.setOnAction(e -> {
            userCard5.setVisible(false);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            
            String string = "You discarded " + game.getUserHand().get(1).getName() + ".\n";
            string += "Computer's turn. ";
            output.setText(string);
            
            game.userDiscardCard(1);
            userCard2.setText(game.getUserHand().get(1).getName());
            userCard3.setText(game.getUserHand().get(2).getName());
            userCard4.setText(game.getUserHand().get(3).getName());
            
            discardPile.setVisible(true);
            discardPile.setText(game.peekDiscard().getName());
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            int deckSize = game.checkDeckSize();
            computer.move(game);
            someoneWon = game.checkWinner(game.getComputerHand());
            discardPile.setText(game.peekDiscard().getName());
            if (someoneWon == true) {
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                compCard1.setText(game.getComputerHand().get(0).getName());
                compCard2.setText(game.getComputerHand().get(1).getName());
                compCard3.setText(game.getComputerHand().get(2).getName());
                compCard4.setText(game.getComputerHand().get(3).getName());
                compCard1.setVisible(true);
                compCard2.setVisible(true);
                compCard3.setVisible(true);
                compCard4.setVisible(true);
                output.setText(computerVictory);
            }
            else {
                if (deckSize == game.checkDeckSize()) 
                    string += "Computer drew from discard pile.\n";
                else
                    string += "Computer drew from the deck.\n";
                
                string += "Computer discarded " + game.peekDiscard().getName() + ".\n";
                discardPile.setDisable(false);
                deckPile.setDisable(false);
                string += "Your turn.\nDraw either from the discard pile or the deck.";
                output.setText(string);
            }
        });
        
        userCard3.setOnAction(e -> {
            userCard5.setVisible(false);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            
            String string = "You discarded " + game.getUserHand().get(2).getName() + ".\n";
            string += "Computer's turn. ";
            output.setText(string);
            
            game.userDiscardCard(2);
            userCard3.setText(game.getUserHand().get(2).getName());
            userCard4.setText(game.getUserHand().get(3).getName());
            
            discardPile.setVisible(true);
            discardPile.setText(game.peekDiscard().getName());
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            int deckSize = game.checkDeckSize();
            computer.move(game);
            discardPile.setText(game.peekDiscard().getName());
            someoneWon = game.checkWinner(game.getComputerHand());
            if (someoneWon == true) {
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                compCard1.setText(game.getComputerHand().get(0).getName());
                compCard2.setText(game.getComputerHand().get(1).getName());
                compCard3.setText(game.getComputerHand().get(2).getName());
                compCard4.setText(game.getComputerHand().get(3).getName());
                compCard1.setVisible(true);
                compCard2.setVisible(true);
                compCard3.setVisible(true);
                compCard4.setVisible(true);
                output.setText(computerVictory);
            }
            else {
                if (deckSize == game.checkDeckSize()) 
                    string += "Computer drew from discard pile.\n";
                else
                    string += "Computer drew from the deck.\n";
            
                string += "Computer discarded " + game.peekDiscard().getName() + ".\n";
                discardPile.setDisable(false);
                deckPile.setDisable(false);
                string += "Your turn.\nDraw either from the discard pile or the deck.";
                output.setText(string);
            }
        });
        
        userCard4.setOnAction(e -> {
            userCard5.setVisible(false);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            
            String string = "You discarded " + game.getUserHand().get(3).getName() + ".\n";
            string += "Computer's turn. ";
            output.setText(string);
            
            game.userDiscardCard(3);
            userCard4.setText(game.getUserHand().get(3).getName());
            
            discardPile.setText(game.peekDiscard().getName());
            discardPile.setVisible(true);
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            int deckSize = game.checkDeckSize();
            computer.move(game);
            discardPile.setText(game.peekDiscard().getName());
            someoneWon = game.checkWinner(game.getComputerHand());
            if (someoneWon == true) {
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                compCard1.setText(game.getComputerHand().get(0).getName());
                compCard2.setText(game.getComputerHand().get(1).getName());
                compCard3.setText(game.getComputerHand().get(2).getName());
                compCard4.setText(game.getComputerHand().get(3).getName());
                compCard1.setVisible(true);
                compCard2.setVisible(true);
                compCard3.setVisible(true);
                compCard4.setVisible(true);
                output.setText(computerVictory);
            }
            else {
                
                if (deckSize == game.checkDeckSize()) 
                    string += "Computer drew from discard pile.\n";
                else
                    string += "Computer drew from the deck.\n";
                string += "Computer discarded " + game.peekDiscard().getName() + ".\n";
                discardPile.setDisable(false);
                deckPile.setDisable(false);
                string += "Your turn.\nDraw either from the discard pile or the deck.";
                output.setText(string);
            }
        });
        
        userCard5.setOnAction(e -> {
            userCard5.setVisible(false);
            userCard1.setDisable(true);
            userCard2.setDisable(true);
            userCard3.setDisable(true);
            userCard4.setDisable(true);
            userCard5.setDisable(true);
            
            String string = "You discarded " + game.getUserHand().get(4).getName() + ".\n";
            string += "Computer's turn. ";
            output.setText(string);
            
            game.userDiscardCard(4);
            discardPile.setText(game.peekDiscard().getName());
            discardPile.setVisible(true);
            
            if (game.checkDeckSize() == 0) {
                    game.resetDeck();
                    discardPile.setVisible(false);
                    string += "Deck is empty, putting discards in deck.\n";
                }
            int deckSize = game.checkDeckSize();
            computer.move(game);
            discardPile.setText(game.peekDiscard().getName());
            someoneWon = game.checkWinner(game.getComputerHand());
            if (someoneWon == true) {
                discardPile.setDisable(true);
                deckPile.setDisable(true);
                userCard1.setDisable(true);
                userCard2.setDisable(true);
                userCard3.setDisable(true);
                userCard4.setDisable(true);
                userCard5.setDisable(true);
                compCard1.setText(game.getComputerHand().get(0).getName());
                compCard2.setText(game.getComputerHand().get(1).getName());
                compCard3.setText(game.getComputerHand().get(2).getName());
                compCard4.setText(game.getComputerHand().get(3).getName());
                compCard1.setVisible(true);
                compCard2.setVisible(true);
                compCard3.setVisible(true);
                compCard4.setVisible(true);
                output.setText(computerVictory);
            }
            else {
                if (deckSize == game.checkDeckSize()) 
                    string += "Computer drew from discard pile.\n";
                else
                    string += "Computer drew from the deck.\n";
                    
                string += "Computer discarded " + game.peekDiscard().getName() + ".\n";
                discardPile.setDisable(false);
                deckPile.setDisable(false);
                string += "Your turn.\nDraw either from the discard pile or the deck.";
                output.setText(string);
            }
        });
        
        Scene scene = new Scene(mainPane);
        primaryStage.setTitle("Project 3 - Card Game");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);
        primaryStage.show();
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
