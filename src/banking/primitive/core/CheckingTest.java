/**
 * 
 */
package banking.primitive.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @authors Alireza Bahremand, and Cecilia La Place
 *
 */
public class CheckingTest {
	// Create objects that will be going under testing, values to be initialized
	// within the setup methods.
	private static Checking checkingAccountJCusack;
	//private static Savings savingsAccountJCusack;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		checkingAccountJCusack = new Checking("John Cusack", (float) 4500000.00);
		//savingsAccountJCusack = new Savings("John Cusack Twin II", (float) 658755550.33);
	}


	/**
	 * testCheckingDeposit checks the deposit method for the checking class. 
	 */
	@Test 
	public void testCheckingDeposit() {
		assertFalse(checkingAccountJCusack.deposit((float) -100.00));
		assertFalse(checkingAccountJCusack.deposit((float) -45.44));
		assertFalse(checkingAccountJCusack.deposit((float) -0.05));
		
		assertEquals(checkingAccountJCusack.deposit((float) -444.44), false);
		assertEquals(checkingAccountJCusack.deposit((float) -4.14), false);
		assertEquals(checkingAccountJCusack.deposit((float) -3433.34), false);
		
		assertNotSame(checkingAccountJCusack.deposit((float) -10.00), true);
		assertNotSame(checkingAccountJCusack.deposit((float) -12), true);
		
		// John's balance is 4,500,000
		
		assertEquals(checkingAccountJCusack.deposit((float) 120.00), true);
		assertEquals(checkingAccountJCusack.deposit((float) 1000.10), true);
		assertNotSame(checkingAccountJCusack.deposit((float) 1220.00), false);
		
		assertTrue(checkingAccountJCusack.deposit((float) 34.43));
		assertTrue(checkingAccountJCusack.deposit((float) 50000.00));
		assertTrue(checkingAccountJCusack.deposit((float) 234.32));
		
		//assertFalse(checkingAccountJCusack.deposit(" "));
		//assertFalse(checkingAccountJCusack.deposit("a"));
		//assertFalse or assertNull(checkingAccountJCusack.deposit(null));
		
		//assertFalse(checkingAccountJCusack.deposit(2e4.44);
		//assertFalse(checkingAccountJCusack.deposit(234.556);
		//assertFalse(checkingAccountJCusack.deposit("SER316");
	}
	
	/**
	 * testCheckingWithdrawal checks the withdrawal method for the checking class. 
	 */
	@Test 
	public void testCheckingWithdrawal() {
		assertFalse(checkingAccountJCusack.withdraw((float) -121.33));
		assertFalse(checkingAccountJCusack.withdraw((float) -56.42));
		assertFalse(checkingAccountJCusack.withdraw((float) -0.008));
		
		// 4552608.85
		// 10 withdrawals to trigger a $2 fee.
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 2330.30), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 121100.58), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 45565.58), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		assertEquals(checkingAccountJCusack.withdraw((float) 100.00), true);
		
		
		assertNotSame(checkingAccountJCusack.withdraw((float) -1232.33), true);
		assertNotSame(checkingAccountJCusack.withdraw((float) -23322.33), true);

		assertTrue(checkingAccountJCusack.withdraw((float) 1212.00));
		assertTrue(checkingAccountJCusack.withdraw((float) 12.10));
		
		// Cause a overdraw
		assertTrue(checkingAccountJCusack.withdraw(100000000000f));
		// Get full coverage by doing one last withdraw with a overdraw.
		assertFalse(checkingAccountJCusack.withdraw(1000100.00f));
	}
	
	/**
	 * testSavingsDeposit checks the deposit method for the savings class. 
	 */
	/*@Test 
	public void testSavingsDeposit() {
		//assertFalse(savingsAccountJCusack.deposit((float) -100.00));
		//assertFalse(savingsAccountJCusack.deposit((float) -45.44));
		//assertFalse(savingsAccountJCusack.deposit((float) -0.05));
		
		//assertFalse(savingsAccountJCusack.deposit(" "));
		//assertFalse(savingsAccountJCusack.deposit("a"));
		//assertFalse or assertNull(savingsAccountJCusack.deposit(null));
		
		//assertFalse(savingsAccountJCusack.deposit(2e4.44);
		//assertFalse(savingsAccountJCusack.deposit(234.556);
		//assertFalse(savingsAccountJCusack.deposit("SER316");
		
		//assertTrue(savingsAccountJCusack.deposit((float) 34.43));
		//assertTrue(savingsAccountJCusack.deposit((float) 50000.00));
		//assertTrue(savingsAccountJCusack.deposit((float) 234.32));
	}*/

	
	/**
	 * testSavingsWithdrawal checks the withdrawal method for the savings class. 
	 */
	/*@Test 
	public void testSavingsWithdrawal() {
		assertFalse(savingsAccountJCusack.deposit((float) -121.33));
		assertFalse(savingsAccountJCusack.deposit((float) -56.42));
		assertFalse(savingsAccountJCusack.deposit((float) -0.008));
		
		//assertFalse(savingsAccountJCusack.deposit(" "));
		//assertFalse(savingsAccountJCusack.deposit("b"));
		//assertFalse or assertNull(savingsAccountJCusack.deposit(null));
		
		//assertFalse(savingsAccountJCusack.deposit("ser316"));
		//assertFalse(savingsAccountJCusack.deposit("233$$$.$$"));
		//assertFalse(savingsAccountJCusack.deposit("six"));
		
		assertTrue(savingsAccountJCusack.deposit((float) 795.55));
		assertTrue(savingsAccountJCusack.deposit((float) 1000000000.00));
		assertTrue(savingsAccountJCusack.deposit((float) 30.00));
	}*/
	

}
