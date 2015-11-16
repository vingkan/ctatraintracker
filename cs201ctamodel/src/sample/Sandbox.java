package sample;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Sandbox {

	public static void main(String[] args) throws Exception{
		String url = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx";
		String charset = "UTF-8";
		String key = "topkek";
		String mapid = "40380";
		String max = "5";
		String query = String.format("key=%s&mapid=%s&max=%s&",
			URLEncoder.encode(key, charset),
			URLEncoder.encode(mapid, charset),
			URLEncoder.encode(max, charset)
		);
		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
		int input;
		String output = "";
		while((input = response.read()) != -1){
			output += (char)input;
		}
		System.out.println(output);
	}

}
