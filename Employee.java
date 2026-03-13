package Entity;

public class Employee {
    private String name, id, designation;
    private double hourlyRate, workingHours, totalSalary;

    public Employee(String name, String id, String designation, double hourlyRate, double workingHours) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.totalSalary = hourlyRate * workingHours;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getDesignation() { return designation; }
    public double getHourlyRate() { return hourlyRate; }
    public double getWorkingHours() { return workingHours; }
    public double getTotalSalary() { return totalSalary; }

    public void setName(String name) { this.name = name; }
    public void setDesignation(String designation) { this.designation = designation; }
    
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        updateTotalSalary();
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
        updateTotalSalary();
    }

    private void updateTotalSalary() {
        this.totalSalary = this.hourlyRate * this.workingHours;
    }
}
