package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsTransacoes;

public class Main {

	// PERMISSOES DOS SEMAFOROS
	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		// INICIANDO AS THREADS
		for (int idThread = 1; idThread<=21; idThread++) {
			Thread tTransacao = new ThreadsTransacoes(idThread, semaforo);
			tTransacao.start();
		}
	}
	

}
