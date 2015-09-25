package nl.hanze.parser;

import nl.hanze.DatabaseHandler;
import nl.hanze.Measurement;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {
	String currentTag = null;
	Measurement measurement = null;
	
	@Override
	public void startElement(String url, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentTag = qName.toLowerCase();
		if(currentTag.equals("weatherdata"))
			measurement = new Measurement();
	}
	
	@Override
	public void endElement(String url, String localName,
			String qName) throws SAXException {
		currentTag = null;
		if(qName.toLowerCase().equals("weatherdata"))
			DatabaseHandler.add(measurement);
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if(currentTag != null)
			switch(currentTag) {
			case "stn":
				measurement.setMeasurement(0, new String(ch, start, length));
				break;
			case "date":
				measurement.setMeasurement(1, new String(ch, start, length));
				break;
			case "time":
				measurement.setMeasurement(2, new String(ch, start, length));
				break;
			case "temp":
				measurement.setMeasurement(3, new String(ch, start, length));
				break;
			case "dewp":
				measurement.setMeasurement(4, new String(ch, start, length));
				break;
			case "stp":
				measurement.setMeasurement(5, new String(ch, start, length));
				break;
			case "slp":
				measurement.setMeasurement(6, new String(ch, start, length));
				break;
			case "visib":
				measurement.setMeasurement(7, new String(ch, start, length));
				break;
			case "prcp":
				measurement.setMeasurement(8, new String(ch, start, length));
				break;
			case "sndp":
				measurement.setMeasurement(9, new String(ch, start, length));
				break;
			case "frshtt":
				measurement.setMeasurement(10, new String(ch, start, length));
				break;
			case "cldc":
				measurement.setMeasurement(11, new String(ch, start, length));
				break;
			case "wnddir":
				measurement.setMeasurement(12, new String(ch, start, length));
				break;
			case "wdsp":
				measurement.setMeasurement(13, new String(ch, start, length));
				break;
			default:
				break;
			}
	}
}
