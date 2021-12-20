package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Transactional
public interface EmployeeRepository extends UserBaseRepository<Employee> {

    List<Employee> findByDaysAvailable(DayOfWeek dayAvailable);

}
