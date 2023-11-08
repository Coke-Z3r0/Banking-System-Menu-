/**
 * Name: Account.Java
 * Author:Lee McGuire Faud
 * Date: 11/2/2023
 * Description: This is the Class that determines the customers account number and their initial
 * balance.
 */
public abstract class Account {
    protected static double balance;
    private int accountNumber;
    private Customer customer;


    public Account(Customer customer, int accountNumber) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);

    public double getBalance() {
        return balance;
    }
}
