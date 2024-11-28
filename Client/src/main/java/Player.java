import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    public Player() {
        hand = new ArrayList<>();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 100;
    }

    // Method to set the player's hand
    public void setHand(ArrayList<Card> newHand) {
        this.hand = newHand;
    }

    // Method to retrieve the player's current hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Getter and Setter for Ante Bet
    public int getAnteBet() {
        return anteBet;
    }

    public void setAnteBet(int anteBet) {
        if (anteBet >= 5 && anteBet <= 25) {
            this.anteBet = anteBet;
            totalWinnings -= anteBet;
        } else {
            throw new IllegalArgumentException("Ante bet must be between $5 and $25.");
        }
    }

    // Getter and Setter for Play Bet
    public int getPlayBet() {
        return playBet;
    }

    public void setPlayBet(int playBet) {
        if (playBet == anteBet) {  // Play Bet must match the Ante Bet
            this.playBet = playBet;
            totalWinnings -= playBet;
        } else {
            throw new IllegalArgumentException("Play bet must be equal to the Ante bet.");
        }
    }

    // Getter and Setter for Pair Plus Bet
    public int getPairPlusBet() {
        return pairPlusBet;
    }

    public void setPairPlusBet(int pairPlusBet) {
        if (pairPlusBet == 0 || (pairPlusBet >= 5 && pairPlusBet <= 25)) {
            this.pairPlusBet = pairPlusBet;
            totalWinnings -= pairPlusBet;
        } else {
            throw new IllegalArgumentException("Pair Plus bet must be $0 (if not placed) or between $5 and $25.");
        }
    }

    // Getter and Setter for Total Winnings
    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void addWinnings(int amount) {
        this.totalWinnings += amount;  // Add or subtract from total winnings
    }

    // Reset all bets for a new round
    public void resetBets() {
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
    }
}
