/**
 * 
 */
package cmegradtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author Gavin Davis
 *
 */
public class Driver {

	/** java Program to check if two textinputs are an anagrm
	 * @param args
	 */

	// Declaring constants
	public final static String resultsFile = "Results.txt";
	public final static String logFile = "log.txt";

	public static void main(String[] args) {

		// scanner declared for keyboard input
		Scanner sc = new Scanner(System.in);

		// declare variables
		String userName;
		String textInput1 = null;
		String textInput2 = null;
		String test;

		// ArrayList collection to act as a cache
		ArrayList<Submission> submissions = new ArrayList<Submission>();

		// object reference
		Submission s = null;

		// continue boolean flag, default to continue loop
		boolean loop = true;

		System.out.println("Welcome to the Anagram CheckerProgram, Release 1");
		// log program start
		log("Program Start", logFile);
		// read text file into cache
		readFromFile(resultsFile, submissions);

		do {

			// take user input
			System.out.println("Enter user name");
			userName = sc.nextLine();
			System.out.println("Enter 1st word");
			textInput1 = sc.nextLine();
			System.out.println("Enter 2nd word");
			textInput2 = sc.nextLine();

			// check inputs havent been used before against cache before processing
			// guards against duplicates being entered into the external file
			// currently checks gainst previously submitted text values regardless of
			// username
			if (!submissionDuplicate(submissions, textInput1, textInput2)) {
				try {
					// create submission object using factory pattern
					s = new Submission(userName, textInput1, textInput2);
					// declare to user if inputs are an anagram
					isAnagramMessage(s);
					// write to cache
					writeToCache(submissions, s);
					// assists with debugging
					// displayCache(submissions);
					// write to external file
					writeSubmissionToFile(s, resultsFile);
				} catch (IllegalArgumentException e) {
					System.out.println();
					System.out.println("Invalid User Input");
					System.out.println(e.getMessage());
					log(e.getMessage(), logFile);
				}

			}

			// ask if the user wants to make another submission
			System.out.println("Would you like to make another submission (y/n)");
			userName = sc.nextLine();
			if (userName.toLowerCase().equals("n")) {
				break;
			}

		} while (loop);
		// exit message
		System.out.println("Program Terminated");
		// close Scanner
		sc.close();
		log("Program Terminated", logFile);
	}
	
	

	/**
	 * Log program events to a specific file for program maintanence and debugging
	 * @param message
	 * @param filename
	 */
	private static void log(String message, String filename) {

		// create instance of calendar for date and time logging
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		String date = year + "/" + month + "/" + day;
		String time = hour + ":" + min + ":" + sec;

		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(date + "," + time + "," + message.toString() + "\n");

			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			// Cannot log error here as this method controls access to the log
			// User prompted that error writing has occurred
		}
		
	}

	/**
	 * Read data from external file, create Submission objects and store in cache
	 * throws I/O exception
	 * 
	 * @param String fileName, ArrayList<Submission> submission
	 */
	private static void readFromFile(String fileName, ArrayList<Submission> submissions) {

		String line;
		String[] input;
		String userName;
		String textInput1;
		String textInput2;

		File file = new File(fileName);
		if (file.exists()) {

			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				line = br.readLine();
				while (line != null) {
					input = line.split(",");
					userName = input[0];
					textInput1 = input[1];
					textInput2 = input[2];
					Submission s = new Submission(userName, textInput1, textInput2);
					submissions.add(s);
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				log(e.getMessage(), logFile);
			}
			System.out.println("External file data imported!");
			log("Data from results file read successfully", logFile);
		} else {
			System.out.println(
					"No Results file found for data import, file ill be created automatically on first submission");
			log("No Results file found for data import, file ill be created automatically on first submission", logFile);
		}
		
		
	}

	/**
	 * Check if the current submission has been made already
	 * 
	 * @param submissions
	 * @param textInput1
	 * @param textInput2
	 * @return true if the textInput1 and textInput2 exist in the cache, independant
	 *         of username
	 */
	private static boolean submissionDuplicate(ArrayList<Submission> submissions, String textInput1,
			String textInput2) {

		for (int i = 0; i < submissions.size(); i++) {
			String un = submissions.get(i).getUserName();
			String word1 = submissions.get(i).getTextInput1();
			String word2 = submissions.get(i).getTextInput2();
			if (textInput1.equals(word1) && textInput2.equals(word2)
					|| textInput1.equals(word2) && textInput2.equals(word1)) {
				System.out.println("This submission has already been made by " + un);
				isAnagramMessage(submissions.get(i));
				return true;
			}
		}
		return false;

	}

	/**
	 * Method used to display contents of cache Used primarily for debugging, not
	 * part of main program
	 * 
	 * @param submissions
	 */
	private static void displayCache(ArrayList<Submission> submissions) {
		System.out.println("Printing contents of cache.....");
		for (Submission s : submissions) {
			System.out.println(s.toString());
		}
	}

	private static void writeSubmissionToFile(Submission s, String filename) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s.toString() + "\n");
			System.out.println("Write Complete!");
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			log(e.getLocalizedMessage(), logFile);
		}
		log("Write to external file successfull", logFile);

	}

	private static void writeToCache(ArrayList<Submission> list, Submission s) {
		list.add(s);
		log("Write to cache successfull", logFile);
	}

	public static void isAnagramMessage(Submission s) {
		StringBuilder sb = new StringBuilder();
		System.out.println();
		sb.append("***************************\n");
		sb.append("*      Anagram Checker    *\n");
		sb.append("***************************\n");
		sb.append("Username: " + s.getUserName() + "\n");
		sb.append("word 1: " + s.getTextInput1() + "\n");
		sb.append("word 2: " + s.getTextInput2() + "\n");
		sb.append("Is an anagram? " + s.checkForAnagram() + "\n");
		sb.append("***************************\n");
		System.out.println(sb.toString());
	}

}
