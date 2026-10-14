package flashchain;

import static org.junit.Assert.*;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author michaela
 *  @version Oct 14, 2026
 */
public class LinkedChainTest
{
    private LinkedChain<String> test;
    
    // ----------------------------------------------------------
    /**
     * creates a test chain
     */
    public void setUp() {
        test = new LinkedChain<String>();
        test.add("1");
        test.add("2");
    }

    // ----------------------------------------------------------
    /**
     * test the addAtIndex method
     */
    @Test
    public void testAddAtIndex()
    {
        test.addAtIndex("mid", 1);
        assertEquals("[2, mid, 1]", test.toString());
        
        test.addAtIndex("start", 0);
        assertEquals("[start, 2, mid, 1]", test.toString());
        
        test.addAtIndex("end", 4);
        assertEquals("[start, 2, mid, 1, end]", test.toString());
        
        test.addAtIndex("mid2", 3);
        assertEquals("[start, 2, mid, mid2, 1, end]", test.toString());
    
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

}
