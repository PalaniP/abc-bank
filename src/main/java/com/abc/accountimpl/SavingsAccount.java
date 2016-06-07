package com.abc.accountimpl;

import com.abc.Account;

public class SavingsAccount extends Account{

    public static final int SAVINGS = 1;


    public SavingsAccount() {
    	super();
    }

    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;

    }

    public int getAccountType() {
        return SAVINGS;
    }

}
