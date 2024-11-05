import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee
{
    private String lastName;
    private int age;
    public Employee(String lastName, int age)
    {
        this.lastName = lastName;
        this.age = age;
    }
    public String getLastName()
    {
        return lastName;
    }
    public int getAge()
    {
        return age;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    @Override
    public String toString()
    {
        return "Employee{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
class CorporationSystem
{
    private List<Employee> employees = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    public void start()
    {
        System.out.println("Enter file path to load employees:");
        String filePath = scanner.nextLine();
        loadEmployees(filePath);
        String command;
        do
        {
            System.out.println("\nCommands: add, edit, delete, search, list, save, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();
            switch (command)
            {
                case "add" -> addEmployee();
                case "edit" -> editEmployee();
                case "delete" -> deleteEmployee();
                case "search" -> searchEmployee();
                case "list" -> listEmployees();
                case "save" -> saveEmployees(filePath);
                case "exit" -> saveEmployees(filePath);
                default -> System.out.println("Unknown command.");
            }
        }
        while (!command.equals("exit"));
    }
    private void addEmployee()
    {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        employees.add(new Employee(lastName, age));
        System.out.println("Employee added.");
    }
    private void editEmployee()
    {
        System.out.print("Enter last name of the employee to edit: ");
        String lastName = scanner.nextLine();
        Employee employee = findEmployeeByLastName(lastName);
        if (employee != null)
        {
            System.out.print("Enter new last name: ");
            employee.setLastName(scanner.nextLine());
            System.out.print("Enter new age: ");
            employee.setAge(Integer.parseInt(scanner.nextLine()));
            System.out.println("Employee updated.");
        }
        else
        {
            System.out.println("Employee not found.");
        }
    }
    private void deleteEmployee()
    {
        System.out.print("Enter last name of the employee to delete: ");
        String lastName = scanner.nextLine();
        Employee employee = findEmployeeByLastName(lastName);
        if (employee != null)
        {
            employees.remove(employee);
            System.out.println("Employee deleted.");
        }
        else
        {
            System.out.println("Employee not found.");
        }
    }
    private void searchEmployee()
    {
        System.out.print("Enter last name to search: ");
        String lastName = scanner.nextLine();
        Employee employee = findEmployeeByLastName(lastName);
        if (employee != null)
        {
            System.out.println(employee);
        }
        else
        {
            System.out.println("Employee not found.");
        }
    }
    private void listEmployees()
    {
        System.out.print("Enter 'age' or 'letter' to filter by: ");
        String filter = scanner.nextLine().trim().toLowerCase();
        if (filter.equals("age"))
        {
            employees.forEach(System.out::println);
        }
        else if (filter.equals("letter"))
        {
            System.out.print("Enter starting letter: ");
            char letter = scanner.nextLine().charAt(0);
            employees.stream()
                    .filter(e -> e.getLastName().startsWith(String.valueOf(letter)))
                    .forEach(System.out::println);
        }
        else
        {
            System.out.println("Invalid.");
        }
    }
    private Employee findEmployeeByLastName(String lastName)
    {
        return employees.stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
    private void loadEmployees(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                String lastName = parts[0];
                int age = Integer.parseInt(parts[1]);
                employees.add(new Employee(lastName, age));
            }
            System.out.println("Employees loaded.");
        }
        catch (IOException e)
        {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
    private void saveEmployees(String filePath)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath)))
        {
            for (Employee employee : employees)
            {
                writer.println(employee.getLastName() + "," + employee.getAge());
            }
            System.out.println("Employees saved.");
        }
        catch (IOException e)
        {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
