package com.control;

import java.lang.Character;

import java.util.ArrayList;

import java.lang.Integer;

import java.lang.Long;

import java.util.List;

import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.io.DataInputStream;

import java.io.FileInputStream;

import com.model.StatisticsContainer;
import com.model.Statistics;
import com.model.ProcessStat;

import com.view.MainWindow;

/*
  Responsible for creating the model and the view and update both with data

  It uses ReadStat to execute native code to store statistics data on disc, which
  it then reads and supplies to the model and view.
 */
public class MainControl {
    
    private MainWindow mainWindow; //View
    private StatisticsContainer statisticsContainer; //Model

    public MainControl ()
    {
	//Creates the a container for storing statistics data
	this.statisticsContainer = new StatisticsContainer();
	this.mainWindow = new MainWindow(this.statisticsContainer);

	ReadStat read = new ReadStat();
	System.out.println("Write data to file");

	// Native C++ code that gets and writes statistics data to file
	read.writedatatofile(); 

	//Read that file
	Statistics statData = readStatFromFile();

	//Update model with new statistics data
	this.statisticsContainer.updateStatistics(statData);

	//Repaint view
	this.mainWindow.repaint();

    }

    private Statistics readStatFromFile()
    {

	String fileName = new String();;

	List<ProcessStat> procStatList = new ArrayList<ProcessStat>();
	List<Long> psCounters = new ArrayList<Long>();
	List<Integer> psSizes = new ArrayList<Integer>();
	List<Integer> psTimers = new ArrayList<Integer>();
	
	try{
	    // Open the file that is the first 
	    // command line parameter
	    FileInputStream fstream = new FileInputStream("statistics.txt");
	    // Get the object of DataInputStream
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));

	    char c;
	    while((c = (char)br.read()) == 'p')
		{
		    fileName = c + br.readLine(); //Get filename
		    
		    for(int i = 0; i < 8; i++)
			{
			    psCounters.add(Long.parseLong(br.readLine()));
			}

		    for(int i = 0; i < 5; i++)
			{
			    psSizes.add(Integer.getInteger(br.readLine()));
			}

		    for(int i = 0; i < 4; i++)
			{
			    psTimers.add(Integer.getInteger(br.readLine()));
			}

		    ProcessStat procData = new ProcessStat(fileName, psCounters, psSizes, psTimers);
		    System.out.println("Put in the list!");
		    procStatList.add(procData);
		}

	    //Close the input stream
	    in.close();
	}catch (Exception e){//Catch exception if any
	    System.err.println("Error: " + e.getMessage());
	}


	Statistics statData = new Statistics(procStatList);

	return statData;
    }
}