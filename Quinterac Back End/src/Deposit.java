import java.util.ArrayList;


public class Deposit extends Transaction{

	public Deposit(ArrayList<String> ma, String transaction) {
		super(ma, transaction);
	}
	
	public static void start() {
		for (String account: masterAccounts) {
			if (account.substring(0, 5) == Integer.toString(getFromAccount())) {
				
			}
		}
	}

}
