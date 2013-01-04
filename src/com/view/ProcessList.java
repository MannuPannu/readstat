package com.view;

import java.util.List;

import javax.swing.ListModel;

import java.lang.String;

import javax.swing.ListSelectionModel;

import com.model.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.control.*;

public class ProcessList extends JList<String> {
    
    private MainPanel mainPanel;
    private MainControl mainControl;

    private DefaultListModel<String> listModel;

    private ListSelectionModel listSelectionModel;

    //The name of the selected process, used to show data in textarea
    private String selectedProcess;

    public ProcessList(MainPanel mainPanel, MainControl mainControl)
    {
	this.mainPanel = mainPanel;
	this.mainControl = mainControl;
	
	createListModel();

	this.setModel(this.listModel);

	this.listSelectionModel = this.getSelectionModel();

	//The process info is affected by the selection of the list
	this.listSelectionModel.addListSelectionListener(this.mainControl.getActionContainer().getSelectProcessAction(this.mainPanel));

	this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	//this.setSelectedIndex(0);
    }

    public void createListModel()
    {
	this.listModel = new DefaultListModel<String>();

	Statistics statistics = this.mainPanel.getStatistics();

	if(statistics != null)
	    {
		List<ProcessStat> procStat = statistics.getProcessStats();
			
		for(int i = 0; i < procStat.size(); i++)
		    {
			this.listModel.addElement(procStat.get(i).fileName);
		    }
	    }
    }

    public String getSelectedProcess() {
	return selectedProcess;
    }

    public void setSelectedProcess(String selectedProcess) {
	this.selectedProcess = selectedProcess;
    }

    public DefaultListModel<String> getListModel() {
	return this.listModel;
    }

    public void setListModel(DefaultListModel<String>listModel) {
	this.listModel = listModel;
    }
}