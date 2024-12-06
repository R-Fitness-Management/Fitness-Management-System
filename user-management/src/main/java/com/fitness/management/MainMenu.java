package com.fitness.management;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {

    private static UserService userService = new UserService();
    private static Map<String, Integer> activityData = new HashMap<>(); 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
        	try {
            System.out.println("\n=== User Management Menu ===");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Deactivate User");
            System.out.println("4. Approve Instructor");
            System.out.println("5. View User Activity Statistics");
            System.out.println("6. View All Users with Status");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    updateUser(scanner);
                    break;
                case 3:
                    deactivateUser(scanner);
                    break;
                case 4:
                    approveInstructor(scanner);
                    break;
                case 5:
                    viewUserActivity();
                    break;
                case 6:
                	viewAllUsersWithStatus();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting User Management...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        	} catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
        }
        	}

        scanner.close();
    }
    
    private static void incrementActivity(String email) {
        activityData.put(email, activityData.getOrDefault(email, 0) + 1);
    }


    private static void addUser(Scanner scanner) {
    	
        String name = getName(scanner);
        String email = getEmail(scanner);
        String role = getRole(scanner);
        String status = getStatus(scanner);

        boolean isActive = status.equalsIgnoreCase("active");
        User user = new User(name, email, role, isActive);

        if (userService.addUser(user)) {
            System.out.println("User added successfully.");
            activityData.put(email, 0); // Initialize activity data
            incrementActivity(user.getEmail());
        } else {
            System.out.println("User with this email already exists.");
        }
    }

    private static void updateUser(Scanner scanner) {
    	
        String email = getEmail(scanner);

        System.out.print("Enter new name: ");
        String name = getName(scanner);

        System.out.print("Enter new role (Instructor/Client): ");
        String role = getRole(scanner);

        if (userService.updateUser(email, name, role)) {
            System.out.println("User updated successfully.");
            incrementActivity(email);
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deactivateUser(Scanner scanner) {
        String email = getEmail(scanner);

        if (userService.detectiveUser(email)) {
            System.out.println("User deactivated successfully.");
            incrementActivity(email);
        } else {
            System.out.println("User not found.");
        }
    }

    private static void approveInstructor(Scanner scanner) {
        String email = getEmail(scanner);

        User user = userService.getUser(email);
        if (user != null && user.getRole().equalsIgnoreCase("Instructor") && !user.isActive()) {
            user.setActive(true);
            System.out.println("Instructor approved successfully.");
        } else if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println("This user is not eligible for approval.");
        }
    }

    private static void viewUserActivity() {
        System.out.println("\n=== User Activity Statistics ===");
        if (activityData.isEmpty()) {
            System.out.println("No activity data available.");
        } else {
            activityData.forEach((email, activityCount) -> {
                System.out.println("Email: " + email + ", Activity Count: " + activityCount);
            });
        }
    }

    
    
    private static void viewAllUsersWithStatus() {
        System.out.println("\n=== List of Users ===");
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : userService.getAllUsers()) {
                String status = user.isActive() ? "Active" : "Inactive";
                System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail() +
                        ", Role: " + user.getRole() + ", Status: " + status);
            }
        }
    }

    // Helper Methods for Validation
    private static String getName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (!name.trim().isEmpty() && name.matches("^[a-zA-Z\\s]+$")) {
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid name (only letters and spaces).");
            }
        }
        return name;
    }

    private static String getEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
        return email;
    }

    private static String getRole(Scanner scanner) {
        String role;
        while (true) {
            System.out.print("Enter role (Instructor/Client): ");
            role = scanner.nextLine();
            if (role.equalsIgnoreCase("Instructor") || role.equalsIgnoreCase("Client")) {
                break;
            } else {
                System.out.println("Invalid role. Please enter 'Instructor' or 'Client'.");
            }
        }
        return role;
    }

    private static String getStatus(Scanner scanner) {
        String status;
        while (true) {
            System.out.print("Enter status (active/inactive): ");
            status = scanner.nextLine();
            if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("inactive")) {
                break;
            } else {
                System.out.println("Invalid status. Please enter 'active' or 'inactive'.");
            }
        }
        return status;
    }
}
