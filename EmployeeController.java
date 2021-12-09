package com.howtodoinjava.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.howtodoinjava.demo.exception.ResourceNotFoundException;

import com.howtodoinjava.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//
//@RestController
//@RequestMapping("/api")
//public class EmployeeController {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    @GetMapping("/employees/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//        return ResponseEntity.ok().body(employee);
//    }
//
//    @PostMapping("/employees")
//    public Employee createEmployee(@Valid @RequestBody Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    @PutMapping("/employees/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
//                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employee.setEmailId(employeeDetails.getEmailId());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setFirstName(employeeDetails.getFirstName());
//        final Employee updatedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//}








////////////////////////////////////////////////////////////////////////////////////////h2:


import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController
{

    private final EmployeeService service;

    @GetMapping(value="/date/{localDate}")
    public ResponseEntity<List<EmployeeEntity>> getByCreateTime( @PathVariable("localDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        List<EmployeeEntity> employees = service.getByCreateTime(localDate);
        return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
}


    @GetMapping(value="/SmallerThanCreateTime/{localDate}")
    public ResponseEntity<List<EmployeeEntity>> getBySmallerThanCreateTime( @PathVariable("localDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate1) {
        List<EmployeeEntity> employees = service.getBySmallerThanCreateTime(localDate1);
        return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value="/lname/{last_name}")
    public ResponseEntity<List<EmployeeEntity>> findByLastName(@PathVariable("last_name") String LastName1) {
       List<EmployeeEntity> employees = service.findByLastName( LastName1);
        return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value="/name/{first_name}")
    public ResponseEntity<List<EmployeeEntity>> findByFirstName(@PathVariable("first_name") String f_name) {
        List<EmployeeEntity> employees = service.findByFirstName( f_name);
        return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value="/email/{email}")
    public ResponseEntity<EmployeeEntity> findByEmail(@PathVariable("email") String email) {
        EmployeeEntity employee = service.findByEmail( email);
        return new ResponseEntity<EmployeeEntity>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();

        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);

        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
   @PostMapping
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity employee)
           throws RecordNotFoundException {
       EmployeeEntity updated = service.createOrUpdateEmployee(employee);
       return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
   }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping(value="/SmallerThanSpringData/{localDate}")
    public ResponseEntity<List<EmployeeEntity>> findByCreateTimeGreaterThanEqual( @PathVariable("localDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate1) {
        List<EmployeeEntity> employees = service.findByCreateTimeGreaterThanEqual(localDate1);
        return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
    }
}