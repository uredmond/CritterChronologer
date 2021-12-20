package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule AS s WHERE :pet MEMBER OF s.petList")
    List<Schedule> findByPet(Pet pet);

    @Query("SELECT s FROM Schedule AS s WHERE :employee MEMBER OF s.employeeList")
    List<Schedule> findByEmployee(Employee employee);

}
