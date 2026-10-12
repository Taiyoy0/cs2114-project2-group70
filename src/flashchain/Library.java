package flashchain;

import java.util.Iterator;

// Challenge Exercise 06 fall 2026
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Joey Gentlesk(jgentlesk)
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
// -------------------------------------------------------------------------
/**
 * The Library Class stores a LinkedChain of Deck objects
 *
 * @author Joey Gentlesk
 * @version Oct 12, 2026
 */
public class Library
{
    private LinkedChain<Deck> linkedDeck;

    /**
     * Builds an Empty library
     */
    public Library()
    {
        linkedDeck = new LinkedChain<Deck>();
    }


    /**
     * Adds the new deck to the library
     * 
     * @param d
     *            The deck being appended
     */
    public void addDeck(Deck d)
    {
        linkedDeck.add(d);
    }


    /**
     * Removes a deck from the chain and confirms if it was removed
     * 
     * @param name
     *            the name of the deck being removed
     * @return true if the deck was removed
     */
    public boolean removeDeck(String name)
    {
        return linkedDeck.remove(findDeck(name));
    }


    /**
     * finds the deck in the library
     * 
     * @param name
     *            name of the deck
     * @return the deck from the library without removing it
     */
    public Deck findDeck(String name)
    {
        String[] names = listDeckNames();
        int index = 0;
        for (String i : names)
        {
            if (i.equals(name))
            {
                return linkedDeck.get(index);
            }
            index++;
        }
        return null;
    }


    /**
     * Makes an array of the deck names
     * 
     * @return array of deck names in order of creation
     */
    public String[] listDeckNames()
    {
        Deck[] temp = new Deck[linkedDeck.size()];
        temp = linkedDeck.toArray(temp);
        String[] names = new String[linkedDeck.size()];
        for (int i = 0; i < linkedDeck.size(); i++)
        {
            names[i] = temp[i].getName();
        }
        return names;
    }


    /**
     * Gives the number of decks in the library
     * 
     * @return number of decks in the chain
     */
    public int deckCount()
    {
        return linkedDeck.size();
    }


    /**
     * Gives the user a way to walk through all the decks
     * 
     * @return an iterator of the decks in the library
     */
    public Iterator<Deck> iterator()
    {
        Iterator<Deck> temp = linkedDeck.iterator();
        return temp;
    }
}
