package telran.ashkelon2020.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
