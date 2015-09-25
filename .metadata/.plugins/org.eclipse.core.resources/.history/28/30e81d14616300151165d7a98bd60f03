package nl.hanze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
/**
 * 
 * @author Jari Germeraad
 *
 */
public class IncomingDataHandler implements Runnable {
	private Socket connection;
	
	public IncomingDataHandler(Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		String input = getInputFromConnection();
		Document document = getXMLFromString(input);
		Server.databaseHandler.writeToDatabase(document);
	}
	
	private String getInputFromConnection() {
		String input = "";
		try {
			//Grab XML data.
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line = null;
			
			while((line = in.readLine()) != null) {
				input += line;
				if(line.contains("</WEATHERDATA>"))
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return input;
	}
	
	private Document getXMLFromString(String xmlString) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource input = new InputSource(new StringReader(xmlString));
			return builder.parse(input);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
