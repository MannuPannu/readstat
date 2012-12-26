package com.view;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import javax.swing.JPanel;

import javax.swing.ListSelectionModel;

import javax.swing.JList;

import java.awt.ComponentOrientation;

import java.awt.Container;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.ComponentOrientation;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.Graphics;

import java.lang.Long;

import java.lang.Integer;

import java.util.List;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import javax.swing.*;

import com.model.StatisticsContainer;
import com.model.Statistics;
import com.model.ProcessStat;

/*
  The main window of the monitor program
 */

public class MainWindow extends JPanel {
    
    //A reference to the model containing all data to be used in the GUI
    StatisticsContainer statisticsContainer;

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    //The process list and model
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private ListSelectionModel listSelectionModel;

    private JFrame mainW;

    public MainWindow(StatisticsContainer statCont)
    {
	this.statisticsContainer = statCont;
	
	createMainWindow();
    }

    private void createMainWindow()
	{
	    /*
	     * Create main window
	     */
	    mainW = new JFrame("System monitor");
	    mainW.getContentPane().add(this);
	    mainW.pack();
	    mainW.setResizable(false);
	    mainW.setVisible(true);
	    mainW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainW.setSize(400, 400);

	    JMenuBar menuBar = new JMenuBar();

	    JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);

	    JMenu processMenu = new JMenu("Process");
	    processMenu.setMnemonic(KeyEvent.VK_P);

	    menuBar.add(fileMenu);
	    menuBar.add(processMenu);

	    mainW.setJMenuBar(menuBar);

	    addComponentsToPane(mainW.getContentPane());
	}

    public void createListModel()
    {
	this.listModel = new DefaultListModel<String>();



	if(this.statisticsContainer != null)
	    {
		Statistics stat = this.statisticsContainer.getStatistics();

		if(stat != null)
		    {
			List<ProcessStat> procStat = stat.getProcessStats();
			
			for(int i = 0; i < procStat.size(); i++)
			    {
				this.listModel.addElement(procStat.get(i).fileName);

				System.out.println("Hej!");
			    }
		    }
	    }
    }

    public void addComponentsToPane(Container pane) {

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

	createListModel(); //Creates the model for the list

	this.list = new JList<String>(this.listModel);
	this.listSelectionModel =  this.list.getSelectionModel();
	this.listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
	
	this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	this.list.setSelectedIndex(0);
	
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	if (shouldFill) {
	    //natural height, maximum width
	    c.fill = GridBagConstraints.HORIZONTAL;
	}

	if (shouldWeightX) {
	    c.weightx = 0.5;
	    c.weighty = 1.0;
	}

	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
	pane.add(this.list, c);

	JTextArea processInfo = new JTextArea(5, 20);
	JScrollPane scrollPane = new JScrollPane(processInfo);

	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.weighty = 1.0;
	c.gridx = 1;
	c.gridy = 0;
	pane.add(scrollPane, c);

    }

    public void updateView()
    {
	addComponentsToPane(mainW.getContentPane());
    }

    //Class for detecting list events
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	    int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting(); 
            System.out.println("Event for indexes "
                          + firstIndex + " - " + lastIndex
                          + "; isAdjusting is " + isAdjusting
                          + "; selected indexes:");

	    if (lsm.isSelectionEmpty()) {
                System.out.println(" <none>");
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        System.out.println(" " + i);
                    }
                }
            }

	}
    }
}