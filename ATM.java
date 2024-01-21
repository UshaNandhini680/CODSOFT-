import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATMLogic {
    private BankAccount userAccount;

    public ATMLogic(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayMenu() {
        String menu = "ATM Menu:\n" +
                "1. Check Balance\n" +
                "2. Deposit\n" +
                "3. Withdraw\n" +
                "4. Exit";

        JOptionPane.showMessageDialog(null, menu);
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                JOptionPane.showMessageDialog(null, "Current Balance: $" + userAccount.getBalance());
                break;
            case 2:
                String depositAmountStr = JOptionPane.showInputDialog("Enter deposit amount: $");
                try {
                    double depositAmount = Double.parseDouble(depositAmountStr);
                    userAccount.deposit(depositAmount);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
                break;
            case 3:
                String withdrawalAmountStr = JOptionPane.showInputDialog("Enter withdrawal amount: $");
                try {
                    double withdrawalAmount = Double.parseDouble(withdrawalAmountStr);
                    userAccount.withdraw(withdrawalAmount);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option. Please choose a valid option.");
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + balance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + balance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        String initialBalanceStr = JOptionPane.showInputDialog("Enter initial account balance: $");
        try {
            double initialBalance = Double.parseDouble(initialBalanceStr);
            BankAccount userAccount = new BankAccount(initialBalance);
            ATMLogic atm = new ATMLogic(userAccount);

            while (true) {
                atm.displayMenu();

                String optionStr = JOptionPane.showInputDialog("Choose an option (1-4):");
                try {
                    int option = Integer.parseInt(optionStr);
                    atm.processOption(option);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
    }
}
