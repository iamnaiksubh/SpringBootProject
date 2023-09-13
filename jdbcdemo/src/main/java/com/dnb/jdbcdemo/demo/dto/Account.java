package com.dnb.jdbcdemo.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.Policy.Parameters;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import com.dnb.jdbcdemo.demo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidFloatException;
import com.dnb.jdbcdemo.demo.exceptions.InvalidIdException;
import com.dnb.jdbcdemo.demo.utils.CustomAccountIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@Data
@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = "customer")
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@GenericGenerator(name = "account_seq", strategy = "com.dnb.jdbcdemo.demo.utils.CustomAccountIdGenerator",
	parameters = {@Parameter(name = CustomAccountIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomAccountIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
			@Parameter(name = CustomAccountIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	@NotBlank(message = "account id should not be blank")
    private String accountId;
	@NotBlank(message = "account holder name should not be blank")
    @Column(nullable = false) private String accountHolderName;
    @Column private String accountType;

    @Min(value = 0, message = "balance can,t be negative")
    @Column private float balance;
    
//    @Length(min = 10, max = 10)
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{10}$")
    @Column private String contactNumber;
	@Column private String address;
	@Column private LocalDate accountCreatedDate = LocalDate.now();
	@NotNull 
	@Column private LocalDate dob;
    @Transient boolean accountStatus;
    //private Customer customer;
    
    
}
