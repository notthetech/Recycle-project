import java.io.*;
import java.time.LocalDate;
import java.util.*;

enum TaskStatus
{
	NOT_STARTED,
	IN_PROGRESS,
	COMPLETED;
}

enum ProgressLevel
{
	BEGINNER,
	INTERMEDIATE,
	ADVANCED;
}

enum WorkoutType
{
	CARDIO,
	PYLOMETRIC,
	WEIGHT,
	BODYWEIGHT,
	OTHER;
}

class Main //FitnesseLogin just add new data or file 
{

	static final String FILE = "workout_users.dat";
	static Scanner sc = new Scanner(System.in);

	static ArrayList<FitnessUser> users = new ArrayList<>();

	public static void main(String[] args)
	{
		loadUsers();

		System.out.println("~~~~FITNESS TIME~~~~");

		System.out.print("Enter your name please: ");
		String name = sc.nextLine().trim();

		FitnessUser user = findOrCreateUser(name);

		System.out.println("\nWazzup " + user.getName() + "!");
		user.displayInfo();

		while (true)
		{
			System.out.println("\n--- MENU ---");
			System.out.println("1) Log Workout");
			System.out.println("2) View Workout History");
			System.out.println("3) Save & Exit");
			System.out.print("Choose 1-3: ");

			int choice = readInt();

			if (choice == 1) logWorkoutUI(user);
			else if (choice == 2) user.showWorkouts();
			else if (choice == 3)
			{
				saveUsers();
				System.out.println("Saved! Bye!");
				break;
			}
			else
			{
				System.out.println("Invalid choice.");
			}
		}
	}

	static FitnessUser findOrCreateUser(String name)
	{
		for (FitnessUser u : users)
		{
			if (u.getName().equalsIgnoreCase(name))
			{
				System.out.println("Welcome back!");
				return u;
			}
		}

		System.out.print("Enter your goal: ");
		String goal = sc.nextLine();

		FitnessUser newUser = new FitnessUser(name, goal);
		users.add(newUser);
		System.out.println("New account created!");
		return newUser;
	}

	static void logWorkoutUI(FitnessUser user)
	{
		System.out.print("Workout type (CARDIO/WEIGHT/BODYWEIGHT/PYLOMETRIC/OTHER): ");
		String input = sc.nextLine().toUpperCase();

		WorkoutType type;

		try
		{
			type = WorkoutType.valueOf(input);
		}
		catch (Exception e)
		{
			type = WorkoutType.OTHER;
		}

		System.out.print("Minutes: ");
		int minutes = readInt();

		System.out.print("Calories (or -1 if unknown): ");
		int calories = readInt();

		if (calories >= 0)
			user.logWorkout(type, minutes, calories);
		else
			user.logWorkout(type, minutes);

		System.out.println("Workout logged!");
	}

	static int readInt()
	{
		while (true)
		{
			try
			{
				return Integer.parseInt(sc.nextLine());
			}
			catch (Exception e)
			{
				System.out.print("Enter a valid number: ");
			}
		}
	}

	static void saveUsers() //fancy file handling
	{
		try (ObjectOutputStream out =
		             new ObjectOutputStream(new FileOutputStream(FILE)))
		{
			out.writeObject(users);
		}
		catch (Exception e)
		{
			System.out.println("Save failed.");
		}
	}

	@SuppressWarnings("unchecked")//ignore
	static void loadUsers()
	{
		File f = new File(FILE);
		if (!f.exists()) return; //no file no run this code

		try (ObjectInputStream in =
		             new ObjectInputStream(new FileInputStream(FILE)))
		{
			users = (ArrayList<FitnessUser>) in.readObject();
		}
		catch (Exception e)
		{
			users = new ArrayList<>(); 
		}
	}
}

abstract class User implements Serializable //basic User data
{
	private String name;
	private String goal;
	private ProgressLevel progress;

	public User(String name, String goal)
	{
		this.name = name;
		this.goal = goal;
		this.progress = ProgressLevel.BEGINNER;
	}

	public String getName()
	{
		return name;
	}

	public String getGoal()
	{
		return goal;
	}

	public ProgressLevel getProgress()
	{
		return progress;
	}

	public void setProgress(ProgressLevel progress)
	{
		this.progress = progress;
	}

	public abstract void displayInfo();
}

class Workout implements Serializable //WORKOUTS and time 
{
	private WorkoutType type;
	private int minutes;
	private int calories;//added every getter and setter just in case
	private LocalDate date;

	public Workout(WorkoutType type, int minutes, int calories)
	{
		this.type = type;
		this.minutes = minutes;
		this.calories = calories;
		this.date = LocalDate.now();
	}

	public int getMinutes() 
	{
		return minutes;
	}
	
	public void setMinutes(int minutes)
	{
	    this.minues = minutes;
	}
	
	public int getCalories() 
	{
		return calories;
	}
	
	public void setCalories(int calories)
	{
	    this.calories = calories;
	}
	
	public WorkoutType getType()
	{
	    return this.type;
	}
	
	public void setType(WorkoutType type)
	{
	    this.type = type;
	}
	
	public LocalDate getDate() //maybe needed added just in case
	{
	    return this.date;
	}
	
	public void setDate(LocalDate date)
	{
	    this.date = date;
	}

	@Override
	public String toString()
	{
		return date + " - " + type + " (" + minutes + " min, " + calories + " cal)";
	}
}

class FitnessUser extends User
{
	private ArrayList<Workout> workouts = new ArrayList<>();

	public FitnessUser(String name, String goal)
	{
		super(name, goal);//call USER
	}

	@Override//because display is overridden
	public void displayInfo()
	{
		System.out.println("User: " + getName());
		System.out.println("Goal: " + getGoal());
		System.out.println("Level: " + getProgress());
	}

	public void logWorkout(WorkoutType type, int minutes)
	{
		workouts.add(new Workout(type, minutes, 0));
	}

	public void logWorkout(WorkoutType type, int minutes, int calories)
	{
		workouts.add(new Workout(type, minutes, calories));
	}

	public void showWorkouts()
	{
		if (workouts.isEmpty())
		{
			System.out.println("(none)");
			return;
		}

		for (Workout w : workouts)
			System.out.println(w);
	}

	public int totalMinutes()
	{
		int total = 0;

		for (Workout w : workouts)
			total += w.getMinutes();

		return total;
	}

	public ArrayList<Workout> getWorkouts()
	{
		return workouts;
	}
}
