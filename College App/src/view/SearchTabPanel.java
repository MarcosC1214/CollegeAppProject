package view;

import javax.swing.JPanel;
//import java.awt.CardLayout;
import javax.swing.JTextField;

import controller.ApiController;
import controller.StudentController;
import model.University;
import security.LoggedStudent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class SearchTabPanel extends JPanel {
	private JTextField textFieldSearch;
	private ApiController apiController;
    DefaultListModel<University> universityDataList;
    private StudentController studentController;
    JList<University> jList;

	/**
	 * Create the panel.
	 */
	public SearchTabPanel() {
       this.apiController = new ApiController();
       this.studentController = new StudentController();
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getUniversitiesBySearchText(textFieldSearch.getText());
			}
		});
		
		
		
		JPanel panel = new JPanel();
		
		JButton jbtnAdd = new JButton("Add");
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ADD COLLEGES TO JSON
				// AND UPDATE STUDENT 
				
				addUniversities();
				
				
				
			}
		});
		
		JPanel jpanelContainer = new JPanel();
		
		universityDataList = new DefaultListModel<>();
		jList = new JList(universityDataList);
		jList.setValueIsAdjusting(true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addGap(9)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(jpanelContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(jbtnAdd, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(551, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(jbtnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpanelContainer, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(403, Short.MAX_VALUE))
		);
		JScrollPane scrollPane = new JScrollPane(jList);
		GroupLayout gl_jpanelContainer = new GroupLayout(jpanelContainer);
		gl_jpanelContainer.setHorizontalGroup(
			gl_jpanelContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_jpanelContainer.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_jpanelContainer.setVerticalGroup(
			gl_jpanelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_jpanelContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addContainerGap())
		);
		jpanelContainer.setLayout(gl_jpanelContainer);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	private void getUniversitiesBySearchText(String text) {
		universityDataList.clear();
		if(text == null || text.isEmpty()) {
			universityDataList.clear();
		
		}
		else {
			List<University> universities =  apiController.getUniversitiesByName(text);
			if(LoggedStudent.isLogged()) {
				LoggedStudent.getLoggedStudent().getUniversities();
				universityDataList.addAll(universities);
			}
			
		}
		
	}
	
	
	private void addUniversities()
	{
		List<University> selectedUniversities = jList.getSelectedValuesList();
		try {
			this.studentController.addUniversitiesToCurrentUser(selectedUniversities);
			//todo show messgae of ssuccess
			JOptionPane.showMessageDialog(null, "University Successfully Added");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//todo show message 
			JOptionPane.showMessageDialog(null, "Unable to add!");
			e.printStackTrace();
		}
		
	}
	
	
	
}
