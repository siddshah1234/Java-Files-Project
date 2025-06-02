// Siddhant Shah
// Java Files Project
// 6/2/2025
// Extra: Store complex combinations of data, including different pieces of data in 1 row of a file

// importing packages
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    // declaring necessary variables
    static final String filename = "data.txt";

    public static void main(String[] args) {
        // monthly employee hourly pay
        Scanner sc = new Scanner(System.in);
        String passwd = "w0rd125";
        boolean accessgranted = false;
        // creating an arraylist of employee objects to store different objects with employee information to display to or edit by the user
        ArrayList<Employee> employees = new ArrayList<>();

        // introducing the user and verifying input
        System.out.println("Welcome to Parsippany Grocery Store Employee Pay Tracker!");
        System.out.print("Please enter the administrator password: ");
        String inputted = sc.next();

        if (inputted.equals(passwd)) {
            accessgranted = true;
            System.out.println("Access granted.");
        } 
        else {
            System.out.println("Access denied.");
        }

        // attempting to open file name and storing parts of each line in an arraylist
        try {
            File file = new File(filename);
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0];
                        double hours = Double.parseDouble(parts[1]);
                        double rate = Double.parseDouble(parts[2]);
                        employees.add(new Employee(name, hours, rate));
                    }
                }
                reader.close();
            } 
            else {
                file.createNewFile();
            }
        } 
        catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        // while loop for the menu and its options while the user does not want to save and exit
        int choice = 0;
        while (choice != 3) {
            // entering 3 different menu options and prompting user
            System.out.println("Menu: ");
            System.out.println("1. Add employee");
            System.out.println("2. View all employees and pay");
            System.out.println("3. Save and exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            // option to add new employee, so asking user to add new information and saving to the arraylist
            if (choice == 1) {
                sc.nextLine();
                System.out.print("Enter employee name: ");
                String name = sc.nextLine();
                System.out.print("Enter hours worked: ");
                double hours = sc.nextDouble();
                System.out.print("Enter hourly rate: ");
                double rate = sc.nextDouble();
                sc.nextLine();
                employees.add(new Employee(name, hours, rate));
                System.out.println("Employee added.");
            } 
            // option to view the employee list, so arraylist of employees is looped through and each variable in each object is displayed
            else if (choice == 2) {
                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } 
                else {
                    System.out.println("Employee Records: ");
                    for (Employee emp : employees) {
                        System.out.println("Name: " + emp.name + ", Hours Worked: " + emp.hoursworked + ", Hourly Rate: $" + emp.hourlyrate + ", Total Pay: $" + emp.totalpay());
                    }
                }
            } 
            // option to save the information and exit the program, so printwriter is declared to clear existing information (info is already stored in arraylist of employees)
            // loop for each object in employees and employee name, hoursworked, and hourlyrate are written to the file
            else if (choice == 3) {
                try {
                    PrintWriter writer = new PrintWriter(filename);
                    for (Employee emp : employees) {
                        writer.println(emp.name + "," + emp.hoursworked + "," + emp.hourlyrate);
                    }
                    writer.close();
                    System.out.println("Data saved.");
                }
                // error trapping 
                catch (FileNotFoundException e) {
                    System.out.println("Error saving file.");
                }
            } 
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

// employee object declaration
class Employee {
    // object variables
    String name;
    double hoursworked;
    double hourlyrate;

    // constructor
    public Employee(String name, double hoursworked, double hourlyrate) {
        this.name = name;
        this.hoursworked = hoursworked;
        this.hourlyrate = hourlyrate;
    }
    
    // totalpay function to return total paycheck
    public double totalpay() {
        return hoursworked * hourlyrate;
    }
}
