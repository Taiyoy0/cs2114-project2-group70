package flashchain;

import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods in the Deck class
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
     * Tests the saveDeck Method
     * 
     * @throws IOException
     */
    public void testSaveDeck()
        throws IOException
    {
        Exception thrown = null;
        try
        {
            deck.saveDeck("");
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IOException);

        File file = new File(deck.getName() + ".txt");
        Card c = new Card("a", "b");
        deck.addCard(c);
        deck.saveDeck(file.getPath());
        Deck newDeck = Deck.loadDeck(file.getPath());
        assertTrue(deck.getName().equals(newDeck.getName()));
        assertTrue(newDeck.getCard(0).toString().equals("a / b"));
    }


    /**
     * Tests the loadDeck Method
     * 
     * @throws IOException
     */
    public void testLoadDeckMethod()
        throws IOException
    {
        Exception thrown = null;
        try
        {
            Deck.loadDeck("");
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IOException);

        File file = new File(deck.getName() + ".txt");
        Card c = new Card("a", "b");
        deck.addCard(c);
        deck.saveDeck(file.getPath());
        Deck newDeck = Deck.loadDeck(file.getPath());
        assertTrue(deck.getName().equals(newDeck.getName()));
        assertTrue(newDeck.getCard(0).toString().equals("a / b"));
    }


    /**
     * Tests the getName method
     */
    public void testGetName()
    {
        assertEquals("name", deck.getName());
    }


    /**
     * Tests the addCard method
     */
    public void testAddCard()
    {
        Card c = new Card("q", "a");
        assertEquals(0, deck.cardCount());
        deck.addCard(c);
        assertEquals(1, deck.cardCount());
    }


    /**
     * Tests the insertCard method
     */
    public void testInsertCard()
    {
        for (int i = 0; i < 3; i++)
        {
            deck.addCard(new Card("", ""));
        }
        Card c = new Card("q", "a");
        deck.insertCard(1, c);
        assertTrue(deck.getCard(1).equals(c));
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
    public void testIterator()
    {
        assertNotNull(deck.iterator());
    }

}
