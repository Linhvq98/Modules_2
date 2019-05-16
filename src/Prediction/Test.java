package Prediction;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

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
	
	public String getResultJSON() {
		JSONObject obj = new JSONObject();
		JSONArray obj2 = new JSONArray();
		JSONObject obj3 = new JSONObject();
		try {
			for (int i = 0; i < 3; i++) {
				obj.put("x_axis", new Integer(1));
				obj.put("y_axis", new Integer(2));
				obj.put("start_time", new String("hoang"));
				obj.put("end_time", new String("dinh"));
				obj.put("id_cell", new Integer(3));
				obj.put("avg_speed", new Double(4));
				obj.put("marker_count", new Integer(5));
				obj.put("indicator", new Double(6));
				obj.put("algorithm", new Integer(7));
				obj.put("color", new String("nam"));
				obj2.put(i, obj);
			}
			obj3.put("data", obj2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj3.toString();
	}
	
	public static void main(String [] args) throws IOException {
		Test t = new Test();
		System.out.println(t.getResultJSON());
		String json = "{\"data\":[{\"marker_count\":5,\"indicator\":6,\"start_time\":\"2019-05-17 00:58:00\",\"y_axis\":2,\"color\":\"nam\",\"avg_speed\":4,\"x_axis\":1,\"end_time\":\"2019-05-17 00:59:00\",\"id_cell\":3,\"algorithm\":7},{\"marker_count\":5,\"indicator\":6,\"start_time\":\"2019-05-17 00:58:00\",\"y_axis\":2,\"color\":\"nam\",\"avg_speed\":4,\"x_axis\":1,\"end_time\":\"2019-05-17 00:59:00\",\"id_cell\":3,\"algorithm\":7},{\"marker_count\":5,\"indicator\":6,\"start_time\":\"2019-05-17 00:58:00\",\"y_axis\":2,\"color\":\"nam\",\"avg_speed\":4,\"x_axis\":1,\"end_time\":\"2019-05-17 00:59:00\",\"id_cell\":3,\"algorithm\":7}]}\r\n" + 
				"";
		t.sendResultJSON(json);
	}
}
