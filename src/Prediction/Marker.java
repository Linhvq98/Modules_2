package Prediction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Marker {
	private static final String url = "http://68.183.238.65:8000/api/data/markers";
	private int id, record_user, x_axis, y_axis, layer;
	private double lat, lng, speed, distance;
	private String vehicle, direct, created_at, updated_at, record_time;

	int[][] grid = { { 6, 13 }, { 12, 27 }, { 24, 54 }, { 48, 108 }, { 96, 216 } };

	double[] news = { 21.16, 105.92, 105.46, 20.95 };

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecord_user() {
		return record_user;
	}

	public void setRecord_user(int record_user) {
		this.record_user = record_user;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getRecord_time() {
		return record_time;
	}

	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}

	public static String getUrl() {
		return url;
	}

	public Marker() {
		super();

	}

	public Marker(double lat, double lng, double speed, double distance, String vehicle, String direct,
			String record_time) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.speed = speed;
		this.distance = distance;
		this.vehicle = vehicle;
		this.direct = direct;
		this.record_time = record_time;
	}

	public String getJSONDataString() {
		StringBuffer response = new StringBuffer();

		try {
			URL obj = new URL(Marker.getUrl());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			Date d = new Date();
			FormatDate fd = new FormatDate();

			// SELECT * FROM markers WHERE 'record_time' >= 'start_time' AND 'record_time'
			// <= 'end_time'
			con.setRequestProperty("start_time", fd.getDate(d, -30)); // 30 min before
			con.setRequestProperty("end_time", fd.getDate(d)); // now

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return response.toString();
	}

	public int getNumberOfReCord(String jsonString) {
		int numberOfRecord = 0;
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			numberOfRecord = jsonArray.length();
		} catch (Exception e) {
			System.out.println(e);
		}

		return numberOfRecord;
	}

	public void displayData() {
		try {

			String jsonString = this.getJSONDataString();
			System.out.println("JSON string: " + jsonString);

			int numberRecord = this.getNumberOfReCord(jsonString);
			System.out.println("number of record: " + numberRecord);

			JSONArray jsonArray = new JSONArray(jsonString);
			for (int i = 0; i < numberRecord; i++) {
				JSONObject aRecord = new JSONObject(jsonArray.getJSONObject(i).toString());
				System.out.println("----");
				System.out.println("id: " + aRecord.getInt("id"));
				System.out.println("lat: " + aRecord.getDouble("lat"));
				System.out.println("lng: " + aRecord.getDouble("lng"));
				System.out.println("speed: " + aRecord.getDouble("speed"));
				System.out.println("vehicle: " + aRecord.getString("vehicle"));
				System.out.println("direct: " + aRecord.getString("direct"));
				System.out.println("distance: " + aRecord.getDouble("distance"));
				System.out.println("record_user: " + aRecord.getInt("record_user"));
				System.out.println("record_time: " + aRecord.getString("record_time"));
				System.out.println("created_at: " + aRecord.getString("created_at"));
				System.out.println("updated_at: " + aRecord.getString("updated_at"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void displayARecord(Marker aRecord) {
		System.out.println("id: " + aRecord.getId());
		System.out.println("lat: " + aRecord.getLat());
		System.out.println("lng: " + aRecord.getLng());
		System.out.println("speed: " + aRecord.getSpeed());
		System.out.println("vehicle: " + aRecord.getVehicle());
		System.out.println("direct: " + aRecord.getDirect());
		System.out.println("distance: " + aRecord.getDistance());
		System.out.println("record_user: " + aRecord.getRecord_user());
		System.out.println("record_time: " + aRecord.getRecord_time());
		System.out.println("created_at: " + aRecord.getCreated_at());
		System.out.println("updated_at: " + aRecord.getUpdated_at());
		System.out.println("x_axis: " + aRecord.getX_axis());
		System.out.println("y_axis: " + aRecord.getY_axis());
		System.out.println("layer: " + aRecord.getLayer());
	}

	public Marker[] createArrayData(int numberRecord) {
		String jsonString = this.getJSONDataString();

		int root = numberRecord / 5;
		//System.out.println("root: " + root);
		//System.out.println("num: " + numberRecord);
		Marker[] data = new Marker[numberRecord];
		try {
			JSONArray jsonArray = new JSONArray(jsonString);

			for (int i = 0; i < numberRecord;) {
				//System.out.println("record: " + (i % root));
				for (int lay = 0; lay <= 4; lay++) {
					//System.out.println("layer: " + lay);
					
					JSONObject aRecord = new JSONObject(jsonArray.getJSONObject(i % root).toString());
					data[i] = new Marker();
					data[i].setId(aRecord.getInt("id"));
					data[i].setLat(aRecord.getDouble("lat"));
					data[i].setLng(aRecord.getDouble("lng"));
					data[i].setSpeed(aRecord.getDouble("speed"));
					data[i].setVehicle(aRecord.getString("vehicle"));
					data[i].setDirect(aRecord.getString("direct"));
					data[i].setDistance(aRecord.getDouble("distance"));
					data[i].setRecord_user(aRecord.getInt("record_user"));
					data[i].setRecord_time(aRecord.getString("record_time"));
					data[i].setCreated_at(aRecord.getString("created_at"));
					data[i].setUpdated_at(aRecord.getString("updated_at"));
					data[i].setLayer(lay+1);
					data[i].setX_axis(getWhereX(aRecord.getDouble("lat"), lay));
					data[i].setY_axis(getWhereY(aRecord.getDouble("lng"), lay));
					i++;
				}
				//System.out.println("---");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return data;
	}

	public int getWhereX(double lat, int layer) {
		int whereX = 0;

		double south = news[3];
		double north = news[0];
		int height = grid[layer][0];

		whereX = (int) Math.floor((lat - south) / (north - south) * height);
		if (whereX == height) {
			whereX--;
		} else if (whereX < 0 || whereX > height) {
			whereX = -1;
		}

		return whereX;
	}

	public int getWhereY(double lng, int layer) {
		int whereY = 0;

		double west = news[2];
		double east = news[1];
		int width = grid[layer][1];

		whereY = (int) Math.floor((lng - west) / (east - west) * width);
		if (whereY == width) {
			whereY--;
		} else if (whereY < 0 || whereY > width) {
			whereY = -1;
		}

		return whereY;
	}

	public static void main(String[] args) throws JSONException {
		/*Marker m = new Marker();
		// m.displayData();

		String jsonString = m.getJSONDataString();

		int numberRecord = m.getNumberOfReCord(jsonString) * 5;
		System.out.println(numberRecord / 5);
		Marker[] data = new Marker[numberRecord];
		data = m.createArrayData(numberRecord);

		m.displayARecord(data[0]);
		/*
		 * for (int i = 0; i < numberRecord; i++) { m.displayARecord(data[0]);
		 * System.out.println("---"); }
		 */
		// m.displayARecord(data[0]);

	}
}
