/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.web.staticcontent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Basic integration tests for demo application.
 *
 * @author Dave Syer
 */
 @RunWith(SpringRunner.class)
 @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 @DirtiesContext
// @DirtiesContext is a spring test annotation which is used to indicate that
// the application context cached should be removed and reloaded after each test
// run
public class SampleWebStaticApplicationTests {

	@LocalServerPort
	private int port = 0;

	 @Test
	public void testHome() throws Exception {
		System.out.println("Tests * * * * * * * * * * testHome running  * * * * * * * * * * * * * * * * * ");
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + this.port,
				String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("<title>Static");
	}

	 @Test
	public void testCss() throws Exception {
		System.out.println("Tests * * * * * * * * * * testCss running  * * * * * * * * * * * * * * * * * ");
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.port + "/webjars/bootstrap/3.0.3/css/bootstrap.min.css", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("body");
		assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.valueOf("text/css"));
	}
}
