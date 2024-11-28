import java.io.Serializable;
import java.util.ArrayList;

public class Dealer implements Serializable {
    Deck theDeck;
    private ArrayList<Card> dealersHand;


    public Dealer() {
        theDeck = new Deck();
        dealersHand = new ArrayList<>();
    }

    public void setDealersHand(ArrayList<Card> dealersHand) {
        this.dealersHand = dealersHand;
    }

    // Method to deal a hand of 3 cards
    public ArrayList<Card> dealHand() {
        ArrayList<Card> temp = new ArrayList<>();
        if (theDeck.size() < 35) {
            theDeck = new Deck();
        }

        for (int i = 0; i < 3; i++) {
            temp.add(theDeck.remove(0));
        }
        return new ArrayList<>(temp);
    }

    // Get dealer's current hand
    public ArrayList<Card> getDealersHand() {
        dealersHand.get(0).setDealer(true);
        dealersHand.get(1).setDealer(true);
        dealersHand.get(2).setDealer(true);
        return dealersHand;
    }


}
