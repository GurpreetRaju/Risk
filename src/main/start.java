package main;

import controller.GameDriver;

public class start {
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameDriver.getInstance().runGame();
            }
        });
    }
}