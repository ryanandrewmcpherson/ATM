package atm;

import java.util.Scanner;

import atm.Atm.Acct;

public class AtmClientApp {

	public static void main(String[] args) {

		boolean anotherTransaction = true;
		Scanner input = new Scanner(System.in);
		Atm atm = new Atm(1000);
		Acct checking = new Acct(12345, 6789, 25.00, 1111, 4123);

		int numTransactions = 0;

		System.out.println("Welcome to ATM!");
		System.out.println("Type Exit to End Session at Any Time!");
		System.out.println("Please insert your debit card (enter your 4 digit debit card number)!");
		String userInput;

		for (int numInvalidResponses = 0; !atm.validateInput(atm.debitCardNumberValid(userInput = input.nextLine(), checking),
				userInput, numInvalidResponses,
				"Account Not Found. Please enter a valid 4 digit debit card number between 0000 and 9999!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;

		while (anotherTransaction) {

			if (numTransactions == 0) {
				System.out.println("Please enter your 4 digit account pin!");
			} else {
				System.out.println("Please re-enter your 4 digit account pin!");
			}

			for (int numInvalidResponses = 0; !atm.validateInput(
					atm.validatePin(userInput = input.nextLine(), checking), userInput, numInvalidResponses,
					"Access Denied! Please enter a valid 4 digit pin number between 0000 and 9999!",
					"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
				;

			System.out.println("What would you like to Do?");
			System.out.println("Press 1 for Balance");
			System.out.println("Press 2 for Deposit");
			System.out.println("Press 3 for Withdrawal");
			System.out.println("Press 4 to Reset Your Pin");
			userInput = "0";

			for (int numInvalidResponses = 0; !atm.validateInput(
					atm.isMenuOption(input.hasNextInt(), userInput = input.nextLine()), userInput, numInvalidResponses,
					"Not a valid menu option. Please enter a number between 0 and 4 associated with a menu option!",
					"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
				;

			switch (Integer.parseInt(userInput)) {
			case 1: {
				System.out.println("Your Balance for account ******" + atm.acctNum(checking) + " is:"
						+ atm.balance(checking));
				break;
			}
			case 2: {
				System.out.println("Enter deposit amount!");

				for (int numInvalidResponses = 0; !atm.validateInput(
						atm.isValidDeposit(input.hasNextDouble(), userInput = input.nextLine()), userInput,
						numInvalidResponses, "Please enter a valid deposit amount. Must be greater than 0 (ex 25.37)!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;

				atm.deposit(Double.parseDouble(userInput), checking);
				System.out.println("Deposit Successful for account ******" + atm.acctNum(checking)+"!");
			
				break;
			}
			case 3: {
				System.out.println("This Atm Carries Twenty Dollar Bills!");
				System.out.println("Please enter withdrawal amount!");
	
				for (int numInvalidResponses = 0; !atm.validateInput(
						atm.isValidWithdrawalAmt(input.hasNextDouble(), userInput = input.nextLine(),checking), userInput,
						numInvalidResponses, "Please enter a valid withdrawal amount. Must be a multiple of 20.00 (ex 40.00)!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;
					
				atm.withdraw(Double.parseDouble(userInput), checking);
				System.out.print("Withdrawal Successful, Please take your cash!");
				break;
			}
			case 4: {
				
			    int newPin = 0000;
			    int confirmNewPin = 0001;
				
				System.out.println("Please confirm your customer number (first 5 digits of your account number) to proceed!");
				
				for (int numInvalidResponses = 0; !atm.validateInput(atm.custNumValid(userInput = input.nextLine(), checking),
						userInput, numInvalidResponses,
						"Customer number entered does not match account in Session. Please Try Again!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;
				
				System.out.println("Please enter your current 4 digit pin!");
	
				for (int numInvalidResponses = 0; !atm.validateInput(
						atm.validatePin(userInput = input.nextLine(), checking), userInput, numInvalidResponses,
						"Access Denied! Please enter a valid 4 digit pin number between 0000 and 9999!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;
				
				while(!atm.pinsMatch(newPin, confirmNewPin))
				{
				
				System.out.println("Please enter your new pin!");
				
				for (int numInvalidResponses = 0; !atm.validateInput(input.hasNextInt() && (userInput = input.nextLine()).length() == 4, userInput, numInvalidResponses,
						"Please enter a valid 4 digit pin number between 0000 and 9999!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;
	            
				newPin = Integer.parseInt(userInput);
				
				System.out.println("Please confirm your new pin!");
				
				for (int numInvalidResponses = 0; !atm.validateInput(input.hasNextInt() && (userInput = input.nextLine()).length() == 4, userInput, numInvalidResponses,
						"Please enter a valid 4 digit pin number between 0000 and 9999!",
						"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
					;
				
				confirmNewPin = Integer.parseInt(userInput);
				
				if(!atm.pinsMatch(newPin, confirmNewPin))
				{
			     System.out.println("Confirm new pin does not match new pin!");
				}
 			}
				atm.changePin(newPin,checking);
				System.out.println("Reset Pin Successful!");
				break;
			}
			default: {
				System.out.println("Switch Failed");
			}

			}

			System.out.println("Would you like to make another transaction? Y for Yes or any key for No?");
			if (!input.nextLine().equalsIgnoreCase("y")) {
				anotherTransaction = false;
			}
			numTransactions++;
		}
		input.close();
	}

}
