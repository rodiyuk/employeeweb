package localgroup.employeeweb.rest;

import localgroup.employeeweb.entity.Employee;
import localgroup.employeeweb.exception.EmployeeNotFoundException;
import localgroup.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/name")
    public ModelAndView getEmployeeByName(@RequestParam(value = "name", required = false) String name) throws EmployeeNotFoundException {
        List<Employee> employees = employeeService.getEmployeeByName(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("name");
        modelAndView.addObject("employeesList", employees);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getEmployeeById(@RequestParam(value = "id") int id) throws EmployeeNotFoundException {
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
}
