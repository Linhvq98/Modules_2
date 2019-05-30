package Prediction;

import java.io.IOException;
import java.util.Date;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
		int numberRecord = m.getNumberOfReCord(jsonString) * 5;
		Marker[] rawData = new Marker[numberRecord];
		rawData = m.createArrayData(jsonString, numberRecord);

		switch (algorithm) {
		case 0: {
			indicator = useAlgorithm_example(rawData, numberRecord);
			break;
		}
		case 1: {
			indicator = useAlgorithm_1(rawData);
			break;
		}
		case 2: {
			indicator = useAlgorithm_2(rawData);
			break;
		}
		case 3: {
			indicator = useAlgorithm_3(rawData);
			break;
		}
		case 4: {
			indicator = useAlgorithm_4(rawData);
			break;
		}
		case 5: {
			indicator = useAlgorithm_5(rawData, numberRecord);
			break;
		}
		default: {
			break;
		}

		}

		return indicator;
	}

	/*
	 * The groups perform their algorithms here use data in 'rawData' array
	 * 'rawData' array contains data: lat, lng, speed, distance, vehicle, direct, record_time
	 */

	// this is a example
	private double useAlgorithm_example(Marker rawData[], int numberRecord) {
		double indicator = 0;

		// implement here
		// algorithm example: use 'avg_speed' to determine 'indicator'
		double totalSpeed = 0;
		int recordOfLayer1 = 0;
		for (int i = 0; i < numberRecord; i++) {
			if (rawData[i].getLayer() == 1) {
				totalSpeed += rawData[i].getSpeed();
				recordOfLayer1++;
			}
		}
		double avgSpeed = (double) totalSpeed / recordOfLayer1;

		System.out.println("avg_speed: " + avgSpeed);
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
	private double useAlgorithm_5(Marker rawData[], int numberRecord) {
		double indicator = 0;
		double sumSpeed = 0, markerCount = 0, density = 0, coverage = 0;
		int [] recordUser = new int[1000];
		int totalDensity = 0;
		//double maxDensity = ;
		//double maxDensity = ;

		for (int i = 0; i < numberRecord; i++) {
			if (rawData[i].getLayer() == this.getId_cell()) {
				if (rawData[i].getX_axis() == this.getX_axis() && rawData[i].getY_axis() == this.getY_axis()) {
					int check=0;
					for(int j=0; j < totalDensity; i++){
						if(rawData[i].getRecord_user()==recordUser[j]){
							check=1;
							break;
						}
					}
					if(check==0){
						recordUser[totalDensity]=rawData[i].getRecord_user();
						totalDensity++;
					}
					markerCount++;
					sumSpeed += rawData[i].getSpeed();
				}
			}
		}

		this.setAvg_speed(sumSpeed/markerCount);
		//coverage = (density/maxDensity)*100;

		if(this.getAvg_speed()>30){  //if(coverage<40||this.getAvg_speed()>30){
			indicator = 4;
		} else if(20<this.getAvg_speed()&&this.getAvg_speed()<=30){
			indicator = 3;
		} else if(10<this.getAvg_speed()&&this.getAvg_speed()<=20) {
			indicator = 2;
		} else {
			indicator = 1;
		}

		return indicator;
	}

	public String mapColor(double indicator) {
		String color = "";

		// just an example
		if (indicator == 1) {
			color = "#ffffff"; // white
		} else if (indicator == 2) {
			color = "#00ff00"; // red
		} else if (indicator == 3) {
			color = "#e1ff00"; // yellow
		} else {
			color = "#ff0000"; // green
		}

		return color;
	}

	// convert array cells detail to JSON
	public String getResultJSON(Cells_detail data[], int numberRecord) {
		JSONObject obj = new JSONObject();
		JSONArray obj2 = new JSONArray();
		JSONObject obj3 = new JSONObject();
		try {
			for (int i = 0; i < numberRecord; i++) {
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

	// done
	public double calculateTotalSpeedOfACell(Marker rawData[], int numberRecord, int whereX, int whereY, int layer) {
		int totalSpeed = 0;
		for (int i = 0; i < numberRecord; i++) {
			if (rawData[i].getLayer() == layer) {
				if (rawData[i].getX_axis() == whereX && rawData[i].getY_axis() == whereY) {
					totalSpeed += rawData[i].getSpeed();
				}
			}

		}
		return totalSpeed;
	}

	// done
	public int calculateMarkerCountOfACell(Marker rawData[], int numberRecord, int whereX, int whereY, int layer) {
		int totalRecord = 0;
		for (int i = 0; i < numberRecord; i++) {
			if (rawData[i].getLayer() == layer) {
				if (rawData[i].getX_axis() == whereX && rawData[i].getY_axis() == whereY) {
					totalRecord++;
				}
			}

		}
		return totalRecord;
	}

	// create array data cells detail
	public Cells_detail[] createArrayCellsDetail(int recordData, Marker rawData[], int recordRawData, int algorithm) {
		// voi 1 thuat toan
		// 5 muc zoom: 6*13, 12*27, 24*54, 48*108, 96*216
		// -> tong so ban ghi cells_detail can tao ra tu 1 thuat toan la: 27618

		Cells_detail[] data = new Cells_detail[recordData];

		Cells_detail c = new Cells_detail();
		System.out.println("in create array cell detail");
		System.out.println(recordRawData);
		
		Date now = new Date();
		FormatDate fd = new FormatDate();
		
		for (int i = 0; i < recordData;) {
			for (int j = 0; j < recordRawData; j++) {
				data[i] = new Cells_detail();

				int x = rawData[i].getX_axis();
				int y = rawData[i].getY_axis();
				int lay = rawData[i].getLayer();

				System.out.println(x + " " + y + " " + lay);
				
				// id_cell: layer ->done
				data[i].setId_cell(lay);

				// x_axis: whereX
				data[i].setX_axis(x);

				// y_axis: whereY
				data[i].setY_axis(y);

				// start_time: now() -> done
				data[i].setStart_time(fd.getDate(now));

				// end_time: after x minute (eg: 30 min) -> done
				data[i].setEnd_time(fd.getDate(now, 30));

				// avg_speed: sum(speed in this cell) / number record of this cell -> done
				data[i].setAvg_speed(c.calculateTotalSpeedOfACell(rawData, recordRawData, x, y, lay)
						/ c.calculateMarkerCountOfACell(rawData, recordRawData, x, y, lay));

				// marker_count: number record of this cell -> done
				data[i].setMarker_count(c.calculateMarkerCountOfACell(rawData, recordRawData, x, y, lay));

				// indicator: calculator indicator -> done
				data[i].setIndicator(c.calculateIndicator(algorithm));

				// algorithm -> done
				data[i].setAlgorithm(algorithm);

				// color: use indicator to determine ->done
				data[i].setColor(c.mapColor(data[i].getIndicator()));
				
				System.out.println(i);
				i++;
				
			}
		}

		return data;
	}

	// B4: gui chuoi json di
	private static final String url = "http://68.183.238.65:8000/api/data/savedata";
	private void sendResultJSON(String json) throws IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
		    HttpPost request = new HttpPost(url);
		    StringEntity params = new StringEntity(json.toString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    httpClient.execute(request);
		// handle response here...
		} catch (Exception ex) {
		    // handle exception here
		} finally {
		    httpClient.close();
		}
	}

	public static void runProcess(int algorithm) throws IOException {
		// B1: tao mang rawData (markers)
		Marker m = new Marker();
		String jsonString = m.getJSONDataString();
		System.out.println(jsonString);
		int recordRawData = m.getNumberOfReCord(jsonString) * 5;
		Marker[] rawData = new Marker[recordRawData];
		rawData = m.createArrayData(jsonString , recordRawData);
		System.out.println("1");

		// B2: tu mang rawData -> tao ra mang data (cellss_detail)
		Cells_detail c = new Cells_detail();
		int recordData = (6 * 13 + 12 * 27 + 24 * 54 + 48 * 108 + 96 * 216);
		Cells_detail[] data = new Cells_detail[recordData];
		data = c.createArrayCellsDetail(recordData, rawData, recordRawData, algorithm);
		System.out.println("2");

		// B3: Convert mang data thanh chuoi json
		String resultJSON = c.getResultJSON(data, recordData);
		System.out.println(resultJSON);
		System.out.println("3");
		
		// B4: gui chuoi json di
		c.sendResultJSON(resultJSON);
	}

	public static void main(String[] args) throws IOException {
		/*for(int i=1; i<=5; i++) {
			Cells_detail.runProcess(i);
		}*/
		Cells_detail.runProcess(0);
	}
}
