package fase2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFase2 {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Servidor Fase 2 (iterativo 1-a-1) en puerto " + PORT);
//Aqui el server esta a la espera, lo abre a traves del puerto 5000
        try (ServerSocket server = new ServerSocket(PORT);
             Socket socket = server.accept(); // Esto lo mantiene bloqueado hasta que el cliente pueda conectarse
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            boolean salir = false; //Controlamos el bucle hasta que salir sea true y asi no se cree un bucle infinito

            while (!salir) {
                String msg = in.readUTF(); //Bloquea hasta que el cliente envie algo
                System.out.println("Cliente: " + msg);

                if (msg.equals("FIN")) { //Si recibe un mensaje de fin termina
                    salir = true;
                    out.writeUTF("Servidor: FIN recibido, cerrando...");
                    out.flush();
                } else {
                    out.writeUTF("Servidor: recibido -> " + msg);
                    out.flush();
                }
            }

        } catch (IOException e) {
            System.out.println("Error E/S en servidor: " + e.getMessage()); //Si hay un error envia una excepcion
        }
    }
}

