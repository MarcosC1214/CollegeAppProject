package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import controller.StudentController;
import model.Student;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import view.StartUp;
import model.Student;

public class RegisterSystem {
	private JTextField textField;
	
	private StudentController studentController;
	
	
	public RegisterSystem() {
		this.studentController =  new StudentController();
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	void initUI1() throws IOException, FileNotFoundException {
		
		JFrame frame = new JFrame("Create New Account");
		
    	
    	java.net.URL URL = getClass().getResource("/resources/colleges.png");
        Image daffyDuckImage = ImageIO.read( URL );
        
		frame.setIconImage(daffyDuckImage);
    	
    	
    	frame.setSize(320, 219);
    	frame.setLocation(800, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1);
		
		
		panel1.setLayout(null);

		JLabel userLabel = new JLabel("New Username");
		userLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 13));
		userLabel.setBounds(10, 62, 98, 25);
		panel1.add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		userText.setBounds(127, 62, 160, 25);
		panel1.add(userText);
		
		JLabel userLabel_1 = new JLabel("Student Name");
		userLabel_1.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 13));
		userLabel_1.setBounds(10, 26, 98, 25);
		panel1.add(userLabel_1);
		
		textField = new JTextField(20);
		textField.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		textField.setBounds(127, 26, 160, 25);
		panel1.add(textField);
		
		
		JLabel passwordLabel = new JLabel("New Password");
		passwordLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 13));
		passwordLabel.setBounds(10, 98, 107, 25);
		panel1.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		passwordText.setBounds(127, 98, 160, 25);
		panel1.add(passwordText);

		JButton loginButton = new JButton("Continue");
		 loginButton.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 12));
		 loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
            	 // ADD REGISTERED USER TO JSON
            	 
            	 if (textField.getText().equals("") || userText.getText().equals("") || new String(passwordText.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "Empty Fields!","ERROR", JOptionPane.ERROR_MESSAGE);
					}
            	 else {
            		 
      				boolean success = doRegister(textField.getText(), userText.getText(), new String(passwordText.getPassword()));
      				frame.dispose();
            	 }
                 	
                 }
                
         });
		 
		loginButton.setBounds(127, 134, 160, 25);
		panel1.add(loginButton);
		
		
		frame.setVisible(true);
	}
	
	private boolean doRegister(String name, String username, String password) {
		Student st;
		try {
			st = this.studentController.registerStudent(name, username, password);
			return st != null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
