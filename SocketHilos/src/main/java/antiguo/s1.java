package antiguo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class s1
{
    public static void main(String[] args)
    {
        ServerSocket servidor = null;
        Socket sc = null;
        final int PUERTO = 5000;

        DataInputStream in = null;
        DataOutputStream out = null;

        try
        {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor a la espera");

            while(true)
            {//Esperar a que un cliente realice petición
                sc = servidor.accept(); // Se queda a la espera pringao

                System.out.println("Cliente conectado con exito");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Lee el mensaje de cliente

                String mensaje = in.readUTF();

                System.out.println("Mensaje: " + mensaje);

                //Envio un mensaje
                out.writeUTF("Les saludo desde el server");

                //Cerrar el puto puerto/socket
                sc.close();
                System.out.println("Conexion terminada exitosamente");
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
