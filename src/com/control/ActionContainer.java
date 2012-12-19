package src.control;

import javax.swing.AbstractAction;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;

import javax.swing.Action;

/* Class that contains all the actions in the program */

public class ActionContainer {
    
    public ActionContainer()
    {
	
    }

    private class selectProcessAction extends AbstractAction
    {
	public selectProcessAction(String text, ImageIcon icon,
                      String desc, Integer mnemonic)
	{
	    super(text, icon);
	}

	public void actionPerformed(ActionEvent e)
	{
	    System.out.println("Action 1");
	}

    }
}