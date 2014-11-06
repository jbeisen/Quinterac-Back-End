import java.util.ArrayList;


public class Transaction {
	
	protected static ArrayList<String> masterAccounts;
	private static int toAccount;
	private static int fromAccount;
	private static int amount;
	private static String name;
	
	public final String ZERO_AMOUNT = "00000000"; // 8 zeros for zero amount
	

	public Transaction (ArrayList<String> ma, String transaction) {
		masterAccounts = ma;
		parseTransaction(transaction);
	}
	
	private void parseTransaction(String transaction) {
		try{
			toAccount = Integer.parseInt(transaction.substring(3, 8));
			fromAccount = Integer.parseInt(transaction.substring(10,15));
			amount = Integer.parseInt(transaction.substring(17,24));
			name = transaction.substring(26,40);
			name = name.replaceAll("_", " ");
			name = name.trim();
		} catch (Exception e) {
			System.out.println("Bad transaction summary input. System failure.");
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
		for (int i = name.length(); i < 15; i++ ) paddedName += "_";
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
	
	public static void addToMasterAccounts(String s) {
		masterAccounts.add(s);
	}
	
}
