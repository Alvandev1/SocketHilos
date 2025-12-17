package fase2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteFase2 {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Cliente Fase 2 conectando a " + HOST + ":" + PORT); //Intenta conectarse al server, con el host y el puerto del server

        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)) { //Permite crear mensajes desde la consola

            boolean salir = false;

            while (!salir) { //Cada vuelta lo que hace es que lee el mensaje del usuario, lo envia al server, espera la respuesta y la muestra
                System.out.print("Tú: ");
                String msg = sc.nextLine();

                out.writeUTF(msg);
                out.flush();

                String resp = in.readUTF();
                System.out.println(resp);

                if (msg.equals("FIN")) { //Si el usuario escriben FIN se acaba
                    salir = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error E/S en cliente: " + e.getMessage()); //Si hay algun error, envia una excepcion
        }
    }
}
