package nl.hanze.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.hanze.Measurement;

public class MeasurementCache {
	Map<Integer, ArrayList<Measurement>> cache;
	
	public MeasurementCache() {
		cache = new HashMap<Integer, ArrayList<Measurement>>();
	}
	
	public void add(Measurement measurement) {
		int station = Integer.parseInt(measurement.getMeasurement(Measurement.STN));
		ArrayList<Measurement> temp = cache.get(station);
		if(temp == null)
			cache.put(station, (temp = new ArrayList<Measurement>()));
		if(temp.size() > 29)
			temp.remove(0);
		temp.add(measurement);
	}
}
