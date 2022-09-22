package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsPratos;

public class Main {

	// PERMISSOES DOS SEMAFOROS
	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		// INICIANDO AS THREADS
		for (int idPrato = 1; idPrato<=5; idPrato++) {
			Thread tPrato = new ThreadsPratos(idPrato, semaforo);
			tPrato.start();
		}
	}
	

}
