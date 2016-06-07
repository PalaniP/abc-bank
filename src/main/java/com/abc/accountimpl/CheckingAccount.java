package com.abc.accountimpl;

import com.abc.Account;

public class CheckingAccount extends Account{

    public static final int CHECKING = 0;


    public CheckingAccount() {
    	super();
    }

    public double interestEarned() {
        double amount = sumTransactions();
                return amount * 0.001;
    }

    public int getAccountType() {
        return CHECKING;
    }

}
