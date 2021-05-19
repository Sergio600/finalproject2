package com.sergio.converter;

import com.sergio.domain.User;
import com.sergio.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    FeedbackConverter feedbackConverter;
    TicketConverter ticketConverter;
    HistoryConverter historyConverter;
    CommentConverter commentConverter;

    @Autowired
    public UserConverter (@Lazy FeedbackConverter feedbackConverter,
                          TicketConverter ticketConverter,
                          @Lazy HistoryConverter historyConverter,
                          @Lazy CommentConverter commentConverter){
        this.feedbackConverter = feedbackConverter;
        this.ticketConverter = ticketConverter;
        this.historyConverter = historyConverter;
        this.commentConverter = commentConverter;

    }

    public User fromDto (UserDto dto){
        if(dto !=null){
            User user = new User();

            user.setId(dto.getId());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setRole(dto.getRole());
            user.setEmail(dto.getEmail());
            return user;
        } else {
            return new User();
        }
    }


    public UserDto toDto (User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
