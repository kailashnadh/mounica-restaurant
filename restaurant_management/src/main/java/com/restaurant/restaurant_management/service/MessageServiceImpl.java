package com.restaurant.restaurant_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.restaurant_management.dao.MessageDAO;
import com.restaurant.restaurant_management.model.Messages;

@Service
public class MessageServiceImpl implements MessageService {
private MessageDAO messageDAO;
@Autowired(required=true) 
public MessageServiceImpl(MessageDAO theMessageDAO) {
	messageDAO = theMessageDAO;
}
	@Override
	public List<Messages> getAllMessages() {
		// TODO Auto-generated method stub
		return messageDAO.getAllMessages();
	}

	@Override
	public Messages findById(Long id) {
		// TODO Auto-generated method stub
		return messageDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Messages message) {
		// TODO Auto-generated method stub
		messageDAO.save(message);

	}

	@Override
	public void deleteMeassageById(Long id) {
		// TODO Auto-generated method stub
		messageDAO.deleteMeassageById(id);

	}

}
