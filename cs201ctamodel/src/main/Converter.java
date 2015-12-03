package main;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

/*
 * Converter for CS201.
 * Used to take in user doubles for custom latitude and longitude coordinates.
 * Added kilometers to miles conversion
 * Reused from Lab 1.
 */
public class Converter {
	
	private static final double CM_PER_INCH = 2.54; //centimeters per inch
	private static final int INCHES_PER_FOOT = 12; //inches per foot
	private static final double MILES_PER_KM = 0.621371; //miles per kilometer
	private static final double WATER_FREEZING_POINT = 32.0; //in Fahrenheit
	private static final double CELSIUS_RATE = 100.0; //degrees C between water's freezing and boiling points
	private static final double FAHRENHEIT_RATE = 180.0; //degrees F between water's freezing and boiling points
	
	private static DecimalFormat celsiusFormat = new DecimalFormat("0.000 °C");
	private static DecimalFormat fahrenheitFormat = new DecimalFormat("0.000 °F");
	private static DecimalFormat moneyFormat = new DecimalFormat("$0.00");
	
	private static String[] booleanOptions = {"True", "False"};

	public static void main(String[] args) {
		
		double inchesInput = getUserDouble("Enter a value in inches.");
		JOptionPane.showMessageDialog(null, "The value you entered is "
				+ Converter.inchesToCentimeters(inchesInput) + " in centimeters.");
		
	}
	
	/* JOPTIONPANE INPUT FILTERING */
	
	/*
	 * All three of these methods take a String message as an input
	 * But that means that after the first input retry, the message
	 * passed through includes the line "That is not a valid input. "
	 * as many times as the error has come up.
	 * I think the easiest way to fix this is to add a boolean argument
	 * for whether the method call is a retry or not, then add the
	 * message if it is a retry.
	 */
	
	public static String getAcceptableStrings(String message, String[] options, boolean retry){
		String displayMessage = "";
		if(retry){
			displayMessage = "That is not a valid option.\n" + message;
		}
		else{
			displayMessage = message;
		}
		displayMessage += "\nOptions:\n";
		for(String option : options){
			displayMessage += "-- " + option + "\n";
		}
		String response = JOptionPane.showInputDialog(displayMessage);
		boolean acceptable = false;
		for(String option : options){
			if(option.toLowerCase().equals(response.toLowerCase())){
				acceptable = true;
			}
		}
		if(!acceptable){
			response = getAcceptableStrings(message, options, true);
		}
		else{
			return response;
		}
		return response;
	}
	
	public static int getUserInteger(String message){
		try{
			String input = JOptionPane.showInputDialog(message);
			if(input == null){
				JOptionPane.showMessageDialog(null, "The program is now closing. Have a nice day!");
				System.exit(1);
			}
			return Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			return getUserInteger("That is not a valid input. " + message);
		}
	}
	
	public static double getUserDouble(String message){
		try{
			String input = JOptionPane.showInputDialog(message);
			if(input == null){
				System.exit(1);
			}
			return Double.parseDouble(input);
		}
		catch(NumberFormatException e){
			return getUserDouble("That is not a valid input. " + message);
		}
	}
	
	public static double getNonZeroPositiveDouble(String message){
		try{
			String input = JOptionPane.showInputDialog(message);
			if(input == null){
				System.exit(1);
			}
			double number = Double.parseDouble(input);
			if(number == 0){
				return getNonZeroPositiveDouble("That is not a valid input. " + message);
			}
			if(number < 0){
				number = Math.abs(number);
			}
			return number;
		}
		catch(NumberFormatException e){
			return getNonZeroPositiveDouble("That is not a valid input. " + message);
		}
	}
	
	/* HEIGHT CONVERSIONS */
	
	/*
	 * Converts input to centimeters and returns it as a unit-less double
	 */
	public static double inchesToCentimeters(int inches){
		return (((double)inches) * CM_PER_INCH);
	}
	
	public static double inchesToCentimeters(double inches){
		return (inches * CM_PER_INCH);
	}
	
	/* 
	 * Converts input to feet and inches with apostrophe units notation
	 */
	public static String inchesToFeet(int inches){
		int inchesRemainder = inches % INCHES_PER_FOOT;
		int feet = (inches - inchesRemainder) / INCHES_PER_FOOT;
		return (feet + "' " + inchesRemainder + "''");  
	}
	
	/*
	 * Converts kilometers to miles
	 */
	public static double kilometersToMiles(double kilometers){
		return (kilometers * MILES_PER_KM);
	}
	
	/* TEMPERATURE CONVERSIONS */
	
	/*
	 * Converts Fahrenheit to Celsius and returns the result as a unit-less double
	 */
	public static double convertFahrenheitToCelsius(double fahrenheit){
		double rate = CELSIUS_RATE / FAHRENHEIT_RATE;
		return ((fahrenheit - WATER_FREEZING_POINT) * rate);
	}

	/*
	 * Converts Celsius to Fahrenheit and returns the result as a unit-less double
	 */
	public static double convertCelsiusToFahrenheit(double celsius){
		double rate = FAHRENHEIT_RATE / CELSIUS_RATE;
		return ((celsius * rate) + WATER_FREEZING_POINT);
	}

	public static DecimalFormat getCelsiusFormat() {
		return celsiusFormat;
	}
	
	public static DecimalFormat getFahrenheitFormat() {
		return fahrenheitFormat;
	}

	public static DecimalFormat getMoneyFormat() {
		return moneyFormat;
	}
	
	/* ANGULAR CONVERSIONS */
	
	public static double degreesToRadians(double degrees){
		return (degrees * (Math.PI / 180.0));
	}
	
	/* MENU OPTIONS */
	
	public static String[] getBooleanOptions(){
		return booleanOptions;
	}
	
}