package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(
			@RequestParam(required = false, value = "department") String department) {
		if(department != null)
			return ResponseEntity.ok(employeeService.getAllEmployeesByDepartment(department));
		else 
			return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@PostMapping
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return ResponseEntity.ok("Employee saved successfully");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable(name = "id") Long id,
			@RequestBody Employee employee) {
		employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok("Updated successfully for the employee id: " + id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "id") Long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok("Deleted successfully for the employee id: " + id);
	}
}
