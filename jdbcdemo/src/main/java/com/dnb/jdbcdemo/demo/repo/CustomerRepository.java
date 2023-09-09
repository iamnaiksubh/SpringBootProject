package com.dnb.jdbcdemo.demo.repo;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;


public interface CustomerRepository {
	public Customer createCustomer(Customer customer);
    public Optional<Customer> getCustomerById(int customerId) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException;

    public boolean deleteCustomer(String accountId);
    public boolean updateCustomer(String accountId);

    public List<Customer> getAllCustomer()throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException;
}
