package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.accountimpl.CheckingAccount;
import com.abc.accountimpl.MaxiSavingsAccount;
import com.abc.accountimpl.SavingsAccount;
import com.abc.exceptions.AccountTransferException;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingsAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransferMoneySuccess() throws AccountTransferException{
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        
        assertEquals(true,henry.transferMoney(savingsAccount, checkingAccount, 500.0));
        
    }
    
    @Test(expected=AccountTransferException.class)
    public void testTransferMoneyFailure() throws AccountTransferException{
        Account checkingAccount = new CheckingAccount();
        Account checkingAccount1 = new CheckingAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(checkingAccount1);

        checkingAccount.deposit(100.0);
        checkingAccount1.deposit(4000.0);
        
        henry.transferMoney(checkingAccount1, checkingAccount, 500.0);
        
    }
    
}
