package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Main.Main;
import Main.Post;
import Main.User;
import Main.UserCollection;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class LoginIn {
	
	private static User displayedUser;
	private static Post selectedPost;
	
	private static DefaultListModel<String> listSearchmodel;
	private static DefaultListModel<String> modelFriend, modelBlocked;
	
	private JFrame frame;
	private JTextField txtSearchLine = new JTextField();
	private int txtSearchLineLength;
	
	public static JButton btnHome;
	private JPanel pMenu;
	private JList<String> listSearch2;
	private JList<String> friendList;
	private JScrollPane spFriend;
	
	private JPanel pFriend;
	private JRadioButton rdbtnNormal, rdbtnBlocked;
	private JButton btnRemoveSelectedUser;
	
	private JTextField txtEnterNewDate, txtEnterNewSchool, txtPostText, txtLatitude, txtLongtitude;
	
	private JInternalFrame fAddPost;
	private JTextField txtFileName, txtWidth, txtHeight, txtDuration;
	private JLabel lblFileName, lblHeight, lblDuration, lblWidth, lblCancel;
	private JButton btnAddPost;
	
	private JScrollPane spPostView, spPostViewFriend;
	
	private JLabel lblName, lblDateOfBirth, lblSchool;
	private JComboBox<String> cbRelationship;
	
	private JPanel pBigger, eachPostPanel, pBiggerFriend, eachPostPanelFriend;
	
	private JPanel pInformation;
	private JButton btnUpdate, btnSave;
	
	public DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JTabbedPane tpPosts;
	private JButton btnBlockPerson, btnAddFriend;
	
	private JTextField txtEnterNewName;
	private JScrollPane spSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginIn window = new LoginIn();
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
	public LoginIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setContentPane(new JLabel(new ImageIcon(Main.class.getResource("/ImageResources/background2.jpg"))));
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		frame.setBackground(null);
		frame.setBounds(10, 0, 851, 785);
		frame.setPreferredSize(new Dimension(851, 785));
		frame.getContentPane().setLayout(null);
		frame.pack();
		
		addPostFrame();
		
		displayUser(UserCollection.getCurrentUser());
		
		menuPanel();
		
		search();
		
		friendPanel();
		
		postToPanel(getDisplayedUser());
		tabbedPanel();
		
		btnBlockPerson = new JButton("Block this person");
		btnBlockPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCollection.findUser(UserCollection.getCurrentUser()).blockFriend(displayedUser.getUserName());
				JOptionPane.showMessageDialog(null, "You blocked " + displayedUser.getName());
				btnHome.doClick();
			}
		});
		btnBlockPerson.setVisible(false);
		btnBlockPerson.setBounds(680, 230, 160, 25);
		frame.getContentPane().add(btnBlockPerson);
		
		btnAddFriend = new JButton("Add Friend");
		btnAddFriend.setVisible(false);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					UserCollection.findUser(UserCollection.getCurrentUser()).addFriend(displayedUser.getUserName());
					JOptionPane.showMessageDialog(null, "You added " + displayedUser.getName() + " as a friend");
					
					btnAddFriend.setVisible(false);
			}
		});
		btnAddFriend.setBounds(565, 230, 110, 25);
		frame.getContentPane().add(btnAddFriend);
		
		DefaultListModel<String> modelll = new DefaultListModel<String>();
		
		for (User i : UserCollection.getList_users()){
			modelll.addElement(i.getName());
		}
		
	}
	
	public void postToPanel(User dUser){
		//Online user Post Views
		spPostView = new JScrollPane();
		spPostView.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		spPostView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPostView.setBounds(255, 260, 590, 460);
		
		pBigger = new JPanel();
		pBigger.setBounds(0, 0, (int)spPostView.getSize().getWidth(), (int)spPostView.getSize().getHeight());
		pBigger.setLayout(new BoxLayout(pBigger, BoxLayout.Y_AXIS));
			
		double x = spPostView.getLocation().getX()+5;
		double y = spPostView.getLocation().getY()+5;
		
		for(Post p : UserCollection.findUser(dUser.getUserName()).getListPosts()){
			eachPostPanel = new JPanel();
			eachPostPanel.setBounds((int)x, (int)y, 80, 60);
			eachPostPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 1, 0, Color.GRAY));
			eachPostPanel.setLayout(new BorderLayout(10, 3));
			eachPostPanel.setPreferredSize(new Dimension(560, 80));
			
			JTextArea taPostText = new JTextArea(p.getPostText());
			taPostText.setEditable(false);
			taPostText.setOpaque(false);
			taPostText.setLineWrap(true);
			taPostText.setPreferredSize(new Dimension(340, 50));
			eachPostPanel.add(taPostText, BorderLayout.CENTER);
			
			JLabel lblTaggedFriends = new JLabel("Tagged Users: " + viewTaggedUsers(p.getTaggedFriends())); eachPostPanel.add(lblTaggedFriends, BorderLayout.SOUTH);
			JButton btnTaggFriend = new JButton("Tag Friend"); btnTaggFriend.setPreferredSize(new Dimension(120, 50));eachPostPanel.add(btnTaggFriend,BorderLayout.EAST);
			btnTaggFriend.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					setSelectedPost(p);
					
					TagFriend tagFriendWindow = new TagFriend(getSelectedPost());
					tagFriendWindow.main(null);
				}
			});
			
			if(p.getClass().getSimpleName().equals("TextPost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/t.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			else if(p.getClass().getSimpleName().equals("ImagePost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/i.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			else if(p.getClass().getSimpleName().equals("VideoPost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/v.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			
			pBigger.add(eachPostPanel);
			
			y+=75;
		}
		spPostView.setViewportView(pBigger);
		
		
		//Friend Post view
		spPostViewFriend = new JScrollPane();
		spPostViewFriend.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		spPostViewFriend.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPostViewFriend.setBounds(255, 260, 585, 435);
		
		pBiggerFriend = new JPanel();
		
		pBiggerFriend.setBounds(0, 0, (int)spPostViewFriend.getSize().getWidth(), (int)spPostViewFriend.getSize().getHeight());
		pBiggerFriend.setLayout(new BoxLayout(pBiggerFriend, BoxLayout.Y_AXIS));
			
		double k = spPostViewFriend.getLocation().getX()+5;
		double l = spPostViewFriend.getLocation().getY()+5;
		
		
		JPanel eachPostPanelFriend;
		for (String friend : UserCollection.findUser(dUser.getUserName()).getListFriends()){
			for(Post p : UserCollection.findUser(friend).getListPosts()){
				eachPostPanelFriend = new JPanel();
				eachPostPanelFriend.setBounds((int)k, (int)l, (int)spPostViewFriend.getSize().getWidth()-10, 45);
				eachPostPanelFriend.setBorder(BorderFactory.createMatteBorder(0, 3, 1, 0, Color.GRAY));
				eachPostPanelFriend.setPreferredSize(new Dimension(560, 80));
				eachPostPanelFriend.setLayout(new BorderLayout(10, 5));
				
				JLabel lblPostID = new JLabel(UserCollection.findUser(friend).getName() + " has shared"); eachPostPanelFriend.add(lblPostID, BorderLayout.NORTH);
				JTextArea taPostText = new JTextArea(p.getPostText());
				taPostText.setEditable(false);
				taPostText.setOpaque(false);
				taPostText.setLineWrap(true);
				taPostText.setPreferredSize(new Dimension(340, 50));
				eachPostPanelFriend.add(taPostText, BorderLayout.CENTER);
				
				JLabel lblTaggedFriends = new JLabel("Tagged Users: " + viewTaggedUsers(p.getTaggedFriends())); eachPostPanelFriend.add(lblTaggedFriends, BorderLayout.SOUTH);
				
				if(p.getClass().getSimpleName().equals("TextPost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/t.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				else if(p.getClass().getSimpleName().equals("ImagePost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/i.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				else if(p.getClass().getSimpleName().equals("VideoPost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/v.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				pBiggerFriend.add(eachPostPanelFriend);
				
				l+=55;
			}
		}
		spPostViewFriend.setViewportView(pBiggerFriend);
		
	}	
	public void tabbedPanel(){
		
		tpPosts = new JTabbedPane(JTabbedPane.TOP);
		tpPosts.setBounds(254, 236, 590, 460);
		
		tpPosts.add("Posts", spPostView);
		tpPosts.add("Friends' Posts", spPostViewFriend);
		
		frame.getContentPane().add(tpPosts);
	}
	/**
	 * displaying user's screen 
	 * @param userName user name who is wanted to display
	 */
	public void displayUser(String userName){
		setDisplayedUser(UserCollection.findUser(userName));
		
		JPanel pInformation = new JPanel();
		pInformation.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		pInformation.setBounds(12, 260, 230, 230);
		frame.getContentPane().add(pInformation);
		pInformation.setLayout(null);
		
		lblName = new JLabel();
		lblName.setText(displayedUser.getName());
		lblName.setFont(new Font("Dialog", Font.ITALIC, 35));
		lblName.setBounds(253, 185, 585, 45);
		frame.getContentPane().add(lblName);
		
		txtEnterNewName = new JTextField(displayedUser.getName());
		txtEnterNewName.setFont(new Font("Dialog", Font.ITALIC, 35));
		txtEnterNewName.setVisible(false);
		txtEnterNewName.setBounds(253, 185, 585, 45);
		frame.getContentPane().add(txtEnterNewName);
		txtEnterNewName.setColumns(10);
		
		JLabel lblPersonIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/personicon8.png")));
		lblPersonIcon.setForeground(new Color(153, 0, 255));
		lblPersonIcon.setBounds(35, 64, 172, 156);
		frame.getContentPane().add(lblPersonIcon);
		
		JLabel lblStringDateOfBirth = new JLabel("Date of Birth ");
		lblStringDateOfBirth.setBounds(30, 30, 181, 15);
		pInformation.add(lblStringDateOfBirth);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		lblDateOfBirth = new JLabel();
		
		lblDateOfBirth.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(30, 60, 187, 15);
		lblDateOfBirth.setText(df.format(displayedUser.getDateOfBirth()));
		pInformation.add(lblDateOfBirth);
		
		JLabel lblStringSchool = new JLabel("School Graduated");
		lblStringSchool.setBounds(30, 90, 146, 15);
		pInformation.add(lblStringSchool);
		
		lblSchool = new JLabel();
		lblSchool.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSchool.setBounds(30, 120, 187, 15);
		lblSchool.setText(displayedUser.getSchoolInfo());
		pInformation.add(lblSchool);
		
		cbRelationship = new JComboBox<String>();
		cbRelationship.setModel(new DefaultComboBoxModel<String>(new String[] {"Single", "In relationship", "Divorced", "Complicated"}));
		cbRelationship.setBounds(30, 150, 187, 25);
		cbRelationship.setSelectedItem(displayedUser.getRelationshipStatu().substring(0, 1).toUpperCase().replace("İ", "I") + displayedUser.getRelationshipStatu().substring(1));
		
		pInformation.add(cbRelationship);
		
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtEnterNewDate.setVisible(true);
				txtEnterNewSchool.setVisible(true);
				txtEnterNewName.setVisible(true);
				
				lblDateOfBirth.setVisible(false);
				lblSchool.setVisible(false);
				lblName.setVisible(false);
				
				btnUpdate.setVisible(false);
				btnSave.setVisible(true);
				
				pInformation.repaint();
				
			}
		});
		btnUpdate.setBounds(60, 190, 117, 25);
		pInformation.add(btnUpdate);
		btnSave = new JButton("SAVE");
		btnSave.setVisible(false);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UserCollection.findUser(displayedUser.getUserName()).setDateOfBirth(df.parse(txtEnterNewDate.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				UserCollection.findUser(displayedUser.getUserName()).setSchoolInfo(txtEnterNewSchool.getText());
				UserCollection.findUser(displayedUser.getUserName()).setRelationshipStatu(cbRelationship.getSelectedItem().toString());
				UserCollection.findUser(displayedUser.getUserName()).setName(txtEnterNewName.getText());
				
				txtEnterNewDate.setVisible(false);
				txtEnterNewSchool.setVisible(false);
				txtEnterNewName.setVisible(false);
				
				lblDateOfBirth.setVisible(true);
				lblSchool.setVisible(true);
				lblName.setVisible(true);
				
				btnUpdate.setVisible(true);
				btnSave.setVisible(false);
				
				refreshInformationPanel();
			}
		});
		btnSave.setBounds(60, 190, 117, 25);
		pInformation.add(btnSave);
		
		txtEnterNewDate = new JTextField(df.format(UserCollection.findUser(displayedUser.getUserName()).getDateOfBirth()));
		txtEnterNewDate.setVisible(false);
		txtEnterNewDate.setBounds(30, 57, 114, 19);
		pInformation.add(txtEnterNewDate);
		txtEnterNewDate.setColumns(10);
		
		txtEnterNewSchool = new JTextField(UserCollection.findUser(displayedUser.getUserName()).getSchoolInfo());
		txtEnterNewSchool.setVisible(false);
		txtEnterNewSchool.setBounds(30, 117, 114, 19);
		pInformation.add(txtEnterNewSchool);
		txtEnterNewSchool.setColumns(10);
		
		
		
	}
	public void menuPanel(){
		spSearch = new JScrollPane();
		spSearch.setBounds(381, 56, 117, 85);
		frame.getContentPane().add(spSearch);
		spSearch.setVisible(false);
		spSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		listSearch2 = new JList<String>();
		spSearch.setViewportView(listSearch2);
		listSearch2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearch2.scrollRectToVisible(new Rectangle(225, 64, 99, 82));
		listSearch2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color fg2 = new Color(196,196,196);
				txtSearchLine.setForeground(fg2);
				txtSearchLine.setBounds(340, 3, 180, 26);
				txtSearchLine.setText("Search user");
				
				try{
					for (User u : UserCollection.getList_users()){
						System.out.println(listSearch2.getSelectedIndex());
						
							if(listSearch2.getSelectedValue().equals(u.getName()))
								setDisplayedUser(UserCollection.findUser(u.getUserName()));
					}
						
				}
				catch(NullPointerException e2){
					JOptionPane.showMessageDialog(null, "Please select a user!");
				}
				
				search();
				spSearch.setBounds(0,0,0,0);
				
				lblName.setText(displayedUser.getName());
				lblDateOfBirth.setText(df.format(displayedUser.getDateOfBirth()));
				lblSchool.setText(displayedUser.getSchoolInfo());
				cbRelationship.setSelectedItem(displayedUser.getRelationshipStatu().substring(0, 1).toUpperCase().replace("İ", "I") + displayedUser.getRelationshipStatu().substring(1));
			
				refreshPostPanel();
				
				btnUpdate.setVisible(false);
				btnSave.setVisible(false);
				cbRelationship.setEnabled(false);
				btnRemoveSelectedUser.setVisible(false);
				btnAddPost.setVisible(false);
				btnBlockPerson.setVisible(true);
				if (UserCollection.findUser(UserCollection.getCurrentUser()).getListFriends().contains(displayedUser.getUserName()))
					btnAddFriend.setVisible(false);
				else if (UserCollection.findUser(UserCollection.getCurrentUser()).equals(displayedUser.getUserName())){
					btnAddFriend.setVisible(false);
					btnBlockPerson.setVisible(false);
				}
				else
					btnAddFriend.setVisible(true);
				DefaultListModel<String> modelFree = new DefaultListModel<String>();
				for(String u : displayedUser.getListFriends()){
					modelFree.addElement(u);
				}
				friendList.setModel(modelFree);
				btnHome.setVisible(true);
				menuPanel();
				
				refreshFriendPanel();
				rdbtnNormal.doClick();
			}
		});
		pMenu = new JPanel();
		pMenu.setBackground(new Color(204, 51, 255));
		pMenu.setForeground(new Color(153, 0, 255));
		pMenu.setBounds(0, 0, 851, 45);
		frame.getContentPane().add(pMenu);
		pMenu.setLayout(null);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(12, 5, 74, 25);
		pMenu.add(btnHome);
		
		JButton btnLoginOut = new JButton("Log out");
		btnLoginOut.setBounds(751, 5, 88, 25);
		btnHome.setVisible(false);
		pMenu.add(btnLoginOut);
		btnLoginOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCollection.findUser(UserCollection.getCurrentUser()).setLastLoginDate(new Date());
				UserCollection.setCurrentUser(null);
				frame.dispose();
				
				Main.getFrmFacebook().setVisible(true);
				
				frame.setVisible(false);
			}
		});
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDisplayedUser(UserCollection.findUser(UserCollection.getCurrentUser()));
				btnHome.setVisible(false);
				lblName.setText(displayedUser.getName());
				lblDateOfBirth.setText(df.format(displayedUser.getDateOfBirth()));
				lblSchool.setText(displayedUser.getSchoolInfo());
				cbRelationship.setSelectedItem(displayedUser.getRelationshipStatu().substring(0, 1).toUpperCase().replace("İ", "I") + displayedUser.getRelationshipStatu().substring(1));
				
				btnAddPost.setVisible(true);
				btnAddFriend.setVisible(false);
				
				tpPosts.setVisible(false);
				search();
				btnRemoveSelectedUser.setVisible(true);
				btnUpdate.setVisible(true);
				btnBlockPerson.setVisible(false);
				cbRelationship.setVisible(true);
				postToPanel(displayedUser);
				tabbedPanel();
				
				refreshFriendPanel();
				rdbtnNormal.doClick();
				
			}
		});
		
		
	}
	/**
	 * internal frame which allows to add post
	 */
	public void addPostFrame(){
		
		fAddPost = new JInternalFrame("Add Post");
		fAddPost.setBounds(531, 50, 426, 180);
		frame.getContentPane().add(fAddPost);
		fAddPost.getContentPane().setLayout(null);
		
		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setBounds(12, 12, 70, 15);
		fAddPost.getContentPane().add(lblPostType);
		
		JComboBox<String> cbPostType = new JComboBox<String>();
		cbPostType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbPostType.getSelectedItem().toString().equals("TextPost")){
					lblFileName.setVisible(false);
					lblDuration.setVisible(false);
					lblWidth.setVisible(false);
					lblHeight.setVisible(false);
					
					txtFileName.setVisible(false);
					txtDuration.setVisible(false);
					txtWidth.setVisible(false);
					txtHeight.setVisible(false);
					
					fAddPost.setBounds(398, 44, 426, 200);
					btnAddPost.setBounds(273, 103, 117, 25);
					lblCancel.setBounds(307, 80, 70, 15);
				}
				else if(cbPostType.getSelectedItem().toString().equals("ImagePost")){
					lblFileName.setVisible(true);
					lblHeight.setVisible(true);
					lblWidth.setVisible(true);
					lblDuration.setVisible(false);
					
					txtFileName.setVisible(true);
					txtDuration.setVisible(false);
					txtWidth.setVisible(true);
					txtHeight.setVisible(true);
					
					fAddPost.setBounds(398, 44, 426, 260);
					btnAddPost.setBounds(273, 182, 117, 25);
					lblCancel.setBounds(307, 160, 70, 15);
				}
				else if(cbPostType.getSelectedItem().toString().equals("VideoPost")){
					lblFileName.setVisible(true);
					lblDuration.setVisible(true);
					lblHeight.setVisible(false);
					lblWidth.setVisible(false);
					
					txtFileName.setVisible(true);
					txtDuration.setVisible(true);
					txtWidth.setVisible(false);
					txtHeight.setVisible(false);
					
					fAddPost.setBounds(398, 44, 426, 260);
					btnAddPost.setBounds(273, 182, 117, 25);
					lblCancel.setBounds(307, 160, 70, 15);
				}
			}
		});
		cbPostType.setModel(new DefaultComboBoxModel<String>(new String[] {"TextPost", "ImagePost", "VideoPost"}));
		cbPostType.setBounds(112, 7, 292, 24);
		fAddPost.getContentPane().add(cbPostType);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(12, 51, 70, 15);
		fAddPost.getContentPane().add(lblText);
		
		txtPostText = new JTextField();
		txtPostText.setBounds(112, 49, 292, 19);
		fAddPost.getContentPane().add(txtPostText);
		txtPostText.setColumns(10);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(12, 86, 70, 15);
		fAddPost.getContentPane().add(lblLatitude);
		
		JLabel lblLongtitude = new JLabel("Longtitude:");
		lblLongtitude.setBounds(12, 111, 83, 15);
		fAddPost.getContentPane().add(lblLongtitude);
		
		txtLatitude = new JTextField();
		txtLatitude.setBounds(112, 80, 114, 19);
		fAddPost.getContentPane().add(txtLatitude);
		txtLatitude.setColumns(10);
		
		txtLongtitude = new JTextField();
		txtLongtitude.setBounds(112, 109, 114, 19);
		fAddPost.getContentPane().add(txtLongtitude);
		txtLongtitude.setColumns(10);
		
		btnAddPost = new JButton("Add Post");
		btnAddPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fAddPost.setVisible(false);
				if (cbPostType.getSelectedItem().toString().equals("TextPost")){
					displayedUser.addTextPost(txtPostText.getText(), txtLongtitude.getText(), txtLatitude.getText(), "");
				}
				else if(cbPostType.getSelectedItem().toString().equals("ImagePost")){
					displayedUser.addImagePost(txtPostText.getText(), txtLongtitude.getText(), txtLatitude.getText(), "",txtFileName.getText(), txtWidth.getText(), txtHeight.getText());
				}
				else if(cbPostType.getSelectedItem().toString().equals("VideoPost")){
					displayedUser.addVideoPost(txtPostText.getText(), txtLongtitude.getText(), txtLatitude.getText(), "",txtFileName.getText(), txtDuration.getText());
				}
				
				txtPostText.setText("");
				txtLatitude.setText("");
				txtLongtitude.setText("");
				txtFileName.setText("");
				txtDuration.setText("");
				txtHeight.setText("");
				txtWidth.setText("");
				
				refreshPostPanel();
				JOptionPane.showMessageDialog(null, "The post has been successfully added.");
				
			}
		});
		btnAddPost.setBounds(273, 103, 117, 25);
		fAddPost.getContentPane().add(btnAddPost);
		
		lblFileName = new JLabel("File name:");
		lblFileName.setBounds(12, 151, 96, 15);
		fAddPost.getContentPane().add(lblFileName);
		
		txtFileName = new JTextField();
		txtFileName.setBounds(112, 149, 114, 19);
		fAddPost.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setBounds(112, 180, 50, 19);
		fAddPost.getContentPane().add(txtWidth);
		txtWidth.setColumns(10);
		
		lblWidth = new JLabel("Width:");
		lblWidth.setBounds(12, 182, 70, 15);
		fAddPost.getContentPane().add(lblWidth);
		
		lblHeight = new JLabel("Height:");
		lblHeight.setBounds(12, 209, 70, 15);
		fAddPost.getContentPane().add(lblHeight);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		txtHeight.setBounds(112, 207, 50, 19);
		fAddPost.getContentPane().add(txtHeight);
		
		lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(12, 182, 70, 15);
		fAddPost.getContentPane().add(lblDuration);
		
		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		txtDuration.setBounds(112, 180, 50, 19);
		fAddPost.getContentPane().add(txtDuration);
		
		lblCancel = new JLabel("cancel");
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fAddPost.setVisible(false);
			}
		});
		lblCancel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblCancel.setForeground(Color.BLUE);
		lblCancel.setBounds(307, 80, 70, 15);
		fAddPost.getContentPane().add(lblCancel);
	}
	
	public void friendPanel(){
		JLabel lblFriends = new JLabel("Friends");
		lblFriends.setBounds(12, 515, 70, 15);
		frame.getContentPane().add(lblFriends);
		
		pFriend = new JPanel();
		pFriend.setOpaque(false);
		pFriend.setBorder(new LineBorder(new Color(128, 128, 128)));
		pFriend.setBounds(12, 540, 230, 155);
		pFriend.setBackground(null);
		frame.getContentPane().add(pFriend);
		pFriend.setLayout(null);
		
		spFriend = new JScrollPane();
		spFriend.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spFriend.setBounds(12, 12, 206, 96);
		pFriend.add(spFriend);
		
		btnRemoveSelectedUser = new JButton("Remove Selected User");
		btnRemoveSelectedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnNormal.isSelected()){
					if(!displayedUser.getListFriends().isEmpty()){
						displayedUser.removeFriend(friendList.getSelectedValue());
						friendList.setModel(modelFriend);
					}
					else{
						JOptionPane.showMessageDialog(null, "No friend to remove");
					}
				}
				else if (rdbtnBlocked.isSelected()){
					displayedUser.blockFriend(friendList.getSelectedValue());
					
				}
				
				refreshFriendPanel();
			}
		});
		btnRemoveSelectedUser.setBounds(12, 119, 206, 25);
		pFriend.add(btnRemoveSelectedUser);
		
		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setOpaque(false);
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelFriend = new DefaultListModel<String>();
				for (String i : displayedUser.getListFriends()){
					modelFriend.addElement(i);
				}
				friendList.setModel(modelFriend);
				spFriend.setViewportView(friendList);
				rdbtnBlocked.setSelected(false);
				rdbtnNormal.setSelected(true);
				btnRemoveSelectedUser.setText("Remove Selected User");
				
			}
		});
		rdbtnNormal.setSelected(true);
		rdbtnNormal.setBounds(80, 511, 80, 23);
		frame.getContentPane().add(rdbtnNormal);
		
		rdbtnBlocked = new JRadioButton("Blocked");
		rdbtnBlocked.setOpaque(false);
		rdbtnBlocked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelBlocked = new DefaultListModel<String>();
				for (String i : displayedUser.getBlockedUsers()){
					modelBlocked.addElement(i);
				}
				friendList.setModel(modelBlocked);
				spFriend.setViewportView(friendList);
				rdbtnNormal.setSelected(false);
				rdbtnBlocked.setSelected(true);
				btnRemoveSelectedUser.setText("Unblock Selected User");
			}
		});
		rdbtnBlocked.setBounds(165, 511, 80, 23);
		frame.getContentPane().add(rdbtnBlocked);
		
		friendList = new JList<String>();
		if (rdbtnNormal.isSelected()){
			modelFriend = new DefaultListModel<String>();
			for (String i : displayedUser.getListFriends()){
				modelFriend.addElement(i);
			}
			friendList.setModel(modelFriend);
			spFriend.setViewportView(friendList);
		}
		else if(rdbtnBlocked.isSelected()){
			modelBlocked = new DefaultListModel<String>();
			for (String i : displayedUser.getBlockedUsers()){
				modelBlocked.addElement(i);
			}
			friendList.setModel(modelBlocked);
			spFriend.setViewportView(friendList);
		}
	}
	
	/**
	 * searching user
	 */
	public void search(){
		txtSearchLine.setBounds(376, 8, 115, 20);
		pMenu.add(txtSearchLine);
		
		txtSearchLine.setText("Search user");
		txtSearchLine.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Color fg = new Color(51,51,51);
				txtSearchLine.setForeground(fg);
				if(txtSearchLine.getText().equals("Search user"))
				txtSearchLine.setText("");
				txtSearchLine.setBounds(314, 8, 240, 20);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Color fg = new Color(196,196,196);
				txtSearchLine.setForeground(fg);
				if(txtSearchLine.getText().equals(""))
					txtSearchLine.setText("Search user");
					txtSearchLine.setBounds(376, 8, 115, 20);
			}
		});
		txtSearchLine.setForeground(SystemColor.windowBorder);
		txtSearchLine.setText("Search user");
		txtSearchLine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtSearchLine.getText().equals("")){
					spSearch.setVisible(false);
				}
				else{
					spSearch.setVisible(true);
					listSearchmodel = new DefaultListModel<String>();
					setTxtSearchLineLength(0);
					for(User us : UserCollection.getList_users()){
						if(!us.getUserName().equals(UserCollection.getCurrentUser())){
							boolean control = true;
							
							for(int i=0 ; i<txtSearchLine.getText().length();i++){
								if(us.getName().toLowerCase().charAt(i) != txtSearchLine.getText().toLowerCase().charAt(i)){
									control = false;
									break;
								}
							}
							
							if(control && !UserCollection.findUser(UserCollection.getCurrentUser()).getBlockedUsers().contains(us.getUserName()) ){
								listSearchmodel.addElement(us.getName());
								setTxtSearchLineLength(getTxtSearchLineLength()+1);
							}
						}
					}
					listSearch2.setModel(listSearchmodel);
					spSearch.setBounds(314, 31, 240,120);
				}
			}
		});
		txtSearchLine.setColumns(10);
		JButton btnAddPost = new JButton("Add Post");
		btnAddPost.setBounds(655, 5, 97, 25);
		pMenu.add(btnAddPost);
		btnAddPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fAddPost.setVisible(true);
			}
		});
	}
	
	public String viewTaggedUsers(Collection<String> inputList){
		String output = "";
		Iterator<String> itr = inputList.iterator();
		
		for (int i=0; i < inputList.size() ; i++){
			if (i != inputList.size()-1){
				output += UserCollection.findUser(itr.next()).getName() + ",";
			}
			else{output += UserCollection.findUser(itr.next()).getName();}
		}
		
		return output;
	}
	
	public void refreshInformationPanel(){
		pInformation = new JPanel();
		pInformation.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		pInformation.setBounds(12, 260, 230, 230);
		frame.getContentPane().add(pInformation);
		pInformation.setLayout(null);
		
		JLabel lblStringDateOfBirth = new JLabel("Date of Birth ");
		lblStringDateOfBirth.setBounds(30, 30, 181, 15);
		pInformation.add(lblStringDateOfBirth);
		
		JLabel lblDateOfBirth = new JLabel();
		lblDateOfBirth.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(30, 60, 187, 15);
		pInformation.add(lblDateOfBirth);
		
		lblDateOfBirth.setText(df.format(displayedUser.getDateOfBirth()));
		
		JLabel lblStringSchool = new JLabel("School Graduated");
		lblStringSchool.setBounds(30, 90, 146, 15);
		pInformation.add(lblStringSchool);
		
		JLabel lblSchool = new JLabel();
		lblSchool.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSchool.setBounds(30, 120, 187, 15);
		pInformation.add(lblSchool);
		
		lblSchool.setText(displayedUser.getSchoolInfo());
		
		JComboBox<String> cbRelationship = new JComboBox<String>();
		cbRelationship.setModel(new DefaultComboBoxModel<String>(new String[] {"Single", "In relationship", "Divorced", "Complicated"}));
		cbRelationship.setBounds(30, 150, 187, 25);
		cbRelationship.setSelectedItem(displayedUser.getRelationshipStatu().substring(0, 1).toUpperCase().replace("İ", "I") + displayedUser.getRelationshipStatu().substring(1));
		pInformation.add(cbRelationship);
		
		lblName.setText(displayedUser.getName());
	}
	
	public void refreshFriendPanel(){
		rdbtnNormal.setSelected(true);
		rdbtnBlocked.setSelected(false);
		
		modelFriend.removeAllElements();
		for (String i : displayedUser.getListFriends()){
			modelFriend.addElement(i);
		}
		friendList.setModel(modelFriend);
		spFriend.setViewportView(friendList);
		
		if(displayedUser.getUserName().equals(UserCollection.getCurrentUser())){
			btnRemoveSelectedUser.setVisible(true);
		}
		else{
			btnRemoveSelectedUser.setVisible(false);
		}
		
		friendPanel();
	}
	
	public void refreshPostPanel(){
		//Online User
		tpPosts.setBounds(255, 260, 583, 435);
		pBigger.removeAll();
		
		double x = spPostView.getLocation().getX()+5;
		double y = spPostView.getLocation().getY()+5;
		
		for(Post p : UserCollection.findUser(displayedUser.getUserName()).getListPosts()){
			eachPostPanel = new JPanel();
			eachPostPanel.setBounds((int)x, (int)y, (int)spPostView.getSize().getWidth()-10, 100);
			eachPostPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 1, 0, Color.GRAY));
			eachPostPanel.setPreferredSize(new Dimension(560, 80));
			eachPostPanel.setLayout(new BorderLayout(10, 3));
			
			JTextArea taPostText = new JTextArea(p.getPostText());
			taPostText.setEditable(false);
			taPostText.setOpaque(false);
			taPostText.setLineWrap(true);
			taPostText.setPreferredSize(new Dimension(340, 50));
			eachPostPanel.add(taPostText, BorderLayout.CENTER);
			JLabel lblTaggedFriends = new JLabel("Tagged Users: " + viewTaggedUsers(p.getTaggedFriends())); eachPostPanel.add(lblTaggedFriends, BorderLayout.SOUTH);
			if(displayedUser.getUserName().equals(UserCollection.getCurrentUser())){
				JButton btnTaggFriend = new JButton("Tagg Friend"); eachPostPanel.add(btnTaggFriend,BorderLayout.EAST);
				btnTaggFriend.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						setSelectedPost(p);
						
						TagFriend tagFriendWindow = new TagFriend(getSelectedPost());
						tagFriendWindow.main(null);
					}
				});
			}
			if(p.getClass().getSimpleName().equals("TextPost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/t.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			else if(p.getClass().getSimpleName().equals("ImagePost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/i.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			else if(p.getClass().getSimpleName().equals("VideoPost")){
				JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/v.png"))); 
				lblPostIcon.setPreferredSize(new Dimension(100, 50));
				eachPostPanel.add(lblPostIcon, BorderLayout.WEST);
			}
			
			pBigger.add(eachPostPanel);
			
			y+=75;
		}
		
		//Friend Post view
		pBiggerFriend.removeAll();
		
		double k = spPostViewFriend.getLocation().getX()+5;
		double l = spPostViewFriend.getLocation().getY()+5;
		
		for (String friend : UserCollection.findUser(displayedUser.getUserName()).getListFriends()){
			for(Post p : UserCollection.findUser(friend).getListPosts()){
				eachPostPanelFriend = new JPanel();
				eachPostPanelFriend.setBounds((int)k, (int)l, (int)spPostViewFriend.getSize().getWidth()-10, 45);
				eachPostPanelFriend.setBorder(BorderFactory.createMatteBorder(0, 3, 1, 0, Color.GRAY));
				eachPostPanelFriend.setPreferredSize(new Dimension(560, 80));
				eachPostPanelFriend.setLayout(new BorderLayout(10, 5));
				
				JLabel lblPostID = new JLabel(UserCollection.findUser(friend).getName() + " has shared"); eachPostPanelFriend.add(lblPostID, BorderLayout.NORTH);
				JTextArea taPostText = new JTextArea(p.getPostText());
				taPostText.setEditable(false);
				taPostText.setOpaque(false);
				taPostText.setLineWrap(true);
				taPostText.setPreferredSize(new Dimension(340, 50));
				eachPostPanelFriend.add(taPostText, BorderLayout.CENTER);
				
				JLabel lblTaggedFriends = new JLabel("Tagged Users: " + viewTaggedUsers(p.getTaggedFriends())); eachPostPanelFriend.add(lblTaggedFriends, BorderLayout.SOUTH);
				
				if(p.getClass().getSimpleName().equals("TextPost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/t.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				else if(p.getClass().getSimpleName().equals("ImagePost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/i.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				else if(p.getClass().getSimpleName().equals("VideoPost")){
					JLabel lblPostIcon = new JLabel(new ImageIcon(LoginIn.class.getResource("/ImageResources/v.png"))); 
					lblPostIcon.setPreferredSize(new Dimension(100, 50));
					eachPostPanelFriend.add(lblPostIcon, BorderLayout.WEST);
				}
				pBiggerFriend.add(eachPostPanelFriend);
				
				l+=55;
			}
		}
		spPostViewFriend.setViewportView(pBiggerFriend);
		
		tpPosts.removeAll();
		tpPosts.setBounds(254, 236, 583, 459);
		
		tpPosts.add("Posts", spPostView);
		tpPosts.add("Friends' Posts", spPostViewFriend);
		
		frame.getContentPane().add(tpPosts);
		
	}
	
	//getter and setter methods
	public int getTxtSearchLineLength() {
		return txtSearchLineLength;
	}

	public void setTxtSearchLineLength(int txtSearchLineLength) {
		this.txtSearchLineLength = txtSearchLineLength;
	}

	public static User getDisplayedUser() {
		return displayedUser;
	}

	public void setDisplayedUser(User displayedUser) {
		LoginIn.displayedUser = displayedUser;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public static Post getSelectedPost() {
		return selectedPost;
	}

	public static void setSelectedPost(Post selectedPost) {
		LoginIn.selectedPost = selectedPost;
	}

	public static JButton getBtnHome() {
		return btnHome;
	}

	public void setBtnHome(JButton btnHome) {
		LoginIn.btnHome = btnHome;
	}
}
