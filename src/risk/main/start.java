package risk.main;

import risk.controller.Controller;

/**
 * This class create a threat and call GameDriver class instance.
 * @author Gurpreet
 * @version 1.0
 */
public class start {
	
	/**
	 * Main method that starts the game.
	 * @param args for command-line arguments.
	 */
	public static void main(String[] args) {
        
		/*Schedule a job for the event-dispatching thread:
          creating and showing this application's GUI.*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller c = new Controller();
                c.initialize();;
            }
        });
    }
}