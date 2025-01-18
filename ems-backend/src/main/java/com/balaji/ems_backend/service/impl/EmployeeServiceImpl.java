package com.balaji.ems_backend.service.impl;

import com.balaji.ems_backend.dto.EmployeeDto;
import com.balaji.ems_backend.entity.Employee;
import com.balaji.ems_backend.exception.ResourceNotFoundException;
import com.balaji.ems_backend.mapper.EmployeeMapper;
import com.balaji.ems_backend.repository.EmployeeRepository;
import com.balaji.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not found for given ID : "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee is not found for given ID : "+employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setLastName(updatedEmployee.getLastName());
        Employee updatedEmployees = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployees);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee is not found for given ID : "+employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
