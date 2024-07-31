package com.zosh.service;

import java.util.List;

import com.zosh.models.Story;
import com.zosh.models.User;

public interface StoryService {
	
	public Story creatStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
