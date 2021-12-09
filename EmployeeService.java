package com.howtodoinjava.demo.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;

@RequiredArgsConstructor
@Service
public class EmployeeService {


    private final EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeList = repository.findAll();
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException {
        if (entity.getId() == null) {
            entity.setCreateTime(LocalDate.now());
            entity = repository.save(entity);
            return entity;
        } else {
            Optional<EmployeeEntity> employee = repository.findById(entity.getId());
            if (employee.isPresent()) {
                EmployeeEntity newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setCreateTime(LocalDate.now());
                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity.setCreateTime(LocalDate.now());
                entity = repository.save(entity);
                return entity;

            }
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public List<EmployeeEntity> findByFirstName(String f_name) {

        List<EmployeeEntity> employees = repository.findByFirstName(f_name);
        if (employees.size() > 0) {
            return employees;
        } else {
            return null;
        }

    }

    public EmployeeEntity findByEmail(String email) {
        Optional<EmployeeEntity> employee = repository.findByEmail(email);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }

    }

    public List<EmployeeEntity> getByCreateTime(LocalDate createTime) {
        List<EmployeeEntity> employeeList = repository.getByCreateTime(createTime);
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return null;
        }

    }

    public List<EmployeeEntity> findByLastName(String LastName1) {
        List<EmployeeEntity> employeeList = repository.findByLastName(LastName1);
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return null;
        }

    }

    public List<EmployeeEntity> getBySmallerThanCreateTime(LocalDate localDate1) {
        List<EmployeeEntity> employeeList = repository.getBySmallerThanCreateTime(localDate1);
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return null;
        }
    }

    public List<EmployeeEntity> findByCreateTimeGreaterThanEqual(LocalDate localDate1) {
        List<EmployeeEntity> employeeList = repository.getBySmallerThanCreateTime(localDate1);
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return null;
        }
    }
}