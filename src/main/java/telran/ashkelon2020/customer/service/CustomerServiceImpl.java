package telran.ashkelon2020.customer.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dao.AccountRepository;
import telran.ashkelon2020.customer.dao.CustomerRepository;
import telran.ashkelon2020.customer.dao.SubscriberRepository;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.AccountNewDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerNewDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.exceptions.DocumentNotFoundException;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;
import telran.ashkelon2020.customer.model.Subscriber;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository custRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SubscriberRepository subscrRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	@Transactional
	public boolean addCustomer(CustomerNewDto custNewDto) {
		if(custRepository.existsById(custNewDto.getId())) {
			return false;
		}
		custRepository.save(new Customer(custNewDto.getId(), 
								custNewDto.getFirstName(), 
								custNewDto.getLastName(), 
								custNewDto.getBirthDate(), 
								custNewDto.getEmail()));
		return true;
	}

	@Override
	public CustomerDto findCustomerById(Integer id) {
		Customer customer = custRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException());
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDto editCustomer(Integer id, CustomerUpdateDto custUpdateDto) {
		Customer customer = custRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException());
		
		String firstN = custUpdateDto.getFirstName();
		if(firstN != null && !firstN.isEmpty()) {
			customer.setFirstName(firstN);
		}
		
		String lastN = custUpdateDto.getLastName();
		if(lastN != null && !lastN.isEmpty()) {
			customer.setLastName(lastN);
		}
		
		String email = custUpdateDto.getEmail();
		if(email != null && !email.isEmpty()) {
			customer.setEmail(email);
		}
		
		custRepository.save(customer);
		
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDto deleteCustomer(Integer id) {
		Customer customer = custRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException());
		
		customer.getAccounts().forEach(a -> deleteAccount(a.getLogin()));
		custRepository.delete(customer);
		
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public boolean addAccount(Integer idCustomer, AccountNewDto accountNewDto) {
		Customer customer = custRepository.findById(idCustomer).orElseThrow(() -> new DocumentNotFoundException());
		if(accountRepository.existsById(accountNewDto.getLogin())){
			return false;
		}
		accountRepository.save(new Account(customer, 
										   accountNewDto.getLogin(), 
										   accountNewDto.getPassword(), 
										   accountNewDto.getTitle()));
		return true;
	}
	
	@Override
	public AccountDto findAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() ->  new DocumentNotFoundException());
		return mapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto editAccount(String login, String title) {
		Account account = accountRepository.findById(login).orElseThrow(() ->  new DocumentNotFoundException());
		if(title != null) {
			account.setTitle(title);
		}
		accountRepository.save(account);
		return mapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto deleteAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() ->  new DocumentNotFoundException());
		
		//subscrRepository.deleteByAccountsLogin(login);
		subscrRepository.findByAccountsLogin(login)
			.forEach(s -> s.getAccounts().remove(account));
		
		subscrRepository.deleteById(login);
		
		
		accountRepository.delete(account);
		return mapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public boolean subscribe(String loginFrom, String loginTo) {
		if(loginFrom == null || loginTo == null || loginFrom.equalsIgnoreCase(loginTo)) {
			return false;
		}
		Account accountFrom = accountRepository.findById(loginFrom).orElseThrow(() -> new DocumentNotFoundException());
		Account accountTo = accountRepository.findById(loginTo).orElseThrow(() -> new DocumentNotFoundException());
		
		Subscriber subscriber = subscrRepository.findById(loginFrom)
									.orElse(new Subscriber(loginFrom, new HashSet<>()));
		
		subscriber.getAccounts().add(accountTo);
		
		subscrRepository.save(subscriber);
						
		return true;
	}
	
	@Override
	@Transactional
	public boolean deleteSubscription(String loginFrom, String loginTo) {
		if(loginFrom == null || loginTo == null || loginFrom.equalsIgnoreCase(loginTo)) {
			return false;
		}
		Account accountFrom = accountRepository.findById(loginFrom).orElseThrow(() -> new DocumentNotFoundException());
		Account accountTo = accountRepository.findById(loginTo).orElseThrow(() -> new DocumentNotFoundException());
		
		Subscriber subscriber = subscrRepository.findById(loginFrom)
									.orElse(null);
		
		if(subscriber == null || !subscriber.getAccounts().contains(accountTo)) {
			return false;
		}
		
		subscriber.getAccounts().remove(accountTo);
		
		subscrRepository.save(subscriber);
						
		return true;
	}

	@Override
	public List<AccountDto> findAccountsByFirstName(String firstName) {
		return accountRepository.findByCustomerFirstName(firstName).stream()
				.map(a -> mapper.map(a, AccountDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<SubscriberDto> findSubscribersByLogin(String login, String loginContains) {
		return accountRepository.findByLoginPart(login, loginContains).stream()
				.map(s -> mapper.map(s, SubscriberDto.class))
				.collect(Collectors.toList());
	}

	

}
