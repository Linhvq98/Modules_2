package Prediction;

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
	
	public double[] calculateIndicator() {
		double[] indicator = new double[5];
		
		Marker m = new Marker();
		String jsonString = m.getJSONDataString();
		int numberRecord = m.getNumberOfReCord(jsonString);
		Marker[] marker = new Marker[numberRecord];
		marker = m.createArrayData(numberRecord);
		
		indicator[0] = useAlgorithm_1(marker);
		indicator[1] = useAlgorithm_2(marker);
		indicator[2] = useAlgorithm_3(marker);
		indicator[3] = useAlgorithm_4(marker);
		indicator[4] = useAlgorithm_5(marker);
		
		// example
		indicator[0] = useAlgorithm_example(marker, numberRecord);
		
		return indicator;
	}
	
	/* 
	 * The groups perform their algorithms here
	 * use data in 'rawData' array
	 * 'rawData' array contains data: lat, lng, speed, distance, vehicle, direct, record_time
	*/
	
	// this is a example
	private double useAlgorithm_example(Marker rawData[], int numberRecord) {
		double indicator = 0;
		
		// implement here
		// algorithm example: use 'avg_speed' to determine 'indicator'
		double totalSpeed = 0;
		for(int i=0; i<numberRecord; i++) {
			totalSpeed += rawData[i].getSpeed();
		}
		double avgSpeed = (double) totalSpeed / numberRecord;
		
		if(avgSpeed < 1) {
			indicator = 1;
		} else if(avgSpeed >= 1 && avgSpeed < 3) {
			indicator = 2;
		} else if(avgSpeed >= 3 && avgSpeed < 5) {
			indicator = 3;
		} else{
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
		
		// just a example
		if(indicator == 1) {
			color = "#ffffff";
		} else if(indicator == 2) {
			color = "#ff0000";
		} else if(indicator == 3) {
			color = "#e1ff00";
		} else {
			color = "#00ff00";
		}
		
		return color;
	}
	
	public static void main(String [] args) {
		Cells_detail c = new Cells_detail();
		System.out.println("indicator use algorithm example: " + c.calculateIndicator()[0]);
		System.out.println("color: " + c.mapColor(c.calculateIndicator()[0]));
	}
}
