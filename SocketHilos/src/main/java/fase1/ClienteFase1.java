package fase1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteFase1 {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Cliente Fase 1 conectando a " + HOST + ":" + PORT);

        try (Socket socket = new Socket(HOST, PORT); //Intenta conectarse al server, con el host y el puerto del server
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            out.writeUTF("Hola desde cliente (Fase 1)"); //Manda el string al server
            out.flush();

            String resp = in.readUTF(); //Recibe respuesta el server
            System.out.println("Servidor responde: " + resp);

        } catch (IOException e) {
            System.out.println("Error E/S en cliente: " + e.getMessage()); //Si hay un error sale esta excepcion
        }
    }
}