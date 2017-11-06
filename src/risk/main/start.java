package risk.main;

import risk.model.GameDriver;
import risk.view.PhaseView;

/**
 * This class create a threat and call GameDriver class instance.
 * @author Gurpreet
 * @version 1.0
 * @author Gunpreet 
 * @version 1.2
 */
public class start extends Object{
	
	/**
	 * PhaseView Observer Instance
	 */
	private PhaseView phaseView;
	
	/**
	 * Sets observer for phase view.
	 * @param driver GameDriver Observable instance.
	 */
	public start(GameDriver driver){
		phaseView = new PhaseView();
		driver.addObserver(phaseView);
	}
	
	public static void main(String[] args) {
		/*Schedule a job for the event-dispatching thread:
          creating and showing this application's GUI.*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new start(GameDriver.getInstance());
            }
        });
    }
}