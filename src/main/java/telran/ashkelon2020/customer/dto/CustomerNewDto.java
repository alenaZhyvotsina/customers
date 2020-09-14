package telran.ashkelon2020.customer.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerNewDto {
	
	Integer id;
	String firstName;
	String lastName;
	LocalDate birthDate;
	String email;

}
