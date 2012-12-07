package gui.view;

import gui.GuiControl;

import javax.swing.*;
import java.awt.*;

/*
  The main window of the monitor program
 */

public class MainWindow extends JFrame {
    
    public MainWindow(GuiControl gc)
    {
	this.setLocation(10, 10);
	this.setSize(new Dimension(400, 400));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("System monitor for Poly/Ml");
	this.setVisible(true);
    }
}