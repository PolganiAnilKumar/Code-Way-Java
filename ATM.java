import java.util.Scanner;

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String withdraw(double amount) {
        if (userAccount.checkBalance() >= amount) {
            userAccount.decreaseBalance(amount);
            return "Withdrawal successful. Remaining balance: " + userAccount.checkBalance();
        } else {
            return "Insufficient funds for withdrawal.";
        }
    }

    public String deposit(double amount) {
        userAccount.increaseBalance(amount);
        return "Deposit successful. Updated balance: " + userAccount.checkBalance();
    }

    public String checkBalance() {
        return "Current balance: " + userAccount.checkBalance();
    }

    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter initial balance for your bank account: ");
            double initialBalance = scanner.nextDouble();

            BankAccount userAccount = new BankAccount(initialBalance);
            ATM atm = new ATM(userAccount);

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");

                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        System.out.println(atm.withdraw(withdrawAmount));
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        System.out.println(atm.deposit(depositAmount));
                        break;
                    case 3:
                        System.out.println(atm.checkBalance());
                        break;
                    case 4:
                        System.out.println("Exiting. Thank you visit us again!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            }
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }

    public void decreaseBalance(double amount) {
        balance -= amount;
    }
}




