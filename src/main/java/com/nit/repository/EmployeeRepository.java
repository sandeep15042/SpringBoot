package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nit.entity.Employee;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByName(String name);

	List<Employee> findByNameAndSalary(String name, Double salary);

	Optional<Employee> findByAddress(String address);

	Optional<Employee> findBySalary(Double double1);
}
