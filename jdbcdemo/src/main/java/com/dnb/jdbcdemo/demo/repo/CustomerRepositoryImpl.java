package com.dnb.jdbcdemo.demo.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.demo.dto.Customer;
import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidPanException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidUUIDException;
import com.dnb.jdbcdemo.demo.utils.DBUtils;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	 @Autowired
	    private DBUtils dbUtils;
	    @Override
	    public Customer createCustomer(Customer customer) {
	        Optional<Connection> connection = dbUtils.getConnection();
	        String createCustomerStatement = "insert into customer " + "(customerId, customerName, customerContactNumber, customerAddress, customerPan, customerUUID) " + "values (?,?,?,?,?,?)";

	        PreparedStatement preparedStatement = null;
	        if (connection.isPresent()) {
	            try {

	                preparedStatement = connection.get().prepareStatement(createCustomerStatement);
	                preparedStatement.setInt(1, customer.getCustomerId());
	                preparedStatement.setString(2, customer.getCustomerName());
	                preparedStatement.setString(3, customer.getCustomerContactNumber());
	                preparedStatement.setString(4, customer.getCustomerAddress());
	                preparedStatement.setString(5, customer.getCustomerPan());
	                preparedStatement.setString(6, customer.getCustomerUUID());
	                int result = preparedStatement.executeUpdate();

	                if (result > 0) return customer;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                if (connection.isPresent()) {
	                    dbUtils.closeConnection(connection.get());
	                }
	            }
	        }

	        return null;
	    }

	    @Override
	    public Optional<Customer> getCustomerById(int customerId) throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
	        Optional<Connection> connection = dbUtils.getConnection();
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        String query = "select * from customer where customerId = ?";

	        if (connection.isPresent()) {
	            try {
	                preparedStatement = connection.get().prepareStatement(query);
	                preparedStatement.setInt(1, customerId);
	                resultSet = preparedStatement.executeQuery();

	                if (resultSet.next()) {
	                    Customer customer = new Customer();
	                    customer.setCustomerId(resultSet.getInt("customerId"));
	                    customer.setCustomerName(resultSet.getString("customerName"));
	                    customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
	                    customer.setCustomerAddress(resultSet.getString("customerAddress"));
	                    customer.setCustomerPan(resultSet.getString("customerPan"));
	                    customer.setCustomerUUID(resultSet.getString("customerUUID"));

	                    return Optional.of(customer);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }finally {
	                connection.ifPresent(dbUtils::closeConnection);
	            }
	        }
	        return Optional.empty();
	    }

	    @Override
	    public boolean deleteCustomer(String customerId) {
	        Optional<Connection> connection = dbUtils.getConnection();
	        PreparedStatement preparedStatement = null;
	        String deleteQuery = "delete from customer where customerId = ?";

	        if (connection.isPresent()) {
	            try {
	                preparedStatement = connection.get().prepareStatement(deleteQuery);
	                preparedStatement.setString(1, customerId);
	                int effectedRow = preparedStatement.executeUpdate();

	                if(effectedRow > 0)
	                    return true;

	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                connection.ifPresent(dbUtils::closeConnection);
	            }
	        }

	        return false;
	    }

	    @Override
	    public boolean updateCustomer(String customerId) {
	        Optional<Connection> connection = dbUtils.getConnection();
	        String deleteQuery = "alter customer where customerId=?";

	        PreparedStatement preparedStatement = null;
	        if (connection.isPresent()) {
	            try {
	                preparedStatement.setString(1, customerId);
	                preparedStatement = connection.get().prepareStatement(deleteQuery);
	                int effectedRow = preparedStatement.executeUpdate(deleteQuery);

	                if(effectedRow > 0)
	                    return true;

	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                connection.ifPresent(dbUtils::closeConnection);
	            }
	        }
	        return false;
	    }

	    @Override
	    public List<Customer> getAllCustomer() throws InvalidPanException, InvalidUUIDException, InvalidContactNumberException, InvalidIdException {
	        Optional<Connection> connection = dbUtils.getConnection();
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        String query = "select * from customer";
	        List<Customer> customerList = new ArrayList<>();

	        if (connection.isPresent()) {
	            try {
	                preparedStatement = connection.get().prepareStatement(query);
	                resultSet = preparedStatement.executeQuery();

	                while (resultSet.next()) {
	                    Customer customer = new Customer();
	                    customer.setCustomerId(resultSet.getInt("customerId"));
	                    customer.setCustomerName(resultSet.getString("customerName"));
	                    customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
	                    customer.setCustomerAddress(resultSet.getString("customerAddress"));
	                    customer.setCustomerPan(resultSet.getString("customerPan"));
	                    customer.setCustomerUUID(resultSet.getString("customerUUID"));

	                    customerList.add(customer);
	                }

	                return customerList;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }finally {
	                connection.ifPresent(dbUtils::closeConnection);
	            }
	        }
	        return null;
	    }
}

