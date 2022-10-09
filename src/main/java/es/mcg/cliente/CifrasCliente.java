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
