package nl.hanze;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Jari Germeraad
 *
 */
public class Server {
	public static final int SOCKET = 7789;
	public static final DatabaseHandler databaseHandler = new DatabaseHandler();
	
	public static void main(String[] args) {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(SOCKET);
			Socket connection = null;
			while(true) {
				if((connection = socket.accept()) != null) {
					new IncomingDataHandler(connection).run();
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
