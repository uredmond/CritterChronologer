package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {

}
