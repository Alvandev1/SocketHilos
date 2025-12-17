package fase1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFase1 {
    private static final int PORT = 5000; //Creamos el puerto

    public static void main(String[] args) {
        System.out.println("Servidor Fase 1 arrancado en puerto " + PORT);
        //Aqui el server esta a la espera, lo abre a traves del puerto 5000
        try (ServerSocket server = new ServerSocket(PORT)) {

            // Esto lo mantiene bloqueado hasta que el cliente pueda conectarse
            try (Socket socket = server.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream()); //Esto lee lo que comente el cliente
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) { //Esto envia respuestas al cliente

                // Como el servidor es de un solo hilo, cuando esta en el sleep no procesa mas conexiones ni hace llamadas
                Thread.sleep(15000);

                String msg = in.readUTF();
                System.out.println("Cliente dice: " + msg);

                out.writeUTF("Servidor recibió: " + msg); //El server lee y responde una vez y luego acaba
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Error E/S en servidor: " + e.getMessage()); //En caso de que haya un error muestra la excepcion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrumpido.");
        }
    }
}
