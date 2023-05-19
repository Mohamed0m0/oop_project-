package bankingapplication;



public class CheckingAccount extends BankAccount implements BankTransaction {



    public CheckingAccount(int accountNumber, double balance,String password) {

        super(accountNumber, balance,password);

    }



    @Override

    public String getAccountType() {

        return "Checking";

    }



    @Override

    public void deposit(double amount) {

        setBalance(getBalance() + amount);

    }



    @Override

    public void withdraw(double amount) {

        if (getBalance() >= amount) {

            setBalance(getBalance() - amount);

        } else {

            System.out.println("Insufficient balance.");

        }

    }

}
