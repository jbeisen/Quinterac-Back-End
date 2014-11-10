import java.util.ArrayList;

/**
 * The Transaction class is the parent class of all transactions.
 * It stores attributes and methods that apply to more than one transaction.
 * 
 * @author Jonah Eisen
 *
 */
public class Transaction {
	
	// class attributes:
	protected static ArrayList<String> masterAccounts;
	private static int toAccount;
	private static int fromAccount;
	private static int amount;
	private static String name;
	
	
	// constant declarations:
	public final String ZERO_AMOUNT = "00000000"; // 8 zeros for zero amount
	public final static int ACCOUNT_LENGTH = 6; // 6 digits for an account
	public final static int NAME_LENGTH = 15; // 15 characters for a name
	
	public final int TO_ACCOUNT_BEGIN_INDEX = 3;
	public final int TO_ACCOUNT_END_INDEX = 8;
	public final int FROM_ACCOUNT_BEGIN_INDEX = 10;
	public final int FROM_ACCOUNT_END_INDEX = 15;
	public final int AMOUNT_BEGIN_INDEX = 17;
	public final int AMOUNT_END_INDEX = 24;
	public final int NAME_BEGIN_INDEX = 26;
	public final int NAME_END_INDEX = 40;
	
	public final static int MA_ACCOUNT_BEGIN_INDEX = 0;
	public final static int MA_ACCOUNT_END_INDEX = 5;
	public final static int MA_AMOUNT_BEGIN_INDEX = 7;
	public final static int MA_AMOUNT_END_INDEX = 14;
	public final static int MA_NAME_BEGIN_INDEX = 16;
	public final static int MA_NAME_END_INDEX = 30;


	/**
	 * This is the constructor for the transaction file. It stores the master accounts file
	 * as a class attribute and sends the transaction line off to the parseTransaction method.
	 * 
	 * @param ma			the ArrayList of the master accounts file
	 * @param transaction	the String of a line in the transaction summary file
	 */
	public Transaction (ArrayList<String> ma, String transaction) {
		masterAccounts = ma;
		parseTransaction(transaction);
	}
	
	 /**
	  * This method takes a line from the transaction summary file and parses out all the different fields.
	  * In other words, it determines the accounts, amount, and name, and stores them as class
	  * attributes so that the line only needs to be parsed once.
	  * 
	  * @param transaction		the String of the transaction line from the transaction summary file
	  */
	private void parseTransaction(String transaction) {
		try{
			toAccount = Integer.parseInt(transaction.substring(TO_ACCOUNT_BEGIN_INDEX, TO_ACCOUNT_END_INDEX + 1));
			fromAccount = Integer.parseInt(transaction.substring(FROM_ACCOUNT_BEGIN_INDEX, FROM_ACCOUNT_END_INDEX + 1));
			amount = Integer.parseInt(transaction.substring(AMOUNT_BEGIN_INDEX, AMOUNT_END_INDEX + 1));
			name = transaction.substring(NAME_BEGIN_INDEX, NAME_END_INDEX + 1);
			name = name.replaceAll("_", " ");
			name = name.trim();
		} catch (Exception e) {
			System.out.println("Fatal Error: Bad transaction summary input.");
			System.exit(1);
		}
	}
	
	/**
	 * A method that turns a name into the properly formatted version for the master accounts file
	 * 
	 * @param name	a String of the original name
	 * @return		a String padded with spaces on the right
	 */
	public static String getPaddedName(String name) {
		String paddedName = new String(name);
		paddedName = paddedName.replaceAll(" ", "_"); // replaces spaces with underscores
		for (int i = name.length(); i < NAME_LENGTH; i++ ) paddedName += "_";
		return paddedName;
	}
	
	/**
	 * A method that turns a number into the properly formatted version for the transaction summary file
	 * 
	 * @param number	the original number
	 * @param digit		the total number of digits that should be in the formatted version
	 * @return			the number padded with zeros on the left
	 */
	public static String getPaddedNumber(Integer number, int digit) {
		String paddedNumber = new String(number.toString());
		for (int i = paddedNumber.length(); i < digit; i++ ) paddedNumber = "0" + paddedNumber;
		return paddedNumber;
	}
	
	/**
	 * A simple getter method for the to account
	 * 
	 * @return the to account
	 */
	public static int getToAccount() { return toAccount; }
	
	/**
	 * A simple getter method for the from account
	 * 
	 * @return the from account
	 */
	public static int getFromAccount() { return fromAccount; }
	
	/**
	 * A simple getter method for the name
	 * 
	 * @return the name
	 */
	public static String getName() { return name; }
	
	/**
	 * A simple getter method for the amount
	 * 
	 * @return the amount
	 */
	public static int getAmount() { return amount; }
	
	/**
	 * This method adds the supplied String as a new line in the master accounts file
	 * 
	 * @param s		the String to be added
	 */
	public static void addToMasterAccounts(String s) {
		masterAccounts.add(s);
	}
	
	/**
	 * This method extracts the account from an account line in the master accounts file.
	 * 
	 * @param accountLine	the specified line of the master accounts file
	 * @return				an int of the account
	 */
	public static int extractAccountFromAccountLine(String accountLine) {
		return Integer.parseInt(accountLine.substring(MA_ACCOUNT_BEGIN_INDEX, MA_ACCOUNT_END_INDEX + 1));
	}
	
	/**
	 * This method extracts the amount from an account line in the master accounts file.
	 * 
	 * @param accountLine	the specified line of the master accounts file
	 * @return				an int of the amount
	 */
	public static int extractAmountFromAccountLine(String accountLine) {
		return Integer.parseInt(accountLine.substring(MA_AMOUNT_BEGIN_INDEX, MA_AMOUNT_BEGIN_INDEX + 1));
	}
	
	/**
	 * This method extracts the name from an account line in the master accounts file.
	 * 
	 * @param accountLine	The specified line of the master account file
	 * @return				a String of the name, with spaces instead of underscores, and trimmed
	 */
	public static String extractNameFromAccountLine(String accountLine) {
		String temp = accountLine.substring(MA_NAME_BEGIN_INDEX, MA_NAME_END_INDEX + 1);
		temp.replaceAll("_", " ");
		temp.trim();
		return temp;
	}
	
}
