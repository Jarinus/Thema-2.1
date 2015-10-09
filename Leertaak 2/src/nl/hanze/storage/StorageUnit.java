package nl.hanze.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import nl.hanze.Measurement;


public class StorageUnit implements Runnable {
	private Semaphore available;
	private BlockingQueue<Measurement> queue;
	private File storageFile;
	
	public static final String FILE_PATH = ".",
							   FILE_NAME = "Measurement Data.txt";
	
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
			
			toWrite += convertToString(m);
		}
		
		System.out.println(toWrite);
		
		try {
			openFile();
		} catch (IOException ioe) {
			System.err.println("Could not create file.");
		}
		
		try {
			write(toWrite);
		} catch (IOException ioe) {
			System.err.println("Could not write data.");
		}
		
		available.release();
	}
	
	private String convertToString(Measurement m) {
		String temp = "";
		
		temp += "\n";
		for(int i = 0; i < 14; i++) {
			temp += m.getMeasurement(i);
			if(i != 13) {
				temp += ",";
			}
		}
		
		//TODO: Improve algorithm where necessary.
		
		return temp;
	}
	
	private void write(String data) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(storageFile, true));
		
		out.write(data);
		out.flush();
		
		out.close();
	}
	
	private void openFile() throws IOException {
		storageFile = new File(FILE_PATH + "/" + FILE_NAME);
		
		if(!storageFile.exists())
			storageFile.createNewFile();
	}
}
