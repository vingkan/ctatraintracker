package main;

import java.io.File;
import java.util.Scanner;

public class ApplicationVK {

	public static void main(String[] args) {

		File file = new File("cta-system-stops.csv");
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				System.out.println(line);
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("Encountered Exception: " + e);
		}

	}

}
