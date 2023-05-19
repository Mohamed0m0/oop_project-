





package bankingapplication;

public abstract class BankAccount {
    private int accountNumber;
    private String password;
    private double balance;

    public BankAccount(int accountNumber, double balance,String pass) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.password=pass;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getpassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract String getAccountType();

    @Override
    public String toString() {
        return getAccountType() + " Account #" + accountNumber + " Balance: $" + balance;
    }

    void deposit(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void withdraw(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
