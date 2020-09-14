package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "login")
@Entity
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	Customer customer;
	
	@Id
	String login;
	String password;
	String title;
		
	@ManyToMany(mappedBy = "accounts")
	/*
	@JoinTable(
			name = "ACCOUNT_SUBSCRIBERS",
			joinColumns = @JoinColumn(name = "ACCOUNT_LOGIN"),
			inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTIONS_LOGIN")
			)
	*/
	Set<Subscriber> subscribers;

	public Account(Customer customer, String login, String password, String title) {
		this.customer = customer;
		this.login = login;
		this.password = password;
		this.title = title;
	}
	
	

}
