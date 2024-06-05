package empmgmt.pojo;

/**
 *
 * @author Prasant
 */
public class Employee {
    private int id;
    private String name;
    private double salary;
    private int deptno;

    public Employee() {
    }

    public Employee(int id, String name, double salary, int deptno) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptno = deptno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}
