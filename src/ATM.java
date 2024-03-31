
public class ATM 
{
	//Attributes
	private boolean userAuthenticated;
	private int currentAccountNumber; // current user's account number
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;
	
	// constants corresponding to main menu options
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	public ATM()
	{
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
	}// end of ATM constructor
	
	//Start ATM Operation
	public void run()
	{
		while (true)
		{
			while(!userAuthenticated)
			{
				screen.displayMessageLine("\nWelcome!");
				authenticateUser();
			}
			performTransaction();
			
			//resetting after performing the transaction
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.displayMessageLine("\nThank you! Goodbye!");
		}//end while loop
	}//end run method

	private void authenticateUser() {
		screen.displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInput();
		screen.displayMessage("\nEnter your PIN: ");
		int pin = keypad.getInput();
		
		// set userAuthenticated to boolean value returned by database
		userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
		// check whether authentication succeeded
		if(userAuthenticated)
		{
			currentAccountNumber = accountNumber;
		}
		else
			screen.displayMessageLine("\nInvalid account number or PIN. Please try again.");
	} //end authenticateUser method
	
	private void performTransaction()
	{
		Transaction currentTransaction = null;
		boolean userExited = false;
		
		while (!userExited)
		{
			int mainMenuSelection = displayMainMenu();
			
			switch (mainMenuSelection)
			{
			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case DEPOSIT:
				// initialize as new object of chosen type
				currentTransaction = createTransaction(mainMenuSelection);
				currentTransaction.execute();
				break;
			case EXIT:
				screen.displayMessageLine("\nExiting the system...");
				userExited = true;
				break;
			default:
				screen.displayMessageLine("\nYou did not enter a valid selection. Please try again.");
				break;
			} //end switch
		} //end while
	} //end performTransactionMethod
	
	private int displayMainMenu()
	{
		screen.displayMessageLine("\nMain menu: ");
		screen.displayMessageLine("1 - View my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit Funds");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessageLine("Enter a choice: ");
		return keypad.getInput();
	}
	
	private Transaction createTransaction(int type)
	{
		Transaction temp = null;
		
		switch (type)
		{
		case BALANCE_INQUIRY:
			temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
			break;
		case WITHDRAWAL:
			temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
			break;
		case DEPOSIT:
			temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
			break;
		} //end switch
		return temp;
	} //end createTransaction method
	
} //end ATM class