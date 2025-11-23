import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class SimpleFitnessTracker {

    // File name where we save users so they can sign back in later
    static final String FILE = "simple_users.dat";

    // Scanner for reading user input from keyboard
    static Scanner sc = new Scanner(System.in);

    // ArrayList stores ALL users (required by your project)
    static ArrayList<FitnessUser> users = new ArrayList<>();

    public static void main(String[] args) {
        // Load old users if file exists (so returning users see history)
        load();

        System.out.println("=== Simple Fitness Tracker ===");

        
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        // Find the user if they exist, otherwise create them
        FitnessUser user = findOrCreateUser(name);

        // Greet user
        System.out.println("\nHi " + user.getName() + "!");

        // If they signed in before, show past workouts
        if (!user.getWorkouts().isEmpty()) {
            System.out.println("Your previous workouts:");
            user.showWorkouts();
        }

        // Main menu loop
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Log Exercise");
            System.out.println("2) Log Sleep");
            System.out.println("3) Log Hydration");
            System.out.println("4) Set Objective (Daily/Weekly)");
            System.out.println("5) Reminder");
            System.out.println("6) View History");
            System.out.println("7) Save & Exit");
            System.out.print("Choose: ");

            int c = readInt();  // safe integer input

            // Menu options call different UI methods
            switch (c) {
                case 1 -> logExerciseUI(user);
                case 2 -> logSleepUI(user);
                case 3 -> logHydrationUI(user);
                case 4 -> setObjectiveUI(user);
                case 5 -> user.remind();
                case 6 -> showAllUI(user);
                case 7 -> {
                    save();        // save everything before leaving
                    break;         // exit loop
                }
                default -> System.out.println("Invalid.");
            }
        }
    }


    /**
     * Finds a user by name in the ArrayList.
     * If not found, makes a new user.
     */
    static FitnessUser findOrCreateUser(String name) {
        for (FitnessUser u : users)
            if (u.getName().equalsIgnoreCase(name)) 
                return u;  // returning user

        // new user case
        FitnessUser newU = new FitnessUser(name);
        users.add(newU);  // store new user in ArrayList
        return newU;
    }

    
    static void logExerciseUI(FitnessUser user) {
        System.out.print("Workout type: ");
        String type = sc.nextLine();

        System.out.print("Minutes: ");
        int min = readInt();

        System.out.print("Calories (or -1 if unknown): ");
        int cal = readInt();

        // If calories are known, call 3-parameter version.
        // Otherwise call 2-parameter version.
        if (cal >= 0) user.logExercise(type, min, cal);
        else user.logExercise(type, min);

        System.out.println("Exercise logged!");
    }

    /**
     *  for sleep monitoring.
     */
    static void logSleepUI(FitnessUser user) {
        System.out.print("Hours slept: ");
        double hrs = Double.parseDouble(sc.nextLine());
        user.logSleep(hrs);
        System.out.println("Sleep logged!");
    }

    /**
     *  for hydration monitoring.
     */
    static void logHydrationUI(FitnessUser user) {
        System.out.print("Cups of water today: ");
        int cups = readInt();
        user.logHydration(cups);
        System.out.println("Hydration logged!");
    }

    /**
     *  for objectives.
     * Can store daily or weekly objectives for specific dates.
     */
    static void setObjectiveUI(FitnessUser user) {
        System.out.println("1) Daily Objective  2) Weekly Objective");
        int choice = readInt();

        System.out.print("Objective description: ");
        String desc = sc.nextLine();

        System.out.print("Target number: ");
        double target = Double.parseDouble(sc.nextLine());

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        // If user chooses daily vs weekly, store different text
        if (choice == 1) user.setDailyObjective(date, desc, target);
        else user.setWeeklyObjective(date, desc, target);

        System.out.println("Objective set!");
    }

    /**
     * Shows all stored data in history.
     */
    static void showAllUI(FitnessUser user) {
        System.out.println("\nWorkouts:");
        user.showWorkouts();

        System.out.println("\nSleep:");
        user.showSleep();

        System.out.println("\nHydration:");
        user.showHydration();

        System.out.println("\nObjectives:");
        user.showObjectives();
    }

    /**
     * Safe input method for integers.
     * Keeps asking until user enters a valid number.
     */
    static int readInt() {
        while (true) {
            try { 
                return Integer.parseInt(sc.nextLine()); 
            }
            catch (NumberFormatException | InputMismatchException e) { 
                System.out.print("Enter a number: "); 
            }
        }
    }



    /**
     * Save ArrayList of users to a file.
     * This is how we remember returning users.
     */
    static void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(users);
            System.out.println("Saved!");
        } catch (Exception e) {
            System.out.println("Save failed.");
        }
    }

    /**
     * Load ArrayList of users from file if it exists.
     */
    @SuppressWarnings("unchecked")
    static void load() {
        File f = new File(FILE);
        if (!f.exists()) return;  // no saved users yet

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            users = (ArrayList<FitnessUser>) in.readObject();
        } catch (Exception e) {
            users = new ArrayList<>();  // if load fails, start fresh
        }
    }
}


abstract class User implements Serializable {

    // private field = ENCAPSULATION / data protection
    private String name;

    public User(String name){ 
        this.name = name; 
    }

    // getter/setter = controlled access to private data
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    // abstract method must be implemented by child class
    public abstract void displayInfo();
}


class FitnessUser extends User {

    // ArrayLists required to store data
    private ArrayList<String> workouts = new ArrayList<>();
    private final ArrayList<String> sleepLogs = new ArrayList<>();
    private final ArrayList<String> hydrationLogs = new ArrayList<>();
    private final ArrayList<String> objectives = new ArrayList<>();

    public FitnessUser(String name){ 
        super(name);  // calls User constructor
    }

    @Override
    public void displayInfo(){ 
        System.out.println("User: " + getName()); 
    }


    // Version 1: no calories
    public void logExercise(String type, int minutes) {
        workouts.add(LocalDate.now() + " - " + type + " (" + minutes + " min)");
    }

    // Version 2: with calories
    public void logExercise(String type, int minutes, int calories) {
        workouts.add(LocalDate.now() + " - " + type + 
                     " (" + minutes + " min, " + calories + " cal)");
    }

    /* -------- monitor sleep/hydration -------- */

    public void logSleep(double hours){
        sleepLogs.add(LocalDate.now() + " - " + hours + " hrs");
    }

    public void logHydration(int cups){
        hydrationLogs.add(LocalDate.now() + " - " + cups + " cups");
    }

    /* -------- objectives for days/weeks -------- */

    public void setDailyObjective(LocalDate day, String desc, double target){
        objectives.add("[Daily " + day + "] " + desc + " target=" + target);
    }

    public void setWeeklyObjective(LocalDate weekStart, String desc, double target){
        objectives.add("[Weekly " + weekStart + "] " + desc + " target=" + target);
    }

  
    public void remind(){
        System.out.println("\nREMINDERS:");

        if (workouts.isEmpty()) 
            System.out.println("- Log a workout today!");

        if (sleepLogs.isEmpty()) 
            System.out.println("- Track your sleep tonight.");

        if (hydrationLogs.isEmpty()) 
            System.out.println("- Drink water and log it.");

        // remind user about objectives for today's date
        LocalDate today = LocalDate.now();
        for (String obj : objectives)
            if (obj.contains(today.toString()))
                System.out.println("- Today’s objective: " + obj);
    }

    /* -------- show history -------- */
    public void showWorkouts(){ printList(workouts); }
    public void showSleep(){ printList(sleepLogs); }
    public void showHydration(){ printList(hydrationLogs); }
    public void showObjectives(){ printList(objectives); }

    // helper to print ArrayList nicely
    private void printList(ArrayList<String> list){
        if (list.isEmpty()) { 
            System.out.println("  (none)"); 
            return; 
        }
        for (String s : list) System.out.println("  " + s);
    }

    /* -------- getters/setters (data protection) -------- */
    public ArrayList<String> getWorkouts(){ return workouts; }
    public void setWorkouts(ArrayList<String> workouts){ this.workouts = workouts; }
}

