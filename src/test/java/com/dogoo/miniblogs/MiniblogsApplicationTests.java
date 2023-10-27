package com.dogoo.miniblogs;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiniblogsApplicationTests {

	@InjectMocks
	MiniblogsApplication miniblogsApplication;

	@Test
	void contextLoads() {
	}

	@Test
	void main() {
		MiniblogsApplication.main(new String[] {});
	}

}
