/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.model;

import admin.controller.DataValidException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class employee {
    private int id;
    private String nameTK;
    private String password;
    private String name;
    private String dob;
    private String address;
    private String phone;
    private String gender;

    public employee(int id,String nameTK, String password, String name, 
            String dob, String address, String phone, String gender) {
        this.id = id;
        this.nameTK = nameTK;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public employee(String nameTK, String password, String name, String dob, String address, String phone, String gender) {
        this.nameTK = nameTK;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
    

    public employee(String nameTK, String password) {
        this.nameTK = nameTK;
        this.password = password;
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTK() {
        return nameTK;
    }

    public void setNameTK(String nameTK) {
        this.nameTK = nameTK;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws InValidPhoneException {
        DataValidException stvalid = new DataValidException();
        if(stvalid.invalidPhone(phone)){
        this.phone = phone;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final employee other = (employee) obj;
        if (!Objects.equals(this.nameTK.toLowerCase(), other.nameTK.toLowerCase())) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return super.toString() + 
                "Employee{" + "nameTK=" + nameTK + ", password=" + password + '}';
    }
    
    
    
    
}
