package com.fitness.management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUtil {

    private static final String USERS_FILE = "users_data.txt";

    
    public static void saveUserData(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                String status = user.isActive() ? "Active" : "Inactive";
                writer.write(user.getName() + "," + user.getEmail() + "," + user.getRole() + "," + status);
                writer.newLine();
            }
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

   
    public static List<User> loadUserData() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String email = parts[1];
                    String role = parts[2];
                    boolean isActive = parts[3].equalsIgnoreCase("Active");
                    users.add(new User(name, email, role, isActive));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Initializing empty data.");
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
        return users;
    }
}
