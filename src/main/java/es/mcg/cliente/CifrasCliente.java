/**
 * EJERCICIO 2: Implementar un programa CifrasCliente y CifrasServidor con las siguiente funcionalidad:
 * a. El cliente enviará números enteros positivos al servidor, y el servidor responderá con la palabra "Recibido".
 * b. Cuando el cliente envíe el número cero, el servidor responderá con una cadena de texto informando de la 
 * media de los números recibidos, la moda, el mayor y el menor de todos los números.
 * Por ejemplo, si el cliente realiza estas cinco llamadas al servidor con los textos:
 * 1) 4; 2) 3; 3) 2; 4) 2; 5) 0
 * La última respuesta del servidor será: "Media: 3, Moda: 2, Mayor: 4, Menor: 2".
 */
package es.mcg.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import es.mcg.servidor.CifrasServidor;

public class CifrasCliente {
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Socket socketCliente = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);
        int n;
        
        try {
            socketCliente = new Socket(HOST, CifrasServidor.PORT);
            inputStream = new DataInputStream(socketCliente.getInputStream());
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            System.out.println(inputStream.readUTF());
            n = sc.nextInt();
            outputStream.writeInt(n);
            while(n != 0)
            {
                System.out.println(inputStream.readUTF());
                n = sc.nextInt();
                outputStream.writeInt(n);
            }
            double media;
            int moda, mayor, menor;
            media = inputStream.readDouble();
            moda = inputStream.readInt();
            menor = inputStream.readInt();
            mayor = inputStream.readInt();

            System.out.println("Media: "+media+" Moda: "+moda+" Mayor: "+mayor+" Menor: "+menor);
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
                outputStream.close();
                sc.close();
            }
            catch(IOException ioException2)
            {
                ioException2.printStackTrace();
            }
        }
    }
}
