package com.example.nangutask.service;

import com.example.nangutask.dao.AuthorDao;
import com.example.nangutask.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorDao authorDao;

	@Override
	public Author create(Author author) {
		return authorDao.saveAndFlush(author);
	}

	@Autowired
	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
}
