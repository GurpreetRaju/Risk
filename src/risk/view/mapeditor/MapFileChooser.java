package risk.view.mapeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class implements file chooser operation
 * @author Harinder
 * @author Jyotsna
 */
public class MapFileChooser {
	
	/**
	 *Button to select a map file
	 */
	final JButton openFileChooser = new JButton("Choose Map File");
	
	/**
	 * Constructor to do the frame setting.
	 * @throws ClassNotFoundException class not found.
	 * @throws InstantiationException instantiation exception.
	 * @throws IllegalAccessException illegal access.
	 * @throws UnsupportedLookAndFeelException Unsupported JFrame check.
	 */
    public MapFileChooser() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame window = new JFrame("Map File Chooser");
        JPanel topPanel = new JPanel();
        
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.add(BorderLayout.NORTH, topPanel);
        topPanel.add(openFileChooser);
        window.setSize(500, 500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    
    /**
     * ActionListener for the file chooser button
     * @param newAction ActionListener object reference
     */
    public void openFileChooseBtnAction(ActionListener newAction) {
    	openFileChooser.addActionListener(newAction);
    	
    }
}