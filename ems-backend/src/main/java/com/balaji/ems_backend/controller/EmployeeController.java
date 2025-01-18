package com.balaji.ems_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.ems_backend.dto.EmployeeDto;
import com.balaji.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor

public class EmployeeController {
    private EmployeeService employeeService;

    //Build ADD Employee RestAPI
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto){
       employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>("Successfully Data added to DataBase", HttpStatus.CREATED);
    }

    // Build GetEmployee RestAPI
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employee RestAPI
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDtos);
    }

    //Build Update Employee RestAPI
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.updateEmployee(employeeId,employeeDto);
        return ResponseEntity.ok(savedEmployee);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Succueefully",HttpStatus.OK);
    }
}
