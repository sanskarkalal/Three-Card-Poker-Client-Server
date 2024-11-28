import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{


    Consumer<PokerInfo> callback;
    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;

    public Client(Consumer<PokerInfo> callback) {
        this.callback = callback;
    }



    public void run() {

        try {
            socketClient= new Socket("127.0.0.1",5555);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        }
        catch(Exception e) {}

        while(true) {

            try {
                PokerInfo pokerinfo = (PokerInfo) in.readObject();

                callback.accept(pokerinfo);
            }
            catch(Exception e) {}
        }

    }

    public void send(PokerInfo data) {

        try {
            out.writeObject(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
