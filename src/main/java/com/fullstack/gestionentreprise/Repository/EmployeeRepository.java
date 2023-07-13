package com.fullstack.gestionentreprise.Repository;

import com.fullstack.gestionentreprise.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

   Optional<Employee> findByEmail(String email);
   @Transactional
   @Modifying
   @Query("UPDATE Employee a " +
           "SET a.enabled = TRUE WHERE a.email = ?1")
   int enableAppUser(String email);}