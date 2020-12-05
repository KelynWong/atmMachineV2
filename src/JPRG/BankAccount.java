/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPRG;

import java.io.Serializable;

/**
 *
 * @author Wong En Ting Kelyn Admission number: P1935800 Class: DIT/1B/01
 *
 */
public class BankAccount implements Serializable {

    private String accountNo, password, type, name;
    private double balance, interestEarned, intRate;

    public BankAccount(String name, String accountNo, String password, double balance, double interestEarned, double intRate, String type) {
        this.name = name;
        this.accountNo = accountNo;
        this.password = password;
        this.balance = balance;
        this.interestEarned = interestEarned;
        this.intRate = intRate;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return this.type;
    }

    public double getTotalInterest() {
        return this.interestEarned;
    }

    public void setInterestEarned(double balance, double preBal) {
        double interest = balance * intRate;
        this.interestEarned += interest;
    }

    public void calculateBalanceIntRate(double balance, double intRate) {
        double interest = balance * intRate;
        this.balance += interest;
    }

    public double getIntRate() {
        return this.intRate;
    }
}
