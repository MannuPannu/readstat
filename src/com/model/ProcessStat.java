package com.model;

import java.lang.Long;

import java.lang.Integer;

import java.util.List;

/*
  Statistics data for each Poly/ml process
 */

public class ProcessStat {
    
    public String fileName; //File name of the process
    public List<Long> psCounters;
    public List<Integer> psSizes;
    public List<Integer> psTimers;

    public ProcessStat(String fileName, List<Long> psCounters, List<Integer> psSizes, 
		       List<Integer> psTimers)
    {
	this.fileName = fileName;
	this.psCounters = psCounters;
	this.psSizes = psSizes;
	this.psTimers = psTimers;
    }
}