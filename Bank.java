import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccount> accounts;
    private Scanner scanner;
    private BankAccount currentAccount;

    public Bank() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        accounts.add(new BankAccount(1001, 1234, "John Doe", 500.00));
        accounts.add(new BankAccount(1002, 2345, "Jane Smith", 1200.00));
        accounts.add(new BankAccount(1003, 3456, "Alice Johnson", 850.00));
    }

    public BankAccount findAccount(int accountNumber, int pin) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.validatePin(pin)) {
                    return account;
                } else {
                    System.out.println("Incorrect PIN. Please try again.");
                    return null;
                }
            }
        }
        System.out.println("Account not found. Please check your account number.");
        return null;
    }

    public boolean login() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your account number: ");
            int accountNumber;
            
            try {
                accountNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric account number.");
                attempts++;
                continue;
            }
            
            System.out.print("Enter your PIN: ");
            int pin;
            
            try {
                pin = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric PIN.");
                attempts++;
                continue;
            }
            
            currentAccount = findAccount(accountNumber, pin);
            
            if (currentAccount != null) {
                System.out.println("Login successful. Welcome, " + currentAccount.getName() + "!");
                return true;
            }
            
            attempts++;
            if (attempts < MAX_ATTEMPTS) {
                System.out.println("You have " + (MAX_ATTEMPTS - attempts) + " attempt(s) remaining.");
            }
        }
        
        System.out.println("Too many failed attempts. For security reasons, the program will now exit.");
        return false;
    }

    public void performTransaction() {
        boolean continueTransactions = true;
        
        while (continueTransactions) {
            System.out.println("\n==== Transaction Menu ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    currentAccount.checkBalance();
                    break;
                    
                case "2":
                    System.out.print("Enter deposit amount: $");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.deposit(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                    }
                    break;
                    
                case "3":
                    System.out.print("Enter withdrawal amount: $");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.withdraw(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                    }
                    break;
                    
                case "4":
                    continueTransactions = false;
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
            
            if (continueTransactions) {
                System.out.print("\nDo you want to perform another transaction? (Y/N): ");
                String another = scanner.nextLine().trim().toUpperCase();
                if (!another.equals("Y")) {
                    continueTransactions = false;
                    System.out.println("Thank you for using our ATM. Goodbye!");
                }
            }
        }
    }
}