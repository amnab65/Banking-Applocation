public class BankAccount {
    private int accountNumber;
    private int pin;
    private String name;
    private double currentBalance;

    public BankAccount(int accountNumber, int pin, String name, double currentBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.currentBalance = currentBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit amount must be positive.");
            return false;
        }
        
        currentBalance += amount;
        System.out.printf("$%.2f has been deposited to your account.\n", amount);
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal amount must be positive.");
            return false;
        }
        
        if (amount > currentBalance) {
            System.out.println("Insufficient funds. Your withdrawal cannot be processed.");
            return false;
        }
        
        currentBalance -= amount;
        System.out.printf("$%.2f has been withdrawn from your account.\n", amount);
        return true;
    }

    public void checkBalance() {
        System.out.printf("Current balance: $%.2f\n", currentBalance);
    }

    public boolean validatePin(int inputPin) {
        return pin == inputPin;
    }
}