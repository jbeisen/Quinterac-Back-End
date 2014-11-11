import java.util.ArrayList;

/**
 * The Transfer class implements the required behavior for the "transfer" transaction.
 * Essentially it is a combination of deposit and withdraw transactions.
 * With its single method apart from the constructor, it adds two new lines to the master
 * accounts file based on the original account number and original name from the old
 * master accounts file with the new amount after the transfer. Then it deletes the two old entries
 * from the old master accounts file.
 * 
 * @author James Pang
 *
 */
public class Transfer extends Transaction{

	/**
	 * This is the constructor for the Transfer class.
	 * 
	 * @param ma				the ArrayList of the master accounts file
	 * @param transaction		the String of the command from the transaction summary file
	 */
	public Transfer(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}

	/**
	 * This method performs the simple task of calculating the new amount after the transfer for both accounts.
	 * If the new amount is negative, the program will generate an error message.
	 * It adds the new and updated entry first and remove the old entry for both accounts.
	 * The new entry contains the original account number, the new balance after the deposit,
	 * and the original name.
	 */
	public void start() {
		for (String account: masterAccounts) {
			if (Integer.toString(extractAccountFromAccountLine(account)).equals(getFromAccount())){
				int newAmount = extractAmountFromAccountLine(account) + getAmount();
				if (newAmount < 0){
					// balance will be negative
					System.out.println("Error: Blance will be negative after the withdrawl");
					return;
				}
				addToMasterAccounts(extractAccountFromAccountLine(account) + "_" + newAmount + "_" + extractNameFromAccountLine(account));
				masterAccounts.remove(account);
			} else if (Integer.toString(extractAccountFromAccountLine(account)).equals(getToAccount())){
				int newAmount = extractAmountFromAccountLine(account) + getAmount();
				addToMasterAccounts(extractAccountFromAccountLine(account) + "_" + newAmount + "_" + extractNameFromAccountLine(account));
				masterAccounts.remove(account);
			}
		}
	}
}
