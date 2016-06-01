package sample.web.mvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
// @ComponentScan("sample.web.mvc")
@SpringBootTest // (webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class MyControllerTests {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		System.out.println("Tests * * * * * * * * * * setUp test running  * * * * * * * * * * * * * * * * * ");
		// this.mvc = MockMvcBuilders.standaloneSetup(new
		// MyControllerTests()).build();
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void getHello() throws Exception {
		System.out.println("Tests * * * * * * * * * * getHello test running * * * * * * * * * * * * * * * * * ");
		MockHttpServletRequestBuilder mockHSRBuilder = MockMvcRequestBuilders.get("/hello");
		System.out.println("Tests * * * * * * * * * * mockHSRBuilder * * * * * * * * * * * * * * * * * \n" + mockHSRBuilder);
		String getResult = "Greetings from Spring Boot!";
		mvc.perform(mockHSRBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo(getResult)));
	}
}
