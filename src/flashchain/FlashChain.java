package flashchain;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Console driver for the FlashChain flashcard prototype.
 *
 * Persistence is intentionally not implemented in this prototype.
 *
 * @author taiyol
 */
public class FlashChain {
    private final Library userLib;
    private final Scanner input;

    /**
     * Creates a FlashChain session with an empty in-memory library.
     */
    public FlashChain() {
        userLib = new Library();
        input = new Scanner(System.in);
    }


    /**
     * Starts the console application.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {
        FlashChain app = new FlashChain();
        app.run();
    }


    /**
     * Runs the main menu until the user chooses to quit.
     */
    private void run() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice;

            if (!input.hasNextLine()) {
                break;
            }
            choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("New deck name: ");
                    if (input.hasNextLine()) {
                        createDeck(input.nextLine());
                    }
                    else {
                        running = false;
                    }
                    break;
                case "2":
                    listDecks();
                    break;
                case "3":
                    openDeck();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Please choose 1-4.");
                    break;
            }
        }

        input.close();
        System.out.println("Goodbye!");
    }


    private void printMainMenu() {
        System.out.println("\n=== FlashChain ===");
        System.out.println("1) Create deck");
        System.out.println("2) List decks");
        System.out.println("3) Open a deck");
        System.out.println("4) Quit");
        System.out.print("Choose: ");
    }


    private void listDecks() {
        if (userLib.deckCount() == 0) {
            System.out.println("No decks yet. Create one first.");
            return;
        }

        Iterator<Deck> decks = userLib.iterator();
        while (decks.hasNext()) {
            Deck deck = decks.next();
            System.out.println("- " + deck.getName() + " (" + deck.cardCount()
                + " cards)");
        }
    }


    private void openDeck() {
        if (userLib.deckCount() == 0) {
            System.out.println("There are no decks to open yet.");
            return;
        }

        System.out.print("Deck name: ");
        if (!input.hasNextLine()) {
            return;
        }

        String deckName = input.nextLine().trim();
        Deck deck = userLib.findDeck(deckName);

        if (deck == null) {
            System.out.println("No deck named \"" + deckName
                + "\". Try again.");
            return;
        }

        openDeckMenu(deck);
    }


    private void openDeckMenu(Deck deck) {
        boolean back = false;

        while (!back) {
            printDeckMenu(deck);

            if (!input.hasNextLine()) {
                return;
            }
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    listCards(deck);
                    break;
                case "2":
                    addCard(deck);
                    break;
                case "3":
                    insertCard(deck);
                    break;
                case "4":
                    removeCard(deck);
                    break;
                case "5":
                    studyDeck(deck);
                    break;
                case "6":
                    back = true;
                    break;
                default:
                    System.out.println("Please choose 1-6.");
                    break;
            }
        }
    }


    private void printDeckMenu(Deck deck) {
        System.out.println("\n--- Deck: " + deck.getName() + " (" + deck
            .cardCount() + " cards) ---");
        System.out.println("1) List cards");
        System.out.println("2) Add card to end");
        System.out.println("3) Insert card at position");
        System.out.println("4) Remove card");
        System.out.println("5) Study");
        System.out.println("6) Back");
        System.out.print("Choose: ");
    }


    private void listCards(Deck deck) {
        if (deck.isEmpty()) {
            System.out.println("This deck has no cards.");
            return;
        }

        Iterator<Card> cards = deck.iterator();
        int position = 1;
        while (cards.hasNext()) {
            System.out.println(position + ". " + cards.next());
            position++;
        }
    }


    private void addCard(Deck deck) {
        System.out.print("Front: ");
        if (!input.hasNextLine()) {
            return;
        }
        String front = input.nextLine();

        System.out.print("Back: ");
        if (!input.hasNextLine()) {
            return;
        }
        String back = input.nextLine();

        try {
            deck.addCard(new Card(front, back));
            System.out.println("Card added.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(
                "Card not created: front and back must not be blank.");
        }
    }


    private void insertCard(Deck deck) {
        int position = readPosition("Insert at position (1-" + (deck.cardCount()
            + 1) + "): ", 1, deck.cardCount() + 1);
        if (position == -1) {
            return;
        }

        System.out.print("Front: ");
        if (!input.hasNextLine()) {
            return;
        }
        String front = input.nextLine();

        System.out.print("Back: ");
        if (!input.hasNextLine()) {
            return;
        }
        String back = input.nextLine();

        try {
            Card card = new Card(front, back);
            // The UI uses positions starting at 1; Deck uses indices starting
            // at 0.
            deck.insertCard(position - 1, card);
            System.out.println("Card inserted.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(
                "Card not created: front and back must not be blank.");
        }
        catch (IndexOutOfBoundsException e) {
            // Defensive check in case the deck implementation rejects the
            // index.
            System.out.println(
                "That position is no longer valid. Please try again.");
        }
    }


    private void removeCard(Deck deck) {
        if (deck.isEmpty()) {
            System.out.println("This deck has no cards to remove.");
            return;
        }

        int position = readPosition("Remove which position (1-" + deck
            .cardCount() + "): ", 1, deck.cardCount());
        if (position == -1) {
            return;
        }

        try {
            Card removed = deck.removeCard(position - 1);
            System.out.println("Removed: " + removed);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(
                "That position is no longer valid. Please try again.");
        }
    }


    /**
     * Reads a whole-number position in the inclusive range supplied.
     * Returns -1 if input ends before a valid position is entered.
     */
    private int readPosition(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            if (!input.hasNextLine()) {
                return -1;
            }

            String entered = input.nextLine().trim();
            try {
                int position = Integer.parseInt(entered);
                if (position < minimum || position > maximum) {
                    System.out.println("Enter a position from " + minimum
                        + " to " + maximum + ".");
                }
                else {
                    return position;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }


    /**
     * Creates and adds a deck. Blank names are reprompted; duplicate names are
     * rejected by Library.addDeck.
     *
     * @param name
     *            the initially entered deck name
     */
    public void createDeck(String name) {
        String deckName = name;

        while (true) {
            try {
                Deck deck = new Deck(deckName.trim());
                userLib.addDeck(deck);
                System.out.println("Created deck \"" + deck.getName() + "\".");
                return;
            }
            catch (IllegalArgumentException e) {
                System.out.println(
                    "Could not create deck: its name must not be blank "
                        + "or already in use.");
                System.out.print(
                    "Enter a different deck name (or type q to cancel): ");

                if (!input.hasNextLine()) {
                    return;
                }
                deckName = input.nextLine();
                if (deckName.trim().equalsIgnoreCase("q")) {
                    System.out.println("Deck creation cancelled.");
                    return;
                }
            }
        }
    }


    /**
     * Studies a temporary queue made from the deck. Cards answered incorrectly
     * go to the end of the queue and are asked again; cards answered correctly
     * leave the queue. The saved deck's card order and contents are unchanged.
     *
     * @param deck
     *            the deck to study
     */
    public void studyDeck(Deck deck) {
        if (deck.isEmpty()) {
            System.out.println("This deck has no cards. Add some first.");
            return;
        }

        LinkedChain<Card> queue = new LinkedChain<>();
        Iterator<Card> cards = deck.iterator();
        while (cards.hasNext()) {
            queue.addToEnd(cards.next());
        }

        int totalCards = queue.size();
        int firstTryCorrect = 0;
        int attempts = 0;

        while (!queue.isEmpty()) {
            Card current = queue.remove();
            attempts++;

            System.out.println("\nCards remaining in this session: " + queue
                .size() + 1);
            System.out.println("FRONT: " + current.getFront());
            System.out.print(
                "Press Enter to reveal the answer (or type q to stop): ");

            if (!input.hasNextLine()) {
                System.out.println(
                    "\nStudy session stopped because input ended.");
                return;
            }
            if (input.nextLine().trim().equalsIgnoreCase("q")) {
                System.out.println("Study session ended early.");
                return;
            }

            System.out.println("BACK:  " + current.getBack());
            Boolean correct = askIfCorrect();
            if (correct == null) {
                System.out.println(
                    "Study session stopped because input ended.");
                return;
            }

            if (correct) {
                if (attempts <= totalCards) {
                    firstTryCorrect++;
                }
            }
            else {
                queue.addToEnd(current);
                System.out.println("That card will come back.");
            }
        }

        System.out.println(
            "\nStudy complete! Every card was answered correctly.");
        System.out.println("First-try score: " + firstTryCorrect + "/"
            + totalCards);
        System.out.println("Total attempts: " + attempts);
    }


    /**
     * @return true for yes, false for no, or null if input ends
     */
    private Boolean askIfCorrect() {
        while (true) {
            System.out.print("Did you get it right? (y/n): ");
            if (!input.hasNextLine()) {
                return null;
            }

            String answer = input.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            else if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            else {
                System.out.println("Please type y or n.");
            }
        }
    }
}
