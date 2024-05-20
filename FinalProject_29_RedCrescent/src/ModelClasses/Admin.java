/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelClasses;
// Working till now 2:37 am
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faiza
 */
public class Admin {

    public static boolean makeEvent(Integer eventId, String eventType, String eventTime, String eventDescription, LocalDate eventDate, String eventLocation) throws IOException {

        Events newEvent = new Events(eventId, eventType, eventTime, eventDescription, eventDate, eventLocation, 0, null);

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            f = new File("Events.bin");

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(newEvent);
            return true;

        } catch (Exception e) {
            System.out.println("Error writing Object to binary file");
            return false;
        } finally {
            oos.close();
        }

    }
    
    public static ArrayList<Events> readAndReturnEventsArrayList() throws IOException, ClassNotFoundException  {

        FileInputStream fis = null;
        try {
            
            fis = new FileInputStream("Events.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Events> eventsArrayList = new ArrayList<>();
            
            try {
                // The File reading starts
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof Events) {
                        Events deserializedEvent = (Events) obj;
                        System.out.println(deserializedEvent.toString());
                        eventsArrayList.add(deserializedEvent);
                    }
                }
            } catch (EOFException e) {
                // End of file reached (EOFException thrown)
                // No more objects to read, so we stop the loop
                System.out.println("FIle has been read to the end.");
            }
            ois.close();
            fis.close();
            Events.setEventsArrayList(eventsArrayList);
            return eventsArrayList; // return the read objects as deserialized objects ArrayList
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Volunteer.class.getName()).log(Level.SEVERE, null, ex);
            // File not found Exception (EOFException thrown)
            System.out.println("File was not found.");
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Volunteer.class.getName()).log(Level.SEVERE, null, ex);
                // File opening exception
            }
        }
        return null; // Return Null if file couldn't be read
    }
}
