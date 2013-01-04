package com.view;

import java.util.List;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JViewport;

import com.model.*;

/**
 * Class responsible for displaying all statistic data for
 * one Poly/Ml process
 *
 * @author Magnus Stenqvist
 */

public class ProcessInfo extends JScrollPane {
    
    private MainPanel mainPanel;

    private JTextArea textArea;
    private JViewport viewPort;

    public ProcessInfo(MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;

	this.textArea = new JTextArea(20, 10);

	this.viewPort = new JViewport();
	this.viewPort.setView(this.textArea);

	this.setViewport(this.viewPort);

	updateTextArea();
    }

    //Reads the Processlist for an selected process
    public void updateTextArea()
    {
	//Update processlists textarea with statistics data
	this.textArea.setText("");

	String nameOfSelectedProcess = this.mainPanel.getProcessList().getSelectedProcess();
	Statistics statistics = this.mainPanel.getStatistics();

	if(nameOfSelectedProcess != null && statistics != null)
	    {
		//Load statistics data from process name
		ProcessStat procStat = statistics.getProcessStatByName(nameOfSelectedProcess);
		String processName = procStat.getFileName();
		this.textArea.append("Info for process: " + processName);

		List<Long> psCounters = procStat.getPsCounters();
		List<Integer> psTimers = procStat.getPsTimers();
		List<Integer> psSizes = procStat.getPsSizes();

		for(int i = 0; i < psCounters.size(); i++)
		    {
			this.textArea.append("\n" + String.valueOf(psCounters.get(i)));
		    }

		for(int i = 0; i < psTimers.size(); i++)
		    {
			this.textArea.append("\n" + String.valueOf(psTimers.get(i)));
		    }

		for(int i = 0; i < psSizes.size(); i++)
		    {
			this.textArea.append("\n" + String.valueOf(psSizes.get(i)));
		    }
	    }
    }
    
    public JTextArea getTextArea() {
	return textArea;
    }

    public void setTextArea(JTextArea textArea) {
	this.textArea = textArea;
    }
}