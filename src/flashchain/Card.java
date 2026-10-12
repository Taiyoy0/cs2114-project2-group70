package flashchain;

// -------------------------------------------------------------------------
/**
 * creates a card class that contains a front question and aback answer
 * 
 * @author michaela
 * @version Oct 12, 2026
 */
public class Card
{
    // ~ Fields ................................................................
    private String front;
    private String back;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Card object.
     * 
     * @param question
     * @param answer
     */
    public Card(String question, String answer)
    {

        front = question;
        back = answer;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * getter for front question on card
     * 
     * @return front
     */
    public String getFront()
    {
        return front;
    }


    // ----------------------------------------------------------
    /**
     * getter for back answer on card
     * 
     * @return back
     */
    public String getBack()
    {
        return back;
    }


    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object.getClass() != this.getClass())
        {
            return false;
        }
        Card thing = (Card)object;
        if (thing.getFront() == this.getFront()
            && thing.getBack() == this.getBack())
        {
            return true;
        }
        return false;
    }


    public String toString()
    {
        return front + " / " + back;
    }
}
