package com.openclassrooms.mddapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MddApiApplicationTests {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Test
	void contextLoads() {
	}

}
