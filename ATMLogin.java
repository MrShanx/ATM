/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  atm_pack.Bank
 *  atm_pack.BankATM
 *  atm_pack.Database
 */
package atm_pack;

import atm_pack.ATMInterface;
import atm_pack.Account;
import atm_pack.Bank;
import atm_pack.BankATM;
import atm_pack.Database;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ATMLogin
extends JFrame {
    private JTextArea user_txt;
    private JPasswordField pass_txt;
    private JButton log_in_butt;
    private Account tempAcc;
    private final Bank bk;
    private BankATM bATM;
    private ATMInterface atm_obj;
    private final Database db_obj;
    private final int WIDTH = 300;
    private final int HEIGHT = 450;

    public ATMLogin(Bank bk_obj, Database db_obj) {
        super("Log in");
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setSize(300, 450);
        this.bk = bk_obj;
        this.db_obj = db_obj;
        this.place_contents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void place_contents() {
        JPanel main_pan = new JPanel();
        main_pan.setLayout(new GridLayout(2, 1));
        ImageIcon img_rbc = new ImageIcon(this.getClass().getResource("../images/hsbc1.png"));
        JLabel img_lbl = new JLabel(img_rbc);
        main_pan.add(img_lbl);
        JPanel sec_main_pan = new JPanel(new GridLayout(3, 1));
        JPanel user_pan = new JPanel(new FlowLayout());
        user_pan.add(new JLabel("Client number:"));
        this.user_txt = new JTextArea(1, 15);
        this.user_txt.setBorder(BorderFactory.createLoweredBevelBorder());
        user_pan.add(this.user_txt);
        JPanel pass_pan = new JPanel();
        pass_pan.add(new JLabel("      Password:"));
        this.pass_txt = new JPasswordField(15);
        this.pass_txt.setBorder(BorderFactory.createLoweredBevelBorder());
        pass_pan.add(this.pass_txt);
        JPanel butt_pan = new JPanel();
        this.log_in_butt = new JButton("Log in");
        butt_pan.add(this.log_in_butt);
        this.add(main_pan);
        sec_main_pan.add(user_pan);
        sec_main_pan.add(pass_pan);
        sec_main_pan.add(butt_pan);
        main_pan.add(sec_main_pan);
        this.log_in_butt.addActionListener(new AtmInterfaceViewListener());
        this.log_in_butt.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent evt) {
                ATMLogin.this.log_in_buttKeyPressed(evt);
            }
        });
        this.user_txt.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent evt) {
                ATMLogin.this.user_textKeyPressed(evt);
            }
        });
        this.pass_txt.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent evt) {
                ATMLogin.this.pass_txtKeyPressed(evt);
            }
        });
    }

    public void user_textKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 9) {
            System.out.println(e.getModifiers());
            if (e.getModifiers() > 0) {
                this.user_txt.transferFocusBackward();
            } else {
                this.user_txt.transferFocus();
            }
            e.consume();
        } else if (e.getKeyCode() == 10) {
            this.log_in_butt.doClick();
            e.consume();
        }
    }

    public void pass_txtKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 9) {
            System.out.println(e.getModifiers());
            if (e.getModifiers() > 0) {
                this.pass_txt.transferFocusBackward();
            } else {
                this.pass_txt.transferFocus();
            }
            e.consume();
        } else if (e.getKeyCode() == 10) {
            this.log_in_butt.doClick();
            e.consume();
        } else {
            this.pass_txt.setEchoChar('*');
        }
    }

    public void log_in_buttKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.log_in_butt.doClick();
            e.consume();
        }
    }

    private String getClientName(String user, String pass) {
        this.tempAcc = this.bk.getAccount(user, pass);
        return this.tempAcc.getClientName();
    }

    private boolean isAClient(String user, String pass) {
        this.tempAcc = this.bk.getAccount(user, pass);
        if (this.tempAcc == null) {
            return false;
        }
        if (this.bk.isClient(this.tempAcc)) {
            return true;
        }
        return false;
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(null, "**Please enter a valid username and/or password**", "Inane error", 0);
    }

    private void showValidDialog(String username) {
        JOptionPane.showMessageDialog(null, "<html><font color='green'>**Welcome " + username + "!**</font></html>", "Logging in", -1);
        this.bATM = new BankATM(this.tempAcc, this.bk);
        this.atm_obj = new ATMInterface(this.bATM, this.db_obj);
        this.atm_obj.setVisible(true);
        this.setVisible(false);
    }

    public Account getTempAcc() {
        return this.tempAcc;
    }

    public ATMInterface getATMInterObj() {
        return this.atm_obj;
    }

    public BankATM getbATM() {
        return this.bATM;
    }

    private class AtmInterfaceViewListener
    implements ActionListener {
        private AtmInterfaceViewListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Log in".equals(e.getActionCommand())) {
                String username = ATMLogin.this.user_txt.getText().trim();
                String password = ATMLogin.this.pass_txt.getText().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    ATMLogin.this.showErrorDialog();
                    return;
                }
                if (ATMLogin.this.isAClient(username, password)) {
                    ATMLogin.this.showValidDialog(ATMLogin.this.getClientName(username, password));
                } else {
                    ATMLogin.this.showErrorDialog();
                }
            }
        }
    }

}