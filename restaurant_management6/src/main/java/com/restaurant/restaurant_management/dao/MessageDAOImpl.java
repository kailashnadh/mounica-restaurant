package com.restaurant.restaurant_management.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Messages;
@Repository
public class MessageDAOImpl implements MessageDAO {
	private EntityManager entityManager;
	@Autowired
	public MessageDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	@Override
	public List<Messages> getAllMessages() {
		Query theQuery = 
				entityManager.createQuery("from Messages");
		
		// execute query and get result list
		List<Messages> messages = theQuery.getResultList();
	//	 messages.forEach(message -> message.setEmployeeName(message.getEmployee().getFirstname()));
		// return the results		
		return messages;
	}

	@Override
	public Messages findById(Long id) {
		Messages message = 
				entityManager.find(Messages.class, id);
		return message;
	}

	@Override
	public void save(Messages message) {
		Messages dbmessagee = entityManager.merge(message);
		message.setMessage_id(dbmessagee.getMessage_id());

	}

	@Override
	public void deleteMeassageById(Long id) {
		Query theQuery = entityManager.createQuery(
				"delete from Messages where id=:messageId");

theQuery.setParameter("messageId", id);

theQuery.executeUpdate();

	}

}
