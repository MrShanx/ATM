/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  atm_pack.Bank
 *  atm_pack.BankATM
 */
package atm_pack;

import atm_pack.Account;
import atm_pack.Bank;
import atm_pack.BankATM;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AtmMainCommandLineOutput {
    static Scanner input = new Scanner(System.in);
    static BankATM bAtm;

    public static void initializeATM(Bank bk) {
        Account tempAcc;
        System.out.println("Welcome to ET Bank!");
        System.out.println(bk.toString());
        do {
            tempAcc = null;
            tempAcc = AtmMainCommandLineOutput.logIn(bk);
            if (tempAcc != null) {
                bAtm = new BankATM(tempAcc, bk);
                continue;
            }
            System.out.println("Log in Failed!");
            AtmMainCommandLineOutput.exitPrompt(bAtm);
        } while (tempAcc == null);
        int choice = 0;
        do {
            if ((choice = AtmMainCommandLineOutput.showMenu()) == 1) {
                AtmMainCommandLineOutput.checkBalance(bAtm);
                continue;
            }
            if (choice == 2) {
                AtmMainCommandLineOutput.deposit();
                continue;
            }
            if (choice == 3) {
                AtmMainCommandLineOutput.withdraw();
                continue;
            }
            bAtm.exitATM();
        } while (choice != 4);
    }

    public static Account logIn(Bank bk) {
        String name = null;
        String pass = null;
        System.out.print("Log in name: ");
        name = input.nextLine();
        System.out.println(name);
        System.out.println();
        System.out.print("Log in password: ");
        pass = input.nextLine();
        Account tempAcc = bk.getAccount(name, pass);
        if (tempAcc != null) {
            System.out.println("Welcome " + tempAcc.getAccountNum() + "!");
        }
        return tempAcc;
    }

    public static void exitPrompt(BankATM bAtm) {
        System.out.print("Exit?(Yes/No)\n");
        String ans = input.nextLine();
        if (ans.equalsIgnoreCase("Yes")) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else if (ans.equalsIgnoreCase("No")) {
            return;
        }
    }

    public static int showMenu() {
        System.out.println("\nEnter (1) Check Balance");
        System.out.println("Enter (2) Deposit");
        System.out.println("Enter (3) Withdraw");
        System.out.println("Enter (4) Exit");
        int answer = 0;
        do {
            System.out.print("Choice: ");
        } while ((answer = input.nextInt()) < 1 || answer > 4);
        System.out.println();
        return answer;
    }

    public static void deposit() {
        System.out.print("Enter amount: ");
        double cash = input.nextDouble();
        bAtm.depositATM(cash);
        AtmMainCommandLineOutput.checkBalance(bAtm);
    }

    public static void withdraw() {
        System.out.print("Enter amount: ");
        int cash = input.nextInt();
        bAtm.withdrawATM(cash);
        AtmMainCommandLineOutput.checkBalance(bAtm);
    }

    public static void checkBalance(BankATM bat) {
        System.out.println("Current Balance: $" + bat.getBalance());
    }

    public static void main(String[] args) {
        Account firstClient = new Account("0001", "1111", "Emmanuel Talan", 5.0);
        ArrayList<Account> accs = new ArrayList<Account>(999);
        accs.add(firstClient);
        accs.add(new Account("0002", "2222", "John Doe", 10.0));
        accs.add(new Account("0003", "3333", "Jane Doe", 20.0));
        Bank bank = new Bank(accs);
        AtmMainCommandLineOutput.initializeATM(bank);
    }
}