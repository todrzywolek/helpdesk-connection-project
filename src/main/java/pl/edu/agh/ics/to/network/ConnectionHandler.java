package pl.edu.agh.ics.to.network;

import lombok.Data;
import lombok.NonNull;
import pl.edu.agh.ics.to.models.Infolinia;
import pl.edu.agh.ics.to.models.Polaczenie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Data
public class ConnectionHandler extends Thread {

    @NonNull
    private Socket socket;

    @NonNull
    private Infolinia infolinia;

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                if (handleConnectionRequest(echoString, output)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Blad Connection handlera: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean handleConnectionRequest(String request, PrintWriter output) {
        if (request.equals("exit")) {
            return true;
        }
        String[] messageSections = request.split(":");
        validateMessageSections(messageSections);

        String response = createResponse(messageSections);

        output.println(response);
        return false;
    }

    public String createResponse(String[] messageSections) {
        if (messageSections[0].equals("i")) {
            return sendDepartmentInfo();
        } else if (messageSections[0].equals("c")) {
            return redirectConnection(messageSections);
        } else {
            return "Bledny prefix wiadomosci";
        }
    }

    public String sendDepartmentInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista dzialow:\n");
        infolinia.getDzialy().forEach(dzial -> sb.append(
                String.format("Nazwa dzialu: %s, Nr dzialu: %s\n", dzial.getNazwaDzialu(), dzial.getNrWewnetrzny())));

        return sb.toString();
    }

    public String redirectConnection(String[] messageSections) {
        int clientNumber = Integer.parseInt(messageSections[1]);
        int departmentNumber = Integer.parseInt(messageSections[2]);
        Polaczenie connection = new Polaczenie(clientNumber, departmentNumber);

        return infolinia.nasluchujPolaczenia(connection);
    }

    private void validateMessageSections(String[] messageSections) {
        if (messageSections.length >= 2 && messageSections.length <= 3)
            throw new IllegalArgumentException("Bledna wiadomosc");
        if (messageSections[0].length() != 1) throw new IllegalArgumentException("Bledny prefix");
        if (messageSections[0].equals("i") && messageSections[2].length() != 1)
            throw new IllegalArgumentException("Bledny nr dzialu");
        Integer.parseInt(messageSections[1]);
        Integer.parseInt(messageSections[2]);
    }
}
