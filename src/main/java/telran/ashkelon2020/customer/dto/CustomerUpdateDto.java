package telran.ashkelon2020.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerUpdateDto {
	
	String firstName;
	String lastName;
	String email;

}
