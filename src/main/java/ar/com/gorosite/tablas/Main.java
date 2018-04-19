package ar.com.gorosite.tablas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
	
	private static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		
		int cuantas=0;
		long comienzo=0;
		int respuestasCorrectas=0;
		int respuestasIncorrectas=0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			cuantas = pideNumeroNatural("¿Cuantas cuentas?", br);
			int hastaQueNumero = pideNumeroNatural("¿Hasta qué número?", br);
			
			System.out.println("Ok... comenzamos...\n");
			
			comienzo = System.currentTimeMillis();
			
			for (int i=0; i<cuantas ; i++ ) {
				
				int x = numeroAleatorio(hastaQueNumero);
				int y = numeroAleatorio(10);

				int respuesta = pideNumeroNatural("¿Cuanto es ... "+x+" x "+y+" ?", br);
				
				if ( respuesta == x*y ) {
					respuestasCorrectas++;
					System.out.println("BIEN!");
				} else {
					respuestasIncorrectas++;
					System.out.println("no :-(");
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long fin = System.currentTimeMillis();
		
		long demoroSegundos = (fin-comienzo)/1000;
		
		System.out.println("Terminamos!!!\n");
		System.out.println("Respuestas correctas:"+respuestasCorrectas);
		System.out.println("Respuestas incorrectas:"+respuestasIncorrectas);
		System.out.println("Tiempo total en segundos:"+demoroSegundos);
		System.out.println("Tiempo promedio por cuenta:"+demoroSegundos/cuantas);

	}
	
	private static int numeroAleatorio(int maximo) {
		if ( maximo < 2 ) {
			return 1;
		}
		return random.nextInt(maximo-1)+1;
	}
	
	private static int pideNumeroNatural(String pregunta, BufferedReader br) throws IOException {

		while ( true ) {
			
			System.out.println(pregunta);

			try {
				String linea = br.readLine();
				Integer i = Integer.valueOf(linea);
				if ( i >= 1 ) {
					return i;
				} else {
					muestraMensajeError("Debe ingresar un número entero mayor o igual a uno.");
				}
			} catch (NumberFormatException e) {
				muestraMensajeError("Eso no es un número!");
			}
		}
	}
	
	private static void muestraMensajeError(String s) {
		System.err.println(s);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
	}



}
