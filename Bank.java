/*
 * Decompiled with CFR 0_123.
 */
package atm_pack;

import atm_pack.Account;
import java.util.ArrayList;

public class Bank {
    ArrayList<Account> accounts = new ArrayList(10);

    public Bank(ArrayList<Account> accs) {
        for (int i = 0; i < accs.size(); ++i) {
            this.accounts.add(accs.get(i));
        }
    }

    public boolean isClient(Account acc) {
        for (int i = 0; i < this.accounts.size(); ++i) {
            if (!this.accounts.get(i).getAccountNum().equalsIgnoreCase(acc.getAccountNum()) || !this.accounts.get(i).getPassword().equalsIgnoreCase(acc.getPassword())) continue;
            return true;
        }
        return false;
    }

    public String toString() {
        String list = "";
        for (int i = 0; i < this.accounts.size(); ++i) {
            list = list + this.accounts.get(i) + "\n";
        }
        return list;
    }

    public Account getAccount(String login_name, String login_pass) {
        String client_name = null;
        if (this.isClient(new Account(login_name, login_pass, client_name))) {
            for (int i = 0; i < this.accounts.size(); ++i) {
                if (!this.accounts.get(i).getAccountNum().equalsIgnoreCase(login_name) || !this.accounts.get(i).getPassword().equalsIgnoreCase(login_pass)) continue;
                return this.accounts.get(i);
            }
        }
        return null;
    }

    public Account getAccount(String login_name, String login_pass, String client_name) {
        if (this.isClient(new Account(login_name, login_pass, client_name))) {
            for (int i = 0; i < this.accounts.size(); ++i) {
                if (!this.accounts.get(i).getAccountNum().equalsIgnoreCase(login_name) || !this.accounts.get(i).getPassword().equalsIgnoreCase(login_pass) || !this.accounts.get(i).getClientName().equalsIgnoreCase(client_name)) continue;
                return this.accounts.get(i);
            }
        }
        return null;
    }
}