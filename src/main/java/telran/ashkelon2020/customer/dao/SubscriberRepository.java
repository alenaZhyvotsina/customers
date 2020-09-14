package telran.ashkelon2020.customer.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
		
	//@Query("delete from Subscriber s where s.accounts.login=?1")
	//void deleteByAccountsLogin(String login);
	
	@Query("select s from Subscriber s join s.accounts a where a.login=?1")
	List<Subscriber> findByAccountsLogin(String login);
	
	void deleteByMyLogin(String login);
		
	List<Subscriber> findByMyLogin(String loginContains);

	/*
	@Query("select s from Subscriber join s.accounts a where a.email like '%@?1'")
	List<Subscriber> findByLoginPart(String loginContains);
	*/	

}
