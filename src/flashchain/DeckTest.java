package flashchain;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods in
 * 
 * @author Joey Gentlesk
 * @version Oct 12, 2026
 */
public class DeckTest
    extends TestCase
{
    private Deck deck;

    public void setUp()
    {
        deck = new Deck("name");
    }


    /**
     * Tests the getName method
     */
    public void testGetName()
    {
        assertEquals("name", deck.getName());
    }


    /**
     * Tests the addCard mehtod
     */
    public void testAddCard()
    {
        Card c = new Card("q", "a");
        assertEquals(0, deck.cardCount());
        deck.addCard(c);
        assertEquals(1, deck.cardCount());
    }


    /**
     * Tests the removeCard
     */
    public void testRemoveCard()
    {
        Card c = new Card("q", "a");
        deck.addCard(c);
        assertEquals(c, deck.removeCard(0));
        assertEquals(0, deck.cardCount());
    }


    /**
     * Tests the getCard Method
     */
    public void testGetCard()
    {
        Card c = new Card("q", "a");
        deck.addCard(c);
        assertEquals(c, deck.getCard(0));
        assertEquals(1, deck.cardCount());
    }


    /**
     * Tests the isEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(deck.isEmpty());
        Card c = new Card("q", "a");
        deck.addCard(c);
        assertFalse(deck.isEmpty());
    }
    
    /**
     * Tests the iterator method
     */
    public void testIterator() {
        assertNotNull(deck.iterator());
    }

}
