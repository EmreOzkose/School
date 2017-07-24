package JFrames;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import Main.Post;
import Main.User;
import Main.UserCollection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TagFriend {

	private JFrame frame;
	
	private Post post;
	private JList<String> listTaggableFriends;
	

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Post po = LoginIn.getSelectedPost();
					TagFriend window = new TagFriend(po);
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
	public TagFriend(Post post) {
		setPost(post);
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 280, 301);
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTaggableFriends = new JLabel("Taggable friends");
		lblTaggableFriends.setBounds(20, 30, 143, 15);
		frame.getContentPane().add(lblTaggableFriends);
		
		listTaggableFriends = new JList<String>();
		listTaggableFriends.setBounds(20, 60, 240, 157);
		frame.getContentPane().add(listTaggableFriends);
		
		try{
			showInList(getPost());
		}
		catch (NullPointerException e) {
			System.out.println("The post is empty");
		}
		
		JButton btnTagFriend = new JButton("Tag Friend");
		btnTagFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(User user : UserCollection.getList_users()){
					if(user.getName().equals(listTaggableFriends.getSelectedValue()))
						getPost().getTaggedFriends().add(user.getUserName());
				}
				
				
				
				LoginIn.getBtnHome().doClick();
				showInList(getPost());
				
			}
		});
		btnTagFriend.setBounds(20, 229, 240, 25);
		frame.getContentPane().add(btnTagFriend);
	}
	
	public void showInList(Post p){
		DefaultListModel<String> modelTag = new DefaultListModel<String>();
		
		for (String u : LoginIn.getDisplayedUser().getListFriends()){
			if (!getPost().getTaggedFriends().contains(u))
				modelTag.addElement(UserCollection.findUser(u).getName());
		}
		
		listTaggableFriends.setModel(modelTag);
		
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
