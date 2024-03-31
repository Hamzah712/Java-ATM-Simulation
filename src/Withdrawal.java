
public class Withdrawal extends Transaction{
	
	public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
		
		keypad = atmKeypad;
		cashDispenser = atmCashDispenser;
	}

	private int amount;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	
	private final static int CANCELED = 6;

	@Override
	public void execute() 
	{
		boolean cashDispensed = false;
		double availableBalance;
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		do
		{
			amount = dispalyMenuOfAmounts();
			
			if(amount!=CANCELED)
			{
				availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
				
				if(amount <= availableBalance)
				{
					if(cashDispenser.isSufficientCashAvailable(amount))
					{
						bankDatabase.debit(getAccountNumber(), amount);
						
						cashDispenser.dispenseCash(amount);
						cashDispensed = true;
						
						screen.displayMessageLine("\nYour cash has been dispensed. Please take your cash now.");
					}//end if
					else
						screen.displayMessageLine("\nInsufficient cash available in the ATM. \n\nPlease choose a smaller amount.");
				}//end if
				else {
					screen.displayMessageLine("\nInsuffiecent funds in your account. \n\n Please choose a smaller amount.");
				} //end else				
			} //end if
			else
			{
				screen.displayMessageLine("\nCanceling transaction...");
				return;
			} //end else
		} while(!cashDispensed);
		
	} //end execute method

	private int dispalyMenuOfAmounts() 
	{
		int userChoice = 0;
		
		Screen screen = getScreen();
		
		int[] amounts = {0, 20, 40, 60, 100, 200};
		
		while (userChoice == 0)
		{
			screen.displayMessageLine("\nWithdrawal Menu: ");
			screen.displayMessageLine("1 - $20");
			screen.displayMessageLine("2 - $40");
			screen.displayMessageLine("3 - $60");
			screen.displayMessageLine("4 - $100");
			screen.displayMessageLine("5 - $200");
			screen.displayMessageLine("6 - Cancel transaction");
			screen.displayMessage("\nChoose a withdrawal amount: ");
			
			int input =keypad.getInput();
			
			switch(input)
			{
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				userChoice = amounts[input];
				break;
			case CANCELED:
				userChoice = CANCELED;
				break;
				default:
					screen.displayMessageLine("\nInvalid selection. Please try again.");
			} //end switch
		} //end while
		
		return userChoice;
	} //end displayMenuOfAmountsmethod
	
} //end motherfucking Withdrawal class
