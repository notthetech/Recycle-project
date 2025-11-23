import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FitnesseLogin {
    static final String FILE="workout_users.dat";
    static Scanner sc=new Scanner(System.in);
    static ArrayList<FitnessUser> users=new ArrayList<>();

    public static void main(String[] args){
        load();
        System.out.print("=== FitTrack ===\nEnter your name: ");
        FitnessUser user=getUser(sc.nextLine().trim());

        System.out.println("\nHi "+user.getName()+"!");
        if(!user.getWorkouts().isEmpty()) user.showWorkouts();
        else System.out.println("No workouts yet.");

        while(true){
            System.out.print("\n1)Log 2)History 3)Update 4)Save&Exit : ");
            int c=readInt();
            if(c==1) logUI(user);
            else if(c==2) user.showWorkouts();
            else if(c==3) updateUI(user);
            else if(c==4){ save(); System.out.println("Saved. Bye!"); break; }
            else System.out.println("Invalid.");
        }
    }

    static FitnessUser getUser(String name){
        for(FitnessUser u:users) if(u.getName().equalsIgnoreCase(name)) return u;
        FitnessUser nu=new FitnessUser(name); users.add(nu); return nu;
    }

    static void logUI(FitnessUser u){
        System.out.print("Type: "); String type=sc.nextLine();
        System.out.print("Minutes: "); int min=readInt();
        System.out.print("Calories (-1 unknown): "); int cal=readInt();
        if(cal>=0) u.logExercise(type,min,cal); else u.logExercise(type,min);
    }

    static void updateUI(FitnessUser u){
        if(u.getWorkouts().isEmpty()){ System.out.println("No workouts."); return; }
        u.showWorkouts();
        System.out.print("Workout ID: "); int id=readInt();
        if(id<0||id>=u.getWorkouts().size()){ System.out.println("Bad ID."); return; }

        System.out.print("Status 1)PLANNED 2)IN_PROGRESS 3)COMPLETED 4)SKIPPED: ");
        int s=readInt();
        Status st = switch(s){
            case 1->Status.PLANNED; case 2->Status.IN_PROGRESS;
            case 3->Status.COMPLETED; case 4->Status.SKIPPED;
            default->{ System.out.println("Bad status."); yield null; }
        };
        if(st==null) return;

        System.out.print("Progress (0-100): "); int p=readInt();
        if(p<0||p>100){ System.out.println("Bad progress."); return; }
        u.updateWorkout(id,st,p);
    }

    static int readInt(){
        while(true) try{ return Integer.parseInt(sc.nextLine()); }
        catch(Exception e){ System.out.print("Enter number: "); }
    }

    static void save(){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(FILE))){
            out.writeObject(users);
        }catch(Exception e){ System.out.println("Save failed."); }
    }
    @SuppressWarnings("unchecked")
    static void load(){
        File f=new File(FILE); if(!f.exists()) return;
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(FILE))){
            users=(ArrayList<FitnessUser>)in.readObject();
        }catch(Exception e){ users=new ArrayList<>(); }
    }
}

enum Status{ PLANNED, IN_PROGRESS, COMPLETED, SKIPPED }

abstract class User implements Serializable{
    private String name;
    public User(String n){ name=n; }
    public String getName(){ return name; }
    public void setName(String n){ name=n; }
    public abstract void displayInfo();
}

class FitnessUser extends User{
    private ArrayList<WorkoutTask> workouts=new ArrayList<>();
    public FitnessUser(String n){ super(n); }
    public void displayInfo(){ System.out.println(getName()); }

    // overloading
    public void logExercise(String t,int m){ workouts.add(new WorkoutTask(t,m,-1)); }
    public void logExercise(String t,int m,int c){ workouts.add(new WorkoutTask(t,m,c)); }

    public void updateWorkout(int i,Status s,int p){
        workouts.get(i).setStatus(s); workouts.get(i).setProgress(p);
    }
    public void showWorkouts(){
        if(workouts.isEmpty()){ System.out.println("(none)"); return; }
        for(int i=0;i<workouts.size();i++) System.out.println(i+") "+workouts.get(i));
    }
    public ArrayList<WorkoutTask> getWorkouts(){ return workouts; }
}

class WorkoutTask implements Serializable{
    private LocalDate date=LocalDate.now();
    private String type; private int minutes, calories;
    private Status status=Status.COMPLETED; private int progress=100;

    public WorkoutTask(String t,int m,int c){ type=t; minutes=m; calories=c; }
    public void setStatus(Status s){ status=s; }
    public void setProgress(int p){ progress=p; }

    public String toString(){
        return date+" - "+type+" ("+minutes+" min"+(calories>=0?", "+calories+" cal":"")+
               ") | "+status+" | "+progress+"%";
    }
}
