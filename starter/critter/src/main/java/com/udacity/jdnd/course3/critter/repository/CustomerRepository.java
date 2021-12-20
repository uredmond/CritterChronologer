package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Customer;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends UserBaseRepository<Customer> {

}
