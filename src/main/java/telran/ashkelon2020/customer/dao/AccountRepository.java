package telran.ashkelon2020.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Subscriber;

public interface AccountRepository extends JpaRepository<Account, String> {
	
	List<Account> findByCustomerFirstName(String firstName);
	
	@Query("select s from Account a join a.subscribers s where a.login=?1 and s.myLogin like concat('%', ?2, '%')")
	List<Subscriber> findByLoginPart(String login, String loginPart);

}
