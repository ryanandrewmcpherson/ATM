package atm;

import java.text.NumberFormat;

public class Atm {

	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private int maxWithdrawal;

	public static class Acct {

		private int acctNum;
		private double bal;
		private int pin;
		private int custNum;
		private int debitCardNumber;

		public Acct(int custNum, int acctNum, double bal, int pin, int debitCardNumber) {
			this.custNum = custNum;
			this.acctNum = acctNum;
			this.bal = bal;
			this.pin = pin;
			this.debitCardNumber = debitCardNumber;

		}

		private double getBal() {
			return bal;
		}

		private int getPin() {

			return pin;

		}

		private int getDebitCardNumber() {
			return debitCardNumber;
		}

		private void setBal(double newBal) {

			bal = newBal;

		}

		private int getCustNum() {

			return custNum;
		}

		private int getAcctNum() {
			return acctNum;
		}

		private void setPin(int newPin) {

			pin = newPin;

		}

	}

	public int acctNum(Acct acct) {

		return acct.getAcctNum();
	}

	public boolean validatePin(String string, Acct acct) {

		if (string.equals("" + acct.getPin())) {
			return true;
		} else
			return false;

	}

	public boolean debitCardNumberValid(String debitCardNumberIn, Acct acct) {
		if (debitCardNumberIn.equals("" + acct.getDebitCardNumber())) {
			return true;
		}
		return false;
	}
	
	public boolean custNumValid(String custNumIn, Acct acct) {
		if (custNumIn.equals("" + acct.getCustNum())) {
			return true;
		}
		return false;
	}
	public boolean pinsMatch(int newPin,int confirmNewPin)
	{
      if(newPin == confirmNewPin)
      {
    	return true;
      }
      return false;
	}
	public String balance(Acct acct) {
		return formatter.format(acct.getBal());
	}

	public void deposit(double depositAmt, Acct acct) {

		acct.setBal(acct.getBal() + depositAmt);

	}

	public void withdraw(double withdrawalAmt, Acct acct) {

		acct.setBal(acct.getBal() - withdrawalAmt);

	}

	public void changePin(int newPin, Acct act) {

		act.setPin(newPin); 
		
	}

	public boolean isMenuOption(boolean inputIsInt, String userInput) {
		if (inputIsInt) {
			if (Integer.parseInt(userInput) > 0 && Integer.parseInt(userInput) < 5) {
				return true;
			}
		}
		return false;
	}

	public boolean validateInput(Boolean condition, String userInput, int numInvalidResponses, String message1,
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

	public boolean isValidDeposit(boolean hasDouble, String userInput) {

		if (hasDouble) {
			if (Double.parseDouble(userInput) > 0.00) {
				return true;
			}
		}

		return false;
	}

	public boolean isValidWithdrawalAmt(boolean hasNextDouble, String userInput, Acct acct) {
		if (hasNextDouble) {
			double reqAmt = Double.parseDouble(userInput);
			int denom1 = 20;
			int testReqAmt = (int) (reqAmt / 1);
			if (testReqAmt % denom1 == 0 && testReqAmt > 0) {
				if (reqAmt <= acct.getBal()) {
				if (reqAmt > 1000.00) {
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

	public Atm(int maxWithdrawal) {
		this.maxWithdrawal = maxWithdrawal;
	}
}
