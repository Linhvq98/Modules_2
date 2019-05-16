package Prediction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
	// get date now 
	public String getDate(Date d) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(d).toString();
	}
	
	//get date after/before m minute (after -> m: positive, before -> m: negative)
	public String getDate(Date d, int m) {
		d = new Date(System.currentTimeMillis() + m*60*1000);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(d).toString();
		
	}
}
