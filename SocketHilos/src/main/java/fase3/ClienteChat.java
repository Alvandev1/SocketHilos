package fase3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 5000; // Defino el puerto donde esta el server

    public static void main(String[] args) {

        String host;
        if (args.length >= 1) {
            host = args[0];
        } else {
            host = DEFAULT_HOST;
        }

        int port;
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        } else {
            port = DEFAULT_PORT;
        }

        System.out.println("Cliente conectando a " + host + ":" + port); //Imprime en la consola el host y el puerto que esta conectandose

        try (Socket socket = new Socket(host, port); //Intenta conectarse al server
             DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //Canal para enviar datos al server
             DataInputStream in = new DataInputStream(socket.getInputStream()); //Canal para enviar datos al server
             Scanner sc = new Scanner(System.in)) { //Lee el teclado

            boolean salir = false; //Mientras el booleano sea false, el chat continua

            while (!salir) { //Cuando salir sea true, termina
                System.out.print("Tú: "); //Imprime lo que he escrito
                String msg = sc.nextLine(); //Bloquea hasta que escriba y pulse enter todo lo escrito se guarda en msg

                out.writeUTF(msg); //Envia el String al server usando el formato UTF
                out.flush(); //Esto fuerza que los mensajes se envien inmediatamente

                String resp = in.readUTF(); //Se bloquea hasta que el servidor envie una respuesta
                System.out.println(resp); //Cuando esta llega se guarda en resp y la imprimimos :)

                if (msg.equals("FIN")) {
                    salir = true; //Si el cliente escribe FIN acaba
                }
            }

        } catch (IOException e) {
            System.out.println("Error E/S en cliente: " + e.getMessage()); //Imprime una excepcion en el caso de que haya algun error :)
        }
    }
}
