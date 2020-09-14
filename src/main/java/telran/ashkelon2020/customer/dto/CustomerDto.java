package telran.ashkelon2020.customer.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
	
	Integer id;
	String firstName;
	String lastName;
	LocalDate birthDate;
	String email;
	
	Set<AccountDto> accounts;

}
