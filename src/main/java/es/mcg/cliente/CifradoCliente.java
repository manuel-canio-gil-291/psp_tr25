/**
 * EJERCICIO 1: Implementar un programa CifradoCliente y CifradoServidor con la siguiente funcionalidad:
 * a. El cliente enviará palabras al servidor, y el servidor responderá con la palabra "Recibido".
 * b. En el momento que el cliente envíe la palabra "fin", el servidor enviará una cadena de texto 
 * cifrando todas las palabras enviadas previamente, sumando un carácter ASCII a cada letra.
 * Por ejemplo, si el cliente realiza estas cuatro llamadas al servidor con los textos:
 * 1) aaa 2) bbbb 3) abcdefg 4) fin
 * La última respuesta del servidor será: bbb cccc bcdefgh
 */
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
            sc.close();
            if(outputStream != null)
            {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
