import java.util.ArrayList;

/**
 * The Deposit class implements the required behavior for the "deposit" transaction.
 * With its single method apart from the constructor, it adds a new line to the master
 * accounts file based on the original account number and original name from the old
 * master accounts file with the new amount after the deposit. Then it deletes the old entry
 * from the old master accounts file.
 * 
 * @author James Pang
 *
 */
public class Deposit extends Transaction{
	
	/**
	 * This is the constructor for the Deposit class.
	 * 
	 * @param ma				the ArrayList of the master accounts file
	 * @param transaction		the String of the command from the transaction summary file
	 */
	public Deposit(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
	/**
	 * This method performs the simple task of calculating the new amount after the deposit.
	 * It adds the new and updated entry first and remove the old entry.
	 * The new entry contains the original account number, the new balance after the deposit,
	 * and the original name.
	 */
	public void start() {
		for (String account: masterAccounts) {
			if (extractAccountFromAccountLine(account) == getToAccount()){
				int newAmount = extractAmountFromAccountLine(account) + getAmount();
				addToMasterAccounts(extractAccountFromAccountLine(account) + "_" + newAmount + "_" + extractNameFromAccountLine(account));
				masterAccounts.remove(account);
			}
		}
	}

}
