/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3_phelps;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phelp
 */
public class CardDeckIT {
    
    public CardDeckIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of shuffleDeck method, of class CardDeck.
     */
    @Test
    public void testShuffleDeck() {
        System.out.println("Test shuffleDeck");
        CardDeck instance = new CardDeck();
        instance.fillDeck();
        instance.soutDeck();
        System.out.println("Deck Made, now Shuffling");
        instance.shuffleDeck();
        System.out.println("deck shuffled now displaying");
        instance.soutDeck();
        System.out.println("");
        System.out.println("End of shuffleDeck test");
    }

    /**
     * Test of fillDeck method, of class CardDeck.
     */
    @Test
    public void testFillDeck() {
        System.out.println("Test fillDeck");
        CardDeck instance = new CardDeck();
        instance.fillDeck();
        instance.soutDeck();
        System.out.println("End of fillDeck Test");
    }

    /**
     * Test of takeDiscard method, of class CardDeck.
     */
    @Test
    public void testTakeDiscard() {
        System.out.println("takeDiscard");
        LinkedList discard = null;
        CardDeck instance = new CardDeck();
        instance.takeDiscard(discard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawCard method, of class CardDeck.
     */
    @Test
    public void testDrawCard() {
        System.out.println("drawCard");
        CardDeck instance = new CardDeck();
        Card expResult = null;
        Card result = instance.drawCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of soutDeck method, of class CardDeck.
     */
    @Test
    public void testSoutDeck() {
        System.out.println("soutDeck");
        CardDeck instance = new CardDeck();
        instance.soutDeck();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSize method, of class CardDeck.
     */
    @Test
    public void testCheckSize() {
        System.out.println("checkSize");
        CardDeck instance = new CardDeck();
        int expResult = 0;
        int result = instance.checkSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
