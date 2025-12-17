package fase3;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class GestorCliente implements Runnable {
    private final Socket socket; //Cada cliente tiene su propio socket y se entrega en el accept del server :)

    public GestorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() { //Esto es lo que ejecutara el hilo

        try (Socket s = socket; //Aqui cada hilo crea su input y output para su socket
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            boolean salir = false;

            while (!salir) {
                String msg = in.readUTF();
                System.out.println("[" + Thread.currentThread().getName() + "] Cliente: " + msg);

                if (msg.equals("FIN")) { //Si el usuario escribe FIN se cierra.
                    salir = true;
                    out.writeUTF("Servidor: FIN recibido, cerrando...");
                    out.flush();
                } else {
                    out.writeUTF("Servidor: recibido -> " + msg);
                    out.flush();
                }
            }

        } catch (EOFException | SocketException e) {
    //Se hace una excepcion si hay cualquier error e imprime el mensaje en la consola
            System.out.println("El cliente se ha desconectado inesperadamente");
        } catch (IOException e) {
            System.out.println("Error E/S en hilo cliente: " + e.getMessage());
        }
    }
}

