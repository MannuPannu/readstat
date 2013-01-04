package com.model;

import java.util.List;

/*
  Keeps process data objects in a hash table
 */
public class Statistics {
    
    private List<ProcessStat> procStatList;

    public Statistics (List<ProcessStat> procStatList) {

	this.procStatList = procStatList;
    }

    public List<ProcessStat> getProcessStats()
    {
	return this.procStatList;
    }

    public ProcessStat getProcessStatByName(String processName)
    {
	ProcessStat processStat = null;

	//Traverse to processstat list by process name and return that process data
	for(int i = 0; i < this.procStatList.size(); i++)
	    {
		if(processName == this.procStatList.get(i).getFileName())
		    {
			processStat = this.procStatList.get(i);
		    }
	    }
	
	return processStat;
    }
}

