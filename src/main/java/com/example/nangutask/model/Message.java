package com.example.nangutask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Message {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
	@SequenceGenerator(allocationSize = 1, name = "message_id_seq", sequenceName = "message_id_seq")
	private Long id;

	private String text;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	@JsonIgnoreProperties("messages")
	@NotNull
	private Author author;

	public Message() {
	}

	public Message( String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
