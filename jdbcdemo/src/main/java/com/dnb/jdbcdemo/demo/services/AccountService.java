package com.dnb.jdbcdemo.demo.services;


import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.demo.dto.Account;
import com.dnb.jdbcdemo.demo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;


public interface AccountService {
    public Account createAccount(Account account) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException, IdNotFoundException ;
    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException;
    public boolean deleteAccount(String accountId) throws IdNotFoundException;
    public boolean updateAccount(String accountId);
    public Iterable<Account> getAllAccount() throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException;

}
