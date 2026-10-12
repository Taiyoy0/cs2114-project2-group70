package flashchain;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Library methods
 * 
 * @author Joey Gentlesk
 * @version Oct 12, 2026
 */
public class LibraryTest
    extends TestCase
{
    private Library lib;
    
    /**
     * Sets up the fields for each method test
     */
    public void setUp() {
        lib = new Library();
    }
    
    /**
     * Tests the addDeck method
     */
    public void testAddDeck() {
        Deck d = new Deck("name");
        lib.addDeck(d);
        assertEquals(1,lib.deckCount());
    }
    
    /**
     * Tests the removeDeck method
     */
    public void testRemoveDeck() {
        Deck d = new Deck("name");
        lib.addDeck(d);
        assertEquals(1,lib.deckCount());
        assertTrue(lib.removeDeck("name"));
    }
    
    /**
     * Tests the findDeck method
     */
    public void testFindDeck() {
        Deck d = new Deck("name");
        lib.addDeck(d);
        assertEquals(d,lib.findDeck("name"));
        assertNull(lib.findDeck("test"));
    }
    
    /**
     * Tests the listDeckNames method
     */
    public void testListDeckNames() {
        Deck d = new Deck("name");
        lib.addDeck(d);
        String[] test = new String[lib.deckCount()];
        test[0] = "name";
        assertTrue(test.equals(lib.listDeckNames()));
    }
    
    /**
     * Tests the deckCount method
     */
    public void testDeckCount() {
        assertEquals(0,lib.deckCount());
        Deck d = new Deck("name");
        lib.addDeck(d);
        assertEquals(1,lib.deckCount());
    }
    
    /**
     * Tests the iterator method
     */
    public void testIterator() {
        assertNotNull(lib.iterator());
    }
}
