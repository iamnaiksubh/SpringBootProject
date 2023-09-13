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
	AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) throws InvalidPanException, InvalidUUIDException,
			InvalidContactNumberException, InvalidIdException, IdNotFoundException {
	
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException,
			InvalidContactNumberException, InvalidFloatException, InvalidIdException {
		// TODO Auto-generated method stub
		return accountRepository.findById(accountId);
	}

	@Override
	public boolean deleteAccount(String accountId) throws IdNotFoundException {
		if (accountRepository.existsById(accountId)) {
			accountRepository.deleteById(accountId);
			
			if (!accountRepository.existsById(accountId)) {
				return true;
			}
		}
		throw new IdNotFoundException("account nahi mila re tera");
	}

	@Override
	public boolean updateAccount(String accountId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Account> getAllAccount() throws InvalidNameException, InvalidDateException,
			InvalidContactNumberException, InvalidFloatException, InvalidIdException {
		// TODO Auto-generated method stub
		
		return accountRepository.findAll();
	}
	
}
