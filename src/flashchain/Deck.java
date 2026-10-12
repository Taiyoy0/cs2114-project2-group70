package flashchain;

import java.util.Iterator;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileNotFoundException;

// Challenge Exercise 06 fall 2026
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Joey Gentlesk(jgentlesk)
// LLM Statement:
// I have used Gemini to help create the loadDeck and saveDeck methods and how
// to store decks on a file and take them out of the files
// -------------------------------------------------------------------------
/**
 * The Deck class is a class that contains a linked chain of the card object
 *
 * @author Joey Gentlesk
 * @version Oct 12, 2026
 */
public class Deck
{
    private String name;
    private LinkedChain<Card> linkedCard;

    /**
     * The basic constructor for the class
     * 
     * @param name
     *            the name of the deck being created
     */
    public Deck(String name)
    {
        if (name.equals(null) || name.equals(""))
        {
            throw new IllegalArgumentException();
        }
        this.name = name;
        linkedCard = new LinkedChain<Card>();
    }


    /**
     * Reads a file to create a deck and populate it'
     * 
     * 
     * @param filePath
     *            where the file is stored on the computer
     * @return deck that was created
     * @throws FileNotFoundException
     */
    public static Deck loadDeck(String filePath)
        throws FileNotFoundException
    {
        try (Scanner input = new Scanner(filePath))
        {
            Deck newDeck = new Deck(input.next());
            while (input.hasNext())
            {
                String front = input.next();
                String back = input.next();
                Card temp = new Card(front, back);
                newDeck.addCard(temp);
            }
            return newDeck;
        }
    }


    /**
     * The basic get method for the name variable
     * 
     * @return name of the deck
     */
    public String getName()
    {
        return name;
    }


    /**
     * The method to append a card to the linked chain of cards
     * 
     * @param c
     *            The card being added to the deck
     */
    public void addCard(Card c)
    {
        linkedCard.add(c);
    }


    /**
     * Inserts the card at a specified index
     * 
     * @param index
     *            index where card is being added
     * @param c
     *            card being added
     */
    public void insertCard(int index, Card c)
    {
        linkedCard.add(c);
    }


    /**
     * Removes and returns the card at a specified index
     * 
     * @param index
     *            the location in the chain
     * @return Card at the index
     */
    public Card removeCard(int index)
    {
        Card temp = linkedCard.get(index);
        linkedCard.remove(temp);
        return temp;

    }


    /**
     * Collects Card at index without removing it
     * 
     * @param index
     *            position of desired card
     * @return the card at index
     */
    public Card getCard(int index)
    {
        return linkedCard.get(index);
    }


    /**
     * a get method for the number of card objects
     * 
     * @return the number of objects in the chain
     */
    public int cardCount()
    {
        return linkedCard.size();
    }


    /**
     * Checks if the chain is empty
     * 
     * @return true if the deck has no card objects in the chain
     */
    public boolean isEmpty()
    {
        return linkedCard.isEmpty();
    }


    /**
     * Walks the through the deck in order
     * 
     * @return Returns an iterator of the deck's cards
     */
    public Iterator<Card> iterator()
    {
        Iterator<Card> temp = linkedCard.iterator();
        return temp;
    }


    /**
     * Writes every card in the deck to a file
     * 
     * @param filePath
     *            the file the cards are being added to
     */
    public void saveDeck(String filePath)
    {
        try
        {
            Path file = Path.of(filePath);
            Files.writeString(file, name);
            Iterator<Card> cards = iterator();
            while (cards.hasNext())
            {
                Card c = cards.next();
                String front = c.getFront();
                Files.writeString(file, front);
                String back = c.getBack();
                Files.writeString(file, back);
            }

            System.out.println("Data saved successfully!");
        }
        catch (IOException e)
        {
            System.err
                .println("An error occurred while writing: " + e.getMessage());
        }
    }

}
