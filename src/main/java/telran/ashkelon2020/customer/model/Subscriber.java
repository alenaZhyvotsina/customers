package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Subscriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	String myLogin;
	
	@ManyToMany //(cascade = CascadeType.ALL)
	@JoinTable(
			name = "SUBSCRIBER_ACCOUNTS",
			joinColumns = @JoinColumn(name = "SUBSCRIBER_MY_ACCOUNT_LOGIN"),
			inverseJoinColumns = @JoinColumn(name = "ACCOUNTS_LOGIN")
			)
	Set<Account> accounts;

	public Subscriber(String myLogin) {
		super();
		this.myLogin = myLogin;
	}
	
}
