package com.fitness.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private static UserService userService = new UserService();
    private static ProgramService programService = new ProgramService();
    private static ClientProfileService clientProfileService = new ClientProfileService();
    private static Map<String, Integer> activityData = new HashMap<>();
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Load data
        List<User> loadedUsers = PersistenceUtil.loadUserData();
        loadedUsers.forEach(userService::addUser);

        List<Program> loadedPrograms = PersistenceUtil.loadProgramData();
        loadedPrograms.forEach(programService::addProgram);

        while (!exit) {
            try {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Admin Menu");
                System.out.println("2. Instructor Menu");
                System.out.println("3. Client Menu");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        manageAdminMenu(scanner);
                        break;
                    case 2:
                        manageInstructorMenu(scanner);
                        break;
                    case 3:
                        manageClientMenu(scanner);
                        break;
                    case 4:
                        saveAllData();
                        System.out.println("Exiting the system...");
                        exit = true;
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

    // Admin Menu
    private static void manageAdminMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. User Management");
            System.out.println("2. Program Monitoring");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageUserAccountsMenu(scanner); // Separate method for user management
                    break;
                case 2:
                    manageProgramMonitoringMenu(scanner); // Separate method for program monitoring
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // User Management Menu (For Admin)
    private static void manageUserAccountsMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== User Management Menu ===");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Deactivate User");
            System.out.println("4. Approve Instructor");
            System.out.println("5. View User Activity Statistics");
            System.out.println("6. View All Users with Status");
            System.out.println("7. Back to Admin Menu");
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
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Program Monitoring Menu (For Admin)
    private static void manageProgramMonitoringMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Program Monitoring Menu ===");
            System.out.println("1. View Most Popular Programs by Enrollment");
            System.out.println("2. Generate Revenue Report");
            System.out.println("3. Generate Attendance Report");
            System.out.println("4. Generate Client Progress Report");
            System.out.println("5. View Active and Completed Programs");
            System.out.println("6. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMostPopularPrograms();
                    break;
                case 2:
                    generateRevenueReport();
                    break;
                case 3:
                    generateAttendanceReport();
                    break;
                case 4:
                    generateClientProgressReport();
                    break;
                case 5:
                    viewActiveAndCompletedPrograms();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Instructor Menu
    private static void manageInstructorMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Instructor Menu ===");
            System.out.println("1. Create Program");
            System.out.println("2. Update Program");
            System.out.println("3. Delete Program");
            System.out.println("4. View All Programs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProgram(scanner);
                    break;
                case 2:
                    updateProgram(scanner);
                    break;
                case 3:
                    deleteProgram(scanner);
                    break;
                case 4:
                    viewAllPrograms();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Client Menu
    private static void manageClientMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Client Menu ===");
            System.out.println("1. Manage Client Profile");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageClientProfile(scanner); // Handles client profiles
                    break;
                case 2:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
  }
    


    private static void manageClientProfile(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Manage Client Profile Menu ===");
            System.out.println("1. Create Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Update Profile");
            System.out.println("4. Delete Profile");
            System.out.println("5. Back to Client Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createClientProfile(scanner);
                    break;
                case 2:
                    viewClientProfile(scanner);
                    break;
                case 3:
                    updateClientProfile(scanner);
                    break;
                case 4:
                    deleteClientProfile(scanner);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    

    

	private static void saveAllData() {
        PersistenceUtil.saveUserData(new ArrayList<>(userService.getAllUsers()));
        PersistenceUtil.saveProgramData(new ArrayList<>(programService.getAllPrograms()));
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
            activityData.put(email, 0); 
            incrementActivity(user.getEmail());
        } else {
            System.out.println("User with this email already exists.");
        }
    }
    private static void updateUser(Scanner scanner) {
        String email = getEmail(scanner);

        
        if (userService.getUser(email) == null) {
            System.out.println("User not found.");
            return; 
        }

        String name = getName(scanner);
        String role = getRole(scanner);

        if (userService.updateUser(email, name, role)) {
            System.out.println("User updated successfully.");
            incrementActivity(email);
        } else {
            System.out.println("Failed to update user."); 
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


    // Program Management Methods
    private static void createProgram(Scanner scanner) {
        System.out.print("Enter Program Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Duration (e.g., 6 weeks): ");
        String duration = scanner.nextLine();

        System.out.print("Enter Difficulty Level (e.g., Beginner/Intermediate): ");
        String difficulty = scanner.nextLine();

        System.out.print("Enter Goals: ");
        String goals = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Schedule (e.g., Online/In-Person): ");
        String schedule = scanner.nextLine();

        System.out.print("Enter Video Tutorials (comma-separated): ");
        List<String> videos = List.of(scanner.nextLine().split(","));

        System.out.print("Enter Documents (comma-separated): ");
        List<String> documents = List.of(scanner.nextLine().split(","));

        Program program = new Program(title, duration, difficulty, goals, price, schedule, videos, documents);
        if (programService.addProgram(program)) {
            PersistenceUtil.saveProgramData(new ArrayList<>(programService.getAllPrograms())); // Save programs
            System.out.println("Program created successfully.");
        } else {
            System.out.println("Failed to create program. Program with this title may already exist.");
        }
    }

    private static void updateProgram(Scanner scanner) {
        System.out.print("Enter Program Title to Update: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Duration: ");
        String duration = scanner.nextLine();

        System.out.print("Enter New Difficulty Level: ");
        String difficulty = scanner.nextLine();

        System.out.print("Enter New Goals: ");
        String goals = scanner.nextLine();

        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter New Schedule: ");
        String schedule = scanner.nextLine();

        System.out.print("Enter New Video Tutorials (comma-separated): ");
        List<String> videos = List.of(scanner.nextLine().split(","));

        System.out.print("Enter New Documents (comma-separated): ");
        List<String> documents = List.of(scanner.nextLine().split(","));

        if (programService.updateProgram(title, duration, difficulty, goals, price, schedule, videos, documents)) {
            PersistenceUtil.saveProgramData(new ArrayList<>(programService.getAllPrograms())); 
            System.out.println("Program updated successfully.");
        } else {
            System.out.println("Failed to update program. Program may not exist.");
        }
    }

    private static void deleteProgram(Scanner scanner) {
        System.out.print("Enter Program Title to Delete: ");
        String title = scanner.nextLine();

        if (programService.deleteProgram(title)) {
            PersistenceUtil.saveProgramData(new ArrayList<>(programService.getAllPrograms())); 
            System.out.println("Program deleted successfully.");
        } else {
            System.out.println("Failed to delete program. Program may not exist.");
        }
    }

    private static void viewAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        if (programs.isEmpty()) {
            System.out.println("No programs found.");
        } else {
            for (Program program : programs) {
                System.out.println(program);
            }
        }
    }
    private static void viewMostPopularPrograms() {
        List<Program> popularPrograms = programService.getMostPopularPrograms(); // Add this method to ProgramService
        if (popularPrograms.isEmpty()) {
            System.out.println("No programs found.");
        } else {
            popularPrograms.forEach(program -> System.out.println("Title: " + program.getTitle()));
        }
    }
    
    private static void generateRevenueReport() {
        Map<String, Double> revenueReport = programService.generateRevenueReport(); // Add this method to ProgramService
        if (revenueReport.isEmpty()) {
            System.out.println("No revenue data found.");
        } else {
            revenueReport.forEach((title, revenue) -> System.out.println("Program: " + title + ", Revenue: $" + revenue));
        }
    }
    
    private static void generateAttendanceReport() {
        Map<String, Integer> attendanceReport = programService.generateAttendanceReport(); // Add this method to ProgramService
        if (attendanceReport.isEmpty()) {
            System.out.println("No attendance data found.");
        } else {
            attendanceReport.forEach((title, attendance) -> System.out.println("Program: " + title + ", Attendance: " + attendance));
        }
    }
    
    private static void generateClientProgressReport() {
        Map<String, String> clientProgressReport = programService.generateClientProgressReport(); // Add this method to ProgramService
        if (clientProgressReport.isEmpty()) {
            System.out.println("No client progress data found.");
        } else {
            clientProgressReport.forEach((title, progress) -> System.out.println("Program: " + title + ", Progress: " + progress));
        }
    }
    
    private static void viewActiveAndCompletedPrograms() {
        Map<String, List<Program>> categorizedPrograms = programService.getActiveAndCompletedPrograms(); // Add this method to ProgramService
        if (categorizedPrograms.isEmpty()) {
            System.out.println("No programs found.");
        } else {
            System.out.println("Active Programs:");
            categorizedPrograms.getOrDefault("active", new ArrayList<>())
                               .forEach(program -> System.out.println(" - " + program.getTitle()));
            System.out.println("Completed Programs:");
            categorizedPrograms.getOrDefault("completed", new ArrayList<>())
                               .forEach(program -> System.out.println(" - " + program.getTitle()));
        }
    }
    
    private static void createClientProfile(Scanner scanner) {
        System.out.println("\n=== Create Client Profile ===");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Fitness Goals: ");
        String fitnessGoals = scanner.nextLine();

        System.out.print("Enter Dietary Preferences: ");
        String dietaryPreferences = scanner.nextLine();

        System.out.print("Enter Dietary Restrictions: ");
        String dietaryRestrictions = scanner.nextLine();

      
      

        Profile createdProfile = new Profile(name, age, email, fitnessGoals, dietaryPreferences, dietaryRestrictions);
        
        clientProfileService.createProfile(createdProfile, userService);
      //System.out.println("Profile created successfully:\n " + createdProfile);
    }



    private static void viewClientProfile(Scanner scanner) {
        System.out.println("\n=== View Client Profile ===");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Profile profile = clientProfileService.viewProfile(email);

        if (profile == null) {
            System.out.println("No profile found for the given email.");
        } else {
            System.out.println("Name: " + profile.getName());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Fitness Goals: " + profile.getFitnessGoals());
            System.out.println("Dietary Preferences: " + profile.getDietaryPreferences());
            System.out.println("Dietary Restrictions: " + profile.getDietaryRestrictions());
        }
    }



    private static void updateClientProfile(Scanner scanner) {
        System.out.println("\n=== Update Client Profile ===");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Profile profile = clientProfileService.viewProfile(email);
        if (profile == null) {
            System.out.println("No profile found for the given email.");
        } else {
            profile.setName(getUpdatedInput(scanner, "Name", profile.getName()));

            // Handle numeric input with validation
            while (true) {
                System.out.print("Enter New Age (" + profile.getAge() + "): ");
                String ageInput = scanner.nextLine();

                // Validate if the input is not blank and a valid number
                if (!ageInput.trim().isEmpty()) {
                    try {
                        int age = Integer.parseInt(ageInput);
                        profile.setAge(age);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for age.");
                    }
                } else {
                    System.out.println("Age cannot be blank. Please enter a valid number.");
                }
            }


            profile.setEmail(getUpdatedInput(scanner, "Email", profile.getEmail()));
            profile.setFitnessGoals(getUpdatedInput(scanner, "Fitness Goals", profile.getFitnessGoals()));
            profile.setDietaryPreferences(getUpdatedInput(scanner, "Dietary Preferences", profile.getDietaryPreferences()));
            profile.setDietaryRestrictions(getUpdatedInput(scanner, "Dietary Restrictions", profile.getDietaryRestrictions()));

            clientProfileService.updateProfile(
                profile.getName(),
                profile.getAge(),
                profile.getEmail(),
                profile.getFitnessGoals(),
                profile.getDietaryPreferences(),
                profile.getDietaryRestrictions(),
                userService
            );

            System.out.println("Profile updated successfully.");
        }
    }



    

	private static void deleteClientProfile(Scanner scanner) {
        System.out.println("\n=== Delete Client Profile ===");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Pass the email to the viewProfile method
        Profile profile = clientProfileService.viewProfile(email);

        if (profile == null) {
            System.out.println("No profile found.");
        } else {
            clientProfileService.deleteProfile(userService); // Pass UserService here
            System.out.println("Profile deleted successfully.");
        }
    }



	private static String getUpdatedInput(Scanner scanner, String field, String currentValue) {
        System.out.print("Enter New " + field + " (" + currentValue + "): ");
        return scanner.nextLine();
    }

}