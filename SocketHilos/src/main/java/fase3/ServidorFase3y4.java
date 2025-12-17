package fase3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFase3y4 {
    private static final int PORT = 5000;

    public static void main(String[] args) {

        int port;
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else {
            port = PORT;
        }

        System.out.println("Servidor Fase 3/4 (multihilo) en puerto " + port);

        try (ServerSocket server = new ServerSocket(port)) {

            while (true) {
                Socket s = server.accept(); //Espera conexiones

                System.out.println("Cliente conectado desde: " + s.getInetAddress());

                GestorCliente gestor = new GestorCliente(s); //Crea un gestor para el cliente
                new Thread(gestor).start(); //Inicia un hilo para atender
            }

        } catch (IOException e) {
            System.out.println("Error E/S en servidor: " + e.getMessage()); //Si hay cualquier error manda una excepcion
        }
    }
}
