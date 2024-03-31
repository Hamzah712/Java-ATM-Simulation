
public class CashDispenser {
	private final static int INITIAL_COUNT = 500; // Indicates the initial count of bills in the cash dispenser when the ATM starts
	private int count; // track of the number of bills remaining in the CashDispenser at any time. 

	public CashDispenser()
	{
		count = INITIAL_COUNT;
	}
	
	public void dispenseCash(int amount)
	{
		int billsRequired = amount/20;
		
		count -= billsRequired;
	}
	
	public boolean isSufficientCashAvailable(int amount)
	{
		int billsRequired = amount/20;
		
		if (count >= billsRequired)
			return true;
		else
			return false;
	}
}
