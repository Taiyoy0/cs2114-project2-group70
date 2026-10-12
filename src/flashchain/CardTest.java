package flashchain;

public class CardTest extends student.TestCase {
    
    private Card card;
    
    public void setUp() {
        card = new Card("2+2=", "4");
    }
    
    public void testGetFront() {
        assertEquals("2+2=", card.getFront());
    }
    
    public void testGetBack() {
        assertEquals("4", card.getBack());
    }
    
    public void testEquals() {
        Card card2 = new Card("2+2=", "4");
        Card card3 = new Card("2+2=", "5");
        Card card4 = new Card("1+2=", "4");
        
        assertTrue(card.equals(card));
        assertFalse(card.equals(null));
        assertFalse(card.equals("This is a string"));
        assertTrue(card.equals(card2));
        assertFalse(card.equals(card3));
        assertFalse(card.equals(card4));
    }
}
