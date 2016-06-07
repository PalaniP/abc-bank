package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.accountimpl.CheckingAccount;
import com.abc.accountimpl.MaxiSavingsAccount;
import com.abc.accountimpl.SavingsAccount;
import com.abc.exceptions.AccountTransferException;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case CheckingAccount.CHECKING:
                s += "Checking Account\n";
                break;
            case SavingsAccount.SAVINGS:
                s += "Savings Account\n";
                break;
            case MaxiSavingsAccount.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    //This needs to happen in one transaction
    public boolean transferMoney(Account fromAccount,Account toAccount,double transferAmt) throws AccountTransferException{
    	
    	if(fromAccount.getAccountType() == toAccount.getAccountType()){
    		throw new AccountTransferException("Transfer money is not allowed between the same account type for the customer");
    	}
    	
        if (transferAmt <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }

        fromAccount.withdraw(transferAmt);
        
        fromAccount.transactions.add(new Transaction(-transferAmt));
        
        toAccount.deposit(transferAmt);
        
        toAccount.transactions.add(new Transaction(transferAmt));

    	
		return true;
    	
        
    }
}
