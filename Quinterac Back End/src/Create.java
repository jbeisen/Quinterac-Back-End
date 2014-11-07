import java.util.ArrayList;

/**
 * The Create class implements the required behavior for the "delete" transaction.
 * With its single method apart from the constructor, adds a new line to the master
 * accounts file based on the new account number and name specified in the
 * transaction summary file.
 * 
 * @author jonaheisen
 *
 */
public class Create extends Transaction {

	/**
	 * This is the constructor for the Create class.
	 * 
	 * @param ma				the ArrayList of the master accounts file
	 * @param transaction		the String of the command from the transaction summary file
	 */
	public Create( ArrayList<String> ma, String transaction) {
		super(ma, transaction);		
	}
	
	/**
	 * This method performs the simple task of adding a new line to the master accounts file
	 * containing the specified new account number, a balance of 0, and the specified name.
	 * It first checks that the account does not already exist.
	 */
	public void start() {
		for (String account: masterAccounts) {
			if (extractAccountFromAccountLine(account) == getToAccount()) {
				// account already exists
				System.out.println("Error: Account already exists.");
				return;
			}
		}
		addToMasterAccounts(getPaddedNumber(getToAccount(), 6) + "_" + ZERO_AMOUNT + "_" + getPaddedName(getName()));
	}
}
