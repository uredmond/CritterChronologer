package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToSchedule(scheduleDTO);
        try {
            Schedule saved = scheduleService.save(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
            scheduleDTO.setId(saved.getId());
            return scheduleDTO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save schedule", e);
        }
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return convertToScheduleDTOs(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        try {
            List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
            return convertToScheduleDTOs(schedules);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find schedule for pet with id:" + petId, e);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        try {
            List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
            return convertToScheduleDTOs(schedules);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find schedule for employee with id:" + employeeId, e);
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        try {
            List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
            return convertToScheduleDTOs(schedules);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find schedule for customer with id:" + customerId, e);
        }
    }

    private Schedule convertToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        schedule.setEmployeeSkills(scheduleDTO.getActivities());
        return schedule;
    }

    private ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setActivities(schedule.getEmployeeSkills());
        List<Long> employeeIds = new ArrayList<>();
        List<Employee> employees = schedule.getEmployeeList();
        for (Employee employee : employees) {
            employeeIds.add(employee.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIds);
        List<Long> petIds = new ArrayList<>();
        List<Pet> pets = schedule.getPetList();
        for (Pet pet : pets) {
            petIds.add(pet.getId());
        }
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    private List<ScheduleDTO> convertToScheduleDTOs(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = convertToScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }
        return scheduleDTOs;
    }
}
