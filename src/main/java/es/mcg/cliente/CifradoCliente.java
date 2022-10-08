package es.mcg.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import es.mcg.servidor.CifradoServidor;

public class CifradoCliente {
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Socket socketCliente = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);
        String palabra = "", palabraSalida;
        boolean fin = false;

        try {
            socketCliente = new Socket(HOST, CifradoServidor.PORT);
            inputStream = new DataInputStream(socketCliente.getInputStream());
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            System.out.println(inputStream.readUTF());
            do
            {
                palabra = sc.nextLine();
                if(palabra == "fin")
                {
                    fin = true;
                }
                else
                {
                outputStream.writeUTF(palabra);
                }
                System.out.println(inputStream.readUTF());
            }while(!fin);
            ArrayList<String> datosSalida = new ArrayList<String>();
            for(String s: datosSalida)
            {
                s = inputStream.readUTF();
                datosSalida.add(s);
            }
            Iterator<String> iterator = datosSalida.iterator();
            while(iterator.hasNext())
            {
                palabraSalida = iterator.next();
                System.out.print(palabraSalida+" ");
            }
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
                sc.close();
            }
            catch(IOException ioException2)
            {
                ioException2.printStackTrace();
            }
        }
    }
}
