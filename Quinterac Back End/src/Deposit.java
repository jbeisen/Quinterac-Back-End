import java.util.ArrayList;


public class Deposit extends Transaction{

	public Deposit(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
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
