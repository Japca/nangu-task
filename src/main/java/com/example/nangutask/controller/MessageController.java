package com.example.nangutask.controller;

import com.example.nangutask.model.Message;
import com.example.nangutask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class MessageController {

	private MessageService messageService;

	@GetMapping("messages")
	public List<Message> getAllMessages() {
			return messageService.getAllMessages();
	}

	@GetMapping("authorMessages")
	public List<Message> getAllMessagesByAuthorId(@RequestParam("authorId") Long authorId) {
		if(authorId == null) {
			return Collections.emptyList();
		}
		return messageService.getMessageByAuthorId(authorId);
	}

	@PostMapping("message")
	public Message createMessage(@Valid @RequestBody Message message) {
		return messageService.create(message);
	}

	@PutMapping("message/{id}")
	public Message updateMessage(@Valid @RequestBody Message message, @PathVariable("id") Long messageId) {
		return messageService.update(message, messageId);
	}


	@DeleteMapping("message/{id}")
	public void deleteMessage(@PathVariable("id") Long messageId) {
		if(messageId == null) {
			throw new IllegalArgumentException("message id cannot be null!");
		}
		messageService.deleteMessage(messageId);
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
