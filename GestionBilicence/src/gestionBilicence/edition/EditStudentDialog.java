package gestionBilicence.edition;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Trivy
 * A class to update the additional elements linked to Students
 */

public class EditStudentDialog extends JDialog {
	boolean hasEmail;
	String email;
	boolean hasStudNum;
	int studNum;

	public EditStudentDialog(){
		super();
		this.setTitle("Student — edition dialog");
		this.setModal(true);
		this.setSize(400,150);
		this.setLocationRelativeTo(null);
		
		JPanel selectionPan = new JPanel();
		JPanel controlPan = new JPanel();
		
		// Definition of selection panel
		int height = 15;
		int labelWidth = 100;
		
		JLabel studNumLabel = new JLabel("APOGEE number:");
		studNumLabel.setPreferredSize( new Dimension(labelWidth,height));
		JCheckBox studNumCheckBox = new JCheckBox();
		studNumCheckBox.setPreferredSize( new Dimension(20,height));
		JTextField studNumJtf = new JTextField("");
		studNumJtf.setPreferredSize(new Dimension(90,height));
		
		JPanel lineOnePan = new JPanel();
		lineOnePan.setLayout(new BoxLayout(lineOnePan,BoxLayout.LINE_AXIS));
		lineOnePan.add(studNumLabel);
		lineOnePan.add(studNumCheckBox);
		lineOnePan.add(studNumJtf);
		
		
		JLabel emailLabel = new JLabel("E-mail:");
		emailLabel.setPreferredSize( new Dimension(labelWidth,height));
		JCheckBox emailCheckBox = new JCheckBox();
		emailCheckBox.setPreferredSize( new Dimension(20,height));
		JTextField emailJtf = new JTextField("");
		emailJtf.setPreferredSize(new Dimension(90,height));
		
		JPanel lineTwoPan = new JPanel();
		lineTwoPan.setLayout(new BoxLayout(lineTwoPan,BoxLayout.LINE_AXIS));
		lineTwoPan.add(emailLabel);
		lineTwoPan.add(emailCheckBox);
		lineTwoPan.add(emailJtf);
		
		
		JLabel apbNumLabel = new JLabel("APB number:");
		apbNumLabel.setPreferredSize( new Dimension(labelWidth,height));
		JCheckBox apbNumCheckBox = new JCheckBox();
		apbNumCheckBox.setPreferredSize( new Dimension(20,height));
		JTextField apbNumJtf = new JTextField("");
		apbNumJtf.setPreferredSize(new Dimension(90,height));
		
		JPanel lineThreePan = new JPanel();
		lineThreePan.setLayout(new BoxLayout(lineThreePan,BoxLayout.LINE_AXIS));
		lineThreePan.add(apbNumLabel);
		lineThreePan.add(apbNumCheckBox);
		lineThreePan.add(apbNumJtf);
		
		// final assembly selectionPan
		selectionPan.setLayout(new BoxLayout(selectionPan,BoxLayout.PAGE_AXIS));
		selectionPan.add(lineOnePan);
		selectionPan.add(lineTwoPan);
		selectionPan.add(lineThreePan);
		
		// Definition of control panel
	    JButton cancelBouton = new JButton("Cancel");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	// ends the dialog
	    	setVisible(false);
	      }      
	    });
		
	    JButton okBouton = new JButton("Ok");
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0){
	    	  // recover elements:
	    	  /*
	    	  user = userJtf.getText();
	    	  passwd = passwdJtf.getText();
	    	  host = (String)hostCombo.getSelectedItem();
	    	  dataBase = (String)dbCombo.getSelectedItem();
	    	  */
	    		    	
	    	  // ends dialog by making the box invisible
	    	  setVisible(false);
	      }
	    });
	    
	    controlPan.add(cancelBouton);
	    controlPan.add(okBouton);
	    
	    this.getContentPane().add(selectionPan, BorderLayout.CENTER);
	    this.getContentPane().add(controlPan, BorderLayout.SOUTH);
	}
	
	public void showEditStudentDialog(){
		this.setVisible(true);
		//String[] infoConn = new String[] {this.user, this.passwd, this.host, this.dataBase};
		//return null;
	}
}
