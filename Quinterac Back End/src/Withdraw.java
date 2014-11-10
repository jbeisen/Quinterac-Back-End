import java.util.ArrayList;


public class Withdraw extends Transaction{

	public Withdraw(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
	public static void start() {
		for (String account: masterAccounts) {
			if (extractAccountFromAccountLine(account) == getFromAccount()){
				int newAmount = extractAmountFromAccountLine(account) + getAmount();
				if (newAmount < 0){
					// balance will be negative
					System.out.println("Error: Blance will be negative after the withdrawl");
					return;
				}
				addToMasterAccounts(extractAccountFromAccountLine(account) + "_" + newAmount + "_" + extractNameFromAccountLine(account));
				masterAccounts.remove(account);
			}
		}
	}

}
