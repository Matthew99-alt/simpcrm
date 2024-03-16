package com.crm.example.model;

public class User {
    private final String firstName;
    private final String secondName;
    private final String middleName;
    private final int password;
    private final String email;
    private final int phone;
    private final String address;
    //avatar?
    public User(String firstName, String secondName,
                String middleName, int password,
                String email, int phone,
                String address){
        this.firstName=firstName;
        this.secondName=secondName;
        this.middleName=middleName;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }

    public int getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }
}
