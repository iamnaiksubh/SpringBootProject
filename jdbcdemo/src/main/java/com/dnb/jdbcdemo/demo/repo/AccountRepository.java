package com.dnb.jdbcdemo.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.demo.dto.Account;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;

public interface AccountRepository {
    public Account createAccount(Account account);
    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException;

    public boolean deleteAccount(String accountId);
    public boolean updateAccount(String accountId);

    public List<Account> getAllAccount() throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException;
}
