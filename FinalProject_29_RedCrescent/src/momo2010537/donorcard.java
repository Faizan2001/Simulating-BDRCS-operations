/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package momo2010537;

/**
 *
 * @author User
 */
public class donorcard {
    
    private String name;
    private String id;
    private String address;
    private String contact;

    public donorcard() {
    }

    public donorcard(String name, String id, String address, String contact) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.contact = contact;
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

    @Override
    public String toString() {
        return "donorcard{" + "name=" + name + ", id=" + id + ", address=" + address + ", contact=" + contact + '}';
    }
    
    
    
    
}
