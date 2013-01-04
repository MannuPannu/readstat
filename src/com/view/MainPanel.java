package com.view;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.ComponentOrientation;

import javax.swing.JPanel;

import com.model.*;
import com.control.*;

/**
 * This class draws all the components on the windows pane
 *
 * @author Magnus Stenqvist
 */

public class MainPanel extends JPanel {
    
    private Statistics statistics;
    private MainControl mainControl;
    

    //The list of running Poly/Ml processes
    private ProcessList processList;

    //The process information component
    private ProcessInfo processInfo;

    //Layout variables
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public MainPanel(Statistics statistics, MainControl mainControl)
    {
	this.statistics = statistics;
	this.mainControl = mainControl;

        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

	this.processList = new ProcessList(this, mainControl);

	this.processInfo = new ProcessInfo(this);

	this.setLayout(new GridBagLayout());
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
	this.add(this.processList, c);

	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.weighty = 1.0;
	c.gridx = 1;
	c.gridy = 0;
	this.add(this.processInfo, c);
    }

    public ProcessList getProcessList() {
	return this.processList;
    }


    public ProcessInfo getProcessInfo() {
	return this.processInfo;
    }

    public Statistics getStatistics() {
	return this.statistics;
    }
}