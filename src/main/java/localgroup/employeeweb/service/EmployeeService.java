package localgroup.employeeweb.service;

import localgroup.employeeweb.entity.Employee;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);
    void deleteEmployeeById(Integer id);
    void deleteEmployee(Employee employee);
    void updateEmployee(Employee employee);
    List<Employee> getEmployeeByName(String firstName, int page);
    List<Employee> getEmployeeByDate(Date date, int page);
    Employee getEmployeeById(Integer id);
    List<Employee> getAllEmployees(int page);
    int countEmployees();

}
