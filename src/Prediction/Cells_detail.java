package Prediction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cells_detail {
	private int x_axis, y_axis, id_cell, marker_count, algorithm;
	private String start_time, end_time, color;
	private double avg_speed, indicator;

	public int getX_axis() {
		return x_axis;
	}

	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}

	public int getY_axis() {
		return y_axis;
	}

	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}

	public int getId_cell() {
		return id_cell;
	}

	public void setId_cell(int id_cell) {
		this.id_cell = id_cell;
	}

	public int getMarker_count() {
		return marker_count;
	}

	public void setMarker_count(int marker_count) {
		this.marker_count = marker_count;
	}

	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getAvg_speed() {
		return avg_speed;
	}

	public void setAvg_speed(double avg_speed) {
		this.avg_speed = avg_speed;
	}

	public double getIndicator() {
		return indicator;
	}

	public void setIndicator(double indicator) {
		this.indicator = indicator;
	}

	public Cells_detail() {
		super();
	}

	public Cells_detail(int x_axis, int y_axis, int id_cell, int marker_count, int algorithm, String start_time,
			String end_time, String color, double avg_speed, double indicator) {
		super();
		this.x_axis = x_axis;
		this.y_axis = y_axis;
		this.id_cell = id_cell;
		this.marker_count = marker_count;
		this.algorithm = algorithm;
		this.start_time = start_time;
		this.end_time = end_time;
		this.color = color;
		this.avg_speed = avg_speed;
		this.indicator = indicator;
	}

	public double calculateIndicator(int algorithm) {
		double indicator = 0;

		Marker m = new Marker();
		String jsonString = m.getJSONDataString();
		int numberRecord = m.getNumberOfReCord(jsonString);
		Marker[] marker = new Marker[numberRecord];
		marker = m.createArrayData(numberRecord);

		switch (algorithm) {
			case 0:{
				indicator = useAlgorithm_example(marker, numberRecord);
				break;
			}
			case 1:{
				indicator = useAlgorithm_1(marker);
				break;
			}
			case 2:{
				indicator = useAlgorithm_2(marker);
				break;
			}
			case 3:{
				indicator = useAlgorithm_3(marker);
				break;
			}
			case 4:{
				indicator = useAlgorithm_4(marker);
				break;
			}
			case 5:{
				indicator = useAlgorithm_5(marker);
				break;
			}
			default:{
				break;
			}
			
		}

		return indicator;
	}

	/*
	 * The groups perform their algorithms here use data in 'rawData' array
	 * 'rawData' array contains data: lat, lng, speed, distance, vehicle, direct,
	 * record_time
	 */

	// this is a example
	private double useAlgorithm_example(Marker rawData[], int numberRecord) {
		double indicator = 0;

		// implement here
		// algorithm example: use 'avg_speed' to determine 'indicator'
		double totalSpeed = 0;
		for (int i = 0; i < numberRecord; i++) {
			totalSpeed += rawData[i].getSpeed();
		}
		double avgSpeed = (double) totalSpeed / numberRecord;

		if (avgSpeed < 1) {
			indicator = 1;
		} else if (avgSpeed >= 1 && avgSpeed < 3) {
			indicator = 2;
		} else if (avgSpeed >= 3 && avgSpeed < 5) {
			indicator = 3;
		} else {
			indicator = 4;
		}
		return indicator;
	}

	// team 1
	private double useAlgorithm_1(Marker rawData[]) {
		double indicator = 1;

		// implement here

		return indicator;
	}

	// team 2
	private double useAlgorithm_2(Marker rawData[]) {
		double indicator = 2;

		// implement here

		return indicator;
	}

	// team 3
	private double useAlgorithm_3(Marker rawData[]) {
		double indicator = 3;

		// implement here

		return indicator;
	}

	// team 4
	private double useAlgorithm_4(Marker rawData[]) {
		double indicator = 4;

		// implement here

		return indicator;
	}

	// team 5
	private double useAlgorithm_5(Marker rawData[]) {
		double indicator = 5;

		// implement here

		return indicator;
	}

	public String mapColor(double indicator) {
		String color = "";

		// just an example
		if (indicator == 1) {
			color = "#ffffff";
		} else if (indicator == 2) {
			color = "#ff0000";
		} else if (indicator == 3) {
			color = "#e1ff00";
		} else {
			color = "#00ff00";
		}

		return color;
	}

	// convert array cells detail to JSON
	public String getResultJSON(Cells_detail data[], int numberRecord) {
		JSONObject obj = new JSONObject();
		JSONArray obj2 = new JSONArray();
		JSONObject obj3 = new JSONObject();
		try {
			for(int i=0; i<numberRecord; i++) {
				obj.put("x_axis", new Integer(data[i].getX_axis()));
				obj.put("y_axis", new Integer(data[i].getY_axis()));
				obj.put("start_time", new String(data[i].getStart_time()));
				obj.put("end_time", new String(data[i].getEnd_time()));
				obj.put("id_cell", new Integer(data[i].getId_cell()));
				obj.put("avg_speed", new Double(data[i].getAvg_speed()));
				obj.put("marker_count", new Integer(data[i].getMarker_count()));
				obj.put("indicator", new Double(data[i].getIndicator()));
				obj.put("algorithm", new Integer(data[i].getAlgorithm()));
				obj.put("color", new String(data[i].getColor()));
				obj2.put(i, obj);
			}
			obj3.put("data", obj2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj3.toString();
	}
	
	// get date now 
	public String getDate(Date d) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(d).toString();
	}
	
	//get date after m minute
	public String getDate(Date d, int m) {
		d = new Date(System.currentTimeMillis() + m*60*1000);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(d).toString();
		
	}
	
	// chua viet
	public double calculateTotalSpeedOfACell() {
		return 0;
	}
	
	// chua lam
	public int calculateMarkerCount() {
		return 1;
	}
	
	// create array data cells detail
	public Cells_detail[] createArrayCellsDetail() {
		// 5 thuat toan
		// 5 muc zoom: 6*13, 12*27, 24*54, 48*108, 96*216
		// -> tong so cell la:
		int numberRecord = 5 * (6*13 + 12*27 + 24*54 + 48*108 + 96*216); 
		Cells_detail[] data = new Cells_detail[numberRecord];
		
		Cells_detail c = new Cells_detail();
		for(int algorithm = 1; algorithm <=5; algorithm++) {
			for(int i=0; i<numberRecord; i++) {
				Date now = new Date();
				
				data[i].setX_axis(i); // whereX
				data[i].setY_axis(i); // whereY
				data[i].setStart_time(c.getDate(now)); // now -> done
				data[i].setEnd_time(c.getDate(now, 30)); // after x minute (eg: 30 min) -> done
				data[i].setId_cell(i); // layer
				data[i].setAvg_speed(c.calculateTotalSpeedOfACell() / c.calculateMarkerCount()); // sum(speed in this cell) / number record of this cell
				data[i].setMarker_count(c.calculateMarkerCount()); // number record of this cell
				data[i].setIndicator(c.calculateIndicator(algorithm)); // calculator
				data[i].setAlgorithm(algorithm); // belong to which team
				data[i].setColor(c.mapColor(data[i].getIndicator())); // use indicator to determine
			
			}
		}
		
		
		return data;
	}

	public static void main(String[] args) {
		Cells_detail c = new Cells_detail();
		/*
		 * System.out.println("indicator use algorithm example: " +
		 * c.calculateIndicator()[0]); System.out.println("color: " +
		 * c.mapColor(c.calculateIndicator()[0]));
		 */
		/*int numberRecord = 5;
		Cells_detail []data = new Cells_detail[numberRecord];
		System.out.println(c.getResultJSON(data, numberRecord));*/
		Date d = new Date();
		System.out.println(c.getDate(d, 30));
	}
}
