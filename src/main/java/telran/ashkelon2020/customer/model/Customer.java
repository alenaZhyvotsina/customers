package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	Integer id;
	String firstName;
	String lastName;
	LocalDate birthDate;
	String email;
	
	@OneToMany(mappedBy = "customer")
	Set<Account> accounts;

	public Customer(Integer id, String firstName, String lastName, LocalDate birthDate, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
	}	
	
	
	
}
