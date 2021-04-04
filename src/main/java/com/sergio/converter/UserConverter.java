package com.sergio.converter;

import com.sergio.domain.User;
import com.sergio.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class UserConverter {

    @Autowired
    FeedbackConverter feedbackConverter;

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    HistoryConverter historyConverter;

    @Autowired
    CommentConverter commentConverter;

    public User fromDto (UserDto dto){
        User user = new User();

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }


    public UserDto toDto (User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
