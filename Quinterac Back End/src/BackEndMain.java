/**
 * This class contains the main method for the Quinterac Back Office system.
 * It will instantiate a new instance of Session upon start-up. The Session
 * class first reads in the two input files. The main loop is then triggered
 * by the main method here, which in turn reads through every line in the
 * merged transaction summary file. A Transaction object is created for each
 * line, and then depending on the transaction code, the object is cast to the
 * relevant child class of Transaction (taking advantage of late binding).
 * Each child of the Transaction class performs the required actions on the
 * master accounts file. Finally, after all lines of the transaction summary
 * file are read by the Session class, the Session class writes the two output
 * files (the new master accounts file and the valid accounts file).
 * 
 * @author Jonah Eisen
 *
 */
public class BackEndMain {

	/**
	 * This is the main method of the Quinterac Back Office system.
	 * It instantiates a new instance of Session upon start-up.
	 * @param args
	 */
	public static void main(String[] args) {
		Session s = new Session();
		s.start();
	}

}
