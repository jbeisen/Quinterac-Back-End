import java.util.ArrayList;


public class Create extends Transaction {

	
	public Create( ArrayList<String> ma, String transaction) {
		super(ma, transaction);
		
	}
	
	public void start() {
		addToMasterAccounts(getPaddedNumber(getToAccount(), 6) + "_" + ZERO_AMOUNT + "_" + getPaddedName(getName()));
	}
}
