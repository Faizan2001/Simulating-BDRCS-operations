/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package momo2010537;

/**
 *
 * @author User
 */
public class donorprofile {
    
    private String name;
    private String id;
    private String address;
    private String contact;
    private String bloodgroup;
    private String bio;
    private String date;
    private String male;
    private String female; 

    public donorprofile() {
    }

    public donorprofile(String name, String id, String address, String contact, String bloodgroup, String bio, String date, String male, String female) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.contact = contact;
        this.bloodgroup = bloodgroup;
        this.bio = bio;
        this.date = date;
        this.male = male;
        this.female = female;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    @Override
    public String toString() {
        return "donorprofile{" + "name=" + name + ", id=" + id + ", address=" + address + ", contact=" + contact + ", bloodgroup=" + bloodgroup + ", bio=" + bio + ", date=" + date + ", male=" + male + ", female=" + female + '}';
    }
    
    
    
}
