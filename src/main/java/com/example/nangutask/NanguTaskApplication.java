package com.example.nangutask;

import com.example.nangutask.model.Author;
import com.example.nangutask.model.Message;
import com.example.nangutask.service.AuthorService;
import com.example.nangutask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class NanguTaskApplication {

	private AuthorService authorService;

	private MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(NanguTaskApplication.class, args);
	}


	@PostConstruct
	public void initData() {
		Author authorOne = new Author("authorOne");
		Author authorTwo = new Author("authorTwo");

		authorService.create(authorOne);
		authorService.create(authorTwo);

		Message messageOne = new Message("message text1 authorOne");
		messageOne.setAuthor(authorOne);
		Message messageTwo = new Message("message text2 authorOne");
		messageTwo.setAuthor(authorOne);
		Message messageThree = new Message("message text1 authorTwo");
		messageThree.setAuthor(authorTwo);
		Message messageFour = new Message("message text2 authorTwo");
		messageFour.setAuthor(authorTwo);

		messageService.create(messageOne);
		messageService.create(messageTwo);
		messageService.create(messageThree);
		messageService.create(messageFour);
	}

	@Autowired
	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
