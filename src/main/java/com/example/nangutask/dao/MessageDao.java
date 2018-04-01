package com.example.nangutask.dao;

import com.example.nangutask.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {

	List<Message> findMessageByAuthor_Id(@Param("authorId") Long authorId);
}
