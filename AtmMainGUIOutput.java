/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  atm_pack.Bank
 *  atm_pack.Database
 */
package atm_pack;

import atm_pack.ATMLogin;
import atm_pack.Bank;
import atm_pack.Database;
import java.util.ArrayList;

public class AtmMainGUIOutput {
    private String account_num = null;
    private String account_pass = null;
    private String client_name = null;
    private double client_balance = 0.0;

    public static void main(String[] args) {
        Database db_ins = new Database();
        db_ins.createConnection();
        ArrayList accs = db_ins.initializeAccounts();
        db_ins.selectBankAccounts();
        Bank bank = new Bank(accs);
        ATMLogin atmLogin = new ATMLogin(bank, db_ins);
        db_ins.shutdownConnection();
    }
}