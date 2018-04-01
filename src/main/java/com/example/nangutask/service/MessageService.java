package com.example.nangutask.service;

import com.example.nangutask.model.Message;

import java.util.List;

public interface MessageService {

	List<Message> getAllMessages();

	List<Message> getMessageByAuthorId(Long authorId);

	Message create(Message message);

	Message update(Message message, Long messageId);

	void deleteMessage(Long messageId);
}

