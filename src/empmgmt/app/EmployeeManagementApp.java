package empmgmt.app;

import empmgmt.dao.EmployeeDAO;
import empmgmt.dbutil.DBConnection;
import empmgmt.pojo.Employee;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class EmployeeManagementApp {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        
        int choice;

        do {
            promptMain();
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> searchEmployee();
                case 3 -> allEmployees();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> {
                    System.out.println("");
                    System.out.println("Thank you!");
                    DBConnection.closeConnection();
                }
                default -> System.out.println("You've choosen wrong input! Please try again.");
            }
            
            System.out.println("");
        } while (choice != 6);
    }

    public static void addEmployee() {
        Employee emp = getEmployee();

        try {
            boolean isAdded = EmployeeDAO.add(emp);
            System.out.println("\nrecord " + (isAdded ? "added!" : "not added!"));

        } catch (SQLException ex) {
            System.out.println("Exception, not added! " + ex);
        }
    }

    public static void searchEmployee() {
        System.out.print("Enter emp no to be search: ");
        int id = sc.nextInt();
        
        System.out.println("");

        try {
            Employee emp = EmployeeDAO.getByID(id);
            if (emp == null) {
                System.out.println("Ops.., No record found!");
            } else {
                System.out.println("Record found!");
                printEmployee(emp, true);
            }

        } catch (SQLException ex) {
            System.out.println("Exception, not found: " + ex);
        }
    }

    public static void allEmployees() {
        try {
            List<Employee> list = EmployeeDAO.list();
            
            System.out.println("");
            
            if (list.isEmpty()) {
                System.out.println("Ops.., No record found!");
            } else {
                System.out.println("Record found!");
                
                printEmployee(null, true);
                for (Employee emp : list) printEmployee(emp, false);
            }

        } catch (SQLException ex) {
            System.out.println("Exception, not get record: " + ex);
        }
    }

    public static void updateEmployee() {

        try {

            Employee emp = null;
            int empno;

            do {
                System.out.print("Enter the emp no to be update: ");
                empno = sc.nextInt();

                emp = EmployeeDAO.getByID(empno);
                if (emp == null) {
                    System.out.println("Ops.., No record found! Please try again.");
                }
            } while (emp == null);

            System.out.println("\nRecord found!");
            printEmployee(emp, true);

            int choice;

            do {

                System.out.println("");
                promptUpdate();
                
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        sc.nextLine();
                        emp.setName(sc.nextLine());
                    }

                    case 2 -> {
                        System.out.print("Enter new salary: ");
                        emp.setSalary(sc.nextDouble());
                    }

                    case 3 -> {
                        System.out.print("Enter new dept no: ");
                        emp.setDeptno(sc.nextInt());
                    }

                    case 4 -> {
                        return;
                    }
                    default ->
                        System.out.println("You've choosen wrong input! Please try again.");
                }
            } while (choice < 0 || choice > 4);

            boolean isUpdate = EmployeeDAO.update(emp);
            if (isUpdate) {
                Employee newEmp = EmployeeDAO.getByID(empno);
                System.out.println("");

                if (newEmp == null) {
                    System.out.println("Updated but something error!");
                } else {
                    System.out.println("Record updated!");
                }

                System.out.println("New record:");
                printEmployee(newEmp, true);
            } else {
                System.out.println("Record not updated!");
            }

        } catch (SQLException ex) {
            System.out.println("Exception, not updated: " + ex);
        }
    }

    public static void deleteEmployee() {
        System.out.print("Enter emp no to be deleted: ");
        int id = sc.nextInt();

        System.out.println("");
        
        try {
            boolean isdeleted = EmployeeDAO.delete(id);
            System.out.println(id + "'s record " + (isdeleted ? "deleted!" : "not deleted!"));

        } catch (SQLException ex) {
            System.out.println("Exception, not deleted: " + ex);
        }
    }

    // ---- Auxilary -------------------------------------------------------------------------------------------------
    public static void promptMain() {
        System.out.println("Please choose an option: ");
        System.out.println("1. Add employee");
        System.out.println("2. Search employee");
        System.out.println("3. View all employees");
        System.out.println("4. Update employee");
        System.out.println("5. Delete employee");
        System.out.println("6. Quit");
        System.out.print("Enter: ");
    }

    public static void promptUpdate() {
        System.out.println("Please choose an option to update");
        System.out.println("1. Update name");
        System.out.println("2. Update salary");
        System.out.println("3. Update dept no");
        System.out.println("4. Back");
        System.out.print("Enter: ");
    }

    public static void printEmployee(Employee emp, boolean header) {
        if (header) System.out.println("Emp no\tName\t\tSalary\tDept no");
        if (emp == null) return;
        
        System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getSalary() + "\t" + emp.getDeptno());
    }

    public static Employee getEmployee() {
        System.out.print("Enter emp no: ");
        int id = sc.nextInt();

        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter salary: ");
        double sal = sc.nextDouble();

        System.out.print("Enter dept no: ");
        int deptno = sc.nextInt();

        return new Employee(id, name, sal, deptno);
    }
}
