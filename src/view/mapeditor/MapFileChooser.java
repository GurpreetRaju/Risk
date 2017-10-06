package view.mapeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MapFileChooser {
	
	final JButton openFileChooser = new JButton("Choose Map File");
	
    public MapFileChooser() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame window = new JFrame("Map File Chooser");
        JPanel topPanel = new JPanel();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.add(BorderLayout.NORTH, topPanel);
        topPanel.add(openFileChooser);
        window.setSize(500, 500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    
    public void openFileChooseBtnAction(ActionListener newAction) {
    	openFileChooser.addActionListener(newAction);
    }
}