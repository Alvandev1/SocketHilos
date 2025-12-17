package antiguo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class c1 {
    public static void main(String[] args)
    {
        //Host del servidor
        final String HOST = "localhost";
        //Puerto del Servidor
        final int PORT = 5000;

        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            Socket socket = new Socket(HOST, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //Enviamos una peticion al servidor
            out.writeUTF("Hola soy el cliente");
            //Recibimos la respuesta del servidor
            String mensaje = in.readUTF();
            System.out.println(mensaje);

        //Cerramos socket
            socket.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
