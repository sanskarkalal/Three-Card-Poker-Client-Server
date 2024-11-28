import java.io.Serializable;

public class PokerInfo implements Serializable {
    String log;
    Dealer dealer;
    Player player;
    Boolean folded;
    Boolean winner;

    PokerInfo() {
        this.dealer = new Dealer();
        this.player = new Player();
        folded = false;
        winner = false;
    }

}
