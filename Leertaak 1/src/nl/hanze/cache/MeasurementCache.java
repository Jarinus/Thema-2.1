package nl.hanze.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.hanze.Measurement;

public class MeasurementCache {
	Map<Integer, ArrayList<Measurement>> cache;
	public static final int CACHE_SIZE = 30;
	
	public MeasurementCache() {
		cache = new HashMap<Integer, ArrayList<Measurement>>();
	}
	
	public synchronized double getAverage(int station, int index) {
		ArrayList<Measurement> temp = cache.get(station);
		if(temp == null)
			return 0.0;
		double total = 0.0;
		for(Measurement m : temp) {
			total += Double.parseDouble(m.getMeasurement(index));
		}
		return total / temp.size();
	}
	
	public synchronized void add(Measurement measurement) {
		int station = Integer.parseInt(measurement.getMeasurement(Measurement.STN));
		ArrayList<Measurement> temp = cache.get(station);
		if(temp == null)
			cache.put(station, (temp = new ArrayList<Measurement>()));
		if(temp.size() >= CACHE_SIZE)
			temp.remove(0);
		temp.add(measurement);
	}
}
