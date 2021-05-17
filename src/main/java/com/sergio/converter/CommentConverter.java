package com.sergio.converter;

import com.sergio.domain.Comment;
import com.sergio.domain.Feedback;
import com.sergio.dto.CommentDto;
import com.sergio.dto.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class CommentConverter {

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    UserConverter userConverter;

    public Comment fromDto(CommentDto dto){
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setDate(dto.getDate());
        comment.setText(dto.getText());
        comment.setUser(userConverter.fromDto(dto.getUser()));
        comment.setTicket(ticketConverter.fromDto(dto.getTicket()));
        return comment;
    }

    public CommentDto toDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setDate(comment.getDate());
        commentDto.setText(comment.getText());
        commentDto.setUserDto(userConverter.toDto(comment.getUser()));
        commentDto.setTicket(ticketConverter.toDto(comment.getTicket()));
        return commentDto;
    }

    public List<Comment> fromDtoList(List<CommentDto> dtoList){
        return dtoList
                .stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> toDtoList(List<Comment> list){
        return list
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
