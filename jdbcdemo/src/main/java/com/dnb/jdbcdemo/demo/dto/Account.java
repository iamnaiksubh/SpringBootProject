package com.dnb.jdbcdemo.demo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;

//@Data
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = "customer")
public class Account {
    private String accountId;
    private String accountHolderName;
    private String accountType;
    private float balance;
    private String contactNumber;
    
	public Account(String accountId,String accountHolderName,String accountType,float balance,String contactNumber,String address,LocalDate accountCreatedDate, LocalDate dob, boolean accountStatus,Customer customer) throws InvalidNameException, InvalidDateException, InvalidContactNumberException, InvalidFloatException, InvalidIdException{

		super();

		this.setAccountId(accountId);
		this.setAccountHolderName(accountHolderName);
		this.setAccountCreatedDate(accountCreatedDate);
		this.setAccountStatus(accountStatus);
		this.setAccountType(accountType);
		this.setAddress(address);
		this.setBalance(balance);
		this.setContactNumber(contactNumber);
		this.setCustomer(customer);
		this.setDob(dob);

	}
    
    
    public void setAccountId(String accountId) throws InvalidIdException {
    	String idRegex = "^[a-zA-Z0-9]+$";
		
		if (Pattern.compile(idRegex).matcher(accountId).find()) {
			this.accountId = accountId;
		}else {
			throw new InvalidIdException("invalid id, should not contain special characters");
		}
		
	}
    
	public void setAccountHolderName(String accountHolderName) throws InvalidNameException {
		String regex = "^[a-zA-Z]{2,}$";
    	
    	if (Pattern.compile(regex).matcher(accountHolderName).find())
    		this.accountHolderName = accountHolderName;
    	else {
			throw new InvalidNameException("account holder name is not valid");
		}
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setBalance(float balance) throws InvalidFloatException {
		String regex = "^[-+]?\\d*\\.?\\d+$";
		String floatString = Float.toString(balance);
		if (Pattern.compile(regex).matcher(floatString).find()) {
			this.balance = balance;
		}
		else {
			throw new InvalidFloatException("invalid balance format or amount");
		}
		
	}
	
	public void setContactNumber(String contactNumber) throws InvalidContactNumberException {
		String regex = "^[0-9]{10}$";
		if (Pattern.compile(regex).matcher(contactNumber).find()) {
			this.contactNumber = contactNumber;
		}
		else {
			throw new InvalidContactNumberException("invalid contact details, must be 10 digit number (exclude +91 or 0)");
		}
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAccountCreatedDate(LocalDate accountCreatedDate) throws InvalidDateException {
		String regex = "\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}";
		if (Pattern.compile(regex).matcher(accountCreatedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).find())
			this.accountCreatedDate = accountCreatedDate;
		else {
			throw new InvalidDateException("invalid date format, required format dd/mm/yyyy");
		}
	}
	
	public void setDob(LocalDate dob) throws InvalidDateException {
		String regex = "\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}";
		if (Pattern.compile(regex).matcher(dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).find())
			this.dob = dob;
		else {
			throw new InvalidDateException("invalid date format, required format dd/mm/yyyy");
		}
	}
	
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	private String address;
    private LocalDate accountCreatedDate = LocalDate.now();
    private LocalDate dob;
    private boolean accountStatus;
    private Customer customer;
}
