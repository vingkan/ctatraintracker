package sample;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class Sandbox {

	public static void main(String[] args) throws Exception{
		
		Scanner scan = new Scanner(new File("greenline-placemark.txt"));
		/*String key = "NAME";
		boolean reading = false;*/
		while(scan.hasNextLine()){
			String nextLine = scan.nextLine();
			/*if(nextLine.length() > 2){
				if(reading){
					System.out.println(stripTags(nextLine));
					reading = false;
				}
				else if(nextLine.contains(key)){
					//System.out.println(stripTags(nextLine));
					reading = true;
				}
			}*/
			System.out.println(nextLine);
		}
		scan.close();
		
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
