package controller;

import java.util.concurrent.Semaphore;

public class ThreadsTransacoes extends Thread {

	private int idThread;
	private Semaphore semaforo;

	public ThreadsTransacoes(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	public void run() {
		realizarCalculos();
	}

	private void realizarCalculos() {
		int tempoCalculo;
		if (idThread % 3 == 1) {
			int tempoTransacao = 1000;
			tempoCalculo = (int) ((Math.random() * 801) + 200);
			simulacaoTempo(tempoCalculo);
			// ####### INICIO SERIALIZA��O ####### 
												transacaoBanco(tempoTransacao);
			// ####### FIM SERIALIZA��O ####### 
			tempoCalculo = (int) ((Math.random() * 801) + 200);
			simulacaoTempo(tempoCalculo);
			// ####### INICIO SERIALIZA��O ####### 
												transacaoBanco(tempoTransacao);
			// ####### FIM SERIALIZA��O ####### 
		
		 }else if (idThread % 3 == 2) {
			int tempoTransacao = 1500;
			tempoCalculo = (int) ((Math.random() * 1001) + 500);
			simulacaoTempo(tempoCalculo);
												transacaoBanco(tempoTransacao);
			tempoCalculo = (int) ((Math.random() * 1001) + 500);
			simulacaoTempo(tempoCalculo);
												transacaoBanco(tempoTransacao);
			tempoCalculo = (int) ((Math.random() * 1001) + 500);
			simulacaoTempo(tempoCalculo);
												transacaoBanco(tempoTransacao);
			
		} else {
			int tempoTransacao = 1500;
												tempoCalculo = (int) ((Math.random() * 1001) + 1000);
			simulacaoTempo(tempoCalculo);
												transacaoBanco(tempoTransacao);
			tempoCalculo = (int) ((Math.random() * 1001) + 1000);
			simulacaoTempo(tempoCalculo);
			tempoCalculo = (int) ((Math.random() * 1001) + 1000);
			simulacaoTempo(tempoCalculo);
		}

	}

	private void transacaoBanco(int tempo) {
		try {
			semaforo.acquire();
			sleep(tempo);
			System.out.println("ID #" +idThread + " Transa�ao de Banco REALIZADA em " +tempo +".s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void simulacaoTempo(int tempo) {
		try {
			sleep(tempo);
			System.out.println("ID #"+idThread + " Tempo: " +tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
