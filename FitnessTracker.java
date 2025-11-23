import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class FitnesseLogin {

    static final String FILE = "workout_users.dat";  // file to save users
    static Scanner sc = new Scanner(System.in);

    // Stores all users (required ArrayList usage)
    static ArrayList<FitnessUser> users = new ArrayList<>();

    public static void main(String[] args) {
        loadUsers();  // load returning users

        System.out.println("=== Workout Only Fitness Tracker ===");

        // Ask for name
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        // Find user if they exist, or create new one
        FitnessUser user = findOrCreateUser(name);

        System.out.println("\nHi " + user.getName() + "!");

        // Show previous workouts if user signed in before
        if (!user.getWorkouts().isEmpty()) {
            System.out.println("Your previous workouts:");
            user.showWorkouts();
        } else {
            System.out.println("No workouts yet. Log your first one!");
        }

        // Simple menu loop
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Log Workout");
            System.out.println("2) View Workout History");
            System.out.println("3) Save & Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            if (choice == 1) logWorkoutUI(user);
            else if (choice == 2) user.showWorkouts();
            else if (choice == 3) {
                saveUsers();
                System.out.println("Saved! Bye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }



    // Find existing user or create a new one
    static FitnessUser findOrCreateUser(String name) {
        for (FitnessUser u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                System.out.println("Welcome back!");
                return u;
            }
        }

        // New user
        FitnessUser newUser = new FitnessUser(name);
        users.add(newUser);
        System.out.println("New account created!");
        return newUser;
    }


    static void logWorkoutUI(FitnessUser user) {
        System.out.print("Workout type (Run/Lift/etc): ");
        String type = sc.nextLine();

        System.out.print("Minutes: ");
        int minutes = readInt();

        System.out.print("Calories (or -1 if unknown): ");
        int calories = readInt();

   
        if (calories >= 0) user.logExercise(type, minutes, calories);
        else user.logExercise(type, minutes);

        System.out.println("Workout logged!");
    }

    // Safe integer input
    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    /* ---------------- SAVE / LOAD ---------------- */

    // Save users ArrayList to file
    static void saveUsers() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(users);
        } catch (Exception e) {
            System.out.println("Save failed.");
        }
    }

    // Load users ArrayList from file
    @SuppressWarnings("unchecked")
    static void loadUsers() {
        File f = new File(FILE);
        if (!f.exists()) return;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE))) {
            users = (ArrayList<FitnessUser>) in.readObject();
        } catch (Exception e) {
            users = new ArrayList<>(); // start fresh if load fails
        }
    }
}

/* =========================================================
   ABSTRACT USER CLASS (required)
   ========================================================= */
abstract class User implements Serializable {

    private String name;  // private for data protection

    public User(String name) { this.name = name; }

    // Getter and setter (encapsulation)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Abstract method child must implement
    public abstract void displayInfo();
}

/* =========================================================
   FITNESS USER CLASS (stores workouts only)
   ========================================================= */
class FitnessUser extends User {

    // ArrayList required to store workout data
    private ArrayList<String> workouts = new ArrayList<>();

    public FitnessUser(String name) { super(name); }

    @Override
    public void displayInfo() {
        System.out.println("User: " + getName());
    }

    /* -------- Overloaded workout logging --------
       Two methods with same name but different parameters.
       (This is your required OVERLOADING)
    */

    // Version 1: workout without calories
    public void logExercise(String type, int minutes) {
        workouts.add(LocalDate.now() + " - " + type + " (" + minutes + " min)");
    }

    // Version 2: workout with calories
    public void logExercise(String type, int minutes, int calories) {
        workouts.add(LocalDate.now() + " - " + type +
                " (" + minutes + " min, " + calories + " cal)");
    }

    // Show workout history
    public void showWorkouts() {
        if (workouts.isEmpty()) {
            System.out.println("(none)");
            return;
        }
        for (String w : workouts) System.out.println(w);
    }

    // Getter/setter for workout list (data protection)
    public ArrayList<String> getWorkouts() { return workouts; }
    public void setWorkouts(ArrayList<String> workouts) { this.workouts = workouts; }
}

