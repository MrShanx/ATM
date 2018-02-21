# ATM
An Automated Teller Machine in GUI application form. <br/>
<i>Java Swing Technology: <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          JFrame, JPanel, JLabel, JButton, JDialog, Embedded Java Derby Database, SQL </i> <br/> <br/>
          
A desktop app that mimics an ATM. <br/>
User can:<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  1) Check Balance <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  2) Withdraw <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  3) Deposit <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  4) Exit App <br/> <br/>
  
The application uses an embedded database <i>(Java Derby)</i> to store and retrieve client information to and from the database.<br/>
Info retrieved and updated includes: <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  1) Client account number <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  2) Client password <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  3) Client full name <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  4) Current Balance <br/> <br/>
  
Two implementations: <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
  1) AtmMainGUIOutput.java - Main class for GUI implementation <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  2) AtmMainCommandLineOutput.java - Main class for commandline implementation (not implemented with database)
