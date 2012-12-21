package com.model;

import com.view.MainWindow;

public class StatisticsContainer {

    private Statistics statistics;
    private MainWindow mainWindow;

    public StatisticsContainer()
    {
	
    }

    public void updateStatistics(Statistics obj)
    {
	this.statistics = obj;
    }

    public Statistics getStatistics()
    {
	return this.statistics;
    }
}