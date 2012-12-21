package com.view;

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

public class MainWindow extends JComponent {
    
    //A reference to the model containing all data to be used in the GUI
    StatisticsContainer statisticsContainer;

    public MainWindow(StatisticsContainer statCont)
    {
	this.statisticsContainer = statCont;
	
	createMainWindow();
    }

    private void createMainWindow()
	{
	    /*
	     * Create game window
	     */
	    JFrame mainW = new JFrame("System monitor");
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
	}

    public void paintComponent(Graphics g)
    {	
	g.drawString("Testing" , 10, 10);

	if(this.statisticsContainer != null)
	    {
		Statistics stat = this.statisticsContainer.getStatistics();
		if(stat != null)
		    {
			List<ProcessStat> procList = stat.getProcessStats();
	
			for(int i = 0; i < procList.size(); i++)
			    {
				ProcessStat procStat = procList.get(i);
		
				String fileName = procStat.fileName;
				List<Long>  psCounters = procStat.psCounters;
				List<Integer> psSizes = procStat.psSizes;
				List<Integer> psTimers = procStat.psTimers;

				g.drawString("Statistics data for file: " + fileName, 10 , 20 + i*20);
			    }
		    }

	    }
    }
}