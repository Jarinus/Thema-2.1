package nl.hanze;

import java.util.ArrayList;

/**
 * @author Jari Germeraad
 */
public class Measurement {
	public static final int STN = 0, DATE = 1, TIME = 2, TEMP = 3, DEWP = 4, STP = 5,
			SLP = 6, VISIB = 7, PRCP = 8, SNDP = 9, FRSHTT = 10, CLDC = 11, WNDDIR = 12,
			WDSP = 13;
	private String[] values;
	
	public Measurement() {
		values = new String[14];
	}
	
	public void setMeasurement(int index, String value) {
		if(index < 0 || index > 13)
			return;
		values[index] = value;
	}
	
	public String getMeasurement(int index) {
		if(index < 0 || index > 13)
			return null;
		return values[index];
	}
	
	public ArrayList<Integer> missingDataRows() {
		ArrayList<Integer> missingDataRows = new ArrayList<Integer>();
		for(int i = 0; i < values.length; i++) {
			if(values[i] == "" || values[i] == null)
				missingDataRows.add(i);
		}
		return missingDataRows;
	}
}
