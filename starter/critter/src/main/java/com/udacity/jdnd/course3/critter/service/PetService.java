package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet savePet(Pet pet, Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            pet.setCustomer(customer);
            pet = petRepository.save(pet);
            List<Pet> pets = new ArrayList<>();
            pets.add(pet);
            customer.setPets(pets);
            customerRepository.save(customer);
        } else {
            pet = petRepository.save(pet);
        }
        return pet;
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Pet> findByOwner(Long id) {
        return petRepository.findByCustomerId(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
