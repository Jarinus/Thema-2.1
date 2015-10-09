package nl.hanze.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import nl.hanze.Measurement;


public class StorageUnit implements Runnable {
	private Semaphore available;
	private BlockingQueue<Measurement> queue;
	
	public StorageUnit(BlockingQueue<Measurement> queue) {
		available = new Semaphore(1, true);
		this.queue = queue;
	}
	
	public void run() {
		try {
			available.acquire();
		} catch (InterruptedException e) {
			System.err.println("StorageUnit write operation was interrupted.");
		}
		
		String toWrite = "";
		
		Measurement m;
		
		while(queue.size() > 0) {
			m = null;
			
			try {
				m = queue.take();
			} catch (InterruptedException e) {}
			
			toWrite += convertToBinaryRepresentation(m);
		}
		
		System.out.println(toWrite);
		
		available.release();
	}
	
	private String convertToBinaryRepresentation(Measurement m) {
		String binaryRepresentation = "",
				temp = "";
		
		
		
		return binaryRepresentation;
	}
}
