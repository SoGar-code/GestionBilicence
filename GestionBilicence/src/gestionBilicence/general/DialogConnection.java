package gestionBilicence.general;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * 
 * @author Trivy
 * Dialog box to parametrize DB connection 
 * Select among predefined elements: 
 * _ String host (localhost)
 * _ String dataBase (testbilicence, eventually basebilicence) 
 * Output:
 * _ String user
 * _ String passwd
 */

public class DialogConnection extends JDialog{
	private String user, passwd, host, dataBase;
	
	public DialogConnection(){
		super();
		this.setTitle("Gestion Bilicence -- connection parameters");
		this.setModal(true);
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		
		JPanel panChoix = new JPanel();
		JPanel panControl = new JPanel();
		
		// Chosing host:
		JLabel hostLabel = new JLabel("Host:");
		JComboBox<String> hostCombo = new JComboBox<String>(new String[]{"localhost:5432"});
		
		// Chosing databases:
		JLabel dbLabel = new JLabel("Database:");
		JComboBox<String> dbCombo = new JComboBox<String>(new String[]{"testbilicence","mysql"});
		
		// User:
		JLabel userLabel = new JLabel("User:");
		JTextField userJtf = new JTextField("postgres");
		
		// Password:
		JLabel passwdLabel = new JLabel("Password:");
		JPasswordField passwdJtf = new JPasswordField();
		
		panChoix.setLayout(new GridLayout(4,2));
		panChoix.add(hostLabel);
		panChoix.add(hostCombo);
		panChoix.add(dbLabel);
		panChoix.add(dbCombo);
		panChoix.add(userLabel);
		panChoix.add(userJtf);
		panChoix.add(passwdLabel);
		panChoix.add(passwdJtf);
		
	    JButton cancelBouton = new JButton("Cancel");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	// ends the dialog and stops the program:
	    	System.exit(ABORT);
	      }      
	    });
	    
	    JButton okBouton = new JButton("Ok");
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0){
	    	  // récupération des infos :
	    	  user = userJtf.getText();
	    	  passwd = passwdJtf.getText();
	    	  host = (String)hostCombo.getSelectedItem();
	    	  dataBase = (String)dbCombo.getSelectedItem();
	    		    	
	    	  // ends dialog by making the box invisible
	    	  setVisible(false);
	      }
	    });
	    
	    panControl.add(cancelBouton);
	    panControl.add(okBouton);
	    
	    this.getContentPane().add(panChoix, BorderLayout.CENTER);
	    this.getContentPane().add(panControl, BorderLayout.SOUTH);
	}
	
	public String[] showDialogConnection(){
		this.setVisible(true);
		String[] infoConn = new String[] {this.user, this.passwd, this.host, this.dataBase};
		return infoConn;
	}

}
