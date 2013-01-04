package com.view;

import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;

import com.model.*;
import com.control.*;

/**
 * The window of the application
 *
 * @author Magnus Stenqvist
 */

public class MainWindow extends JFrame {
    
    //A reference to the model containing all data to be used in the GUI
    Statistics statistics;

    MainControl mainControl;

    private MainPanel mainPanel;

    public MainWindow(Statistics statistics, MainControl mainControl)
    {
	this.statistics = statistics;
	this.mainControl = mainControl;
	
	createMainWindow();
	createMenuBar();
    }

    private void createMainWindow()
    {
	/*
	 * Create main window
	 */
	this.setTitle("System Monitor");

	this.mainPanel = new MainPanel(this.statistics, this.mainControl);
	
	this.getContentPane().add(this.mainPanel);

	this.pack();
	this.setResizable(false);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(600, 400);
    }

    private void createMenuBar()
    {
	JMenuBar menuBar = new JMenuBar();

	JMenu fileMenu = new JMenu("File");
	fileMenu.setMnemonic(KeyEvent.VK_F);

	JMenuItem quitProgram = new JMenuItem("Quit");
	quitProgram.setAction(this.mainControl.getActionContainer().getQuitProgramAction("Quit", null, null, null));

	fileMenu.add(quitProgram);

	//	JMenu processMenu = new JMenu("Process");
	//	processMenu.setMnemonic(KeyEvent.VK_P);

	menuBar.add(fileMenu);
	//	menuBar.add(processMenu);

	this.setJMenuBar(menuBar);
    }

    public MainPanel getMainPanel() {
	return mainPanel;
    }
}