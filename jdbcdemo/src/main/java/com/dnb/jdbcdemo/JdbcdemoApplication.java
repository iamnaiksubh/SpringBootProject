package com.dnb.jdbcdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.naming.InvalidNameException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dnb.jdbcdemo.demo.dto.Account;
import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;
import com.dnb.jdbcdemo.demo.services.AccountService;
import com.dnb.jdbcdemo.demo.services.CustomerService;

@SpringBootApplication
public class JdbcdemoApplication {

	private static AccountService accountService;
	private static CustomerService customerService;
	private static Scanner sc = new Scanner(System.in);
	

    private static void showEntityMenu(Entity entity) {
    	int choice = -1;
    	String entityName = entity.value ? "Account" : "Customer";
        do {
            System.out.println("\n Enter your choice :\n" +
                    "1. Create " + entityName + "\n" +
                    "2. Search " + entityName + " \n" +
                    "3. Delete " + entityName + " \n" +
                    "4. Get All " + entityName + " \n" +
                    "5. Exit");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Enter correct choice");
                continue;
            }
            
            
            if (entity.value) {
            	switch (choice) {
                case 1 -> createAccount();
                case 2 -> getEntity(Entity.ACCOUNT);
                case 3 -> deleteEntity(Entity.ACCOUNT);
                case 4 -> getAllEntity(Entity.ACCOUNT);
                case 5 -> {
                }
            }
			}
            else {
	            switch (choice) {
	                case 1 -> createCustomer();
	                case 2 -> getEntity(Entity.CUSTOMER);
	                case 3 -> deleteEntity(Entity.CUSTOMER);
	                case 4 -> getAllEntity(Entity.CUSTOMER);
	                case 5 -> {
	                }
	            }
            }

        } while (choice != 5);
	}

	private static void createCustomer() {
		List<Customer> customerList = new ArrayList<Customer>();
		
		System.out.println("How many customer you want to create : ");
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++){
			Customer customer = new Customer();
			System.out.println("Customer Id :");
			try {
				customer.setCustomerId(sc.nextInt());
			} catch (InvalidIdException e) {
				e.printStackTrace();
			}
			System.out.println("Customer Name :");
			customer.setCustomerName(sc.next());
			System.out.println("Customer Contact Number :");
			try {
				customer.setCustomerContactNumber(sc.next());
			} catch (InvalidContactNumberException e) {
				e.printStackTrace();
			}
			System.out.println("Customer Address :");
			customer.setCustomerAddress(sc.next());
			System.out.println("Customer Pan :");
			try {
				customer.setCustomerPan(sc.next());
			} catch (InvalidPanException e) {
				e.printStackTrace();
			}
			System.out.println("Customer UUID :");
			try {
				customer.setCustomerUUID(sc.next());
			} catch (InvalidUUIDException e) {
				e.printStackTrace();
			}
			customerList.add(customer);
		}
		
		customerList.forEach(customer -> {
			customerService.createCustomer(customer);
			System.out.println("Customer Created....\n");
		});
		
	}

	private static void getAllEntity(Entity entity) {
		
		// getAllAccounts
		if (entity.value) {
			List<Account> accountList = null;
			try {
				try {
					accountList = accountService.getAllAccount();
				} catch (InvalidIdException e) {
					e.printStackTrace();
				}
			} catch (InvalidNameException | InvalidDateException | InvalidContactNumberException
					| InvalidFloatException e) {
				e.printStackTrace();
			}
			
			if (accountList.isEmpty()) {
				System.out.print("No accounts details to show.\n\n");
				return;
			}

	        accountList.forEach(account -> {
	            System.out.println(account.getAccountId() + " " + account.getAccountHolderName() + " " + account.getAccountType() + " " +
	                    account.getBalance() + " " + account.getAccountCreatedDate() + " " + account.getDob() + " " + account.isAccountStatus());
	        });
	        return;
		}
		
		// getAllCustomers
		List<Customer> customerList = null;
		try {
			customerList = customerService.getAllCustomer();
		} catch (InvalidPanException | InvalidUUIDException | InvalidContactNumberException | InvalidIdException e) {
			e.printStackTrace();
		}
			
		if (customerList.isEmpty()) {
			System.out.print("No customer details to show.\n\n");
		}
        customerList.forEach(customer -> {
        	System.out.println(customer.getCustomerId() + " " + customer.getCustomerName() + " " + customer.getCustomerAddress() + " " + 
    	            customer.getCustomerContactNumber() + " " + customer.getCustomerPan() + " " + customer.getCustomerUUID());
        });
        
    }

    private static void deleteEntity(Entity entity) {
    	String enitityName = entity.value ? "Account" : "Customer";
        System.out.println("Enter the " + enitityName + " Id which you want to delete : ");
        String entityId = sc.next();
        
        boolean result;
        
        if (entity.value) {
        	result = accountService.deleteAccount(entityId);
		}else {
			result = customerService.deleteCustomer(entityId);
		}

        if (result) {
            System.out.println(enitityName + " deleted....\n");
        } else {
            System.out.println("No "+ enitityName + " found\n");
        }
    }

    public static void createAccount() {
        List<Account> accounts = new ArrayList<>();
        System.out.println("How many account you want to create : ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Account newAccount = new Account();
            System.out.println("Account Id : ");
            try {
				newAccount.setAccountId(sc.next());
			} catch (InvalidIdException e) {
				e.printStackTrace();
			}

            System.out.println("Account Holder Name : ");
            try {
				newAccount.setAccountHolderName(sc.next());
			} catch (InvalidNameException e) {
				e.printStackTrace();
			}

            System.out.println("Account Type : ");
            newAccount.setAccountType(sc.next());

            System.out.println("Balance : ");
            try {
				newAccount.setBalance(sc.nextFloat());
			} catch (InvalidFloatException e) {
				e.printStackTrace();
			}

            System.out.println("Contact Number : ");
            try {
				newAccount.setContactNumber(sc.next());
			} catch (InvalidContactNumberException e) {
				e.printStackTrace();
			}

            System.out.println("Address : ");
            newAccount.setAddress(sc.next());

            System.out.println("DOB : ");
            String dob = sc.next();
            try {
				newAccount.setDob(convertToLocalDate(dob));
			} catch (InvalidDateException e) {
				e.printStackTrace();
			}

            newAccount.setAccountStatus(true);
            
            System.out.println("Enter Customer details (id, name, contact number, address, pan, uuid):");
            Customer customer = null;
			try {
				customer = new Customer(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
			} catch (InvalidPanException | InvalidUUIDException | InvalidContactNumberException
					| InvalidIdException e) {
				e.printStackTrace();
			}
            newAccount.setCustomer(customer);
            accounts.add(newAccount);
        }

        accounts.forEach(account -> {
            try {
				accountService.createAccount(account);
				System.out.println("Account Created.....\n\n");
			} catch (IdNotFoundException | InvalidPanException | InvalidUUIDException | InvalidContactNumberException | InvalidIdException e) {
				e.printStackTrace();
			}
        });
    }
 

    
    
    public static void getEntity(Entity entity) {
    	String entityName = (entity.value ? "Account" : "Customer");
        System.out.println("Enter the " + entityName + " Id : ");
        String entityId = sc.next();
        
        if (entity.value) {
        	Optional<Account> result = Optional.empty();
			try {
				result = accountService.getAccountById(entityId);
			} catch (InvalidNameException | InvalidDateException | InvalidContactNumberException
					| InvalidFloatException | InvalidIdException e) {
				e.printStackTrace();
			}
			
        	if (result.isPresent()) {
                Account account = result.get();
                System.out.println(account.getAccountId() + " " + account.getAccountHolderName() + " " + account.getAccountType() + " " +
                        account.getBalance() + " " + account.getAccountCreatedDate() + " " + account.getDob() + " " + account.isAccountStatus() + "\n");
                return;
            }
        	
		}else {
			Optional<Customer> result = Optional.empty();
			try {
				result = customerService.getCustomerById(Integer.parseInt(entityId));
			} catch (NumberFormatException | InvalidPanException | InvalidUUIDException | InvalidContactNumberException
					| InvalidIdException e) {
				e.printStackTrace();
			}
			if (result.isPresent()) {
	            Customer customer = result.get();
	            System.out.println(customer.getCustomerId() + " " + customer.getCustomerName() + " " + customer.getCustomerAddress() + " " + 
	            customer.getCustomerContactNumber() + " " + customer.getCustomerPan() + " " + customer.getCustomerUUID() + "\n");
	            return;
	        }
		}
        
        System.out.println("No " + entityName + " found for this " + entityId + " id.");
        
    }
    
    public static LocalDate convertToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
    
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(JdbcdemoApplication.class, args);
        accountService = applicationContext.getBean(AccountService.class);
        customerService = applicationContext.getBean(CustomerService.class);
        
        int choice = -1;
        
        do {
        	
        	System.out.println("\nEnter your choice : \n"
        			+ "1. Account Menu \n"
        			+ "2. Customer Menu \n"
        			+ "3. Exit");
        	
        	choice = sc.nextInt();
        	
        	switch (choice) {
        	case 1 -> showEntityMenu(Entity.ACCOUNT);
        	case 2 -> showEntityMenu(Entity.CUSTOMER);
        	case 3 -> {
        		sc.close();
        		break;
        	}
			default -> throw new IllegalArgumentException("Unexpected value : " + choice);
			}
        	
        }while(choice != 3);   
    
    }
	
	enum Entity {
		ACCOUNT(true),
		CUSTOMER(false);
		
		boolean value;
		private Entity(Boolean value) {
			this.value = value;
		}
		
		public boolean getValue() {
			return this.value;
		}
	}

}
