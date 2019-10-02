package com.restaurant.restaurant_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Messages;
import com.restaurant.restaurant_management.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
private MessageService messageService;
@Autowired
public MessageController(MessageService messageService) {
	this.messageService = messageService;
}
@GetMapping("/all")
public Iterable<Messages> allEmployees() {
	return messageService.getAllMessages();
}

@PostMapping("/add")
public Messages addMessage(@RequestBody Messages message) {	
	messageService.save(message);
	return message;
}
@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
	@GetMapping("/{messageId}")
	public Messages employeeById(@PathVariable("messageId") long messageId) {

		return messageService.findById(messageId);
	}

@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{messageId}")
public void deleteEmployee(@PathVariable("messageId") long messageId) {
	messageService.deleteMeassageById(messageId);
}
}
