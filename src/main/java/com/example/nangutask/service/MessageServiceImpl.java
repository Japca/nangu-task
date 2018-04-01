package com.example.nangutask.service;

import com.example.nangutask.dao.MessageDao;
import com.example.nangutask.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;

	@Override
	public List<Message> getAllMessages() {
		return messageDao.findAll();
	}

	@Override
	public List<Message> getMessageByAuthorId(Long authorId) {
		return messageDao.findMessageByAuthor_Id(authorId);
	}

	@Override
	public Message create(Message message) {
		return messageDao.save(message);
	}

	@Override
	public Message update(Message message, Long messageId) {
		Message foundMessage = messageDao.findById(messageId).orElse(null);
		if(foundMessage == null) {
			return null;
		}

		foundMessage.setText(message.getText());
		return messageDao.save(foundMessage);
	}
	@Override
	public void deleteMessage(Long messageId) {
		messageDao.deleteById(messageId);

	}

	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
