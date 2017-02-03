/**
 * 
 */
package banking.primitive.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alireza Bahremand, and Cecilia La Place
 *
 */
public class SavingsTest {
	private static Savings savingsAccountJCusack;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		savingsAccountJCusack = new Savings("John Cusack", (float) 100.00);
	}

	@Test
	public void testDeposit() {
		// Test statements to determine error with Savings.deposit function.
		assertFalse(savingsAccountJCusack.deposit(-3443.44f));
		assertFalse(savingsAccountJCusack.deposit(-243.33f));
		assertFalse(savingsAccountJCusack.deposit(-0.10f));
		
		assertTrue(savingsAccountJCusack.deposit(120.00f));
		assertTrue(savingsAccountJCusack.deposit(20.50f));
		assertTrue(savingsAccountJCusack.deposit(70.50f));
		
		// 1C Test Sequence
		assertFalse(savingsAccountJCusack.deposit(-50.00f));
		// Should cause account to close.
		assertNotSame(savingsAccountJCusack.deposit(0.25f), false);
		assertTrue(savingsAccountJCusack.deposit(50f));
		assertEquals(savingsAccountJCusack.deposit(0.01f), true);
		assertEquals(savingsAccountJCusack.deposit(135.0f), true);
		assertNotSame(savingsAccountJCusack.deposit(125.75f), false);
	
		// Account should contain 102699.91
	}
	
	@Test
	public void testWithdrawal() {
		assertFalse(savingsAccountJCusack.withdraw(-25.00f));
		
		// This statement must withdraw 4 times so we can receive the free of $1.
		assertTrue(savingsAccountJCusack.withdraw(20.00f));
		assertTrue(savingsAccountJCusack.withdraw(20.00f));
		assertTrue(savingsAccountJCusack.withdraw(20.00f));
		// For the final assert below, we will be charged a 1 free, so balance is 19$
		assertTrue(savingsAccountJCusack.withdraw(20.00f));
		// John Cusack has 19.00 at this point.

		// Want to set our amount back to 80 for next series of tests.
		assertTrue(savingsAccountJCusack.deposit(61.50f));
		assertTrue(savingsAccountJCusack.withdraw(20f));
		assertNotSame(savingsAccountJCusack.withdraw(20f), false);
		assertNotSame(savingsAccountJCusack.withdraw(20f), false);
		// After here we get a true return but will have a overdraw with balance of -4
		assertNotSame(savingsAccountJCusack.withdraw(20), false);
		
		// Want to set our quantity of cash back to 50 for another overdraw test.
		assertTrue(savingsAccountJCusack.deposit(55.50f));
		// This statement will cause an overdraw.
		assertTrue(savingsAccountJCusack.withdraw(100f));
		
		// Balance is at -50 at this point, need to restore to 100
		assertNotSame(savingsAccountJCusack.deposit(101.75f), false);
		assertTrue(savingsAccountJCusack.withdraw(0.25f));
		// Balance returns as 50.0
		// Statement test should return false as first conditional checks if 
		// withdraw amount is greater than 0.
		assertFalse(savingsAccountJCusack.withdraw(0.00f));
	}

}
