package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.University;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import security.LoggedStudent;
import view.events.CollegeEventListener;

public class CostPanel extends JPanel implements CollegeEventListener {
	private JTable jTable;
	private UniversityTableModel  model;

	/**
	 * Create the panel.
	 */
	public CostPanel() {
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		model = new UniversityTableModel(new ArrayList<>());
		
		jTable = new JTable(model);
		scrollPane.setViewportView(jTable);
		jTable.setAutoCreateRowSorter(true);
		setLayout(groupLayout);

		
	}
	


	@Override
	public void doCompare(List<University> data) {
		// TODO Auto-generated method stub
		model.getData().clear();
		model.getData().addAll(data);
		model.fireTableStructureChanged();
		jTable.repaint();
		
	}
}
