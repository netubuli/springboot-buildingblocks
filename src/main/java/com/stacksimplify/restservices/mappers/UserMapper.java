package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper{
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	UserMsDto userMsDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMsDto> usersToMsDto(List<User> user);

}
