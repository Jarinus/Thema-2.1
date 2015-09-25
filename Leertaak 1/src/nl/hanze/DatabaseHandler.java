package nl.hanze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hanze.cache.MeasurementCache;

/**
 * @author Jari Germeraad
 */
public class DatabaseHandler {
	private static Connection connection;
	private static ArrayList<String> queryBuffer;
	private static MeasurementCache cache;
	
	public static final int BUFFER_SIZE = 3000;
	
	public DatabaseHandler() {
		connection = getConnection();
		queryBuffer = new ArrayList<String>();
		cache = new MeasurementCache();
	}
	
	public static void add(Measurement measurement) {
		addToBuffer(createInsertQuery(measurement));
		cache.add(measurement);
	}
	
	private static String createInsertQuery(Measurement measurement) {
		String insertQuery = "INSERT INTO weatherdata(stn, date, \"time\", temp, dewp, stp, slp, visib, prcp, sndp, frshtt, cldc, wnddir, wdsp) VALUES (";
		ArrayList<Integer> temp;
		if((temp = measurement.missingDataRows()).size() > 0) {
			for(Integer i : temp) {
				supplementMeasurementEntry(i, measurement);
			}
		}
		insertQuery += measurement.getMeasurement(0) + ", ";
		insertQuery += "\'" +  measurement.getMeasurement(1) + "\', ";
		insertQuery += "\'" +  measurement.getMeasurement(2) + "\', ";
		insertQuery += measurement.getMeasurement(3) + ", ";
		insertQuery += measurement.getMeasurement(4) + ", ";
		insertQuery += measurement.getMeasurement(5) + ", ";
		insertQuery += measurement.getMeasurement(6) + ", ";
		insertQuery += measurement.getMeasurement(7) + ", ";
		insertQuery += measurement.getMeasurement(8) + ", ";
		insertQuery += measurement.getMeasurement(9) + ", ";
		insertQuery += "B\'" +  measurement.getMeasurement(10) + "\', ";
		insertQuery += measurement.getMeasurement(11) + ", ";
		insertQuery += measurement.getMeasurement(12) + ", ";
		insertQuery += measurement.getMeasurement(13) + ");";
		return insertQuery;
	}
	
	private static void supplementMeasurementEntry(int index, Measurement measurement) {
		double temp = cache.getAverage(Integer.parseInt(measurement.getMeasurement(Measurement.STN)), index);
		measurement.setMeasurement(index, String.valueOf(temp));
	}
	
	private static void addToBuffer(String query) {
		synchronized(queryBuffer) {
			if(queryBuffer.size() >= BUFFER_SIZE) {
				writeBatchToDatabase(queryBuffer);
				queryBuffer = new ArrayList<String>();
			}
			queryBuffer.add(query);
		}
	}
	
	private static void writeBatchToDatabase(ArrayList<String> queryArray) {
		Statement statement;
		try {
			statement = connection.createStatement();
			
			for(String query : queryArray) {
				if(query == null)
					continue;
				statement.addBatch(query);
			}
			
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		String  url  = "jdbc:postgresql://localhost/leertaak1",
				user = "postgres",
				pass = "root";
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
