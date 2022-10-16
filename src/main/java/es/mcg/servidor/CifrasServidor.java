/**
 * EJERCICIO 2: Implementar un programa CifrasCliente y CifrasServidor con las siguiente funcionalidad:
 * a. El cliente enviará números enteros positivos al servidor, y el servidor responderá con la palabra "Recibido".
 * b. Cuando el cliente envíe el número cero, el servidor responderá con una cadena de texto informando de la 
 * media de los números recibidos, la moda, el mayor y el menor de todos los números.
 * Por ejemplo, si el cliente realiza estas cinco llamadas al servidor con los textos:
 * 1) 4; 2) 3; 3) 2; 4) 2; 5) 0
 * La última respuesta del servidor será: "Media: 3, Moda: 2, Mayor: 4, Menor: 2".
 */
package es.mcg.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CifrasServidor {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        ArrayList<Integer> valores = null;
        double suma, media;
        int mayor = 0, menor = 0;
        try {
            socketServidor = new ServerSocket(PORT);
            socketCliente = socketServidor.accept();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            inputStream = new DataInputStream(socketCliente.getInputStream());
            valores = new ArrayList<Integer>();
            int s;
            outputStream.writeUTF("Introduce un entero. 0 para acabar");
            s = inputStream.readInt();
            while(s != 0) 
            {
                valores.add(s);
                outputStream.writeUTF("Recibido");
                s = inputStream.readInt();
            }
            //Para poder calcular la media del ArrayList debemos primero calcular la suma y despues
            // se obtiene con el tamanio del ArrayList en formato tipo double.
            suma = calcularSuma(valores);
            media = suma / valores.size();
            outputStream.writeDouble(media);
            //Para poder calcular la moda usaremos el HasMap para recorrer los valores del ArrayList
            // y obtener el numero que mas se repite
            HashMap<Integer, Integer> map = new HashMap<>();
            int repetido = 0, numModa = -1, repetidoCon = 0;
            for(int i : valores)
            {
                if(map.containsKey(i))
                {
                    repetido = map.get(i);
                    map.put(i, ++repetido);
                }
                else
                {
                    map.put(i, 1);
                }
            }
            for(Map.Entry<Integer,Integer> e : map.entrySet())
            {
                if(repetidoCon < e.getValue())
                {
                    repetidoCon = e.getValue();
                    numModa = e.getKey();
                }
            }

            outputStream.writeInt(numModa);
            //Usaremos el bucle for para obtener el numero menor del ArrayList
            for(int i = 0; i < valores.size(); i++)
            {
                if(valores.get(i) < menor)
                {
                    menor = valores.get(i);
                }
            }
            outputStream.writeInt(menor);
            //Hacemos lo mismo para obtener el numero mayor
            for(int i = 0; i < valores.size(); i++)
            {
                if(valores.get(i) > mayor)
                {
                    mayor = valores.get(i);
                }
            }
            outputStream.writeInt(mayor);
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

    public static double calcularSuma(ArrayList<Integer> valores)
    {
        double suma = 0;
        //Con el Iterator recorremos el ArrayList y hacemos la suma
        Iterator it = valores.iterator();
        while(it.hasNext())
        {
            suma = suma + (Integer) it.next();
        }
        return suma;
    }
}
