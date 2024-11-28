import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    public Deck() {
        newDeck();
    }

    // Method to initialize a new shuffled deck
    public void newDeck() {
        this.clear();
        char[] suits = {'C', 'D', 'S', 'H'};
        for (char suit : suits) {
            for (int value = 2; value <= 14; value++) {
                this.add(new Card(suit, value));
            }
        }
        Collections.shuffle(this);
    }
}

