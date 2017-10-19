package main;

import model.GameDriver;

/**
 * This class create a threat and call GameDriver class instance.
 * @author Gurpreet
 * @version 1.0
 */
public class start {
	public static void main(String[] args) {
        
		/*Schedule a job for the event-dispatching thread:
          creating and showing this application's GUI.*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameDriver.getInstance();
            }
        });
    }
}