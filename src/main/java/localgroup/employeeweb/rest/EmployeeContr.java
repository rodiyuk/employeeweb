//package localgroup.employeeweb.rest;
//
//import localgroup.employeeweb.entity.Employee;
//import localgroup.employeeweb.service.EmployeeService1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeContr {
//
//@Autowired
//private EmployeeService1 employeeService;
//
//@GetMapping
//public List<Employee> getEmployees(){
//    return employeeService.getEmployees();
//}
//
//@PostMapping
//public @ResponseBody Employee createEmployees(@RequestBody Employee employee){
//    return employeeService.save( employee );
//}
//
//}