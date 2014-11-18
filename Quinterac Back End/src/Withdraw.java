import java.util.ArrayList;

/**
 * The Withdraw class implements the required behavior for the "withdraw" transaction.
 * With its single method apart from the constructor, it adds a new line to the master
 * accounts file based on the original account number and original name from the old
 * master accounts file with the new amount after the withdrawal. Then it deletes the old entry
 * from the old master accounts file.
 * 
 * @author James Pang
 *
 */
public class Withdraw extends Transaction{

	/**
	 * This is the constructor for the Create class.
	 * 
	 * @param ma				the ArrayList of the master accounts file
	 * @param transaction		the String of the command from the transaction summary file
	 */
	public Withdraw(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
	/**
	 * This method performs the simple task of calculating the new amount after the withdrawal.
	 * If the new amount is negative, the program will generate an error message.
	 * It adds the new and updated entry first and remove the old entry.
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
				return;
			}
		}
	}

}
