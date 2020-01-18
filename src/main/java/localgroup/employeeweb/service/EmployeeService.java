package localgroup.employeeweb.service;

import localgroup.employeeweb.dto.Employee;
import localgroup.employeeweb.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);

    void deleteEmployeeById(Integer id) throws EmployeeNotFoundException;

    void deleteEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployeeByName(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee getEmployeeById(Integer employeeId) throws EmployeeNotFoundException;

    List<Employee> getAllEmployees();

}
