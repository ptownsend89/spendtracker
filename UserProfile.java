package com.company;

import java.util.Scanner;

public class UserProfile {
    Scanner scan = new Scanner(System.in);
    private String username;
    private String password;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        System.out.println("Username "+username+ " logged in");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void createUser(String username, String password){
        System.out.println("Create new user. Enter username: ");
        setUsername(username);
        System.out.println("Username "+ username + " created");
        setPassword(password);
        System.out.println("Password "+ encryptPassword(password) + " created.");
    }
    public void verifyUser(String username, String password){
        if (username.equals(this.username) && password.equals(this.password)){
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication unsuccessful");
        }
    }
    private String encryptPassword(String password){
        String encryptedPword = "";
        for (int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            encryptedPword = encryptedPword + "*";

        }
        return encryptedPword;
    }
}
