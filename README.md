How to run:
1. in cmd, navigate to the folder that was downloaded with cd
2. run `javac -d ./bin ./src/flashchain/*.java`
3. run `java -cp ./bin flashchain.FlashChain`


Reflection:
What changed since specification: 
- In FlashChain, additional helper methods have been added to clean up and organize menu navigation, card management, and input validation.
- In FlashChain, a temporary queue created using LinkedChain so incorrectly answered cards return to the end without changing the original deck. 
What changed in the LinkedChain:
-
What happened to unknowns
- Leitner-style repetition and shuffling are still possible to implement, we still haven't figured out how to implement them
What doesn't work yet
- Saving and loading decks to a file

Next Steps:
What is left to finish the MVP:
- Add saving/loaing
Which stretch goals will we attempt:
- Shuffling
- Leitner-style repetition
What new tests will we need:
- The app can properly write and load decks from a file, without any cards lost or mistakingly added
- The app can properly handle any bad input when accepting a file path for saving/loading
- Ensure shuffling properly reorders a deck, but the the deck contains the same cards
- Ensure Leitner-style repetition properly organizes cards in a deck in appropriate boxes, and cards can properly move between boxes at appropriate times
Who does what:



Attribution:
- Card: Michaela, drafted and revised without AI
- CardTest:
- Deck:Joey Gentlesk, drafted and revised with assistance from GenAI
- DeckTest:Joey Gentlesk, drafted and revised without GenAI
- Library:Joey Gentlesk, drafted and revised without GenAI
- LibraryTest:Joey Gentlesk, drafted and revised without AI
- FlashChain: Taiyo, drafted and revised with assistance from GenAI
- LinkedChain: Michaela, implemented and revised without AI
