package atm;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class Atm {

	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private BigDecimal maxWithdrawal;
	Scanner input = new Scanner(System.in);

	public static class Cust {
		private String custName;
		private String custNum;
		private String pin;
		private String debitCardNumber;
		private List<Acct> accts;

		private String getCustName() {
			return custName;
		}
		private String getCustNum() {

			return custNum;
		}
		private String getDebitCardNumber() {
			return debitCardNumber;
		}
		private String getPin() {

			return pin;
		}
		private void setPin(String newPin) {

			pin = newPin;
		}
		private List<Acct> getAccts() {
			return accts;
		}
		public Cust(String custName, String custNum, String debitCardNumber, String pin, List<Acct> accts) {
			this.custName = custName;
			this.custNum = custNum;
			this.pin = pin;
			this.debitCardNumber = debitCardNumber;
			this.accts = accts;
		}
	}

	public static class Acct {
		
		private String acctName;
		private String acctNum;
		private BigDecimal bal;

		public Acct(String acctName, String acctNum, BigDecimal bal) {
			this.acctName = acctName;
			this.acctNum = acctNum;
			this.bal = bal;
		}
		private String getAcctName() {
			return acctName;
		}
		private String getAcctNum() {
			return acctNum;
		}
		private BigDecimal getBal() {
			return bal;
		}
		private void setBal(BigDecimal newBal) {
			bal = newBal;
		}
	}

	public void operatingSystem(List<Cust> custs) {
		printInitGreeting();
		Cust cust = scanDebitCard(custs);
		boolean anotherTransaction = true;
		int numTransactions = 0;
		while (anotherTransaction) {
	
			if (numTransactions == 0) {
				System.out.println("Please enter your 4 digit account pin!");
			} else {
				System.out.println("Please re-enter your 4 digit account pin!");
			}
	
			mainMenu(verifyPin(cust));
	
			System.out.println("Would you like to make another transaction? Y for Yes or any key for No?");
			if (!input.nextLine().equalsIgnoreCase("y")) {
				anotherTransaction = false;
			}
			numTransactions++;
		}
	
	}

	private void printInitGreeting() {
		System.out.println("Welcome to ATM!");
		System.out.println("Type Exit to End Session at Any Time!");
		System.out.println("Please insert your debit card (enter your 16 digit debit card number)!");
	}

	private Cust scanDebitCard(List<Cust> custs) {
		String debitCard;
		for (int numInvalidResponses = 0; !validateInput(debitCardNumberValid(debitCard = input.nextLine(), custs),
				debitCard, numInvalidResponses, "Customer Not Found! \nPlease enter a 16 digit debit card number associated with a customer on record!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		return getCust(debitCard, custs);
	}

	private boolean validateInput(Boolean condition, String userInput, int numInvalidResponses, String message1,
			String message2, String escapeCommand) {
	
		if (condition) {
			return true;
		} else if (userInput.equalsIgnoreCase(escapeCommand)) {
			System.exit(0);
		} else if (!userInput.equalsIgnoreCase(escapeCommand)) {
			numInvalidResponses++;
			if (numInvalidResponses >= 3) {
				System.out.println(message2);
				System.exit(0);
			}
	
			System.out.println(message1);
		}
		return false;
	}

	private boolean debitCardNumberValid(String debitCardNumberIn, List<Cust> Custs) {
		for (Cust cust : Custs) {
			if (debitCardNumberIn.equals(cust.getDebitCardNumber())) {
				return true;
			}
		}
		return false;
	}

	private Cust getCust(String debitCard, List<Cust> custs) {
		for (Cust cust : custs) {
			if (debitCard.equals(cust.getDebitCardNumber())) {
				return cust;
			}
		}
		return null;
	}

	private Cust verifyPin(Cust cust) {
		String pinEntered;
		for (int numInvalidResponses = 0; !validateInput(verifyPin(pinEntered = input.nextLine(), cust), pinEntered,
				numInvalidResponses, "Access Denied! Please enter a valid 4 digit pin number between 0000 and 9999!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		return cust;
	}

	private boolean verifyPin(String pin, Cust cust) {
	
		if (pin.equals(cust.getPin())) {
			return true;
		} else
			return false;
	
	}

	private void mainMenu(Cust cust) {
		System.out.println("Hello " + cust.getCustName() + "! What would you like to Do?");
		System.out.println("Press 1 for Balance");
		System.out.println("Press 2 for Deposit");
		System.out.println("Press 3 for Withdrawal");
		System.out.println("Press 4 for Transfer");
		System.out.println("Press 5 to Reset Your Pin");
	
		String menuSelection;
	
		for (int numInvalidResponses = 0; !validateInput(
				isMenuOption(input.hasNextInt(), menuSelection = input.nextLine(), 5), menuSelection,
				numInvalidResponses,
				"Not a valid menu option. Please enter a number between 0 and 4 associated with a menu option!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
	
		switch (Integer.parseInt(menuSelection)) {
		case 1: {
	        printBal(acctMenu(cust));
			break;
		}
		case 2: {
			deposit(depositPrompt(acctMenu(cust)));
			break;
		}
		case 3: {
			withdraw(withdrawalPrompt(acctMenu(cust)));
			break;
		}
		case 4: {
	         transfer(transferFrom(cust),transferTo(cust));
	         break;
		}
		case 5: {
			resetPin(cust);
			break;
		}
		default: {
			System.out.println("Switch Failed");
		}
		}
	}

	private boolean isMenuOption(boolean inputIsInt, String userInput, int numOptions) {
		if (inputIsInt) {
			if (Integer.parseInt(userInput) > 0 && Integer.parseInt(userInput) <= numOptions) {
				return true;
			}
		}
		return false;
	}

	private Acct acctMenu(Cust cust) {
		String userInput;
	
		for (Acct acct : getAccts(cust)) {
	
			System.out.println("Press " + (getAccts(cust).indexOf(acct) + 1) + " for " + acctNum(acct) + "*****" + acctName(acct));
	
		}
		for (int numInvalidResponses = 0; !validateInput(
				isMenuOption(input.hasNextInt(), userInput = input.nextLine(), getAccts(cust).size()), userInput,
				numInvalidResponses,
				"Not a valid menu option. Please enter a number between 0 and " + getAccts(cust).size()
						+ " associated with a menu option!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
	
		return getAcct(cust, userInput);
	}

	private List<Acct> getAccts(Cust currCust) {
		return currCust.getAccts();
	}

	private Acct getAcct(Cust currCust, String userInput) {
		return getAccts(currCust).get(Integer.parseInt(userInput) - 1);
	}

	private void printBal(Acct acct) {
		System.out.println(
				"The balance of the " + acctName(acct) + " account *****" + acctNum(acct) + " is " + balance(acct));
	
	}

	private String balance(Acct acct) {
		return formatter.format(acct.getBal());
	}

	private String acctName(Acct acct) {
		
		return acct.getAcctName();
	}

	private String acctNum(Acct acct) {
	
		return acct.getAcctNum();
	}

	private Acct depositPrompt(Acct acct) {
		System.out.println(
				"Please enter the deposit amount for " + acct.getAcctName() + " account *****" + acctNum(acct));
	
		return acct;
	}

	private Acct deposit(Acct acct) {
		String depositAmt;
		for (int numInvalidResponses = 0; !validateInput(
				isValidDeposit(input.hasNextDouble(), depositAmt = input.nextLine()), depositAmt, numInvalidResponses,
				"Please enter a valid deposit amount. Must be greater than 0 (ex 25.37)!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		acct.setBal(acct.getBal().add(new BigDecimal(depositAmt)));
		System.out.println("Deposit successful for " + acctName(acct) + " account *****" + acctNum(acct));
	
		return acct;
	}

	private boolean isValidDeposit(boolean hasDouble, String userInput) {
	
		if (hasDouble) {
			if (Double.parseDouble(userInput) > 0.00) {
				return true;
			}
		}
	
		return false;
	}

	private Acct withdrawalPrompt(Acct acct) {
	
		System.out.println("This Atm Carries Twenty Dollar Bills!");
		System.out.println("Please enter withdrawal amount!");
	
		return acct;
	
	}

	private Acct withdraw(Acct acct) {
		String withdrawalAmt;
	
		for (int numInvalidResponses = 0; !validateInput(
				isValidWithdrawalAmt(input.hasNextDouble(), withdrawalAmt = input.nextLine(), acct), withdrawalAmt,
				numInvalidResponses, "Please enter a valid withdrawal amount. Must be a multiple of 20.00 (ex 40.00)!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
	
		acct.setBal(acct.getBal().subtract(new BigDecimal(withdrawalAmt)));
	
		System.out.println("Withdaw successful for " + acctName(acct) + " account *****" + acctNum(acct)
				+ "Please take your cash!");
		
		return acct;
	
	}

	private boolean isValidWithdrawalAmt(boolean hasNextDouble, String withdrawalAmt, Acct acct) {
		if (hasNextDouble) {
			BigDecimal reqAmt = new BigDecimal(withdrawalAmt);
			int denom1 = 20;
			int testReqAmt = reqAmt.intValue();
			if (testReqAmt % denom1 == 0 && testReqAmt > 0) {
				if (reqAmt.compareTo(acct.getBal()) <= 0) {
					if (reqAmt.compareTo(maxWithdrawal) == 1) {
						System.out.println("Max Withdrawal Amount Exceeded!");
						System.out.println("Atm Maximum is " + formatter.format(maxWithdrawal));
						return false;
					}
					return true;
				}
				System.out.print("Insufficient Funds!");
				return false;
			}
	
		}
	
		return false;
	
	}

	private void resetPin(Cust cust) {
		String newPin = "0000";
	
		verifyCustNum(cust);
		
		System.out.print("Please enter your current pin!");
		verifyPin(cust);
	
		while (!confirmNewPin().equals(newPin=newPin())) {
			System.out.println("Confirm new pin does not match new pin!");
		}
		
		System.out.println("Reset Pin Successful!");
	
		cust.setPin(newPin);
	
	}

	private Cust verifyCustNum(Cust cust) {
		String custNumEntered;
		System.out.println("Please confirm your customer number (first 5 digits of your account number) to proceed!");
		for (int numInvalidResponses = 0; !validateInput(verifyCustNum(custNumEntered = input.nextLine(), cust),
				custNumEntered, numInvalidResponses,
				"Customer number entered does not match account in Session. Please Try Again!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		return cust;
	}

	private boolean verifyCustNum(String custNumEntered, Cust cust) {
		System.out.print(custNumEntered);
		if (custNumEntered.equals(cust.getCustNum())) {
			
			return true;
		}
		return false;
	}

	private String newPin() {
		String newPinEntered ="";
		System.out.println("Please enter your new pin!");
		for (int numInvalidResponses = 0; !validateInput(
				input.hasNextInt() && (newPinEntered = input.nextLine()).length() == 4, newPinEntered,
				numInvalidResponses, "Please enter a valid 4 digit pin number between 0000 and 9999!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		return newPinEntered;
	}

	private String confirmNewPin() {
		String confirmNewPinEntered ="";
		System.out.println("Please confirm your new pin!");
		for (int numInvalidResponses = 0; !validateInput(
				input.hasNextInt() && (confirmNewPinEntered = input.nextLine()).length() == 4, confirmNewPinEntered,
				numInvalidResponses, "Please enter a valid 4 digit pin number between 0000 and 9999!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		return confirmNewPinEntered;
	}

	private void transfer(Acct from, Acct to) {
		String transferAmt;
		
		transferAmtPrompt(from, to);
	
		for (int numInvalidResponses = 0; !validateInput(
				isValidTransferAmt(input.hasNextDouble(), transferAmt = input.nextLine(), from), transferAmt,
				numInvalidResponses, "Please enter a valid transfer amount. Must be less than balance of account to transfer from!",
				"Maximum Invalid Responses Reached! Session Terminated!", "exit"); numInvalidResponses++)
			;
		from.setBal(from.getBal().subtract(new BigDecimal(transferAmt)));
		to.setBal(from.getBal().add(new BigDecimal(transferAmt)));
		System.out.println("Transfer successful from " + acctName(from) + " account *****" + acctNum(from)
		+ " to " + acctName(to) + " account *****" + acctNum(to) + "!");
	
	}

	private void transferAmtPrompt(Acct from, Acct to) {
		System.out.println("Please enter the amount to transfer from " + acctName(from) + " account *****" + acctNum(from)
		+ " to " + acctName(to) + " account *****" + acctNum(to) + "!");
	}

	private Acct transferFrom(Cust cust) {
		System.out.println("Please select an account to transfer to!");
		Acct from = acctMenu(cust);
		return from;
	}

	private Acct transferTo(Cust cust) {
		System.out.println("Please select an account to transfer from!");
		Acct to = acctMenu(cust);
		return to;
	}

	private Boolean isValidTransferAmt(boolean hasNextDouble, String transferAmt, Acct from) {
		BigDecimal reqAmt = new BigDecimal(transferAmt);
		if (reqAmt.compareTo(from.getBal()) <= 0) {
			return true;	
		}
		return false;
	}

	public Atm(BigDecimal maxWithdrawal) {
		this.maxWithdrawal = maxWithdrawal;
	}

}
