package com.aws.spring.demo.usermodule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserModuleApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserModuleApplicationTests.class);

	@Test
	public void contextLoads() {

		LOGGER.debug("Testing contextLoads() method");
		
		int fno = 10;
		int sno = 10;
		
		LOGGER.debug("Is fno[{}] == sno[{}].", fno, sno);
		
		assertEquals(fno, sno);
	}

}
