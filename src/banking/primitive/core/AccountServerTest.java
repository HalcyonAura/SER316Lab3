package banking.primitive.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountServerTest {
	//SER 316 Task begins after setUp
	private static AccountServer accountServer = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountServer = AccountServerFactory.getMe().lookup();
	}

	@Before
	public void setUp() throws Exception {
		accountServer.newAccount("Checking", "CheckingTest1", 100.0f);
		accountServer.newAccount("Savings", "SavingsTest1", 200.0f);
		accountServer.newAccount("Checking", "CheckingTest2", 300.0f);
		accountServer.newAccount("Savings", "SavingsTest2", 400.0f);
		
	}
	
	//SER316: Task 2 Step 3 Part d
		@Test
		public void testCloseAcccount(){
			//Test accounts being closed
			accountServer.newAccount("Checking", "CloseCheckingTest", 10.0f);
			accountServer.newAccount("Savings", "CloseSavingsTest", 10.0f);
			assertTrue(!accountServer.closeAccount("Bob's Account"));
			assertTrue(!accountServer.closeAccount(null));
			assertTrue(accountServer.closeAccount("CloseCheckingTest"));
			assertTrue(accountServer.closeAccount("CloseSavingsTest"));		
		}
		
		@Test
		public void testGetActiveAccounts(){
			accountServer.newAccount("Checking", "ActiveCheckingTest", 10.0f);
			accountServer.newAccount("Savings", "ActiveSavingsTest", 10.0f);
			accountServer.newAccount("Checking", "ClosedCheckingTest", 10.0f);
			accountServer.newAccount("Savings", "ClosedSavingsTest", 10.0f);
			
			accountServer.closeAccount("ClosedSavingsAccount");
			accountServer.closeAccount("ClosedCheckingsAccount");
			
			List<Account> res = new ArrayList<>();
			res.add(accountServer.getAccount("ActiveSavingsTest"));
			res.add(accountServer.getAccount("ActiveCheckingTest"));
			assertNotSame(accountServer.getActiveAccounts(), res);
		}
		
		//End Task 2.3.d


	/**
	 * testNewAccountsType checks for valid input types in the 1st parameter
	 */
	@Test (expected=java.lang.IllegalArgumentException.class)
	public void testNewAccountType() {
        assertTrue(!accountServer.newAccount("BLAH", "BadChecking1", 10.0f));
        assertNull(accountServer.getAccount("BadChecking1"));
	}
	
    /**
     * testNewAccountsBalance checks for valid input values in the 3rd parameter
     */
    @Test (expected=java.lang.IllegalArgumentException.class)
    public void testNewAccountBalance() {
      assertTrue(!accountServer.newAccount("Checking", "BadChecking2", -10.0f));
      assertTrue(!accountServer.newAccount("Savings", "BadSavings2", -10.0f));
      assertNull(accountServer.getAccount("BadChecking2"));
      assertNull(accountServer.getAccount("BadChecking2"));
    }   

    /**
     * testNewAccountDuplicate checks that accounts with duplicate names can't be added
     */
    @Test
    public void testNewAccountDuplicate() {
        assertTrue(!accountServer.newAccount("Savings", "CheckingTest1", 500.0f));
        assertTrue(!accountServer.newAccount("Savings", "SavingsTest1", 500.0f));
    }
    
	/**
	 * testNewAccount checks the behavior of adding an account
	 * Question: What should the BVA result in here?
	 */
	@Test
	public void testNewAccount() {
		// Get all the accounts
		List<Account> accounts = accountServer.getAllAccounts();
		
		// Add the accounts
		if (accountServer.newAccount("Checking",  "Checkingnewtest1", 100.0f) &&
		    accountServer.newAccount("Savings",  "Savingsnewtest1", 100.0f)) {
		
		    // Now when I get the accounts again there should be 2 new ones
	        List<Account>updatedAccounts = accountServer.getAllAccounts();
	        // this assert checks that we didn't blow away one that shouldn't be touched
	        assertTrue(updatedAccounts.containsAll(accounts));
	        // These check what we put in got in
	        assertNotNull(accountServer.getAccount("Checkingnewtest1"));
	        assertNotNull(accountServer.getAccount("Savingsnewtest1"));
		} else {
		    fail("failed to add new acocunts");
		}
	}

	@Test
	public void testGetAccount() {
		accountServer.newAccount("Checking", "CheckingGetTest", 100.0f);
		
		// how were each of these tests arrived at?
		assertNotNull(accountServer.getAccount("CheckingGetTest"));
		assertNull(accountServer.getAccount(null));
		assertNull(accountServer.getAccount(""));
		assertNull(accountServer.getAccount("  blah   blah   "));
		assertNull(accountServer.getAccount("CheckingGetTes"));
		assertNull(accountServer.getAccount("heckingGetTest"));
		assertNull(accountServer.getAccount("CheckingGetTest2"));
		assertNull(accountServer.getAccount("Checking GetTest"));
		assertNull(accountServer.getAccount("checkinggettest"));
	}
	
}