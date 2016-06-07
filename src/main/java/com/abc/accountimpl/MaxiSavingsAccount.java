package com.abc.accountimpl;

import com.abc.Account;

public class MaxiSavingsAccount extends Account{

    public static final int MAXI_SAVINGS = 2;


    public MaxiSavingsAccount() {
    	super();
    }

    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;

    }

    public int getAccountType() {
        return MAXI_SAVINGS;
    }

}
