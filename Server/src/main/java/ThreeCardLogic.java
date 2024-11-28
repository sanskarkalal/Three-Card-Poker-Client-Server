import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThreeCardLogic {

    // Hand rankings:
    // 0 = High Card, 1 = Straight Flush, 2 = Three of a Kind, 3 = Straight, 4 = Flush, 5 = Pair
    public static int evalHand(ArrayList<Card> hand) {
        // Sort the hand by card value in descending order
        Collections.sort(hand, Comparator.comparingInt(Card::getValue).reversed());

        boolean isFlush = isFlush(hand);
        boolean isStraight = isStraight(hand);

        if (isFlush && isStraight) {
            return 1; // Straight Flush
        } else if (isThreeOfAKind(hand)) {
            return 2; // Three of a Kind
        } else if (isStraight) {
            if ((hand.get(0).getValue() == 14) && (hand.get(1).getValue() == 3) && (hand.get(2).getValue() == 2)) {
                return 4;
            }
            return 3; // Straight
        } else if (isFlush) {
            return 5; // Flush
        } else if (isPair(hand)) {
            return 6; // Pair
        } else {
            return 0; // High Card
        }
    }

    // Helper to determine if the hand is a flush
    private static boolean isFlush(ArrayList<Card> hand) {
        return hand.get(0).getSuit() == hand.get(1).getSuit() &&
                hand.get(0).getSuit() == hand.get(2).getSuit();
    }

    // Helper to determine if the hand is a straight, including Ace-2-3
    private static boolean isStraight(ArrayList<Card> hand) {
        // Standard consecutive values
        boolean standardStraight = (hand.get(0).getValue() == hand.get(1).getValue() + 1) &&
                (hand.get(1).getValue() == hand.get(2).getValue() + 1);

        // Ace-low straight (Ace, 2, 3)
        boolean aceLowStraight = (hand.get(0).getValue() == 14) && // Ace
                (hand.get(1).getValue() == 3) &&
                (hand.get(2).getValue() == 2);


        return standardStraight || aceLowStraight;
    }

    // Helper to determine if the hand is three of a kind
    private static boolean isThreeOfAKind(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() &&
                hand.get(0).getValue() == hand.get(2).getValue();
    }

    // Helper to determine if the hand is a pair
    private static boolean isPair(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() ||
                hand.get(1).getValue() == hand.get(2).getValue() ||
                hand.get(0).getValue() == hand.get(2).getValue();
    }

    // Evaluate Pair Plus winnings based on hand ranking
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int handRanking = evalHand(hand);

        switch (handRanking) {
            case 1: // Straight Flush
                return bet * 40;
            case 2: // Three of a Kind
                return bet * 30;
            case 3: // Straight
                return bet * 6;
            case 5: // Flush
                return bet * 3;
            case 6: // Pair
                return bet; // 1 to 1 payout
            default:
                return 0; // No payout for High Card
        }
    }

    // Compare dealer's and player's hands
    // Returns 0 = Neither Wins, 1 = Player Wins, 2 = Dealer Wins
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerRank = evalHand(dealer);
        int playerRank = evalHand(player);
        System.out.println(dealerRank + "resultdealer ");
        System.out.println(playerRank + "resultplayer ");

        // Compare hand rankings
        if (playerRank < dealerRank) {
            return 2; // Dealer Wins
        } else if (dealerRank < playerRank) {
            return 1; // Player Wins
        } else {
            // If ranks are the same, compare by highest card
            return compareHighCard(dealer, player);
        }
    }

    // Helper to compare hands by highest card in case of a tie
    private static int compareHighCard(ArrayList<Card> dealer, ArrayList<Card> player) {
        // Sort both hands in descending order to start with the highest card
        Collections.sort(dealer, Comparator.comparingInt(Card::getValue).reversed());
        Collections.sort(player, Comparator.comparingInt(Card::getValue).reversed());

        // Compare each card in order
        for (int i = 0; i < 3; i++) {
            if (player.get(i).getValue() > dealer.get(i).getValue()) {
                return 2; // Player Wins
            } else if (dealer.get(i).getValue() > player.get(i).getValue()) {
                return 1; // Dealer Wins
            }
        }
        return 0; // Tie if all cards are identical
    }
}
