package localgroup.employeeweb.dao;

import localgroup.employeeweb.entity.Employee;
import localgroup.employeeweb.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EmployeeDao {
    void addEmployee(Employee employee);
    void deleteEmployeeById(Integer id) ;
    void deleteEmployee(Employee employee);
    void updateEmployee(Employee employee);
    List<Employee> getEmployeeByName(String firstName, int page);
    List<Employee> getEmployeeByDate(Date date, int page);
    Employee getEmployeeById(Integer id);
    List<Employee> getAllEmployees(int page);
    int countEmployees();
}