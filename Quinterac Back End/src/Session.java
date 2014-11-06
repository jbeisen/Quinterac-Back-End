import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Session {

	private static ArrayList<String> masterAccounts; // Array List for master accounts file
	private static ArrayList<String> transactionSummary; // Array List for merged transaction summary file
	
	
	public Session() {
		readMasterAccounts();
		transactionSummary = new ArrayList<>();
	}
	
	private static void readMasterAccounts() {
		String[] temp = getFile("Master Accounts.txt"); // returns String array of valid account lines
		masterAccounts = new ArrayList<String>();
		for (String s: temp) {
			masterAccounts.add(s); // turns that into ArrayList of String
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
	
	private static void writeMasterAccounts() {
		// sort master accounts
		// write them to file
	}
	
	private static void writeValidAccounts() {
		// take account numbers from master accounts
		// write them to file
	}
	
	public void start() {
		
		Transaction t;
		
		for (String transaction: transactionSummary) {
			if (transaction.regionMatches(0, "01", 0, 2)) { // transaction is create
				t = new Deposit(masterAccounts, transaction);
				((Deposit) t).start();
			}
			else if (transaction.regionMatches(0, "02", 0, 2)) {
				t = new Withdraw(masterAccounts, transaction);
				((Withdraw) t).start();
			}
			else if (transaction.regionMatches(0, "03", 0, 2)) {
				t = new Transfer(masterAccounts, transaction);
				((Transfer) t).start();
			}
			else if (transaction.regionMatches(0, "04", 0, 2)) {
				t = new Create(masterAccounts, transaction);
				((Create) t).start();
			}
			else if (transaction.regionMatches(0, "05", 0, 2)) {
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
