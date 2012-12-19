package com.statistics;

public class Array {
    
    public Statistics[] statObjArray;

    public int size;

    Array (Statistics[] statObjArray, int size) {

	this.size = size;

	this.statObjArray = new Statistics[size];

	System.arraycopy(statObjArray, 0, this.statObjArray, 0, size);
    }
}