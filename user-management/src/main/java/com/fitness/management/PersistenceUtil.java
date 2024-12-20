package com.fitness.management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUtil {

    private static final String USERS_FILE = "users_data.txt";
    private static final String PROGRAMS_FILE = "programs.dat";

    
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
    public static void saveProgramData(List<Program> programs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PROGRAMS_FILE))) {
            oos.writeObject(programs);
           // System.out.println("Programs Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving programs: " + e.getMessage());
        }
    }

   
    @SuppressWarnings("unchecked")
    public static List<Program> loadProgramData() {
        File file = new File(PROGRAMS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Program>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading programs: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    
    public static void saveClientProfileData(List<Profile> profiles) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("client_profiles.dat"))) {
            oos.writeObject(profiles);
            System.out.println("Client profiles saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving client profiles: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public static List<Profile> loadClientProfileDataList() {
        File file = new File("client_profiles.dat");
        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject(); // Read the object
            if (obj instanceof List) { // Ensure it is a List
                return (List<Profile>) obj; // Cast safely after checking
            } else {
                System.out.println("Error: Invalid data format in file.");
                return new ArrayList<>(); // Return an empty list if data is invalid
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading client profiles: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an exception
        }
    }


    public static void deleteClientProfileData() {
        File file = new File("client_profile.dat");
        if (file.exists()) {
            file.delete();
        }
    }



}
