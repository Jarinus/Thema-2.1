package nl.jgermeraad;

import java.util.concurrent.Semaphore;


public class BridgeSimulation {
	private Semaphore bridge;
	
	public static void main(String[] args) {
		new BridgeSimulation();
	}
	
	public BridgeSimulation() {
		bridge = new Semaphore(1, true);
		new Thread(new Farmer("Northern farmer")).start();
		new Thread(new Farmer("Southern farmer")).start();
	}
	
	private class Farmer implements Runnable {
		private String name;
		private int sleepTime;
		
		public Farmer(String name) {
			this.name = name;
			sleepTime = 1000;
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					bridge.acquire();
					System.out.println(name + " is crossing the bridge.");
					Thread.sleep(sleepTime);
					bridge.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
