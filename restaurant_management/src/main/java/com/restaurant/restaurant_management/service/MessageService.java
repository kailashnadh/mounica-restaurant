package com.restaurant.restaurant_management.service;
import java.util.List;

import com.restaurant.restaurant_management.model.Messages;

public interface MessageService {
	// getMessages 
		// add message
		//get messagebyId
		//update message
		//delete message
	public List<Messages> getAllMessages();
		
		public Messages findById(Long id);
		
		public void save(Messages message);
		
		public void deleteMeassageById(Long id);

}
