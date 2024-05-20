package ModelClasses;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author faiza
 */
public abstract class User implements Serializable {
    String name, email, password, location;
    Integer id, age;

    public User(String name, String email, String password, String location, Integer id, Integer age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    

    @Override
    public String toString() {
        return "Name =" + name + ", Email=" + email + ", Password=" + password + ", Location=" + location + ", ID=" + id + ", Age=" + age + "";
    }

   
    
    
    
}
