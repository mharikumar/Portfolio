package librarygui;
import java.util.Date;

import javax.swing.JFrame;
import storage.*;
import backend.*;

import librarygui.LibraryUserInterface;

/** 
 * class for creating the library gui screen
 * @param none
 */	
public class LibraryUserInterfaceManager {
public static void main(String[] args) {
		@SuppressWarnings("deprecation")
	
	JFrame frame = new LibraryUserInterface();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BookMarkers Library");
      frame.setVisible(true);      
}
}
