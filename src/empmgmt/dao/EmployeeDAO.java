package empmgmt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import empmgmt.dbutil.DBConnection;
import empmgmt.pojo.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class EmployeeDAO {
   
    public static boolean add(Employee emp) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into Employees values(?,?,?,?)");
        pst.setInt(1, emp.getId());
        pst.setString(2, emp.getName());
        pst.setDouble(3, emp.getSalary());
        pst.setInt(4, emp.getDeptno());
        
        return pst.executeUpdate() == 1;
    }
    
    public static Employee getByID(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from Employees where empno = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
        Employee employee = null;
        
        if (rs.next()) {
            employee = new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
        }
        
        return employee;
    }
    
    public static List list() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from Employees order by empno");
        ResultSet rs = pst.executeQuery();
        
        List<Employee> empList = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
            empList.add(employee);
        }
        
        return empList;
    }
    
    public static boolean update(Employee emp) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("update Employees set ename = ?, sal = ?, deptno = ? where empno = ?");
        pst.setString(1, emp.getName());
        pst.setDouble(2, emp.getSalary());
        pst.setInt(3, emp.getDeptno());
        pst.setInt(4, emp.getId());
        
        return pst.executeUpdate() == 1;
    }
    
    public static boolean delete(int empno) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("delete from Employees where empno = ?");
        pst.setInt(1, empno);
        
        return pst.executeUpdate() == 1;
    }
    
}
