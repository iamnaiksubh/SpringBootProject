package com.dnb.jdbcdemo.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.demo.dto.Account;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
