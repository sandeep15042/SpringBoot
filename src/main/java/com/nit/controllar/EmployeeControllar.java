package com.nit.controllar;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nit.entity.Employee;
import com.nit.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("/get")
public class EmployeeControllar {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/getEmployees/{id}")
	public Employee getEmployee(@PathVariable("id") Integer id) {
		return employeeRepository.findById(id).get();
	}

	@Transactional
	@PostMapping("/saveEmploye")
	public Employee saveEmployee(@RequestBody Employee employee) throws Exception {
		Optional<Employee> existingEmployee = employeeRepository.findByAddress(employee.getAddress());
		if (existingEmployee.isPresent()) {
			throw new RuntimeException("Employee with this address already exists");
		}
		// System.out.println("employee saved succusfully");

		return employeeRepository.save(employee);

	}

	@GetMapping("/getEmployeeByName/{name}")
	public List<Employee> getEmployeeByName(@PathVariable("name") String name) {

		return employeeRepository.findByName(name);
	}

	@GetMapping("/getEmployeeByNameAndSalary/{name}/{salary}")
	public List<Employee> getEmployeeByNameAndSalary(@PathVariable("name") String name,
			@PathVariable("salary") Double salary) {
		return employeeRepository.findByNameAndSalary(name, salary);
	}

	@DeleteMapping("/deleteEmployeeById/{id}")
	public String deleteEmployeeByName(@PathVariable("id") Integer id) {

		employeeRepository.deleteById(id);
		log.debug("employee delete succussfully");
		return "Employee deleted succussfully";
	}

	@PutMapping("/{id}")
	public String updateEmployeeByName(@PathVariable("id") Employee employee, @RequestBody Employee emp) {

		employeeRepository.save(emp);
		// log.debug("employee updated succussfully");
		return "Employee updated succussfully";
	}

}
