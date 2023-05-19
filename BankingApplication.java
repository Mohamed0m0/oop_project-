
package bankingapplication;

public class BankingApplication extends Application {



    private List<BankAccount> bankAccounts = new ArrayList<>();

    private TextField accountNumberField, amountField, balanceField;

    private Label statusLabel;

     private  PasswordField passwordField;



@Override

public void start(Stage primaryStage) throws Exception {
     GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
 
        Label accountNumberLabel = new Label("Account Number:");
        accountNumberField = new TextField();
        Label amountLabel = new Label("Amount:");
        amountField = new TextField();
        Label balanceLabel = new Label("Balance:");
        balanceField = new TextField();
        balanceField.setEditable(false);
        Label password = new Label("password:");
        passwordField = new PasswordField();
        Button checkBalanceButton = new Button("Check Balance");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button createAccountButton = new Button("Create Account");
        Button deleteAccountButton = new Button("Delete Account");
        Button exitButton = new Button("Exit");
        statusLabel = new Label();
 
        gridPane.add(accountNumberLabel, 0, 0);
        gridPane.add(accountNumberField, 1, 0);
        gridPane.add(amountLabel, 0, 2);
        gridPane.add(amountField, 1, 2);
        gridPane.add(balanceLabel, 0, 3);
        gridPane.add(balanceField, 1, 3);
        gridPane.add(password, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(checkBalanceButton, 0, 4);
        gridPane.add(depositButton, 1, 4);
        gridPane.add(withdrawButton, 2, 4);
        gridPane.add(createAccountButton, 0, 5);
        gridPane.add(deleteAccountButton, 1, 5);
        gridPane.add(exitButton, 2, 5);
        gridPane.add(statusLabel, 0, 6, 3, 1);
 
        checkBalanceButton.setOnAction(event -> checkBalance()); 
        depositButton.setOnAction(event -> deposit());
        withdrawButton.setOnAction(event -> withdraw());
        createAccountButton.setOnAction(event -> createAccount());
        deleteAccountButton.setOnAction(event -> deleteAccount());
        exitButton.setOnAction(event -> exit());
 
        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setTitle("Banking Application");
        primaryStage.setScene(scene);
        primaryStage.show();
      }
      private void checkBalance() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        BankAccount account = findAccount(accountNumber); 
        String password = (passwordField.getText());
        String password2 = account.getpassword();
        if (account != null) {
            if(!password.equals(password2))
            {
                balanceField.setText("");
                statusLabel.setText("Wrong Password");
                return;
            }
            balanceField.setText(String.format("$%.2f", account.getBalance()));
            statusLabel.setText("");
        } else {
            statusLabel.setText("Account not found.");
        }
    }

    private void deposit() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        BankAccount account = findAccount(accountNumber); 
        String password = (passwordField.getText());
        if (account != null) {
        String password2 = account.getpassword();
              if(!password.equals(password2))
            {
                statusLabel.setText("Wrong Password");
                return;
            }
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            balanceField.setText(String.format("$%.2f", account.getBalance()));
            statusLabel.setText("Deposit successful.");
        } else {
            statusLabel.setText("Account not found.");
        }
    }

    private void withdraw() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
       BankAccount account = findAccount(accountNumber); 
        String password = (passwordField.getText());
        if (account != null) {
        String password2 = account.getpassword();
              if(!password.equals(password2))
            {
                statusLabel.setText("Wrong Password");
                return;
            }
            double amount = Double.parseDouble(amountField.getText());
            double balance = account.getBalance();
            if(amount<=balance)
            {
            account.withdraw(amount);
            balanceField.setText(String.format("$%.2f", account.getBalance()));
            statusLabel.setText("Withdrawal successful.");
            }else
            {
            balanceField.setText(String.format("$%.2f", account.getBalance()));
            statusLabel.setText("Insufficient balance.");
            }
        } else {
            statusLabel.setText("Account not found.");
        }
    }

    private BankAccount findAccount(int accountNumber) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private void createAccount() {  
            try {
                int accountNumber = Integer.parseInt(accountNumberField.getText()); 
                String password = (passwordField.getText());
                BankAccount account2 = findAccount(accountNumber); 
                if(account2!=null)
                {
                statusLabel.setText("This account already exists.");
                }
                else
                {
                double balance = Double.parseDouble(amountField.getText());
                BankAccount account; 
                    account = new CheckingAccount(accountNumber, balance,password); 
                bankAccounts.add(account);
                balanceField.setText(String.format("$%.2f", account.getBalance()));
                statusLabel.setText("Account created successfully.");
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("Invalid information.");
                 balanceField.setText(" ");
            } catch (IllegalArgumentException ex) {
                statusLabel.setText(ex.getMessage());
            } 
    }

    private void deleteAccount() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        BankAccount account = findAccount(accountNumber); 
        String password = (passwordField.getText());
        if (account != null) {
        String password2 = account.getpassword();
              if(!password.equals(password2))
            {
                statusLabel.setText("Wrong Password");
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Account");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this account?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bankAccounts.remove(account);
                balanceField.setText("");
                statusLabel.setText("Account deleted successfully.");
            }
        } else {
            statusLabel.setText("Account not found.");
        }
    }

    private void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
