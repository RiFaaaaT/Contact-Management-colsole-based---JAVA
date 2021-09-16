
//package contactManagement;

import java.io.File; 
import java.io.IOException; 
import java.io.RandomAccessFile; 
import java.lang.NumberFormatException; 
import javax.swing.JOptionPane;

class UpdateContact implements Update { 
    
    public void updateContact()
    { 
  
        try { 
  
            // Get the name of the contact to be updated 
            // from the Command line argument 
         
            String newName = JOptionPane.showInputDialog("Enter name to update info");
  
            // Get the number to be updated 
            // from the Command line argument 
            long newNumber = Long.parseLong(JOptionPane.showInputDialog("Enter the contact number which wants to be update")); 
  
            String nameNumberString; 
            String name; 
            long number; 
            int index; 
  
            // Using file pointer creating the file. 
            File file = new File("usersContact.txt"); 
  
            if (!file.exists()) {
                // Create a new file if not exists. 
                file.createNewFile(); 
            } 
  
            // Opening file in reading and write mode. 
            RandomAccessFile raf = new RandomAccessFile(file, "rw"); 
            boolean found = false; 
  
            // Checking whether the name 
            // of contact already exists. 
            // getFilePointer() give the current offset 
            // value from start of the file. 
            while (raf.getFilePointer() < raf.length()) { 
  
                // reading line from the file. 
                nameNumberString = raf.readLine(); 
  
                // finding the position of '!' 
                index = nameNumberString.indexOf('!'); 
  
                // separating name and number. 
                name = nameNumberString.substring(0, index); 
                number = Long.parseLong(nameNumberString.substring(index + 1)); 
  
                // if condition to find existence of record. 
                if (name.equals(newName) || number == newNumber) { 
                    found = true; 
                    break; 
                } 
            } 
  
            // Update the contact if record exists. 
            if (found == true) { 
                
                System.out.println(" You are here "); 
                // Creating a temporary file 
                // with file pointer as tmpFile. 
                File tmpFile = new File("temp.txt"); 
  
                // Opening this temporary file 
                // in ReadWrite Mode 
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw"); 
  
                // Set file pointer to start 
                raf.seek(0); 
  
                // Traversing the friendsContact.txt file 
                while (raf.getFilePointer() < raf.length()) { 
  
                    // Reading the contact from the file 
                    nameNumberString = raf.readLine(); 
  
                    index = nameNumberString.indexOf('!'); 
                    name = nameNumberString.substring(0, index); 
  
                    // Check if the fetched contact 
                    // is the one to be updated 
                    if (name.equals(newName)) { 
  
                        // Update the number of this contact 
                        nameNumberString = name + "!" + String.valueOf(newNumber); 
                    } 
  
                    // Add this contact in the temporary file 
                    tmpraf.writeBytes(nameNumberString); 
  
                    // Add the line separator in the temporary file 
                    tmpraf.writeBytes(System.lineSeparator()); 
                } 
  
                // The contact has been updated now 
                // So copy the updated content from 
                // the temporary file to original file. 
  
                // Set both files pointers to start 
                raf.seek(0); 
                tmpraf.seek(0); 
  
                // Copy the contents from 
                // the temporary file to original file. 
                while (tmpraf.getFilePointer() < tmpraf.length()) { 
                    raf.writeBytes(tmpraf.readLine()); 
                    raf.writeBytes(System.lineSeparator()); 
                } 
  
                // Set the length of the original file 
                // to that of temporary. 
                raf.setLength(tmpraf.length()); 
  
                // Closing the resources. 
                tmpraf.close(); 
                raf.close(); 
  
                // Deleting the temporary file 
                tmpFile.delete(); 
  
                System.out.println("Information updated."); 
            } 
            // The contact to be updated could not be found 
            else { 
  
                // Closing the resources. 
                raf.close(); 
  
                // Print the message 
                System.out.println("Information not found"); 
            } 
        } 
  
        catch (IOException ioe) { 
            System.out.println(ioe); 
        } 
  
        catch (NumberFormatException nef) { 
            System.out.println(nef); 
        } 
    } 
}
