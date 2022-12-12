package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ApiController;
import controller.StudentController;
import model.University;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Menu {
	private JTextField textField;
	private ApiController apiController;
	private StudentController studentController;
	JList jlist;
	DefaultListModel universityDataList;
	private JTable table;
	private JTable table_1;
	CollegeTabPanel collegeTabPanel;
	CostPanel costPanel;
	JFrame frame;
	
	public Menu() {
		super();
	
		this.apiController = new ApiController();
		this.studentController = new StudentController();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	void initializeUI() throws IOException { 

		
		frame = new JFrame("Menu");
		frame.setResizable(false);
		
		java.net.URL URL = getClass().getResource("/resources/colleges.png");
        Image img = ImageIO.read( URL );
        
		frame.setIconImage(img);
		
		frame.setBounds(300, 300, VisualConstants.PANEL_HEIGHT, VisualConstants.PANEL_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 40, 764, 431);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new SearchTabPanel();
		tabbedPane.addTab("Colleges", null, panel, null);
		tabbedPane.setBackgroundAt(0, Color.RED);
		
	
		
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		collegeTabPanel = new CollegeTabPanel();
		tabbedPane_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		tabbedPane.addTab("Your Colleges", collegeTabPanel);
		tabbedPane.setBackgroundAt(1, Color.GREEN);
		
		
		
		CostPanel costPanel = new CostPanel();
		tabbedPane.addTab("Costs", null, costPanel, null);
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setBackgroundAt(2, Color.YELLOW);
		
		collegeTabPanel.addCollegeEventListener(costPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 11, 764, 29);
		frame.getContentPane().add(panel_1);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRemoveAccount();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(675, Short.MAX_VALUE)
					.addComponent(btnDeleteAccount))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnDeleteAccount)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
//		table_1 = new JTable();
//		table_1.setEnabled(false);
//		table_1.setColumnSelectionAllowed(true);
//		table_1.setCellSelectionEnabled(true);
//		table_1.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"Type Of Costs For Selected College:", ""},
//				{"Average Cost Of Attendance", null},
//				{"Room and Board Fees", null},
//				{"Tuition for In State Students", null},
//				{"Tuition for Out of State Students", null},
//				{"Book Supply Fees", null},
//			},
//			new String[] {
//				"Type Of Cost", "Selected College"
//			}
//		));
//		table_1.getColumnModel().getColumn(0).setPreferredWidth(214);
//		table_1.getColumnModel().getColumn(1).setPreferredWidth(312);
//		panel_1.add(table_1);
//		
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
			
		frame.setVisible(true);
	}
	
	
	private void doRemoveAccount() {
		boolean success = this.studentController.removeAccount();
		if(success) {
			frame.dispose();
		}
	}
}
