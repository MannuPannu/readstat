package com;

import com.statistics.Array;
import com.statistics.Statistics;
import com.statistics.ReadStat;

import com.gui.GuiControl;

class Main 
{
  public static void main(String[] args) 
  {
    ReadStat read = new ReadStat();
    Array statArray = read.getStatistics();
    GuiControl guiControl = new GuiControl(statArray);
  }
}