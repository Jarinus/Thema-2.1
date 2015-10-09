package nl.hanze;

import java.net.ServerSocket;
import java.net.Socket;

import nl.hanze.storage.StorageManager;

/**
 * @author Jari Germeraad
 */
public class Server {
	public static final int SOCKET = 7789;
	
	public static void main(String[] args) {
		ServerSocket socket = null;
		new StorageManager();
		try {
			socket = new ServerSocket(SOCKET, 800);
			Socket connection = null;
			while(true) {
				if((connection = socket.accept()) != null) {
					new Thread(new IncomingDataHandler(connection)).start();
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
