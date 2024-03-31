import java.util.Scanner;

public class Keypad {
	private Scanner input;
	
	public Keypad()
	{
		input = new Scanner(System.in);
	}
	
	public int getInput()
	{
		return input.nextInt(); //Assuming the user is trustworthy and will input an integer
	}							//real ATM’s keypad permits only integer input
}								//throw an InputMismatchException if the user enters non-integer input
