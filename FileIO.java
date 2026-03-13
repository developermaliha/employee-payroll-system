package File;

import Entity.Employee;
import java.io.*;
import java.util.*;

public class FileIO {
    public static void loadFromFile(Employee[] employees) {
        try (Scanner sc = new Scanner(new File("./File/Employees.txt"))) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String designation = data[2];
                double hourlyRate = Double.parseDouble(data[3]);
                double workingHours = Double.parseDouble(data[4]);

                employees[id] = new Employee(name, String.valueOf(id), designation, hourlyRate, workingHours);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveChangesInFile(Employee[] employees) {
        try (FileWriter writer = new FileWriter("./File/Employees.txt")) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] != null) {
                    Employee emp = employees[i];
                    writer.write(i + ";" + emp.getName() + ";" + emp.getDesignation() + ";" +
                            emp.getHourlyRate() + ";" + emp.getWorkingHours() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
