package atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import atm.Atm.Acct;
import atm.Atm.Cust;

public class AtmLauncher {

	public static void main(String[] args) {
		List<Cust> custs = new ArrayList<Cust>();
		List<Acct> accts1 = new ArrayList<Acct>();
		List<Acct> accts2 = new ArrayList<Acct>();
		Acct checking1 = new Acct("Checking", "6789", new BigDecimal("25.00"));
		Acct savings1 = new Acct("Savings", "6788", new BigDecimal("50.00"));
		Acct checking2 = new Acct("Checking", "9876", new BigDecimal("25.00"));
		Acct savings2 = new Acct("Savings", "9877", new BigDecimal("50.00"));
		accts1.add(checking1);
		accts1.add(savings1);
		accts2.add(checking2);
		accts2.add(savings2);
		Cust LSW = new Cust("Luke Skywalker","12345","4234895478653427","1111",accts1);
		Cust OWK = new Cust("Obi-Wan Kenobi","54321","4234854741235476","2222",accts2);
		custs.add(LSW);
		custs.add(OWK);
		Atm atm = new Atm(new BigDecimal("1000.00"));
	    atm.operatingSystem(custs);
	}

}
