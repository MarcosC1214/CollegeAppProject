package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.StudentController;
import model.Student;
import view.StartUp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginSystem {
	
	private StudentController studentController;
	
	JFrame frame;
	public LoginSystem() {
		this.studentController =  new StudentController();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	void initUI() throws IOException {
		
		
		frame = new JFrame("Login");
    	
    	java.net.URL URL = getClass().getResource("/resources/colleges.png");
        Image daffyDuckImage = ImageIO.read( URL );
        
		frame.setIconImage(daffyDuckImage);
    	
    	
    	frame.setSize(300, 150);
    	frame.setLocation(800, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1);
		
		
		panel1.setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 13));
		userLabel.setBounds(10, 10, 80, 25);
		panel1.add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		userText.setBounds(100, 10, 160, 25);
		panel1.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 13));
		passwordLabel.setBounds(10, 40, 80, 25);
		panel1.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		passwordText.setBounds(100, 40, 160, 25);
		panel1.add(passwordText);

		JButton loginButton = new JButton("Login");
		 loginButton.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		 loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
            	 
            	   boolean success = doLogin(userText.getText(), new String(passwordText.getPassword()));
                	
            	   if (success) {
                	Menu mainMenu;
    				try {
    					frame.setVisible(false);
    					mainMenu = new Menu();
    					mainMenu.initializeUI();
    					//frmWelcome.setVisible(false);
    					
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                	
                }
            	   else {
            		   JOptionPane.showMessageDialog(null, "Incorrect Password!",
                               "ERROR", JOptionPane.ERROR_MESSAGE);
            	   }
            	   
             	}
             });
		 
		loginButton.setBounds(100, 80, 160, 25);
		panel1.add(loginButton);
		
		frame.setVisible(true);
	}
	
	private boolean doLogin(String username, String password) {
		Student st = this.studentController.loginStudent(username, password);
		frame.dispose();
		return st != null;
	}
}

