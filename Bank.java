import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleBankingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n--- Simple Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    accounts.add(createAccount());
                    System.out.println("Account created successfully!");
                    break;
                case 2:
                    deposit(accounts, scanner);
                    break;
                case 3:
                    withdraw(accounts, scanner);
                    break;
                case 4:
                    checkBalance(accounts, scanner);
                    break;
                case 5:
                    accountDetails(accounts, scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static Account createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        return new Account(name);
    }

    private static void deposit(List<Account> accounts, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.deposit(amount);
                System.out.println("Deposit successful!");
                return;
            }
        }

        System.out.println("Account not found.");
    }

    private static void withdraw(List<Account> accounts, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getBalance() >= amount) {
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance.");
                }
                return;
            }
        }

        System.out.println("Account not found.");
    }

    private static void checkBalance(List<Account> accounts, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("Current balance: " + account.getBalance());
                return;
            }
        }

        System.out.println("Account not found.");
    }

    private static void accountDetails(List<Account> accounts, Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("Account Details:");
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Account Holder: " + account.getAccountHolder());
                System.out.println("Current Balance: " + account.getBalance());
                System.out.println("Transaction History:");
                for (Transaction transaction : account.getTransactionHistory()) {
                    System.out.println(transaction);
                }
                return;
            }
        }

        System.out.println("Account not found.");
    }
}

class Account {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountHolder) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " - " + amount;
    }
}