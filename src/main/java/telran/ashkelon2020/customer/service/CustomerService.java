package telran.ashkelon2020.customer.service;

import java.util.List;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.AccountNewDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerNewDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;

public interface CustomerService {
	
	boolean addCustomer(CustomerNewDto custNewDto);
	
	CustomerDto findCustomerById(Integer id);
	
	CustomerDto editCustomer(Integer id, CustomerUpdateDto custUpdateDto);
	
	CustomerDto deleteCustomer(Integer id);
	
	
	
	boolean addAccount(Integer idCustomer, AccountNewDto accountNewDto);
	
	AccountDto editAccount(String login, String title);
	
	AccountDto deleteAccount(String login);
	
	AccountDto findAccount(String login);
	

	boolean subscribe(String loginFrom, String loginTo);
	
	boolean deleteSubscription(String loginFrom, String loginTo);
	
	
	List<AccountDto> findAccountsByFirstName(String firstName);
	
	List<SubscriberDto> findSubscribersByLogin(String login, String loginContains);

}
