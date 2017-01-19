package com.barrycommins;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		classes = {TestConfig.class, Application.class},
		properties = "server.port=8080")
public class DefaultPortTest {

	@LocalServerPort
	private int port;

	@SuppressWarnings("SpringJavaAutowiredMembersInspection")
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testRedirect() throws Exception {
		final ResponseEntity<String> entity = restTemplate.getForEntity("https://localhost:" + port, String.class);
		assertThat(entity).isNotNull();
	}

}