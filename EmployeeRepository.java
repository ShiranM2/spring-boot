package com.howtodoinjava.demo.repository;
//
//import com.howtodoinjava.demo.model.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long>{
//
//}

//////////////////////////////////////////////////////////////////////////////h2:


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.EmployeeEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByEmail(String email);

    List<EmployeeEntity> findByFirstName(String firstName);

    @Query("SELECT u FROM EmployeeEntity u WHERE u.lastName=:LastName1")
    List<EmployeeEntity> findByLastName(String LastName1);

    @Query("SELECT u FROM EmployeeEntity u WHERE u.createTime=:createTime ")
    List<EmployeeEntity> getByCreateTime(LocalDate createTime);

    //1.find employee created before specific date-using Query
    @Query("SELECT u FROM EmployeeEntity u WHERE u.createTime <:createTime1 ")
    List<EmployeeEntity> getBySmallerThanCreateTime(LocalDate createTime1);

    //2.find employee created before specific date-using Spring Data
    List<EmployeeEntity> findByCreateTimeGreaterThanEqual(LocalDate createTime1);
}