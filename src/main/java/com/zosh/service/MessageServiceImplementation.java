package com.zosh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.models.Chat;
import com.zosh.models.Message;
import com.zosh.models.User;
import com.zosh.repository.ChatRepository;
import com.zosh.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		
		Chat chat=chatService.findChatById(chatId);
		Message message=new Message();
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage=messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);

		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessage(Integer chatId) throws Exception {
		
		Chat chat=chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

}
