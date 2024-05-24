package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public List<Employee> getAllEmployeesByDepartment(String department){
		return employeeRepository.getEmployeesByDepartment(department);
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee id not found for the id: "+id));
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void updateEmployee(Long id, Employee employee) {
		if(employeeRepository.existsById(id)) {
			Employee existingEmployee = employeeRepository.findById(id).get();
			if(!existingEmployee.getName().equals(employee.getName())) {
				existingEmployee.setName(employee.getName());
			}
			if(!existingEmployee.getEmail().equals(employee.getEmail())) {
				existingEmployee.setEmail(employee.getEmail());
			}
			if(!existingEmployee.getDepartment().equals(employee.getDepartment())) {
				existingEmployee.setDepartment(employee.getDepartment());
			}
			if(!existingEmployee.getPhone().equals(employee.getPhone())) {
				existingEmployee.setPhone(employee.getPhone());
			}
			
			employeeRepository.save(existingEmployee);
		}
		else {
			throw new EmployeeNotFoundException("Employee id not found for updation: "+ id);
		}
	}
	
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
}
