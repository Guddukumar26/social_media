package com.zosh.service;

import java.util.List;

import com.zosh.models.Chat;
import com.zosh.models.Message;
import com.zosh.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessage(Integer chatId) throws Exception;

}
