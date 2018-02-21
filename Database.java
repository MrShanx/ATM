/*
 * Decompiled with CFR 0_123.
 */
package atm_pack;

import atm_pack.Account;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private static final String driverURL = "org.apache.derby.jdbc.ClientDriver";
    private static final String dbURL = "jdbc:derby://localhost:1527/Client Accounts;create=true;user=dondon;password=dondon";
    private static String tableName;
    private static String balanceColumn;
    private static String accountNumColumn;
    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement preparedStatement;
    private static ArrayList<Account> accs;

    public Database() {
        tableName = "accounts";
        balanceColumn = "balance";
        accountNumColumn = "accountnumber";
        conn = null;
        stmt = null;
        accs = new ArrayList(999);
    }

    public void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Client Accounts;create=true;user=dondon;password=dondon");
        }
        catch (Exception except) {
            except.printStackTrace();
            String ex = except.toString();
            System.out.println("Failed connection: " + ex);
        }
    }

    public void selectBankAccounts() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; ++i) {
                String column_name = rsmd.getColumnLabel(i);
                if (column_name.equalsIgnoreCase("password")) continue;
                System.out.print(column_name + "\t");
            }
            System.out.println("\n-------------------------------------------------");
            while (results.next()) {
                String account_num = results.getString("accountnumber");
                String client_name = results.getString("clientname");
                double balance = results.getDouble("balance");
                System.out.println(account_num + "\t\t" + client_name + "\t\t" + balance);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public void shutdownConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
                conn.close();
            }
        }
        catch (SQLException sqlExcept) {
            System.out.println("Shutdown Exception: " + sqlExcept.toString());
        }
    }

    public ArrayList<Account> initializeAccounts() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            while (results.next()) {
                String account_num = results.getString("accountnumber");
                String client_pass = results.getString("password");
                String client_name = results.getString("clientname");
                double balance = results.getDouble("balance");
                this.initialize(account_num, client_pass, client_name, balance);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return accs;
    }

    private void initialize(String account_num, String client_pass, String client_name, double balance) {
        Account tempAccount = new Account(account_num, client_pass, client_name, balance);
        accs.add(tempAccount);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateBalance(Account acc) throws SQLException {
        String account_num = acc.getAccountNum();
        double balance = acc.getBalance();
        try {
            String updateSQLString = "UPDATE " + tableName + " SET " + balanceColumn + " = ? WHERE " + accountNumColumn + " = ?";
            preparedStatement = conn.prepareStatement(updateSQLString);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, account_num);
            preparedStatement.executeUpdate();
            System.out.println("\nAccount #: " + acc.getAccountNum() + " updated.\n");
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
}