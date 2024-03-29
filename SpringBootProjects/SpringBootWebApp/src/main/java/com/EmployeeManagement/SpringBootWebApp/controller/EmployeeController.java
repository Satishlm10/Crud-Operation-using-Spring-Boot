package com.EmployeeManagement.SpringBootWebApp.controller;

import com.EmployeeManagement.SpringBootWebApp.entity.Employee;
import com.EmployeeManagement.SpringBootWebApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping(value = {"","/","/showEmployees"})
     public ModelAndView showEmployees(){
       ModelAndView mav = new ModelAndView("list-employees");
       List<Employee> list =  employeeRepository.findAll();
       mav.addObject("employees",list);
       return mav;
    }
    @RequestMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        mav.addObject("employee",newEmployee);
        return mav;
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
    ModelAndView mav = new ModelAndView("add-employee-form");
    Employee employee = employeeRepository.findById(employeeId).get();
    mav.addObject("employee",employee);
    return mav;
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId){
        employeeRepository.deleteById(employeeId);
        return "redirect:/";
    }






}
