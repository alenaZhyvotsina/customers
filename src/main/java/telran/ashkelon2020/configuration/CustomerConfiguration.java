package telran.ashkelon2020.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {
	
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setSkipNullEnabled(true)
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		return modelMapper;
	}

}
