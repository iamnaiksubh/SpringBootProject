package com.dnb.jdbcdemo.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;
import com.dnb.jdbcdemo.demo.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    public CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(int customerId) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return customerRepository.deleteCustomer(customerId);
    }

    @Override
    public boolean updateCustomer(String customerId) {

        return false;
    }

    @Override
    public List<Customer> getAllCustomer() throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
        return customerRepository.getAllCustomer();
    }
	

}
