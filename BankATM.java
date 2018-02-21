/*
 * Decompiled with CFR 0_123.
 */
package atm_pack;

import atm_pack.Account;
import atm_pack.Bank;
import javax.swing.JOptionPane;

public class BankATM {
    double balance = 0.0;
    private Account currAcct = null;
    private Bank currBank = null;

    public BankATM(Account acc, Bank bak) {
        this.currAcct = acc;
        this.currBank = bak;
        this.balance = this.currAcct != null ? this.currAcct.getBalance() : 0.0;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean withdrawATM(int withdrawal) {
        if (this.balance < (double)withdrawal || withdrawal < 0) {
            JOptionPane.showMessageDialog(null, "**Insufficient funds**", "Error", 0);
            return false;
        }
        this.balance -= (double)withdrawal;
        this.currAcct.setNewBalance(this.balance);
        return true;
    }

    public boolean depositATM(double deposit) {
        if (deposit <= 0.0) {
            JOptionPane.showMessageDialog(null, "**Invalid Amount**", "Error", 0);
            return false;
        }
        this.balance += deposit;
        this.currAcct.setNewBalance(this.balance);
        return true;
    }

    public Bank getBank() {
        return this.currBank;
    }

    public Account getAcc() {
        return this.currAcct;
    }

    public void exitATM() {
        System.exit(0);
    }
}