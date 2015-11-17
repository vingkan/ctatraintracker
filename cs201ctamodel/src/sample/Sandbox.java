package sample;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sandbox {

	public static void main(String[] args) throws Exception{
		
		Scanner scan = new Scanner(new File("full-kml.txt"));
		String key = "DESCRIPTION";
		boolean reading = false;
		boolean readingPlacemark = false;
		List<String> results = new ArrayList<String>();
		while(scan.hasNextLine()){
			String nextLine = scan.nextLine();
			//System.out.print(nextLine);
			if(nextLine.contains("</Placemark>")){
				readingPlacemark = false;
				//System.out.println("----------");
			}
			else if(readingPlacemark){
				//System.out.println(nextLine);
				reading = handlePlacemark(results, nextLine, key, reading);
				//reading = handlePlacemark(nextLine, "RAIL LINES", reading);
			}
			else if(nextLine.contains("<Placemark>")){
				readingPlacemark = true;
				//System.out.println("----------");
			}
		}
		scan.close();
		for(String result : results){
			System.out.println(result);
		}
	}
	
	public static boolean handlePlacemark(List<String> results, String nextLine, String key, boolean reading){
		if(nextLine.length() > 2){
			if(reading){
				results.add(stripTags(nextLine));
				reading = false;
			}
			else if(nextLine.contains(key)){
				//System.out.println(stripTags(nextLine));
				reading = true;
			}
		}
		return reading;
	}
	
	public static String stripTags(String line){
		String value = line;
		value = value.replace("<td>", "");
		value = value.replace("</td>", "");
		value = value.substring(1);
		return value;
	}
	
	public static void httpGetRequestTry() throws Exception{
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
