
public abstract class Transaction {
	
	private int accountNumber;
	private Screen screen;
	private BankDatabase bankDatabase;
	
	public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase)
	{
		accountNumber = userAccountNumber;
		screen = atmScreen;
		bankDatabase = atmBankDatabase;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public abstract void execute();

	public Screen getScreen() {
		return screen;
	}

	public BankDatabase getBankDatabase() {
		return bankDatabase;
	}
}
