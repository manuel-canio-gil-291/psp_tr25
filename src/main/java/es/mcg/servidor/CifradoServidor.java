/**
 * EJERCICIO 1: Implementar un programa CifradoCliente y CifradoServidor con la siguiente funcionalidad:
 * a. El cliente enviará palabras al servidor, y el servidor responderá con la palabra "Recibido".
 * b. En el momento que el cliente envíe la palabra "fin", el servidor enviará una cadena de texto 
 * cifrando todas las palabras enviadas previamente, sumando un carácter ASCII a cada letra.
 * Por ejemplo, si el cliente realiza estas cuatro llamadas al servidor con los textos:
 * 1) aaa 2) bbbb 3) abcdefg 4) fin
 * La última respuesta del servidor será: bbb cccc bcdefgh
 */
package es.mcg.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CifradoServidor {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        ArrayList<String> palabras;
        String dato = "";
        boolean fin = false;
        try {
            socketServidor = new ServerSocket(PORT);
            socketCliente = socketServidor.accept();
            palabras = new ArrayList<String>();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeUTF("Escriba algunas palabras");
            inputStream = new DataInputStream(socketCliente.getInputStream());
            do
            {
                dato = inputStream.readUTF();
                if(dato == "fin") {
                    fin = true;
                }
                else
                {
                palabras.add(dato);
                }
                outputStream.writeUTF("Recibido");
            }
            while(!fin);
            for(int i = 0; i < palabras.size(); i++)
            {
                int ascii;
                String res;
                ascii = palabras.get(i).codePointAt(i);
                int sum = ascii + 1;
                res = Character.toString(sum);
                outputStream.writeUTF(res);
            }
            inputStream.close();
            outputStream.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        finally
        {
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(outputStream != null)
            {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(socketCliente != null)
            {
                try {
                    socketCliente.close();
                } catch (IOException ioException) { 
                    ioException.printStackTrace();
                }
            }
            if(socketServidor != null)
            {
                try {
                    socketServidor.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
