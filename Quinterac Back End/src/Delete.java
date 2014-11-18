import java.util.ArrayList;

/**
 * The Delete class implements the required behavior for the "delete" transaction.
 * With its single method apart from the constructor, it checks the relevant requirements
 * for a delete operation and then deletes the account from the master accounts file.
 * 
 * @author Jonah Eisen
 *
 */
public class Delete extends Transaction {

	/**
	 * This is the constructor method for the Delete class
	 * 
	 * @param ma				the ArrayList of the master accounts file
	 * @param transaction		the String of the command from the transaction summary file
	 */
	public Delete(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
	/**
	 * This method does the entire delete operation. It finds the account line in the master accounts,
	 * checks that the proper requirements are made (name matching, amount zero), and deletes
	 * the line from the master accounts file.
	 */
	public void start() {
		// find line in master accounts file
		for (String account: masterAccounts) {
			if (extractAccountFromAccountLine(account) == getToAccount()) {
				// check that name matches:
				if (extractNameFromAccountLine(account).equals(getName())) {
					// check that account balance is 0
					if (extractAmountFromAccountLine(account) == 0) {
						masterAccounts.remove(account);
						return;
					}
					else {
						System.out.println("Error: Account Balance not zero. Account not deleted.");
					}
				}
				else {
					System.out.println("Error: Name does not match. Account not deleted.");
				}
			}
		}
	}
}
