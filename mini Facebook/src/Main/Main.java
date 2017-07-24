package Main;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import JFrames.CreateUser;
import JFrames.LoginIn;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {
	private static JFrame frmFacebook;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private static ArrayList<ArrayList<String>> inputUsersTXT = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> inputCommandsTXT = new ArrayList<ArrayList<String>>();
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	private static JList<String> UserListGUI;
	private JLabel sup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 for (int i=0;i<args.length;i++){
				 Scanner scanner = new Scanner(new File(args[i]), "ISO-8859-1" );
				 while(scanner.hasNextLine()){
					 String line = scanner.nextLine();
					 Collection  <String> row = new ArrayList  <String> ();
					 
					 for (String url : line.split("\t")){		
						 row.add(url);
					 }
					
					 if (i == 0)
						 getInputUsersTXT().add((ArrayList<String>) row);
					 else if (i == 1)
						 getInputCommandsTXT().add((ArrayList<String>) row);
				 }
				 scanner.close();
			 }
		 }
		 catch (FileNotFoundException ex) {
			 System.out.println("No File Found!");
			 return;
		 }//try-catch
		
		UserCollection.addInitialUsers(inputUsersTXT);
		UserCollection.addInitialCommands1(inputCommandsTXT);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFacebook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFacebook = new JFrame();
		frmFacebook.setTitle("Facebook");
		frmFacebook.setBounds(100, 100, 718, 385);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frmFacebook.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frmFacebook.getHeight()) / 2;
		frmFacebook.setLocation(x,y);
		frmFacebook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacebook.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(378, 44, 129, 15);
		frmFacebook.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(378, 87, 70, 15);
		frmFacebook.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(500, 45, 114, 19);
		txtUsername.setForeground(SystemColor.windowBorder);
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtUsername.setForeground(fg);
				if(txtUsername.getText().equals("User name"))
					txtUsername.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtUsername.setForeground(fg);
				if (txtUsername.getText().equals(""))
					txtUsername.setText("User name");
			}
		});
		txtUsername.setText("User name");
		frmFacebook.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(500, 87, 114, 19);
		txtPassword.setForeground(SystemColor.windowBorder);
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
		txtPassword.setText("Password");
		frmFacebook.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLoginIn = new JButton("Login");
		btnLoginIn.setBounds(500, 118, 114, 25);
		btnLoginIn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				String userName = txtUsername.getText();
				String password = txtPassword.getText();
				
				if(UserCollection.inUser(userName)){
					UserCollection.setCurrentUser(userName);
					if (UserCollection.findUser(UserCollection.getCurrentUser()).getPassword().equals(password)){
						UserCollection.setCurrentUser(txtUsername.getText());
						new LoginIn();
						LoginIn.main(null);
						frmFacebook.setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid Password!");
				}
				else
					JOptionPane.showMessageDialog(null, "No such user!");
			}
		});
		frmFacebook.getContentPane().add(btnLoginIn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 200, 570, 64);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frmFacebook.getContentPane().add(scrollPane);
		
		UserListGUI = new JList<String>();
		UserListGUI.setVisibleRowCount(2);
		
		UserListGUI.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		UserListGUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(UserListGUI);
		
		for(User i : UserCollection.getList_users()){
			model.addElement(i.getUserName());
		}
		UserListGUI.setModel(model);
	
		UserListGUI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User clickedUser = UserCollection.findUser(UserListGUI.getSelectedValue());
				txtUsername.setText(clickedUser.getUserName());
				txtPassword.setText(clickedUser.getPassword());
				
			}
		});
		
		JButton btnSgnIn = new JButton("Sign in");
		btnSgnIn.setBounds(500, 290, 114, 25);
		btnSgnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CreateUser.main(null);
			}
		});
		frmFacebook.getContentPane().add(btnSgnIn);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFacebook.dispose();
			}
		});
		btnExit.setBounds(631, 0, 60, 25);
		frmFacebook.getContentPane().add(btnExit);
		
		JLabel lblUsers = new JLabel("Users : ");
		lblUsers.setBounds(45, 180, 70, 15);
		frmFacebook.getContentPane().add(lblUsers);
		
		JLabel appIcon = new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/purplefacebook.png")));
		appIcon.setText("");
		appIcon.setBounds(45, 44, 309, 99);
		frmFacebook.getContentPane().add(appIcon);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserListGUI.isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "Please select a user");
				}
				else{
					UserCollection.getList_users().remove(UserCollection.findUser(UserListGUI.getSelectedValue().toString()));
					
					model.removeAllElements();
					
					for(User i : UserCollection.getList_users()){
						model.addElement(i.getUserName());
					}
					UserListGUI.setModel(model);
					
				}
				
			}
		});
		btnRemoveUser.setBounds(45, 290, 130, 25);
		frmFacebook.getContentPane().add(btnRemoveUser);
		
		JLabel lblBackground = new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/background2.jpg")));
		lblBackground.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() >= 2){
					sup.setBounds(0, 0, frmFacebook.getWidth(), frmFacebook.getHeight());
				}
				
			}
		});
		lblBackground.setBounds(0, 0, frmFacebook.getWidth(), frmFacebook.getHeight());
		frmFacebook.getContentPane().add(lblBackground);
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frmFacebook.setJMenuBar(menuBar);
		
		JMenuItem mnıtmSystem = new JMenuItem("System");
		menuBar.add(mnıtmSystem);
		
		JLabel sup = new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/123.jpg")));
		sup.setBounds(100, 100, 0, 0);
		frmFacebook.getContentPane().add(sup);
		
	}
	
	//getter and setter methods
	public static ArrayList<ArrayList<String>> getInputUsersTXT() {
		return inputUsersTXT;
	}

	public void setInputUsersTXT(ArrayList<ArrayList<String>> inputUsersTXT) {
		Main.inputUsersTXT = inputUsersTXT;
	}

	public static ArrayList<ArrayList<String>> getInputCommandsTXT() {
		return inputCommandsTXT;
	}

	public void setInputCommandsTXT(ArrayList<ArrayList<String>> inputCommandsTXT) {
		Main.inputCommandsTXT = inputCommandsTXT;
	}

	public static DefaultListModel<String> getModel() {
		return model;
	}

	public static void setModel(DefaultListModel<String> model) {
		Main.model = model;
	}

	public static JList<String> getUserListGUI() {
		return UserListGUI;
	}

	public static void setUserListGUI(JList<String> userListGU) {
		UserListGUI = userListGU;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public static JFrame getFrmFacebook() {
		return frmFacebook;
	}

	public void setFrmFacebook(JFrame frmFacebook) {
		Main.frmFacebook = frmFacebook;
	}
}
