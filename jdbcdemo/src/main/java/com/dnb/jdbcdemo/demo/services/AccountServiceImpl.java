package com.dnb.jdbcdemo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.demo.dto.Account;
import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;
import com.dnb.jdbcdemo.demo.repo.AccountRepository;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public AccountRepository accountRepository;
    
    @Autowired
    public CustomerService customerService;

    @Override
    public Account createAccount(Account account) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException, IdNotFoundException  {
    	Optional<Customer> optional = customerService.getCustomerById(account.getCustomer().getCustomerId());
    	
    	if (optional.isPresent())
    		return accountRepository.createAccount(account);
    	else {
    		 throw new IdNotFoundException("Customer Details Not Found");
    	}
    }

    @Override
    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException {
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public boolean deleteAccount(String accountId) {
        return accountRepository.deleteAccount(accountId);
    }

    @Override
    public boolean updateAccount(String accountId) {

        return false;
    }

    @Override
    public List<Account> getAllAccount() throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException {
        return accountRepository.getAllAccount();
    }
}
