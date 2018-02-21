package atm_pack;

import java.util.Objects;

public class Account {
    String login_accountNum;
    String login_password;
    double balance;
    String name;

    public Account(String clientLog, String clientPassword, String client_name) {
        this.login_accountNum = clientLog;
        this.login_password = Objects.requireNonNull(clientPassword);
        this.name = client_name;
    }

    public Account(String clientLog, String clientPassword, String client_name, double balance) {
        this.login_accountNum = clientLog;
        this.login_password = Objects.requireNonNull(clientPassword);
        this.balance = balance;
        this.name = client_name;
    }

    public void setAccountNum(String name) {
        this.login_accountNum = name;
    }

    public String getAccountNum() {
        return this.login_accountNum;
    }

    public void setNewPassword(String pass) {
        this.login_password = Objects.requireNonNull(pass);
    }

    public String getPassword() {
        return this.login_password;
    }

    public void setNewBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setClientName(String client_name) {
        this.name = client_name;
    }

    public String getClientName() {
        return this.name;
    }

    public String toString() {
        return String.format("Account name: %s \n     Balance: $%.2f", this.login_accountNum, this.balance);
    }
}