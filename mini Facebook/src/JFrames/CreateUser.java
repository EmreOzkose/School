package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Main.Main;
import Main.User;
import Main.UserCollection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateUser {

	private JFrame frame;
	private JTextField txtUserName;
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtRePassword;
	private JTextField txtBirthday;
	private JTextField txtSchool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 430);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x, y);
		frame.getContentPane().setLayout(null);
		
		JLabel appIcon = new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/purplefacebook.png")));
		appIcon.setText("");
		appIcon.setBounds(84, 19, 309, 99);
		frame.getContentPane().add(appIcon);
		
		JLabel label = new JLabel("User Name");
		label.setBounds(57, 118, 202, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(57, 147, 202, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(57, 174, 202, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Password (re-type)");
		label_3.setBounds(57, 201, 202, 15);
		frame.getContentPane().add(label_3);
		
		txtUserName = new JTextField();
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtUserName.setForeground(fg);
				if(txtUserName.getText().equals("User name"))
					txtUserName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtUserName.setForeground(fg);
				if (txtUserName.getText().equals(""))
					txtUserName.setText("User name");
			}
		});
		txtUserName.setForeground(SystemColor.windowBorder);
		txtUserName.setText("User name");
		txtUserName.setColumns(10);
		txtUserName.setBounds(265, 116, 160, 19);
		frame.getContentPane().add(txtUserName);
		
		txtName = new JTextField();
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtName.setForeground(fg);
				if(txtName.getText().equals("Name"))
					txtName.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtName.setForeground(fg);
				if (txtName.getText().equals(""))
					txtName.setText("Name");
			}
		});
		txtName.setForeground(SystemColor.windowBorder);
		txtName.setText("Name");
		txtName.setColumns(10);
		txtName.setBounds(265, 145, 160, 19);
		frame.getContentPane().add(txtName);
		
		txtPassword = new JTextField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtPassword.setForeground(fg);
				if(txtPassword.getText().equals("Password"))
					txtPassword.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtPassword.setForeground(fg);
				if (txtPassword.getText().equals(""))
					txtPassword.setText("Password");
			}
		});
		txtPassword.setForeground(SystemColor.windowBorder);
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(265, 172, 160, 19);
		frame.getContentPane().add(txtPassword);
		
		txtRePassword = new JTextField();
		txtRePassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtRePassword.setForeground(fg);
				if(txtRePassword.getText().equals("Re-password"))
					txtRePassword.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtRePassword.setForeground(fg);
				if (txtRePassword.getText().equals(""))
					txtRePassword.setText("RePassword");
			}
		});
		txtRePassword.setForeground(SystemColor.windowBorder);
		txtRePassword.setText("Re-password");
		txtRePassword.setColumns(10);
		txtRePassword.setBounds(265, 199, 160, 19);
		frame.getContentPane().add(txtRePassword);
		
		JLabel lblRelationship = new JLabel("Relationship");
		lblRelationship.setBounds(57, 235, 202, 15);
		frame.getContentPane().add(lblRelationship);
		
		JComboBox<String> cbRelationship = new JComboBox<String>();
		cbRelationship.setModel(new DefaultComboBoxModel<String>(new String[] {"In relationship", "Divorced", "Complicated", "Single"}));
		cbRelationship.setBounds(265, 230, 160, 24);
		frame.getContentPane().add(cbRelationship);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtUserName.getText().equals("")){
					if (!txtName.getText().equals("")){
						if(!txtPassword.getText().equals("")){
							if(txtPassword.getText().equals(txtRePassword.getText())){
								try{
									
									DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
									@SuppressWarnings("unused")	//for taking parse exception if date format is wrong 
									Date date = df.parse(txtBirthday.getText());
									
									UserCollection.getList_users().add(new User(UserCollection.getGivingUserID(),txtName.getText(),txtUserName.getText(),txtPassword.getText(),txtBirthday.getText(),txtSchool.getText(), cbRelationship.getSelectedItem().toString()));
									UserCollection.setGivingUserID();
									JOptionPane.showMessageDialog(null, txtName.getText() + " has been successfully added.");
									
									Main.getModel().addElement(txtUserName.getText());
									Main.getUserListGUI().setModel(Main.getModel());
									
									txtUserName.setText("");
									txtName.setText("");
									txtPassword.setText("");
									txtRePassword.setText("");
									txtBirthday.setText("");
									txtSchool.setText("");
									cbRelationship.setSelectedItem(null);
								}catch (ParseException e2) {
									JOptionPane.showMessageDialog(null, "Date Format is wrong! Please check it");
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "Passwords are not same!");
						}
						else
							JOptionPane.showMessageDialog(null, "Password cannot be empty");
					}
					else
						JOptionPane.showMessageDialog(null, "Name cannot be empty");
				}
				else
					JOptionPane.showMessageDialog(null, "User name cannot be empty!");
			}
		});
		btnSave.setBounds(265, 340, 160, 25);
		frame.getContentPane().add(btnSave);
		
		JLabel lblAppCon = new JLabel("appIcon");
		lblAppCon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppCon.setIcon(new ImageIcon(CreateUser.class.getResource("/ImageResources/Linkedin.png")));
		lblAppCon.setBounds(-78, 29, 744, 75);
		frame.getContentPane().add(lblAppCon);
		
		JLabel lblBirthday = new JLabel("Birthday (ex:01/01/1997)");
		lblBirthday.setBounds(57, 266, 202, 15);
		frame.getContentPane().add(lblBirthday);
		
		txtBirthday = new JTextField();
		txtBirthday.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtBirthday.setForeground(fg);
				if(txtBirthday.getText().equals("Birthday"))
					txtBirthday.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtBirthday.setForeground(fg);
				if (txtBirthday.getText().equals(""))
					txtBirthday.setText("Birthday");
			}
		});
		txtBirthday.setForeground(SystemColor.windowBorder);
		txtBirthday.setText("ex:01/01/1997");
		txtBirthday.setBounds(265, 264, 160, 19);
		frame.getContentPane().add(txtBirthday);
		txtBirthday.setColumns(10);
		
		JLabel lblSchool = new JLabel("School");
		lblSchool.setBounds(57, 295, 202, 15);
		frame.getContentPane().add(lblSchool);
		
		txtSchool = new JTextField();
		txtSchool.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtSchool.setForeground(fg);
				if(txtSchool.getText().equals("School"))
					txtSchool.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtSchool.setForeground(fg);
				if (txtSchool.getText().equals(""))
					txtSchool.setText("School");
			}
		});
		txtSchool.setForeground(SystemColor.windowBorder);
		txtSchool.setText("School");
		txtSchool.setBounds(265, 293, 160, 19);
		frame.getContentPane().add(txtSchool);
		txtSchool.setColumns(10);
		
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		lblCancel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblCancel.setForeground(Color.BLUE);
		lblCancel.setBounds(323, 377, 70, 15);
		frame.getContentPane().add(lblCancel);
		
		background();
	}
	
	public void background(){
		JLabel lblBackground = new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/background2.jpg")));
		lblBackground.setBounds(0, 0, 851, 710);
		frame.getContentPane().add(lblBackground);
	}
	
}
