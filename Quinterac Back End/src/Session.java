import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class contains the main loop of the system in which it asks the user for transaction codes.
 * It is instantiated upon start-up.
 * 
 * @author Jonah Eisen
 *
 */
public class Session {

	private static ArrayList<String> masterAccounts; // Array List for master accounts file
	private static ArrayList<String> transactionSummary; // Array List for merged transaction summary file
	
	public final static String DEPOSIT_CODE = "01";
	public final static String WITHDRAW_CODE = "02";
	public final static String TRANSFER_CODE = "03";
	public final static String CREATE_CODE = "04";
	public final static String DELETE_CODE = "05";
	
	/**
	 * This is the constructor for the Session class.
	 * It simply invokes the method to read the two input files.
	 */
	public Session() {
		masterAccounts = new ArrayList<String>();
		transactionSummary = new ArrayList<String>();
		readFile(masterAccounts, "Master Accounts.txt");
		readFile(transactionSummary, "Merged Transaction Summary.txt");
	}
	
	/**
	 * This is a method that invokes the getFile method with the supplied file name,
	 * and stores the result in the specified ArrayList of type String
	 * 
	 * @param internalList		the internal ArrayList to store the data
	 * @param fileName			the name of the file to be read
	 */
	private static void readFile(ArrayList<String> internalList, String fileName) {
		String[] temp = getFile(fileName); // returns String array of lines
		//internalList = new ArrayList<String>();
		for (String s: temp) {
			internalList.add(s); // turns that into ArrayList of Strings
		}
	}
	
	/**
	 * This method is a generic text file reader. It stores each line of the text file as a String in an ArrayList.
	 * 	
	 * @param filename	a String of the file name that is to be read
	 * @return			an ArrayList of the contents of the file
	 */
	private static String[] getFile(String filename) {
		File file = new File(filename);
		ArrayList<String> data = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				data.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found.");
			System.exit(1);
		}
		return data.toArray(new String[data.size()]);
	}
	
	/**
	 * This method sorts the master accounts file in ascending order and then
	 * invokes the method to write it to the file.
	 */
	private static void writeMasterAccounts() {
		Collections.sort(masterAccounts);
		writeFile(masterAccounts, "New Master Accounts File.txt");
	}
	
	/**
	 * This method extracts all the account numbers from the master accounts file and
	 * writes them to the new valid accounts file.
	 */
	private static void writeValidAccounts() {
		ArrayList<String> validAccounts = new ArrayList<>();
		for (String accountLine: masterAccounts) {
			validAccounts.add(Integer.toString(Transaction.extractAccountFromAccountLine(accountLine)));
		}
		writeFile(validAccounts, "Valid Accounts.txt");
	}
	
	/**
	 * This method writes the specified ArrayList of type String to a file given the specified file name.
	 */
	private static void writeFile(ArrayList<String> internalList, String fileName) {
		File ts = new File(fileName);
	    try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ts));
			for (String s: internalList) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Write operation failed.");
			System.exit(1);
		}

	}
	
	/**
	 * This method is the most central part of the system.
	 * Herein lies the main loop that examines every line of the merged transaction
	 * summary file and instantiates the appropriate Transaction. It passes that transaction
	 * the master accounts file as well as the appropriate line from the transaction
	 * summary file. It then triggers the performance of the that transaction.
	 */
	public void start() {
		
		
		Transaction t;
		
		for (String transaction: transactionSummary) {
			
			System.out.println(transaction);
			
			String transactionCode = transaction.substring(0, 2);
			System.out.println(transactionCode);
			
			if (transactionCode.equals("01")) { // transaction is create
				t = new Deposit(masterAccounts, transaction);
				((Deposit) t).start();
			}
			else if (transactionCode.equals("02")) {
				t = new Withdraw(masterAccounts, transaction);
				((Withdraw) t).start();
			}
			else if (transactionCode.equals("03")) {
				t = new Transfer(masterAccounts, transaction);
				((Transfer) t).start();
			}
			else if (transactionCode.equals("04")) {
				System.out.println("create read");
				t = new Create(masterAccounts, transaction);
				((Create) t).start();
			}
			else if (transactionCode.equals("05")) {
				t = new Delete(masterAccounts, transaction);
				((Delete) t).start();
			}
			else { // transaction code is "00" for end of session
				// do nothing
			}
			
		}
		
		writeMasterAccounts();
		writeValidAccounts();
	}
}
