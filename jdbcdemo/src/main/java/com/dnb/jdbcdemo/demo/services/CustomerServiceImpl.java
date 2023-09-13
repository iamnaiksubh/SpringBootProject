package com.dnb.jdbcdemo.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;
import com.dnb.jdbcdemo.demo.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId)
			throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

	@Override
	public boolean deleteCustomer(int customerId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
		if (customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			
			if (!customerRepository.existsById(customerId)) {
				return true;
			}
		}
		
		throw new IdNotFoundException("iss id ka customer nahi hai");
	}

	@Override
	public Iterable<Customer> getAllCustomer()
			throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
		// TODO Auto-generated method stub
		
		return customerRepository.findAll();
	}
	

	
}
