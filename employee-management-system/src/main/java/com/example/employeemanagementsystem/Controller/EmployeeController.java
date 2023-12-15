package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.Entity.Employee;
import com.example.employeemanagementsystem.Repository.EmployeeRepository;
import com.example.employeemanagementsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeManagement")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
    @PostMapping("/createEmp")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.creatEmployee(employee);
    }
    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployeeById(id, employee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>("Employee with id "+ id +" deleted Successfully",HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>("Employee with id "+ id +" not found", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>("An error accured while processing transaction ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
