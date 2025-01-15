package examenes;

import java.util.Random;
import java.util.Scanner;

public class Examen {

	// Importamos random.
	static Random rnd = new Random();

	// Importamos Scanner.
	static Scanner sc = new Scanner(System.in);

	// Abecedario.
	static char[][] abecedario = { { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' },
			{ 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q' }, { 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' } };

	// Frases
	static String[] frases = {
			"las guerras seguiran mientras el color de la piel siga siendo mas importante que el de los ojos",
			"aprende a vivir y sabras morir bien", "cada dia sabemos mas y entendemos menos",
			"el dinero no puede comprar la vida", "la verdadera sabiduria esta en reconocer la propia ignorancia" };

	// Frase con la cual se jugará.
	static String fraseReal;

	// Frase codificada.
	static String fraseCodificada = "";

	public static void main(String[] args) {
		int contLetra = 0;
		int codigo;
		String letra = "";

		// Llamamos a la función que desordenará el abecedario.
		desordenaAbecedario();

		// Llamamos a la función que eligirá la frase.
		fraseReal = eligeFrase(frases);

		// Llamamos a la función que codificará a la frase.
		codificaFrase(contLetra);

		// TODO: ELIMINAMOS LAS 2 SIGUIENTES LINEAS.
		System.out.println(fraseReal);
		System.out.println(fraseCodificada);

		// Creamos un bucle que comprobará si la frase codificada coincide con la real.
		while (!sonIguales()) {

			// Le preguntamos al usuario cual codigo quiere comprobar.
			System.out.println("¿Cual código quieres comprobar?");

			// Leemos entrada de teclado.
			codigo = sc.nextInt();

			// Le preguntamos al usuario cual letra quiere comprobar.
			System.out.println("¿Cual letra crees que es?");

			// Leemos entrada de teclado.
			letra = sc.next().toLowerCase();

			// Imprimimos mediante el booleano resultante de llamar a la función
			// compruebaCodigo si ha acertado o no.
			System.out.println(compruebaCodigo(letra, codigo) ? "Acertaste" : "Fallaste");

			// Imprimimos la frase codificada.
			System.out.println(fraseCodificada);
		}

		// Imprimimos que el usuario ha ganado la partida.
		System.out.println("¡GANASTE!");

	}

	// Creamos la función que codificará la frase.
	public static void codificaFrase(int contLetra) {

		// Creamos un bucle que irá recorriendo las letras de la frase real.
		while (contLetra < fraseReal.length()) {

			// Si la letra es un espacio, concatenamos a la frase codificada otro espacio.
			if (fraseReal.charAt(contLetra) == ' ') {

				// Concatenamos el espacio.
				fraseCodificada += " ";

				// Si el caracter no es un espacio, entrará en el bloque else,.
			} else {

				// Creamos un bucle el cual irá recorriendo las filas del abecedario.
				for (int i = 0; i < abecedario.length; i++) {

					// Creamos un bucle que irá recorriendo las columnas del abecedario.
					for (int j = 0; j < abecedario[0].length; j++) {

						// Creamos un condicional en el cual, si la letra coincide con el valor
						// almacenado en el abecedario aleatorizado, guardamos sus valores de i y j
						if (fraseReal.charAt(contLetra) == abecedario[i][j]) {

							// Concatenamos las coordenadas de la i y j.
							fraseCodificada += "" + i + j + " ";
						}

					}
				}
			}
			// Incrementamos el contador de letras del bucle while.
			contLetra++;
		}
	}

	// Creamos una función que desordenará el abecedario.
	public static void desordenaAbecedario() {
		// Creamos una variable que corresponderá a las filas.
		int posAleatoriaX;

		// Creamos una variable que corresponderá a las columnas.
		int posAleatoriaY;

		// Creamos una variable auxiliar para ir swapeando valores.
		char aux;

		// Creamos un bucle que irá recorriendo las filas del abecedario.
		for (int i = 0; i < abecedario.length; i++) {

			// Creamos un bucle que irá recorriendo las columnas del abecedario.
			for (int j = 0; j < abecedario[0].length; j++) {

				// Creamos una posición aleatoria entre 0 y los índices del array.
				posAleatoriaX = rnd.nextInt(0, abecedario.length);

				// Creamos una posición aleatoria entre 0 y los índices del array.
				posAleatoriaY = rnd.nextInt(0, abecedario[0].length);

				// Guardamos en una variable auxiliar el valor de la primera posición.
				aux = abecedario[i][j];

				// Guardamos en el primer valor del array el valor de la posición aleatoria.
				abecedario[i][j] = abecedario[posAleatoriaX][posAleatoriaY];

				// Intercambiamos el valor aleatorio con el primer valor.
				abecedario[posAleatoriaX][posAleatoriaY] = aux;

			} // if (patata == true)
		}
	}

	// Creamos una función que elegirá la frase.
	public static String eligeFrase(String[] frases) {
		// Inicializamos la variable frase vacía.
		String frase = "";

		// Seleccionamos la frase al azar.
		frase = frases[rnd.nextInt(0, frases.length)];

		// Retornamos la frase.
		return frase;
	}

	// Creamos la función que comprobará si el usuario acertó.
	public static boolean compruebaCodigo(String letra, int cod) {

		// Creamos una variable booleana que dirá si el usuario acertó.
		boolean esta = false;

		String codigoString = "";

		// Calculamos el valor de la fila.
		int i = cod / 10;

		// Calculamos el valor de la columna.
		int j = cod % 10;

		// Comprobamos si el abecedario con las posiciones del código dado por el
		// usuario coincide con la letra que supone que es.
		if (abecedario[i][j] == letra.charAt(0)) {

			// Si el codigo es inferior de 9, significa que es de 1 sola cifra.
			if (cod <= 9) {

				// Concateno 0 al String.
				codigoString += "0";
			}

			// Añado al String el valor de cod.
			codigoString += cod;

			// Establecemos el valor del booleano en true.
			esta = true;
		}
		// Creamos un condicional en el cual, si ha encontrado la frase o no, entrará.
		if (esta) {

			// Si lo ha encontrado, reemplazamos el codigo con la letra.
			fraseCodificada = fraseCodificada.replace(codigoString, letra);

			// Si no se cumple la condición, entrará en el else.
		} else {

			// Si no lo ha encontrado, le imprimimos que no lo ha encontrado.
			System.out.println("No acertaste, prueba otra vez");
		}
		// Imprimimos la frase codificada.
		System.out.println(fraseCodificada);

		// Ponemos la variable en vacío.
		codigoString = "";

		// Devolvemos el valor del booleano está.
		return esta;
	}

	// Creamos la función que comproborá si la frase real es igual a la codificada.
	public static boolean sonIguales() {

		// Declaramos una variable booleana que dirá si son iguales o no.
		boolean sonIguales;

		// Creamos una variable que almacenará una copia de la frase real.
		String backUpFraseReal = fraseReal;

		// Creamos una variable que almacenará una copia de la frase codificada.
		String backUpFraseCodificada = fraseCodificada;

		// Quitamos los espacios en frase real.
		backUpFraseReal = backUpFraseReal.replace(" ", "");

		// Quitamos los espacios en frase cod.
		backUpFraseCodificada = backUpFraseCodificada.replace(" ", "");

		// Comprobamos si son iguales.
		sonIguales = backUpFraseCodificada.equals(backUpFraseReal);

		// Devolvemos el booleano.
		return sonIguales;
	}
}
