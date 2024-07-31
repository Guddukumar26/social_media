package com.zosh.service;

import java.util.List;

import com.zosh.models.Chat;
import com.zosh.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2 );
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);

}
