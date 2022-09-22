package controller;

import java.util.concurrent.Semaphore;

public class ThreadsPratos extends Thread {

	private int idPrato;
	private Semaphore semaforo;

	public ThreadsPratos(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	public void run() {
		preparandoPrato();
		// ################## INICIO DA SEÇÃO CRITICA ################## //
		try {
			semaforo.acquire();
			entregandoPrato();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		// ################## FINAL DA SEÇÃO CRITICA ################## //
	}

	private void preparandoPrato() {
		String nomePrato;
		int tempoMaximo;
		if (idPrato % 2 == 1) {
			tempoMaximo = (int) ((Math.random() * 301) + 500);
			nomePrato = "SOPA DE CEBOLA";
		} else {
			tempoMaximo = (int) ((Math.random() * 601) + 600);
			nomePrato = "LASANHA";
		}
		System.out.println("#" + idPrato + " " + nomePrato + " INICIOU ");
		int preparoPercorrido = 0;
		int deslocamento = 100;

		while (preparoPercorrido < tempoMaximo) {
			preparoPercorrido = deslocamento + preparoPercorrido;
			// Parte que garante que não passará de 100%
			if (preparoPercorrido > tempoMaximo) {
				preparoPercorrido = tempoMaximo;
			}
			try {
				int tempo = 100; // 0,1 segundos
				sleep(tempo);
				System.out.println("#" + idPrato + " ESTÁ COM " + (preparoPercorrido * 100) / tempoMaximo + "%");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void entregandoPrato() {
		int tempo = 500; // milisegundos
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("	PRATO " + "#" + idPrato + " --- FOI ENTREGUE --- ");
	}

}
