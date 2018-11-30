package pl.edu.agh.ics.to.network;

import lombok.Data;
import pl.edu.agh.ics.to.models.Infolinia;

import java.io.IOException;
import java.net.ServerSocket;

@Data
public class NetworkConnectionListener implements ConnectionListener {

    @Override
    public void listen(Infolinia infolinia) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                new ConnectionHandler(serverSocket.accept(), infolinia).start();
            }

        } catch (IOException e) {
            System.out.println("Blad serwera: " + e.getMessage());
        }

    }
}
