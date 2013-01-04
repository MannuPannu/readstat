package com.view;

import java.lang.String;

import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;

//Class for detecting list events
class SharedListSelectionHandler implements ListSelectionListener {

    MainPanel mainPanel;

    public SharedListSelectionHandler(MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
    }

    //Executes whenever the user clicks in the processlist 
    public void valueChanged(ListSelectionEvent e) { 
	ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	int firstIndex = e.getFirstIndex();
	int lastIndex = e.getLastIndex();
	boolean isAdjusting = e.getValueIsAdjusting(); 
	// System.out.println("Event for indexes "
	// 		   + firstIndex + " - " + lastIndex
	// 		   + "; isAdjusting is " + isAdjusting
	// 		   + "; selected indexes:");

	if (lsm.isSelectionEmpty()) {
	    System.out.println(" <none>");
	} else {
	    // Find out which indexes are selected.
	    int minIndex = lsm.getMinSelectionIndex();
	    int maxIndex = lsm.getMaxSelectionIndex();
	    for (int i = minIndex; i <= maxIndex; i++) {
		if (lsm.isSelectedIndex(i)) {

		    //Set which process file name is selected in ProcessList
		    this.mainPanel.getProcessList().setSelectedProcess((String)this.mainPanel.getProcessList().getListModel().get(i));

		    //Invoke update textarea
		    this.mainPanel.getProcessInfo().updateTextArea();
		    
		}
	    }
	}

    }
}