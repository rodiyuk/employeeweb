package localgroup.employeeweb.rest;

import localgroup.employeeweb.dto.Employee;
import localgroup.employeeweb.exception.EmployeeNotFoundException;
import localgroup.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/employees")
    public ModelAndView getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employees");
        modelAndView.addObject("employeesList", employees);
        return modelAndView;
    }

    @GetMapping("/employee/{id}")
    public ModelAndView getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("id");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.updateEmployee(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        employeeService.addEmployee(employee);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        return modelAndView;
    }
//    // Создать сотрудника
//    @PostMapping("/employees")
//    public Employee addNewEmployee(@Valid @RequestBody Employee employee) {
//        return (Employee) employeeRepository.save(employee);
//    }

//    // получить всех сотрудников
//    @GetMapping("/")
//    public List getAllEmployees(){
//        return employeeRepository.findAll();
//    }

  /*  // Получить запись по id
    @GetMapping("/employees{id}")
    public Employee getEmployeeById(@PathVariable(value = "employeeId") Long id) throws Throwable {
        return (Employee) employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));
    }

    // Удалить запись по id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity employeeDelete(@PathVariable(value = "employeeId") Long id) throws Throwable {
        Employee employee = (Employee) employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }

    // Обновить запись
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "employeeId") Long id,
                               @Valid @RequestBody Employee employeeDetails) throws Throwable {

        Employee employee = (Employee) employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setGender(employeeDetails.getGender());
        employee.setDepartmentId(employeeDetails.getDepartmentId());
        employee.setJobTitle(employeeDetails.getJobTitle());
        employee.setBirth_date(employeeDetails.getBirth_date());

        Employee updatedEmployee = (Employee) employeeRepository.save(employee);
        return updatedEmployee;
    }*/
//    private EmployeeService employeeService = new EmployeeService();
//    private Long idEmployee;
//    private String firstName;
//    private String lastName;


//    public void employeeDelete(long id) {
//        employeeService.deleteEmployee(id);
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    public Employee getEmployeeById(long id) {
//        return employeeService.getEmployeeById(id);
//
//    }
//
//    public Employee getEmployeeByName(String firstName, String lastName) {
//        return employeeService.getEmployeeByName(firstName, lastName);
//    }
}
