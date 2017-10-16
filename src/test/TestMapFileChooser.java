package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

import org.junit.Test;
/**
 * Junit4 test 
 * MapFileChooser class
 * @param file java file chooser for choosing files from local directories
 * @author jyotsna
 *
 */
public class TestMapFileChooser {

	@Test
	public void test() {
		JFileChooser fc = new JFileChooser();
		 Map<String, String> map = new HashMap<String, String>();
		assertNotNull(fc.FILES_AND_DIRECTORIES);
		//assertNotNull(fc.getSelectedFile());
		assertNotNull(fc);
		//assertEquals(fc.getSelectedFile(),map);
		
	}

}
