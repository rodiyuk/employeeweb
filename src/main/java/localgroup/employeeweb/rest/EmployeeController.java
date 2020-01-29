package localgroup.employeeweb.rest;

import localgroup.employeeweb.entity.Employee;
import localgroup.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    private int page;
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
    public ModelAndView getAllEmployees(@RequestParam(defaultValue = "1") int page) {
        List<Employee> employees = employeeService.getAllEmployees(page);
        int countEmployees = employeeService.countEmployees();
        int countPages = (countEmployees + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employees");
        modelAndView.addObject("page", page);
        modelAndView.addObject("countEmployees", countEmployees);
        modelAndView.addObject("countPages", countPages);
        modelAndView.addObject("employeesList", employees);
        this.page = page;
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchEmployee(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "date", required = false) Date date,
                                          @RequestParam(defaultValue = "1", required = false) int page) {
        List<Employee> employees = new ArrayList<>();
        if (name != null) {
            employees = employeeService.getEmployeeByName(name, page);
            if(employees.size()==0){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("name", name);
                modelAndView.setViewName("notFound");
                return modelAndView;
            }
        } else if (date != null) {
            employees = employeeService.getEmployeeByDate(date, page);
            if(employees.size()==0){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("date", date);
                modelAndView.setViewName("notFound");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/employees");
        int countEmployees = employees.size();
        int countPages = (countEmployees + 9) / 10;
        modelAndView.addObject("page", page);
        modelAndView.addObject("countEmployees", countEmployees);
        modelAndView.addObject("countPages", countPages);
        modelAndView.addObject("employeesList", employees);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView getEmployeeById(@RequestParam(value = "id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee==null){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("id", id);
            modelAndView.setViewName("notFound");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("id");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        employeeService.addEmployee(employee);
        int countEmployees = employeeService.countEmployees();
        int page = (countEmployees + 9) / 10;
        modelAndView.setViewName("redirect:/employees");
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        int countEmployees = employeeService.countEmployees();
        int page = ((countEmployees - 1) % 10 == 0) && countEmployees > 10 && this.page == (countEmployees + 9) / 10 ?
                this.page - 1 : this.page;
        employeeService.deleteEmployee(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employees");
        modelAndView.addObject("page", page);
        return modelAndView;
    }
}
