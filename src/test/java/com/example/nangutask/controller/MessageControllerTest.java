package com.example.nangutask.controller;

import com.example.nangutask.model.Author;
import com.example.nangutask.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {

	private WebApplicationContext context;

	private MockMvc mvc;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}

	@Test
	public void getAllMessagesTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/messages"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("message text1 authorOne")));
	}

	@Test
	public void getAllMessagesByAuthorIdTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/authorMessages").param("authorId", "1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("message text1 authorOne")));
	}

	@Test
	public void createMessageTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/message")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(createMessage())))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Test message")));
	}

	@Test
	public void updateMessageTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/message/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(createMessage())))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Test message")));
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/message/4"))
				.andExpect(status().isOk());
	}

	private Message createMessage() {
		Message message = new Message();
		message.setText("Test message");

		Author author = new Author();
		author.setId(1L);
		message.setAuthor(author);
		return message;
	}


	@Autowired
	public void setContext(WebApplicationContext context) {
		this.context = context;
	}
}
