package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import model.University;
import security.LoggedStudent;
import view.events.CollegeEventListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import controller.StudentController;

import javax.swing.LayoutStyle.ComponentPlacement;

public class CollegeTabPanel extends JPanel {
	 DefaultListModel<University> universityDataList;
	 JList<University> jListUniv;
	 List<CollegeEventListener> compareListners = new ArrayList<CollegeEventListener>();
	 private StudentController studentController;

	/**
	 * Create the panel.
	 */
	public CollegeTabPanel() {
		
		this.studentController = new StudentController();
		JButton btnGenrate = new JButton("Compare");
		btnGenrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCompare();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRemove();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGenrate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove)
							.addGap(270))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenrate)
						.addComponent(btnRemove))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		universityDataList = new DefaultListModel<>();
		
		jListUniv = new JList(universityDataList);
		scrollPane.setViewportView(jListUniv);
		setLayout(groupLayout);
		
	    this.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e )
	        {
	        	loadList();
	        }

	        public void componentHidden ( ComponentEvent e )
	        {
	           clearList();
	        }
	    } );

	}
	
	
	public void addCollegeEventListener(CollegeEventListener collegeEventListener) {
		this.compareListners.add(collegeEventListener);
	}
	
	private void loadList() {
		universityDataList.addAll(LoggedStudent.getLoggedStudent().getUniversities());
	}
	
	private void clearList() {
		universityDataList.clear();
	}
	private void doCompare() {
		List<University> selectedUniversities = jListUniv.getSelectedValuesList();
		if(!selectedUniversities.isEmpty()) {
			for(CollegeEventListener listener: this.compareListners) {
				listener.doCompare(selectedUniversities);
			}
		}
		
	}
	
	private void doRemove() {
		List<University> selectedUniversities = jListUniv.getSelectedValuesList();
		if(!selectedUniversities.isEmpty()) {
			try {
				studentController.removeUniversities(selectedUniversities);
				universityDataList.clear();
				universityDataList.addAll(LoggedStudent.getLoggedStudent().getUniversities());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
