package com.zosh.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String chat_name;
	
	private String chat_image;
	
	@ManyToMany
	private List<User> users=new ArrayList<>();
	
	private LocalDateTime timeStamp;
	
	/*
	 * "chat" this is the define inside Message.java
	 * due to this message_chat table will not created and message will inside the chats table
	 */
	@OneToMany(mappedBy = "chat")
	private List<Message> messages=new ArrayList<>();
	
	
	
	
	

}
