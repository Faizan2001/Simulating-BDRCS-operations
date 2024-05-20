package ModelClasses;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 */
/**
 *
 * @author faiza
 */
public class Volunteer extends User implements Serializable {

    private String preferredDays;
    private ArrayList<Events> participatedEvents;
    private ArrayList<Events> completedEvents; // New field

    public Volunteer(Integer id, String name, String email, Integer age, String password, String location, String preferredDays, ArrayList<Events> participatedEvents, ArrayList<Events> completedEvents) {
        super(name, email, password, location, id, age);
        this.preferredDays = preferredDays;
        this.participatedEvents = new ArrayList<>();
        this.completedEvents = new ArrayList<>();
    }

    public static boolean registerToFile(Integer id, String name, String email, String password, Integer age, String preferredDays, String location, ArrayList<Events> participatedEvents, ArrayList<Events> completedEvents) throws IOException {

        Volunteer volunteer = new Volunteer(id, name, email, age, password, location, preferredDays, participatedEvents, completedEvents);

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            f = new File("Volunteer.bin");

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(volunteer);
            return true;

        } catch (Exception e) {
            System.out.println("Error writing Object to binary file");
            return false;
        } finally {
            oos.close();
        }

    }

    public static ArrayList<Volunteer> readAndReturnArrayList() throws IOException, ClassNotFoundException {

        FileInputStream fis = null;
        try {

            fis = new FileInputStream("Volunteer.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();

            try {
                // The File reading starts
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof Volunteer) {
                        Volunteer deserializedVolunteer = (Volunteer) obj;
                        System.out.println(deserializedVolunteer.toString());
                        volunteerArrayList.add(deserializedVolunteer);
                    }
                }
            } catch (EOFException e) {
                // End of file reached (EOFException thrown)
                // No more objects to read, so we stop the loop
                System.out.println("FIle has been read to the end.");
            }
            ois.close();
            fis.close();
            return volunteerArrayList; // return the read objects as deserialized objects ArrayList

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

    public ArrayList<Events> getCompletedEvents() {
        return completedEvents;
    }

    public void addCompletedEvent(Events event) {
        completedEvents.add(event);
    }

    public ArrayList<Events> getParticipatedEvents() {
        return this.participatedEvents;
    }

    public void setParticipatedEvents(ArrayList<Events> participatedEvents) {
        this.participatedEvents = participatedEvents;
    }

    // Add events to the volunteer instance instead of setting a new list every time.
    public void addEvent(Events eventsAdded) {
        if (this.participatedEvents != null) {
            this.participatedEvents.add(eventsAdded);
        }
    }

    public String getPreferredDays() {
        return preferredDays;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public void setPreferredDays(String preferredDays) {
        this.preferredDays = preferredDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Volunteer: "+ super.toString() + ", PreferredDays=" + preferredDays + ", ParticipatedEvents=" + participatedEvents;
    }

    // When adding new events, we need to update the change to the serialized .bin file
    public static void updateVolunteerInFile(Volunteer volunteer) throws IOException, ClassNotFoundException {
        ArrayList<Volunteer> volunteerList = readAndReturnArrayList();

        for (int i = 0; i < volunteerList.size(); i++) {
            if (volunteerList.get(i).getId().equals(volunteer.getId())) {
                volunteerList.set(i, volunteer);
                break;
            }
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Volunteer.bin"));
        for (Volunteer v : volunteerList) {
            oos.writeObject(v);
        }
        oos.close();
    }

}
