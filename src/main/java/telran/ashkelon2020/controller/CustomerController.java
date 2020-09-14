package telran.ashkelon2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.AccountNewDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerNewDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer")
	public boolean addCustomer(@RequestBody CustomerNewDto custNewDto) {
		return customerService.addCustomer(custNewDto);
	}
	
	@GetMapping("/customer/{id}")
	public CustomerDto findCustomer(@PathVariable Integer id) {
		return customerService.findCustomerById(id);
	}
	
	@PutMapping("/customer/{id}")
	public CustomerDto editCustomer(@PathVariable Integer id, @RequestBody CustomerUpdateDto custUpdDto) {
		return customerService.editCustomer(id, custUpdDto);
	}
	
	@DeleteMapping("/customer/{id}")
	public CustomerDto deleteCustomer(@PathVariable Integer id) {
		return customerService.deleteCustomer(id);
	}
	
	@PostMapping("/account/{id}")
	public boolean addAccount(@PathVariable Integer id, @RequestBody AccountNewDto accountNewDto) {
		return customerService.addAccount(id, accountNewDto);
	}
	
	@GetMapping("/account/{login}")
	public AccountDto findAccount(@PathVariable String login) {
		return customerService.findAccount(login);
	}
	
	@PutMapping("/account/{login}/{title}")
	public AccountDto editAccount(@PathVariable String login, @PathVariable String title) {
		return customerService.editAccount(login, title);
	}
	
	@DeleteMapping("/account/{login}")
	public AccountDto deleteAccount(@PathVariable String login) {
		return customerService.deleteAccount(login);
	}
	
	//FIXME
	@PostMapping("/subscriber/{loginFrom}/{loginTo}")
	public boolean subscribe(@PathVariable String loginFrom, @PathVariable String loginTo) {
		return customerService.subscribe(loginFrom, loginTo);
	}
	
	@DeleteMapping("/subscriber/{loginFrom}/{loginTo}")
	public boolean deleteSubscription(@PathVariable String loginFrom, @PathVariable String loginTo) {
		return customerService.deleteSubscription(loginFrom, loginTo);
	}
	
	@GetMapping("/accounts/{firstName}")
	public List<AccountDto> getAccountsByFirstName(@PathVariable String firstName) {
		return customerService.findAccountsByFirstName(firstName);
	}
	
	@GetMapping("/account/{login}/subscribers/{loginContains}")
	public List<SubscriberDto> findSubscribersByLoginContains(@PathVariable String login, @PathVariable String loginContains) {
		return customerService.findSubscribersByLogin(login, loginContains);
	}

}
