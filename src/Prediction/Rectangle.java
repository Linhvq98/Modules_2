package Prediction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Rectangle {
	private static final String url = "http://68.183.238.65:8000/api/data/rectangles";
	private int layer, height, width;
	private double east, west, south, north;
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public double getEast() {
		return east;
	}
	public void setEast(double east) {
		this.east = east;
	}
	public double getWest() {
		return west;
	}
	public void setWest(double west) {
		this.west = west;
	}
	public double getSouth() {
		return south;
	}
	public void setSouth(double south) {
		this.south = south;
	}
	public double getNorth() {
		return north;
	}
	public void setNorth(double north) {
		this.north = north;
	}
	
	
	
	
	
	public String getJSONDataString() {
		StringBuffer response = new StringBuffer();
		
		try {
			URL obj = new URL(Rectangle.url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null) {
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
	
	public Rectangle[] createArrayData(int numberRecord) {
		String jsonString = this.getJSONDataString();
		
		Rectangle[] data = new Rectangle[numberRecord];
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			for(int i=0; i<numberRecord; i++) {
				JSONObject aRecord = new JSONObject(jsonArray.getJSONObject(i).toString());
				data[i] = new Rectangle();
				data[i].setLayer(aRecord.getInt("id"));
				data[i].setHeight(aRecord.getInt("height"));
				data[i].setWidth(aRecord.getInt("width"));
				data[i].setEast(aRecord.getDouble("east"));
				data[i].setWest(aRecord.getDouble("west"));
				data[i].setSouth(aRecord.getDouble("south"));
				data[i].setNorth(aRecord.getDouble("north"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return data;
	}
	
	public void displayARecord(Rectangle aRecord) {
		System.out.println("layer: " + aRecord.getLayer());
		System.out.println("height: " + aRecord.getHeight());
		System.out.println("width: " + aRecord.getWidth());
		System.out.println("east: " + aRecord.getEast());
		System.out.println("west: " + aRecord.getWest());
		System.out.println("south: " + aRecord.getSouth());
		System.out.println("north: " + aRecord.getNorth());
	}
	
	public static void main(String [] args) {
		Rectangle r = new Rectangle();
		
		String jsonString = r.getJSONDataString();
		int numberRecord = r.getNumberOfReCord(jsonString);
		Rectangle[] rectangles = new Rectangle[numberRecord];
		rectangles = r.createArrayData(numberRecord);
		r.displayARecord(rectangles[0]);
		r.displayARecord(rectangles[1]);
		r.displayARecord(rectangles[2]);
		r.displayARecord(rectangles[3]);
		r.displayARecord(rectangles[4]);
		//m.displayData();
	}
}
