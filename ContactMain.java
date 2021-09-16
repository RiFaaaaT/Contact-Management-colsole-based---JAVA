// package contactManagement;

import java.io.File; 
import java.io.IOException; 
import java.io.RandomAccessFile; 
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author rifat
 */

// add interface
interface Contatct {
  public void addInfo(); 
}

// display interface
interface Display {
    public void showContact();
}

// update interface
interface Update {
    public void updateContact();
}

// delete interface
interface Delete {
    public void deleteContact();
}

public class ContactMain {
    public static void main(String[] args) {
        
        long number = Long.parseLong(JOptionPane.showInputDialog("Enter number in the range of 1-4 for 1=add, 2=display, 3=update and 4=delete information")); 
		
		if(number == 1) {
            //Add information
            AddContact add = new AddContact();  // Create a AddContact class object
            add.addInfo();
        } else if (number == 2) {
             // Display information
            DisplayContacts display = new DisplayContacts();  // Create a DisplayContacts class object
            display.showContact();
        } else if (number == 3) {
            // Update information
            UpdateContact update = new UpdateContact();  // Create a UpdateContact class object
            update.updateContact(); 
        } else if (number == 4) {
            // Update information
            DeleteContact delete = new DeleteContact();  // Create a deleteContact class object
            delete.deleteContact();
        } else {
            System.out.println("Sorry! You have selected out of range."); 
        }
		
         return;
    }
}
