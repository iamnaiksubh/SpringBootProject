package com.dnb.jdbcdemo.demo.dto;

import java.util.regex.Pattern;

import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Customer {
	@Id private int customerId;
	@Column private String customerName;
	@Column private String customerContactNumber;
	@Column private String customerAddress;
	@Column private String customerPan;
 	@Column private String customerUUID;
	
	
	public Customer(int customerId, String customerName, String customerContactNumber, String customerAddress,
			String customerPan, String customerUUID) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
		super();
		this.setCustomerId(customerId);;
		this.setCustomerName(customerName);;
		this.setCustomerContactNumber(customerContactNumber);;
		this.setCustomerAddress(customerAddress);;
		this.setCustomerPan(customerPan);;
		this.setCustomerUUID(customerUUID);;
	}
	
	
	public void setCustomerId(int customerId) throws InvalidIdException {
		String idRegex = "^[a-zA-Z0-9]+$";
		
		if (Pattern.compile(idRegex).matcher(Integer.toString(customerId)).find()) {
			this.customerId = customerId;
		}else {
			throw new InvalidIdException("invalid id, should not contain special characters");
		}
		
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void setCustomerContactNumber(String customerContactNumber) throws InvalidContactNumberException {
		String regex = "^[0-9]{10}$";
		if (Pattern.compile(regex).matcher(customerContactNumber).find()) {
			this.customerContactNumber = customerContactNumber;
		}
		else {
			throw new InvalidContactNumberException("invalid contact details, must be 10 digit number (exclude +91 or 0)");
		}
		
	}
	
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public void setCustomerPan(String customerPan) throws InvalidPanException {
		String panRegex = "^[A-Z]{5}[0-9]{4}[A-Z]$";
		if (Pattern.compile(panRegex).matcher(customerPan).find()) {
			this.customerPan = customerPan;
		}else {
			throw new InvalidPanException("invalid pan format");
		}
	}
	
	public void setCustomerUUID(String customerUUID) throws InvalidUUIDException {
		String uuidRegex = "^[0-9]{12}$";
		if (Pattern.compile(uuidRegex).matcher(customerUUID).find()) {
			this.customerUUID = customerUUID;	
		}else {
			throw new InvalidUUIDException("invalid UUID format");
		}
		
	}

}
