package GUI;

import javax.swing.*;
import java.awt.*;
import Entity.Employee;
import File.FileIO;


public class PayrollManagerPage extends JFrame {
    private JTextField idField, nameField, hourlyRateField, workingHoursField, totalSalaryField;
    private JComboBox<String> designationBox;
    private JTextArea textArea;
    private Employee[] employees = new Employee[10000];
	
    private Cursor Hand;
	private ImageIcon Image;
    
	
	
    public PayrollManagerPage() {
        setTitle("Employee Payroll");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true); 
       setLocationRelativeTo(null); 
		
		

		

        
       ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(logo.getImage());


        
        getContentPane().setBackground(Color.decode("#EEEFE0"));

        
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BorderLayout());
        topContainer.setBackground(Color.decode("#EEEFE0"));

        
        JLabel titleLabel = new JLabel("M&N CORPORATIONS", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#5A827E"));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        topContainer.add(titleLabel, BorderLayout.NORTH);

        
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.decode("#EEEFE0"));

        inputPanel.add(new JLabel("Employee ID"));
        idField = new JTextField(); inputPanel.add(idField);

        inputPanel.add(new JLabel("Name"));
        nameField = new JTextField(); inputPanel.add(nameField);

        inputPanel.add(new JLabel("Designation"));
        String[] roles = {"CEO", "Secretary", "Administrative Assistant", "Receptionist", "Manager", "Executive", "Assistant"};
        designationBox = new JComboBox<>(roles); inputPanel.add(designationBox);

        inputPanel.add(new JLabel("Hourly Rate"));
        hourlyRateField = new JTextField(); inputPanel.add(hourlyRateField);

        inputPanel.add(new JLabel("Working Hours"));
        workingHoursField = new JTextField(); inputPanel.add(workingHoursField);

        inputPanel.add(new JLabel("Total Salary"));
        totalSalaryField = new JTextField();
        totalSalaryField.setEditable(false);
        inputPanel.add(totalSalaryField);

        topContainer.add(inputPanel, BorderLayout.CENTER);
        add(topContainer, BorderLayout.NORTH);

       
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#EEEFE0"));

        JButton addBtn = new JButton("Generate Salary");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        add(buttonPanel, BorderLayout.CENTER);

       
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.decode("#EEEFE0"));

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(Color.decode("#A7C1A8"));
        bottomPanel.add(saveBtn, BorderLayout.NORTH);

        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

      
        FileIO.loadFromFile(employees);
        refreshTextArea();
        
		
        
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String designation = designationBox.getSelectedItem().toString();
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double workingHours = Double.parseDouble(workingHoursField.getText());

                Employee emp = new Employee(name, String.valueOf(id), designation, hourlyRate, workingHours);
                employees[id] = emp;

                totalSalaryField.setText(String.valueOf(emp.getTotalSalary()));
                refreshTextArea();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Employee emp = employees[id];

                if (emp != null) {
                    emp.setName(nameField.getText());
                    emp.setDesignation(designationBox.getSelectedItem().toString());
                    emp.setHourlyRate(Double.parseDouble(hourlyRateField.getText()));
                    emp.setWorkingHours(Double.parseDouble(workingHoursField.getText()));

                    totalSalaryField.setText(String.valueOf(emp.getTotalSalary()));
                    refreshTextArea();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                employees[id] = null;
                refreshTextArea();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        });

        clearBtn.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            hourlyRateField.setText("");
            workingHoursField.setText("");
            totalSalaryField.setText("");
        });

        saveBtn.addActionListener(e -> {
            FileIO.saveChangesInFile(employees);
            JOptionPane.showMessageDialog(this, "Changes saved to file.");
        });

        setVisible(true);
		
		
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 
    }

    private void refreshTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Employee emp : employees) {
            if (emp != null) {
                sb.append("ID: ").append(emp.getId())
                  .append(", Name: ").append(emp.getName())
                  .append(", Designation: ").append(emp.getDesignation())
                  .append(", Hourly Rate: ").append(emp.getHourlyRate())
                  .append(", Working Hours: ").append(emp.getWorkingHours())
                  .append(", Total Salary: ").append(emp.getTotalSalary())
                  .append("\n");
            }
        }
        textArea.setText(sb.toString());
		
		
		 Hand = new Cursor(Cursor.HAND_CURSOR);
		 
        
     



    }
}
