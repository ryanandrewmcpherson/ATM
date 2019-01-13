package atm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import atm.Atm.Acct;

public class AtmTest {

	@Test
	public void shouldReturnFalseIfInvalidPinEntered() {
		Atm firstpoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		boolean pinValid = firstpoint.validatePin("2222", checking);
		assertFalse(pinValid);
	}

	@Test
	public void shouldReturnFalseifFoundifDebitCardNumberInvalid() {
		Atm firstpoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		boolean acctFound = firstpoint.debitCardNumberValid("4555", checking);
		assertFalse(acctFound);

	}

	@Test
	public void shouldReturnTrueIfvalidPinEntered() {
		Atm firstpoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		boolean pinValid = firstpoint.validatePin("1111", checking);
		assertTrue(pinValid);
	}

	@Test
	public void shouldReturnTrueIfvalidDebitCardNumberEntered() {
		Atm firstPoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		boolean acctFound = firstPoint.debitCardNumberValid("4444", checking);
		assertTrue(acctFound);
	}

	@Test
	public void shouldReturn25ifBalanceViewed() {
		Atm firstPoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		String balance = firstPoint.balance(checking);
		assertEquals("$25.00", balance);
	}

	@Test
	public void balShouldIncreaseby25ifDepositof25ismade() {
		Atm firstPoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		firstPoint.deposit(25.0, checking);
		String balance = firstPoint.balance(checking);
		assertEquals("$50.00", balance);
	}

	@Test
	public void balShouldDecreaseby25IfWithdrawalOf25IsMade() {
		Atm firstPoint = new Atm(1000);
		Acct checking = new Atm.Acct(12345, 6789, 25.00, 1111, 4444);
		firstPoint.withdraw(25.0, checking);
		String balance = firstPoint.balance(checking);
		assertEquals("$0.00", balance);
	}



	
}


