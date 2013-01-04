package com.control;

import javax.swing.event.ListSelectionListener;

import javax.swing.event.ListSelectionEvent;

import javax.swing.ListSelectionModel;

import javax.swing.AbstractAction;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import com.view.*;

/* Class that contains all the actions in the program */

public class ActionContainer {

    private SelectProcessInListAction selectProcessAction;
    private QuitProgramAction quitProgramAction;

    public ActionContainer()
    {
    }

    private class QuitProgramAction extends AbstractAction
    {
	public QuitProgramAction(String text, ImageIcon icon,
				 String desc, Integer mnemonic)
	{
	    super(text, icon);
	}

	public void actionPerformed(ActionEvent e)
	{
	    System.exit(0);
	}

    }

    //Class for detecting list events
    class SelectProcessInListAction implements ListSelectionListener {

	MainPanel mainPanel;

	public SelectProcessInListAction(MainPanel mainPanel)
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

    public SelectProcessInListAction getSelectProcessAction(MainPanel mainPanel) {
	return this.selectProcessAction = new SelectProcessInListAction(mainPanel);
    }

    public QuitProgramAction getQuitProgramAction(String text, ImageIcon icon,
						  String desc, Integer mnemonic) {
	return this.quitProgramAction = new QuitProgramAction(text, icon, desc, mnemonic);
    }

}