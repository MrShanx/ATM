package atm_pack;

import atm_pack.ATMInterface;
import atm_pack.Account;
import atm_pack.BankATM;
import atm_pack.Database;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.net.URL;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ATMInterface
extends JFrame {
    JButton check_butt;
    JButton deposit_butt;
    JButton withdraw_butt;
    JButton exit_butt;
    BankATM bATM;
    Database db_object;
    private double old_balance;
    private final int WIDTH = 300;
    private final int HEIGHT = 450;

    public ATMInterface(BankATM batm, Database dbObj) {
        super("Menu");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setSize(300, 450);
        this.bATM = batm;
        this.db_object = dbObj;
        this.old_balance = batm.getBalance();
        this.place_contents();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    private void place_contents() {
        JPanel main_pan = new JPanel(new GridLayout(2, 1));
        JPanel lbl_pan = new JPanel();
        ImageIcon img_ico = new ImageIcon(this.getClass().getResource("../images/hsbc2.png"));
        JLabel img_lbl = new JLabel(img_ico);
        lbl_pan.add(img_lbl);
        JPanel menu_pan = new JPanel(new GridLayout(4, 1));
        this.check_butt = new JButton("check balance");
        JPanel check_pan = new JPanel();
        check_pan.add(this.check_butt);
        this.deposit_butt = new JButton("       deposit       ");
        JPanel deposit_pan = new JPanel();
        deposit_pan.add(this.deposit_butt);
        this.withdraw_butt = new JButton("      withdraw     ");
        JPanel withdraw_pan = new JPanel();
        withdraw_pan.add(this.withdraw_butt);
        this.exit_butt = new JButton("           exit           ");
        
	JPanel exit_pan = new JPanel();
        	exit_pan.add(this.exit_butt);
        
	menu_pan.add(check_pan);
        menu_pan.add(deposit_pan);
        menu_pan.add(withdraw_pan);
        menu_pan.add(exit_pan);
        
	main_pan.add(lbl_pan);
        main_pan.add(menu_pan);
        this.add(main_pan);

        check_butt.addActionListener(new ATMInterface.MyActionListener(this, null));
    	check_butt.addKeyListener(new ATMInterface.1(this));
    	
	withdraw_butt.addActionListener(new ATMInterface.MyActionListener(this, null));
    	withdraw_butt.addKeyListener(new ATMInterface.2(this));
    	
	deposit_butt.addActionListener(new ATMInterface.MyActionListener(this, null));
    	deposit_butt.addKeyListener(new ATMInterface.3(this));
    	
	exit_butt.addActionListener(new ATMInterface.MyActionListener(this, null));
    	exit_butt.addKeyListener(new ATMInterface.4(this));
}

    public void check_buttKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.check_butt.doClick();
            e.consume();
        }
    }

    public void withdraw_buttKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.withdraw_butt.doClick();
            e.consume();
        }
    }

    public void deposit_buttKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.deposit_butt.doClick();
            e.consume();
        }
    }

    public void exit_buttKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.exit_butt.doClick();
            e.consume();
        }
    }

    private void printBalance() {
        double bal = 0.0;
        bal = this.bATM.getBalance();
        JOptionPane.showMessageDialog(null, "<html><font color='grey'>**Current balance: $" + bal + "**</font></html>", "Information", 1);
        System.out.println("**printing balance**");
    }

    private void printNewBalanceDep() {
        double bal = 0.0;
        bal = this.bATM.getBalance();
        JOptionPane.showMessageDialog(null, "<html><font color='green'>**New balance: $" + bal + "**</font></html>", "Information", 1);
        System.out.println("**printing balance**");
    }

    private void printNewBalanceWith() {
        double bal = 0.0;
        bal = this.bATM.getBalance();
        JOptionPane.showMessageDialog(null, "<html><font color='red'>**New balance: $" + bal + "**</font></html>", "Information", 1);
        System.out.println("**printing balance**");
    }

    private void deposit() {
        String dep = null;
        int confirm = -1;
        try {
            dep = JOptionPane.showInputDialog(this, "Enter amount: ", 1);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "**Invalid Amount**", "Error", 0);
        }
        if (dep != null) {
            confirm = JOptionPane.showConfirmDialog(null, "Confirm deposit");
        }
        if (confirm == 0) {
            double amt = Double.parseDouble(dep);
            boolean result = this.bATM.depositATM(amt);
            if (result) {
                this.printNewBalanceDep();
                System.out.println("**money deposited**");
            } else {
                this.printBalance();
            }
        }
    }

    private void withdraw() {
        String with = null;
        int confirm = -1;
        try {
            with = JOptionPane.showInputDialog(this, "Enter amount: ", 1);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "**Invalid Amount**", "Error", 0);
        }
        if (with != null) {
            confirm = JOptionPane.showConfirmDialog(null, "Confirm withdrawal");
        }
        if (confirm == 0) {
            int amt = Integer.parseInt(with);
            boolean result = this.bATM.withdrawATM(amt);
            if (result) {
                this.printNewBalanceWith();
                System.out.println("**money withdrew**");
            } else {
                this.printBalance();
            }
        }
    }

    private void exit() throws SQLException {
        double new_balance = this.bATM.getAcc().getBalance();
        if (this.old_balance != new_balance) {
            this.db_object.updateBalance(this.bATM.getAcc());
        }
        this.db_object.selectBankAccounts();
        JOptionPane.showMessageDialog(null, "**Thank You**", "Exit", -1);
        System.out.println("**exiting machine**");
        System.exit(0);
    }
}