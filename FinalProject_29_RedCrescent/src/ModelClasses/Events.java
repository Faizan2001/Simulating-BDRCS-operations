/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelClasses;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author faiza
 */
public class Events implements Serializable {

    private Integer eventId;
    private String eventType, eventTime, eventDescription, eventLocation;
    private LocalDate eventDate;
    private Integer volunteerList;
    private ArrayList<Volunteer> participatingVolunteers;
    private static ArrayList<Events> eventsArrayList;

    // eventsArrayList is a static field for all Event objects that stores a list of all the events there are, and stores as an Array inside each Event object. 
    // This was primarily done so that the list of events can be updated and fetched easily using the eventsArrayList field.
    public Events(Integer eventId, String eventType, String eventTime, String eventDescription, LocalDate eventDate, String eventLocation, Integer volunteerList, ArrayList<Volunteer> participatingVolunteers) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.volunteerList = 0;
        this.participatingVolunteers = new ArrayList<>();
        eventsArrayList = new ArrayList<>(); // Initialize eventsArrayList because we need to access the events Array List in VounteerSceneController when we click 'Cancel
                                                                    //   participation, but due to no eventsArrayList being initialized at that point, likely get NullPointerException. Hence, 
                                                                    //   we do the initialization in the constructor. We open the Events.bin file and load events into the eventsArrayList.

        // Load events from file and add them to eventsArrayList
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Events.bin"))) {
            while (true) {
                try {
                    Events event = (Events) ois.readObject();
                    eventsArrayList.add(event);
                } catch (EOFException e) {
                    break; // End of file
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading Events from file: " + e.getMessage());
        }

    }

    public ArrayList<Volunteer> getParticipatingVolunteers() {
        return participatingVolunteers;
    }

    public void setParticipatingVolunteers(ArrayList<Volunteer> participatingVolunteers) {
        this.participatingVolunteers = participatingVolunteers;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getVolunteerList() {
        return volunteerList;
    }

    public void setVolunteerList(Integer volunteerList) {
        this.volunteerList = volunteerList;
    }

 

    public static void setEventsArrayList(ArrayList<Events> eventsList) {
        eventsArrayList = eventsList;
    }
    
       public void addToVolunteerListSize() {
        this.volunteerList++;
    }
    
     // Add events to the volunteer instance and update the file
    public void addVolunteerToList(Volunteer volunteerAdded) {
     
            this.participatingVolunteers.add(volunteerAdded);
            addToVolunteerListSize(); // Increment the volunteer count
            updateEventsFile(); // Update the Events file
        
    }

    // Update eventsArrayist in every Object
    public void updateEventsFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Events.bin"))) {
            for (Events event : eventsArrayList) {
                oos.writeObject(event);
            }
        } catch (IOException e) {
            System.out.println("Error updating Events file: " + e.getMessage());
        }
    }

}
