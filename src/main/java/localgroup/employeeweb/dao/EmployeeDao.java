package localgroup.employeeweb.dao;

import localgroup.employeeweb.entity.Employee;
import localgroup.employeeweb.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {
    void addEmployee(Employee employee);

    void deleteEmployeeById(Integer id) throws EmployeeNotFoundException;

    void deleteEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployeeByName(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee getEmployeeById(Integer employeeId) throws EmployeeNotFoundException;

    List<Employee> getAllEmployees();

}