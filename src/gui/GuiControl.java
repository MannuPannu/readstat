package gui;

import gui.view.MainWindow;
import statistics.Array;
import statistics.Statistics;

import javax.swing.*;
import java.awt.*;

/*
  The main controller of the Graphical user interface
 */

public class GuiControl extends JComponent{
    
    private Array statArray;

    public GuiControl(Array statArray)
    {
	this.statArray = statArray;

	createMainWindow();
	//	MainWindow mainW = new MainWindow(this);
    }

    public Array getStatArray()
    {
	return this.statArray;
    }

    private void createMainWindow()
	{
	    /*
	     * Create game window
	     */
	
	    JFrame mainW = new JFrame("System monitor");
	    //	    mainW.addKeyListener(this.eventHandler);	
	    mainW.getContentPane().add(this);
	    mainW.pack();
	    mainW.setResizable(false);
	    mainW.setVisible(true);
	    mainW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainW.setSize(400, 400);
	}

    //Render all data to the screen
    public void paintComponent(Graphics g) {

        int arraySize = statArray.size;

        Statistics [] s = statArray.statObjArray;
	int i = 0;
	//	for(int i = 0; i < arraySize; i++)
	//  {
		String fileName = s[i].fileName;
		long[] psCounters = new long[s[i].psCountersSize];
		psCounters = s[i].psCounters;

		int[] psSizes = new int[s[i].psSizesSize];
		psSizes = s[i].psSizes;

		int[] psTimers = new int[s[i].psTimersSize];
		psTimers = s[i].psTimers;

		g.drawString("Statistics data for file: " + fileName, 10 , 20);
		int j = 0;
		    for(;j < s[i].psCountersSize; j++)
		    {
			String tempStr = String.valueOf(psCounters[j]);
			g.drawString(tempStr, 10, 40 + j*20);
		    }

		    int k = 0;
		    for(; k < s[i].psSizesSize; k++)
		    {
			String tempStr = String.valueOf(psSizes[k]);
			g.drawString(tempStr, 10, 40 + j*20 + k * 20);
		    }
		    
		    int l = 0;
		    for(; l < s[i].psTimersSize; l++)
		    {
			String tempStr = String.valueOf(psTimers[l]);
			g.drawString(tempStr, 10, 40 + j*20 + k*20 + l *20);
		    }
    }
}