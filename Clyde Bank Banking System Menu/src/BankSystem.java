/**
 * Name: BankSystem.Java
 * Author:Lee McGuire Faud
 * Date: 11/2/2023
 * Description: This is the class that houses all the functions, and holds the main menu.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList();
    private int accountNumberCounter = 1001;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to Glasgow Clyde Bank!");
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void register() {
        System.out.println("Registration:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println("Select account type:");
        System.out.println("1. Basic\n2. Premium");
        System.out.print("Enter account type (1/2): ");
        int accountTypeChoice = scanner.nextInt();
        System.out.println("Your account Number is: "+accountNumberCounter);
        scanner.nextLine(); // Consume the newline character

        Customer customer = new Customer(firstName, lastName, password);
        customers.add(customer);

        if (accountTypeChoice == 1) {
            Account account = new BasicAccount(customer, generateAccountNumber());
            accounts.add(account);
        } else if (accountTypeChoice == 2) {
            double overdraftLimit = 2000;
            Account account = new PremiumAccount(customer, generateAccountNumber(), overdraftLimit);
            accounts.add(account);
        } else {
            System.out.println("Invalid choice. Account not created.");
        }

        System.out.println("Registration successful!");
    }

    public void login() {
        System.out.println("Login:");
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null && account.getCustomer().verifyPassword(password)) {
            accountMenu(account);
        } else {
            System.out.println("Login failed. Account not found or password incorrect.");
        }
    }

    public void accountMenu(Account account) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\nAccount Menu");
            System.out.println("1. Deposit\n2. Withdraw\n3. View Balance\n4. Logout");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                        System.out.println("Your new account balance is: "+ account.getBalance());
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 4:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int generateAccountNumber() {
        return accountNumberCounter++;
    }

    private Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
