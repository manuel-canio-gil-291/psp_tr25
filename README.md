# Ejercicios de programacion y servicio de procesos: Transparencia 25 y 26

## Ejercicio 1
Implementar un programa `CifradoCliente` y `CifradoServidor` con la siguiente funcionalidad:
- El cliente enviará palabras al servidor, y el servidor responderá con la palabra `Recibido`.
- En el momento que el cliente envíe la palabra `fin`, el servidor enviará una cadena de texto cifrando todas las palabras enviadas previamente, sumando un carácter ASCII a cada letra.
> Por ejemplo, si el cliente realiza estas cuatro llamadas al servidor con los textos: aaa, bbbb, abcdefg, fin. La última respuesta del servidor será: bbb cccc bcdefgh

## Ejercicio 2
Implementar un programa `CifrasCliente` y `CifrasServidor` con las siguiente funcionalidad:
- El cliente enviará números enteros positivos al servidor, y el servidor responderá con la palabra `Recibido`.
- Cuando el cliente envíe el número cero, el servidor responderá con una cadena de texto informando de la media de los números recibidos, la moda, el mayor y el menor de todos los números.
> Por ejemplo, si el cliente realiza estas cinco llamadas al servidor con los textos: 4, 3, 2, 2, 0. La última respuesta del servidor será: "Media: 3, Moda: 2, Mayor: 4, Menor: 2".