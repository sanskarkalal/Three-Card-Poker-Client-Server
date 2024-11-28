import java.io.Serializable;

public class Card implements Serializable {
    private char suit;  // 'C', 'D', 'S', 'H'
    private int value;  // 2 - 14 (2-10, J=11, Q=12, K=13, A=14)
    private String imagePath;
    private Boolean isDealer;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
        this.isDealer = false;
        this.imagePath = "/images/" + value + suit + ".png";
    }

    public String getImagePath() {
        return imagePath;
    }

    // Getters and Setters for suit and value
    public char getSuit() { return suit; }
    public int getValue() { return value; }

    public Boolean getDealer() {
        return isDealer;
    }

    public void setDealer(Boolean dealer) {
        isDealer = dealer;
    }
}
